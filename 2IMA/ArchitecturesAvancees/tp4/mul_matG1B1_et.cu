#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <cuda_runtime.h>


// CUDA Kernel
__global__ void matrixMul( float* C, float* A, float* B, int TM)
{
    // calcul des coordonnees du thread
    int i =  blockDim.y * blockIdx.y + threadIdx.y;
    int j =  blockDim.x * blockIdx.x + threadIdx.x;

    // calcul de c[i][j]
    float cs = 0;
    for (int k = 0; k < TM; k++) {
        cs += A[i*TM + k] * B[k*TM + j];
    }
    C[i*TM + j] = cs;

}


/////////////////////////////////////////////////////////
// Programme main
/////////////////////////////////////////////////////////

int main(int argc, char** argv)
{
    int i, j, GRID_SIZE_X, GRID_SIZE_Y, BLOCK_SIZE_X, BLOCK_SIZE_Y, BLOCK_DIM, DEC, TAILLE;
    int TM=2048;
    cudaError_t cerror;

    if (argc>1) {
        TM=atoi(argv[1]);
    }
    DEC = 1;
    BLOCK_DIM = 512;
    BLOCK_SIZE_X = BLOCK_DIM/DEC; 
    BLOCK_SIZE_Y = BLOCK_DIM/BLOCK_SIZE_X; 
    GRID_SIZE_X = TM/BLOCK_SIZE_X;
    GRID_SIZE_Y = TM;
    TAILLE = TM*TM * sizeof(float);

    // definiton de la grille et des blocs
    dim3 block(BLOCK_SIZE_X, BLOCK_SIZE_Y);
    dim3 grid(GRID_SIZE_X, GRID_SIZE_Y);

    // allocation des matrices sur CPU
    float *h_A, *h_B, *h_C ;
    h_A = (float*) malloc(TAILLE);
    h_B = (float*) malloc(TAILLE);
    h_C = (float*) malloc(TAILLE);

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
    cudaMalloc((void**) &d_A, TAILLE);
    cudaMalloc((void**) &d_B, TAILLE);
    cudaMalloc((void**) &d_C, TAILLE);

    // copie des matrives A et B depuis le CPU vers le GPU
    cudaMemcpy(d_A, h_A, TAILLE, cudaMemcpyHostToDevice);
    cudaMemcpy(d_B, h_B, TAILLE, cudaMemcpyHostToDevice);

    // lancement des threads
    matrixMul<<< grid, block >>>(d_C, d_A, d_B, TM);

    // Vérification en cas d'erreur
    cerror=cudaGetLastError(); 
    if ((int)cerror !=0) { 
        printf("Erreur appel kernel %d \n", (int) cerror);
        exit(cerror);
    }
    // copie de la matrive C depuis le GPU
    cudaMemcpy(h_C, d_C, TAILLE, cudaMemcpyDeviceToHost);

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
            if ((i==j) && (h_C[i*TM+j] != (float)((i+1)*(i+1)+TM-1))) {
                printf("Erreur i: %d j: %d %f\n", i, j, h_C[i*TM+j] );
                exit(1);
            } else if ((i!=j) && (h_C[i*TM+j] != (float)(i+j+TM))) {
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
    return 0; 
}
