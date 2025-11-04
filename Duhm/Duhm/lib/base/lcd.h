#ifndef LCD_H
#define LCD_H

#include "color.h"

#include <stdint.h>

#define LCD_WIDTH  128
#define LCD_HEIGHT 128

/**
	* LCD initialization routine
	* - sets up gpio in order to provide SPI communication to the display
	* - initializes the display with a startup sequence
	* - can also be used to reset the display
  */
void lcd_init(void);

/**
	* Fill routine
	* - fills the entire screen with given rgb color value
	*
	* @param	color		fill color in RGB565 format
	*/
void lcd_fill(color_t color);

/**
	* Pixel drawing routine
	* - Sets the color value of a pixel on the lcd
	*
	* @param	x		x-coordinate of the pixel
	* @param	y		y-coordinate of the pixel
	* @param	color		pixel color om RGB565 format
	*/
void lcd_pixel(int x, int y, color_t color);

/**
	* Line drawing routine
	* - Draws a 1px line between x0,y0 and x1,y1
	*
	* @param	x0		x-coordinate of the start point
	* @param	y0		y-coordinate of the start point
	* @param	x1		x-coordinate of the end point
	* @param	y1		y-coordinate of the end point
	* @param	color		pixel color om RGB565 format
	*/
void lcd_line(int x0, int y0, int x1, int y1, color_t color);

/**
	* Line drawing routine
	* - Draws a 1px horizontal line between x0 and x1 at row y
	*
	* @param	x0		x-coordinate of the start point
	* @param	y 		y-coordinate of the line
	* @param	x1		x-coordinate of the end point
	* @param	color		pixel color om RGB565 format
	*/
void lcd_horizontal_line(int x0, int y, int x1, color_t color);

/**
	* Line drawing routine
	* - Draws a 1px vertical line between y0 and y1 at column x
	*
	* @param	x 		x-coordinate of the line
	* @param	y0		y-coordinate of the start point
	* @param	y1		y-coordinate of the end point
	* @param	color		pixel color om RGB565 format
	*/
void lcd_vertical_line(int x, int y0, int y1, color_t color);

/**
	* Line drawing routine
	* - Draws a 1px line between x0,y0 and x1,y1
	*
	* @param	x0				x-coordinate of the start point
	* @param	y0				y-coordinate of the start point
	* @param	x1				x-coordinate of the end point
	* @param	y1				y-coordinate of the end point
	* @param  len_line	length of one dash
	* @param	color 	 	pixel color om RGB565 format
	*/
void lcd_dashed_line(int x0, int y0, int x1, int y1, int len_line, color_t color);

/**
	* Rectangle drawing routine
	* - Draws a 1px rectangle defined by the two points x0,y0 and x1,y1
	*
	* @param	x0		x-coordinate of the first point
	* @param	y0		y-coordinate of the first point
	* @param	x1		x-coordinate of the second point
	* @param	y1		y-coordinate of the second point
	* @param	color		pixel color om RGB565 format
	*/
void lcd_rectangle(int x0, int y0, int x1, int y1, color_t color);

/**
	* Filled rectangle drawing routine
	* - Draws a filled rectangle defined by the two points x0,y0 and x1,y1
	*
	* @param	x0		x-coordinate of the first point
	* @param	y0		y-coordinate of the first point
	* @param	x1		x-coordinate of the second point
	* @param	y1		y-coordinate of the second point
	* @param	color		pixel color om RGB565 format
	*/
void lcd_rectangle_fill(int x0, int y0, int x1, int y1, color_t color);
	
/**
	* Circle drawing routine
	* - Draws a 1px circle outline with given color values
	*
	* @param 	x0		x-coordinate of circle center
	* @param	y0		y-coordinate of circle center
	* @param	radius		radius of circle
	* @param	color		circle color in RGB565 format
	*/
void lcd_circle(int x0, int y0, int radius, color_t color);

/**
	* Filled circle drawing routine
	* - Draws a filled circle outline with given color values
	*
	* @param 	x0		x-coordinate of circle center
	* @param	y0		y-coordinate of circle center
	* @param	radius		radius of circle
	* @param	color		circle color in RGB565 format
	*/
void lcd_circle_fill(int x0, int y0, int radius, color_t color);

/**
	*	Character drawing routine
	* - Draws a given ASCII character on the given position with a specific foreground and background color
	*
	* @param	x		x-coordinate for the character to be drawn (origin: upper-left)
	* @param	y		y-coordinate for the character to be drawn (origin: upper-left)
	* @param	ch	character to be drawn (possible values 0-127; basic ascii)
	* @param	colorf		foreground color in RGB565 format
	* @param	colorb		background color in RGB565 format
	*/
