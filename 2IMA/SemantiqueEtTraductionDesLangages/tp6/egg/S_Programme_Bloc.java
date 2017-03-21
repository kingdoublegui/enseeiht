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
public class S_Programme_Bloc {
LEX_Bloc scanner;
  S_Programme_Bloc() {
	}
  S_Programme_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  boolean att_eval;
  Block att_ast;
  LEX_Bloc att_scanner;
  private void regle0() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Bloc_Bloc x_4 = new S_Bloc_Bloc(scanner,att_eval) ;
	//appel
	x_2.analyser(LEX_Bloc.token_identificateur);
if  (att_eval)	  action_inh_0(x_2, x_4);
	x_4.analyser() ;
if  (att_eval)	  action_ast_0(x_2, x_4);
  }
private void action_inh_0(T_Bloc x_2, S_Bloc_Bloc x_4) throws Exception {
try {
// instructions
x_4.att_factory= new BlockFactoryImpl();
x_4.att_tds= new SymbolTable();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Programme -> identificateur #inh Bloc #ast ;", e });
}
  }
private void action_ast_0(T_Bloc x_2, S_Bloc_Bloc x_4) throws Exception {
try {
// instructions
this.att_ast=x_4.att_ast;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Programme -> identificateur #inh Bloc #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_identificateur : // 65
        regle0 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
