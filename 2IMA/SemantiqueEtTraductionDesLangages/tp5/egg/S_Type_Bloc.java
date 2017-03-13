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
public class S_Type_Bloc {
LEX_Bloc scanner;
  S_Type_Bloc() {
	}
  S_Type_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  Type att_ast;
  LEX_Bloc att_scanner;
  private void regle2() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_type_bool);
if  (att_eval)	  action_ast_2();
  }
  private void regle3() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_type_int);
if  (att_eval)	  action_ast_3();
  }
  private void regle4() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Type_Bloc x_3 = new S_Type_Bloc(scanner,att_eval) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	S_Type_Bloc x_5 = new S_Type_Bloc(scanner,att_eval) ;
	T_Bloc x_6 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_4(x_3, x_5);
	x_2.analyser(LEX_Bloc.token_inferieur);
	x_3.analyser() ;
	x_4.analyser(LEX_Bloc.token_virgule);
	x_5.analyser() ;
	x_6.analyser(LEX_Bloc.token_superieur);
if  (att_eval)	  action_ast_4(x_3, x_5);
  }
private void action_auto_inh_4(S_Type_Bloc x_3, S_Type_Bloc x_5) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_5.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
x_5.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Type -> inferieur Type1 virgule Type2 superieur #ast ;"});
}
  }
private void action_ast_2() throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createBooleanType();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> type_bool #ast ;"});
}
  }
private void action_ast_3() throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createIntegerType();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> type_int #ast ;"});
}
  }
private void action_ast_4(S_Type_Bloc x_3, S_Type_Bloc x_5) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createCoupleType(x_3.att_ast, x_5.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> inferieur Type1 virgule Type2 superieur #ast ;"});
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_type_bool : // 52
        regle2 () ;
      break ;
      case LEX_Bloc.token_type_int : // 51
        regle3 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 26
        regle4 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
