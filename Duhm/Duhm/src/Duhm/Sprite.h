#ifndef DUHM_SPRITE_H
#define DUHM_SPRITE_H

#include "Sprite.h"
#include "Texture.h"

#include <stdint.h>

typedef struct Duhm_Sprite_struct {
	float distance;
	int32_t bounds[4];
	Duhm_Texture* texture;
	int32_t flags;
} Duhm_Sprite;

#endif
