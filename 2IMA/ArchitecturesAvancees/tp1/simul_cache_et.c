#include <stdio.h>
#include <limits.h>
#include <stdlib.h>

/* Taille du cache associatif par ensemble */
#define N_LIGNE 64		/* Nombre de lignes (ensembles) */
#define N_BLOC_LIGNE 8	        /* Nombre d'emplacements (blocs) par ligne    */
#define TAILLE_BLOC 64		/* Nombre d'octets par bloc     */

/* Declaration du cache */

/* un emplacement du cache */
typedef struct{
  unsigned int tag;  /* le numero du bloc */
  char valid;        /* validite du bloc : non valide == 0 */
  unsigned int remp; /* indicateur d'utilisation du bloc */
} emplacement;

/* un ensemble du cache */
typedef struct{
  emplacement ens[N_BLOC_LIGNE];
} ensemble;

/* le cache (VARIABLE GLOBALE) */
ensemble le_cache[N_LIGNE];

/* compteur incremente a chaque reference au cache (VARIABLE GLOBALE) */
unsigned int comp_temps;

/**********************************************************************/
/* prototypes des foncions et procedures utilisees par le main        */
/**********************************************************************/

/**********************************************************************/
/* remet le cache a vide                                              */
/**********************************************************************/
void vider_cache();

/**********************************************************************/
/* simule la lecture d'une donnee referencee par son adresse "adresse" 
 * dans le cache avec une strategie de remplacement definie par
 * la procedure d'initialisation "init" 
 * et procedure de mise a jour "maj"
 * Retourne 1 si la donnee est presente, 0 sinon                      */
/**********************************************************************/
int cache(unsigned int adresse,
          void (*init)(unsigned int, unsigned int), 
          void (*maj)(unsigned int, unsigned int));

/**********************************************************************/
/* prototypes des procedures de politique de cache                    */
/**********************************************************************/

/**********************************************************************/
/* met a jour le champ remplacement du bloc avec la strategie lru 
 * (bloc se trouvant deja dans le cache)                              */
/**********************************************************************/
void maj_lru(unsigned int num_ligne, unsigned int num_bloc); 

/**********************************************************************/
/* initialise le champ remplacement d'un nouveau bloc 
 * avec la strategie  lru                                             */
/**********************************************************************/
void init_lru(unsigned int num_ligne, unsigned int num_bloc);

/**********************************************************************/
/* met a jour le champ remplacement du bloc avec la strategie lfu
 * (bloc se trouvant deja dans le cache)                              */
/**********************************************************************/
void maj_lfu(unsigned int num_ligne, unsigned int num_bloc); 

/**********************************************************************/
/* initialise le champ remplacement d'un nouveau bloc 
 * avec la strategie lfu                                              */
/**********************************************************************/
void init_lfu(unsigned int num_ligne, unsigned int num_bloc);


/**********************************************************************/
/* Main                                                               */
/**********************************************************************/
int main(int argc, char* argv[]) {

/**********************/
unsigned int TM = 512;  		/* matrice TMxTM           */
unsigned int TELEM = 4;  		/* Nb octets par element   */
unsigned int ADRA = 0;		/* Adresse de base de matA */
unsigned int ADRB;			/* Adresse de base de MatB */
/*********************/
  int i, j, k, hit;
  unsigned int adrc, NhitA, NhitB;
  float p;

/*********************/
  if (argc>1) {
    TM = atoi(argv[1]);
  }

  ADRB = ADRA+TM*TM*TELEM;	
/*********************/

  NhitA = 0;			/* nombre de hit pour matA */
  NhitB = 0;			/* nombre de hit pour matB */

  vider_cache();

  /* boucles de multiplication de 2 matrices
     La lecture des elements des matrices est simulee
     par un appel a la fonction cache */

  ....

  /* Afficher les nombres de hit pour A et B,
     ainsi que les pourcentages respectifs */

  printf("\nPolitique LRU\n");
  p = ((float) NhitA * 100)/(TM*TM*TM);
  printf("nombre hits A : %d - pourcentage : %f\n", NhitA, p);
  p = ((float) NhitB * 100)/(TM*TM*TM);
  printf("nombre hits B : %d - pourcentage : %f\n", NhitB, p);
  printf("nombre d'acces au cache : %d\n", comp_temps);

  return 0;
}

/**********************************************************************/
/* Implementation des fonctions et procedures                         */
/**********************************************************************/

/**********************************************************************/
/* remet le cache a vide                                              */
/**********************************************************************/
void vider_cache() {

  int i,j;

  for(i = 0; i < N_LIGNE; i++){
    for(j = 0; j < N_BLOC_LIGNE; j++){
     /* on indique que le bloc est non valide (0)
      * et on met a zero le champ qui indique son utilisation */
      le_cache[i].ens[j].valid = 0;
      le_cache[i].ens[j].remp = 0;
    }
  }
  return;
}

/**********************************************************************/
/* 3 fonctions utiles a l'algo de recherche dans le cache             */
/**********************************************************************/

/**********************************************************************/
/* verifie si la donnee reference par "tag" se trouve dans l'ensemble
 * (ou ligne) numero "num_ligne"
 * Retourne le numero du bloc si trouve, -1 sinon                     */
