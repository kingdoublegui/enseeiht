CC       = gcc
LINK     = $(CC)

CFLAGS   = -O3 -fopenmp -Wno-unused-result -Wno-pointer-to-int-cast -Wno-format-security
LDFLAGS  = $(CFLAGS) 

OBJS = kernels.o iohb.o readmat.o ConjugateGradient.o

main: $(OBJS)
	$(LINK) -o $@  $(LDFLAGS) $(OBJS) -lm






clean:
	(rm -f *.o main)



%.o: %.c
	$(CC)  $(CFLAGS)  $(CINCLUDES) $(INCLUDES) $(CDEFS) $(PREC) -c $<
%.o: %.f90				       
	$(FC)  $(FCFLAGS) $(FINCLUDES) $(INCLUDES) $(FDEFS) $(DEFINE_PREPEND)$(PREC) -c $<




