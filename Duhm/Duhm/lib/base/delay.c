/**
	* Project           : TM4C123GH6PM Timer controlled delays
	* Program name      : delay.h, delay.c
	* Author            : Daniel Laube <d.laube@ostfalia.de>
	* Date created      : 20190104
	* Purpose           : Active waiting delay with timers to improve accuracy
	* Revision History  :
	*
	*		See delay.h
	*/

#include "delay.h"

#include <TM4C123GH6PM.h>

void delay_us(uint32_t time) {
	SYSCTL->RCGCTIMER |= 0x01;

	TIMER0->CTL  = 0; 
	TIMER0->CFG  = 0x04;
	TIMER0->TAMR = 0x02;

	// 80MHz Clock -> 80 clocks for 1us
	TIMER0->TAILR = 80 - 1;
	TIMER0->ICR  = 0x1;
	TIMER0->CTL |= 0x01;

	// Loop time times over the counter to get time us
	for (uint32_t i = 0; i < time; ++i) {	
		// Wait until timer stops
		while ((TIMER0->RIS & 0x1) == 0);
		
		// Reset timer
		TIMER0->ICR = 0x1;
	}
}

void delay_ms(uint32_t time) {
	SYSCTL->RCGCTIMER |= 0x01;

	TIMER0->CTL  = 0;
	TIMER0->CFG  = 0x00;
	TIMER0->TAMR = 0x02;

	// 80MHz Clock -> 80000 clocks for 1ms
	TIMER0->TAILR = 80000 - 1; 	
	TIMER0->ICR  = 0x1;
	TIMER0->CTL |= 0x01;

	// Loop time times over the counter to get time ms
	for (uint32_t i = 0; i < time; ++i) {
		// Wait until timer stops
		while ((TIMER0->RIS & 0x1) == 0);
		
		// Reset timer
		TIMER0->ICR = 0x1;
	}
}
