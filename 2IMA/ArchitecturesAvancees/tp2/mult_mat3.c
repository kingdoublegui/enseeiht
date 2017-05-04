 
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

/* Taille du cache associatif par ensemble */
#define N_LIGNE 64      /* Nombre de lignes (ensembles) */
#define N_BLOC_LIGNE 8          /* Nombre d'emplacements (blocs) par ligne    */
#define TAILLE_BLOC 64      /* Nombre d'octets par bloc     */
#define TAILLE_CACHE N_LIGNE*N_BLOC_LIGNE*TAILLE_BLOC       /* Nombre d'octets par bloc     */
 
/////////////////////////////////////////////////////////
// Program main
/////////////////////////////////////////////////////////
 
int main(int argc, char** argv)
{
   register int i, j, k, ki, TM, msec;
   float cc;
   clock_t start, temps;
   float *MA, *MB, *MC;
    unsigned int TELEM = 4;       /* Nb octets par element   */

// TM saisie comme argument du main, sinon 1000 par defaut
   TM=1000;
   if (argc > 1) {
   	TM=atoi(argv[1]);
   }

// allocation memoire pour les matrices A, B, et C
   MA = (float *) malloc(TM*TM*sizeof(float));
   MB = (float *) malloc(TM*TM*sizeof(float));
   MC = (float *) malloc(TM*TM*sizeof(float));

// initialisation des matrices avec des valeurs permettant de vérifier le resultat


   for(i = 0; i < TM; i++){
      for(j = 0; j < TM; j++){
      	MA[i*TM+j] = 1.0;
      	MB[i*TM+j] = 1.0;
      	MC[i*TM+j] = 0.0;
		if (i==j) {
			MA[i*TM+j]=(float) (i+1);
			MB[i*TM+j]=(float) (i+1);
		}
      }
   }

   start = clock();

    int largeur = TAILLE_CACHE/(TM*TELEM);

    int pas = 8;
   // multiplication C = A * B
   for (k = 0; k < TM; k+=pas)
       for (i = 0; i < TM; i++)
        for (j = 0; j < TM; j++) {
            cc = MC[i*TM + j];
            for (ki = k; ki < k+pas; ki++)
          		cc  += MA[i * TM + ki] * MB[ki * TM + j];
        MC[i*TM + j] = cc;
        }
   temps = clock() - start;
   msec = temps * 1000 / CLOCKS_PER_SEC;
   printf("Temps multiplication %d secondes %d millisecondes\n", msec/1000, msec%1000);


// Verification des resultats
// Si les boucles de multiplication ne sont pas exécutées correctement, les erreurs sont détectées et affichées à l'écran.
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
