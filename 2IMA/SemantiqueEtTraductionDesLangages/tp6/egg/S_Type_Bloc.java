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
  RecordType glob_54_t;
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
  private void regle5() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	//appel
	x_2.analyser(LEX_Bloc.token_identificateur_type);
if  (att_eval)	  action_ast_5(x_2);
  }
  private void regle54() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	S_Champs_Bloc x_6 = new S_Champs_Bloc(scanner,att_eval) ;
	T_Bloc x_7 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_54(x_3, x_6);
	x_2.analyser(LEX_Bloc.token_enregistrement);
	x_3.analyser(LEX_Bloc.token_identificateur_type);
	x_4.analyser(LEX_Bloc.token_accolade_ouvrante);
if  (att_eval)	  action_inh_54(x_3, x_6);
	x_6.analyser() ;
	x_7.analyser(LEX_Bloc.token_accolade_fermante);
if  (att_eval)	  action_ast_54(x_3, x_6);
  }
private void action_inh_54(T_Bloc x_3, S_Champs_Bloc x_6) throws Exception {
try {
// instructions
if ((this.att_tds.contains(x_3.att_txt))){
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_already_defined, BlocMessages.Bloc_already_defined,new Object[]{""+x_3.att_txt});

}
else {
glob_54_t=this.att_factory.createRecordType(x_3.att_txt);
this.att_tds.register(glob_54_t);
}
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Type -> enregistrement identificateur_type accolade_ouvrante #inh Champs accolade_fermante #ast ;", e });
}
  }
private void action_auto_inh_54(T_Bloc x_3, S_Champs_Bloc x_6) throws Exception {
try {
// instructions
x_6.att_factory=this.att_factory;
x_6.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Type -> enregistrement identificateur_type accolade_ouvrante #inh Champs accolade_fermante #ast ;", e });
}
  }
private void action_ast_5(T_Bloc x_2) throws Exception {
try {
// locales
Optional<Declaration> loc_d;
Declaration loc_t;
// instructions
if (this.att_tds.knows(x_2.att_txt)){
loc_d=this.att_tds.get(x_2.att_txt);
if ((loc_d.isPresent())){
loc_t=loc_d.get();
if (loc_t instanceof TypeDeclaration ){
this.att_ast=((TypeDeclaration)loc_t).getType();
}
else {
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_not_a_type, BlocMessages.Bloc_not_a_type,new Object[]{""+x_2.att_txt});


}
}
else {
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_undefined_ident, BlocMessages.Bloc_undefined_ident,new Object[]{""+x_2.att_txt});

}
}
else {
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_undefined_ident, BlocMessages.Bloc_undefined_ident,new Object[]{""+x_2.att_txt});

}
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> identificateur_type #ast ;", e });
}
  }
private void action_auto_inh_4(S_Type_Bloc x_3, S_Type_Bloc x_5) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_5.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
x_5.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Type -> inferieur Type1 virgule Type2 superieur #ast ;", e });
}
  }
private void action_ast_2() throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createBooleanType();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> type_bool #ast ;", e });
}
  }
private void action_ast_3() throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createIntegerType();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> type_int #ast ;", e });
}
  }
private void action_ast_4(S_Type_Bloc x_3, S_Type_Bloc x_5) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createCoupleType(x_3.att_ast, x_5.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> inferieur Type1 virgule Type2 superieur #ast ;", e });
}
  }
private void action_ast_54(T_Bloc x_3, S_Champs_Bloc x_6) throws Exception {
try {
// instructions
glob_54_t.addAll(x_6.att_champs);
this.att_ast=glob_54_t;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Type -> enregistrement identificateur_type accolade_ouvrante #inh Champs accolade_fermante #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_type_bool : // 60
        regle2 () ;
      break ;
      case LEX_Bloc.token_type_int : // 59
        regle3 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 33
        regle4 () ;
      break ;
      case LEX_Bloc.token_identificateur_type : // 66
        regle5 () ;
      break ;
      case LEX_Bloc.token_enregistrement : // 62
        regle54 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
