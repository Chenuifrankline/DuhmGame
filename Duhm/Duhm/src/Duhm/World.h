#ifndef DUHM_WORLD_H
#define DUHM_WORLD_H

#include "Texture.h"

#include <color.h>
#include <math.h>
#include <stdint.h>
#include <stdint.h>
#include <world.h>

typedef struct Duhm_World_struct {
	int32_t width;
	int32_t height;
	uint8_t* cells;
	float ceil_height;
	float floor_height;
	color_t sky_color;
	color_t floor_color;
	color_t fog_color;
	Duhm_Texture* textures[2];
} Duhm_World;

int32_t Duhm_World_get_tile_type_at(Duhm_World* const me, int32_t pos_x, int32_t pos_y);

int32_t Duhm_World_raycast(Duhm_World* const me, float pos_x, float pos_y, float dir_x, float dir_y, float* result_dist, Duhm_Texture** result_tile_type);

extern Duhm_World Duhm_world;

#endif
