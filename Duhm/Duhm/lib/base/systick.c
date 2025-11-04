#include "systick.h"

volatile int do_draw;
volatile int timer_counter;
volatile int fps_display;
volatile int fps_counter;

#define NVIC_ST_CTRL_R          (*((volatile uint32_t *)0xE000E010))
#define NVIC_ST_RELOAD_R        (*((volatile uint32_t *)0xE000E014))
#define NVIC_ST_CURRENT_R       (*((volatile uint32_t *)0xE000E018))

#define NVIC_ST_CTRL_CLK_SRC    0x00000004  // Clock Source
#define NVIC_ST_CTRL_INTEN      0x00000002  // Interrupt Enable
#define NVIC_ST_CTRL_ENABLE     0x00000001  // Enable

uint32_t SysTick_Config(uint32_t ticks)
{
  NVIC_ST_RELOAD_R = ticks;
  NVIC_ST_CTRL_R |= NVIC_ST_CTRL_ENABLE
                 |  NVIC_ST_CTRL_INTEN
                 |  NVIC_ST_CTRL_CLK_SRC;
  NVIC_ST_CURRENT_R = 0;                                               /* Function successful */
}

void sys_tick_handler(void) {
    do_draw = 1;

    timer_counter += 1;
    if (50 == timer_counter) {
        fps_display = fps_counter;
        fps_counter = 0;
        timer_counter = 0;
    }
}
