open Graph.Pack.Graph;;

let get_vertices g =
    let f v l = v::l
    in
    fold_vertex f g [];;

let count_marked g =
    fold_vertex (fun v x -> if (Mark.get v) = 1 then (1 + x) else x) g 0;;

let degre g v =
    let suivants = succ g v in
    List.length suivants;;

let couleurs_differentes l c =
    let f v r = (r && (Mark.get v != c)) in
    List.fold_right f l true;;

let sort_list g l =
    let cmp v1 v2 = (out_degree g v1) - (out_degree g v2)
    in
    List.sort cmp l;;

let coloration_possible g v c =
    if Mark.get v != 0 then false else
    let suivants = succ g v in
    couleurs_differentes suivants c;;

let colorier_n g n =
    let f x y =
        if (coloration_possible g x n)
        then (Mark.set x n; ())
        else ()
    in
    let l1 = get_vertices g in
    let l2 = sort_list g l1 in
    List.fold_right f l2 ();;

let fini g =
    fold_vertex (fun x y -> (y && Mark.get x != 0)) g true;;

let rec coloration g =
    (Mark.clear g;
    let rec aux gg n =
        if fini gg then ()
        else
            (colorier_n gg n;
            aux gg (n+1))
    in
    aux g 1)
    ;;

let disp g =
    fold_vertex (fun v l -> (v, (Mark.get v))::l) g [];;


