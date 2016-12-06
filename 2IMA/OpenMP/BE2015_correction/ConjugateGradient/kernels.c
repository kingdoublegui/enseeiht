#include <math.h>

/* This routine computes the 2-norm of a vector */
double norm2(int n, double *x){
  int i;
  double res;
  
  res = 0.0;

 /* CC: reduction is necessary to have a correct result */
#pragma omp parallel for reduction(+:res)
  for(i=0; i<n; i++)
    res += x[i]*x[i];

  res = sqrt(res);

  return res;

}


/* This routine computes the dot-product of two vectors */
double dot(int n, double *x, double *y){
  int i;
  double res;
  
  res = 0.0;

 /* CC: reduction is necessary to have a correct result */
#pragma omp parallel for reduction(+:res)
  for(i=0; i<n; i++)
    res += x[i]*y[i];

  return res;

}

/* This routine computes the product of a sparse matrix A of size m
   times a vector x and stores the result in a vector y :
   
   y = alpha*A*x + beta*y */
void spmv(int n, int *rowptr, int *colind, double *val, double alpha, double *x, double beta, double *y){

  int i, j;

 /* CC: simple parallelization by rows. The dynamic scheculing helps
    in case the matrix has rows with different number of nonzeroes. */
#pragma omp parallel for private(j) //schedule(dynamic,50)
  for(i=0; i<n; i++){
    /* for each row... */
    y[i] = beta*y[i];
    for(j=rowptr[i]; j<rowptr[i+1]; j++){
      /* for each coefficient in the row... */
      y[i] += alpha*val[j]*x[colind[j]];
    }
  }

  return;
}

/* This routine computes the sum of two vectors x and y of size m and
   stores the result in y

   y = beta*y + alpha*x */
void axpby(int n, double alpha, double *x, double beta, double *y){

  int i;

 /* CC: as easy as it gets */
#pragma omp parallel for 
  for(i=0; i<n; i++)
    y[i] = beta*y[i]+alpha*x[i];

}

