---------------- MODULE philosophes0_fourchettes ----------------
(* Philosophes. Version en utilisant l'état des voisins. *)

EXTENDS Naturals

CONSTANT N

Philos == 0..N-1

gauche(i) == (i+1)%N       \* philosophe à gauche du philo n°i
droite(i) == (i+N-1)%N     \* philosophe à droite du philo n°i

Hungry == "H"
Thinking == "T"
Eating == "E"

Gauche == "G"
Droite == "D"
Table == "T"

VARIABLES
    etat,         \* i -> Hungry,Thinking,Eating
    fourchettes

(* TODO : propriétés de philosophes0 (exclusion, vivacité) *)
TypeInvariant ==
    [] (/\ etat \in [ Philos -> {Hungry, Thinking, Eating} ]
        /\ fourchettes \in [ Philos -> {Gauche, Droite, Table} ])
    
ExclMutuelle ==
    [] (\A i \in Philos : etat[i] = Eating => (etat[gauche(i)] # Eating /\ etat[droite(i)] # Eating))

VivaciteIndividuelle ==
    \A i \in Philos : etat[i] = Hungry ~> etat[i] = Eating
    
VivaciteGlobale ==
    (\E i \in Philos : etat[i] = Hungry) ~> (\E j \in Philos : etat[j] = Eating)

----------------------------------------------------------------

Init ==
    /\ etat = [ i \in Philos |-> Thinking ]
    /\ fourchettes = [ i \in Philos |-> Table ]

prendre_fourchette_gauche(i) ==
    /\ fourchettes[i] = Table
    /\ fourchettes[gauche(i)] # Droite
    /\ fourchettes' = [fourchettes EXCEPT ![i] = Droite]
    /\ UNCHANGED etat
    
prendre_fourchette_droite(i) ==
    /\ fourchettes[droite(i)] # Droite
    /\ fourchettes' = [fourchettes EXCEPT ![droite(i)] = Gauche]
    /\ UNCHANGED etat
    
demande(i) ==
    /\ etat[i] = Thinking
    /\ etat' = [etat EXCEPT ![i] = Hungry]
    /\ UNCHANGED fourchettes

mange(i) ==
    /\ etat[i] = Hungry
    /\ fourchettes[i] = Droite
    /\ fourchettes[droite(i)] = Gauche
    /\ etat' = [etat EXCEPT ![i] = Eating]
    /\ UNCHANGED fourchettes

pense(i) ==
    /\ etat[i] = Eating
    /\ etat' = [etat EXCEPT ![i] = Thinking]
    /\ fourchettes  = [ fourchettes EXCEPT ![i] = Table, ![droite(i)] = Table ]

Next ==
  \E i \in Philos : \/ demande(i)
                    \/ mange(i)
                    \/ pense(i)
                    \/ prendre_fourchette_gauche(i)
                    \/ prendre_fourchette_droite(i)

Fairness == \A i \in Philos :
              /\ WF_<<etat, fourchettes>> (mange(i))
              /\ WF_<<etat, fourchettes>> (pense(i))

Spec ==
  /\ Init
  /\ [] [ Next ]_<<etat, fourchettes>>
  /\ Fairness

================================
