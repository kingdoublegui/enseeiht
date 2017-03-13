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
  private void regle26() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_multiplication);
if  (att_eval)	  action_ast_26();
  }
private void action_ast_26() throws Exception {
try {
// instructions
this.att_bin_op=BinaryOperator.Multiply;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Multiplicatif -> multiplication #ast ;"});
}
  }
  public void analyser () throws Exception {
    regle26 () ;
  }
  }
