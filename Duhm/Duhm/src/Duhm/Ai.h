#ifndef DUHM_AI_H
#define DUHM_AI_H

#include "AiState.h"
#include "Object.h"
#include "Texture.h"

#include <math.h>
#include <random.h>

typedef struct Duhm_Ai_struct {
	Duhm_Object* object;
	float timer;
	Duhm_Object* target;
	float target_pos[2];
	Duhm_AiState state;
	Duhm_Texture* textures[5];
	Duhm_Object* possible_targets[2];
} Duhm_Ai;

void Duhm_Ai_update(Duhm_Ai* const me, float dt);

extern Duhm_Ai Duhm_mario0ai;
extern Duhm_Ai Duhm_mario1ai;

#endif
