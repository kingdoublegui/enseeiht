#include <string.h>
#include <stdio.h>

long t_zero;

#define MAXEVENTS 10000
#define MAXTHREADS 48



typedef struct event_struct{
  int type;
  long t_start, t_stop;
} Event;

Event events[MAXTHREADS][MAXEVENTS];
int nevents[MAXTHREADS];


void trace_init();
void trace_event_start(int type);
void trace_event_stop(int type);
void trace_dump(char *);

