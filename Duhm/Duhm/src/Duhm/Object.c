#include "Object.h"

void Duhm_Object_update(Duhm_Object* const me, float dt) {
	me->pos[0] += me->vel[0] * dt;
	me->pos[1] += me->vel[1] * dt;
	me->pos[2] += me->vel[2] * dt;
	if (0.0f < me->pos[2] && !(me->flags & 0x08 /*Object_Flags_Frozen*/)) {
	    me->vel[2] -= 4.0f * dt; // gravity
	}
	if (me->pos[2] < 0.0f) {
	    if (me->flags & 0x04 /*Object_Flags_Bouncy*/) {
	        me->vel[2] = fabsf(me->vel[2]);
	    } else {
	        me->vel[2] = 0.0f;
	        me->pos[2] = 0.0f;
	    }
	}
}
Duhm_Object Duhm_player = {
	.view = {
		1.0,
		0.0
	},
	.pos = {
		3.5,
		3.5,
		0.0
	},
	.size = {
		0.5,
		0.5
	}
};

Duhm_Object Duhm_mario0 = {
	.texture = &Duhm_texMario,
	.view = {
		1.0,
		0.0
	},
	.flags = 0x02 /*Object_Flags_Shadow*/,
	.pos = {
		2.0,
		2.0,
		0.0
	},
	.size = {
		0.4,
		0.53
	}
};

Duhm_Object Duhm_mario1 = {
	.texture = &Duhm_texMario,
	.view = {
		1.0,
		0.0
	},
	.flags = 0x02 /*Object_Flags_Shadow*/,
	.pos = {
		5.0,
		2.0,
		0.0
	},
	.size = {
		0.4,
		0.53
	}
};

Duhm_Object Duhm_ball0 = {
	.texture = &Duhm_texBall,
	.flags = 0x02 /*Object_Flags_Shadow*/ | 0x04 /*Object_Flags_Bouncy*/,
	.pos = {
		4.0,
		3.5,
		0.5
	},
	.size = {
		0.2,
		0.2
	}
};

Duhm_Object Duhm_ball1 = {
	.texture = &Duhm_texBall,
	.flags = 0x02 /*Object_Flags_Shadow*/ | 0x04 /*Object_Flags_Bouncy*/,
	.pos = {
		3.0,
		3.0,
		0.5
	},
	.size = {
		0.2,
		0.2
	}
};

Duhm_Object Duhm_sun = {
	.texture = &Duhm_texSun,
	.flags = 0x01 /*Object_Flags_No_Collision*/ | 0x08 /*Object_Flags_Frozen*/,
	.pos = {
		4.5,
		9.0,
		3.0
	},
	.size = {
		1.0,
		1.0
	}
};
