#ifndef ADC_H
#define ADC_H

#include <stdint.h>

#define ADC_MAX_VAL ((1 << 12) - 1)

typedef enum {
	ADC_0 = 0x40038000UL,
	ADC_1 = 0x40039000UL,
} ADC;

typedef enum {
	ADC_Channel_0,
	ADC_Channel_1,
	ADC_Channel_2,
	ADC_Channel_3,
} ADC_Channel;

typedef enum {
	AIN_0  = 0,  // PE3
	AIN_1  = 1,  // PE2
	AIN_2  = 2,  // PE1
	AIN_3  = 3,  // PE0
	AIN_4  = 4,  // PD3
	AIN_5  = 5,  // PD2
	AIN_6  = 6,  // PD1
	AIN_7  = 7,  // PD0
	AIN_8  = 8,  // PE5
	AIN_9  = 9,  // PE4
	AIN_10 = 10, // PB4
	AIN_11 = 11, // PB5
} ADC_Input;

void adc_init(ADC adc);

void adc_get_values(ADC adc, ADC_Channel channel, uint16_t *target, int count);

void adc_start(ADC adc, ADC_Channel channel);

void add_input_to_channel(ADC adc, ADC_Channel channel, ADC_Input in);

#endif // ADC_H
