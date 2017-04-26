open Dag

let dag1 = Dag.create ();;

let vs1 = Dag.createv ("s",1,1.);;
let vp1 = Dag.createv ("p",2,1.);;
let vi1 = Dag.createv ("i",1,1.);;
let vd1 = Dag.createv ("d",2,1.);;
let ve1 = Dag.createv ("e",1,1.);;
let vr1 = Dag.createv ("r",2,1.);;
let vb1 = Dag.createv ("b",1,1.);;
let vc1 = Dag.createv ("c",2,1.);;
let vf1 = Dag.createv ("f",1,1.);;
let vg1 = Dag.createv ("g",2,1.);;
let vh1 = Dag.createv ("h",1,1.);;
let vj1 = Dag.createv ("j",2,1.);;
let vk1 = Dag.createv ("k",1,1.);;
let vl1 = Dag.createv ("l",2,1.);;
let vm1 = Dag.createv ("m",1,1.);;
let vn1 = Dag.createv ("n",2,1.);;
let vo1 = Dag.createv ("o",1,1.);;
let vq1 = Dag.createv ("q",2,1.);;

Dag.add_vertex dag1 vb1;;
Dag.add_vertex dag1 vc1;;
Dag.add_vertex dag1 vf1;;
Dag.add_vertex dag1 vg1;;
Dag.add_vertex dag1 vh1;;
Dag.add_vertex dag1 vj1;;
Dag.add_vertex dag1 vk1;;
Dag.add_vertex dag1 vl1;;
Dag.add_vertex dag1 vm1;;
Dag.add_vertex dag1 vn1;;
Dag.add_vertex dag1 vo1;;
Dag.add_vertex dag1 vq1;;
Dag.add_vertex dag1 vs1;;
Dag.add_vertex dag1 vp1;;
Dag.add_vertex dag1 vi1;;
Dag.add_vertex dag1 vd1;;
Dag.add_vertex dag1 ve1;;
Dag.add_vertex dag1 vr1;;


Dag.add_edge dag1 vs1 vk1;;
Dag.add_edge dag1 vs1 vp1;;
Dag.add_edge dag1 vj1 vi1;;
Dag.add_edge dag1 vp1 vh1;;
Dag.add_edge dag1 vg1 vh1;;
Dag.add_edge dag1 vh1 vc1;;
Dag.add_edge dag1 vp1 vg1;;
Dag.add_edge dag1 vp1 vi1;;
Dag.add_edge dag1 vf1 vo1;;
Dag.add_edge dag1 vi1 vd1;;
Dag.add_edge dag1 vi1 vf1;;
Dag.add_edge dag1 vd1 vl1;;
Dag.add_edge dag1 vd1 ve1;;
Dag.add_edge dag1 vd1 vm1;;
Dag.add_edge dag1 vb1 vl1;;
Dag.add_edge dag1 vn1 vm1;;
Dag.add_edge dag1 ve1 vr1;;
Dag.add_edge dag1 vq1 vr1;;

Dag.dot_output dag1 "dag1.dot";;

let dag2 = Dag.create();;

let vx2 = Dag.createv ("x",1,1.);;
let vb2 = Dag.createv ("b",2,1.);;
let vc2 = Dag.createv ("c",1,1.);;
let vo2 = Dag.createv ("o",2,1.);;
let ve2 = Dag.createv ("e",1,1.);;
let vf2 = Dag.createv ("f",2,1.);;
let vi2 = Dag.createv ("i",1,1.);;
let vh2 = Dag.createv ("h",2,1.);;
let vr2 = Dag.createv ("r",1,1.);;
let vj2 = Dag.createv ("j",2,1.);;
let va2 = Dag.createv ("a",1,1.);;
let vn2 = Dag.createv ("n",2,1.);;

Dag.add_vertex dag2 vx2;;
Dag.add_vertex dag2 vb2;;
Dag.add_vertex dag2 vc2;;
Dag.add_vertex dag2 vo2;;
Dag.add_vertex dag2 ve2;;
Dag.add_vertex dag2 vf2;;
Dag.add_vertex dag2 vi2;;
Dag.add_vertex dag2 vh2;;
Dag.add_vertex dag2 vr2;;
Dag.add_vertex dag2 vj2;;
Dag.add_vertex dag2 va2;;
Dag.add_vertex dag2 vn2;;

Dag.add_edge dag2 va2 vj2;;
Dag.add_edge dag2 va2 vn2;;
Dag.add_edge dag2 vj2 vf2;;
Dag.add_edge dag2 vn2 vo2;;
Dag.add_edge dag2 vn2 vr2;;
Dag.add_edge dag2 vo2 vr2;;
Dag.add_edge dag2 vo2 ve2;;
Dag.add_edge dag2 vr2 vf2;;
Dag.add_edge dag2 ve2 vh2;;
Dag.add_edge dag2 vr2 ve2;;
Dag.add_edge dag2 ve2 vx2;;
Dag.add_edge dag2 vi2 vb2;;
Dag.add_edge dag2 vi2 vc2;;
Dag.add_edge dag2 vx2 vi2;;

Dag.dot_output dag2 "dag2.dot";;

let dag3 = Dag.create();;

let va3 = Dag.createv ("a",1,1.);;
let vv3 = Dag.createv ("v",2,1.);;
let vc3 = Dag.createv ("c",1,1.);;
let vd3 = Dag.createv ("d",2,1.);;
let ve3 = Dag.createv ("e",1,1.);;
let vf3 = Dag.createv ("f",2,1.);;
let vi3 = Dag.createv ("i",1,1.);;
let vh3 = Dag.createv ("h",2,1.);;
let vg3 = Dag.createv ("g",1,1.);;
let vj3 = Dag.createv ("j",2,1.);;
let vm3 = Dag.createv ("m",1,1.);;
let vn3 = Dag.createv ("n",2,1.);;
let vk3 = Dag.createv ("k",1,1.);;
let vl3 = Dag.createv ("l",2,1.);;

