open Graph.Pack.Graph;;

let sommet_aleatoire g =
    fold_vertex (fun v _ -> Some v) g None;;

let rec marquage g v =
    Mark.set v 1;
    iter_succ (marquage g) g v;;

let est_connexe g =
    match sommet_aleatoire g with
    | None -> true
    | Some v ->
            Mark.clear g;
            marquage g v;
            fold_vertex (fun v b -> (b && Mark.get v == 1)) g true;;


let est_degre_pair g v =
    (out_degree g v) mod 2 == 0 ;;


let est_eulerien g =
    ((est_connexe g) && (fold_vertex (fun v b -> (b && est_degre_pair g v)) g true));;

let est_semi_eulerien g =
    ((est_eulerien g)
    || (est_connexe g) &&
       ((fold_vertex (fun v i -> (if (est_degre_pair g v) then i else i+1 )) g 0) == 2));;
