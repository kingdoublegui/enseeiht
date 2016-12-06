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

  
  int i, b, e, ll;
  int iam, nth;
  /* Use the loc_last array to store the last coefficient of the
     locally computed prefix scan */
  int loc_last[MAXTHREADS];



# pragma omp parallel private(iam, nth, ll, b, i)
  {
    /* CC: each thread gets its ID and the total number of threads in
       the parallel region */
    iam = omp_get_thread_num();
    nth = omp_get_num_threads();
    
  /* CC: ll is the length of the part of array handled by each
     thread */
    ll = (l-1)/nth+1;
    b = iam*ll;
    if(iam == nth-1)
      ll = l-b;

  /* CC: each thread computes the inclusive prefix scan on its part of
     array */
    prefix_inc_seq(ll, pref+b);

  /* CC: each thread stores the last element of its piece of prefix
     scan into the corresponding element of loc_last */
    loc_last[iam] = pref[b+ll-1];
    
  /* CC: a barrier is needed here to avoid threads from running too
     fast */
# pragma omp barrier

  /* CC: one single thread now does the exclusive prefix scan on the
     loc_last array */
# pragma omp master
    {
      prefix_exc_seq(nth, loc_last);
    }

  /* CC: a barrier is needed here to prevent threads from doing the
     instructions below before the exclusive prefix scan is done. Note
     that the barrier is not necessary if single is used instead of
     mater above because single has an implied barrier at the end. */
# pragma omp barrier

  /* CC: each thread updates its part of the array with the result of
     the exclusive prefix scan */
    for(i=b; i<b+ll; i++)
      pref[i] += loc_last[iam];
    

  }


  return;

}



long usecs (){
  struct timeval t;

  gettimeofday(&t,NULL);
  return t.tv_sec*1000000+t.tv_usec;
}
