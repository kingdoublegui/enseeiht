open Ast
open Semantics

let debug = ref false;

(* ..................................................................*)
type typeType =
  | IntegerType
  | BooleanType
  | FunctionType of typeType * typeType
  | VariableType of (typeType ref * int)
  | ReferenceType of typeType
  | UnitType
  | ErrorType
  | UnknownType
;;

let rec string_of_type t =
  match t with
  | IntegerType -> "int"
  | BooleanType -> "bool"
  | FunctionType (tpar,tres) -> "(" ^ (string_of_type tpar) ^ " -> " ^ (string_of_type tres) ^ ")"
  | VariableType (r,c) -> "('" ^ (string_of_int c) ^ " " ^ (string_of_type (!r)) ^ ")"
  | ReferenceType t -> "(ref " ^ (string_of_type t) ^ ")"
  | UnitType -> "unit"
  | ErrorType -> "error"
  | UnknownType -> "unknown"
and string_of_type_env env =
  match env with
    | [] -> ""
    | (key,typevalue)::q -> 
       ("{" ^ key ^ " = " ^ (string_of_type typevalue)) ^ "} " ^ (string_of_type_env q) 
;;

let rec normalize t =
  match t with
  | (IntegerType | BooleanType | UnitType 
    | ErrorType | UnknownType) as result -> result
  | FunctionType (tp,tr) -> 
    let ntp = normalize tp in
    let ntr = normalize tr in
      if ((ntp <> ErrorType) && (ntr <> ErrorType)) then 
        FunctionType (ntp,ntr)
      else 
        ErrorType
  | VariableType (r,_) when ((! r) <> UnknownType) -> (normalize (! r))
  | VariableType _ as result -> result
  | ReferenceType t -> 
      let nt = normalize t in
        if (nt <> ErrorType) then ReferenceType nt
      else 
        ErrorType
;;

