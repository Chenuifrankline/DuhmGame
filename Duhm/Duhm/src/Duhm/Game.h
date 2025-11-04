#ifndef DUHM_GAME_H
#define DUHM_GAME_H

#include "Ai.h"
#include "Controls.h"
#include "Object.h"
#include "Sprite.h"
#include "World.h"

#include <TM4C123GH6PM.h>
#include <color.h>
#include <lcd.h>
#include <shadow.h>
#include <stdint.h>
#include <systick.h>

typedef struct Duhm_Game_struct {
	Duhm_Object* objects[6];
	Duhm_Ai* ais[2];
	Duhm_World* world;
	Duhm_Object* player;
	color_t ceil_floor_lookup[128];
	int32_t sprite_order[6];
	color_t column_a[128];
	color_t column_b[128];
	float fov_lookup[256];
	Duhm_Controls* controls;
} Duhm_Game;

void Duhm_Game_init(Duhm_Game* const me);

void Duhm_Game_collision_detection(Duhm_Game* const me);

void Duhm_Game_draw(Duhm_Game* const me);

void Duhm_Game_run(Duhm_Game* const me);

extern Duhm_Game Duhm_game;

#endif
