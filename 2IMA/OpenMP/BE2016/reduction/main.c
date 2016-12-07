#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "omp.h"
#include "aux.h"

int sequential_reduction(int *x, int n);
int parallel_reduction(int *x, int n);


int main(int argc, char **argv){
  int  n, i, result;
  long t_start, t_end;
  int  *x;
  
  // Command line argument: array length
  if ( argc == 2 ) {
    n = atoi(argv[1]);    /* the length of the pref */
  } else {
    printf("Usage:\n\n ./main n\n\nwhere n is the length of the array to be used.\n");
    return 1;
  }


  x=(int *)malloc(sizeof(int)*n);

  
  /* Fill the array with random numbers */
  srand(1);
  for (i = 0; i < n; i++) 
    x[i] = rand() % n;

  /* Sequential reduction */
  t_start = usecs();
  result = sequential_reduction(x, n);
  t_end = usecs();
  printf("Sequential time : %8.2f msec.  ---  Result: %d\n",((double)t_end-t_start)/1000.0, result);
  



  /* Fill the array with random numbers */
  srand(1);
  for (i = 0; i < n; i++) 
    x[i] = rand() % n;

  /* Parallel reduction */
  t_start = usecs();
  result = parallel_reduction(x, n);
  t_end = usecs();
  printf("Parallel   time : %8.2f msec.  ---  Result: %d\n",((double)t_end-t_start)/1000.0, result);

  
  return 0;
}



int sequential_reduction(int *x, int n){
  int i;

  for(i=1; i<n; i++)
    operator(x, x+i);

  return x[0];
}

int parallel_reduction(int *x, int n) {
    int result;
    #pragma omp parallel
    {
        #pragma omp single
        {
            result = parallel_reduction_rec(x, n);
        }
    }
    return result;
}

int parallel_reduction_rec(int *x, int n){
  int i;

  int m = n/2;
  int seuil = 2;

  int j;
  if (m <= seuil) {
    for (j=1; j < n; j++)
      operator(x, x+j);
    return x[0];
  }
  
#pragma omp task if (m > seuil)
  {
  parallel_reduction_rec(x, m);
}
#pragma omp task if (m > seuil)
{
  parallel_reduction_rec(x+m, n-m);
}

#pragma omp taskwait
  operator(x, x+m);
  
  return x[0];
}
