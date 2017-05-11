/* Multiplication de matrices avec decoupage vertical de B sur l'indice j et execution dans differents thread 
*/

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

/* nombre de threads */
#define PASK 8 
#define DECI 4
#define DECJ 4
#define DECK 4
#define NB_TH DECI*DECJ 

/*au cas ou on abesoin de synchroniser l'acces memoire 
  static pthread_mutex_t mon_verrou;
  pthread_mutex_lock(&mon_verrou);
  pthread_mutex_unlock(&mon_verrou);
  */

/* pour que les matrices soient visibles par les threads */
float *MA, *MB, *MC;  
int TM;

/* ------------------------------------------------------Fonction pour les thread 
   -------------------------------------------------------*/
void * thread_fonc(void * p_data)
{
    int i, j, i1, i2, j1, j2, k1, k2, k, ke, largeuri, largeurj, largeurk;
    float cc;
    int* data = (int*) p_data;
    i1 = *data;  
    i2 = *(data+1);
    j1 = *(data+2);  
    j2 = *(data+3);
    k1 = *(data+4);  
    k2 = *(data+5);

    largeuri = i2-i1;
    largeurj = j2-j1;
    largeurk = k2-k1;
    float* CL = malloc(largeuri*largeurj*sizeof(float));

    for (i = 0; i < largeuri; i++)
        for (j = 0; j < largeurj; j++)
            CL[i*largeurj+j] = 0;

    for (ke = k1; ke < k2 ; ke+=PASK){ 
        for (i = i1 ; i < i2 ; i++){ 
            for (j = j1 ; j < j2 ; j++){
                cc=CL[(i-i1)*largeurj+j-j1];
                for (k = ke ; k < ke+PASK ; k++){
                    cc +=MA[i*TM+k]*MB[k*TM+j];
                }
                CL[(i-i1)*largeurj+j-j1] =cc;

            }
        }
    }

    for (i = 0; i < largeuri; i++)
        for (j = 0; j < largeurj; j++)
            MC[(i+i1)*TM+j+j1] = CL[i*largeurj+j];
    pthread_exit(NULL);
}

/*-------------------------------------------------------
  Program main
  --------------------------------------------------------*/

int main(int argc, char* argv[])
{
    register int i, j, msec, ret,nth;

    /* Structure stockant les id des threads */
    pthread_t thread_id[NB_TH];

    /*tableau des parametres des threads*/
    int ta[NB_TH][6]; 

    /* TM saisie comme argument du main, sinon 1000 par defaut */
    TM=1000;
    if (argc > 1) {
        TM=atoi(argv[1]);
    }
    /* Verification de la faisabilite du decoupage */
    if ((TM % NB_TH)!=0) {
        printf("TM doit etre multiple de %d\n",NB_TH);
        exit(1);
    }

    /* allocation memoire pour les matrices A, B, et C */
    MA = (float *) malloc(TM*TM*sizeof(float));
    MB = (float *) malloc(TM*TM*sizeof(float));
    MC = (float *) malloc(TM*TM*sizeof(float));

    /* initialisation des matrices avec des valeurs permettant de verifier le resultat */
    for(i = 0; i < TM; i++){
        for(j = 0; j < TM; j++){
            MA[i*TM+j] = 1.0;
            MB[i*TM+j] = 1.0;
            MC[i*TM+j]=0.0;
            if (i==j) {
                MA[i*TM+j]=(float) (i+1);
                MB[i*TM+j]=(float) (i+1);
            }
        }
    }
    /* Creation des threads */
    printf ("Creation des threads  !\n");
    for (i=0; i < DECI; i++) {
        for (j=0; j < DECJ; j++) {
            nth = i*DECJ+j;
            ta[nth][0] = i * TM / DECI;
            ta[nth][1] = (i+1) * TM / DECI;
            ta[nth][2] = j * TM / DECJ;
            ta[nth][3] = (j+1) * TM / DECJ;
            ta[nth][4] = 0;
            ta[nth][5] = TM;
            ret = pthread_create (&thread_id[nth], NULL, thread_fonc, (void *) ta[nth]);
            if (ret) {
                perror ("thread_create");
                exit(2);
            }
        }
    } 

    /* Attente de la fin des threads. */
    printf ("Attente de la fin des threads  !\n");
    for (i=0; i<NB_TH; i++) {
        pthread_join (thread_id[i], NULL);
    }

    /* Verification des resultats */
    for(i = 0; i < TM; i++){
        for(j = 0; j < TM; j++){
            if ((i==j) && (MC[i*TM+j] != (float)((i+1)*(i+1)+TM-1))) {
                printf("Erreur i: %d j: %d\n", i, j);
                exit(1);
            }
            else if ((i!=j) && (MC[i*TM+j] != (float)(i+j+TM))) {
                printf("Erreur i: %d j: %d\n", i, j);
                exit(1);
            }
        }
    } 

    /* liberation de l'espace memoire */
    free(MA);
    free(MB);
    free(MC);

    return EXIT_SUCCESS;
}
