(* Type des opérateurs binaires *)
type binary = 
  | Add 
  | Substract 
  | Or 
  | Multiply 
  | Divide 
  | And 
  | Equal 
  | Different 
  | Lesser 
  | LesserEqual 
  | Greater 
  | GreaterEqual
;;

(*Type des opérateurs unaires *)
type unary = 
  | Negate
;;

(*Type pour la construction de l'arbre abstrait (Abstract Syntaxic Tree) *)
type ast =
  | FunctionNode of string * ast
  | CallNode of ast * ast
  | IfthenelseNode of ast * ast * ast
  | LetNode of string * ast * ast
  | LetrecNode of string * ast * ast
  | RefNode of ast
  | ReadNode of ast
  | WriteNode of ast * ast
  | SequenceNode of ast * ast
  | AccessNode of string
  | IntegerNode of int
  | BinaryNode of binary * ast * ast
  | UnaryNode of unary * ast
  | TrueNode
  | FalseNode
  | UnitNode
;;

(* string_of_ast : ast -> string *)
(* Convertit un ast en une chaine de caractères en vue de son affichage *)
let rec string_of_ast tree =
  match tree with
    | (FunctionNode (par,body)) -> 
	("(fun " ^ par ^ " -> " ^ (string_of_ast body) ^ ")")
    | (CallNode (func,par)) -> 
	("(" ^ (string_of_ast func) ^ " " ^ (string_of_ast par) ^ ")") 
    | (IfthenelseNode (cond,bthen,belse)) -> 
	("(if " ^ (string_of_ast cond) ^ " then " ^ (string_of_ast bthen) ^ " else " ^ (string_of_ast belse) ^ ")")
    | (LetNode (id,blet,bin)) -> 
	("(let " ^ id ^ " = " ^ (string_of_ast blet) ^ " in " ^ (string_of_ast bin) ^ ")")
    | (LetrecNode (id,blet,bin)) -> 
	("(let rec " ^ id ^ " = " ^ (string_of_ast blet) ^ " in " ^ (string_of_ast bin) ^ ")")
    | (RefNode exp) -> ("(ref " ^ (string_of_ast exp) ^ ")")
    | (ReadNode exp) -> ("(! " ^ (string_of_ast exp) ^ ")")
    | (WriteNode (id,exp)) -> 
	("(" ^ (string_of_ast id) ^ " := " ^ (string_of_ast exp) ^ ")")
    | (SequenceNode (exp,exp2)) -> ("( " ^ (string_of_ast exp) ^ "; " ^ (string_of_ast exp2) ^ ")")
    | (AccessNode name) -> name
    | (IntegerNode value) -> (string_of_int value) 
    | (BinaryNode (op,left,right)) -> 
	( "(" ^ (string_of_ast left) ^
	    (match op with
	       | Equal -> " = "
	       | Different -> " != "
	       | Lesser -> " < "
	       | Greater -> " > "
	       | LesserEqual -> " <= "
	       | GreaterEqual -> " => "
	       | Add -> " + "
	       | Substract -> " - "
	       | Or -> " || "
	       | Multiply -> " * "
	       | Divide -> " / "
	       | And -> " && "
	    ) ^ (string_of_ast right) ^ ")" ) 
    | (UnaryNode (op,expr)) -> 
	( "(" ^ 
	    (match op with
	       | Negate -> "- "
            ) ^ (string_of_ast expr) ^ ")" )
    | TrueNode  -> ( "true")
    | FalseNode  -> ( "false")
    | UnitNode  -> ( "()")
;;    

