#include "lcd.h"

#include "delay.h"
#include "gpio.h"

#include <TM4C123GH6PM.h>
#include <math.h>

/**
 *    LCD Controller Bus GPIOs
 */
#define LCD_SCK_PORT    PORTB
#define LCD_SCK_PIN     PIN4
#define LCD_MOSI_PORT   PORTB
#define LCD_MOSI_PIN    PIN7
#define LCD_CS_PORT     PORTA
#define LCD_CS_PIN      PIN4
#define LCD_RESET_PORT  PORTF
#define LCD_RESET_PIN   PIN0
#define LCD_DS_PORT     PORTF
#define LCD_DS_PIN      PIN4

#define LCD_USE_HW_SPI

/**
 *    LCD Controller Constants
 */
#define ST7735S_MADCTL_MY  0x80
#define ST7735S_MADCTL_MX  0x40
#define ST7735S_MADCTL_MV  0x20
#define ST7735S_MADCTL_ML  0x10
#define ST7735S_MADCTL_RGB 0x08
#define ST7735S_MADCTL_MH  0x04

/**
 *    LCD Controller Commands
 */
#define ST7735S_NOP       0x00
#define ST7735S_SWRESET   0x01 // Software Reset
#define ST7735S_RDDID     0x04 // Read Display ID
#define ST7735S_RDDST     0x09 // Read Display Status
#define ST7735S_RDDPM     0x0A // Read Display Power Mode
#define ST7735S_RDDMADCTL 0x0B // Read Display MADCTL
#define ST7735S_RDDCOLMOD 0x0C // Read Display Pixel Format
#define ST7735S_RDDIM     0x0D // Read Display Image Mode
#define ST7735S_RDDSM     0x0E // Read Display Signal Mode
#define ST7735S_RDDSDR    0x0F // Read Display Self Diagnostic Results
#define ST7735S_SLPIN     0x10 // Sleep In
#define ST7735S_SLPOUT    0x11 // Sleep Out
#define ST7735S_PTLON     0x12 // Partial Display Mode On
#define ST7735S_NORON     0x13 // Normal Display Mode On
#define ST7735S_INVOFF    0x20 // Display Inversion Off
#define ST7735S_INVON     0x21 // Display Inversion On
#define ST7735S_GAMSET    0x26 // Gamma Set
#define ST7735S_DISPOFF   0x28 // Display Off
#define ST7735S_DISPON    0x29 // Display On
#define ST7735S_CASET     0x2A // Column Address Set
#define ST7735S_RASET     0x2B // Row Address Set
#define ST7735S_RAMWR     0x2C // Memory Write
#define ST7735S_RGBSET    0x2D // Color Setting
#define ST7735S_RAMRD     0x2E // Memory Read
#define ST7735S_PTLAR     0x30 // Partial Area
#define ST7735S_SCRLAR    0x33 // Scroll Area Set
#define ST7735S_TEOFF     0x34 // Tearing Effect Line Off
#define ST7735S_TEON      0x35 // Tearing Effect Line On
#define ST7735S_MADCTL    0x36 // Memory Data Access Control
#define ST7735S_VSCSAD    0x37 // Vertical Scroll Start Address
#define ST7735S_IDMOFF    0x38 // Idle Mode Off
#define ST7735S_IDMON     0x39 // Idle Mode On
#define ST7735S_COLMOD    0x3A // Interface Pixel Format
#define ST7735S_FRMCTR1   0xB1 // Frame Rate Control (Normal Mode)
#define ST7735S_FRMCTR2   0xB2 // Frame Rate Control (Idle Mode)
#define ST7735S_FRMCTR3   0xB3 // Frame Rate Control (Partial Mode)
#define ST7735S_INVCTR    0xB4 // Display Inversion Control
#define ST7735S_PWCTR1    0xC0 // Power Control 1
#define ST7735S_PWCTR2    0xC1 // Power Control 2
#define ST7735S_PWCTR3    0xC2 // Power Control 3 (Normal Mode)
#define ST7735S_PWCTR4    0xC3 // Power Control 4 (Idle Mode)
#define ST7735S_PWCTR5    0xC4 // Power Control 5 (Partial Mode)
#define ST7735S_VMCTR1    0xC5 // VCOM Control 1
#define ST7735S_VMOFCTR   0xC7 // VCOM Offset Control
#define ST7735S_WRID2     0xD1 // Write ID2 Value
#define ST7735S_WRID3     0xD2 // Write ID3 Value
#define ST7735S_NVFCTR1   0xD9 // NVM Control Status
#define ST7735S_RDID1     0xDA // Read ID1 Value
#define ST7735S_RDID2     0xDB // Read ID2 Value
#define ST7735S_RDID3     0xDC // Read ID3 Value
#define ST7735S_NVFCTR2   0xDE // NVM Read Command
#define ST7735S_NVFCTR3   0xDF // NVM Write Command
#define ST7735S_GMCTRP1   0xE0 // Gamma + Polarity Correction Characteristics Setting
#define ST7735S_GMCTRN1   0xE1 // Gamma - Polarity Correction Characteristics Setting
#define ST7735S_GCV       0xFC // Gate Pump Clock Frequency Variable

