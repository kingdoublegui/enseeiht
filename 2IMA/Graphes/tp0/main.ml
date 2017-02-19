let l1 = [1; 2; 3];;
let l2 = [3; 2; 5; 1];;

let rec minList l =
    match l with
    | [t]-> t
    | t::q -> min t (minList q)
    | _ -> failwith("empty list");;

let minList2 l =
    match l with
    | [t] -> t 
    | t::q -> List.fold_right ( min ) q t
    | _ -> failwith("empty list");;
