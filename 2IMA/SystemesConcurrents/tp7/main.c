#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <math.h>
#include "common.h"




int main(int argc, char **argv){

  int     N, NB, B, NN;
  double  flops, nrm2, nrmf;
  double  *Acpy, *x, *b;
  Matrix  A;
  long    t_start,t_end;
  int     *ipiv;
  int     info, nth;
  int     ISEED[4] = {0,0,0,1};
  int     IONE=1;
  char    NoTran = 'N', *nt;
  double  DONE=1.0, DMONE=-1.0;


  if(argc != 3){
    printf("Usage:\n\n./main B NB\n\nwhere B is the size of block-columns and \n\
NB is the number of block-columns the matrix is made of.\n");
    return 1;
  }
  
  
  B      = atoi(argv[1]);    /* block size */
  NB     = atoi(argv[2]);    /* dimension in blocks */
  N      = B*NB;
  NN     = N*N;

  nt     = getenv("OMP_NUM_THREADS");

  flops  = ((double)2.0*(double) N)*((double) N)*((double) N)/3.0;

  Acpy   = (double *)malloc(NN*sizeof(double));
  A.A    = (double *)malloc(NN*sizeof(double));
  A.ipiv = (int *)malloc(N*sizeof(int));

  dlarnv_(&IONE, ISEED, &NN, Acpy);
  
  A.B  = B;
  A.NB = NB;
  A.N  = N;
  memcpy( A.A, Acpy, N*N*sizeof(double) ); 

  printf("Matrix size: %d\n",N);

  
  printf("\n========== Sequential    (1 threads) ==========\n" );
  t_start = usecs();
  lu_seq(A);
  t_end = usecs();
  printf("Time (msec.) : %7.1f\n",(t_end-t_start)/1e3);
  printf("Gflop/s      : %7.1f\n",flops/(t_end-t_start)/1e3);
  checkres(A, Acpy);





  printf("\n========== Loop Parallel (%1s threads) ==========\n",nt);
  memcpy( A.A, Acpy, N*N*sizeof(double) ); 
  t_start = usecs();
  lu_par_loop(A);
  t_end = usecs();
  printf("Time (msec.) : %7.1f\n",(t_end-t_start)/1e3);
  printf("Gflop/s      : %7.1f\n",flops/(t_end-t_start)/1e3);
  checkres(A, Acpy);






  printf("\n========== DAG  Parallel (%1s threads) ==========\n",nt);
  memcpy( A.A, Acpy, N*N*sizeof(double) ); 
  t_start = usecs();
  lu_par_dag(A);
  t_end = usecs();
  printf("Time (msec.) : %7.1f\n",(t_end-t_start)/1e3);
  printf("Gflop/s      : %7.1f\n",flops/(t_end-t_start)/1e3);
  checkres(A, Acpy);

  return 0;


}
