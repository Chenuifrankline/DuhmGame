#include "random.h"

typedef struct {
  uint64_t state;
} random_t;

random_t global_random_state = {0x853c49e6748fea9bULL};

uint32_t random_uint32() {
  // minimal PCG Random Number Generator from pcg-random.org
  // 64-bit state, 32-bit output
  uint64_t oldstate = global_random_state.state;
  global_random_state.state = oldstate * 6364136223846793005ull + 1442695040888963407ull;
  uint32_t xorshifted = (uint32_t)(((oldstate >> 18u) ^ oldstate) >> 27u);
  int shift = (int)(oldstate >> 59u);
  return (xorshifted >> shift) | (xorshifted << (32 - shift));
}

float random_float() {
  return (float)(random_uint32() & 0xFFFFFF) / 16777216.0f;
}

int random_bit() {
  return random_uint32() & 0x01;
}
