---------------- MODULE missionnairesEtCannibales ----------------
\* Time-stamp: <06 déc 2013 15:15 queinnec@enseeiht.fr>

(* Le problème de l'homme, du loup, du mouton et du chou *)

(* Version opératoire. *)

EXTENDS Naturals, Integers, TLC

VARIABLES
  canG, canD, misG, misD, barque

CANNIBALES == 3
MISSIONNAIRES == 3
Rives == {"G", "D"}
NombresCan == 0..CANNIBALES
NombresMis == 0..MISSIONNAIRES

TypeInvariant ==
  [] (/\ canG \in NombresCan
      /\ canD \in NombresCan
      /\ misG \in NombresMis
      /\ misD \in NombresMis
      /\ barque \in Rives
      /\ canG + canD = CANNIBALES
      /\ misG + misD = MISSIONNAIRES)

pasMiam ==
    /\ (misG > 0 => misG >= canG)
    /\ (misD > 0 => misD >= canD)

ToujoursOk == []pasMiam

Solution ==
  [] \neg(canG = 0 /\ misG = 0)

----------------------------------------------------------------

chge(r) == IF r = "G" THEN 1 ELSE -1

Init ==
  /\ canG = CANNIBALES
  /\ canD = 0
  /\ misG = MISSIONNAIRES
  /\ misD = 0
  /\ barque = "G"
  
bougerGD(ncan, nmis) ==
  /\ barque = "G"
  /\ ncan <= canG
  /\ nmis <= misG
  /\ canG' = canG-ncan
  /\ canD' = canD+ncan
  /\ misG' = misG-nmis
  /\ misD' = misD+nmis
  /\ barque' = "D"
  /\ pasMiam'

bougerDG(ncan, nmis) ==
  /\ barque = "D"
  /\ ncan <= canD
  /\ nmis <= misD
  /\ canG' = canG+ncan
  /\ canD' = canD-ncan
  /\ misG' = misG+nmis
  /\ misD' = misD-nmis
  /\ barque' = "G"
  /\ pasMiam'

jeterG ==
  /\ canG = 1
  /\ misG = MISSIONNAIRES
  /\ canG' = canG-1
  /\ UNCHANGED <<misG, canD, misD, barque>>
  
jeterD ==
  /\ canD = 1
  /\ misD = MISSIONNAIRES
  /\ canD' = canD-1
  /\ UNCHANGED <<canG, misG, misD, barque>>

Next == bougerGD(2,0) \/ bougerGD(1,1) \/ bougerGD(1,0) \/ bougerGD(0,1) \/bougerGD(0,2)
    \/ bougerDG(2,0) \/ bougerDG(1,0) \/ bougerDG(1,1) \/ bougerDG(0,1) \/ bougerDG(0,2)
    (* \/ jeterG \/ jeterD *)

Spec == Init /\ [] [ Next ]_<<canG,canD,misG,misD, barque>>

================================================================
