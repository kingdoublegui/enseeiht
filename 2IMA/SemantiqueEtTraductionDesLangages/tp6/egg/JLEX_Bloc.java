package egg;
import mg.egg.eggc.runtime.libjava.lex.*;

public class JLEX_Bloc implements  LEX_ANALYZER  {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 256;
	private final int YY_EOF = 257;

	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public JLEX_Bloc (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public JLEX_Bloc (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	public JLEX_Bloc () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	public void fromContext(LEX_CONTEXTE cont) {
		yy_reader = cont.source;
		yy_buffer = cont.buffer;
		yy_buffer_read = cont.b_read;
		yy_buffer_index = cont.b_index;
		yy_buffer_start = cont.b_start;
		yy_buffer_end = cont.b_end;
		yychar = cont.car;
		yyline = cont.ligne;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}
	public void toContext(LEX_CONTEXTE cont) {		cont.source = yy_reader;
		cont.buffer = yy_buffer;
		cont.b_read = yy_buffer_read;
		cont.b_index = yy_buffer_index;
		cont.b_start = yy_buffer_start;
		cont.b_end = yy_buffer_end;
		cont.ligne = yyline;
		cont.car = yychar;
	}
	public void setReader(java.io.BufferedReader r) {
		yy_reader = r;
	}
	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
			'\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
			'\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
			        ('\r' == yy_buffer[yy_buffer_end-1] ||
			         '\n' == yy_buffer[yy_buffer_end-1] ||
			         2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
			         2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.err.print(yy_error_string[code]);
		java.lang.System.err.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NOT_ACCEPT,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,258,
"3:9,4,5,3:2,1,3:18,4,18,3:3,23,24,3,10,11,22,19,17,20,15,2,43:10,3,16,12,14" +",13,3:2,46:26,8,3,9,3,45,3,30,39,41,38,28,29,44,37,33,44:2,31,44,35,40,34,4" +"4,26,32,25,27,44,36,44,42,44,6,21,7,3:130,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,87,
"0,1,2,3,1:7,4,5,6,1:3,7,1:4,8,9,10,1:6,11,1,11:12,12,13,14,15,16,17,18,19,2" +"0,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,4" +"5,46,11,47,48,49,50,51,52")[0];

	private int yy_nxt[][] = unpackFromString(53,47,
"1,2,3,4,2:2,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,46,20,21,48,22,80:2,82," +"61,80:2,62,47,83,80,84,80:2,85,80,86,80,23,80,4,24,-1:48,2,-1:2,2:2,-1:43,4" +"5,-1:58,25,-1:46,26,-1:46,27,-1:46,28,-1:57,80,63,80:15,64,80:4,-1:43,23,-1" +":28,24:22,-1:25,80:22,-1:2,45:3,32,45:41,-1:21,29,-1:50,80:4,31,80:5,51,80:" +"11,-1:24,30,-1:47,33,80:21,-1:25,80:13,34,80:8,-1:25,35,80:21,-1:25,80:3,36" +",80:18,-1:25,80:3,37,80:18,-1:25,80:6,38,80:15,-1:25,80:3,39,80:18,-1:25,40" +",80:21,-1:25,80:3,41,80:18,-1:25,42,80:21,-1:25,43,80:21,-1:25,80:4,44,80:1" +"7,-1:25,80:5,66,80,49,80:14,-1:25,67,80:9,50,80:11,-1:25,80:2,52,80:19,-1:2" +"5,80:9,71,80:12,-1:25,80:7,53,80:14,-1:25,80:6,72,80:15,-1:25,80,73,80:20,-" +"1:25,80:8,74,80:13,-1:25,80:15,54,80:6,-1:25,80:10,76,80:11,-1:25,80:3,77,8" +"0:18,-1:25,80:7,55,80:14,-1:25,80:2,78,80:19,-1:25,80:10,56,80:11,-1:25,80:" +"6,57,80:15,-1:25,80:7,58,80:14,-1:25,80:13,79,80:8,-1:25,80:16,59,80:5,-1:2" +"5,80:3,60,80:18,-1:25,80:8,75,80:13,-1:25,80:6,65,80:15,-1:25,80,68,80:20,-" +"1:25,80:12,81,80:9,-1:25,80:15,69,80:6,-1:25,80:15,70,80:6");

	public Yytoken yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

	return new Yytoken(LEX_Bloc.EOF , "EOF" , yyline , yychar , yychar+1 ) ;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					
case 1:
					
	
					
case -2:
					
	break;
					
case 2:
					
	{return new Yytoken(LEX_Bloc.token_separateur, yytext(), yyline, yychar, yychar+1);}
					
case -3:
					
	break;
					
case 3:
					
	{return new Yytoken(LEX_Bloc.token_division, yytext(), yyline, yychar, yychar+1);}
					
case -4:
					
	break;
					
case 4:
					
	{return new Yytoken(LEX_Bloc.token_autre, yytext(), yyline, yychar, yychar+1);}
					
case -5:
					
	break;
					
case 5:
					
	{return new Yytoken(LEX_Bloc.token_accolade_ouvrante, yytext(), yyline, yychar, yychar+1);}
					
case -6:
					
	break;
					
case 6:
					
	{return new Yytoken(LEX_Bloc.token_accolade_fermante, yytext(), yyline, yychar, yychar+1);}
					
case -7:
					
	break;
					
case 7:
					
	{return new Yytoken(LEX_Bloc.token_crochet_ouvrant, yytext(), yyline, yychar, yychar+1);}
					
case -8:
					
	break;
					
case 8:
					
	{return new Yytoken(LEX_Bloc.token_crochet_fermant, yytext(), yyline, yychar, yychar+1);}
					
case -9:
					
	break;
					
case 9:
					
	{return new Yytoken(LEX_Bloc.token_parenthese_ouvrante, yytext(), yyline, yychar, yychar+1);}
					
case -10:
					
	break;
					
case 10:
					
	{return new Yytoken(LEX_Bloc.token_parenthese_fermante, yytext(), yyline, yychar, yychar+1);}
					
case -11:
					
	break;
					
case 11:
					
	{return new Yytoken(LEX_Bloc.token_inferieur, yytext(), yyline, yychar, yychar+1);}
					
case -12:
					
	break;
					
case 12:
					
	{return new Yytoken(LEX_Bloc.token_superieur, yytext(), yyline, yychar, yychar+1);}
					
case -13:
					
	break;
					
case 13:
					
	{return new Yytoken(LEX_Bloc.token_affectation, yytext(), yyline, yychar, yychar+1);}
					
case -14:
					
	break;
					
case 14:
					
	{return new Yytoken(LEX_Bloc.token_point, yytext(), yyline, yychar, yychar+1);}
					
case -15:
					
	break;
					
case 15:
					
	{return new Yytoken(LEX_Bloc.token_point_virgule, yytext(), yyline, yychar, yychar+1);}
					
case -16:
					
	break;
					
case 16:
					
	{return new Yytoken(LEX_Bloc.token_virgule, yytext(), yyline, yychar, yychar+1);}
					
case -17:
					
	break;
					
case 17:
					
	{return new Yytoken(LEX_Bloc.token_negation, yytext(), yyline, yychar, yychar+1);}
					
case -18:
					
	break;
					
case 18:
					
	{return new Yytoken(LEX_Bloc.token_addition, yytext(), yyline, yychar, yychar+1);}
					
case -19:
					
	break;
					
case 19:
					
	{return new Yytoken(LEX_Bloc.token_soustraction, yytext(), yyline, yychar, yychar+1);}
					
case -20:
					
	break;
					
case 20:
					
	{return new Yytoken(LEX_Bloc.token_multiplication, yytext(), yyline, yychar, yychar+1);}
					
case -21:
					
	break;
					
case 21:
					
	{return new Yytoken(LEX_Bloc.token_modulo, yytext(), yyline, yychar, yychar+1);}
					
case -22:
					
	break;
					
case 22:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -23:
					
	break;
					
case 23:
					
	{return new Yytoken(LEX_Bloc.token_entier, yytext(), yyline, yychar, yychar+1);}
					
case -24:
					
	break;
					
case 24:
					
	{return new Yytoken(LEX_Bloc.token_identificateur_type, yytext(), yyline, yychar, yychar+1);}
					
case -25:
					
	break;
					
case 25:
					
	{return new Yytoken(LEX_Bloc.token_inferieur_egal, yytext(), yyline, yychar, yychar+1);}
					
case -26:
					
	break;
					
case 26:
					
	{return new Yytoken(LEX_Bloc.token_superieur_egal, yytext(), yyline, yychar, yychar+1);}
					
case -27:
					
	break;
					
case 27:
					
	{return new Yytoken(LEX_Bloc.token_egalite, yytext(), yyline, yychar, yychar+1);}
					
case -28:
					
	break;
					
case 28:
					
	{return new Yytoken(LEX_Bloc.token_different, yytext(), yyline, yychar, yychar+1);}
					
case -29:
					
	break;
					
case 29:
					
	{return new Yytoken(LEX_Bloc.token_ou, yytext(), yyline, yychar, yychar+1);}
					
case -30:
					
	break;
					
case 30:
					
	{return new Yytoken(LEX_Bloc.token_et, yytext(), yyline, yychar, yychar+1);}
					
case -31:
					
	break;
					
case 31:
					
	{return new Yytoken(LEX_Bloc.token_si, yytext(), yyline, yychar, yychar+1);}
					
case -32:
					
	break;
					
case 32:
					
	{return new Yytoken(LEX_Bloc.token_commentaire, yytext(), yyline, yychar, yychar+1);}
					
case -33:
					
	break;
					
case 33:
					
	{return new Yytoken(LEX_Bloc.token_premier, yytext(), yyline, yychar, yychar+1);}
					
case -34:
					
	break;
					
case 34:
					
	{return new Yytoken(LEX_Bloc.token_second, yytext(), yyline, yychar, yychar+1);}
					
case -35:
					
	break;
					
case 35:
					
	{return new Yytoken(LEX_Bloc.token_type_int, yytext(), yyline, yychar, yychar+1);}
					
case -36:
					
	break;
					
case 36:
					
	{return new Yytoken(LEX_Bloc.token_vrai, yytext(), yyline, yychar, yychar+1);}
					
case -37:
					
	break;
					
case 37:
					
	{return new Yytoken(LEX_Bloc.token_sinon, yytext(), yyline, yychar, yychar+1);}
					
case -38:
					
	break;
					
case 38:
					
	{return new Yytoken(LEX_Bloc.token_type_bool, yytext(), yyline, yychar, yychar+1);}
					
case -39:
					
	break;
					
case 39:
					
	{return new Yytoken(LEX_Bloc.token_faux, yytext(), yyline, yychar, yychar+1);}
					
case -40:
					
	break;
					
case 40:
					
	{return new Yytoken(LEX_Bloc.token_afficher, yytext(), yyline, yychar, yychar+1);}
					
case -41:
					
	break;
					
case 41:
					
	{return new Yytoken(LEX_Bloc.token_tant_que, yytext(), yyline, yychar, yychar+1);}
					
case -42:
					
	break;
					
case 42:
					
	{return new Yytoken(LEX_Bloc.token_constante, yytext(), yyline, yychar, yychar+1);}
					
case -43:
					
	break;
					
case 43:
					
	{return new Yytoken(LEX_Bloc.token_enregistrement, yytext(), yyline, yychar, yychar+1);}
					
case -44:
					
	break;
					
case 44:
					
	{return new Yytoken(LEX_Bloc.token_typedef, yytext(), yyline, yychar, yychar+1);}
					
case -45:
					
	break;
					
case 46:
					
	{return new Yytoken(LEX_Bloc.token_autre, yytext(), yyline, yychar, yychar+1);}
					
case -46:
					
	break;
					
case 47:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -47:
					
	break;
					
case 48:
					
	{return new Yytoken(LEX_Bloc.token_autre, yytext(), yyline, yychar, yychar+1);}
					
case -48:
					
	break;
					
case 49:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -49:
					
	break;
					
case 50:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -50:
					
	break;
					
case 51:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -51:
					
	break;
					
case 52:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -52:
					
	break;
					
case 53:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -53:
					
	break;
					
case 54:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -54:
					
	break;
					
case 55:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -55:
					
	break;
					
case 56:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -56:
					
	break;
					
case 57:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -57:
					
	break;
					
case 58:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -58:
					
	break;
					
case 59:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -59:
					
	break;
					
case 60:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -60:
					
	break;
					
case 61:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -61:
					
	break;
					
case 62:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -62:
					
	break;
					
case 63:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -63:
					
	break;
					
case 64:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -64:
					
	break;
					
case 65:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -65:
					
	break;
					
case 66:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -66:
					
	break;
					
case 67:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -67:
					
	break;
					
case 68:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -68:
					
	break;
					
case 69:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -69:
					
	break;
					
case 70:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -70:
					
	break;
					
case 71:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -71:
					
	break;
					
case 72:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -72:
					
	break;
					
case 73:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -73:
					
	break;
					
case 74:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -74:
					
	break;
					
case 75:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -75:
					
	break;
					
case 76:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -76:
					
	break;
					
case 77:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -77:
					
	break;
					
case 78:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -78:
					
	break;
					
case 79:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -79:
					
	break;
					
case 80:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -80:
					
	break;
					
case 81:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -81:
					
	break;
					
case 82:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -82:
					
	break;
					
case 83:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -83:
					
	break;
					
case 84:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -84:
					
	break;
					
case 85:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -85:
					
	break;
					
case 86:
					
	{return new Yytoken(LEX_Bloc.token_identificateur, yytext(), yyline, yychar, yychar+1);}
					
case -86:
					
	break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
