#ifndef MARIO_H
#define MARIO_H

#include "color.h"

#define mario_width  16
#define mario_height 16
extern color_t mario_data[mario_width * mario_height];

#define mario_jump_width  16
#define mario_jump_height 16
extern color_t mario_jump_data[mario_jump_width * mario_jump_height];

#define mario_walk0_width  16
#define mario_walk0_height 16
extern color_t mario_walk0_data[mario_walk0_width * mario_walk0_height];

#define mario_walk1_width  16
#define mario_walk1_height 16
extern color_t mario_walk1_data[mario_walk1_width * mario_walk1_height];

#define mario_walk2_width  16
#define mario_walk2_height 16
extern color_t mario_walk2_data[mario_walk2_width * mario_walk2_height];

#endif // MARIO_H
