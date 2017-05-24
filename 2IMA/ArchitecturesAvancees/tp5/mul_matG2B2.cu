
#include <stdio.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include<cuda_runtime.h>

#define BLOCK_SIZE_X 32
#define BLOCK_SIZE_Y 32
#define TILE_SIZE BLOCK_SIZE_X
#define LOOP_DIVIDER 8

// CUDA Kernel
    __global__ void
matrixMul( float* C, float* A, float* B, int wA)
{

    // Coordonnees absolues du thread

    int i = blockIdx.y * blockDim.y+ threadIdx.y;
    int j = blockIdx.x * blockDim.x+ threadIdx.x;

    int tx = threadIdx.x;
    int ty = threadIdx.y;

    // chque thread calcule C[i][j]
    __shared__ float As[TILE_SIZE][TILE_SIZE];
    __shared__ float Bs[TILE_SIZE][TILE_SIZE];
    float Csub=0.0;
    for (int ke = 0; ke < wA; ke += TILE_SIZE) {
        As[ty][tx] = A[i * wA + (ke+tx)];
        Bs[ty][tx] = B[(ke+ty) * wA + j];

        __syncthreads();
        for (int k = 0; k < TILE_SIZE; k++) {
            Csub += As[ty][k] * Bs[k][tx];
        }
        __syncthreads();
    }
    C[i * wA + j] = Csub;
}

/////////////////////////////////////////////////////////
// Program main
/////////////////////////////////////////////////////////

int main(int argc, char** argv)
{
    int i, j;
    int TM=2048;
    // TM peut etre saisie comme argument de la commande
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

    // mesure du temps de transfert
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
