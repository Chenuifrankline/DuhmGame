#ifndef DUHM_TEXTURE_H
#define DUHM_TEXTURE_H

#include <ball.h>
#include <cobblestone.h>
#include <color.h>
#include <dirt.h>
#include <mario.h>
#include <stdint.h>
#include <sun.h>

typedef struct Duhm_Texture_struct {
	int32_t width;
	int32_t height;
	color_t* data;
} Duhm_Texture;

extern Duhm_Texture Duhm_texBall;
extern Duhm_Texture Duhm_texCobblestone;
extern Duhm_Texture Duhm_texDirt;
extern Duhm_Texture Duhm_texMario;
extern Duhm_Texture Duhm_texMarioJump;
extern Duhm_Texture Duhm_texMarioWalk0;
extern Duhm_Texture Duhm_texMarioWalk1;
extern Duhm_Texture Duhm_texMarioWalk2;
extern Duhm_Texture Duhm_texSun;

#endif
