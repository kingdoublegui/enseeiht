package egg;
import java.util.*;
import fr.n7.stl.block.ast.*;
import fr.n7.stl.block.ast.impl.*;
import fr.n7.stl.util.*;
import mg.egg.eggc.runtime.libjava.lex.*;
import mg.egg.eggc.runtime.libjava.*;
import mg.egg.eggc.runtime.libjava.messages.*;
import mg.egg.eggc.runtime.libjava.problem.IProblem;
import java.util.Vector;
public class S_Valeur_Bloc {
LEX_Bloc scanner;
  S_Valeur_Bloc() {
	}
  S_Valeur_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  Value att_ast;
  LEX_Bloc att_scanner;
  private void regle46() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_entier);
if  (att_eval)	  action_ast_46(x_2);
  }
  private void regle48() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_faux);
if  (att_eval)	  action_ast_48();
  }
  private void regle47() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_vrai);
if  (att_eval)	  action_ast_47();
  }
private void action_ast_47() throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createBooleanValue(true);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Valeur -> vrai #ast ;", e });
}
  }
private void action_ast_48() throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createBooleanValue(false);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Valeur -> faux #ast ;", e });
}
  }
private void action_ast_46(T_Bloc x_2) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createIntegerValue(x_2.att_txt);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Valeur -> entier #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_entier : // 61
        regle46 () ;
      break ;
      case LEX_Bloc.token_vrai : // 48
        regle47 () ;
      break ;
      case LEX_Bloc.token_faux : // 49
        regle48 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