// 8x8 ASCII Pixmap, stored as 2 32-bit values per char.
uint32_t font8x8_basic[128][2] = {
    { 0x00000000, 0x00000000 },   // U+0000 (nul)
    { 0x00000000, 0x00000000 },   // U+0001
    { 0x00000000, 0x00000000 },   // U+0002
    { 0x00000000, 0x00000000 },   // U+0003
    { 0x00000000, 0x00000000 },   // U+0004
    { 0x00000000, 0x00000000 },   // U+0005
    { 0x00000000, 0x00000000 },   // U+0006
    { 0x00000000, 0x00000000 },   // U+0007
    { 0x00000000, 0x00000000 },   // U+0008
    { 0x00000000, 0x00000000 },   // U+0009
    { 0x00000000, 0x00000000 },   // U+000A
    { 0x00000000, 0x00000000 },   // U+000B
    { 0x00000000, 0x00000000 },   // U+000C
    { 0x00000000, 0x00000000 },   // U+000D
    { 0x00000000, 0x00000000 },   // U+000E
    { 0x00000000, 0x00000000 },   // U+000F
    { 0x00000000, 0x00000000 },   // U+0010
    { 0x00000000, 0x00000000 },   // U+0011
    { 0x00000000, 0x00000000 },   // U+0012
    { 0x00000000, 0x00000000 },   // U+0013
    { 0x00000000, 0x00000000 },   // U+0014
    { 0x00000000, 0x00000000 },   // U+0015
    { 0x00000000, 0x00000000 },   // U+0016
    { 0x00000000, 0x00000000 },   // U+0017
    { 0x00000000, 0x00000000 },   // U+0018
    { 0x00000000, 0x00000000 },   // U+0019
    { 0x00000000, 0x00000000 },   // U+001A
    { 0x00000000, 0x00000000 },   // U+001B
    { 0x00000000, 0x00000000 },   // U+001C
    { 0x00000000, 0x00000000 },   // U+001D
    { 0x00000000, 0x00000000 },   // U+001E
    { 0x00000000, 0x00000000 },   // U+001F
    { 0x00000000, 0x00000000 },   // U+0020 (space)
    { 0x183C3C18, 0x00180018 },   // U+0021 (!)
    { 0x00003636, 0x00000000 },   // U+0022 (")
    { 0x367F3636, 0x0036367F },   // U+0023 (#)
    { 0x1E033E0C, 0x000C1F30 },   // U+0024 ($)
    { 0x18336300, 0x0063660C },   // U+0025 (%)
    { 0x6E1C361C, 0x006E333B },   // U+0026 (&)
    { 0x00030606, 0x00000000 },   // U+0027 (')
    { 0x06060C18, 0x00180C06 },   // U+0028 (()
    { 0x18180C06, 0x00060C18 },   // U+0029 ())
    { 0xFF3C6600, 0x0000663C },   // U+002A (*)
    { 0x3F0C0C00, 0x00000C0C },   // U+002B (+)
    { 0x00000000, 0x060C0C00 },   // U+002C (,)
    { 0x3F000000, 0x00000000 },   // U+002D (-)
    { 0x00000000, 0x000C0C00 },   // U+002E (.)
    { 0x0C183060, 0x00010306 },   // U+002F (/)
    { 0x7B73633E, 0x003E676F },   // U+0030 (0)
    { 0x0C0C0E0C, 0x003F0C0C },   // U+0031 (1)
    { 0x1C30331E, 0x003F3306 },   // U+0032 (2)
    { 0x1C30331E, 0x001E3330 },   // U+0033 (3)
    { 0x33363C38, 0x0078307F },   // U+0034 (4)
    { 0x301F033F, 0x001E3330 },   // U+0035 (5)
    { 0x1F03061C, 0x001E3333 },   // U+0036 (6)
    { 0x1830333F, 0x000C0C0C },   // U+0037 (7)
    { 0x1E33331E, 0x001E3333 },   // U+0038 (8)
    { 0x3E33331E, 0x000E1830 },   // U+0039 (9)
    { 0x000C0C00, 0x000C0C00 },   // U+003A (:)
    { 0x000C0C00, 0x060C0C00 },   // U+003B (;)
    { 0x03060C18, 0x00180C06 },   // U+003C (<)
    { 0x003F0000, 0x00003F00 },   // U+003D (=)
    { 0x30180C06, 0x00060C18 },   // U+003E (>)
    { 0x1830331E, 0x000C000C },   // U+003F (?)
    { 0x7B7B633E, 0x001E037B },   // U+0040 (@)
    { 0x33331E0C, 0x0033333F },   // U+0041 (A)
    { 0x3E66663F, 0x003F6666 },   // U+0042 (B)
    { 0x0303663C, 0x003C6603 },   // U+0043 (C)
    { 0x6666361F, 0x001F3666 },   // U+0044 (D)
    { 0x1E16467F, 0x007F4616 },   // U+0045 (E)
    { 0x1E16467F, 0x000F0616 },   // U+0046 (F)
    { 0x0303663C, 0x007C6673 },   // U+0047 (G)
    { 0x3F333333, 0x00333333 },   // U+0048 (H)
    { 0x0C0C0C1E, 0x001E0C0C },   // U+0049 (I)
    { 0x30303078, 0x001E3333 },   // U+004A (J)
    { 0x1E366667, 0x00676636 },   // U+004B (K)
    { 0x0606060F, 0x007F6646 },   // U+004C (L)
    { 0x7F7F7763, 0x0063636B },   // U+004D (M)
    { 0x7B6F6763, 0x00636373 },   // U+004E (N)
    { 0x6363361C, 0x001C3663 },   // U+004F (O)
    { 0x3E66663F, 0x000F0606 },   // U+0050 (P)
    { 0x3333331E, 0x00381E3B },   // U+0051 (Q)
    { 0x3E66663F, 0x00676636 },   // U+0052 (R)
    { 0x0E07331E, 0x001E3338 },   // U+0053 (S)
    { 0x0C0C2D3F, 0x001E0C0C },   // U+0054 (T)
    { 0x33333333, 0x003F3333 },   // U+0055 (U)
    { 0x33333333, 0x000C1E33 },   // U+0056 (V)
    { 0x6B636363, 0x0063777F },   // U+0057 (W)
    { 0x1C366363, 0x0063361C },   // U+0058 (X)
    { 0x1E333333, 0x001E0C0C },   // U+0059 (Y)
    { 0x1831637F, 0x007F664C },   // U+005A (Z)
    { 0x0606061E, 0x001E0606 },   // U+005B ([)
    { 0x180C0603, 0x00406030 },   // U+005C (\)
    { 0x1818181E, 0x001E1818 },   // U+005D (])
    { 0x63361C08, 0x00000000 },   // U+005E (^)
    { 0x00000000, 0xFF000000 },   // U+005F (_)
    { 0x00180C0C, 0x00000000 },   // U+0060 (`)
    { 0x301E0000, 0x006E333E },   // U+0061 (a)
    { 0x3E060607, 0x003B6666 },   // U+0062 (b)
    { 0x331E0000, 0x001E3303 },   // U+0063 (c)
    { 0x3e303038, 0x006E3333 },   // U+0064 (d)
    { 0x331E0000, 0x001E033f },   // U+0065 (e)
    { 0x0f06361C, 0x000F0606 },   // U+0066 (f)
    { 0x336E0000, 0x1F303E33 },   // U+0067 (g)
    { 0x6E360607, 0x00676666 },   // U+0068 (h)
    { 0x0C0E000C, 0x001E0C0C },   // U+0069 (i)
    { 0x30300030, 0x1E333330 },   // U+006A (j)
    { 0x36660607, 0x0067361E },   // U+006B (k)
    { 0x0C0C0C0E, 0x001E0C0C },   // U+006C (l)
    { 0x7F330000, 0x00636B7F },   // U+006D (m)
    { 0x331F0000, 0x00333333 },   // U+006E (n)
    { 0x331E0000, 0x001E3333 },   // U+006F (o)
    { 0x663B0000, 0x0F063E66 },   // U+0070 (p)
    { 0x336E0000, 0x78303E33 },   // U+0071 (q)
    { 0x6E3B0000, 0x000F0666 },   // U+0072 (r)
    { 0x033E0000, 0x001F301E },   // U+0073 (s)
    { 0x0C3E0C08, 0x00182C0C },   // U+0074 (t)
    { 0x33330000, 0x006E3333 },   // U+0075 (u)
    { 0x33330000, 0x000C1E33 },   // U+0076 (v)
    { 0x6B630000, 0x00367F7F },   // U+0077 (w)
    { 0x36630000, 0x0063361C },   // U+0078 (x)
    { 0x33330000, 0x1F303E33 },   // U+0079 (y)
    { 0x193F0000, 0x003F260C },   // U+007A (z)
    { 0x070C0C38, 0x00380C0C },   // U+007B ({)
    { 0x00181818, 0x00181818 },   // U+007C (|)
    { 0x380C0C07, 0x00070C0C },   // U+007D (})
    { 0x00003B6E, 0x00000000 },   // U+007E (~)
    { 0x00000000, 0x00000000 }    // U+007F
};

