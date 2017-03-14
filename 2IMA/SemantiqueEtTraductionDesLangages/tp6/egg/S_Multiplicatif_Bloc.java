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
public class S_Multiplicatif_Bloc {
LEX_Bloc scanner;
  S_Multiplicatif_Bloc() {
	}
  S_Multiplicatif_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  boolean att_eval;
  LEX_Bloc att_scanner;
  BinaryOperator att_bin_op;
  private void regle40() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_et);
if  (att_eval)	  action_ast_40();
  }
  private void regle28() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_multiplication);
if  (att_eval)	  action_ast_28();
  }
  private void regle39() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_modulo);
if  (att_eval)	  action_ast_39();
  }
  private void regle38() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_division);
if  (att_eval)	  action_ast_38();
  }
private void action_ast_38() throws Exception {
try {
// instructions
this.att_bin_op=BinaryOperator.Divide;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Multiplicatif -> division #ast ;", e });
}
  }
private void action_ast_28() throws Exception {
try {
// instructions
this.att_bin_op=BinaryOperator.Multiply;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Multiplicatif -> multiplication #ast ;", e });
}
  }
private void action_ast_39() throws Exception {
try {
// instructions
this.att_bin_op=BinaryOperator.Modulo;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Multiplicatif -> modulo #ast ;", e });
}
  }
private void action_ast_40() throws Exception {
try {
// instructions
this.att_bin_op=BinaryOperator.And;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Multiplicatif -> et #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_multiplication : // 43
        regle28 () ;
      break ;
      case LEX_Bloc.token_division : // 44
        regle38 () ;
      break ;
      case LEX_Bloc.token_modulo : // 45
        regle39 () ;
      break ;
      case LEX_Bloc.token_et : // 47
        regle40 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
