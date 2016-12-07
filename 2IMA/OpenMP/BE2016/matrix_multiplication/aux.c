#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <math.h>
#include "aux.h"

int     ISEED[4] = {0,0,0,1};
int     IONE=1;
char    NoTran = 'N';
double  DONE=1.0, DMONE=-1.0;
double alpha=1.0, beta=1.0;

void init_data(block ***a, block ***b, block ***c, block ***d, int n, int nb){

  int i, j, k, nbnb;

  nbnb = nb*nb;

  *a = (block**)malloc(n*sizeof(block*));
  *b = (block**)malloc(n*sizeof(block*));
  *c = (block**)malloc(n*sizeof(block*));
  *d = (block**)malloc(n*sizeof(block*));

  for(i=0; i<n; i++){
    (*a)[i] = (block*)malloc(n*sizeof(block));
    (*b)[i] = (block*)malloc(n*sizeof(block));
    (*c)[i] = (block*)malloc(n*sizeof(block));
    (*d)[i] = (block*)malloc(n*sizeof(block));
    for(j=0; j<n; j++){
      (*a)[i][j].b = (double*)malloc(nbnb*sizeof(double));
      (*b)[i][j].b = (double*)malloc(nbnb*sizeof(double));
      (*c)[i][j].b = (double*)malloc(nbnb*sizeof(double));
      (*d)[i][j].b = (double*)malloc(nbnb*sizeof(double));
      dlarnv_(&IONE, ISEED, &nbnb, (*a)[i][j].b);
      dlarnv_(&IONE, ISEED, &nbnb, (*b)[i][j].b);
      dlarnv_(&IONE, ISEED, &nbnb, (*c)[i][j].b);
      /* for(k=0; k<nbnb; k++){ */
        /* (a[i][j].b)[k] = (double)rand() / RAND_MAX; */
        /* (b[i][j].b)[k] = (double)rand() / RAND_MAX; */
        /* (c[i][j].b)[k] = (double)rand() / RAND_MAX; */
      /* } */
      memcpy( (*d)[i][j].b, (*c)[i][j].b, nbnb*sizeof(double) ); 
    }
  }


}


void block_mult(block a, block b, block c, int nb){

  dgemm_(&NoTran, &NoTran, &nb, &nb, &nb,
         &alpha,
         a.b,  &nb,
         b.b,  &nb,
         &beta,
         c.b,  &nb);
    
}


void compare_matrices(block **c, block **d, int n, int nb){
  
  int i, j, k, nbnb;
  double mx;
  nbnb = nb*nb;

  mx = 0.0;
  for(i=0; i<n; i++){
    for(j=0; j<n; j++){
      for(k=0; k<nbnb; k++){
        if(abs((c[i][j].b)[k]-(d[i][j].b)[k]) > mx)
          mx = abs((c[i][j].b)[k]-(d[i][j].b)[k]);
      }
    }
  }

  printf("The maximum difference on coefficients is %e\n",mx);

}



long usecs (){
  struct timeval t;

  gettimeofday(&t,NULL);
  return t.tv_sec*1000000+t.tv_usec;
}