/**
  \brief   Enable Interrupt
  \details Enables a device specific interrupt in the NVIC interrupt controller.
  \param [in]      IRQn  Device specific interrupt number.
  \note    IRQn must not be negative.
 */
static inline void NVIC_EnableIRQ(IRQn_Type IRQn)
{
  if ((int32_t)(IRQn) >= 0)
  {
    asm volatile ("":::"memory");
    NVIC->ISER[(((uint32_t)IRQn) >> 5UL)] = (uint32_t)(1UL << (((uint32_t)IRQn) & 0x1FUL));
    asm volatile ("":::"memory");
  }
}

////////////////////////////////////////////////////////////////////////////////
// DMA Stuff
////////////////////////////////////////////////////////////////////////////////

// This struct is not defined in the TM4C123 header, so we have to define our own.
// It's like a peripheral register struct, but we can put it in the memory where
// we want. (As long as the address is 1024-byte-aligned)
typedef struct {
    uint32_t src_end_point;
    uint32_t dst_end_point;
    uint32_t control;
    uint32_t unused;
} DMA_Channel_Control;

// Out DMA "registers", one for each channel. There are 64 channels, though we
// only use one at the moment.
DMA_Channel_Control dma_config[64] __attribute__((aligned (1024)));

// This interrupt handler is called by the DMA when a transmission is completed.
// If you need to use SSI2 interrupts for something else that should work too.
// Just put the code in the else branch.
void ssi2_isr(void) {
    if (UDMA->CHIS & (1 << 13)) { // Was the interrupt caused by DMA?

        // Wait for this transmission to complete. This interrupt triggers
        // when there are still 4 values in the SSI2 FIFO.
        while (SSI2->SR & 0x10);

        // This interrupt is called when the data transfer is finished. So
        // all that is left to do is turn everything off again.
        SSI2->CR1    &= ~(1 << 1); // Disable SSI2
        SSI2->DMACTL &= ~(1 << 1); // Disable TX DMA
        SSI2->IM     &= ~(1 << 3); // Turn off the interrupts.
        SSI2->CR1    |=  (1 << 1); // Enable SSI2

        UDMA->ENACLR |=  (1 << 13); // Disable the DMA channel.
        UDMA->CHIS   |=  (1 << 13); // Clear the interrupt bit.
    } else {
        // Interrupt not caused by DMA.
    }
}

