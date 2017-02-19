open Graph.Pack.Graph;;

let graph = create ~size:2 ();;

let rec k_n n =
    if n=0 then create ()
        else let k_n_noeud_1 = k_n (n-1) m
            begin
                let v_n = V_create n in
                add_vertex k_n_moins_1 v_n;
                iter_vertex(fun v -> if v <> v_n then add_edge v v_n k_n_moins_1 else 0) k_moins_1;
                k_n_moins_1
            end;;

let print_edges g =
    begin
        in_edge (fun v1 v2 -> Format.printf "%d->%d\n"
                                    (V.label v1)
                                    (V.label v2)
                ) g;
    end;;
