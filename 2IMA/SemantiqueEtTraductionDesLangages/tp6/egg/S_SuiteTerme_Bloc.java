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
public class S_SuiteTerme_Bloc {
LEX_Bloc scanner;
  S_SuiteTerme_Bloc() {
	}
  S_SuiteTerme_Bloc(LEX_Bloc scanner, boolean eval) {
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
	S_Additif_Bloc x_2 = new S_Additif_Bloc(scanner,att_eval) ;
	S_Terme_Bloc x_3 = new S_Terme_Bloc(scanner,att_eval) ;
	S_SuiteTerme_Bloc x_5 = new S_SuiteTerme_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_22(x_2, x_3, x_5);
	x_2.analyser() ;
	x_3.analyser() ;
if  (att_eval)	  action_ast_inh_22(x_2, x_3, x_5);
	x_5.analyser() ;
if  (att_eval)	  action_ast_syn_22(x_2, x_3, x_5);
  }
  private void regle23() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_ast_23();
  }
private void action_auto_inh_22(S_Additif_Bloc x_2, S_Terme_Bloc x_3, S_SuiteTerme_Bloc x_5) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_5.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
x_5.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","SuiteTerme -> Additif Terme #ast_inh SuiteTerme1 #ast_syn ;", e });
}
  }
private void action_ast_syn_22(S_Additif_Bloc x_2, S_Terme_Bloc x_3, S_SuiteTerme_Bloc x_5) throws Exception {
try {
// instructions
this.att_ast=x_5.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast_syn","SuiteTerme -> Additif Terme #ast_inh SuiteTerme1 #ast_syn ;", e });
}
  }
private void action_ast_inh_22(S_Additif_Bloc x_2, S_Terme_Bloc x_3, S_SuiteTerme_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_gauche=this.att_factory.createBinaryExpression(this.att_gauche, x_2.att_bin_op, x_3.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast_inh","SuiteTerme -> Additif Terme #ast_inh SuiteTerme1 #ast_syn ;", e });
}
  }
private void action_ast_23() throws Exception {
try {
// instructions
this.att_ast=this.att_gauche;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteTerme -> #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_addition : // 36
        regle22 () ;
      break ;
      case LEX_Bloc.token_soustraction : // 37
        regle22 () ;
      break ;
      case LEX_Bloc.token_ou : // 38
        regle22 () ;
      break ;
      case LEX_Bloc.token_egalite : // 35
        regle23 () ;
      break ;
      case LEX_Bloc.token_different : // 34
        regle23 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 26
        regle23 () ;
      break ;
      case LEX_Bloc.token_inferieur_egal : // 28
        regle23 () ;
      break ;
      case LEX_Bloc.token_superieur : // 27
        regle23 () ;
      break ;
      case LEX_Bloc.token_superieur_egal : // 29
        regle23 () ;
      break ;
      case LEX_Bloc.token_point_virgule : // 31
        regle23 () ;
      break ;
      case LEX_Bloc.token_parenthese_fermante : // 25
        regle23 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
