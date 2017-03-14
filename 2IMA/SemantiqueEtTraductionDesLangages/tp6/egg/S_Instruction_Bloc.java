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
public class S_Instruction_Bloc {
LEX_Bloc scanner;
  S_Instruction_Bloc() {
	}
  S_Instruction_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  Instruction att_ast;
  LEX_Bloc att_scanner;
  private void regle11() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	S_Expression_Bloc x_4 = new S_Expression_Bloc(scanner,att_eval) ;
	T_Bloc x_5 = new T_Bloc(scanner ) ;
	S_Bloc_Bloc x_6 = new S_Bloc_Bloc(scanner,att_eval) ;
	S_SuiteConditionnelle_Bloc x_7 = new S_SuiteConditionnelle_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_11(x_4, x_6, x_7);
	x_2.analyser(LEX_Bloc.token_si);
	x_3.analyser(LEX_Bloc.token_parenthese_ouvrante);
	x_4.analyser() ;
	x_5.analyser(LEX_Bloc.token_parenthese_fermante);
	x_6.analyser() ;
	x_7.analyser() ;
if  (att_eval)	  action_ast_11(x_4, x_6, x_7);
  }
  private void regle10() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	S_Expression_Bloc x_5 = new S_Expression_Bloc(scanner,att_eval) ;
	T_Bloc x_6 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_10(x_2, x_5);
	x_2.analyser(LEX_Bloc.token_identificateur);
	x_3.analyser(LEX_Bloc.token_affectation);
if  (att_eval)	  action_inh_10(x_2, x_5);
	x_5.analyser() ;
	x_6.analyser(LEX_Bloc.token_point_virgule);
if  (att_eval)	  action_ast_10(x_2, x_5);
  }
  private void regle9() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Type_Bloc x_3 = new S_Type_Bloc(scanner,att_eval) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	T_Bloc x_5 = new T_Bloc(scanner ) ;
	S_Valeur_Bloc x_7 = new S_Valeur_Bloc(scanner,att_eval) ;
	T_Bloc x_8 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_9(x_3, x_4, x_7);
	x_2.analyser(LEX_Bloc.token_constante);
	x_3.analyser() ;
	x_4.analyser(LEX_Bloc.token_identificateur);
	x_5.analyser(LEX_Bloc.token_affectation);
if  (att_eval)	  action_inh_9(x_3, x_4, x_7);
	x_7.analyser() ;
	x_8.analyser(LEX_Bloc.token_point_virgule);
if  (att_eval)	  action_ast_9(x_3, x_4, x_7);
  }
  private void regle8() throws Exception {

	//declaration
	S_Type_Bloc x_2 = new S_Type_Bloc(scanner,att_eval) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	S_Expression_Bloc x_6 = new S_Expression_Bloc(scanner,att_eval) ;
	T_Bloc x_7 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_8(x_2, x_3, x_6);
	x_2.analyser() ;
	x_3.analyser(LEX_Bloc.token_identificateur);
	x_4.analyser(LEX_Bloc.token_affectation);
if  (att_eval)	  action_inh_8(x_2, x_3, x_6);
	x_6.analyser() ;
	x_7.analyser(LEX_Bloc.token_point_virgule);
if  (att_eval)	  action_ast_8(x_2, x_3, x_6);
  }
  private void regle16() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	S_Type_Bloc x_3 = new S_Type_Bloc(scanner,att_eval) ;
	T_Bloc x_4 = new T_Bloc(scanner ) ;
	T_Bloc x_5 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_16(x_3, x_4);
	x_2.analyser(LEX_Bloc.token_typedef);
	x_3.analyser() ;
	x_4.analyser(LEX_Bloc.token_identificateur_type);
	x_5.analyser(LEX_Bloc.token_point_virgule);
if  (att_eval)	  action_ast_16(x_3, x_4);
  }
  private void regle15() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	S_Expression_Bloc x_4 = new S_Expression_Bloc(scanner,att_eval) ;
	T_Bloc x_5 = new T_Bloc(scanner ) ;
	T_Bloc x_6 = new T_Bloc(scanner ) ;
	//appel
if  (att_eval)	  action_auto_inh_15(x_4);
	x_2.analyser(LEX_Bloc.token_afficher);
	x_3.analyser(LEX_Bloc.token_parenthese_ouvrante);
	x_4.analyser() ;
	x_5.analyser(LEX_Bloc.token_parenthese_fermante);
	x_6.analyser(LEX_Bloc.token_point_virgule);
