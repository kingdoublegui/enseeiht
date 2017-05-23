---------------- MODULE visiteur ----------------

EXTENDS Naturals, FiniteSets, TLC

CONSTANT
    NbSites

\* Pour pouvoir écrire les obligations de preuves du raffinement, et également pour pouvoir faire des importations de modules plus facilement,
\* toutes les définitions importantes (Inv, Init, ContratClient et ContratModule) sont paramétrées par des variables arbitraires.
\* Ainsi, on pourra quantifier sur ces variables pour prouver le raffinement ou faire de la composition d'actions et de modules.
\* Dans les actions exécutées par ce module, ces paramètres seront associés naturellement aux variables d'états.

----------------------------------------------------------------

\* INVARIANT

\* ensemble de choix de procédures possibles
CHOIX == { "move", "has_visited", "has_finished", "reset" }

NO_DATA == "<NO_DATA>"

Sites == 1..NbSites

ETAT == SUBSET Sites

TypeEtat(etat) ==
 /\ etat \in ETAT

\* version paramétrée
Inv(etat) ==
 /\ TypeEtat(etat)

----------------------------------------------------------------

\* ETAT INITIAL

\* version paramétrée
Init(etat) ==
 /\ etat = {}

----------------------------------------------------------------

\* Choix = "move"
\* PROCEDURE move : version paramétrée

Pre_move(param, etat) ==
 /\ param = NO_DATA
 /\ Cardinality(etat) < NbSites

Act_move(param, etat, etat_p, result) ==
 /\ \E s \in Sites : 
    /\ s \notin etat
    /\ etat_p = etat \union {s}
    /\ result = s

----------------------------------------------------------------

\* Choix = "has_visited"
\* PROCEDURE has_visited : version paramétrée

Pre_has_visited(param, etat) ==
 /\ param \in Sites

Act_has_visited(param, etat, etat_p, result) ==
 /\ result = (param \in etat)
 /\ etat_p = etat

----------------------------------------------------------------

\* Choix = "has_finished"
\* PROCEDURE has_finished : version paramétrée

Pre_has_finished(param, etat) ==
 /\ param = NO_DATA

Act_has_finished(param, etat, etat_p, result) ==
 /\ result = (etat = Sites)
 /\ etat_p = etat

----------------------------------------------------------------

\* Choix = "reset"
\* PROCEDURE reset : version paramétrée

Pre_reset(param, etat) ==
 /\ param = NO_DATA

Act_reset(param, etat, etat_p, result) ==
 /\ result = NO_DATA
 /\ etat_p = {}

----------------------------------------------------------------

\* CONTRAT CLIENT

\* contrat paramétré
ContratClient(choix, param, etat) ==
 \/ (choix = "move"         /\ Pre_move(param, etat))
 \/ (choix = "has_visited"  /\ Pre_has_visited(param, etat))
 \/ (choix = "has_finished" /\ Pre_has_finished(param, etat))
 \/ (choix = "reset"        /\ Pre_reset(param, etat))

----------------------------------------------------------------

\* CONTRAT MODULE

\* contrat paramétré
ContratModule(choix, param, etat, etat_p, result) ==
 /\ (choix = "move"         => (Pre_move(param, etat)         => Act_move(param, etat, etat_p, result)))
 /\ (choix = "has_visited"  => (Pre_has_visited(param, etat)  => Act_has_visited(param, etat, etat_p, result)))
 /\ (choix = "has_finished" => (Pre_has_finished(param, etat) => Act_has_finished(param, etat, etat_p, result)))
 /\ (choix = "reset"        => (Pre_reset(param, etat)        => Act_reset(param, etat, etat_p, result)))

----------------------------------------------------------------

================================================================
