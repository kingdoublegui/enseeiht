#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

void treeinit(int *n, int **left, int **right, int **data, int l);
void treetrav_seq(int n, int *data, int *left, int *right);
void treetrav_par(int n, int *data, int *left, int *right);

int process(int ldata, int rdata, int i);
long usecs ();
