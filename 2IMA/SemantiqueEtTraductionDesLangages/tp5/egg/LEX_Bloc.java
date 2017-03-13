package egg;
import java.util.Arrays;
import mg.egg.eggc.runtime.libjava.EGGException;
import mg.egg.eggc.runtime.libjava.messages.NLS;
import mg.egg.eggc.runtime.libjava.lex.LEXICAL4;
import mg.egg.eggc.runtime.libjava.lex.LEX_CONTEXTE;
import mg.egg.eggc.runtime.libjava.problem.IProblemReporter;
import mg.egg.eggc.runtime.libjava.problem.IProblem;
import mg.egg.eggc.runtime.libjava.problem.ProblemSeverities;
import java.util.*;
import fr.n7.stl.block.ast.*;
import fr.n7.stl.block.ast.impl.*;
import fr.n7.stl.util.*;
public class LEX_Bloc extends LEXICAL4  {
 public static final int EOF = 0 ;
 public static final int token_tant_que = 1 ;
 public static final int token_accolade_ouvrante = 2 ;
 public static final int token_egalite = 3 ;
 public static final int token_identificateur_type = 4 ;
 public static final int token_soustraction = 5 ;
 public static final int token_afficher = 6 ;
 public static final int token_faux = 7 ;
 public static final int token_multiplication = 8 ;
 public static final int token_type_int = 9 ;
 public static final int token_identificateur = 10 ;
 public static final int token_entier = 11 ;
 public static final int token_type_bool = 12 ;
 public static final int token_parenthese_fermante = 13 ;
 public static final int token_si = 14 ;
 public static final int token_superieur = 15 ;
 public static final int token_vrai = 16 ;
 public static final int token_sinon = 17 ;
 public static final int token_superieur_egal = 18 ;
 public static final int token_second = 19 ;
 public static final int token_ou = 20 ;
 public static final int token_negation = 21 ;
 public static final int token_inferieur = 22 ;
 public static final int token_affectation = 23 ;
 public static final int token_point_virgule = 24 ;
 public static final int token_accolade_fermante = 25 ;
 public static final int token_point = 26 ;
 public static final int token_crochet_fermant = 27 ;
 public static final int token_virgule = 28 ;
 public static final int token_parenthese_ouvrante = 29 ;
 public static final int token_separateur = 30 ;
 public static final int token_addition = 31 ;
 public static final int token_et = 32 ;
 public static final int token_enregistrement = 33 ;
 public static final int token_premier = 34 ;
 public static final int token_constante = 35 ;
 public static final int token_commentaire = 36 ;
 public static final int token_crochet_ouvrant = 37 ;
 public static final int token_inferieur_egal = 38 ;
 public static final int token_division = 39 ;
 public static final int token_modulo = 40 ;
  static final int token_autre = 41 ;
  public static final String[][] tokenImages = {
	{"<EOF>"} ,{"tant_que"},
{"accolade_ouvrante"},
{"egalite"},
    {"identificateur_type"} ,
{"soustraction"},
{"afficher"},
{"faux"},
{"multiplication"},
{"type_int"},
    {"identificateur"} ,
    {"entier"} ,
{"type_bool"},
{"parenthese_fermante"},
{"si"},
{"superieur"},
{"vrai"},
{"sinon"},
{"superieur_egal"},
{"second"},
{"ou"},
{"negation"},
{"inferieur"},
{"affectation"},
{"point_virgule"},
{"accolade_fermante"},
{"point"},
{"crochet_fermant"},
{"virgule"},
{"parenthese_ouvrante"},
    {"separateur"} ,
{"addition"},
{"et"},
{"enregistrement"},
{"premier"},
{"constante"},
    {"commentaire"} ,
{"crochet_ouvrant"},
{"inferieur_egal"},
{"division"},
{"modulo"},
  } ;
  private int [] separateurs = { 
token_separateur
, token_commentaire
	} ;
  public int[] getSeparateurs(){
	return separateurs;
    }
  private int [] comments = { 
	} ;
  public int[] getComments(){
	return comments;
    }
  private int le_comment = -1;
  public int getComment(){
	return le_comment;
    }
  public LEX_Bloc(IProblemReporter pr, LEX_CONTEXTE lc, int k) {
	super(pr, lc,k);
	dernier_accepte = 0 ;
	analyseur = new JLEX_Bloc();
  }
  public void setSource ( LEXICAL4 scanner) throws EGGException{
	scanner.analyseur.toContext(scanner.contexte);
	analyseur.fromContext(scanner.contexte);
  }
  public void setReader ( LEXICAL4 scanner) {
	scanner.analyseur.setReader(scanner.contexte.source);
  }
  public void accepter_sucre ( int t ) throws EGGException {
	if ( fenetre[0].code == t ) {
	  dernier_accepte = fenetre[0].ligne ;
	  recovery = false;
	  decaler () ;
	}else {
	  _interrompre(IProblem.Syntax,getBeginLine(), IBlocMessages.id_Bloc_expected_token, BlocMessages.Bloc_expected_token,new Object[]{fenetre[0].getNom(), Arrays.asList(tokenImages[t])});
	}
  }
  public void accepter_fds() throws EGGException {
	lit ( 1 ) ;
	if ( fenetre[0].code != EOF ) {
	  _interrompre(IProblem.Syntax,getBeginLine(), IBlocMessages.id_Bloc_expected_token, BlocMessages.Bloc_expected_eof, new Object[]{fenetre[0].getNom()});
	  }
     else {
	  dernier_accepte = fenetre[0].ligne ;
	  }
	}
  public int ligneDepart () {
	return getBeginLine() + getEndLine() ;
	}
  public void _interrompre (int cat,  int line, int id,  String c , Object [] m )  {
	  //recovery = true;
	  recovery = false;
	  contexte.errors++;
	    problemReporter.handle(cat, id,line  , NLS.bind(c,m), getOffset(), getOffset() + getLength() - 1,ProblemSeverities.Error,m);
	}
	public void _signaler ( int cat, int line, int id, String c , Object [] m )  {
	    problemReporter.handle(cat , id, line  , NLS.bind(c,m),getOffset(), getOffset() + getLength() - 1,ProblemSeverities.Warning,m);
	}
  }
