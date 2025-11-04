#ifndef DUHM_CONTROLS_H
#define DUHM_CONTROLS_H

#include "Object.h"

#include <adc.h>
#include <gpio.h>
#include <stdint.h>

typedef struct Duhm_Controls_struct {
	Duhm_Object* player;
	int32_t prev_z;
	int32_t prev_b;
	float view_angle;
} Duhm_Controls;

void Duhm_Controls_process_inputs(Duhm_Controls* const me, float dt);

void Duhm_Controls_init(Duhm_Controls* const me);

extern Duhm_Controls Duhm_controls;

#endif
