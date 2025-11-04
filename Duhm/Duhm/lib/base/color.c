#include "color.h"

color_t col_add(color_t a, color_t b) {
  int ra = color_get_r(a);
  int ga = color_get_g(a);
  int ba = color_get_b(a);
  int rb = color_get_r(b);
  int gb = color_get_g(b);
  int bb = color_get_b(b);
  return RGB565(
    ra + rb,
    ga + gb,
    ba + bb);
}

color_t col_multiply(color_t a, color_t b) {
  int ra = color_get_r(a);
  int ga = color_get_g(a);
  int ba = color_get_b(a);
  int rb = color_get_r(b);
  int gb = color_get_g(b);
  int bb = color_get_b(b);
  return RGB565(
    ra * rb / 256,
    ga * gb / 256,
    ba * bb / 256);
}

#if 0
color_t col_multiply_scalar(color_t a, uint8_t b) {
  int ra = color_get_r(a);
  int ga = color_get_g(a);
  int ba = color_get_b(a);
  return RGB565(
    ra * b / 256,
    ga * b / 256,
    ba * b / 256);
}
#else
color_t col_multiply_scalar(color_t a, uint8_t b) {
  uint32_t ra = a.val & 0b1111100000000000;
  uint32_t ga = a.val & 0b0000011111100000;
  uint32_t ba = a.val & 0b0000000000011111;
  return (color_t){(uint16_t)((
      ((ra * b) & 0b111110000000000000000000) |
      ((ga * b) & 0b000001111110000000000000) |
      // This last AND is unnecessary, because the /256 will cut off those bits anyways.
      ((ba * b)/* & 0b000000000001111100000000*/))
      / 256U)};
}
#endif

color_t col_lerp(color_t a, color_t b, uint8_t i) {
  int ra = color_get_r(a);
  int ga = color_get_g(a);
  int ba = color_get_b(a);
  int rb = color_get_r(b);
  int gb = color_get_g(b);
  int bb = color_get_b(b);
  return RGB565(
    (ra * (255 - i) + rb * i) / 256,
    (ga * (255 - i) + gb * i) / 256,
    (ba * (255 - i) + bb * i) / 256);
}
