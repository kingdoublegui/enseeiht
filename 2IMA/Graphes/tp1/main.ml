open Graph.Pack.Graph;;

let est_connexe g =
    let rec dfs g v =
        Mark.set v 1;
        fold_succ (fun v mark -> if (Mark.get v ~= 1) then mark = mark+1;(dfs g v)) g

let est_degre_pair g v =
    (out_degree g v) mod 2 == 0 ;;


let est_eulerien g =
    ((est_connexe g) && (fold_vertex (fun v b -> (b && est_degre_pair g v)) g true));;

let est_semi_eulerien g =
    ((est_eulerien g)
    || (est_connexe g) &&
       ((fold_vertex (fun v i -> (if (est_degre_pair g v) then i else i+1 )) g 0) == 2));;
