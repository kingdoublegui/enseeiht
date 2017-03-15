---------------- MODULE allocateur1 ----------------
\* Time-stamp: <30 mar 2016 10:24 queinnec@enseeiht.fr>

(* Version concrète de l'allocation de ressources. *)

EXTENDS Naturals

CONSTANT
   M,    \* nbre de ressources
   N     \* nbre de processus

VARIABLES
  nbDispo,
  demande,
  satisfait

vars == << nbDispo, demande, satisfait >>

TypeInvariant ==
  [] (/\ nbDispo \in 0..M
      /\ demande \in [ 0..N-1 -> 0..M ]
      /\ satisfait \in [ 0..N-1 -> BOOLEAN ])

(* Calcule la somme des demandes pour les processus contenus dans S *)
SommeDemande[S \in SUBSET (0..N-1) ] ==
  IF S = {} THEN 0
  ELSE LET e == CHOOSE x \in S : TRUE
       IN demande[e] + SommeDemande[S \ {e}]

(* XXXX Autres propriétés temporelles de sûreté ??? *)

(* XXXX Autres propriétés temporelles de vivacité ??? *)

----------------------------------------------------------------

Init ==
   /\ nbDispo = M
   /\ demande = [ i \in 0..N-1 |-> 0 ]
   /\ satisfait = [ i \in 0..N-1 |-> FALSE ]

demander(i,p) == /\ TRUE \* TODO

obtenir(i) == /\ TRUE  \* TODO

liberer(i,q) == /\ TRUE \* TODO

Next ==
 \E i \in 0..N-1 : \E p \in 1..M :
    \/ demander(i,p)
    \/ obtenir(i)
    \/ liberer(i,p)

Fairness == TRUE \* TODO

Spec ==
 /\ Init
 /\ [] [ Next ]_vars
 /\ Fairness

================================================================
