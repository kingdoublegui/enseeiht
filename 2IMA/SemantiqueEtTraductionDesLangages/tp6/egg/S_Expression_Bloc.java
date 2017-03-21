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
public class S_Expression_Bloc {
LEX_Bloc scanner;
  S_Expression_Bloc() {
	}
  S_Expression_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  Expression att_ast;
  LEX_Bloc att_scanner;
  private void regle20() throws Exception {

	//declaration
	S_Condition_Bloc x_2 = new S_Condition_Bloc(scanner,att_eval) ;
	S_SuiteCondition_Bloc x_4 = new S_SuiteCondition_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_20(x_2, x_4);
	x_2.analyser() ;
if  (att_eval)	  action_inh_20(x_2, x_4);
	x_4.analyser() ;
if  (att_eval)	  action_ast_20(x_2, x_4);
  }
private void action_inh_20(S_Condition_Bloc x_2, S_SuiteCondition_Bloc x_4) throws Exception {
try {
// instructions
x_4.att_gauche=x_2.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Expression -> Condition #inh SuiteCondition #ast ;", e });
}
  }
private void action_auto_inh_20(S_Condition_Bloc x_2, S_SuiteCondition_Bloc x_4) throws Exception {
try {
// instructions
x_2.att_factory=this.att_factory;
x_4.att_factory=this.att_factory;
x_2.att_tds=this.att_tds;
x_4.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Expression -> Condition #inh SuiteCondition #ast ;", e });
}
  }
private void action_ast_20(S_Condition_Bloc x_2, S_SuiteCondition_Bloc x_4) throws Exception {
try {
// instructions
this.att_ast=x_4.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Expression -> Condition #inh SuiteCondition #ast ;", e });
}
  }
  public void analyser () throws Exception {
    regle20 () ;
  }
  }
