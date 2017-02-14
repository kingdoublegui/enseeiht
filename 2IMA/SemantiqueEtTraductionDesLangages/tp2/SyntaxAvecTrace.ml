open Lex
open List
open Ast

type attributes =
  | Ident of string
  | Number of int
  | BinaryOperator of binary
  | Ast of ast
  | None
;;

type parseResult =
  | Success of inputStream * attributes
  | Failure
;;

(* accept a token *)
let accept expected stream =
  match stream with
  | token :: next when (token = expected) -> 
    (print_endline ("Expected " ^ (string_of_token expected)));
    (print_endline ("Found " ^ (string_of_token token)));
    (Success (next,None))
  | _ -> Failure

(* accept a special token : ident*)
and acceptIdent stream =
    match stream with
      | (IdentToken ident)::next -> (Success (next,(Ident ident)))
      | _ -> Failure 

and 
(* accept a special token : number *)
acceptNumber stream =
    match stream with
      | (NumberToken number)::next -> (Success (next,(Number number)))
      | _ -> Failure

;;
(*  #1  - E -> function ident -> E     *)
let rec rule1 stream =
  (print_endline "#1 ");
  
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
  (print_endline "#2 ");
  
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
  (print_endline "#3 ");
  
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
  (print_endline "#4 ");
  
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
(*  #28  - E -> ES EZ      *)
rule28 stream =
  (print_endline "#28 ");
  (match (parseES stream) with
  | Success (next,(Ast expr)) ->
    (match (parseEZ expr next) with
    | Success (next, (Ast expr)) ->
      Success (next, (Ast expr))
    | _ -> Failure) 
  | _ -> Failure)
    
and 
parseE stream =
  (print_endline "E ");
  match stream with
  | FunctionToken :: _  -> rule1 stream 
  | LetToken      :: _  -> rule2 stream 
  | RecToken      :: _  -> rule3 stream
  | IfToken       :: _  -> rule4 stream
  | ((IdentToken _) | (NumberToken _) | TrueToken | FalseToken 
    | MinusToken | LeftParenthesisToken |ReadToken | RefToken )
                  :: _  -> rule28 stream
  | _                   -> Failure

and
(*  #32  - EZ -> sequence E *)
rule32 stream lhs =
  (print_endline "#32 ");
  
  match (accept SequenceToken stream) with
  | Success (next,None) ->
    (match (parseE next) with
    | Success (next,(Ast rhs)) -> 
      Success (next, (Ast (SequenceNode (lhs,rhs)))) 
    | _ -> Failure)
  | _ -> Failure

  
and 
(*  #33  - EZ ->       *)
rule33 stream lhs = 
  (print_endline "#33 ");
Success (stream,(Ast lhs))
 
and 
parseEZ lhs stream =
  (print_endline "EZ ");
  match stream with
  | SequenceToken  :: _  -> rule32 stream lhs
  | (RightParenthesisToken | InToken 
    | ThenToken | ElseToken) :: _  -> rule33 stream lhs
  | []                  -> rule33 stream lhs
  | _                   -> Failure
    
and
(* #34   ES -> EA EY *)
rule34 stream =
  (print_endline "#34 ");
  match (parseEA stream) with
  | Success (next,(Ast expr)) ->
    (match (parseEY expr next) with
    | Success (next, (Ast expr)) ->
      Success (next, (Ast expr))
    | _ -> Failure) 
  | _ -> Failure
    
and 
parseES stream =
  (print_endline "ES ");
  match stream with
  | ((IdentToken _) | (NumberToken _) | TrueToken 
    | FalseToken | MinusToken | LeftParenthesisToken
     |ReadToken | RefToken  ) 
                      :: _  ->  rule34 stream
  | _                       -> Failure
    

and 
(*  #29  - EY -> := EA    *)
rule29 stream lhs =
  (print_endline "#29 ");
  
  match (accept WriteToken stream) with
  | Success (next,None) ->
    (match (parseEA next) with
    | Success (next,(Ast rhs)) -> 
      Success (next, (Ast (WriteNode (lhs,rhs)))) 
    | _ -> Failure)
  | _ -> Failure


(*  #30  - EY ->       *)
and 
rule30 stream lhs = 
  (print_endline "#30 ");
Success (stream,(Ast lhs))

and 
parseEY lhs stream =
  (print_endline "EY ");
  match stream with
  | WriteToken :: _  -> rule29 stream lhs
  | (SequenceToken | RightParenthesisToken | InToken 
    | ThenToken | ElseToken) :: _ -> rule30 stream lhs 
  | []                  -> rule30 stream lhs
  | _                   -> Failure
    
and
(*  #5  - EA -> ER EX      *)
rule5 stream =
  (print_endline "#5 ");
  
  match (parseER stream) with
  | Success (next,(Ast expr)) ->
    (match (parseEX expr next) with
    | Success (next, (Ast expr)) ->
      Success (next, (Ast expr))
    | _ -> Failure) 
  | _ -> Failure
    
and 
parseEA stream =
  (print_endline "EA ");
  match stream with
  | ((IdentToken _) | (NumberToken _) | TrueToken 
    | FalseToken | MinusToken | LeftParenthesisToken
     |ReadToken | RefToken  ) 
                      :: _  ->  rule5 stream
  | _                       -> Failure
    
and 
(*  #6  - EX -> RBO ER      *)
rule6 stream lhs =
  (print_endline "#6 ");
  
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
rule7 stream lhs = 
  (print_endline "#7 ");
Success (stream,(Ast lhs))
  
and
(*  #8  - RBO -> = | > | >= | < | <= | !=     *)
parseRBO stream =
  (print_endline "#8 ");
  match stream with
  | EqualToken :: _ ->
    (match (accept EqualToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator Equal))
    | _ -> Failure)
  | DifferentToken :: _ ->
    (match (accept DifferentToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator Different))
    | _ -> Failure)
  | LesserToken :: _ ->
    (match (accept LesserToken stream) with
    | (Success (next,None)) ->
       Success (next,(BinaryOperator Lesser))
    | _ -> Failure)
  | LesserEqualToken ::_ ->
    (match (accept LesserEqualToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator LesserEqual))
    | _ -> Failure)
  | GreaterToken :: _ ->
    (match (accept GreaterToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator Greater))
    | _ -> Failure)
  | GreaterEqualToken :: _ ->
    (match (accept GreaterEqualToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator GreaterEqual))
    | _ -> Failure)
  | _ -> Failure


and 
parseEX lhs stream =
  (print_endline "EX ");
  match stream with
  | (EqualToken | DifferentToken | LesserToken | LesserEqualToken 
    | GreaterToken | GreaterEqualToken) 
                   :: _  -> rule6 stream lhs
  | (RightParenthesisToken | InToken | ThenToken | ElseToken
     | WriteToken | SequenceToken ) 
                   :: _  -> rule7 stream lhs
  | []                  -> rule7 stream lhs
  | _                   -> Failure
    
and 
(*  #9  - ER -> T TX      *)
rule9 stream =
  (print_endline "#9 ");
  
  match (parseT stream) with
  | Success (next,(Ast lhs)) -> 
    (match (parseTX lhs next) with
    | Success (next,(Ast expr)) -> 
      Success (next,(Ast expr))
    | _ -> Failure) 
  | _ -> Failure

and 
parseER stream =
  (print_endline "ER ");
  match stream with
  | ((IdentToken _) | (NumberToken _) | TrueToken 
    | FalseToken | MinusToken | LeftParenthesisToken
     |ReadToken | RefToken  ) 
                      :: _  ->  rule9 stream
  | _                       -> Failure
    
and 
(*  #10 - TX -> ABO T TX      *)
rule10 stream lhs =
  (print_endline "#10 ");
  
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
rule11 stream lhs = 
  (print_endline "#11 ");
Success (stream,(Ast lhs))
  
and 
(*  #12 - ABO ->  + | - | ||     *)
parseABO stream =
  (print_endline "#12 ");
  match stream with
  | PlusToken :: _ ->
    (match (accept PlusToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator Add))
    | _ -> Failure)
  | MinusToken :: _ ->
    (match (accept MinusToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator Substract))
    | _ -> Failure)
  | OrToken :: _ ->
    (match (accept OrToken stream) with
    | (Success (next,None)) ->
      Success (next,(BinaryOperator Or))
    | _ -> Failure)
  | _ -> Failure

and 
parseTX lhs stream =
  (print_endline "TX ");
  match stream with
  | ( PlusToken | MinusToken | OrToken ) 
                      :: _  -> rule10 stream lhs
  | (RightParenthesisToken | InToken | ThenToken 
    | ElseToken | EqualToken | DifferentToken 
    | LesserToken | LesserEqualToken 
    | GreaterToken | GreaterEqualToken
    | WriteToken | SequenceToken) 
                      :: _  -> rule11 stream lhs
  | []                      -> rule11 stream lhs
  | _                       -> Failure
    

and
(*  #13 - T -> F FX      *)
rule13 stream = 
  (print_endline "#13 ");
  
  match (parseF stream) with
  | Success (next,(Ast lhs)) -> 
    (match (parseFX lhs next) with
    | Success (next,(Ast expr)) ->
      Success (next,(Ast expr))
    | _ -> Failure)
  | _ -> Failure

and 
parseT stream =
  (print_endline "T ");
  match stream with 
   | ((IdentToken _) | (NumberToken _) | TrueToken 
     | FalseToken | MinusToken | LeftParenthesisToken
      |ReadToken | RefToken  ) 
                     :: _ -> rule13 stream
   | _ -> Failure

and
(*  #14 - FX -> MBO F FX      *)
rule14 stream lhs = 
  (print_endline "#14 ");
  
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
rule15 stream lhs = 
  (print_endline "#15 ");
Success (stream,(Ast lhs))
  
and 
(*  #16 - MBO -> * | / | &&     *)
parseMBO stream =
  (print_endline "#16 ");
  match stream with
  | StarToken :: _ ->
    (match (accept StarToken stream) with
    | (Success (next,None)) ->
       Success (next,(BinaryOperator Multiply))
    | _ -> Failure)
  | SlashToken :: _ ->
    (match (accept SlashToken stream) with
    | (Success (next,None)) ->
        Success (next,(BinaryOperator Divide))
    | _ -> Failure)
  | AndToken :: _ ->
    (match (accept AndToken stream) with
    | (Success (next,None)) ->
       Success (next,(BinaryOperator And))
    | _ -> Failure)
  | _ -> Failure

and 
parseFX lhs stream =
  (print_endline "FX ");
  match stream with
  | (StarToken | SlashToken | AndToken) :: _ -> rule14 stream lhs
  | (PlusToken | MinusToken | RightParenthesisToken 
    | InToken | ThenToken | ElseToken | EqualToken 
    | DifferentToken | LesserToken | LesserEqualToken 
    | GreaterToken | GreaterEqualToken
     | WriteToken | SequenceToken ) 
                     :: _  -> rule15 stream lhs
  | []                  -> rule15 stream lhs
  | _                   -> Failure


and 
(*  #17 - F -> - F      *)
rule17 stream = 
  (print_endline "#17 ");
  
  match (accept MinusToken stream) with
  | Success (next,None) ->
    (match (parseF next) with
    | Success (next,(Ast expr))->
      Success (next,(Ast (UnaryNode (Negate,expr))))
    | _ -> Failure)
  | _ -> Failure

and
(*  #26 - F -> ! F      *)
rule26 stream = 
  (print_endline "#26 ");
  
  match (accept ReadToken stream) with
  | Success (next,None) ->
    (match (parseF next) with
    | Success (next,(Ast expr))->
      Success (next,(Ast (ReadNode expr)))
    | _ -> Failure)
  | _ -> Failure

and 
(*  #27 - F -> ref F      *)
rule27 stream = 
  (print_endline "#27 ");
  
  match (accept RefToken stream) with
  | Success (next,None) ->
    (match (parseF next) with
    | Success (next,(Ast expr))->
      Success (next,(Ast (RefNode expr)))
    | _ -> Failure)
  | _ -> Failure

and 
(*  #18 - F -> number      *)
rule18 stream = 
  (print_endline "#18 ");
  
  match (acceptNumber stream) with
  | Success (next,(Number number)) ->
    Success (next,(Ast (IntegerNode number)))
  | _ -> Failure

and 
(*  #19 - F -> FF ARG      *)
rule19 stream = 
  (print_endline "#19 ");
  
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
  (print_endline "#20 ");
  
  match (accept TrueToken stream) with
  | Success (next,None) ->
    Success (next,(Ast TrueNode))
  | _ -> Failure

and 
(*  #21 - F -> false     *)
rule21 stream = 
  (print_endline "#21 ");
  
  match (accept FalseToken stream) with
  | Success (next,None) ->
    Success (next,(Ast FalseNode))
  | _ -> Failure

and 
parseF stream =
  (print_endline "F ");
  match stream with
  | MinusToken :: _  -> rule17 stream
  | (NumberToken value) :: _ ->  rule18 stream
  | ((IdentToken _) | LeftParenthesisToken
  | WriteToken | SequenceToken ) :: _ -> rule19 stream
  | TrueToken  :: _ -> rule20 stream
  | FalseToken  :: _ -> rule21 stream
  | ReadToken :: _ -> rule26 stream
  | RefToken :: _ -> rule27 stream
  | _ -> Failure

and
(*  #22 - FF -> ( E )     *)
rule22 stream = 
  (print_endline "#22 ");
  
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
  (print_endline "#23 ");
  
  match (acceptIdent stream) with
  | Success (next,(Ident ident)) ->
    Success (next,(Ast (AccessNode ident)))
  | _ -> Failure

and parseFF stream =
  (print_endline "FF ");
  match stream with
  | LeftParenthesisToken :: _  -> rule22 stream
  | (IdentToken name) :: _ -> rule23 stream
  | _ -> Failure

and
(*  #24 - ARG -> ( E )     *)
rule24 stream lhs = 
  (print_endline "#24 ");
  
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
rule25 stream lhs = 
  (print_endline "#25 ");
Success (stream,(Ast lhs))
  
and 
parseARG lhs stream =
  (print_endline "ARG ");
  match stream with
  | LeftParenthesisToken :: _  ->  rule24 stream lhs
    | (StarToken | SlashToken | PlusToken | MinusToken 
    | RightParenthesisToken | InToken | ThenToken 
    | ElseToken | EqualToken | DifferentToken 
    | LesserToken | LesserEqualToken | GreaterToken 
    | GreaterEqualToken | WriteToken 
    | ReadToken | SequenceToken ) :: _ -> rule25 stream lhs
  | [] -> rule25 stream lhs
  | _ -> Failure


;;