////////////////////////////////////////////////////////////////////////////////
// Utility functions
////////////////////////////////////////////////////////////////////////////////

static inline int clamp(int val, int min, int max) {
    if (val < min) return min;
    if (max < val) return max;
    return val;
}

static inline int min(int a, int b) {
    if (a < b) return a;
    return b;
}

static inline int max(int a, int b) {
    if (a < b) return b;
    return a;
}

static inline int abs(int a) {
    if (a < 0) return -a;
    return a;
}

////////////////////////////////////////////////////////////////////////////////
// Internal functions for SSI data transfer to the Display
////////////////////////////////////////////////////////////////////////////////

// All commands are identified by a single byte. Commands are distinguished
// from data by the fact that LCD_DS_PIN is set to low.
static inline void lcd_command(uint8_t command) {

    // Wait for previous transmissions to complete, because we're changing
    // the data width and set the DS pin.
    while (SSI2->SR & 0x10);

    // Switch to 8-bit transfer.
    SSI2->CR1 &= ~(1 << 1);
    SSI2->CR0  =  (1 << 6) | 0x07;
    SSI2->CR1 |=  (1 << 1);

    // Select DS pin low to signal that we're sending a command.
    //gpio_set(LCD_DS_PORT, LCD_DS_PIN, 0);
    *((uint32_t *)LCD_DS_PORT + LCD_DS_PIN) = 0;
	
    // Put the command in the send FIFO
    SSI2->DR = command;

    // Wait for the transmission to complete before we change the pin and data
    // width back.
    while (SSI2->SR & 0x10);

    // Set DS pin back to high because we're probably going to send data next.
    //gpio_set(LCD_DS_PORT, LCD_DS_PIN, 1);
	*((uint32_t *)LCD_DS_PORT + LCD_DS_PIN) = LCD_DS_PIN;

    // Switch back to 16-bit transfer.
    SSI2->CR1 &= ~(1 << 1);
    SSI2->CR0  =  (1 << 6) | 0x0F;
    SSI2->CR1 |=  (1 << 1);
}

// Most data is 16-bit, but there are a few exceptions during initialization.
// For those few cases this function can be used.
static inline void lcd_data8(uint8_t data) {

    // Wait for previous transmissions to complete, because we're changing the
    // data width.
    while (SSI2->SR & 0x10);

    // Switch to 8-bit transfer.
    SSI2->CR1 &= ~(1 << 1);
    SSI2->CR0  =  (1 << 6) | 0x07;
    SSI2->CR1 |=  (1 << 1);

    // Put the data in the send FIFO
    SSI2->DR = data;

    // Wait for the transmission to complete before we change the data width
    // back.
    while (SSI2->SR & 0x10);

    // Switch back to 16-bit transfer.
    SSI2->CR1 &= ~(1 << 1);
    SSI2->CR0  =  (1 << 6) | 0x0F;
    SSI2->CR1 |=  (1 << 1);
}

// Put a single 16-bit value into the send FIFO. We assume that the SSI is
// configured for 16-bit transmission and that the DS pin is set to high. This
// allows us to make these functions really short and efficient. All these
// functions will return before the data is actually sent over the wire.
// But don't worry, you can keep putting data into the FIFO and these functions
// will wait if it is full. Other functions that reconfigure the SSI or set
// pins will wait for any ongoing transmission to complete before they do
// anything.
static inline void lcd_data(uint16_t data) {
    while (!(SSI2->SR & 0x02)); // make sure the FIFO is not full
    SSI2->DR = data; // Put the data in the SSI transfer register
}

// Puts the same 16-bit value into the SSI FIFO multiple times. Can be used to
// fill a region with a solid color.
static inline void lcd_data_repeated(uint16_t data, uint32_t n) {
    while (n--) {
        while (!(SSI2->SR & 0x02)); // make sure the FIFO is not full
        SSI2->DR = data; // Put the data in the SSI transfer register
    }
}

// Copies data from a buffer into the SSI FIFO. Can be used to transit images.
static inline void lcd_data_buffer(const uint16_t *data, uint32_t n) {
    while (n--) {
        while (!(SSI2->SR & 0x02)); // make sure the FIFO is not full
        SSI2->DR = *data++; // Put the data in the SSI transfer register
    }
}

