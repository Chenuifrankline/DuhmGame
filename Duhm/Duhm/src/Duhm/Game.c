#include "Game.h"

void Duhm_Game_init(Duhm_Game* const me) {
	SysTick_Config(16000000 / 50 /*FPS*/);
	
	lcd_init();
	
	for (int i = 0; i < 2; ++i) {
	    Duhm_Ai *ai  = me->ais[i];
	    ai->target_pos[0] = ai->object->pos[0];
	    ai->target_pos[1] = ai->object->pos[1];
	}
	
	for (int i = 0; i < LCD_WIDTH; ++i) {
	    float f = ((float)i / (float)LCD_WIDTH - 0.5f) * 3.142f * 0.25f;
	    // To avoid the fisheye effect, we're not creating normalized vectors
	    // here, but direction vectors with the x component normalized to 1.
	    // Note that sin(x)/cos(x) == tan(x)
	    me->fov_lookup[i * 2 + 0] = 1.0f;//cosf(f);
	    me->fov_lookup[i * 2 + 1] = tanf(f);//sinf(f);
	}
}

void Duhm_Game_collision_detection(Duhm_Game* const me) {
	// Collision detection
	for (int i = 0; i < 6; ++i) {
	    Duhm_Object *obj = me->objects[i];
	    float radius0 = obj->size[0] * 0.5f;
	    float mass0   = obj->size[0] * obj->size[1];
	
	    // Object vs object
	    if (!(obj->flags & 0x01 /*Object_Flags_No_Collision*/)) {
	        for (int j = i + 1; j < 6; ++j) {
	            Duhm_Object *other = me->objects[j];
	            float radius1   = other->size[0] * 0.5f;
	            float mass1     = other->size[0] * other->size[1];
	            if (!(other->flags & 0x01 /*Object_Flags_No_Collision*/)) {
	                float min_dist = radius0 + radius1;
	                float dx = obj->pos[0] - other->pos[0];
	                float dy = obj->pos[1] - other->pos[1];
	                float dist_sq = dx * dx + dy * dy;
	                if (dist_sq < min_dist * min_dist) {
	                    float dist = sqrtf(dist_sq);
	                    dx /= dist;
	                    dy /= dist;
	                    float dv = (other->vel[0] - obj->vel[0]) * dx
	                             + (other->vel[1] - obj->vel[1]) * dy;
	
	                    float f0 = (min_dist - dist) * (mass1 / (mass0 + mass1));
	                    if (obj->flags & 0x04 /*Object_Flags_Bouncy*/) {
	                        obj->vel[0] += dx * dv * f0;
	                        obj->vel[1] += dy * dv * f0;
	                    }
	                    obj->pos[0] += dx * (min_dist - dist) * f0;
	                    obj->pos[1] += dy * (min_dist - dist) * f0;
	
	                    float f1 = (min_dist - dist) * (mass0 / (mass0 + mass1));
	                    if (other->flags & 0x04 /*Object_Flags_Bouncy*/) {
	                        other->vel[0] -= dx * dv * f1;
	                        other->vel[1] -= dy * dv * f1;
	                    }
	                    other->pos[0] -= dx * (min_dist - dist) * f1;
	                    other->pos[1] -= dy * (min_dist - dist) * f1;
	                }
	            }
	        }
	    }
	
	    // Object vs world
	    int min_x = rintf(obj->pos[0] - radius0);
	    int min_y = rintf(obj->pos[1] - radius0);
	    int max_x = rintf(obj->pos[0] + radius0);
	    int max_y = rintf(obj->pos[1] + radius0);
	    for (int y = min_y; y <= max_y; ++y) {
	        for (int x = min_x; x <= max_x; ++x) {
	            if (0 != Duhm_World_get_tile_type_at(me->world, x, y)) {
	                float dx = fminf(fmaxf(obj->pos[0], (float)x - 0.5f), (float)x + 0.5f) - obj->pos[0];
	                float dy = fminf(fmaxf(obj->pos[1], (float)y - 0.5f), (float)y + 0.5f) - obj->pos[1];
	                float dist_sq = dx * dx + dy * dy;
	                if (dist_sq < radius0 * radius0) {
	                    float dist = sqrtf(dist_sq);
	                    dx /= dist;
	                    dy /= dist;
	                    float f = (radius0 - dist);
	                    obj->pos[0] -= dx * f;
	                    obj->pos[1] -= dy * f;
	                    float vel_into_wall = fmaxf(0.0f, obj->vel[0] * dx + obj->vel[1] * dy);
	                    if (obj->flags & 0x04 /*Object_Flags_Bouncy*/) {
	                        obj->vel[0] -= 2.0f * dx * vel_into_wall;
	                        obj->vel[1] -= 2.0f * dy * vel_into_wall;
	                    } else {
	                        obj->vel[0] -= dx * vel_into_wall;
	                        obj->vel[1] -= dy * vel_into_wall;
	                    }
	                }
	            }
	        }
	    }
	}
}

