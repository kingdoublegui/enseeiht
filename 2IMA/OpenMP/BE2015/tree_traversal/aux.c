#include "aux.h"

long usecs (){
  struct timeval t;

  gettimeofday(&t,NULL);
  return t.tv_sec*1000000+t.tv_usec;
}


void mysleep(double sec){

  long s, e;
  s=0; e=0;
  s = usecs();
  while(((double) e-s)/1000000 < sec)
    {
      e = usecs();
    }
  return;
}



void init(int *left, int *right, int l, int f, int *root){

  int i;

  *root = f+pow(2,l)-2;

  if(l==1)
    return;
  
  init(left, right, l-1, f,              left  + *root); 
  init(left, right, l-1, f+pow(2,l-1)-1, right + *root); 

}



void treeinit(int *n, int **left, int **right, int **data, int l){

  int root, i;

  *n = pow(2,l)-1;
  *data  = (int*)malloc((*n)*sizeof(int));
  *left  = (int*)malloc((*n)*sizeof(int));
  *right = (int*)malloc((*n)*sizeof(int));

  for(i=0; i<*n; i++){
    (*data)[i]  = 0;
    (*left)[i]  = -1;
    (*right)[i] = -1;
  }
  
  init(*left, *right, l, 0, &root);

}


int process(int ldata, int rdata, int i){

  mysleep(0.1);
  return ldata+rdata+i;

}
