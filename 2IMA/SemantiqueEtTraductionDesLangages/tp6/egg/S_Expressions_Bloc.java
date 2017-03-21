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
public class S_Expressions_Bloc {
LEX_Bloc scanner;
  S_Expressions_Bloc() {
	}
  S_Expressions_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  LEX_Bloc att_scanner;
  LinkedList<Expression> att_expressions;
  private void regle59() throws Exception {

	//declaration
	S_Expression_Bloc x_2 = new S_Expression_Bloc(scanner,att_eval) ;
	S_SuiteExpressions_Bloc x_3 = new S_SuiteExpressions_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_59(x_2, x_3);
	x_2.analyser() ;
	x_3.analyser() ;
if  (att_eval)	  action_ast_59(x_2, x_3);
  }
private void action_ast_59(S_Expression_Bloc x_2, S_SuiteExpressions_Bloc x_3) throws Exception {
try {
// instructions
x_3.att_expressions.addFirst(x_2.att_ast);
this.att_expressions=x_3.att_expressions;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Expressions -> Expression SuiteExpressions #ast ;", e });
}
  }
private void action_auto_inh_59(S_Expression_Bloc x_2, S_SuiteExpressions_Bloc x_3) throws Exception {
try {
// instructions
x_2.att_factory=this.att_factory;
x_3.att_factory=this.att_factory;
x_2.att_tds=this.att_tds;
x_3.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Expressions -> Expression SuiteExpressions #ast ;", e });
}
  }
  public void analyser () throws Exception {
    regle59 () ;
  }
  }
