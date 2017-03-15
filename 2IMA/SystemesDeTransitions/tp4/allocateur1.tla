---------------- MODULE allocateur1 ----------------
\* Time-stamp: <30 mar 2016 10:24 queinnec@enseeiht.fr>

(* Version concrète de l'allocation de ressources. *)

EXTENDS Naturals, Sequences

CONSTANT
   M,    \* nbre de ressources
   N     \* nbre de processus

VARIABLES
  nbDispo,
  demande,
  satisfait,
  attente

vars == << nbDispo, demande, satisfait, attente >>

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

MaxRessource ==
    SommeDemande[{i \in 0..N-1 : satisfait[i]}] + nbDispo = M

Surete ==
    /\ MaxRessource

(* XXXX Autres propriétés temporelles de vivacité ??? *)
VivaciteFaible ==
    (\E i \in 0..N-1 : demande[i] # 0) ~> (\E j \in 0..N-1 : satisfait[j])

VivaciteForte ==
    \A i \in 0..N-1 : demande[i] # 0 ~> satisfait[i]

----------------------------------------------------------------

Init ==
   /\ nbDispo = M
   /\ demande = [ i \in 0..N-1 |-> 0 ]
   /\ satisfait = [ i \in 0..N-1 |-> FALSE ]
   /\ attente = <<>>

demander(i,p) ==
    /\ demande[i] = 0
    /\ p <= M
    /\ demande' = [demande EXCEPT ![i] = p]
    /\ attente' = Append(attente, i)
    /\ UNCHANGED <<nbDispo, satisfait>>

obtenir(i) ==
    /\ Len(attente) > 0
    /\ i = Head(attente)
    /\ demande[i] > 0
    /\ demande[i] <= nbDispo
    /\ satisfait[i] = FALSE
    /\ attente' = Tail(attente)
    /\ nbDispo' = nbDispo - demande[i]
    /\ satisfait' = [satisfait EXCEPT ![i] = TRUE]
    /\ UNCHANGED demande

liberer(i,q) ==
    /\ demande[i] = q
    /\ satisfait[i]
    /\ demande' = [demande EXCEPT ![i] = 0]
    /\ nbDispo' = nbDispo + q
    /\ satisfait' = [satisfait EXCEPT ![i] = FALSE]
    /\ UNCHANGED attente

Next ==
 \E i \in 0..N-1 : \E p \in 1..M :
    \/ demander(i,p)
    \/ obtenir(i)
    \/ liberer(i,p)

Fairness == \A i \in 0..N-1 :
              \A p \in 1..M :
              /\ WF_vars (obtenir(i))
              /\ WF_vars (liberer(i, p))

Spec ==
 /\ Init
 /\ [] [ Next ]_vars
 /\ Fairness

================================================================
