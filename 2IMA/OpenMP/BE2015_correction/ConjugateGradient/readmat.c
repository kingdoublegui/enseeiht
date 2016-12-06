#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <string.h>

int readHB_info(const char* filename, int* M, int* N, int* nz, char** Type, 
                int* Nrhs);
int readHB_newmat_double(const char* filename, int* M, int* N, int* nonzeros, 
                         int** colptr, int** rowind, double** val);

void readmat(const char *fname, int *M, int *N, int **rowptr, int **colind, double **val){

  int nonzeros, Nrhs;
  int *colptr, *rowind;
  double *tmp;
  double *rhs;
  int rhsentries;
  double *guess;
  double *exact;
  char *Type;
  char Ptrfmt[]="(13I6)";
  char Indfmt[]="(16I5)";
  char Valfmt[]="(3E26.18)";
  char Rhsfmt[]="(3E26.18)";
  int i, j, cnt, r, c;



  /* Get information about the array stored in the file specified in the  */
  /* argument list:                                                       */
  /* fprintf(stderr,"Reading matrix info from %s...\n",fname); */
  readHB_info(fname, M, N, &nonzeros, &Type, &Nrhs);

  /* fprintf(stderr,"Matrix in file %s is %d x %d, with %d nonzeros with type %s;\n", */
          /* fname, *M, *N, nonzeros, Type); */
  /* fprintf(stderr,"%d right-hand-side(s) available.\n",Nrhs); */

  /* Read the matrix information, generating the associated storage arrays  */ 
  /* fprintf(stderr,"Reading the matrix from %s...\n",fname); */
  readHB_newmat_double(fname, M, N, &nonzeros, &colptr, &rowind, &tmp);

  *rowptr = (int*)malloc((*M+1)*sizeof(int));

  memset(*rowptr, 0, (*M+1)*sizeof(int));

  cnt = 0;
  for(i=0; i<*M; i++){
    for(j=colptr[i]-1; j<colptr[i+1]-1; j++){
      c = i;
      r = rowind[j]-1;
      if(r<*M-1)
        (*rowptr)[r+2]+=1;
      cnt += 1;
      if(r!=c && c<*M-1){
        (*rowptr)[c+2]+=1;
        cnt += 1;
      }       

    }
  }
   

  for(i=1; i<=*M; i++)
    (*rowptr)[i] += (*rowptr)[i-1];



  *val = (double*)malloc(cnt*sizeof(double));
  *colind = (int*)malloc(cnt*sizeof(int));


  for(i=0; i<*M; i++){
    for(j=colptr[i]-1; j<colptr[i+1]-1; j++){
      c = i;
      r = rowind[j]-1;
      (*colind)[(*rowptr)[r+1]] = c;
      (*val)[(*rowptr)[r+1]] = tmp[j];
      (*rowptr)[r+1]+=1;

      if(r!=c){
        (*colind)[(*rowptr)[c+1]] = r;
        (*val)[(*rowptr)[c+1]] = tmp[j];
        (*rowptr)[c+1]+=1;
      }
    }
  }   


  free(tmp);
  free(rowind);
  free(colptr);

  return;

}

long usecs (){
  struct timeval t;

  gettimeofday(&t,NULL);
  return t.tv_sec*1000000+t.tv_usec;
}

