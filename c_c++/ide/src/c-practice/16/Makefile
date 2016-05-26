# **** GNU make manual: http://www.gnu.org/software/make/manual/make.html ****

# setting compiler and compile options
CC      = gcc
LD      = ld
CFLAGS  = -ggdb -MD -Wall -Werror -fno-strict-aliasing -O2


# target to compile
CFILES  = $(shell find . -name "*.c")
OBJS    = $(CFILES:.c=.out)

all: $(OBJS)

$(OBJS): %.out: %.c
	$(CC) -o $@ $< $(CFLAGS)


clean:
	-rm -f $(OBJS) $(OBJS:.o=.d)
