 
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <cblas.h>
 
#define TM 2000

/////////////////////////////////////////////////////////
// Program main
/////////////////////////////////////////////////////////
 
int main(int argc, char** argv)
{
   register int i, j, msec;
   clock_t start, temps;
   float *MA, *MB, *MC;

// allocation memoire pour les matrices A, B, et C
   MA = (float *) malloc(TM*TM*sizeof(float));
   MB = (float *) malloc(TM*TM*sizeof(float));
   MC = (float *) malloc(TM*TM*sizeof(float));

   if(MA==NULL || MB==NULL || MC==NULL) {
	printf("Probleme d'allocation memoire\n");
	exit(1);
   }

// initialisation des matrices avec des valeurs permettant de vérifier le resultat

   for(i = 0; i < TM; i++){
      for(j = 0; j < TM; j++){
      	MA[i*TM+j] = 1.0;
      	MB[i*TM+j] = 1.0;
		if (i==j) {
			MA[i*TM+j]=(float) (i+1);
			MB[i*TM+j]=(float) (i+1);
		}
      }
   }

   start = clock();

/* ******* Multiplication blas ******* */

  /* 1 Storage order of matrix.
     2-3 (constant) Whether or not the A and B (respectively) matrices 
          should be transposed before multiplication.
     4	integer	The number of rows in the A matrix.
     5	integer	The number of columns in the B matrix.
     6	integer	The number of columns in the A matrix.
     7	double	A scalar to multiply by during computation.
     8	double *	The data array for the A matrix.
     9	integer	The major stride for the A matrix.
     10	double *	The data array for the B matrix.
     11	integer	The major stride for the B matrix.
     12	double	A scalar to multiply C by during the computation.
     13	double *	The data array for the C matrix (output).
     14	integer	The major stride for the C matrix. */

  cblas_sgemm(CblasRowMajor, CblasNoTrans, CblasNoTrans, TM, TM, TM,
              1.0, MA, TM, MB, TM, 0.0, MC, TM);

  
   temps = clock() - start;
   msec = temps * 1000 / CLOCKS_PER_SEC;
   printf("Temps multiplication %d secondes %d millisecondes\n", msec/1000, msec%1000);


// Verification du resultat de la multiplication

   for(i = 0; i < TM; i++){
      for(j = 0; j < TM; j++){
		if ((i==j) && (MC[i*TM+j] != (float)((i+1)*(i+1)+TM-1))) {
				printf("Erreur i: %d j: %d\n", i, j);
			}
		else if ((i!=j) && (MC[i*TM+j] != (float)(i+j+TM))) {
			printf("Erreur i: %d j: %d\n", i, j);
		}
      }
   }

   // liberation de l'espace memoire
   free(MA);
   free(MB);
   free(MC);
   return(0);
}
