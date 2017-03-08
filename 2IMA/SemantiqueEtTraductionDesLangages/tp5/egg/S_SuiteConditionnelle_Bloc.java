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
public class S_SuiteConditionnelle_Bloc {
LEX_Bloc scanner;
  S_SuiteConditionnelle_Bloc() {
	}
  S_SuiteConditionnelle_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  boolean att_eval;
  Block att_ast;
  LEX_Bloc att_scanner;
  private void regle11() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Bloc_Bloc x_3 = new S_Bloc_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_11(x_3);
	x_2.analyser(LEX_Bloc.token_sinon);
	x_3.analyser() ;
if  (att_eval)	  action_ast_11(x_3);
  }
  private void regle10() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_ast_10();
  }
private void action_auto_inh_11(S_Bloc_Bloc x_3) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","SuiteConditionnelle -> sinon Bloc #ast ;"});
}
  }
private void action_ast_10() throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createBlock();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteConditionnelle -> #ast ;"});
}
  }
private void action_ast_11(S_Bloc_Bloc x_3) throws Exception {
try {
// instructions
this.att_ast=x_3.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteConditionnelle -> sinon Bloc #ast ;"});
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_type_bool : // 51
        regle10 () ;
      break ;
      case LEX_Bloc.token_type_int : // 50
        regle10 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 25
        regle10 () ;
      break ;
      case LEX_Bloc.token_identificateur : // 54
        regle10 () ;
      break ;
      case LEX_Bloc.token_si : // 44
        regle10 () ;
      break ;
      case LEX_Bloc.token_tant_que : // 47
        regle10 () ;
      break ;
      case LEX_Bloc.token_afficher : // 46
        regle10 () ;
      break ;
      case LEX_Bloc.token_accolade_fermante : // 20
        regle10 () ;
      break ;
      case LEX_Bloc.token_sinon : // 45
        regle11 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
