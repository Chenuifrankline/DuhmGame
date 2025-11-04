#ifndef GPIO_H
#define GPIO_H

#include <stdint.h>

/**
 * Port Base Addresses
 */
typedef enum {
	PORTA = 0x40004000,
	PORTB = 0x40005000,
	PORTC = 0x40006000,
	PORTD = 0x40007000,
	PORTE = 0x40024000,
	PORTF = 0x40025000
} GPIO_Port;

typedef enum {
    PIN0 = 0x01,
    PIN1 = 0x02,
    PIN2 = 0x04,
    PIN3 = 0x08,
    PIN4 = 0x10,
    PIN5 = 0x20,
    PIN6 = 0x40,
    PIN7 = 0x80,
} GPIO_Pin;

/**
 * GPIO Modes
 */
typedef enum {
	INPUT,
	INPUT_PULLUP,
	OUTPUT,
	ALTERNATE,
	ANALOG,
} GPIO_Mode;

/**
	* GPIO initialization routine
	* - Sets up system clock for GPIO port
	*
	* @param	port		gpio port that the pin resides on
	*/
void gpio_init(GPIO_Port port);

/**
	*	GPIO mode selection routine
	*	-	Selects a mode of a given gpio port/pin
	*	
	* @param	port		gpio port that the pin resides on
	* @param	pin			gpio pin number in the gpio port
	*/
void gpio_mode(GPIO_Port port, GPIO_Pin pin, GPIO_Mode mode);

/**
	* GPIO output set routine
	* - Sets the pin to value (0/1, LOW/HIGH)
	*
	* @param	port		gpio port that the pin resides on
	* @param	pin			gpio pin number in the gpio port
	* @param	value		whether to turn the pin off or on (0 or 1)
	*/
void gpio_set(GPIO_Port port, GPIO_Pin pin, uint8_t value);

/**
	* GPIO input get routine
	* - Reads the digital value of the pin into value
	*
	* @param	port		gpio port that the pin resides on
	* @param	pin			gpio pin number in the gpio port
	* @param	value		address to write the result to
	*/
uint8_t gpio_get(GPIO_Port port, GPIO_Pin pin);

/**
	* GPIO alternate mode setting routine
	* - Sets the alternate function of pin to mode
	*
	* @param	port		gpio port that the pin resides on
	* @param	pin			gpio pin number in the gpio port
	* @param	mode		alternate function number (see datasheet)
	*/
void gpio_alternate(GPIO_Port port, GPIO_Pin pin, uint8_t mode);

/**
	* GPIO pullup setting routine
	* - Sets the pullup modes of pin
	*
	* @param	port		gpio port that the pin resides on
	* @param	pin			gpio pin number in the gpio port
	* @param	enable	whether to enable pullup on this pin
	*/
void gpio_pullup(GPIO_Port port, GPIO_Pin pin, uint8_t enable);

#endif // GPIO_H
