#include "adc.h"

#include <TM4C123GH6PM.h>

void adc_init(ADC adc) {
    switch (adc) {
        case ADC_0:
            SYSCTL->RCGCADC |= 1; // Enable Peripheral Clock
            break;
        case ADC_1:
            SYSCTL->RCGCADC |= 2; // Enable Peripheral Clock
            break;
    }
}

void add_input_to_channel(ADC adc, ADC_Channel channel, ADC_Input in) {
    ADC0_Type *regs = ((ADC0_Type *)adc);

    volatile uint32_t *SSMUX = 0;
    volatile uint32_t *SSCTL = 0;

    switch (channel) {
        case ADC_Channel_0: {
            SSMUX = &regs->SSMUX0;
            SSCTL = &regs->SSCTL0;
        } break;
        case ADC_Channel_1: {
            SSMUX = &regs->SSMUX1;
            SSCTL = &regs->SSCTL1;
        } break;
        case ADC_Channel_2: {
            SSMUX = &regs->SSMUX2;
            SSCTL = &regs->SSCTL2;
        } break;
        case ADC_Channel_3: {
            SSMUX = &regs->SSMUX3;
            SSCTL = &regs->SSCTL3;
        } break;
    }

    regs->ACTSS &= ~(1 << (int)channel); // Disable Sequencer

    // Search for the ENDn bit in the control register to find out
    // how long the sequence was so far.
    // The index variable will contain the place where we insert
    // the new input into the sequence.
    // If no ENDn bit was set so far, the index will remain 0.
    int index = 0;
    for (int i = 0; i < 32; i += 4) {
        if (*SSCTL & (0x02 << i)) {
            index = i + 4;
            break;
        }
    }

    *SSMUX |=  (uint32_t)in << index;   // Add new input to sequence.
    *SSCTL &=  ~((0x02 << index) >> 4); // Clear old end bit.
    *SSCTL |=    (0x02 << index);       // Set new end bit.
}

void adc_start(ADC adc, ADC_Channel channel) {
    ADC0_Type *regs = ((ADC0_Type *)adc);
    regs->ACTSS |=  (1 << (int)channel); // Enable Sequencer
    regs->PSSI  |=  (1 << (int)channel); // trigger ADC Sequencing
}

void adc_get_values(ADC adc, ADC_Channel channel, uint16_t *target, int count) {
    ADC0_Type *regs = ((ADC0_Type *)adc);

    volatile uint32_t *SSFIFO = 0;

    switch (channel) {
        case ADC_Channel_0: {
            SSFIFO = &regs->SSFIFO0;
        } break;
        case ADC_Channel_1: {
            SSFIFO = &regs->SSFIFO1;
        } break;
        case ADC_Channel_2: {
            SSFIFO = &regs->SSFIFO2;
        } break;
        case ADC_Channel_3: {
            SSFIFO = &regs->SSFIFO3;
        } break;
    }
    for (int i = 0; i < count; ++i) {
        target[i] = *SSFIFO & 0xFFF;
    }

    regs->PSSI |= (1 << (int)channel); // trigger next ADC Sequencing
}