void Duhm_Game_draw(Duhm_Game* const me) {
	// Drawing
	float cam_x = me->player->pos[0];
	float cam_y = me->player->pos[1];
	float cam_z = me->player->pos[2] + me->player->size[1] * 0.9f;
	
	// Ceiling and floor lookup.
	{
	    for (int i = 0; i < LCD_HEIGHT / 2; ++i) {
	        float dist = (me->world->ceil_height - cam_z) * (float)LCD_HEIGHT / 2.0f / ((float)LCD_HEIGHT / 2.0f - (float)i);
	        uint8_t p = (uint8_t)(255.0f * dist / 10.0f /*MAX_DEPTH*/);
	        me->ceil_floor_lookup[i] = col_lerp(me->world->sky_color, me->world->fog_color, p);
	    }
	    for (int i = LCD_HEIGHT / 2; i < LCD_HEIGHT; ++i) {
	        float dist = (cam_z - me->world->floor_height) * (float)LCD_HEIGHT / 2.0f / ((float)i - (float)LCD_HEIGHT / 2.0f);
	        uint8_t p = (uint8_t)(255.0f * dist / 10.0f /*MAX_DEPTH*/);
	        me->ceil_floor_lookup[i] = col_lerp(me->world->floor_color, me->world->fog_color, p);
	    }
	}
	
	// Build a list of sprites to draw.
	int num_sprites = 0;
	int num_shadows = 0;
	Duhm_Sprite sprites[6];
	Duhm_Sprite shadows[6];
	for (int i = 0; i < 6; ++i) {
	    Duhm_Object *obj = me->objects[i];
	    if (!obj->texture) continue;
	
	    float dx = obj->pos[0] - cam_x;
	    float dy = obj->pos[1] - cam_y;
	    float dz = obj->pos[2] + obj->size[1] - cam_z;
	    float dist = me->player->view[0] * dx + me->player->view[1] * dy;
	    if (10.0f /*MAX_DEPTH*/ <= dist || dist <= 0.1f) continue;
	
	    float x0 = 0.5f + (me->player->view[0] * dy - me->player->view[1] * dx - obj->size[0] * 0.5f) * 1.25f / dist;
	    float y0 = 0.5f - dz / dist;
	    float x1 = x0 + obj->size[0] * 1.25f / dist;
	    float y1 = y0 + obj->size[1] / dist;
	    if (x1 <= 0.0f || 1.0f <= x0) continue;
	    if (y1 <= 0.0f || 1.0f <= y0) continue;
	
	    int flip_x = (me->player->view[0] * obj->view[1] - me->player->view[1] * obj->view[0] < 0) ? 1 : 0;
	
	    sprites[num_sprites] = (Duhm_Sprite){
	        .distance = dist,
	        .bounds = {
	            (int)((float)LCD_WIDTH  * x0),
	            (int)((float)LCD_HEIGHT * y0),
	            (int)((float)LCD_WIDTH  * x1),
	            (int)((float)LCD_HEIGHT * y1)
	        },
	        .texture = obj->texture,
	        .flags = (flip_x ? 0x01 /*Sprite_Flags_H_Flip*/ : 0)
	    };
	    me->sprite_order[num_sprites] = num_sprites;
	    num_sprites += 1;
	
	    if (!(obj->flags & 0x02 /*Object_Flags_Shadow*/)) continue;
	
	    float shadow_size = obj->size[0] * 0.8f / (1.0f + obj->pos[2]);
	    float sx0 = 0.5f + (-me->player->view[1] * dx + me->player->view[0] * dy - shadow_size * 0.5f) * 1.25f / dist;
	    float sx1 = x0 + shadow_size * 1.25f / dist;
	    float sy0 = 0.5f + cam_z / (dist + shadow_size * 0.5f);
	    float sy1 = 0.5f + cam_z / (dist - shadow_size * 0.5f);
	
	    if (1.0f <= sy0) continue;                
	
	    shadows[num_shadows] = (Duhm_Sprite){
	        .distance = dist,
	        .bounds = {
	            (int)((float)LCD_WIDTH  * sx0),
	            (int)((float)LCD_HEIGHT * sy0),
	            (int)((float)LCD_WIDTH  * sx1),
	            (int)((float)LCD_HEIGHT * sy1)
	        },
	        .texture = 0, // shadows use a hard-coded texture
	        .flags = 0
	    };
	    num_shadows += 1;
	}
	
	// Sort the visible objects by depth, so that the closest will be drawn last, covering
	// objects behind it.
	{
	    int last_unsorted_index = num_sprites - 1;
	    int last_swapped_index;
	    do {
	        last_swapped_index = -1;
	        for (int i = 0; i < last_unsorted_index; ++i) {
	            if (sprites[me->sprite_order[i]].distance < sprites[me->sprite_order[i + 1]].distance) {
	                int32_t tmp = me->sprite_order[i];
	                me->sprite_order[i] = me->sprite_order[i + 1];
	                me->sprite_order[i + 1] = tmp;
	                last_swapped_index = i;
	            }
	        }
	        last_unsorted_index = last_swapped_index;
	    } while (0 <= last_swapped_index);
	}
	
	// Go through the columns of the display and render the scene.
	for (int x = 0; x < LCD_WIDTH; ++x) {
	    color_t *column = (x & 1 ? me->column_a : me->column_b);
	
	    float dir_x = me->player->view[0] * me->fov_lookup[x * 2 + 0] - me->player->view[1] * me->fov_lookup[x * 2 + 1];
	    float dir_y = me->player->view[1] * me->fov_lookup[x * 2 + 0] + me->player->view[0] * me->fov_lookup[x * 2 + 1];
	
	    // draw the walls
	    int   wall_top  = LCD_HEIGHT / 2;
	    int   wall_bot  = LCD_HEIGHT / 2;
	    float wall_dist = 10.0f /*MAX_DEPTH*/;
	    Duhm_Texture* texture;
	    if (Duhm_World_raycast(me->world, cam_x, cam_y, dir_x, dir_y, &wall_dist, &texture)) {
	
	        wall_top -= (int)(( me->world->ceil_height - cam_z) / wall_dist * (float)LCD_HEIGHT);
	        wall_bot += (int)((cam_z - me->world->floor_height) / wall_dist * (float)LCD_HEIGHT);
	        int wall_height = wall_bot - wall_top;
	
	        int min_y = (wall_top < 0 ? 0 : wall_top);
	        int max_y = (LCD_HEIGHT < wall_bot ? LCD_HEIGHT : wall_bot);
	
	        uint8_t shade = (uint8_t)fminf(255.0f, 255.0f * wall_dist / 10.0f /*MAX_DEPTH*/);
	        uint8_t inv_shade = 255 - shade;
	        color_t fog = col_multiply_scalar(me->world->fog_color, shade);
	        float p = cam_x + dir_x * wall_dist + cam_y + dir_y * wall_dist;
	
	        int tw = texture->width;
	        int th = texture->height;
	        int tx = (int)((p - floor(p)) * (float)tw);
	        const color_t *tex_column = &texture->data[tx * th];
	        int ty = (min_y - wall_top) * th / wall_height;
	        int max_ty = (max_y - wall_top) * th / wall_height;
	        int y = min_y;
	        // Draw all texture pixels that are definitely on the screen.
	        for (; ty < max_ty; ++ty) {
	            int next_y = (ty + 1) * wall_height / th + wall_top;
	            color_t color = col_add(col_multiply_scalar(tex_column[ty], inv_shade), fog);
	            for (; y < next_y; ++y) {
	                column[y] = color;
	            }
	        }
	        // If the last texture pixel was cut off by the bottom of the
	        // screen, the loop above won't draw it and we handle it here.
	        // This is more efficient than constantly checking in the loop
	        // above if y is off-screen.
	        if (y < max_y) {
	            color_t color = col_add(col_multiply_scalar(tex_column[ty], inv_shade), fog);
	            for (; y < LCD_HEIGHT; ++y) {
	                column[y] = color;
	            }
	        }
	    }
	
	    // Draw the floor and ceiling
	    for (int i = 0; i < wall_top; ++i) {
	        column[i] = me->ceil_floor_lookup[i];
	    }
	    for (int i = wall_bot; i < LCD_HEIGHT; ++i) {
	        column[i] = me->ceil_floor_lookup[i];
	    }
	
	#if 1 // draw shadows
	    for (int i = 0; i < num_shadows; ++i) {
	
	        if (wall_dist < shadows[i].distance) continue;
	        if (x < shadows[i].bounds[0] || shadows[i].bounds[2] <= x) continue;
	
	        int sx = shadows[i].bounds[0];
	        int sy = shadows[i].bounds[1];
	        int sw = shadows[i].bounds[2] - sx;
	        int sh = shadows[i].bounds[3] - sy;
	
	        int tw = shadow_width;
	        int th = shadow_height;
	        int tx = (x - sx) * tw / sw;
	        const uint8_t *tex_column = &shadow_data[tx * th];
	        int min_y = (sy < 0 ? 0 : sy);
	        int max_y = (sy + sh < LCD_HEIGHT ? sy + sh: LCD_HEIGHT);
	        int ty = (min_y - sy) * th / sh;
	        int max_ty = (max_y - sy) * th / sh;
	        int y = min_y;
	        // See wall drawing code for why we have a for followed by
	        // an if for the last pixel.
	        for (; ty < max_ty; ++ty) {
	            int next_y = (ty + 1) * sh / th + sy;
	            // Technically this is wrong because column[y] changes for every y.
	            // But these changes and the difference between y and next_y are
	            // so small that it is not noticeable.
	            color_t color = col_multiply_scalar(column[y], tex_column[ty]);
	            for (;y < next_y; ++y) {
	                column[y] = color;
	            }
	        }
	        if (y < max_y) {
	            // Same as above.
	            color_t color = col_multiply_scalar(column[y], tex_column[ty]);
	            for (;y < LCD_HEIGHT; ++y) {
	                column[y] = color;
	            }
	        }
	    }
	#endif
	
	    // draw objects
	    for (int i = 0; i < num_sprites; ++i) {
	        const Duhm_Sprite *sprite = &sprites[me->sprite_order[i]];
	        if (wall_dist < sprite->distance) continue;
	        if (x < sprite->bounds[0] || sprite->bounds[2] <= x) continue;
	
	        int sx = sprite->bounds[0];
	        int sy = sprite->bounds[1];
	        int sw = sprite->bounds[2] - sx;
	        int sh = sprite->bounds[3] - sy;
	
	        int tw = sprite->texture->width;
	        int th = sprite->texture->height;
	        uint8_t shade = (uint8_t)fminf(255.0f, 255.0f * sprite->distance / 10.0f /*MAX_DEPTH*/);
	        uint8_t inv_shade = 255 - shade;
	        color_t fog = col_multiply_scalar(me->world->fog_color, shade);
	        int tx = (x - sx) * tw / sw;
	        if (sprite->flags & 0x01 /*Sprite_Flags_H_Flip*/) tx = sprite->texture->width - tx - 1;
	        const color_t *tex_column = &sprite->texture->data[tx * th];
	        int min_y = (sy < 0 ? 0 : sy);
	        int max_y = (sy + sh < LCD_HEIGHT ? sy + sh : LCD_HEIGHT);
	        int ty = (min_y - sy) * th / sh;
	        int max_ty = (max_y - sy) * th / sh;
	        int y = min_y;
	        // See wall drawing code for why we have a for followed by
	        // an if for the last pixel.
	        for (; ty < max_ty; ++ty) {
	            int next_y = (ty + 1) * sh / th + sy;
	            color_t col = tex_column[ty];
	            if (col.val == 0xF81F) {
	                y = next_y;
	                continue;
	            }
	            color_t color = col_add(col_multiply_scalar(col, inv_shade), fog);
	            for (;y < next_y; ++y) {
	                column[y] = color;
	            }
	        }
	        if (y < max_y) {
	            color_t col = tex_column[ty];
	            if (col.val != 0xF81F) {
	                color_t color = col_add(col_multiply_scalar(col, inv_shade), fog);
	                for (;y < LCD_HEIGHT; ++y) {
	                    column[y] = color;
	                }
	            }
	        }
	    }
	
	    lcd_bitmap(x, 0, column, 1, LCD_HEIGHT);
	}
}

void Duhm_Game_run(Duhm_Game* const me) {
	Duhm_Game_init(me);
	Duhm_Controls_init(me->controls);
	
	while (1) {
	    while (!do_draw);
	    do_draw = 0;
	    fps_counter += 1;
	
	    Duhm_Controls_process_inputs(me->controls, 1.0f / 50.0f);
	    for (int i = 0; i < 2; ++i) {
	        Duhm_Ai_update(me->ais[i], 1.0f / 50.0f);
	    }
	    for (int i = 0; i < 6; ++i) {
	        Duhm_Object_update(me->objects[i], 1.0f / 50.0f);
	    }
	    Duhm_Game_collision_detection(me);
	    Duhm_Game_draw(me);
	}
}
Duhm_Game Duhm_game = {
	.world = &Duhm_world,
	.player = &Duhm_player,
	.objects = {
		&Duhm_player,
		&Duhm_mario0,
		&Duhm_ball0,
		&Duhm_mario1,
		&Duhm_ball1,
		&Duhm_sun
	},
	.controls = &Duhm_controls,
	.ais = {
		&Duhm_mario0ai,
		&Duhm_mario1ai
	}
};
