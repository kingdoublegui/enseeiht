(*
Cree les graphes de test dag1 ... dag5 et genere les fichiers .dot associes.
Pour visualiser les graphes : 
	dot dag.dot -Tps -o dag.ps
	evince dag.ps
*)


module Dag = 
struct
	module Vertex = struct
	  type t = string*int*float*float ref
	  let compare : t -> t -> int = Pervasives.compare
	  let hash = Hashtbl.hash
	  let equal = (=)
	  let default = (("O",0,0.0,ref 0.0) : t)

	  let name     (s,_,_,_ : string*int*float*float ref) = s
	  let resstype (_,r,_,_ : string*int*float*float ref) = r
	  let ress     (_,_,m,_ : string*int*float*float ref) = m

	  let strname      (nod : string*int*float*float ref) = name nod
	  let strresstype  (nod : string*int*float*float ref) = string_of_int (resstype nod)
	  let strress      (nod : string*int*float*float ref) = string_of_float (ress nod)

	  let init     (_,_,m,l : string*int*float*float ref)    =  l:=m;;
	  let decrease (_,_,_,l : string*int*float*float ref) v  =  l:=!l-.v;;
	  let computed (_,_,_,l : string*int*float*float ref)    = !l<=0.;;
	  let lastress (_,_,_,l : string*int*float*float ref)    = !l;;

	  let createlabel (s,r,m : string*int*float) = (s,r,m, ref m);;
	end;;

	module Edge = struct
	  type t = int
	  let compare : t -> t -> int = Pervasives.compare
	  let hash = Hashtbl.hash
	  let equal = (=)
	  let default = 0
	end;;

	include Graph.Imperative.Digraph.AbstractLabeled(Vertex)(Edge)

	
	module Display = struct
		include Graph.Imperative.Digraph.AbstractLabeled(Vertex)(Edge)
		let vertex_name v = Vertex.strname (V.label v)
		let graph_attributes _ = []
		let default_vertex_attributes _ = []
		let vertex_attributes v = [`Label ((Vertex.strname (V.label v))^":r"^(Vertex.strresstype (V.label v))^"\n "^(Vertex.strress (V.label v)))]
		let default_edge_attributes _ = []
		let edge_attributes e = []
		let get_subgraph _ = None
	end
	module Dot_ = Graph.Graphviz.Dot(Display)
	module Neato = Graph.Graphviz.Neato(Display)

	let dot_output g f =
		let oc = open_out f in
		if is_directed then Dot_.output_graph oc g else Neato.output_graph oc g;
        close_out oc;;
    
    let init g = iter_vertex (fun v -> Vertex.init (V.label v)) g;;

    let createv lab = V.create (Vertex.createlabel lab);;
    
    let namev ver           =  Vertex.name (V.label ver);;
    let typeressv ver       =  Vertex.resstype (V.label ver);;
    let costv ver           =  Vertex.ress (V.label ver);;

    let initv ver           =  Vertex.init (V.label ver);;
    let decreasev ver value =  Vertex.decrease (V.label ver) value;;
    let computedv ver       =  Vertex.computed (V.label ver);;
    let currcostv ver       =  Vertex.lastress (V.label ver);;

end;;

