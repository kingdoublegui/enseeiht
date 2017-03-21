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
public class S_SuiteExpressions_Bloc {
LEX_Bloc scanner;
  S_SuiteExpressions_Bloc() {
	}
  S_SuiteExpressions_Bloc(LEX_Bloc scanner, boolean eval) {
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
  private void regle60() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Expression_Bloc x_3 = new S_Expression_Bloc(scanner,att_eval) ;
	S_SuiteExpressions_Bloc x_4 = new S_SuiteExpressions_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_60(x_3, x_4);
	x_2.analyser(LEX_Bloc.token_virgule);
	x_3.analyser() ;
	x_4.analyser() ;
if  (att_eval)	  action_ast_60(x_3, x_4);
  }
  private void regle61() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_ast_61();
  }
private void action_ast_60(S_Expression_Bloc x_3, S_SuiteExpressions_Bloc x_4) throws Exception {
try {
// instructions
x_4.att_expressions.addFirst(x_3.att_ast);
this.att_expressions=x_4.att_expressions;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteExpressions -> virgule Expression SuiteExpressions1 #ast ;", e });
}
  }
private void action_ast_61() throws Exception {
try {
// instructions
this.att_expressions= new LinkedList<Expression>();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","SuiteExpressions -> #ast ;", e });
}
  }
private void action_auto_inh_60(S_Expression_Bloc x_3, S_SuiteExpressions_Bloc x_4) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_4.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
x_4.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","SuiteExpressions -> virgule Expression SuiteExpressions1 #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_virgule : // 39
        regle60 () ;
      break ;
      case LEX_Bloc.token_accolade_fermante : // 28
        regle61 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
