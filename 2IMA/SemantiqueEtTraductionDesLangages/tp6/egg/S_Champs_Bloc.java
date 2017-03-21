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
public class S_Champs_Bloc {
LEX_Bloc scanner;
  S_Champs_Bloc() {
	}
  S_Champs_Bloc(LEX_Bloc scanner, boolean eval) {
	this.scanner = scanner;
	this.att_eval = eval;
	this.att_scanner = scanner;
	}
int [] sync= new int[0];
  BlockFactory att_factory;
  SymbolTable att_tds;
  boolean att_eval;
  LinkedList<FieldDeclaration> att_champs;
  LEX_Bloc att_scanner;
  private void regle56() throws Exception {

	//declaration
	//appel
if  (att_eval)	  action_champs_56();
  }
  private void regle55() throws Exception {

	//declaration
	S_Champ_Bloc x_2 = new S_Champ_Bloc(scanner,att_eval) ;
	S_Champs_Bloc x_3 = new S_Champs_Bloc(scanner,att_eval) ;
	//appel
if  (att_eval)	  action_auto_inh_55(x_2, x_3);
	x_2.analyser() ;
	x_3.analyser() ;
if  (att_eval)	  action_champs_55(x_2, x_3);
  }
private void action_champs_56() throws Exception {
try {
// instructions
this.att_champs= new LinkedList<FieldDeclaration>();
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#champs","Champs -> #champs ;", e });
}
  }
private void action_auto_inh_55(S_Champ_Bloc x_2, S_Champs_Bloc x_3) throws Exception {
try {
// instructions
x_2.att_factory=this.att_factory;
x_3.att_factory=this.att_factory;
x_2.att_tds=this.att_tds;
x_3.att_tds=this.att_tds;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#auto_inh","Champs -> Champ Champs1 #champs ;", e });
}
  }
private void action_champs_55(S_Champ_Bloc x_2, S_Champs_Bloc x_3) throws Exception {
try {
// locales
LinkedList<FieldDeclaration> loc_champs;
// instructions
loc_champs=x_3.att_champs;
loc_champs.addFirst(x_2.att_champ);
this.att_champs=loc_champs;
}catch(RuntimeException e) {	   att_scanner._interrompre(IProblem.Internal,att_scanner.getBeginLine(),ICoreMessages.id_EGG_runtime_error, CoreMessages.EGG_runtime_error,new Object[] { "Bloc", "#champs","Champs -> Champ Champs1 #champs ;", e });
}
  }
  public void analyser () throws Exception {
    scanner.lit ( 1 ) ;
    switch ( scanner.fenetre[0].code ) {
      case LEX_Bloc.token_type_bool : // 60
        regle55 () ;
      break ;
      case LEX_Bloc.token_type_int : // 59
        regle55 () ;
      break ;
      case LEX_Bloc.token_inferieur : // 33
        regle55 () ;
      break ;
      case LEX_Bloc.token_identificateur_type : // 66
        regle55 () ;
      break ;
      case LEX_Bloc.token_enregistrement : // 62
        regle55 () ;
      break ;
      case LEX_Bloc.token_accolade_fermante : // 28
        regle56 () ;
      break ;
      default :
        	   scanner._interrompre(IProblem.Syntax, scanner.getBeginLine(), IBlocMessages.id_Bloc_unexpected_token,BlocMessages.Bloc_unexpected_token,new String[]{scanner.fenetre[0].getNom()});
    }
  }
  }