if  (att_eval)	  action_ast_15(x_4);
  }
  private void regle14() throws Exception {

	//declaration
	T_Bloc x_2 = new T_Bloc(scanner ) ;
	T_Bloc x_3 = new T_Bloc(scanner ) ;
	S_Expression_Bloc x_4 = new S_Expression_Bloc(scanner,att_eval) ;
	T_Bloc x_5 = new T_Bloc(scanner ) ;
	S_Bloc_Bloc x_6 = new S_Bloc_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_14(x_4, x_6);
	x_2.analyser(LEX_Bloc.token_tant_que);
	x_3.analyser(LEX_Bloc.token_parenthese_ouvrante);
	x_4.analyser() ;
	x_5.analyser(LEX_Bloc.token_parenthese_fermante);
	x_6.analyser() ;
if  (att_eval)	  action_ast_14(x_4, x_6);
  }
private void action_auto_inh_8(S_Type_Bloc x_2, T_Bloc x_3, S_Expression_Bloc x_6) throws Exception {
try {
// instructions
x_2.att_factory=this.att_factory;
x_6.att_factory=this.att_factory;
x_2.att_tds=this.att_tds;
x_6.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Instruction -> Type identificateur affectation #inh Expression point_virgule #ast ;", e });
}
  }
private void action_auto_inh_9(S_Type_Bloc x_3, T_Bloc x_4, S_Valeur_Bloc x_7) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_7.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
x_7.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Instruction -> constante Type identificateur affectation #inh Valeur point_virgule #ast ;", e });
}
  }
private void action_inh_9(S_Type_Bloc x_3, T_Bloc x_4, S_Valeur_Bloc x_7) throws Exception {
try {
// locales
Declaration loc_d;
// instructions
loc_d=this.att_factory.createConstantDeclaration(x_4.att_txt, x_3.att_ast, null);
if ((this.att_tds.contains(loc_d.getName()))){
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token, BlocMessages.Bloc_unexpected_token,new Object[]{""+x_4.att_txt});

}
else {
this.att_tds.register(loc_d);
}
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Instruction -> constante Type identificateur affectation #inh Valeur point_virgule #ast ;", e });
}
  }
private void action_inh_8(S_Type_Bloc x_2, T_Bloc x_3, S_Expression_Bloc x_6) throws Exception {
try {
// locales
Declaration loc_d;
// instructions
loc_d=this.att_factory.createVariableDeclaration(x_3.att_txt, x_2.att_ast, null);
if ((this.att_tds.contains(loc_d.getName()))){
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token, BlocMessages.Bloc_unexpected_token,new Object[]{""+x_3.att_txt});

}
else {
this.att_tds.register(loc_d);
}
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Instruction -> Type identificateur affectation #inh Expression point_virgule #ast ;", e });
}
  }
private void action_auto_inh_15(S_Expression_Bloc x_4) throws Exception {
try {
// instructions
x_4.att_factory=this.att_factory;
x_4.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Instruction -> afficher parenthese_ouvrante Expression parenthese_fermante point_virgule #ast ;", e });
}
  }
private void action_auto_inh_16(S_Type_Bloc x_3, T_Bloc x_4) throws Exception {
try {
// instructions
x_3.att_factory=this.att_factory;
x_3.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Instruction -> typedef Type identificateur_type point_virgule #ast ;", e });
}
  }
private void action_ast_10(T_Bloc x_2, S_Expression_Bloc x_5) throws Exception {
try {
// locales
Optional<Declaration> loc_opd;
Declaration loc_d;
// instructions
loc_opd=this.att_tds.get(x_2.att_txt);
loc_d=loc_opd.get();
if (loc_d instanceof VariableDeclaration ){
this.att_ast=this.att_factory.createAssignment(((VariableDeclaration)loc_d), x_5.att_ast);
}

}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Instruction -> identificateur affectation #inh Expression point_virgule #ast ;", e });
}
  }
private void action_ast_11(S_Expression_Bloc x_4, S_Bloc_Bloc x_6, S_SuiteConditionnelle_Bloc x_7) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createConditional(x_4.att_ast, x_6.att_ast, x_7.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Instruction -> si parenthese_ouvrante Expression parenthese_fermante Bloc SuiteConditionnelle #ast ;", e });
}
  }
private void action_ast_9(S_Type_Bloc x_3, T_Bloc x_4, S_Valeur_Bloc x_7) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createConstantDeclaration(x_4.att_txt, x_3.att_ast, x_7.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Instruction -> constante Type identificateur affectation #inh Valeur point_virgule #ast ;", e });
}
  }
