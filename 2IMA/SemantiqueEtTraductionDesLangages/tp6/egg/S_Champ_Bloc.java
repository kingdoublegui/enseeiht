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
public class S_Champ_Bloc {
LEX_Bloc scanner;
  S_Champ_Bloc() {
	}
  S_Champ_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  LEX_Bloc att_scanner;
  FieldDeclaration att_champ;
  private void regle57() throws Exception {

	//declaration
	S_Type_Bloc x_2 = new S_Type_Bloc(scanner,att_eval) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_57(x_2, x_3);
	x_2.analyser() ;
	x_3.analyser(LEX_Bloc.token_identificateur);
	x_4.analyser(LEX_Bloc.token_point_virgule);
if  (att_eval)	  action_ast_57(x_2, x_3);
  }
private void action_auto_inh_57(S_Type_Bloc x_2, T_Bloc x_3) throws Exception {
try {
// instructions
x_2.att_factory=this.att_factory;
x_2.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Champ -> Type identificateur point_virgule #ast ;", e });
}
  }
private void action_ast_57(S_Type_Bloc x_2, T_Bloc x_3) throws Exception {
try {
// instructions
this.att_champ=this.att_factory.createFieldDeclaration(x_3.att_txt, x_2.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Champ -> Type identificateur point_virgule #ast ;", e });
}
  }
  public void analyser () throws Exception {
    regle57 () ;
  }
  }
