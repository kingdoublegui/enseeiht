#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <math.h>
#include "omp.h"
#include "aux.h"

void sequential_product(block **a, block **b, block **c, int n, int nb);
void parallel_product(block **a, block **b, block **c, int n, int nb);

int main(int argc, char **argv){
  int   n, nb;
  long  t_start, t_end;
  block **a, **b, **c, **d;
 
  // Command line argument: array length
  if ( argc == 2 ) {
    n = atoi(argv[1]);    /* for a matrix of size nxn blocks */
  } else {
    printf("Usage:\n\n ./main n\n\nwhere n is the number of blocks in rows and columns of the matrices.\n");
    return 1;
  }

  /* Statically fixed to 100, no need to change this unless for debugging */
  nb = 100;
  
  init_data(&a, &b, &c, &d, n, nb);

  
  /* Sequential version */
  t_start = usecs();
  sequential_product(a, b, c, n, nb);
  t_end = usecs();
  printf("Sequential   time    : %8.2f msec.\n",((double)t_end-t_start)/1000.0);



  /* Parallel with tasks */
  t_start = usecs();
  parallel_product(a, b, d, n, nb);
  t_end = usecs();
  printf("Parallel     time    : %8.2f msec.\n",((double)t_end-t_start)/1000.0);

  /* Comprare the two resulting matrices */
  compare_matrices(c, d, n, nb);
  return 0;
  
}


void sequential_product(block **a, block **b, block **c, int n, int nb){
  int i, j, k;
  
  for(i=0; i<n; i++)
    for(j=0; j<n; j++)
      for(k=0; k<n; k++)
        {
          block_mult(a[i][k], b[k][j], c[i][j], nb);
        }

}
  


void parallel_product(block **a, block **b, block **c, int n, int nb){
  int i, j, k;


  for(i=0; i<n; i++)
    for(j=0; j<n; j++)
      for(k=0; k<n; k++)
        {
          block_mult(a[i][k], b[k][j], c[i][j], nb);
        }

}
