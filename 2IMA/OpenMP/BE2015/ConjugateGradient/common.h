long usecs ();
void spmv(int m, int *rowptr, int *colind, double *val, double alpha, 
          double *x, double beta, double *y);
double norm2(int m, double *x);
double dot(int m, double *x, double *y);
void axpby(int m, double alpha, double *x, double beta, double *y);
void readmat(const char *fname, int *M, int *N, int **rowptr, int **colind, double **val);
