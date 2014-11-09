CC      = gcc
LDFLAGS = -lm
CFLAGS  = -ggdb -MD -Wall -Werror -O2


# target to compile
CFILES  = $(shell find -name "*.c")
OBJS    = $(CFILES:.c=.o)

%.o: %.c
	$(CC) -c -o $@ $< $(CFLAGS)

calculator: $(OBJS)
	$(CC) $(CFLAGS) $(CFILES) -o calculator $(LDFLAGS) 

run: calculator
	./calculator

gdb: calculator
	gdb --args ./calculator


