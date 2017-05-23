 
#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include<cuda_runtime.h>

//une tuile carre
#define BLOCK_SIZE_X 32
#define BLOCK_SIZE_Y BLOCK_SIZE_X 
#define TILE_SIZE BLOCK_SIZE_X 

// CUDA Kernel
__global__ void
matrixMul( float* C, float* A, float* B, int wA)
{
 
   // Coordonnees du thread
   // Block index
    int bx = blockIdx.x;
    int by = blockIdx.y;
 
    // Thread index
    int tx = threadIdx.x;
    int ty = threadIdx.y;
 
    // indice de la premiere tuile de A traitee par le bloc
    int a1 = wA * BLOCK_SIZE_Y * by;
 
    // indice de la derniere tuile de A traitee par le bloc
    int a2   = a1 + wA - TILE_SIZE;
 
    // pas d'adresse entre tuiles de la matrice A
    int apas  = TILE_SIZE;
 
    // indice de la premiere tuile de B traitee par le bloc
    int b1 = BLOCK_SIZE_X * bx;
 
    // pas d'adresse entre tuiles de la matrice B
    int bpas  = TILE_SIZE * wA;

    float Csub=0.0;

    // boucle sur les tuiles
    for (int a = a1, b = b1;
             a <= a2;
             a += apas, b += bpas) 
    {

        // Declaration en memoire partagee de la matrice devant contenir une tuie de A
        __shared__ float As[BLOCK_SIZE_Y][TILE_SIZE];
 
        // Declaration en memoire partagee de la matrice devant contenir une tuie de A
        __shared__ float Bs[TILE_SIZE][BLOCK_SIZE_X];
 
        // Copie des tuiles depuis la memoire globale vers la memoire partagee
        // chaque thread copie un element de chaque matrice
	  As[ty][tx] = A[a + wA * ty + tx];
	  Bs[ty][tx] = B[b + wA * ty + tx];

        // Synchronisation pour que les threads attendent la fin de la copie 
        __syncthreads();
 
        // multiplication entre tuiles 
        for ( int k = 0; k < TILE_SIZE; k +=1) {
            Csub += As[ty][k] * Bs[k][tx];
		}
 
         // Synchronisation pour que les threads attendent la fin du calcul
        __syncthreads();
 
    }
 
    // Enregistrement du resultat
    // indice de la ligne 
    C[a1 + wA * ty + b1 + tx] = Csub;
}
  
/////////////////////////////////////////////////////////
// Program main
/////////////////////////////////////////////////////////
 
int
main(int argc, char** argv)
{
	int i, j;
	int TM=2048;
	//BLOCK_SIZE_X =BLOCK_SIZE;
	//BLOCK_SIZE_Y = BLOCK_SIZE;
	if (argc>1) {
		 TM=atoi(argv[1]);
     }

	// Verification de la bonne taille par rapport aux dimensions des blocs
	if ((TM % BLOCK_SIZE_X) !=0) {
		printf("Taille matrice non multiple de taille bloc %d \n", BLOCK_SIZE_X);
		exit(1);
	}
	if ((TM % BLOCK_SIZE_Y) !=0) {
		printf("Taille matrice non multiple de taille bloc %d \n", BLOCK_SIZE_Y);
		exit(1);
	}
     // 1. Allocation memoire sur CPU
   unsigned int size_A = TM*TM;
   unsigned int mem_size_A = sizeof(float) * size_A;
   float* h_A = (float*) malloc(mem_size_A);
 
   unsigned int size_B = TM*TM;
   unsigned int mem_size_B = sizeof(float) * size_B;
   float* h_B = (float*) malloc(mem_size_B);
 
   unsigned int size_C = TM*TM;
   unsigned int mem_size_C = sizeof(float) * size_C;
   float* h_C = (float*) malloc(mem_size_C);

   // 2. initialisation des donnees CPU
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

 
   // 3. allocation memoire sur GPU
   float* d_A;
   float* d_B;
   float* d_C;
   cudaMalloc((void**) &d_A, mem_size_A);
   cudaMalloc((void**) &d_B, mem_size_B);
   cudaMalloc((void**) &d_C, mem_size_C);
 
   // 4. copie des donnes CPU vers GPU

   // mesure du temps 
   float  elapsedTime ;
   cudaEvent_t start , stop ;
   cudaEventCreate (&start ) ;
   cudaEventCreate (&stop ) ;
   cudaEventRecord ( start , 0 ) ;

   cudaMemcpy(d_A, h_A, mem_size_A, cudaMemcpyHostToDevice);
   cudaMemcpy(d_B, h_B, mem_size_B, cudaMemcpyHostToDevice);

   // 5. choix de la structure : grille et blocs
   dim3 threads(BLOCK_SIZE_X, BLOCK_SIZE_Y);
   dim3 grid(TM / threads.x, TM / threads.y);
 
   printf("bloc %d %d \n", BLOCK_SIZE_X, BLOCK_SIZE_Y);
   printf("grille %d %d \n", TM / threads.x, TM / threads.y);

   // 6. execution du kernel
   matrixMul<<< grid, threads >>>(d_C, d_A, d_B, TM);

   // 7. copie du resultat depuis le GPU
   cudaMemcpy(h_C, d_C, mem_size_C, cudaMemcpyDeviceToHost);

   cudaEventRecord ( stop , 0 ) ;
   cudaEventSynchronize ( stop ) ;
   cudaEventElapsedTime ( &elapsedTime , start , stop ) ;
   cudaEventDestroy ( start ) ;
   cudaEventDestroy ( stop ) ;
   printf ( "Temps consomme: %f secondes\n" , elapsedTime / 1000.0 ) ;
 
  // Verification des resultats

  for(i = 0; i < TM; i++){
    for(j = 0; j < TM; j++){
	if ((i==j) && (h_C[i*TM+j] != (float)((i+1)*(i+1)+TM-1))) 	   	{
		printf("Erreur i: %d j: %d %f\n", i, j, h_C[i*TM+j] ); 
		exit(1);
		}
		else if ((i!=j) && (h_C[i*TM+j] != (float)(i+j+TM))) 			{
			printf("Erreur i: %d j: %d\n", i, j);
			exit(1);
		}
      }
   } 

   // 8. liberation de la memoire
   free(h_A);
   free(h_B);
   free(h_C);
   cudaFree(d_A);
   cudaFree(d_B);
   cudaFree(d_C); 
}
