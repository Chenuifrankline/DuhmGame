#include "gpio.h"

#include "delay.h"

#include <TM4C123GH6PM.h>

void gpio_set(GPIO_Port port, GPIO_Pin pin, uint8_t value) {
    *((uint32_t *)port + pin) = value * pin;
}

uint8_t gpio_get(GPIO_Port port, GPIO_Pin pin) {
    GPIOA_Type *regs = (GPIOA_Type *)port;
    return (regs->DATA & pin) ? 1 : 0;
}

void gpio_init(GPIO_Port port) {
    switch (port) {
        case PORTA:
            SYSCTL->RCGCGPIO |= (1 << 0);
            break;
        case PORTB:
            SYSCTL->RCGCGPIO |= (1 << 1);
            break;
        case PORTC:
            SYSCTL->RCGCGPIO |= (1 << 2);
            break;
        case PORTD:
            SYSCTL->RCGCGPIO |= (1 << 3);
            break;
        case PORTE:
            SYSCTL->RCGCGPIO |= (1 << 4);
            break;
        case PORTF:
            SYSCTL->RCGCGPIO |= (1 << 5);
            break;
    }
    delay_us(1);
}

void gpio_mode(GPIO_Port port, GPIO_Pin pin, GPIO_Mode mode) {
    GPIOA_Type *regs = (GPIOA_Type *)port;
    
    if((port == PORTF && PIN0 == pin) || (port == PORTD && PIN7 == pin)){
        regs->LOCK = 0x4C4F434B; // Unlock
        regs->CR  |= pin;        // Commit Register
    }
    
    switch(mode){
        case INPUT: {
            regs->DIR   &= ~pin; // Direction 0 -> input
            regs->AMSEL &= ~pin; // Analog Function 0 -> disabled
            regs->AFSEL &= ~pin; // Alternate Function 0 -> disabled
            for (uint32_t i = 0; i < 8; ++i) {
                if (pin & (1 << i)) {
                    regs->PCTL  &= ~(15 << (i * 4));
                }
            }
            regs->PUR   &= ~pin; // Pull up 0 -> disabled
            regs->DEN   |=  pin; // Digital IO 1 -> enabled
        } break;
        case INPUT_PULLUP: {
            regs->DIR   &= ~pin; // Direction 0 -> input
            regs->AMSEL &= ~pin; // Analog Function 0 -> disabled
            regs->AFSEL &= ~pin; // Alternate Function 0 -> disabled
            for (uint32_t i = 0; i < 8; ++i) {
                if (pin & (1 << i)) {
                    regs->PCTL  &= ~(15 << (i * 4));
                }
            }
            regs->PUR   |=  pin; // Pull up 1 -> enabled
            regs->DEN   |=  pin; // Digital IO 1 -> enabled
        } break;
        case OUTPUT: {
            regs->DIR   |=  pin; // Direction 1 -> output
            regs->AMSEL &= ~pin; // Analog Function 0 -> disabled
            regs->AFSEL &= ~pin; // Alternate Function 0 -> disabled
            for (uint32_t i = 0; i < 8; ++i) {
                if (pin & (1 << i)) {
                    regs->PCTL  &= ~(15 << (i * 4));
                }
            }
            regs->PUR   &= ~pin; // Pull up 0 -> disabled
            regs->DEN   |=  pin; // Digital IO 1 -> enabled
        } break;
        case ALTERNATE: {
            regs->DIR   |=  pin; // Direction 1 -> output
            regs->AMSEL &= ~pin; // Analog Function 0 -> disabled
            regs->PUR   &= ~pin; // Pull up 0 -> disabled
            regs->DEN   |=  pin; // Digital IO 1 -> enabled
            regs->AFSEL |=  pin; // Alternate Function 1 -> enabled
        } break;
        case ANALOG: {
            regs->DIR   &= ~pin; // Direction 1 -> output
            regs->AMSEL |=  pin; // Analog Function 1 -> enabled
            regs->PUR   &= ~pin; // Pull up 0 -> disabled
            regs->DEN   &= ~pin; // Digital IO 1 -> enabled
            regs->AFSEL &= ~pin; // Alternate Function 0 -> disabled
        } break;
    }
}

void gpio_alternate(GPIO_Port port, GPIO_Pin pin, uint8_t mode){
    GPIOA_Type *regs = (GPIOA_Type *)port;    
    for (uint32_t i = 0; i < 8; ++i) {
        if (pin & (1 << i)) {
            regs->PCTL  &= ~(15   << (i * 4));
            regs->PCTL  |=  (mode << (i * 4));
        }
    }
}

void gpio_pullup(GPIO_Port port, GPIO_Pin pin, uint8_t enable){
    GPIOA_Type *regs = (GPIOA_Type *)port;
    if (enable) {
        regs->PUR |=  pin;
    } else {
        regs->PUR &= ~pin;
    }
}

