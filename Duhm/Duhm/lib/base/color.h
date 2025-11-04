#ifndef COLOR_H
#define COLOR_H

#include <stdint.h>

typedef struct {
  uint16_t val;
} color_t;

#define RGB565(R,G,B) ((color_t){(uint16_t)((((R) / 8) << 11) | (((G) / 4) << 5) | ((B) / 8))})
#define color_get_r(color) ((color.val >> 8) & 0b11111000);
#define color_get_g(color) ((color.val >> 3) & 0b11111100);
#define color_get_b(color) ((color.val << 3) & 0b11111000);

#define COLOR_RED     RGB565(0xFF, 0x00, 0x00)
#define COLOR_YELLOW  RGB565(0xFF, 0xFF, 0x00)
#define COLOR_ORANGE  RGB565(0xFF, 0xF0, 0x00)
#define COLOR_GREEN   RGB565(0x00, 0xFF, 0x00)
#define COLOR_CYAN    RGB565(0x00, 0xFF, 0xFF)
#define COLOR_BLUE    RGB565(0x00, 0x00, 0xFF)
#define COLOR_MAGENTA RGB565(0xFF, 0x00, 0xFF)
#define COLOR_WHITE   RGB565(0xFF, 0xFF, 0xFF)
#define COLOR_BLACK   RGB565(0x00, 0x00, 0x00)

color_t col_add(color_t a, color_t b);

color_t col_multiply(color_t a, color_t b);

color_t col_multiply_scalar(color_t a, uint8_t b);

color_t col_lerp(color_t a, color_t b, uint8_t i);

#endif // COLOR_H
