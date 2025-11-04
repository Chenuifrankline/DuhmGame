#ifndef DUHM_AISTATE_H
#define DUHM_AISTATE_H

typedef enum {
	Duhm_AiState_Idle,
	Duhm_AiState_Wander,
	Duhm_AiState_Hunt
} Duhm_AiState;

extern Duhm_AiState Duhm_AiState_Literals[3];

#endif