Dag.add_vertex dag3 va3;;
Dag.add_vertex dag3 vv3;;
Dag.add_vertex dag3 vc3;;
Dag.add_vertex dag3 vd3;;
Dag.add_vertex dag3 ve3;;
Dag.add_vertex dag3 vf3;;
Dag.add_vertex dag3 vg3;;
Dag.add_vertex dag3 vh3;;
Dag.add_vertex dag3 vi3;;
Dag.add_vertex dag3 vj3;;
Dag.add_vertex dag3 vk3;;
Dag.add_vertex dag3 vl3;;
Dag.add_vertex dag3 vm3;;
Dag.add_vertex dag3 vn3;;

Dag.add_edge dag3 vg3 vf3;;
Dag.add_edge dag3 vn3 vf3;;
Dag.add_edge dag3 vn3 va3;;
Dag.add_edge dag3 vd3 va3;;
Dag.add_edge dag3 vd3 vl3;;
Dag.add_edge dag3 vj3 vl3;;
Dag.add_edge dag3 vj3 vm3;;
Dag.add_edge dag3 vk3 vm3;;
Dag.add_edge dag3 vf3 vc3;;
Dag.add_edge dag3 va3 vc3;;
Dag.add_edge dag3 va3 vv3;;
Dag.add_edge dag3 vl3 vv3;;
Dag.add_edge dag3 vl3 ve3;;
Dag.add_edge dag3 vm3 ve3;;
Dag.add_edge dag3 vc3 vi3;;
Dag.add_edge dag3 vv3 vi3;;
Dag.add_edge dag3 vv3 vh3;;
Dag.add_edge dag3 ve3 vh3;;

Dag.dot_output dag3 "dag3.dot";;

let dag4 = Dag.create();;

let va4 = Dag.createv ("a",1,2.);;
let vb4 = Dag.createv ("b",2,2.);;
let vc4 = Dag.createv ("c",1,2.);;
let vd4 = Dag.createv ("d",2,2.);;
let ve4 = Dag.createv ("e",1,2.);;
let vf4 = Dag.createv ("f",2,2.);;
let vo4 = Dag.createv ("o",1,2.);;
let vp4 = Dag.createv ("p",2,2.);;
let vq4 = Dag.createv ("q",1,2.);;
let vr4 = Dag.createv ("r",2,2.);;
let vg4 = Dag.createv ("g",1,2.);;
let vh4 = Dag.createv ("h",2,1.);;
let vi4 = Dag.createv ("i",1,1.);;
let vj4 = Dag.createv ("j",2,1.);;
let vk4 = Dag.createv ("k",1,1.);;
let vl4 = Dag.createv ("l",2,1.);;
let vm4 = Dag.createv ("m",1,1.);;
let vs4 = Dag.createv ("s",2,1.);;
let vt4 = Dag.createv ("t",1,1.);;
let vu4 = Dag.createv ("u",2,1.);;
let vn4 = Dag.createv ("n",1,3.);;

Dag.add_vertex dag4 va4;;
Dag.add_vertex dag4 vb4;;
Dag.add_vertex dag4 vc4;;
Dag.add_vertex dag4 vd4;;
Dag.add_vertex dag4 ve4;;
Dag.add_vertex dag4 vf4;;
Dag.add_vertex dag4 vo4;;
Dag.add_vertex dag4 vp4;;
Dag.add_vertex dag4 vq4;;
Dag.add_vertex dag4 vr4;;
Dag.add_vertex dag4 vg4;;
Dag.add_vertex dag4 vh4;;
Dag.add_vertex dag4 vi4;;
Dag.add_vertex dag4 vj4;;
Dag.add_vertex dag4 vk4;;
Dag.add_vertex dag4 vl4;;
Dag.add_vertex dag4 vm4;;
Dag.add_vertex dag4 vs4;;
Dag.add_vertex dag4 vt4;;
Dag.add_vertex dag4 vu4;;
Dag.add_vertex dag4 vn4;;

Dag.add_edge dag4 va4 vb4;;
Dag.add_edge dag4 vb4 vc4;;
Dag.add_edge dag4 vc4 vd4;;
Dag.add_edge dag4 vd4 ve4;;
Dag.add_edge dag4 ve4 vf4;;
Dag.add_edge dag4 vf4 vo4;;
Dag.add_edge dag4 vo4 vp4;;
Dag.add_edge dag4 vp4 vq4;;
Dag.add_edge dag4 vq4 vr4;;
Dag.add_edge dag4 vg4 vh4;;
Dag.add_edge dag4 vg4 vi4;;
Dag.add_edge dag4 vg4 vj4;;
Dag.add_edge dag4 vg4 vk4;;
Dag.add_edge dag4 vg4 vl4;;
Dag.add_edge dag4 vg4 vm4;;
Dag.add_edge dag4 vg4 vs4;;
Dag.add_edge dag4 vg4 vt4;;
Dag.add_edge dag4 vg4 vu4;;
Dag.add_edge dag4 vr4 vn4;;
Dag.add_edge dag4 vh4 vn4;;
Dag.add_edge dag4 vi4 vn4;;
Dag.add_edge dag4 vj4 vn4;;
Dag.add_edge dag4 vk4 vn4;;
Dag.add_edge dag4 vl4 vn4;;
Dag.add_edge dag4 vm4 vn4;;
Dag.add_edge dag4 vs4 vn4;;
Dag.add_edge dag4 vt4 vn4;;
Dag.add_edge dag4 vu4 vn4;;

Dag.dot_output dag4 "dag4.dot";;


