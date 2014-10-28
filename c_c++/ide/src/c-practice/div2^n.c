#include <stdio.h>

int main(int argc, char *argv[]) {
	unsigned int a = 8;
	int b = 8;
	
	printf("%d %d", a/4, b/4);
}

/*learning assembly:
	1. the register needs to be prefixed with a "%". Constant numbers need to be prefixed with a "$".
	2.GAS assembly instructions are generally suffixed with the letters "b", "s", "w", "l", "q" or "t" to determine what size operand is being manipulated.

	*    b = byte (8 bit)
	*    s = short (16 bit integer) or single (32-bit floating point)
	*    w = word (16 bit)
	*    l = long (32 bit integer or 64-bit floating point)
	*    q = quad (64 bit)
	*    t = ten bytes (80-bit floating point)

	3.Examples:

	movl    -4(%ebp, %edx, 4), %eax  # Full example: load *(ebp - 4 + (edx * 4)) into eax
	movl    -4(%ebp), %eax           # Typical example: load a stack variable into eax
	movl    (%ecx), %edx             # No offset: copy the target of a pointer into a register
	leal    8(,%eax,4), %eax         # Arithmetic: multiply eax by 4 and add 8
	leal    (%eax,%eax,2), %eax      # Arithmetic: multiply eax by 2 and add eax (i.e. multiply by 3)

	movw   $ff00,%ax        # ax=1111.1111.0000.0000 (0xff00, unsigned 65280, signed -256) 
	shrw   $3,%ax           # ax=0001.1111.1110.0000 (0x1fe0, signed and unsigned 8160)
                        # (logical shifting unsigned numbers right by 3
                        #   is like integer division by 8)
	shlw   $1,%ax           # ax=0011.1111.1100.0000 (0x3fc0, signed and unsigned 16320) 
                        # (logical shifting unsigned numbers left by 1
                        #   is like multiplication by 2)

	movw   $ff00,%ax        # ax=1111.1111.0000.0000 (0xff00, unsigned 65280, signed -256)
	salw   $2,%ax           # ax=1111.1100.0000.0000 (0xfc00, unsigned 64512, signed -1024)
                        # (arithmetic shifting left by 2 is like multiplication by 4 for
                        #   negative numbers, but has an impact on positives with most
                        #   significant bit set (i.e. set bits shifted out))
	sarw   $5,%ax           # ax=1111.1111.1110.0000 (0xffe0, unsigned 65504, signed -32)
                        # (arithmetic shifting right by 5 is like integer division by 32
                        #   for negative numbers)
 */