// Puts the same 16-bit value into the SSI FIFO multiple times. This function
// uses DMA, so there is some setup cost but the function will return long
// before all the data was written into the FITO (assuming n is large).
static inline void lcd_data_repeated_dma(uint16_t data, uint32_t n) {
    while (n) {
        // The DMA can only transfer 1024 items in a row. So we might need to loop
        // if we want to send more than that.
        // TODO: maybe this could be done more efficiently via arbitration.
        uint32_t size = min(n, 1024);
        n -= size;

        while (UDMA->ENASET & (1 << 13)); // Wait for the DMA channel to be free.

        // We need a place in memory to keep the 16-bit value. The parameter
        // to this function doesn't work, because the location will become invalid
        // once this function returns, which should be long before the DMA is
        // finished.
        // It's okay to abuse a static "local" here, because the function
        // can not be entered again before the transmission has ended.
        static uint16_t buffer;
        buffer = data;

        dma_config[13].src_end_point = (uint32_t)&buffer;
        dma_config[13].control = ((0x03U)  << 30) // Don't increment Dst address
                               | ((0x01U)  << 28) // Dst is 2 bytes in size
                               | ((0x03U)  << 26) // Don't increment Src address
                               | ((0x01U)  << 24) // Src is 2 bytes in size
                               | ((0x02U)  << 14) // Arbitration size is 4
                               | ((size-1) <<  4) // Transfer size minus one
                               | ((0x01U)  <<  3) // Use burst transfer
                               | ((0x01U)  <<  0);// Basic transfer mode

        UDMA->USEBURSTSET |= (1 << 13); // Use burst requests
        UDMA->ENASET      |= (1 << 13); // Enable the DMA channel

        SSI2->CR1    &= ~(1 << 1); // Disable SSI2 because we're going to reconfigure it.
        SSI2->DMACTL |=  (1 << 1); // Enable TX DMA
        SSI2->IM     |=  (1 << 3); // Turn on the interrupt. This will be intercepted by the DMA.
        SSI2->CR1    |=  (1 << 1); // Enable SSI2
    }
}

// Copies data from a buffer into the SSI FIFO. This function uses DMA, so there
// is some setup cost but the function will return long before all the data was
// written into the FITO (assuming n is large).
static inline void lcd_data_buffer_dma(const uint16_t *data, uint32_t n) {
    while (n) {
        // The DMA can only transfer 1024 items in a row. So we might need to loop
        // if we want to send more than that.
        // TODO: maybe this could be done more efficiently via arbitration.
        uint32_t size = min(n, 1024);
        n -= size;
        data += size;

        while (UDMA->ENASET & (1 << 13)); // Wait for the DMA channel to be free.

        dma_config[13].src_end_point = (uint32_t)(data - 1);
        dma_config[13].control = ((0x03U)  << 30) // Don't increment Dst address
                               | ((0x01U)  << 28) // Dst is 2 bytes in size
                               | ((0x01U)  << 26) // Increment Src address by 2 byte
                               | ((0x01U)  << 24) // Src is 2 bytes in size
                               | ((0x02U)  << 14) // Arbitration size is 4
                               | ((size-1) <<  4) // Transfer size minus one
                               | ((0x01U)  <<  3) // Use burst transfer
                               | ((0x01U)  <<  0);// Basic transfer mode

        UDMA->USEBURSTSET |= (1 << 13); // Use burst requests
        UDMA->ENASET      |= (1 << 13); // Enable the DMA channel

        SSI2->CR1    &= ~(1 << 1); // Disable SSI2 because we're going to reconfigure it.
        SSI2->DMACTL |=  (1 << 1); // Enable TX DMA
        SSI2->IM     |=  (1 << 3); // Turn on the interrupt. This will be intercepted by the DMA.
        SSI2->CR1    |=  (1 << 1); // Enable SSI2
    }
}

// Sets up the rectangle on the screen where pixels should be written to. Pixels
// will be by ascending x coordinate first. When enough pixels are transferred
// to fill a row of the rectangle set up by this function, the display will
// automatically do a "line break", advance in y and reset the x back to the
// left.
// This function automatically checks that the given rectangle fully lies in the
// screen, and clip off anything off-screen. The size of the clipped rectangle
// is returned and can be used by the caller to check how many pixels have to
// be transmitted afterwards.
static inline uint32_t lcd_window(int x, int y, int width, int height) {
    int xmin = clamp(x,               0, LCD_WIDTH);
    int ymin = clamp(y,               0, LCD_HEIGHT);
    int xmax = clamp(x + abs(width) , 0, LCD_WIDTH);
    int ymax = clamp(y + abs(height), 0, LCD_HEIGHT);
    int size = (xmax - xmin) * (ymax - ymin);
  
    if (size) {
        // Specify Column Range
        lcd_command(ST7735S_CASET);
        lcd_data((uint16_t)(0x02 + xmin));
        lcd_data((uint16_t)(0x02 + xmax - 1));

        // Specify Row Range
        lcd_command(ST7735S_RASET);
        lcd_data((uint16_t)(0x03 + ymin));
        //lcd_data((uint16_t)(0x03 + ymax - 1)); // No point sending the max_y

        // Write Command, the display will expect pixel data after this.
        lcd_command(ST7735S_RAMWR);
    }

    return (uint32_t)size;
}