void lcd_character(int x, int y, char ch, color_t colorf, color_t colorb);

/**
	*	Character sequence drawing routine
	* - Draws a given character sequence (text) with a specific foreground and background color
	* - The position can be specified with lcd_cursor(x, y)
	* - The cursor position is updated when drawing text with lcd_print_rgb(), lcd_println_rgb(), lcd_print() and lcd_println()
	* - Instead of drawing the \n character the cursor y position is increased
	* 
	* @param	ch		character sequence
	* @param	colorf		foreground color in RGB565 format
	* @param	colorb		background color in RGB565 format
	*/
void lcd_print_rgb(const char *ch, color_t colorf, color_t colorb);
/**
	*	Character sequence drawing routine with newline suffix
	* - Draws a given character sequence (text) with a specific foreground and background color
	* - The position can be specified with lcd_cursor(x, y)
	* - The cursor position is updated when drawing text with lcd_print_rgb(), lcd_println_rgb(), lcd_print() and lcd_println()
	* - Instead of drawing the \n character the cursor y position is increased
	* - After drawing the sequence the cursor y position is increased
	* 
	* @param	ch		character sequence
	* @param	colorf		foreground color in RGB565 format
	* @param	colorb		background color in RGB565 format
	*/
void lcd_println_rgb(const char *ch, color_t colorf, color_t colorb);

/**
	*	Character sequence drawing routine withoud color
	* - Draws a given character sequence (text) with white foreground color and black background color
	* - The position can be specified with lcd_cursor(x, y)
	* - The cursor position is updated when drawing text with lcd_print_rgb(), lcd_println_rgb(), lcd_print() and lcd_println()
	* - Instead of drawing the \n character the cursor y position is increased
	*
	* @param	ch		character sequence
	*/
void lcd_print(const char *ch);

/**
	*	Character sequence drawing routine without color
	* - Draws a given character sequence (text) with white foreground color and black background color
	* - The position can be specified with lcd_cursor(x, y)
	* - The cursor position is updated when drawing text with lcd_print_rgb(), lcd_println_rgb(), lcd_print() and lcd_println()
	* - Instead of drawing the \n character the cursor y position is increased
	* - After drawing the sequence the cursor y position is increased
	*
	* @param	ch		character sequence
	*/
void lcd_println(const char *ch);

/**
	* Cursor setting routine
	* - Sets the character drawing cursor to the specified position
	*
	* @param	x		x-coordinate for the cursor (origin: upper-left)
	* @param	y		y-coordinate for the cursor (origin: upper-left)	
	*/
void lcd_cursor(int x, int y);

/**
	* Bitmap drawing routine
	* - draws a bitmap at the specified position
	* - pixel data is given as uint16_t array (RGB565)
	* - pixel data length must be width*height
	*
	* @param	x		x-coordinate for the bitmap to be drawn (origin: upper-left)
	* @param	y		y-coordinate for the bitmap to be drawn (origin: upper-left)
	* @param	bitmap	uint16_t array of bitmap data (RGB565)
	* @param 	width		width of bitmap
	* @param	height	height of bitmap
	*/
void lcd_bitmap(int x, int y, const color_t *bitmap, int width, int height);

/**
	* Bitmap drawing routine
	* - draws a bitmap at the specified position
	* - pixel data is given as uint16_t array (RGB565)
	* - pixel data length must be width*height
	*
	* @param	x		x-coordinate for the bitmap to be drawn (origin: upper-left)
	* @param	y		y-coordinate for the bitmap to be drawn (origin: upper-left)
	* @param	bitmap	uint16_t array of bitmap data (RGB565)
	* @param 	width		width of bitmap
	* @param	height	height of bitmap
	* @param 	alphacolor	color key for alpha (RGB565)
	*/
void lcd_bitmap_alpha(int x, int y, const color_t *bitmap, int width, int height, color_t alphacolor);

/**
	* Bitmap drawing routine
	* - draws a bitmap into the specified rectangle
	* - pixel data is given as uint16_t array (RGB565)
	* - pixel data length must be tex_width*tex_height
	*
	* @param	x		left x-coordinate for the target rectangle
	* @param	y		top y-coordinate for the target rectangle
	* @param	x		right x-coordinate for the target rectangle
	* @param	y		bottom y-coordinate for the target rectangle
	* @param	bitmap	uint16_t array of bitmap data (RGB565)
	* @param 	tex_width		width of bitmap
	* @param	tex_height	height of bitmap
	*/
void lcd_bitmap_scaled(int x0, int y0, int x1, int y1, const color_t *bitmap, int tex_width, int tex_height);

#endif
