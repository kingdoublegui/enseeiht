struct {
  double *b;
} typedef block;


void init_data(block ***a, block ***b, block ***c, block ***d, int n, int nb);
long usecs ();
void block_mult(block a, block b, block c, int nb);
void compare_matrices(block **c, block **d, int n, int nb);

void dgemm_ (char *TRANSA, char *TRANSB,
             int *M, int *N, int *K,
             double *ALPHA,
             double *A, int *LDA,
             double *B, int *LDB,
             double *BETA,
             double *C, int *LDC);

void dlarnv_(int *idist, int *iseed, int *n, double *x);
