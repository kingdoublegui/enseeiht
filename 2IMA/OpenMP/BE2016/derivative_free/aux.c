#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>



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

double evaluate(double x, double y){
  /* mysleep(0.000001); */
  return 1.34*x*x+1.15*y*y+2 + x*y -y-2*x+0.33;
}

