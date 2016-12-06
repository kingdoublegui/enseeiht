#include "aux.h"


int main(int argc, char **argv) {

  int n, *left, *right, *dataseq, *datapar;
  int i, l, r;
  long ts, te;

  if ( argc == 2 ) {
    l = atoi(argv[1]);    /* the number of levels in the tree */
  } else {
    printf("Usage:\n\n ./main l\n\nwhere l is the number of levels in the tree.\n");
    return 1;
  }
  
  
  treeinit(&n, &left, &right, &dataseq, l);

  printf("==================================================\n\n");
  printf("Starting sequential execution\n");
  
  ts = usecs();
  treetrav_seq(n, dataseq, left, right);
  te = usecs()-ts;

  printf("Sequential execution time: %6ld  msec.\n",te/1000);

  
  free(left);
  free(right);
  n=0;

  treeinit(&n, &left, &right, &datapar, l);
  
  printf("\nStarting parallel execution\n");

  ts = usecs();
  treetrav_par(n, datapar, left, right);
  te = usecs()-ts;
  printf("Parallel   execution time: %6ld  msec.\n",te/1000);
  

  /* check result */
  for(i=0; i<n; i++){
    if(dataseq[i] != datapar[i]){
      printf("\n\nThe result is wrong!\n");
      free(left);
      free(right);
      free(dataseq);
      free(datapar);
      return 1;
    }
  }
  
  printf("\n\nThe result is correct!\n");
  free(left);
  free(right);
  free(dataseq);
  free(datapar);
  return 0;

}

