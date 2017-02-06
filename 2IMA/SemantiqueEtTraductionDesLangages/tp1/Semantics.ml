(* Analyseur sémantique *)

open Ast

(* ========================================================*)
(* Définition du type des erreurs *)
type errorType =
  | UnknownIdentError of string
  | TypeMismatchError
  | RuntimeError
  | UndefinedExpressionError
;;

(* ========================================================*)
(* Définition du type des valeurs renvoyées par l'interprète *)
type valueType =
  | FrozenValue of (ast * environment)
  | IntegerValue of int
  | BooleanValue of bool
  | ErrorValue of errorType
and environment = (string * valueType) list
;;

(* ========================================================*)
(* string_of_names : string list -> string *)
(* Converti une liste de chaînes de caractères en une seule chaîne de caractères *)
let string_of_names names =
	List.fold_right (fun t tq -> t ^ " " ^ tq ) names "";;

(* string_of_env : environment -> string *)
(* Convertit un environnement en une chaine de caractères en vue de son affichage *)
let rec string_of_env env =
  match env with
  | [] -> ""
  | (key,value)::q -> (key ^ "," ^ (string_of_value value)) ^ ";" ^ (string_of_env q)
(* string_of_value : valueType -> string *)
(* Convertit une valueType en une chaine de caractères en vue de son affichage *)
and string_of_value value =
  match value with
  | (FrozenValue (expr,env)) -> ((string_of_ast expr) ^ (string_of_env env))
  | (IntegerValue value) -> (string_of_int value)
  | (BooleanValue value) -> (if (value) then "true" else "false")
  | (ErrorValue error) -> (string_of_error error)
(* string_of_error : errorType -> string *)
(* Convertit une erreur en une chaine de caractères en vue de son affichage *)
and string_of_error error =
  match error with
  | (UnknownIdentError name) -> "Unknown ident : " ^ name
  | RuntimeError -> "Runtime error"
  | TypeMismatchError -> "Type mismatch"
  | UndefinedExpressionError -> "Undefined expression error"
;;

(* ========================================================*)
type 'a searchResult = 
  | NotFound 
  | Found of 'a
;;

(* lookfor : string -> environment -> valueType searchResult *)
(* Cherche un identifiant dans un environnement et renvoie la valeur associée le cas échéant *)
let rec lookfor name env =
  match env with
  | [] -> NotFound
  | (key,value) :: others ->
    (if (key = name) then (Found value) else (lookfor name others))
;;  

(* ========================================================*)
(* value_of_expr : ast -> environment -> valueType *)
(* Fonction d'évaluation des expressions *)
let rec value_of_expr expr env =
  match expr with
    | (FunctionNode (_,_)) -> ruleFunction expr env
    | (CallNode (fexpr,pexpr)) -> ruleCallByValue env fexpr pexpr
    (*| (CallNode (fexpr,pexpr)) -> ruleCallByName env fexpr pexpr *) 
    | (IfthenelseNode (cond,bthen,belse)) -> ruleIf env cond bthen belse
    | (LetNode (ident,bvalue,bin)) -> ruleLet env ident bvalue bin
    | (LetrecNode (ident,bvalue,bin)) -> ruleLetrec env ident bvalue bin 
    | (AccessNode name) -> ruleName env name
    | (IntegerNode value) -> ruleInteger value
    | (TrueNode) ->  ruleTrue 
    | (FalseNode) ->  ruleFalse 
    | (BinaryNode (op,left,right)) -> ruleBinary env op left right
    | (UnaryNode (op,expr)) -> ruleUnary env op expr
    | _ -> ErrorValue UndefinedExpressionError (* les expressions avec effets de bord *)

(* ========================================================*)
and 
(* ruleName : environment -> string -> valueType *)
(* Fonction d'évaluation d'un identificateur *)
ruleName env name = 
  match (lookfor name env) with
  | NotFound -> (ErrorValue (UnknownIdentError name))
(* A completer*)
  | (Found value) -> value

(* ========================================================*)
and 
(* ruleLet : environment -> string -> ast- > ast -> valueType *)
(* Fonction d'évaluation d'un let *)
(* "let ident = bvalue in bin" *)
ruleLet env ident bvalue bin = 
(* A traiter*)
     (ErrorValue UndefinedExpressionError)
(* ========================================================*)
and 
(* ruleBinary : environment -> binary -> ast- > ast -> valueType *)
(* Fonction d'évaluation d'un opérateur binaire *)
ruleBinary env op left right = 
  let leftvalue = 
    (value_of_expr left env) 
  in
    (match leftvalue with
    | (ErrorValue _) as result -> result
    | _ ->
      (let rightvalue = 
         (value_of_expr right env) 
       in
         (match rightvalue with
         | (ErrorValue _) as result -> result
         | _ ->
           (match (leftvalue,rightvalue) with 
           | ((IntegerValue leftvalue), (IntegerValue rightvalue)) ->
             (match op with
             | Equal -> (BooleanValue (leftvalue = rightvalue))
             | Different -> (BooleanValue (leftvalue <> rightvalue))
             | Lesser -> (BooleanValue (leftvalue < rightvalue))
             | LesserEqual -> (BooleanValue (leftvalue <= rightvalue))
             | Greater -> (BooleanValue (leftvalue > rightvalue))
             | GreaterEqual -> (BooleanValue (leftvalue >= rightvalue))
             | Add -> (IntegerValue (leftvalue + rightvalue))
             | Substract -> (IntegerValue (leftvalue - rightvalue))
             | Multiply -> (IntegerValue (leftvalue * rightvalue))
             | Divide -> 
                 (if (rightvalue = 0) then 
                    (ErrorValue RuntimeError)
                  else 
                    (IntegerValue (leftvalue / rightvalue)))
             | _ -> (ErrorValue TypeMismatchError))
             | ((BooleanValue leftvalue), (BooleanValue rightvalue)) -> 
               (match op with
                  | Or -> (BooleanValue (leftvalue || rightvalue))
                  | And -> (BooleanValue (leftvalue && rightvalue))
                  | _ -> (ErrorValue TypeMismatchError))
             | _ -> (ErrorValue TypeMismatchError)))))

(* ========================================================*)
and 
(* ruleUnary : environment -> unary -> ast- > valueType *)
(* Fonction d'évaluation d'un opérateur unaire *)
ruleUnary env op exp =
  let value =  
    (value_of_expr exp env) 
  in
    match value with
    | (ErrorValue _) as result -> result
    | (IntegerValue value) ->
      (match op with
      | Negate -> (IntegerValue (- value)))
    | _ -> (ErrorValue TypeMismatchError)

(* ========================================================*)
and
(* ruleIf : environment -> ast -> ast -> ast- > valueType *)
(* Fonction d'évaluation d'une conditionnelle *)
(* "if cond then bthen else belse" *)
ruleIf env cond bthen belse = 
(* A traiter*)
     (ErrorValue UndefinedExpressionError)
(* ========================================================*)
and 
(* ruleFunction : ast -> environment -> valueType *)
(* Fonction d'évaluation d'une fonction *)
ruleFunction expr env = 
(* A traiter*)
     (ErrorValue UndefinedExpressionError)

(* Appel par nom *)
(* ========================================================*)
and
(* ruleCallByName : environment -> ast -> ast -> valueType *)
(* Fonction d'évaluation d'un appel de fonction avec passage de paramètre par nom*)
ruleCallByName env fexpr pexpr = 
(* A traiter*)
     (ErrorValue UndefinedExpressionError)
(* ========================================================*)
and
(* ruleCallByValue : environment -> ast -> ast -> valueType *)
(* Fonction d'évaluation d'un appel de fonction avec passage de paramètre par valeur*)
ruleCallByValue env fexpr pexpr = 
(* Appel par valeur *)
(* A traiter*)
     (ErrorValue UndefinedExpressionError)
(* ========================================================*)
and
(* ruleLetrec : environment -> string -> ast- > ast -> valueType *)
(* Fonction d'évaluation d'un let rec*)
(* "letrec ident = bvalue in bin" *)
ruleLetrec env ident bvalue bin = 
(* A traiter*)
     (ErrorValue UndefinedExpressionError)
(* ========================================================*)
and
(* ruleTrue : valueType *)
(* Fonction d'évaluation de true *)
ruleTrue = (BooleanValue true)

(* ========================================================*)
and
(* ruleFalse : valueType *)
(* Fonction d'évaluation de false *)
ruleFalse = (BooleanValue false)

(* ========================================================*)
and 
(* ruleInteger : int -> valueType *)
(* Fonction d'évaluation d'un entier *)
ruleInteger value = (IntegerValue value)
;;