////////////////////////////////////////////////////////////////////////////////
// Public functions, as advertised in the header!
////////////////////////////////////////////////////////////////////////////////

void lcd_init(void) {

    ////////////////////////////////////////////////////////////////////////////
    // Pin setup
    ////////////////////////////////////////////////////////////////////////////

    gpio_init(LCD_SCK_PORT);
    gpio_mode(LCD_SCK_PORT, LCD_SCK_PIN, ALTERNATE);
    gpio_alternate(LCD_SCK_PORT, LCD_SCK_PIN, 2);

    gpio_init(LCD_MOSI_PORT);
    gpio_mode(LCD_MOSI_PORT, LCD_MOSI_PIN, ALTERNATE);
    gpio_alternate(LCD_MOSI_PORT, LCD_MOSI_PIN, 2);

    gpio_init(LCD_CS_PORT);
    gpio_mode(LCD_CS_PORT, LCD_CS_PIN, OUTPUT);

    gpio_init(LCD_RESET_PORT);
    gpio_mode(LCD_RESET_PORT, LCD_RESET_PIN, OUTPUT);

    gpio_init(LCD_DS_PORT);
    gpio_mode(LCD_DS_PORT, LCD_DS_PIN, OUTPUT);

    // GPIO Default Pin States
    gpio_set(LCD_CS_PORT, LCD_CS_PIN, 0);
    gpio_set(LCD_DS_PORT, LCD_DS_PIN, 1);

    // Pull up for clock (idle high)
    gpio_pullup(LCD_SCK_PORT, LCD_SCK_PIN, 1);

    ////////////////////////////////////////////////////////////////////////////
    // Configure SSI and DMA Peripheries
    ////////////////////////////////////////////////////////////////////////////

    NVIC_EnableIRQ(SSI2_IRQn);

    SYSCTL->RCGCSSI |= 0x04; // Enable SSI2 Clock gate
    delay_us(1);

    SSI2->CR1 &= ~(1 << 1); // Disable SSI2
    SSI2->CR0  = 0x07;      // 8 bit transfers
    SSI2->CC   = 0x00;      // System Clock as Clock Source
    SSI2->CPSR = 0x02;      // Prescaler -> 2 -> SSI Clock = SysClk/2
    SSI2->CR0 |=  (1 << 6); // Clk Idle High
    SSI2->CR1 |=  (1 << 1); // Enable SSI2

    // Point the DMA towards the SSI data register.
    dma_config[13].dst_end_point = (uint32_t)&SSI2->DR;

    // Use UDMA channel 13 ENC 2 for SSI2 TX DMA
    SYSCTL->RCGCDMA   |= 1; // Enable UDMA Clock gate
    delay_us(1);

    UDMA->CFG         |= 1;
    UDMA->CTLBASE      = (uint32_t)dma_config;
    UDMA->PRIOSET     |= (1 << 13); // set priority (probably unnecessary when only one DMA runs)
    UDMA->ALTCLR      |= (1 << 13); // use the primary control structure
    UDMA->REQMASKCLR  |= (1 << 13); // unmask the DMA channel
    UDMA->CHMAP1      |= (0x02) << 20; // Use Source 2 (SSI2) in channel 13.

    ////////////////////////////////////////////////////////////////////////////
    // Reset and initialize the screen
    ////////////////////////////////////////////////////////////////////////////

    // LCD Power Reset
    gpio_set(LCD_RESET_PORT, LCD_RESET_PIN, 1);
    delay_ms(100);
    gpio_set(LCD_RESET_PORT, LCD_RESET_PIN, 0);
    delay_ms(50);
    gpio_set(LCD_RESET_PORT, LCD_RESET_PIN, 1);
    delay_ms(120);

    // Software Reset
    lcd_command(ST7735S_SWRESET);
    delay_ms(150);

    // Sleep-Out (Wakeup)
    lcd_command(ST7735S_SLPOUT);
    delay_ms(200);

    // Initialization Sequence
    lcd_command(ST7735S_FRMCTR1);//In normal mode(Full colors)
    // Note:
    // fps = 850kHz/((RTNA * 2 + 40) * (LINE_WIDTH + FPB + BPB + 2))

    // 100Hz:
    lcd_data8(5);//RTNA: set 1-line period
    lcd_data8(20);//FPB:  front porch
    lcd_data8(20);//BPB:  back porch

    // 50Hz:
    //lcd_data8(14);//RTNA: set 1-line period
    //lcd_data8(63);//FPB:  front porch
    //lcd_data8(57);//BPB:  back porch
    
    // 81.511315689Hz:
    //lcd_data8(0x02);//RTNA: set 1-line period
    //lcd_data8(0x35);//FPB:  front porch
    //lcd_data8(0x36);//BPB:  back porch

    lcd_command(ST7735S_GAMSET);
    lcd_data8(0x04);

    lcd_command(ST7735S_COLMOD);
    lcd_data8(0x05);
    delay_ms(10);

    lcd_command(ST7735S_NORON);
    delay_ms(10);

    lcd_command(ST7735S_DISPON);
    delay_ms(120);

    lcd_command(ST7735S_MADCTL);
    lcd_data8(ST7735S_MADCTL_MX | ST7735S_MADCTL_MY | ST7735S_MADCTL_RGB);
}

