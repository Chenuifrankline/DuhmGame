#ifndef DUHM_OBJECT_H
#define DUHM_OBJECT_H

#include "Texture.h"

#include <math.h>
#include <stdint.h>

typedef struct Duhm_Object_struct {
	float pos[3];
	float size[2];
	Duhm_Texture* texture;
	float view[2];
	uint32_t flags;
	float vel[3];
	float walk_dist;
} Duhm_Object;

void Duhm_Object_update(Duhm_Object* const me, float dt);

extern Duhm_Object Duhm_player;
extern Duhm_Object Duhm_mario0;
extern Duhm_Object Duhm_mario1;
extern Duhm_Object Duhm_ball0;
extern Duhm_Object Duhm_ball1;
extern Duhm_Object Duhm_sun;

#endif
