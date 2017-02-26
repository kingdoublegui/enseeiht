(* graphe non orienté g1 *)
(* les sommets de label 0,1,2 forment une clique *)
(* et {2,3} est une arête *)

let g1 = create ();;
let vv0=V.create 0;;
let vv1=V.create 1;;
let vv2=V.create 2;;
let vv3=V.create 3;;
add_vertex g1 vv0;;
add_vertex g1 vv1;;
add_vertex g1 vv2;;
add_vertex g1 vv3;;
add_edge g1 vv0 vv1;;
add_edge g1 vv1 vv2;;
add_edge g1 vv2 vv0;;
add_edge g1 vv2 vv3;;


(* Graphe non orienté g2 *)
(* avec trois composantes connexes K_1 K_2 et K_3 *)
let g2 = create ();;
let k00=V.create 0;;
let k10=V.create 1;;
let k11=V.create 2;;
let k20=V.create 3;;
let k21=V.create 4;;
let k22=V.create 5;;
add_vertex g2 k00;;
add_vertex g2 k10;;
add_vertex g2 k11;;
add_vertex g2 k20;;
add_vertex g2 k21;;
add_vertex g2 k22;;
add_edge g2 k10 k11;;
add_edge g2 k20 k21;;
add_edge g2 k21 k22;;
add_edge g2 k22 k20;;

(* graphe ge eulérien K_3 *)
let ge = create ();;
let k0=V.create 0;;
let k1=V.create 1;;
let k2=V.create 2;;
add_vertex ge k0;;
add_vertex ge k1;;
add_vertex ge k2;;
add_edge ge k0 k1;;
add_edge ge k2 k1;;
add_edge ge k0 k2;;

(* un poisson *)
(* /\/| *)
(* \/\| *)
let gp = create ();;
let vp0=V.create 0;;
let vp1=V.create 1;;
let vp2=V.create 2;;
let vp3=V.create 3;;
let vp4=V.create 4;;
let vp5=V.create 5;;
add_vertex gp vp0;;
add_vertex gp vp1;;
add_vertex gp vp2;;
add_vertex gp vp3;;
add_vertex gp vp4;;
add_vertex gp vp5;;
add_edge gp vp0 vp1;;
add_edge gp vp0 vp2;;
add_edge gp vp1 vp3;;
add_edge gp vp2 vp3;;
add_edge gp vp3 vp4;;
add_edge gp vp3 vp5;;
add_edge gp vp4 vp5;;

(* la petite maison semi eulerienne *)
let gm = create ();;
let vm0=V.create 0;;
let vm1=V.create 1;;
let vm2=V.create 2;;
let vm3=V.create 3;;
let vm4=V.create 4;;
add_vertex gm vm0;;
add_vertex gm vm1;;
add_vertex gm vm2;;
add_vertex gm vm3;;
add_vertex gm vm4;;
add_edge gm vm0 vm1;;
add_edge gm vm0 vm2;;
add_edge gm vm1 vm2;;
add_edge gm vm1 vm3;;
add_edge gm vm1 vm4;;
add_edge gm vm2 vm3;;
add_edge gm vm2 vm4;;
add_edge gm vm3 vm4;;



(* un graphe semi-eulérien *)
let g_euler = create ();;

let ve4 = V.create 1;;
let ve5 = V.create 2;;
let ve6 = V.create 3;;
let ve1 = V.create 4;;
let ve7 = V.create 5;;
let ve8 = V.create 6;;
let ve2 = V.create 7;;
let ve3 = V.create 8;;

add_vertex g_euler ve5;;
add_vertex g_euler ve6;;
add_vertex g_euler ve1;;
add_vertex g_euler ve8;;
add_vertex g_euler ve2;;
add_vertex g_euler ve3;;
add_vertex g_euler ve7;;
add_vertex g_euler ve4;;

add_edge g_euler ve5 ve6;;
add_edge g_euler ve8 ve4;;
add_edge g_euler ve4 ve5;;
add_edge g_euler ve1 ve2;;
add_edge g_euler ve8 ve1;;
add_edge g_euler ve3 ve8;;
add_edge g_euler ve3 ve7;;
add_edge g_euler ve7 ve4;;
add_edge g_euler ve2 ve3;;
add_edge g_euler ve6 ve8;;
