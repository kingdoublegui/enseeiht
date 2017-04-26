open Graph.Pack.Digraph;;
open Dag;;

(* sources_croissant : Dag.t -> Dag.V.t list *)
(* Retourne l'ensemble des sources du dag *)
(* entrée: un DAG
   sortie: liste des sources du dag *)
let sources g = 
	(* renvoit true si v est une source *)
	let est_source v =
		((Dag.pred g v)=[])
	in
	(Dag.fold_vertex (fun v l -> if (est_source v) then (v::l) else l) g [])
;;

(* tri_topologique : Dag.t -> Dag.V.t list *)
(* Algorithme de tri topologique utilisant un format de file pour Y (cf enoncé) *)
(* entrees: 
   - un DAG
   sorties:
   - une liste des sommets du DAG ordonnee selon un tri topologique 
*)

let tri_topologique dag = 
	(* renvoit true si v appartient à l *)
	let appartient v l = 
		List.fold_right (fun v_l acc -> acc||(v_l=v)) l false 
	in
	(* fonction qui renvoit true si l'ensemble des prédecesseurs de 
		vj sont dans z *)
	let pred_in vj z =
		Dag.fold_pred (fun pred_vj acc -> acc&&(appartient pred_vj z)) dag vj true
	in
	(* fonction qui ajoute en queue de y (format file) les successeur
		de vi ayant tous ses prédécesseurs dans z, et retourne ce nouveau y*)
	let ajout_succ_OK vi y z = 
		Dag.fold_succ (fun vj y -> if (pred_in vj z) then y@[vj] else y) dag vi y
	in
	let rec boucle z = function
		[] -> z
		|vi::y -> let z = z@[vi] in
					let y = (ajout_succ_OK vi y z) in
					boucle z y
	in 
	boucle [] (sources dag)
;;

type trace = (Dag.V.t list) list;;

let aff_trace tr = List.map (fun l -> List.map Dag.namev l) tr;;

(* ordonnanceur_multi : int -> Dag.t -> trace *)
(* Algorithme qui retourne une trace d'exécution du dag pour un 
   nombre de ressoureces données *)
let ordonnanceur_multi r dag =
	let tri = tri_topologique dag 
	in
	(* retourne true si le traitement de t ne dépend pas d'une tache présente dans 
		t_non_traite *)
	let traitement_possible t t_non_traite = 
		let rec appartient e li = 
			List.fold_left (fun acc t -> acc||(t=e)) false li
		in
		List.fold_left (fun acc v -> acc&&(not(appartient v t_non_traite))) true (Dag.pred dag t)
	in
	(* retourne une liste représentant l'exécution d'une étape 
	                                  ainsi que la liste des taches restantes 
	   Prend en argument : 
		      - la liste des taches traités dans la meme étape (donc non finies) ou ignorées 
				- les ressources restantes
				- une liste de taches a effectuer triées suivant un ordre de dépendance*)
	let rec trace1etape t_non_finie r a_traiter =
		match r, a_traiter with
		|_, [] -> ([], [])
		|r, (t::q) when (Dag.computedv t) -> trace1etape t_non_finie r q
		|0, l -> ([], l)
		|r, (t::q) when (traitement_possible t (t_non_finie@a_traiter)) -> 
							Dag.decreasev t 1.0;
		          	 	let (tr, t_restantes) = trace1etape (t::t_non_finie) (r-1) (t::q)
						 	in
					    	 	((t::tr), t_restantes)
		|r, (t::q) -> let (tr, t_restantes) = (trace1etape (t::t_non_finie) r q) in
						 (tr, t::t_restantes)
	in
	
	let rec aux r = function
		[] -> []
		|l -> let (tr,t_restantes) = (trace1etape [] r l) in 
					 (tr::(aux r t_restantes) : trace)
	in
	Dag.init dag;
	aux r tri
;;


(* ordonnanceur_heterogene float -> int -> int -> Dag.t -> trace *)
(* Algorithme qui retourne une trace d'exécution du dag pour un 
   nombre de ressoureces données avec 2 types de ressources*)
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
let ordonnanceur_heterogene alpha r1 r2 dag =
	let tri = tri_topologique dag 
	in
	(* retourne true si le traitement de t ne dépend pas d'une tache présente dans 
		t_non_traite *)
	let traitement_possible t t_non_traite = 
		let rec appartient e li = 
			List.fold_left (fun acc t -> acc||(t=e)) false li
		in
		List.fold_left (fun acc v -> acc&&(not(appartient v t_non_traite))) true (Dag.pred dag t)
	in
	(* execution : Dag.V.t -> float -> int -> unit *)
	(* Execution d'une tache t par une ressource i.
		Applique decreasev avec les bons paramètres *)
	let execution t alpha i = 
		if Dag.typeressv t = i then
			Dag.decreasev t 1.0
		else
			Dag.decreasev t (1.0/.alpha)
	in
	(* retourne une liste représentant l'exécution d'une étape 
	                                  ainsi que la liste des taches restantes 
	   Prend en argument : 
		      - la liste des taches traités dans la meme étape (donc non finies) ou ignorées 
				- le parametre d'heterogeneite
				- les ressources restantes
				- une liste de taches a effectuer triées suivant un ordre de dépendance
				- une liste des tache commencées aux etapes precedentes pour chaque ressource
		Retourne : 
				- la trace de l'étape traitée
				- les taches restantes non commencees
				- les taches inachevées par les ressources 1 et 2 (type Dag.V.t Option) *)
	let rec trace1etape t_non_finie alpha r1 r2 a_traiter t_commence1 t_commence2 =
		match t_commence1, r1, t_commence2, r2, a_traiter with 
		|None, _, None, _, [] -> (* Fin du traitement *)
				([], [], None, None)
		|_, 0, None, _, [] -> 
				([], a_traiter, t_commence1, t_commence2)
		|None, _, _, 0, [] -> 
				([], a_traiter, t_commence1, t_commence2)
		|_, 0, _, 0, _ -> (* Aucune ressources disponibles *)
				([], a_traiter, t_commence1, t_commence2)
		|_, _, Some t, r2, _ when (r2 != 0)-> (* suite du traitement de t par r2*)	
				execution t alpha 2;
				if (Dag.computedv t) then 
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha r1 (r2-1) a_traiter t_commence1 None
					in
					((t::tr), t_restantes, t1, t2)
				else
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha r1 (r2-1) a_traiter t_commence1 (Some t)
					in
					((t::tr), t_restantes, t1, t2)
		|Some t, r1, _, _, _ when (r1 != 0) -> (* Suite du traitement commencé par r1 *)
				execution t alpha 1;
				if (Dag.computedv t) then 
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha (r1-1) r2 a_traiter None t_commence2
					in
					((t::tr), t_restantes, t1, t2)
				else
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha (r1-1) r2 a_traiter (Some t) t_commence2
					in
					((t::tr), t_restantes, t1, t2)
		|_, _, _, _, (t::q) when (traitement_possible t (t_non_finie@a_traiter))-> 
				(* Traitement d'un nouveau t *)
				if (r1 != 0) then (* On sait avec le filtrage précédent que t1 = None *)
					trace1etape (t::t_non_finie) alpha r1 r2 q (Some t) t_commence2
				else (* r2 != 0 car le cas r1=r2=0 est filtré plus haut *)
					trace1etape (t::t_non_finie) alpha r1 r2 q t_commence1 (Some t)
		|_, _, _, _, (t::q) -> (* t ne peut pas encore etre exécuté *)
				let (tr, t_restantes, t1, t2) = 
					trace1etape (t::t_non_finie) alpha r1 r2 q t_commence1 t_commence2
				in
				(tr, (t::t_restantes), t1, t2)
	in
	(* Retourne une liste contenant les valeurs optionnelles de t1 et t2 *)
	let t_courant t1 t2 = 
		match t1, t2 with 
		|None, None -> []
		|None, Some v2 -> [v2]
		|Some v1, None -> [v1]
		|Some v1, Some v2 -> [v1; v2]
	in
	let rec aux t1 t2 l =
		match l, t1, t2 with
		|[], None, None -> []
		|_, _, _ -> let (tr,t_restantes, t_commence1, t_commence2) = 
										(trace1etape (t_courant t1 t2) alpha r1 r2 l t1 t2) in 
					 (tr::(aux t_commence1 t_commence2 t_restantes) : trace)
	in
	Dag.init dag;
	aux None None tri
;;


(* ordonnanceur_heterogene_quick float -> int -> int -> Dag.t -> trace *)
(* Algorithme qui retourne une trace d'exécution du dag pour un 
   nombre de ressoureces données avec 2 types de ressources*)
(* entrees:
   - facteur alpha de vitesse relative (section 4)
   - un nombre entier de ressources de type 1 r1
   - un nombre entier de ressources de type 2 r2
   - un DAG
   sorties:
   - une trace d'execution du DAG
   specifs: 
   - vous prendrez en compte le type de ressource, de sorte à donner une trace valide
   *)
let ordonnanceur_heterogene_quick alpha r1 r2 dag =
	let tri = tri_topologique dag 
	in
	(* retourne true si le traitement de t ne dépend pas d'une tache présente dans 
		t_non_traite *)
	let traitement_possible t t_non_traite = 
		let rec appartient e li = 
			List.fold_left (fun acc t -> acc||(t=e)) false li
		in
		List.fold_left (fun acc v -> acc&&(not(appartient v t_non_traite))) true (Dag.pred dag t)
	in
	(* execution : Dag.V.t -> float -> int -> unit *)
	(* Execution d'une tache t par une ressource i.
		Applique decreasev avec les bons paramètres *)
	let execution t alpha i = 
		if Dag.typeressv t = i then
			Dag.decreasev t 1.0
		else
			Dag.decreasev t (1.0/.alpha)
	in
	(* retourne une liste représentant l'exécution d'une étape 
	                                  ainsi que la liste des taches restantes 
	   Prend en argument : 
		      - la liste des taches traités dans la meme étape (donc non finies) ou ignorées 
				- le parametre d'heterogeneite
				- les ressources restantes
				- une liste de taches a effectuer triées suivant un ordre de dépendance
				- une liste des tache commencées aux etapes precedentes pour chaque ressource
		Retourne : 
				- la trace de l'étape traitée
				- les taches restantes non commencees
				- les taches inachevées par les ressources 1 et 2 (type Dag.V.t Option) *)
	let rec trace1etape t_non_finie alpha r1 r2 a_traiter t_commence1 t_commence2 =
		match t_commence1, r1, t_commence2, r2, a_traiter with 
		|None, _, None, _, [] -> (* Fin du traitement *)
				([], [], None, None)
		|_, 0, None, _, [] -> 
				([], a_traiter, t_commence1, t_commence2)
		|None, _, _, 0, [] -> 
				([], a_traiter, t_commence1, t_commence2)
		|_, 0, _, 0, _ -> (* Aucune ressources disponibles *)
				([], a_traiter, t_commence1, t_commence2)
		|_, _, Some t, r2, _ when (r2 != 0) -> (* suite du traitement de t par r2*)	
				execution t alpha 2;
				if (Dag.computedv t) then 
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha r1 (r2-1) a_traiter t_commence1 None
					in
					((t::tr), t_restantes, t1, t2)
				else
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha r1 (r2-1) a_traiter t_commence1 (Some t)
					in
					((t::tr), t_restantes, t1, t2)
		|Some t, r1, _, _, _ when (r1 != 0)-> (* Suite du traitement commencé par r1 *)
				execution t alpha 1;
				if (Dag.computedv t) then 
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha (r1-1) r2 a_traiter None t_commence2
					in
					((t::tr), t_restantes, t1, t2)
				else
					let (tr, t_restantes, t1, t2) = 
						trace1etape t_non_finie alpha (r1-1) r2 a_traiter (Some t) t_commence2
					in
					((t::tr), t_restantes, t1, t2)
		|_, _, _, _, (t::q) when (traitement_possible t (t_non_finie@a_traiter))-> 
				(* Traitement d'un nouveau t *)
				(* Seule cette condition change *)
				if ((Dag.typeressv t = 1)&&(r1 != 0)) then (* t1 = None avec le filtrage prec*)
					trace1etape (t::t_non_finie) alpha r1 r2 q (Some t) t_commence2
				else if (r2 != 0) then (* t2 = None avec le filtrage précédent *)
					trace1etape (t::t_non_finie) alpha r1 r2 q t_commence1 (Some t)
				else (* r2 = 0 et r1 != 0 et t1 = None avec les filtrages precedent *)
					trace1etape (t::t_non_finie) alpha r1 r2 q (Some t) t_commence2
		|_, _, _, _, (t::q) -> (* t ne peut pas encore etre exécuté *)
				let (tr, t_restantes, t1, t2) = 
					trace1etape (t::t_non_finie) alpha r1 r2 q t_commence1 t_commence2
				in
				(tr, (t::t_restantes), t1, t2)
	in
	(* Retourne une liste contenant les valeurs optionnelles de t1 et t2 *)
	let t_courant t1 t2 = 
		match t1, t2 with 
		|None, None -> []
		|None, Some v2 -> [v2]
		|Some v1, None -> [v1]
		|Some v1, Some v2 -> [v1; v2]
	in
	let rec aux t1 t2 l = 
		match l, t1, t2 with
		|[], None, None -> []
		|_, _, _ -> let (tr,t_restantes, t_commence1, t_commence2) = 
										(trace1etape (t_courant t1 t2) alpha r1 r2 l t1 t2) in 
					 (tr::(aux t_commence1 t_commence2 t_restantes) : trace)
	in
	Dag.init dag;
	aux None None tri
;;
