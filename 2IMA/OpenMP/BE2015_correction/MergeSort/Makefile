CC       = gcc
LINK     = $(CC)

CFLAGS   = -O3 -fopenmp 
LDFLAGS  = $(CFLAGS) 

OBJS = mergesort.o

main: $(OBJS)
	$(LINK) -o $@  $(LDFLAGS) $(OBJS) $(LIBS)


dbg: 
	($(MAKE) clean main CDEFS=-DDEBUG)




clean:
	(rm -f *.o main)



%.o: %.c
	$(CC)  $(CFLAGS)  $(CINCLUDES) $(INCLUDES) $(CDEFS) $(PREC) -c $<