void lcd_fill(color_t color) {
    // Select the whole screen
    uint32_t size = lcd_window(0, 0, LCD_WIDTH, LCD_HEIGHT);
    lcd_data_repeated_dma(color.val, size);
}

void lcd_pixel(int x, int y, color_t color) {
    // Select a single pixel. If the pixel is off-screen, lcd_window will return 0.
    if (lcd_window(x, y, 1, 1)) {
        // Set pixel color value (RGB565 format)
        lcd_data(color.val);
    }
}

void lcd_horizontal_line(int x0, int y, int x1, color_t color) {
    int xmin = min(x0, x1);
    int xmax = max(x0, x1);
    uint32_t size = lcd_window(xmin, y, xmax - xmin + 1, 1);
    lcd_data_repeated(color.val, size);
}

void lcd_vertical_line(int x, int y0, int y1, color_t color) {
    int ymin = min(y0, y1);
    int ymax = max(y0, y1);
    uint32_t size = lcd_window(x, ymin, 1, ymax - ymin + 1);
    lcd_data_repeated(color.val, size);
}

void lcd_line(int x0, int y0, int x1, int y1, color_t color) {

    int dx = x1 - x0;
    int dy = y1 - y0;

    if (dy * dy < dx * dx) { // use square because we want to compare magnitudes, not signs
        // Horizontal-ish case
        float m = (float)dy / fabsf((float)dx);
        int sign = (dx < 0 ? -1 : 1);
        float y = (float)y0;
        for (int x = x0; x != x1; x += sign) {
            lcd_pixel(x, (int)y, color);
            y += m;
        }
    } else {
        // Vertical-ish case
        float m = (float)dx / fabsf((float)dy);
        int sign = (dy < 0 ? -1 : 1);
        float x = (float)x0;
        for (int y = y0; y != y1; y += sign) {
            lcd_pixel((int)x, y, color);
            x += m;
        }
    }
}

void lcd_dashed_line(int x0, int y0, int x1, int y1, int len_line, color_t color) {
    int dx = x1 - x0;
    int dy = y1 - y0;

    int len = 0;
    int visible = 1;

    if (dy * dy < dx * dx) { // use square because we want to compare magnitudes, not signs
        // Horizontal-ish case
        float m = (float)dy / fabsf((float)dx);
        int sign = (dx < 0 ? -1 : 1);
        float y = (float)y0;
        for (int x = x0; x != x1; x += sign) {
            if (len == len_line) {
                len = 0;
                visible = !visible;
            }
            if (visible) {
                lcd_pixel(x, (int)y, color);
            }
            y += m;
        }
    } else {
        // Vertical-ish case
        float m = (float)dx / fabsf((float)dy);
        int sign = (dy < 0 ? -1 : 1);
        float x = (float)x0;
        for (int y = y0; y != y1; y += sign) {
            if (len == len_line) {
                len = 0;
                visible = !visible;
            }
            if (visible) {
                lcd_pixel((int)x, y, color);
            }
            x += m;
        }
    }
}

void lcd_rectangle(int x0, int y0, int x1, int y1, color_t color) {
    int xmin = min(x0, x1);
    int ymin = min(y0, y1);
    int xmax = max(x0, x1);
    int ymax = max(y0, y1);
    lcd_horizontal_line(x0, ymin, x1, color);
    lcd_horizontal_line(x0, ymax - 1, x1, color);
    lcd_vertical_line(xmin, y0, y1, color);
    lcd_vertical_line(xmax - 1, y0, y1, color);
}

void lcd_rectangle_fill(int x0, int y0, int x1, int y1, color_t color) {
    int xmin = min(x0, x1);
    int ymin = min(y0, y1);
    int xmax = max(x0, x1);
    int ymax = max(y0, y1);
    uint32_t size = lcd_window(xmin, ymin, xmax - xmin, ymax - ymin);
    lcd_data_repeated_dma(color.val, size);
}

void lcd_circle(int x0, int y0, int radius, color_t color) {
    int x = radius;
    int y = 0;
    int radiusError = 1 - radius;

    while (y <= x) {
        lcd_pixel(x0 - y, y0 + x, color);
        lcd_pixel(x0 + y, y0 + x, color);
        lcd_pixel(x0 - x, y0 + y, color);
        lcd_pixel(x0 + x, y0 + y, color);
        lcd_pixel(x0 - x, y0 - y, color);
        lcd_pixel(x0 + x, y0 - y, color);
        lcd_pixel(x0 - y, y0 - x, color);
        lcd_pixel(x0 + y, y0 - x, color);

        y++;
        if (radiusError < 0) {
          radiusError += 2 * y + 1;
        } else {
          x--;
          radiusError += 2 * (y - x) + 1;
        }
    }
}

