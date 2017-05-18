 
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <cuda_runtime.h>
 

// CUDA Kernel
__global__ void matrixMul( float* C, float* A, float* B, int TM)
{
   // calcul des coordonnees du thread
   //int i =  ;
   //int j =  ;
 
   // calcul de c[i][j]
 
   // ....

}
 
 
/////////////////////////////////////////////////////////
// Programme main
/////////////////////////////////////////////////////////
 
int main(int argc, char** argv)
{
	int i, j, GRID_SIZE_X, BLOCK_SIZE_X;
	int TM=1024;
	cudaError_t cerror;

	if (argc>1) {
		 TM=atoi(argv[1]);
	}
	BLOCK_SIZE_X = TM; 
	GRID_SIZE_X = TM;
 
   // definiton de la grille et des blocs
   //dim3 block ...;
   //dim3 grid ...;

   // allocation des matrices sur CPU
   float *h_A, *h_B, *h_C ;
   //.....
   
   // initialisation des matrices avec des valeurs permettant de verifier le resultat
   for(i = 0; i < TM; i++){
      for(j = 0; j < TM; j++){
      	h_A[i*TM+j] = 1.0;
      	h_B[i*TM+j] = 1.0;
		h_C[i*TM+j] = 0.0;

		if (i==j) {
			h_A[i*TM+j]=(float) (i+1);
			h_B[i*TM+j]=(float) (i+1);
		}
      }
   }

   // Pour mesurer le temps de calcul
   float tc;
   cudaEvent_t depart, arret;
   cudaEventCreate(&depart);
   cudaEventCreate(&arret);
   cudaEventRecord(depart,0);

   // allocation des matrices sur GPU
   float *d_A, *d_B, *d_C;
   //...
 
   // copie des matrives A et B depuis le CPU vers le GPU
   //.....

   // lancement des threads
   matrixMul<<< grid, block >>>(d_C, d_A, d_B, TM);
 
   // Vérification en cas d'erreur
   cerror=cudaGetLastError(); 
   if ((int)cerror !=0) { 
     printf("Erreur appel kernel %d \n", (int) cerror);
     exit(cerror);
   }
   // copie de la matrive C depuis le GPU
   // ....

   // Mesure du temps : transfert + calcul
   cudaEventRecord(arret,0);
   cudaEventSynchronize(arret);
   cudaEventElapsedTime(&tc,depart, arret);

   cudaEventDestroy(depart);
   cudaEventDestroy(arret);
   printf("Temps consommé : %f seconde\n", tc/1000.0);

   // verification du resultat
  for(i = 0; i < TM; i++){
    for(j = 0; j < TM; j++){
	if ((i==j) && (h_C[i*TM+j] != (float)((i+1)*(i+1)+TM-1))) 	   	{
		printf("Erreur i: %d j: %d %f\n", i, j, h_C[i*TM+j] ); exit(1);
		}
		else if ((i!=j) && (h_C[i*TM+j] != (float)(i+j+TM))) 			{
			printf("Erreur i: %d j: %d\n", i, j);
			exit(1);
		}
      }
   } 

   // liberation de la memoire
   free(h_A);
   free(h_B);
   free(h_C);
   cudaFree(d_A);
   cudaFree(d_B);
   cudaFree(d_C);
 
}
