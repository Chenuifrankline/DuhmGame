#include "World.h"

int32_t Duhm_World_get_tile_type_at(Duhm_World* const me, int32_t pos_x, int32_t pos_y) {
	if (pos_x < 0 || me->width <= pos_x || pos_y < 0 || me->height <= pos_y) return 0;
	return me->cells[pos_y * me->width + pos_x];
}

int32_t Duhm_World_raycast(Duhm_World* const me, float pos_x, float pos_y, float dir_x, float dir_y, float* result_dist, Duhm_Texture** result_tile_type) {
	float dist = 0;
	int sign_x = (dir_x < 0 ? -1 : 1);
	int sign_y = (dir_y < 0 ? -1 : 1);
	int int_pos_x = rintf(pos_x);
	int int_pos_y = rintf(pos_y);
	int tile_type = 0;
	do {
	    float x_in_cell = pos_x - (float)int_pos_x;
	    float y_in_cell = pos_y - (float)int_pos_y;
	    float dtx = ((float)sign_x * 0.5f - x_in_cell) / dir_x;
	    float dty = ((float)sign_y * 0.5f - y_in_cell) / dir_y;
	    if (dtx * dtx < dty * dty) {
	        // step in x
	        pos_x += dir_x * dtx;
	        pos_y += dir_y * dtx;
	        int_pos_x += sign_x;
	        dist += fabsf(dtx);
	    } else {
	        // step in y
	        pos_x += dir_x * dty;
	        pos_y += dir_y * dty;
	        int_pos_y += sign_y;
	        dist += fabsf(dty);
	    }
	    if (10.0f /*MAX_DEPTH*/ <= dist) return 0;
	    tile_type = Duhm_World_get_tile_type_at(me, int_pos_x, int_pos_y);
	} while (0 == tile_type);
	if (result_dist) *result_dist = dist;
	if (result_tile_type) {
	    *result_tile_type = tile_type ? me->textures[tile_type - 1] : 0;
	}
	return 1;
}
Duhm_World Duhm_world = {
	.width = world_width,
	.height = world_height,
	.sky_color = {0xC79F},
	.floor_color = {0x9726},
	.fog_color = {0xDFBF},
	.ceil_height = 1.0,
	.floor_height = 0.0,
	.cells = world_data,
	.textures = {
		&Duhm_texCobblestone,
		&Duhm_texDirt
	}
};
