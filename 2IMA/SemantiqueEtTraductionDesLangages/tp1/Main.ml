open Scanner 
open Syntax
open Ast 
open Semantics
open Lex

(* main : unit -> unit *)
(* Analyse le contenu d'un fichier passé en paramètre ou l'entrée standard si aucun fichier n'est donné *)
(* Affiche OK si l'analyse syntaxique c'est bien passée et KO sinon *)
(* Dans le cas où l'analyse syntaxique c'est bien passé, lance l'analyse sémantique avec un environement d'évaluation initial vide *)
let main () =
  let cin = 
    if Array.length Sys.argv > 1 then 
      open_in Sys.argv.(1)
    else  
      stdin
  in
    let lexbuf = Lexing.from_channel cin in
    match (parseE (scanner lexbuf))
     with
       | Success ([EOSToken],(Ast expr)) -> 
	   ((print_endline "Ok"); 
	    (print_endline (string_of_ast expr));
	    let env = [] in (* [ ("x", (IntegerValue 0)); ("y", (IntegerValue 1)) ] *)
	      (print_endline (string_of_value (value_of_expr expr env))))
       | _ -> print_endline "Ko" 
;;

main();;
