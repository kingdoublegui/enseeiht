#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <math.h>
#include "omp.h"
#define MAXTHREADS 16


long usecs ();
void prefix_inc_seq(int l, int *pref);
void prefix_inc_par(int l, int *pref);
void prefix_exc_seq(int l, int *pref);


int main(int argc, char **argv){
  int l, i;
  long    t_start, t_end, save;
  int *pref1, *pref2;

  // Command line argument: array length
  if ( argc == 2 ) {
    l = atoi(argv[1]);    /* the length of the pref */
  } else {
    printf("Usage:\n\n ./main n\n\nwhere n is the length of the array to be used.\n");
    return 1;
  }

  pref1 = (int*) malloc(l*sizeof(int));
  pref2 = (int*) malloc(l*sizeof(int));
  for(i=0; i<l; i++)
    pref1[i] = rand() % 10;
  memcpy(pref2, pref1, l*sizeof(int));


  /* Sequential INCLUSIVE prefix scan */
  t_start = usecs();
  prefix_inc_seq(l, pref1);
  t_end = usecs();
  printf("Time sequential: %8.2f msec.\n",((double)t_end-t_start)/1000.0);

#ifdef DEBUG
  printf("Sequential: ");
  for(i=0; i<l; i++)
    printf("%d ",pref1[i]);
  printf("\n");
#endif

  printf("\n");
  /* Parallel INCLUSIVE prefix scan */
  t_start = usecs();
  prefix_inc_par(l, pref2);
  t_end = usecs();
  printf("Time parallel  : %8.2f msec.\n",((double)t_end-t_start)/1000.0);


#ifdef DEBUG
  printf("Parallel  : ");
  for(i=0; i<l; i++)
    printf("%d ",pref2[i]);
  printf("\n");
#endif

  /* Check the result */
  for(i=0; i<l; i++)
    if(pref1[i] != pref2[i]){
      printf("\nError! %4d \n",i);
      return 1;
    }
  
  printf("\nThe result is correct.\n");

  return 0;

}


/* Sequential INCLUSIVE prefix scan routine 
   
   - l    : length of the pref array
   - pref : the array whose prefix scan has to be computed
*/
void prefix_inc_seq(int l, int *pref){

  
  int i;
  
  for(i=1; i<l; i++)
    pref[i] = pref[i]+pref[i-1];

  return;

}




/* Sequential EXCLUSIVE prefix scan routine 
   
   - l    : length of the pref array
   - pref : the array whose prefix scan has to be computed
*/
void prefix_exc_seq(int l, int *pref){

  int i;
  int c, p;
  
  p = pref[0];
  pref[0] = 0;
  for(i=1; i<l; i++){
    c = pref[i];
    pref[i] = pref[i-1]+p;
    p = c;
  }
  return;

}




/* Parallel INCLUSIVE prefix scan routine */
/* Write this routine using OpenMP to implement the parallel algorithm
   described in the subject */
void prefix_inc_par(int l, int *pref){

  
  /* Use the loc_last array to store the last coefficient of the
     locally computed prefix scan */
  int loc_last[MAXTHREADS];

/* ===================================================================== 
   Write the parallel inclusive prefix scan algorithm here 
   ===================================================================== */







  return;

}



long usecs (){
  struct timeval t;

  gettimeofday(&t,NULL);
  return t.tv_sec*1000000+t.tv_usec;
}
