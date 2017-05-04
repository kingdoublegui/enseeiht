#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <limits.h>
#include <time.h>

#define TV 100 	//Taille du vecteur

int main(int argc, char *argv[]) {
  unsigned register int i, j, msec; 
  clock_t start, temps;
  float va[TV];
  float vb[TV];
  
  for (i = 0 ; i < TV ; i++){
    va[i]=(float) i;
    vb[i]=0.011;
  }
  start=clock();
  for(j=0; j<20000000; j++) {
    for (i = 0 ; i < TV ; i++){
      va[i]=va[i]+vb[i];
    }
  }
  for (i = 0 ; i < TV ; i+=10){
    printf(" %f ",va[i]);
  }
  printf("\n");
  temps= clock() - start;
  msec = temps *1000 / CLOCKS_PER_SEC;
  printf(" Temps d'execution %d secondes %d millisecondes \n", msec/1000, msec%1000);
  return 0;
}
