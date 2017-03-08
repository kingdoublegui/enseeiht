package egg;
import java.util.*;
import fr.n7.stl.block.ast.*;
import fr.n7.stl.block.ast.impl.*;
import fr.n7.stl.util.*;
import mg.egg.eggc.runtime.libjava.*;
import mg.egg.eggc.runtime.libjava.lex.*;
public class T_Bloc {
  LEX_Bloc att_scanner;
  String att_txt;
  LEX_Bloc scanner;
  private String txt;
  public String getTxt(){return txt;}
  public T_Bloc(LEX_Bloc scanner ) {
	this.scanner = scanner ;
	this.att_scanner = scanner ;
	}
  public void analyser(int t) throws EGGException {
	scanner.lit ( 1 ) ;
	txt = scanner.fenetre[0].getNom() ;
	att_txt = txt ;
	scanner.accepter_sucre ( t ) ;
	}
	}