/**********************************************************************/
int appartient(unsigned int num_ligne, unsigned int tag){
  unsigned int i;

  i = 0;
  /* on cherche dans l'ensemble de taille N_BLOC_LIGNE
   * le bloc valide de tag "tag" 
   * echec : i == N_BLOC_LIGNE
   * succes : le_cache[num_ligne].ens[i].valid != 0 et 
   *          le_cache[num_ligne].ens[i].tag == tag 
   * condition de la boucle : non (echec et succes) */
  while(i < N_BLOC_LIGNE && 
       (le_cache[num_ligne].ens[i].valid == 0 || 
        le_cache[num_ligne].ens[i].tag != tag)) {
    i++;
  }
  if(i == N_BLOC_LIGNE) {
    /* on n'a pas trouve le bloc */
    return -1;
  } else {
    /* on l'a trouve dans la case i */
    return i;
  }
}

/**********************************************************************/
/* verifie s'il existe un emplacement libre dans l'ensemble 
 * (ou ligne) numero "num_ligne"
 * Retourne le numero de l'emplacement si trouve, -1 sinon            */
/**********************************************************************/
int un_emplacement_libre(unsigned int num_ligne){
  int i;

  i = 0;
  /* on cherche dans l'ensemble de taille N_BLOC_LIGNE
   * le bloc libre == non valide
   * echec : i == N_BLOC_LIGNE
   * succes : le_cache[num_ligne].ens[i].valid == 0
   * condition de la boucle : non (echec et succes) */
  while(i < N_BLOC_LIGNE && le_cache[num_ligne].ens[i].valid != 0){ 
    i++;
  }
  if(i == N_BLOC_LIGNE) {
    /* on n'a pas trouve d'emplacement libre */
    return -1;
  } else {
    /* on l'a trouve dans la case i */
    return i;
  }
}

/**********************************************************************/
/* Selectionne l'emplacement du bloc a remplacer quand l'ensemble est
 * plein
 * Retourne le numero de l'emplacement selectionne                    */
/**********************************************************************/
unsigned int select_emplacement(unsigned int num_ligne){
  int i;
  int remp;
  int iremp;

  /* initialisation avec l'emplacement 0 */
  remp = le_cache[num_ligne].ens[0].remp;
  iremp = 0;

  /* parcours complet de l'ensemble */
  for(i = 1; i < N_BLOC_LIGNE; i++) {
    if( le_cache[num_ligne].ens[i].remp < remp){
      remp =  le_cache[num_ligne].ens[i].remp;
      iremp = i;
    }
  }
  /* iremp contient l'indice de l'emplacement du bloc a remplacer */

  return iremp;
}

/**********************************************************************/
/* simule la lecture d'une donnee referencee par son adresse "adresse" 
 * dans le cache avec une strategie de remplacement definie par
 * la procedure d'initialisation "init" 
 * et procedure de mise a jour "maj"
 * Retourne 1 si la donnee est presente, 0 sinon                      */
/**********************************************************************/
int cache(unsigned int adresse, void (*init)(unsigned int, unsigned int), 
                            void (*maj)(unsigned int, unsigned int)){

  unsigned int adr_bloc;
  unsigned int num_ligne;
  unsigned int tag_adr;
  unsigned int bon;
  unsigned int libre;
  unsigned int vieux;
  int retour;

  /* adresse du bloc en memoire contenant la donnee */
  adr_bloc = adresse / TAILLE_BLOC;
  /* ensemble (ligne) du cache ou est susceptible de se trouver le bloc */
  num_ligne = adr_bloc % N_LIGNE;
  /* adresse du bloc dans l'ensemble */
  tag_adr = adr_bloc / N_LIGNE;

  /* le bloc est-il dans le cache ? */
  bon = appartient(num_ligne, tag_adr);

  if(bon != -1){
    /* oui, on met a jour son utilisation */
    maj(num_ligne, bon);
    retour = 1;
  } else {
    /* un emplacement de cette ligne est-il libre */
    libre = un_emplacement_libre(num_ligne);

    if(libre != -1) {
      /* oui, on "met" le bloc dedans */
      le_cache[num_ligne].ens[libre].tag = tag_adr;
      le_cache[num_ligne].ens[libre].valid = 1;
      init(num_ligne, libre);
    } else {
      /* non, on trouve quel emplacement contient le bloc a remplacer */
      vieux = select_emplacement(num_ligne);

      /* on "met" le bloc dedans */
      le_cache[num_ligne].ens[vieux].tag = tag_adr;
      le_cache[num_ligne].ens[vieux].valid = 1;
      init(num_ligne, vieux);
    }
    retour = 0;
  }
  return retour;
}


/* politiques de cache : initialisation et maj */
void maj_lru(unsigned int num_ligne, unsigned int numb_bloc) {

  if (comp_temps < UINT_MAX-1) {
    comp_temps++;
  }
  le_cache[num_ligne].ens[numb_bloc].remp = comp_temps;
}

void init_lru(unsigned int num_ligne, unsigned int numb_bloc) {
  if (comp_temps < UINT_MAX-1) {
    comp_temps++;
  }
  le_cache[num_ligne].ens[numb_bloc].remp = comp_temps;
}

void maj_lfu(unsigned int num_ligne, unsigned int numb_bloc) {
  if (comp_temps < UINT_MAX-1) {
    comp_temps++;
  }
  if (le_cache[num_ligne].ens[numb_bloc].remp < UINT_MAX-1) {
    le_cache[num_ligne].ens[numb_bloc].remp++;
  }
}

void init_lfu(unsigned int num_ligne, unsigned int numb_bloc) {
  if (comp_temps < UINT_MAX-1) {
    comp_temps++;
  }
  le_cache[num_ligne].ens[numb_bloc].remp = 1;
}
