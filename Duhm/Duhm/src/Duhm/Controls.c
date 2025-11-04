#include "Controls.h"

void Duhm_Controls_process_inputs(Duhm_Controls* const me, float dt) {
	// Input
	uint16_t joy_vals[2];
	adc_get_values(ADC_0, ADC_Channel_0, joy_vals, 2);
	
	{
	    int new_z = gpio_get(PORTE, PIN4);
	    if (new_z != me->prev_z) {
	        if (new_z) {
	            // TODO: z was released
	        } else {
	            // TODO: z was pressed
	        }
	        me->prev_z = new_z;
	    }
	}
	int strafe = !gpio_get(PORTD, PIN6);
	{
	    int new_b = gpio_get(PORTD, PIN7);
	    if (new_b != me->prev_b) {
	        if (new_b) {
	            // TODO: B was released
	        } else {
	            // B was pressed
	            if (0.0f == me->player->pos[2]) {
	                me->player->vel[2] = 2.0f;
	            }
	        }
	        me->prev_b = new_b;
	    }
	}
	
	// Processing
	{
	    const float deadzone = 0.1f;
	    
	    float joy_y = (float)joy_vals[1] / (float)ADC_MAX_VAL - 0.5f;
	    float joy_x = (float)joy_vals[0] / (float)ADC_MAX_VAL - 0.5f;
	    float move_x = 0.0f;
	    float move_y = 0.0f;
	
	    if (joy_y < -deadzone) {
	        move_x = (joy_y + deadzone) * 10.0f;
	    } else if (deadzone < joy_y) {
	        move_x = (joy_y - deadzone) * 10.0f;
	    }
	    if (strafe) {
	        if (joy_x < -deadzone) {
	            move_y = (joy_x + deadzone) * 10.0f;
	        } else if (deadzone < joy_x) {
	            move_y = (joy_x - deadzone) * 10.0f;
	        }
	    } else {
	        if (joy_x < -deadzone) {
	            me->view_angle += (joy_x + deadzone) * 10.0f * dt;
	            me->player->view[0] = cosf(me->view_angle);
	            me->player->view[1] = sinf(me->view_angle);
	        }
	        if (deadzone < joy_x) {
	            me->view_angle += (joy_x - deadzone) * 10.0f * dt;
	            me->player->view[0] = cosf(me->view_angle);
	            me->player->view[1] = sinf(me->view_angle);
	        }
	    }
	    me->player->vel[0] = me->player->view[0] * move_x - me->player->view[1] * move_y;
	    me->player->vel[1] = me->player->view[1] * move_x + me->player->view[0] * move_y;
	}
}

void Duhm_Controls_init(Duhm_Controls* const me) {
	gpio_init(PORTB);
	gpio_init(PORTC);
	gpio_init(PORTD);
	gpio_init(PORTE);
	adc_init(ADC_0);
	
	gpio_mode(PORTB, PIN5, ANALOG); // Joystick X (PB5 == AIN_11)
	gpio_mode(PORTD, PIN3, ANALOG); // Joystick Y (PD3 == AIN_4)
	
	gpio_mode(PORTD, PIN6, INPUT); // Button A (PD6)
	gpio_mode(PORTD, PIN7, INPUT); // Button B (PD7)
	gpio_mode(PORTE, PIN4, INPUT); // Button Z (PE4)
	
	add_input_to_channel(ADC_0, ADC_Channel_0, AIN_11);
	add_input_to_channel(ADC_0, ADC_Channel_0, AIN_4);
	adc_start(ADC_0, ADC_Channel_0);
}
Duhm_Controls Duhm_controls = {
	.player = &Duhm_player
};
