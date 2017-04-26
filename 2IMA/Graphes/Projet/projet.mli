
(* entrees: 
   - un DAG
   sorties:
   - une liste des sommets du DAG ordonnee selon un tri topologique 
   specifs: 
   - vous implementerez l'algorithme de tri topologique de l'enonce, en utilisant un format de file pour Y (section 1)
   *)
val tri_topologique : Dag.Dag.t -> Dag.Dag.V.t list

(* trace d'execution 
   definie en Section 3 de l'enonce (voir Figure 5)
*)
type trace = (Dag.Dag.V.t list) list 

(* entrees: 
   - un nombre entier de ressources r
   - un DAG
   sorties:
   - une trace d'execution du DAG
   specifs: 
   - le DAG est suppose non pondere
   - vous n'utiliserez pas d'heuristique
   *)
val ordonnanceur_multi : int -> Dag.Dag.t -> trace

(* entrees:
   - facteur alpha de vitesse relative (section 4)
   - un nombre entier de ressources de type 1 r1
   - un nombre entier de ressources de type 2 r2
   - un DAG
   sorties:
   - une trace d'execution du DAG
   specifs: 
   - vous prendrez en compte le type de ressource, de sorte à donner une trace valide
   - vous n'utiliserez pas d'heuristique
   *)
let ordonnanceur_heterogene : float -> int -> int-> Dag.Dag.t -> trace

(* entrees: 
   - facteur alpha de vitesse relative (section 4)
   - un nombre entier de ressources de type 1 r1
   - un nombre entier de ressources de type 2 r2
   - un DAG
   sorties:
   - une trace d'execution du DAG
   specifs: 
   - vous prendrez en compte le type de ressource, de sorte à donner une trace valide
   - vous ameliorerez de manière simple l'algorithme de facon a reduire le temps total d'execution
   - vous n'utiliserez pas d'heuristique
   *)
let ordonnanceur_heterogene_quick : float -> int -> int-> Dag.Dag.t -> trace