(* ..................................................................*)
(* unify : typeType -> typeType                                      *)
(*     -> typeType * bool                                            *)
(* unify permet de comparer 2 types (en valuant eventuellement les   *)
(* variables de types presentes dans chacun des 2). La valeur ren-   *)
(* voyee est le premier type et true s'il y a correspondance         *)
(* ErrorType et false sinon                                          *)
(* ..................................................................*)
let rec unify t1 t2 =
  (* test de presence de v dans t : pour eviter une boucle infinie *)
  let rec occur_check v t =
    if ( ! debug ) then (print_endline (string_of_type t));
    match t with
    | (IntegerType | BooleanType | UnitType 
      | ErrorType | UnknownType) -> false
    | FunctionType (tp,tr) -> 
      ((occur_check v tp) || (occur_check v tr))
    | VariableType (r,_) -> r == v
    | ReferenceType t -> (occur_check v t)

  and
  (* la vraie comparaison *)
    aux t1 t2 =
    match t1,t2 with
    | IntegerType,IntegerType -> IntegerType,true
    | BooleanType,BooleanType -> BooleanType,true
    | UnitType,UnitType -> UnitType,true
    | ReferenceType st1, ReferenceType st2 ->
        let t,tr = (aux st1 st2) in
          (ReferenceType t), tr
    | (FunctionType (tp1,tr1)),(FunctionType (tp2,tr2)) ->
      let utp,rtp = (aux tp1 tp2) in
      let utr,rtr = (aux tr1 tr2) in
        (FunctionType (utp,utr)),(rtp && rtr)
    | (VariableType (v1,_)), (VariableType (v2,_)) when 
         ((! v1) <> UnknownType) && ((! v2) <> UnknownType) -> (aux (! v1) (! v2))
    | (VariableType (v1,_)), _ when 
         ((! v1) = UnknownType) && (not (occur_check v1 t2)) -> ((v1 := t2); (t2,true))
    | _, (VariableType (v2,_)) when 
         ((! v2) = UnknownType) && (not (occur_check v2 t1)) -> ((v2 := t1); (t1,true))
    | ErrorType, _ -> (ErrorType,false)
    | _, ErrorType -> (ErrorType,false)
    | _ -> (ErrorType,false) 
  in
    let t1 = normalize t1 in
    let t2 = normalize t2 in
      (if (! debug) then 
        (print_endline ("before " ^ (string_of_type t1) ^ " <-> " ^ (string_of_type t2))));
      let t,r = aux t1 t2 in
        (if (! debug) then 
          (print_endline ("after " ^ (string_of_type t1) ^ " <-> " ^ (string_of_type t2))));
        (if (! debug) then 
          (print_endline ("result " ^ (string_of_type t))));
         (normalize t),r
;;

(* ..................................................................*)
(* newVariable : typeType                                            *)
(* Cree une variable de type, differente des précedentes             *)
(* dont la valeur initiale (modifiable) est : Unknown                *)
(* ..................................................................*)
let counter = ref 0
;;

let newVariable () =
  counter := (! counter) + 1;
  (VariableType ((ref UnknownType),(! counter)))
;;

(* ..................................................................*)
(* type_of_expr Ast.ast -> environment -> typeType                   *)
(*  calcule le type de l'expression dans un environnement de types   *)
(* Chaque expression est traitee par une fonction ruleXXX traitant   *)
(* a la regle d'inference associee                                   *)
(* ..................................................................*)
let rec
type_of_expr expr env =
  let rec aux expr env =
    if ( ! debug ) then 
      (print_endline ((string_of_ast expr) ^ " -> " ^ (string_of_type_env env))); 
    match expr with
    | (AccessNode name)                     -> ruleName name env
    | (IntegerNode value)                   -> ruleInteger
    | (TrueNode)                            -> ruleBoolean
    | (FalseNode)                           -> ruleBoolean
    | (UnitNode)                            -> ruleUnit
    | (BinaryNode (op,left,right))          -> ruleBinary op left right env
    | (UnaryNode (op,subexpr))              -> ruleUnary op subexpr env
    | (IfthenelseNode (cond, ethen, eelse)) -> ruleIf cond ethen eelse env
    | (LetNode (ident,bvalue,bin))          -> ruleLet ident bvalue bin env
    | (LetrecNode (ident,bvalue,bin))       -> ruleLetrec ident bvalue bin env
    | (FunctionNode (par,body))             -> ruleFunction par body env
    | (CallNode (fct,par))                  -> ruleCall fct par env
    | (RefNode subexpr)                     -> ruleRef subexpr env
    | (ReadNode subexpr)                    -> ruleRead subexpr env
    | (WriteNode (left,right))              -> ruleWrite left right env
    | (SequenceNode (left,right))           -> ruleSequence left right env
  in
    (normalize (aux expr env))

and
(* ..................................................................*)
ruleName name env =
  (match (lookforEnv name env) with
  | NotFound -> ErrorType 
  | (Found t) -> t) 

and
(* ..................................................................*)
ruleInteger = IntegerType

and
(* ..................................................................*)
ruleBoolean = BooleanType

and
(* ..................................................................*)
ruleUnit = UnitType

and
(* ..................................................................*)
ruleBinary op left right env =
  let tleft = (type_of_expr left env) in
  let tright = (type_of_expr right env) in
    (match op with
    | (Equal | Different | Lesser | LesserEqual | Greater | GreaterEqual) ->
      let utl,url = unify tleft IntegerType in
      let utr,urr = unify tright IntegerType in
        (if (url && urr) then BooleanType else ErrorType)
    | (Add | Substract | Multiply | Divide) ->
      let utl,url = unify tleft IntegerType in
      let utr,urr = unify tright IntegerType in
        (if (url && urr) then IntegerType else ErrorType)
    | (Or | And) ->
      let utl,url = unify tleft BooleanType in
      let utr,urr = unify tright BooleanType in
        (if (url && urr) then BooleanType else ErrorType)
(*
    | _ -> ErrorType)
*)
    )

and
(* ..................................................................*)
ruleUnary op expr env =
  (match op with
  | Negate ->
    let texpr = (type_of_expr expr env) in
    let ute,ure = unify texpr IntegerType in
      (if (ure) then IntegerType else ErrorType)
  (* | _ -> ErrorType *)
      )

and
(* ..................................................................*)
ruleIf cond ethen eelse env =
  let tcond = (type_of_expr cond env) in
  let tthen = (type_of_expr ethen env) in
  let telse = (type_of_expr eelse env) in
  let utc,urc = unify tcond BooleanType in
  let ut,ur = unify tthen telse in
    (if (urc && ur) then ut else ErrorType)

and
(* ..................................................................*)
ruleLet ident bvalue bin env =
  let typeident = (type_of_expr bvalue env) in
(*    (print_endline ((string_of_ast bvalue) ^ " -> " ^ (string_of_type typeident)));
    (print_endline ((string_of_ast bin))); *)
    (type_of_expr bin ((ident,typeident)::env)) 

and
(* ..................................................................*)
ruleLetrec ident bvalue bin env =
  let typevar = newVariable () in
  let typeident = (type_of_expr bvalue ((ident,typevar)::env)) in
  let ut,ur = unify typevar typeident in
    (if (ur) then 
      (type_of_expr bin ((ident,typeident)::env))
    else 
       ErrorType)

and
(* ...............A COMPLETER .......................................*)
ruleFunction par body env = ErrorType

and 
(* ...............A COMPLETER .......................................*)
ruleCall fct par env = ErrorType

and
(* ...............A COMPLETER .......................................*)
ruleRef expr env = ErrorType

and
(* ...............A COMPLETER .......................................*)
ruleRead expr env = ErrorType

and
(* ...............A COMPLETER .......................................*)
ruleWrite left right env = ErrorType

and
(* ...............A COMPLETER .......................................*)
ruleSequence left right env = ErrorType

(* ...........fin des regles d'inference..........................................*)
;;
