#include <math.h>
#include <string.h>
#include<stdlib.h>
#include<stdio.h>
#include "iohb.h"
#include "common.h"


int main(int argc, char *argv[])
{
  int n;
  int *rowptr, *colind;
  double *val, *x, *b, *r, *d, *t;
  int i, j, cnt, c, it, itmax;
  double alpha, beta, rnrm, eps, rp, rp_old;
  long   t_start, t_end;


  if (argc != 2)
    {
      fprintf(stderr,"Usage: \n%s Matrix-file \n", argv[0]);
      exit(-1);
    }
  
  /* Read the input matrix */
  readmat(argv[1], &n, &n, &rowptr, &colind, &val);


  b = (double*)malloc(n*sizeof(double));
  x = (double*)malloc(n*sizeof(double));
  r = (double*)malloc(n*sizeof(double));
  d = (double*)malloc(n*sizeof(double));
  t = (double*)malloc(n*sizeof(double));
  

  /* Initialize the right-hand side, solution and residual vectors */
  for(i=0; i<n; i++){
    b[i] = 1.0;
    r[i] = b[i];
    x[i] = 1.0;
  }

  /* Threshold value for the stopping criterion */
  eps = 1e-10;
  /* Maximum number of iteration allowed */
  itmax = n*4;

  /* Start the Conjugate gradient method */
  t_start = usecs();

  /* r = b-A*x */
  spmv(n, rowptr, colind, val, -1.0, x, 1.0, r);

  /* d = r */
  for(i=0; i<n; i++){
    d[i] = r[i];
  }

  /* rp = r'*r */
  rp = dot(n,r,r);

  for(it=0;it<itmax;it++){
    /* rnrm = ||r||_2 */
    rnrm = norm2(n, r);
    if(it%10 == 0)
      /* print the residual norm at this iteration */
      printf("%4d -- rnrm %e\n",it,rnrm);

    if(rnrm < eps) 
      /* exit if the residual norm falls below the threshold */
      break;
    
    /* t = A*d */
    spmv(n, rowptr, colind, val, 1.0, d, 0.0, t);
    /* alpha = (r'*r)/(d'*(A*d)) */
    alpha = rp/dot(n,d,t);

    /* x = x+alpha*d */
    axpby(n, alpha, d, 1.0, x);
    /* r = r-alpha*A*d */
    axpby(n, -alpha, t, 1.0, r);

    /* Save the old r'*r */
    rp_old = rp;
    /* rp = r'*r */
    rp = dot(n,r,r);

    /* beta = rp / rp_old */
    beta = rp/rp_old;

    /* d = r+beta*d */
    axpby(n, 1.0, r, beta, d);

  }
  t_end = usecs();

  if(it>=itmax && rnrm>eps){
    printf("\nThe Conjugate Gradient method did not converge\n");
    return 1;
  } else {
  /* Print the final number of iterations and the residual norm */
  printf("\nThe method converged in %4d iterations \nwith a residual norm %e\n",it,rnrm);
  printf("Solution time    : %8.2f msec.\n",((double)t_end-t_start)/1000.0);
  return 0;
  }

}



