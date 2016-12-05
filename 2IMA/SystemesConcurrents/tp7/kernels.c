#include "trace.h"
#include "common.h"
#include <math.h>
#include <stdlib.h>

void panel(Matrix A, int i){

  int m, ld, info, np, ii;

  ii  = i*A.B;
  np  = A.B;
  m   = A.N-ii;
  ld  = A.N;
#if defined(DBG)
  printf("%2d -- panel : %d\n",omp_get_thread_num(),i);
#endif
  trace_event_start(PNL);
  dgetrf_(&m, &np, A.A+ii*ld+ii, &ld, A.ipiv+ii, &info);
  trace_event_stop(PNL);

}


void update(Matrix A, int i, int j){

  int m, mu, nu, np, ld, info, ii, jj;
  char NoTran = 'N', Lower='L', Unit='U', Left='L';
  int IONE=1, IMONE=-1, IZERO=0;
  double DONE=(double)1.0, DMONE=-1.0, DZERO=0.0;

  ii = i*A.B;
  jj = j*A.B;
  np = A.B;
  nu = A.B;

  m   = A.N-ii;
  mu  = m-np;
  ld  = A.N;
  
#if defined(DBG)
  printf("%2d -- update: %d %d\n",omp_get_thread_num(),i,j);
#endif
  trace_event_start(UPD);
  dlaswp_(&nu, A.A+jj*ld+ii, &ld, &IONE, &np, A.ipiv+ii, &IONE);
  dtrsm_ (&Left, &Lower, &NoTran, &Unit, 
          &np, &nu, 
          &DONE, 
          A.A+ii*ld+ii, &ld,
          A.A+jj*ld+ii, &ld);
  dgemm_ (&NoTran, &NoTran,
          &mu, &nu, &np,
          &DMONE, 
          A.A+ii*ld+ii+np, &ld,
          A.A+jj*ld+ii, &ld,
          &DONE,
          A.A+jj*ld+ii+np, &ld);
  trace_event_stop(UPD);

  return;
}


void backperm(Matrix A){

  int i, j, ld, ipb, ipo;
  int IONE=1;

  ld  = A.N;

  trace_event_start(END);
  for(i=A.B; i<A.N; i+=A.B){
    for(j=i; j<A.N; j++)
      A.ipiv[j]+=A.B;
    ipo = i+1;
    ipb = i+A.B;
#pragma omp parallel for
      for(j=0; j<i; j+=A.B)
        dlaswp_(&A.B, A.A+j*ld, &ld, &ipo, &ipb, A.ipiv, &IONE);
  }
  trace_event_stop(END);

  return;
}


void checkres(Matrix A, double *Acpy){

  int     ISEED[4] = {0,0,0,3};
  int     IONE=1;
  char    NoTran = 'N';
  double  DONE=1.0, DMONE=-1.0;
  double  *x, *b;
  int info;
  double  flops, nrm2, nrmf;

  x      = (double *)malloc(A.N*sizeof(double));
  b      = (double *)malloc(A.N*sizeof(double));


  dlarnv_(&IONE, ISEED, &A.N,  b);
  memcpy( x, b, A.N*sizeof(double) );

  dgetrs_(&NoTran, &(A.N), &IONE, A.A, &(A.N), A.ipiv, x, &(A.N), &info);

  dgemv_(&NoTran, &A.N, &A.N, &DONE, Acpy, &A.N, x, &IONE, &DMONE, b, &IONE);
  nrm2 = dnrm2_c(A.N, b, IONE);
  nrmf = dnrmf_c(A.N, A.N, Acpy, A.N);
  printf("Residual norm: %e\n",sqrt(A.N)*nrm2/nrmf);

  free(x);
  free(b);

  return;

}
