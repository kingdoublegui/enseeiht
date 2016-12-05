#include "trace.h"
#include "common.h"

/* This is a sequential routine for the LU factorization of a square
   matrix in block-columns */
void lu_seq(Matrix A){


  int i, j;

  trace_init();

  for(i=0; i<A.NB; i++){
    /* Do the panel */
    panel(A, i);
    
    for(j=i+1; j<A.NB; j++){
      /* Do all the correspondint updates */
      update(A, i, j);}
    
  }
  
  /* Do row permutations resulting from the numerical pivoting */
  backperm(A);

  trace_dump("trace_seq.svg");

  return;

}

