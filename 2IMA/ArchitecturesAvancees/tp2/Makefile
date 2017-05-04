
# BLAS
LIBBLAS=-lblas
# OpenBLAS
INCOPEN=-I/mnt/n7fs/ens/tp_guivarch/opt/OpenBLAS
LIBOPEN=-L/mnt/n7fs/ens/tp_guivarch/opt/OpenBLAS -lopenblas -lpthread


# FLAGS
CFLAGS= -O3

all : mat_mul_blas 
	

mat_mul_blas: mat_mul_blas.o
	gcc mat_mul_blas.o $(LIBBLAS) -o mat_mul_blas

mat_mul_blas.o : mat_mul_blas.c
	gcc -c -Wall $(CFLAGS) mat_mul_blas.c

clean :
	rm -f *.o mat_mul_blas
