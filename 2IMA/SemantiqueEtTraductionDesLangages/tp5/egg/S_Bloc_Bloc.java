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
public class S_Bloc_Bloc {
LEX_Bloc scanner;
  S_Bloc_Bloc() {
	}
  S_Bloc_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  boolean att_eval;
  Block att_ast;
  LEX_Bloc att_scanner;
  private void regle1() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Instructions_Bloc x_3 = new S_Instructions_Bloc(scanner,att_eval) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_1(x_3);
	x_2.analyser(LEX_Bloc.token_accolade_ouvrante);
	x_3.analyser() ;
	x_4.analyser(LEX_Bloc.token_accolade_fermante);
if  (att_eval)	  action_ast_1(x_3);
  }
private void action_ast_1(S_Instructions_Bloc x_3) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createBlock(x_3.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Bloc -> accolade_ouvrante Instructions accolade_fermante #ast ;"});
}
  }
private void action_auto_inh_1(S_Instructions_Bloc x_3) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Bloc -> accolade_ouvrante Instructions accolade_fermante #ast ;"});
}
  }
  public void analyser () throws Exception {
    regle1 () ;
  }
  }
