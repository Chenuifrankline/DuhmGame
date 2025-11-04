#ifndef SYSTICK_H
#define SYSTICK_H

#include <stdint.h>

extern volatile int do_draw;
extern volatile int timer_counter;
extern volatile int fps_display;
extern volatile int fps_counter;

uint32_t SysTick_Config(uint32_t ticks);

void sys_tick_handler(void);

#endif // SYSTICK_H
