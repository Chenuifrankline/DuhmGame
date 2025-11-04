#include "Ai.h"

void Duhm_Ai_update(Duhm_Ai* const me, float dt) {
	Duhm_Object *obj = me->object;
	me->timer += random_float() * dt;
	if (1.0f <= me->timer) {
	    me->timer = 0.0f;
	    uint32_t rand = random_uint32();
	    switch (rand & 3) {
	        case 0: {
	            // Jump
	            if (0.0f == obj->pos[2]) {
	                obj->vel[2] = 2.0f;
	            }
	            me->state = Duhm_AiState_Idle;
	        } break;
	        case 1:
	        case 2: {
	            me->target_pos[0] = obj->pos[0] + (float)((rand >>  1) & 0x3FF) / 512.0f - 1.0f;
	            me->target_pos[1] = obj->pos[1] + (float)((rand >> 11) & 0x3FF) / 512.0f - 1.0f;
	            float dx = me->target_pos[0] - obj->pos[0];
	            float dy = me->target_pos[1] - obj->pos[1];
	            float dist = sqrtf(dx * dx + dy * dy);
	            me->object->view[0] = dx / dist;
	            me->object->view[1] = dy / dist;
	            me->state = Duhm_AiState_Wander;
	        } break;
	        case 3: {
	            me->state = Duhm_AiState_Hunt;
	            float min_dist = 100.0f;
	            for (int i = 0; i < 2; ++i) {
	                float dx = me->possible_targets[i]->pos[0] - obj->pos[0];
	                float dy = me->possible_targets[i]->pos[1] - obj->pos[1];
	                float dist_sq = dx * dx + dy * dy;
	                if (dist_sq < min_dist) {
	                    min_dist = dist_sq;
	                    me->target = me->possible_targets[i];
	                }
	            }
	        } break;
	    }
	}
	
	switch (me->state) {
	    case Duhm_AiState_Idle: {
	
	    } break;
	    case Duhm_AiState_Wander: {
	        obj->vel[0] = fminf(fmaxf(me->target_pos[0] - obj->pos[0], -0.5f), 0.5f);
	        obj->vel[1] = fminf(fmaxf(me->target_pos[1] - obj->pos[1], -0.5f), 0.5f);
	    } break;
	    case Duhm_AiState_Hunt: {
	        float dx = me->target->pos[0] - obj->pos[0];
	        float dy = me->target->pos[1] - obj->pos[1];
	        float dist = sqrtf(dx * dx + dy * dy);
	        me->object->view[0] = dx / dist;
	        me->object->view[1] = dy / dist;
	        obj->vel[0] = me->object->view[0] * 1.5f;
	        obj->vel[1] = me->object->view[1] * 1.5f;
	        if (dist < 0.5f && 0.0f == obj->pos[2] && 0.25f < me->target->pos[2]) {
	            obj->vel[2] = 2.0f;
	        }
	    } break;
	}
	
	if (0.01f < obj->pos[2]) {
	    // Object is jumping
	    obj->texture = me->textures[1];
	    obj->walk_dist = 0.0f;
	} else {
	    float dist = sqrtf(obj->vel[0] * obj->vel[0] + obj->vel[1] * obj->vel[1]) * dt;
	    if (0.005f < dist) {
	        // Object is moving
	        obj->walk_dist += dist;
	        // The walk cycle repeats 4 times per tile, so we limit the
	        // value to 0.25f, to avoid the value potentially growing
	        // a lot and losing precision.
	        while (0.25f <= obj->walk_dist) obj->walk_dist -= 0.25f;
	        int walk_cycle = (int)(obj->walk_dist * 12.0f);
	        obj->texture = me->textures[2 + walk_cycle];
	    } else {
	        // Object is standing
	        obj->walk_dist = 0.0f;
	        obj->texture = me->textures[0];
	    }
	}
}
Duhm_Ai Duhm_mario0ai = {
	.object = &Duhm_mario0,
	.textures = {
		&Duhm_texMario,
		&Duhm_texMarioJump,
		&Duhm_texMarioWalk0,
		&Duhm_texMarioWalk1,
		&Duhm_texMarioWalk2
	},
	.possible_targets = {
		&Duhm_ball0,
		&Duhm_ball1
	}
};

Duhm_Ai Duhm_mario1ai = {
	.object = &Duhm_mario1,
	.textures = {
		&Duhm_texMario,
		&Duhm_texMarioJump,
		&Duhm_texMarioWalk0,
		&Duhm_texMarioWalk1,
		&Duhm_texMarioWalk2
	},
	.possible_targets = {
		&Duhm_ball0,
		&Duhm_ball1
	}
};
