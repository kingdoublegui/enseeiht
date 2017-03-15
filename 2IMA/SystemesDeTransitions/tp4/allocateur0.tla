---------------- MODULE allocateur0 ----------------
\* Time-stamp: <14 fév 2014 11:04 queinnec@enseeiht.fr>

(* Version abstraite de l'allocation de ressources. *)

EXTENDS Naturals

CONSTANT
   M    \* nbre de ressources

VARIABLES
  nbDispo

TypeInvariant == [] (nbDispo \in 0..M)

Init == nbDispo = M

allouer(p) == /\ p \in 0..nbDispo
              /\ nbDispo' = nbDispo - p

liberer(q) == /\ q \in 0..M-nbDispo
              /\ nbDispo' = nbDispo + q

Next ==
 \E i \in 1..M :
    \/ allouer(i)
    \/ liberer(i)

Spec ==
 /\ Init
 /\ [] [ Next ]_<<nbDispo>>

================================================================
