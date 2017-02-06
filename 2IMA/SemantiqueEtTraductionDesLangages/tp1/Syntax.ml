(* Analayseur syntaxique -> cf dernier TP d'OMI *)

open Lex
open List
open Ast

(* ========================================================*)
type attributes =
  | Ident of string
  | Number of int
  | BinaryOperator of binary
  | Ast of ast
  | None
;;

(* ========================================================*)

type parseResult =
  | Success of inputStream * attributes
  | Failure
;;
(* ========================================================*)


(* accept : token -> inputStream -> parseResult *)
(* Vérifie que le premier token du flux d'entrée est bien le token attendu *)
(* et avance dans l'analyse si c'est le cas *)
let accept expected stream =
  match (hd stream) with
  | token when (token = expected) -> (Success ((tl stream),None))
  | _ -> Failure
;;

(* acceptIdent : inputStream -> parseResult *)
(* Vérifie que le premier token du flux d'entrée est bien un identifiant *)
(* et avance dans l'analyse si c'est le cas *)
let acceptIdent stream =
    match (hd stream) with
      | (IdentToken ident) -> (Success ((tl stream),(Ident ident)))
      | _ -> Failure 
;;

(* acceptNumber : inputStream -> parseResult *)
(* Vérifie que le premier token du flux d'entrée est bien un nombre *)
(* et avance dans l'analyse si c'est le cas *)
let acceptNumber stream =
    match (hd stream) with
      | (NumberToken number) -> (Success ((tl stream),(Number number)))
      | _ -> Failure
;;
(* ========================================================*)
(*  #1  - E -> fun ident -> E     *)
let rec rule1 stream =
  match (accept FunctionToken stream) with
  | Success (next,None) ->
    (match (acceptIdent next) with
    | Success (next,(Ident param)) ->
      (match (accept BodyToken next) with
      | Success (next,None) ->
        (match (parseE next) with
        | Success (next,(Ast body)) ->
          Success (next,(Ast (FunctionNode (param,body))))
        | _ -> Failure) 
      | _ -> Failure) 
    | _ -> Failure) 
  | _ -> Failure

and 
(*  #2  - E -> let ident = E in E     *)
rule2 stream =
  match (accept LetToken stream) with
  | Success (next,None) ->
    (match (acceptIdent next) with
    | Success (next,(Ident name)) ->
      (match (accept EqualToken next) with
      | Success (next,None) ->
        (match (parseE next) with
        | Success (next,(Ast value)) ->
          (match (accept InToken next) with
          | Success (next,None) ->
            (match (parseE next) with
            | Success (next,(Ast body)) -> 
               Success (next,(Ast (LetNode (name,value,body))))
            | _ -> Failure) 
          | _ -> Failure) 
        | _ -> Failure) 
      | _ -> Failure) 
    | _ -> Failure) 
  | _ -> Failure

and 
(*  #3  - E -> letrec ident = E in E     *)
rule3 stream =
  match (accept RecToken stream) with
  | Success (next,None) ->
    (match (acceptIdent next) with
    | Success (next,(Ident name)) ->
      (match (accept EqualToken next) with
      | Success (next,None) ->
        (match (parseE next) with
        | Success (next,(Ast value)) ->
          (match (accept InToken next) with
          | Success (next,None) ->
            (match (parseE next) with
            | Success (next,(Ast body)) -> 
              Success (next,(Ast (LetrecNode (name,value,body))))
            | _ -> Failure) 
          | _ -> Failure) 
        | _ -> Failure) 
      | _ -> Failure) 
    | _ -> Failure) 
  | _ -> Failure

and 
(*  #4  - E -> if E then E else E     *)
rule4 stream =
  match (accept IfToken stream) with
  | Success (next,None) ->
    (match (parseE next) with
    | Success (next,(Ast cond)) ->
      (match (accept ThenToken next) with
      | Success (next,None) ->
        (match (parseE next) with
        | Success (next,(Ast ethen)) ->
          (match (accept ElseToken next) with
          | Success (next,None) ->
            (match (parseE next) with
            | Success (next,(Ast eelse)) -> 
              Success (next,(Ast (IfthenelseNode (cond,ethen,eelse)))) 
            | _ -> Failure) 
          | _ -> Failure) 
        | _ -> Failure) 
      | _ -> Failure) 
    | _ -> Failure)
  | _ -> Failure
     
and 
(*  #5  - E -> ER EX      *)
rule5 stream =
  match (parseER stream) with
  | Success (next,(Ast expr)) ->
    (match (parseEX expr next) with
    | Success (next, (Ast expr)) ->
      Success (next, (Ast expr))
    | _ -> Failure) 
  | _ -> Failure
    
and 
(* parseE : inputStream -> parseResult *)
(* Analyse du non terminal E *)
parseE stream =
  match (hd stream) with
  | FunctionToken -> rule1 stream 
  | LetToken      -> rule2 stream 
  | RecToken      -> rule3 stream
  | IfToken       -> rule4 stream
  | ((IdentToken _) | (NumberToken _) | TrueToken | FalseToken 
    | MinusToken | LeftParenthesisToken )
                  -> rule5 stream
  | _             -> Failure

(* ========================================================*)
and 
(*  #6  - EX -> RBO ER      *)
rule6 stream lhs =
  match (parseRBO stream) with
  | Success (next,(BinaryOperator op)) ->
    (match (parseER next) with
    | Success (next,(Ast rhs)) ->
      Success (next, (Ast (BinaryNode (op,lhs,rhs))))
(*
    | Success (next,(Ast rhs)) ->
      (match (parseEX (BinaryNode (op,lhs,rhs)) next) with
      | Success (next,expr) -> Success (next,expr)
      | _ -> Failure) 
*)
    | _ -> Failure) 
  | _ -> Failure
     
and 
(*  #7  - EX ->      *)
rule7 stream lhs = Success (stream,(Ast lhs))

and
(*  #8  - RBO -> = | > | >= | < | <= | !=     *)
parseRBO stream =
  match (hd stream) with
  | EqualToken  ->
    (match (accept EqualToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Equal))
    | _ -> Failure)
  | DifferentToken  ->
    (match (accept DifferentToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Different))
    | _ -> Failure)
  | LesserToken  ->
    (match (accept LesserToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Lesser))
    | _ -> Failure)
  | LesserEqualToken ->
    (match (accept LesserEqualToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator LesserEqual))
    | _ -> Failure)
  | GreaterToken  ->
    (match (accept GreaterToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Greater))
    | _ -> Failure)
  | GreaterEqualToken  ->
    (match (accept GreaterEqualToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator GreaterEqual))
    | _ -> Failure)
  | _ -> Failure

and 
(* parseEX : inputStream -> parseResult *)
(* Analyse du non terminal EX *)
parseEX lhs stream =
  match (hd stream) with
  | (EqualToken | DifferentToken | LesserToken | LesserEqualToken 
    | GreaterToken | GreaterEqualToken) 
                     -> rule6 stream lhs
  | (EOSToken | RightParenthesisToken | InToken | ThenToken | ElseToken) 
                     -> rule7 stream lhs
  | _                   -> Failure
    
(* ========================================================*)
and 
(*  #9  - ER -> T TX      *)
rule9 stream =
  match (parseT stream) with
  | Success (next,(Ast lhs)) -> 
    (match (parseTX lhs next) with
    | Success (next,(Ast expr)) -> 
      Success (next,(Ast expr))
    | _ -> Failure) 
  | _ -> Failure

and 
parseER stream =
  match (hd stream) with
  | ((IdentToken _) | (NumberToken _) | TrueToken 
    | FalseToken | MinusToken | LeftParenthesisToken ) 
                        ->  rule9 stream
  | _                       -> Failure
    
(* ========================================================*)
and 
(*  #10 - TX -> ABO T TX      *)
rule10 stream lhs =
  match (parseABO stream) with
  | Success (next,(BinaryOperator op)) ->
    (match (parseT next) with
    | Success (next,(Ast rhs)) -> 
      (match (parseTX (BinaryNode (op,lhs,rhs)) next) with
      | Success (next,(Ast expr)) ->
        Success (next,(Ast expr))
      | _ -> Failure)
    | _ -> Failure)
  | _ -> Failure

and 
(*  #11 - TX ->      *)
rule11 stream lhs = Success (stream,(Ast lhs))

and 
(*  #12 - ABO ->  + | - | ||     *)
parseABO stream =
  match (hd stream) with
  | PlusToken  ->
    (match (accept PlusToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Add))
    | _ -> Failure)
  | MinusToken  ->
    (match (accept MinusToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Substract))
    | _ -> Failure)
  | OrToken  ->
    (match (accept OrToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Or))
    | _ -> Failure)
  | _ -> Failure

and 
(* parseTX : inputStream -> parseResult *)
(* Analyse du non terminal TX *)
parseTX lhs stream =
  match (hd stream) with
  | ( PlusToken | MinusToken | OrToken ) 
                        -> rule10 stream lhs
  | (EOSToken | RightParenthesisToken | InToken | ThenToken 
    | ElseToken | EqualToken | DifferentToken 
    | LesserToken | LesserEqualToken 
    | GreaterToken | GreaterEqualToken) 
                        -> rule11 stream lhs
  | _                       -> Failure
    
(* ========================================================*)
and
(*  #13 - T -> F FX      *)
rule13 stream = 
  match (parseF stream) with
  | Success (next,(Ast lhs)) -> 
    (match (parseFX lhs next) with
    | Success (next,(Ast expr)) ->
      Success (next,(Ast expr))
    | _ -> Failure)
  | _ -> Failure

and 
(* parseT : inputStream -> parseResult *)
(* Analyse du non terminal T *)
parseT stream =
  match (hd stream) with 
   | ((IdentToken _) | (NumberToken _) | TrueToken 
     | FalseToken | MinusToken | LeftParenthesisToken ) 
                      -> rule13 stream
   | _ -> Failure

(* ========================================================*)
and
(*  #14 - FX -> MBO F FX      *)
rule14 stream lhs = 
  match (parseMBO stream) with
  | Success (next,(BinaryOperator op)) ->
    (match (parseF next) with
    | Success (next,(Ast rhs)) -> 
      (match (parseFX (BinaryNode (op,lhs,rhs)) next) with
      | Success (next,(Ast expr)) ->
        Success (next,(Ast expr))
      | _ -> Failure)
    | _ -> Failure)
  | _ -> Failure

and 
(*  #15 - FX ->      *)
rule15 stream lhs = Success (stream,(Ast lhs))

and 
(*  #16 - MBO -> * | / | &&     *)
parseMBO stream =
  match (hd stream) with
  | StarToken  ->
    (match (accept StarToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Multiply))
    | _ -> Failure)
  | SlashToken  ->
    (match (accept SlashToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator Divide))
    | _ -> Failure)
  | AndToken  ->
    (match (accept AndToken stream) with
    | (Success (next,None)) -> Success (next,(BinaryOperator And))
    | _ -> Failure)
  | _ -> Failure

and 
(* parseFX : inputStream -> parseResult *)
(* Analyse du non terminal FX *)
parseFX lhs stream =
  match (hd stream) with
  | (StarToken | SlashToken | AndToken )  -> rule14 stream lhs
  | (EOSToken | PlusToken | MinusToken |OrToken | RightParenthesisToken 
    | InToken | ThenToken | ElseToken | EqualToken 
    | DifferentToken | LesserToken | LesserEqualToken 
    | GreaterToken | GreaterEqualToken ) 
                       -> rule15 stream lhs
  | _                   -> Failure

(* ========================================================*)
and 
(*  #17 - F -> - F      *)
rule17 stream = 
  match (accept MinusToken stream) with
  | Success (next,None) ->
    (match (parseF next) with
    | Success (next,(Ast expr))->
      Success (next,(Ast (UnaryNode (Negate,expr))))
    | _ -> Failure)
  | _ -> Failure

and 
(*  #18 - F -> number      *)
rule18 stream = 
  match (acceptNumber stream) with
  | Success (next,(Number number)) ->
    Success (next,(Ast (IntegerNode number)))
  | _ -> Failure

and 
(*  #19 - F -> FF ARG      *)
rule19 stream = 
  match (parseFF stream) with
  | Success (next,(Ast lhs)) -> 
    (match (parseARG lhs next) with
    | Success (next,(Ast expr)) ->
      Success (next,(Ast expr))
    | _ -> Failure)
  | _ -> Failure

and 
(*  #20 - F -> true     *)
rule20 stream = 
  match (accept TrueToken stream) with
  | Success (next,None) ->
    Success (next,(Ast TrueNode))
  | _ -> Failure

and 
(*  #21 - F -> false     *)
rule21 stream = 
  match (accept FalseToken stream) with
  | Success (next,None) ->
    Success (next,(Ast FalseNode))
  | _ -> Failure

and 
(* parseF : inputStream -> parseResult *)
(* Analyse du non terminal F *)
parseF stream =
  match (hd stream) with
  | MinusToken   -> rule17 stream
  | (NumberToken value)  ->  rule18 stream
  | ((IdentToken _) | LeftParenthesisToken )  -> rule19 stream
  | TrueToken   -> rule20 stream
  | FalseToken   -> rule21 stream
  | _ -> Failure

(* ========================================================*)
and
(*  #22 - FF -> ( E )     *)
rule22 stream = 
  match (accept LeftParenthesisToken stream) with
  | Success (next,None) ->
    (match (parseE next) with
    | Success (next,(Ast expr)) ->
      (match (accept RightParenthesisToken next) with
      | Success (next,None) ->
        Success (next,(Ast expr))
      | _ -> Failure)
    | _ -> Failure)
  | _ -> Failure

and 
(*  #23 - FF -> ident      *)
rule23 stream = 
  match (acceptIdent stream) with
  | Success (next,(Ident ident)) ->
    Success (next,(Ast (AccessNode ident)))
  | _ -> Failure

and 
(* parseFF : inputStream -> parseResult *)
(* Analyse du non terminal FF *)
parseFF stream =
  match (hd stream) with
  | LeftParenthesisToken   -> rule22 stream
  | (IdentToken name)  -> rule23 stream
  | _ -> Failure

(* ========================================================*)
and
(*  #24 - ARG -> ( E )     *)
rule24 stream lhs = 
  match (accept LeftParenthesisToken stream) with
  | Success (next,None) ->
    (match (parseE next) with
    | Success (next,(Ast rhs)) ->
      (match (accept RightParenthesisToken next) with
      | Success (next,None) ->
        Success (next,(Ast (CallNode (lhs,rhs))))
      | _ -> Failure)
    | _ -> Failure)
  | _ -> Failure

and 
(*  #25 - ARG ->      *)
rule25 stream lhs = Success (stream,(Ast lhs))

(* parseARG : inputStream -> parseResult *)
(* Analyse du non terminal ARG *)
and parseARG lhs stream =
  match (hd stream) with
  | LeftParenthesisToken   ->  rule24 stream lhs
    | (EOSToken |StarToken | SlashToken | PlusToken | MinusToken 
    | RightParenthesisToken | InToken | ThenToken 
    | ElseToken | EqualToken | DifferentToken 
    | LesserToken | LesserEqualToken | GreaterToken 
    | GreaterEqualToken )  -> rule25 stream lhs
  | _ -> Failure

(* ========================================================*)
;;
