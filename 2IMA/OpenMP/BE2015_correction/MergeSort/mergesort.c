#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/time.h>


long usecs ();
void merge(int a[],int temp[], int low, int high, int mid);
void mergesortrec(int a[],int temp[], int low, int high);

/* Parallelization concerns this routine and the next one */
void mergesort(int a[],int temp[], int low, int high){

#pragma omp parallel
  {
#pragma omp single
    {
      mergesortrec(a, temp,  low, high);
    }
  }

}


void mergesortrec(int a[],int temp[], int low, int high) {
 /* Only the coefficients from low to high of the a and temp arrays
    are modified inside this routine */

  int mid;
  
  /* CC: note that this is exactly the same as the tree traversal TP */
  if(low<high) {
    mid=(low+high)/2;             //find the midpoint
    
#pragma omp task if(mid-low > 1000)
    {
      mergesortrec(a,temp,low,mid);   //sort the first half
    }
#pragma omp task if(high-mid > 1000)
    {
      mergesortrec(a,temp,mid+1,high); //sort the second half
    }
    
#pragma omp taskwait
    merge(a,temp,low,high,mid);  //merge them together into one sorted list
  }
}



/* This routine has to remain unchanged */
void merge(int a[],int temp[], int low, int high, int mid){
  int i, j, k;
  i=low; j=mid+1; k=low;
  while((i<=mid)&&(j<=high)){
    if(a[i]<a[j]){
      temp[k]=a[i]; k++; i++;
    } else {
      temp[k]=a[j]; k++; j++;
    }
  }
  while(i<=mid){
    temp[k]=a[i]; k++; i++;
  }
  while(j<=high){
    temp[k]=a[j]; k++; j++;
  }
  for(i=low;i<k;i++) {
    a[i]=temp[i];
  }
}


int main(int argc, char **argv) {
  int LEN;
  long   t_start, t_end;
  int i, *x,*temp;

  // Command line argument: array length
  if ( argc == 2 ) {
    LEN  = atoi(argv[1]);  
  } else {
    printf("Usage:\n\n ./main n\n\nwhere n is the length of the array to be sorted.\n");
    return 1;
  }

  x=(int *)malloc(sizeof(int)*LEN);
  temp=(int *)malloc(sizeof(int)*LEN);
  if(x==NULL || temp == NULL){
    printf("Out of memory"); exit(0);
  }
 
  //Fill the array to be sorted with random numbers
  for (i = 0; i < LEN; i++) 
    x[i] = rand() % LEN;
 
#ifdef DEBUG
  printf("before sort:\n");
  for (i = 0; i < LEN; i++) printf("%d ", x[i]);
  printf("\n");
#endif
 
  t_start = usecs();
  mergesort(x,temp,0, (LEN-1));
  t_end = usecs();
 
 
#ifdef DEBUG
  printf("after sort:\n");
  for (i = 0; i < LEN; i++) printf("%d ", x[i]);
  printf("\n");
#endif
 
  /* Check the result */
  for(i=1; i<LEN; i++)
    if(x[i] < x[i-1]){
      printf("\nThe result is not correct\n");
      return 1;
    }

  printf("\nThe result is correct\nTime    : %8.2f msec.\n",((double)t_end-t_start)/1000.0);
  return 0;
}




long usecs (){
  struct timeval t;

  gettimeofday(&t,NULL);
  return t.tv_sec*1000000+t.tv_usec;
}
