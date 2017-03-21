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
public class S_SuiteCondition_Bloc {
LEX_Bloc scanner;
  S_SuiteCondition_Bloc() {
	}
  S_SuiteCondition_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  Expression att_ast;
  Expression att_gauche;
  LEX_Bloc att_scanner;
  private void regle22() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_ast_22();
  }
  private void regle21() throws Exception {

	//declaration
	S_Comparatif_Bloc x_2 = new S_Comparatif_Bloc(scanner,att_eval) ;
	S_Condition_Bloc x_3 = new S_Condition_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_21(x_2, x_3);
	x_2.analyser() ;
	x_3.analyser() ;
if  (att_eval)	  action_ast_21(x_2, x_3);
  }
private void action_auto_inh_21(S_Comparatif_Bloc x_2, S_Condition_Bloc x_3) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","SuiteCondition -> Comparatif Condition #ast ;", e });
}
  }
private void action_ast_21(S_Comparatif_Bloc x_2, S_Condition_Bloc x_3) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createBinaryExpression(this.att_gauche, x_2.att_bin_op, x_3.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteCondition -> Comparatif Condition #ast ;", e });
}
  }
private void action_ast_22() throws Exception {
try {
// instructions
this.att_ast=this.att_gauche;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteCondition -> #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_egalite : // 42
        regle21 () ;
      break ;
      case LEX_Bloc.token_different : // 41
        regle21 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 33
        regle21 () ;
      break ;
      case LEX_Bloc.token_inferieur_egal : // 35
        regle21 () ;
      break ;
      case LEX_Bloc.token_superieur : // 34
        regle21 () ;
      break ;
      case LEX_Bloc.token_superieur_egal : // 36
        regle21 () ;
      break ;
      case LEX_Bloc.token_point_virgule : // 38
        regle22 () ;
      break ;
      case LEX_Bloc.token_parenthese_fermante : // 32
        regle22 () ;
      break ;
      case LEX_Bloc.token_virgule : // 39
        regle22 () ;
      break ;
      case LEX_Bloc.token_accolade_fermante : // 28
        regle22 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
