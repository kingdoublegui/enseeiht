#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>
#include <math.h>
#include "aux.h"

unsigned long process_node(struct node *node){
  mysleep(0.00005);
  return node->val+1;
}



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



void init_list(struct node **head){

  struct node *curr;
  int n, i;

  n = 10000;
  
  *head = (struct node*)malloc(sizeof(struct node));
  curr = *head;
  
  for(i=0; i<n-1; i++){
    curr->val = i+1;
    curr->next = (struct node*)malloc(sizeof(struct node));
    curr = curr->next;
  }
  curr->val=1;
  curr->next = NULL;

  return;

}
