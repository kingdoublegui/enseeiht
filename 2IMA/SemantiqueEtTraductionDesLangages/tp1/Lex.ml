(* Type des token généré par l'analyseur lexical *)
type token = 
  | EOSToken
  | OrToken
  | AndToken
  | FunctionToken
  | BodyToken
  | IfToken
  | ThenToken
  | ElseToken
  | LetToken
  | InToken
  | TrueToken
  | FalseToken
  | RecToken
  | IdentToken of string
  | NumberToken of int
  | EqualToken
  | DifferentToken
  | LesserToken
  | GreaterToken
  | LesserEqualToken
  | GreaterEqualToken
  | PlusToken
  | MinusToken
  | StarToken
  | SlashToken
  | LeftParenthesisToken
  | RightParenthesisToken;;

(* Type des données en sortie de l'analyseur lexical / entrée de l'analyseur syntaxique *)
type inputStream = token list;;

(* string_of_token : token -> trsing *)
(* Convertit un token en une chaine de caractères en vue de son affichage *)
let string_of_token token =
  match token with 
  | EOSToken -> "$"
  | FunctionToken -> "function"
  | BodyToken -> "->"
  | IfToken -> "if"
  | ThenToken -> "then"
  | ElseToken -> "else"
  | LetToken -> "let"
  | InToken -> "in"
  | TrueToken -> "true"
  | FalseToken -> "false"
  | RecToken -> "letrec"
  | NumberToken xxx  -> string_of_int xxx
  | IdentToken  name -> name
  | EqualToken -> "="
  | DifferentToken -> "!="
  | LesserToken -> "<"
  | GreaterToken -> ">"
  | LesserEqualToken -> "<="
  | GreaterEqualToken -> ">="
  | PlusToken -> "+"
  | MinusToken -> "-"
  | StarToken -> "*"
  | SlashToken -> "/"
  | AndToken -> "&&"
  | OrToken -> "||"
  | LeftParenthesisToken -> "("
  | RightParenthesisToken -> ")"
  ;;

(* string_of_stream : inputStream -> string *)
(* Converti un inputStream (liste de token) en une chaîne de caractère *)
let string_of_stream stream =
  List.fold_right (fun t tq -> (string_of_token t) ^ " " ^ tq ) stream "";;
