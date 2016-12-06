#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>


void mat_mult(int n, double *A, double *B, double *C);
void init(int n, int size, double **A, double **B, double **C, int **ptr);
long usecs ();




int main(){

  int    size, i, *ptr, ld, m, n, k;
  double *A, *B, *C;
  long   t_start, t_end;


  size = 1500;

  init(n, size, &A, &B, &C, &ptr);


  t_start = usecs();
  /* Parallelize this loop */
 /* CC: the dynamic schedule is important for performnce because the
    cost of a matrix multiplication increases with the cibe of n */
#pragma omp parallel for schedule(dynamic,1)
  for (n=size; n>=100; n-=100){
    /* Multiply two matrices of size n:  C = A*B  */
    mat_mult(n, A+ptr[n], B+ptr[n], C+ptr[n]);
  }
  t_end = usecs();
  printf("time    : %8.2f msec.\n",((double)t_end-t_start)/1000.0);


  return 0;

}







/* Matrix multiplication routine */
void mat_mult(int n, double *A, double *B, double *C){
  double alpha, beta;
  char   NoTran='N';
  
  alpha = 3.1;
  beta  = 0.7;

  dgemm_(&NoTran, &NoTran, 
         &n, &n, &n, 
         &alpha,
         A, &n,
         B, &n,
         &beta, 
         C, &n);

  return;
}





/* Initialization routine */
void init(int n, int size, double **A, double **B, double **C, int **ptr){

  int i, totsize;

  totsize=0;
  *ptr = (int*)malloc((size+1)*sizeof(int));

  for (i=100; i<=size; i+=100){
    (*ptr)[i] = totsize;
    totsize+=i*i;
  }

  *A = (double*)malloc(totsize*sizeof(double)); 
  *B = (double*)malloc(totsize*sizeof(double)); 
  *C = (double*)malloc(totsize*sizeof(double)); 
  
  for (i=0; i<totsize; i++){
    (*A)[i] = (double)rand()/RAND_MAX;
    (*B)[i] = (double)rand()/RAND_MAX;
    (*C)[i] = (double)rand()/RAND_MAX;
  }

  return;

}


/* Timer */
long usecs (){
  struct timeval t;

  gettimeofday(&t,NULL);
  return t.tv_sec*1000000+t.tv_usec;
}
