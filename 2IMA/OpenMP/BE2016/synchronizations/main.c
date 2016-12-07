#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "omp.h"
#include "aux.h"
#define NIT 1000000
#define NE  10000

void sequential(int *data);
void parallel_critical(int *data);
void parallel_atomic(int *data);
void parallel_locks(int *data);

int main(int argc, char **argv){
  int    n, i, j, k, nth, thn, cnt;
  long   t_start, t_end, save;
  double s, z, x, y, nx, ny, nz, mz;
  int    data[NE];
  
  /* Initialize data */
  for(i=0; i<NE; i++)
    data[i]=0;
  
  t_start = usecs();
  sequential(data);
  t_end = usecs();
  
  for(cnt=0, i=0; i<NE; i++){
    cnt+=data[i];
  }
  printf("Sequential   time : %8.2f msec.",((double)t_end-t_start)/1000.0);
  printf("       -- result: %4d\n",cnt);


  /***********************************************************************/
  /***********************************************************************/
  /***********************************************************************/


  for(i=0; i<NE; i++)
    data[i]=0;
  
  t_start = usecs();
  parallel_critical(data);
  t_end = usecs();

  for(cnt=0, i=0; i<NE; i++){
    cnt+=data[i];
  }
  printf("Critical     time : %8.2f msec.",((double)t_end-t_start)/1000.0);
  printf("       -- result: %4d\n",cnt);


  /***********************************************************************/
  /***********************************************************************/
  /***********************************************************************/


  for(i=0; i<NE; i++)
    data[i]=0;
  
  t_start = usecs();
  parallel_atomic(data);
  t_end = usecs();

  for(cnt=0, i=0; i<NE; i++){
    cnt+=data[i];
  }
  printf("Atomic       time : %8.2f msec.",((double)t_end-t_start)/1000.0);
  printf("       -- result: %4d\n",cnt);

  
  /***********************************************************************/
  /***********************************************************************/
  /***********************************************************************/


  for(i=0; i<NE; i++){
    data[i]=0;
  }
  
  t_start = usecs();
  parallel_locks(data);
  t_end = usecs();
  
  for(cnt=0, i=0; i<NE; i++){
    cnt+=data[i];
  }
  printf("Locks        time : %8.2f msec.",((double)t_end-t_start)/1000.0);
  printf("       -- result: %4d\n",cnt);
  
  
  return 0;
}


void sequential(int *data){
  int i, j;
  
  for(i=0; i<NIT; i++){
    j = rand() % NE;
    data[j] += func();
  }
}



void parallel_critical(int *data){
  int i, j;
  
  for(i=0; i<NIT; i++){
    j = rand() % NE;
    data[j] += func();
  }

}
  



void parallel_atomic(int *data){
  int i, j;
  
  for(i=0; i<NIT; i++){
    j = rand() % NE;
    data[j] += func();
  }
  
}


void parallel_locks(int *data){
  int i, j;
  
  for(i=0; i<NIT; i++){
    j = rand() % NE;
    data[j] += func();
  }

}
