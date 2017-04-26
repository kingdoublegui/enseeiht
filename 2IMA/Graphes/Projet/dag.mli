open Graph
module Dag : 
sig	
	module Vertex : 
	sig
		type t
		val compare : t -> t -> int
		val equal : t -> t -> bool
		val default : t
	end

	module Edge :
	sig
		type t
		val  compare : t -> t -> int
		val equal : t -> t -> bool
		val default : t
    end

	include (module type of Graph.Imperative.Digraph.AbstractLabeled(Vertex)(Edge))
    

    (************************)
    (* Fonctions sur le DAG *)
    (************************)
    (* Enregistre le graphe sous forme de fichier .dot *)
    val dot_output : t -> string -> unit
    (* Reinitialise l'avancement de tous les processus du graphe *)
	val init : t -> unit
    

    (************************)
    (* Creation d'un vertex *)
    (************************)
    (* Cree un processus, en lui attribuant un nom, un type et un cout *)
    val createv   : string*int*float -> V.t


    (******************************)
    (* Informations sur le vertex *)
    (******************************)
    (* Donne le nom d'un processus *)
    val namev : V.t -> string
    (* Donne le type de ressource associe au processus *)
    val typeressv : V.t -> int
    (* Donne le cout total du processus *)
    val costv : V.t -> float


    (************************)
    (* Avancement du vertex *)
    (************************)
    (* Reinitialise l'avancement d'un processus *)
    val initv     : V.t -> unit
    (* Evalue l'avancement d'un processus en retranchant au cout courant *)
    val decreasev : V.t -> float -> unit
    (* Verifie si le processus a ete completement execute *)
    val computedv : V.t -> bool
    (* Donne le cout restant du processus *)
    val currcostv : V.t -> float
end
