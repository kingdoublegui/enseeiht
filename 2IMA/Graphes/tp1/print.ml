(* affiche_v : t -> unit *)
(* affiche les labels des sommets d'un graphe *)
let affiche_v g = 
  begin
    iter_vertex (fun v -> (Format.printf "%i " (V.label v))) g;
    Format.printf "\n"
  end
;;