private void action_inh_10(T_Bloc x_2, S_Expression_Bloc x_5) throws Exception {
try {
// locales
Optional<Declaration> loc_opd;
Declaration loc_d;
// instructions
if ((!(this.att_tds.knows(x_2.att_txt)))){
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token, BlocMessages.Bloc_unexpected_token,new Object[]{""+x_2.att_txt});

}
else {
loc_opd=this.att_tds.get(x_2.att_txt);
loc_d=loc_opd.get();
if (loc_d instanceof ConstantDeclaration ){
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_expected_token, BlocMessages.Bloc_expected_token,new Object[]{""+"ConstanteDeclaration", ""+"VariableDeclaration"});

}

}
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#inh","Instruction -> identificateur affectation #inh Expression point_virgule #ast ;", e });
}
  }
private void action_ast_14(S_Expression_Bloc x_4, S_Bloc_Bloc x_6) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createRepetition(x_4.att_ast, x_6.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Instruction -> tant_que parenthese_ouvrante Expression parenthese_fermante Bloc #ast ;", e });
}
  }
private void action_ast_15(S_Expression_Bloc x_4) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createPrinter(x_4.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Instruction -> afficher parenthese_ouvrante Expression parenthese_fermante point_virgule #ast ;", e });
}
  }
private void action_ast_16(S_Type_Bloc x_3, T_Bloc x_4) throws Exception {
try {
// locales
TypeDeclaration loc_v;
// instructions
if (this.att_tds.contains(x_4.att_txt)){
att_scanner._interrompre(IProblem.Semantic, att_scanner.getBeginLine(), IBlocMessages.id_Bloc_unexecpected_token, BlocMessages.Bloc_unexecpected_token,new Object[]{""+x_4.att_txt});

}
else {
loc_v=this.att_factory.createTypeDeclaration(x_4.att_txt, x_3.att_ast);
this.att_tds.register(loc_v);
this.att_ast=loc_v;
}
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Instruction -> typedef Type identificateur_type point_virgule #ast ;", e });
}
  }
private void action_auto_inh_10(T_Bloc x_2, S_Expression_Bloc x_5) throws Exception {
try {
// instructions
x_5.att_factory=this.att_factory;
x_5.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Instruction -> identificateur affectation #inh Expression point_virgule #ast ;", e });
}
  }
private void action_auto_inh_11(S_Expression_Bloc x_4, S_Bloc_Bloc x_6, S_SuiteConditionnelle_Bloc x_7) throws Exception {
try {
// instructions
x_4.att_factory=this.att_factory;
x_6.att_factory=this.att_factory;
x_7.att_factory=this.att_factory;
x_4.att_tds=this.att_tds;
x_6.att_tds=this.att_tds;
x_7.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Instruction -> si parenthese_ouvrante Expression parenthese_fermante Bloc SuiteConditionnelle #ast ;", e });
}
  }
private void action_ast_8(S_Type_Bloc x_2, T_Bloc x_3, S_Expression_Bloc x_6) throws Exception {
try {
// instructions
this.att_ast=this.att_factory.createVariableDeclaration(x_3.att_txt, x_2.att_ast, x_6.att_ast);
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#ast","Instruction -> Type identificateur affectation #inh Expression point_virgule #ast ;", e });
}
  }
private void action_auto_inh_14(S_Expression_Bloc x_4, S_Bloc_Bloc x_6) throws Exception {
try {
// instructions
x_4.att_factory=this.att_factory;
x_6.att_factory=this.att_factory;
x_4.att_tds=this.att_tds;
x_6.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Instruction -> tant_que parenthese_ouvrante Expression parenthese_fermante Bloc #ast ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_type_bool : // 53
        regle8 () ;
      break ;
      case LEX_Bloc.token_type_int : // 52
        regle8 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 26
        regle8 () ;
      break ;
      case LEX_Bloc.token_identificateur_type : // 59
        regle8 () ;
      break ;
      case LEX_Bloc.token_constante : // 54
        regle9 () ;
      break ;
      case LEX_Bloc.token_identificateur : // 58
        regle10 () ;
      break ;
      case LEX_Bloc.token_si : // 46
        regle11 () ;
      break ;
      case LEX_Bloc.token_tant_que : // 49
        regle14 () ;
      break ;
      case LEX_Bloc.token_afficher : // 48
        regle15 () ;
      break ;
      case LEX_Bloc.token_typedef : // 56
        regle16 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