void lcd_circle_fill(int x0, int y0, int radius, color_t color) {
    int x = radius;
    int y = 0;
    int radiusError = 1 - radius;

    while (y <= x) {
        uint32_t size;
        size = lcd_window(x0 - y, y0 + x, 2*y, 1);
        lcd_data_repeated(color.val, size);

        size = lcd_window(x0 - y, y0 - x, 2*y, 1);
        lcd_data_repeated(color.val, size);

        size = lcd_window(x0 - x, y0 + y, 2*x, 1);
        lcd_data_repeated(color.val, size);

        size = lcd_window(x0 - x, y0 - y, 2*x, 1);
        lcd_data_repeated(color.val, size);

        y++;
        if (radiusError < 0) {
            radiusError += 2 * y + 1;
        } else {
            x--;
            radiusError += 2 * (y -  x) + 1;
        }
    }
}

void lcd_character(int x, int y, char ch, color_t colorf, color_t colorb) {
    // Bounds check
    if(ch > 127)
        return;

    lcd_window(x, y, 8, 8);

    // TODO: if the character is partially off-screen, this breaks!
    uint32_t bitmap = font8x8_basic[(uint32_t)ch][0];
    for (int i = 0; i < 32; ++i) {
        lcd_data(bitmap & (1 << i) ? colorf.val : colorb.val);
    }
    bitmap = font8x8_basic[(uint32_t)ch][1];
    for (int i = 0; i < 32; ++i) {
        lcd_data(bitmap & (1 << i) ? colorf.val : colorb.val);
    }
}

int cursor_x;
int cursor_y;

void lcd_print_rgb(const char *ch, color_t colorf, color_t colorb) {
    int i = 0;
    uint8_t x_bound = cursor_x;

    while (ch[i] != '\0') {
        if (ch[i] == '\n') {
            cursor_y += 8;
            cursor_x = x_bound;
            i++;
            continue;
        }
        lcd_character(cursor_x, cursor_y, ch[i], colorf, colorb);
        i++;
        cursor_x += 8;
    }
}

void lcd_println_rgb(const char *ch, color_t colorf, color_t colorb) {
    int x_bound = cursor_x;
    lcd_print_rgb(ch, colorf, colorb);
    cursor_y += 8;
    cursor_x = x_bound;
}

void lcd_print(const char *ch) {
    lcd_print_rgb(ch, COLOR_WHITE, COLOR_BLACK);
}

void lcd_println(const char *ch) {
    lcd_println_rgb(ch, COLOR_WHITE, COLOR_BLACK);
}

void lcd_cursor(int x, int y) {
    cursor_x = x;
    cursor_y = y;
}

void lcd_bitmap(int x, int y, const color_t *bitmap, int width, int height) {

    lcd_window(x, y, width, height);

    int start_y = max(0, -y);
    int end_y   = min(LCD_HEIGHT, y + height) - y;

    const uint16_t *data = (const uint16_t *)bitmap;
    data += start_y * width;

    if (x < 0 || LCD_WIDTH < x + width) {
        // Worst case: the image is cut off on the side.
        // We have to transfer the incomplete rows one by one.
        int start_x = max(0, -x);
        int end_x   = min(LCD_WIDTH, x + width) - x;
        int w       = end_x - start_x;
        data += start_x;
        for (int i = start_y; i < end_y; ++i) {
            lcd_data_buffer_dma(data, w);
            data += width;
        }
    } else {
        // Otherwise we can transfer all pixels at once.
        // If some rows are off-screen, we just start later
        // or end earlier in the image.
        int h = end_y - start_y;
        lcd_data_buffer_dma(data, h * width);
    }
}

void lcd_bitmap_alpha(int x, int y, const color_t *bitmap, int width, int height, color_t alphacolor) {

    int start_x = max(0, -x);
    int start_y = max(0, -y);
    int end_x   = min(LCD_WIDTH,  x + width)  - x;
    int end_y   = min(LCD_HEIGHT, y + height) - y;

    int window_moved = 1;
    int was_skipping = 0;

    for (int j = start_y; j < end_y; ++j) {
        if (window_moved) {
            window_moved = 0;
            lcd_window(x + start_x, y + j, end_x - start_x, end_y - j);
        }
        for (int i = 0; i < width; ++i) {
            color_t color = bitmap[j * width + i];
            if(color.val == alphacolor.val) {
                was_skipping = 1;
                continue;
            }
            if (was_skipping) {
                was_skipping = 0;
                lcd_window(x + i, y + j, end_x - i, end_y - j);
                window_moved = 1;
            }
            lcd_data(color.val);
        }
    }
}

void lcd_bitmap_scaled(int x0, int y0, int x1, int y1, const color_t *bitmap, int tex_width, int tex_height) {

    int xmin = min(x0, x1);
    int ymin = min(y0, y1);
    int xmax = max(x0, x1);
    int ymax = max(y0, y1);

    lcd_window(xmin, ymin, xmax - xmin, ymax - ymin);

    int start_x = max(0, -xmin);
    int start_y = max(0, -ymin);
    int end_x   = min(LCD_WIDTH,  xmax) - xmin;
    int end_y   = min(LCD_HEIGHT, ymax) - ymin;
    int w = end_x - start_x;
    int h = end_y - start_y;

    for (int y = 0; y < h; ++y) {
        int ty = y * tex_height / h;
        for (int x = 0; x < w; ++x) {
            int tx = x * tex_width / w;
            lcd_data(bitmap[tx + ty * tex_width].val);
        }
    }
}
