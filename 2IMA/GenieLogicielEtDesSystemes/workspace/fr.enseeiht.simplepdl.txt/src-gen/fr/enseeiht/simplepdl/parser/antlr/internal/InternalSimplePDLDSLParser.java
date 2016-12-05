package fr.enseeiht.simplepdl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import fr.enseeiht.simplepdl.services.SimplePDLDSLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSimplePDLDSLParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'process'", "'{'", "'}'", "'wd'", "'ws'", "'from'", "'to'", "'gd'", "' '", "'re'", "'rs'", "'needs'", "'s2s'", "'f2s'", "'s2f'", "'f2f'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalSimplePDLDSLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSimplePDLDSLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSimplePDLDSLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalSimplePDLDSL.g"; }



     	private SimplePDLDSLGrammarAccess grammarAccess;

        public InternalSimplePDLDSLParser(TokenStream input, SimplePDLDSLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Process";
       	}

       	@Override
       	protected SimplePDLDSLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleProcess"
    // InternalSimplePDLDSL.g:65:1: entryRuleProcess returns [EObject current=null] : iv_ruleProcess= ruleProcess EOF ;
    public final EObject entryRuleProcess() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcess = null;


        try {
            // InternalSimplePDLDSL.g:65:48: (iv_ruleProcess= ruleProcess EOF )
            // InternalSimplePDLDSL.g:66:2: iv_ruleProcess= ruleProcess EOF
            {
             newCompositeNode(grammarAccess.getProcessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcess=ruleProcess();

            state._fsp--;

             current =iv_ruleProcess; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcess"


    // $ANTLR start "ruleProcess"
    // InternalSimplePDLDSL.g:72:1: ruleProcess returns [EObject current=null] : (otherlv_0= 'process' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_processElements_3_0= ruleProcessElement ) )* otherlv_4= '}' ) ;
    public final EObject ruleProcess() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_processElements_3_0 = null;



        	enterRule();

        try {
            // InternalSimplePDLDSL.g:78:2: ( (otherlv_0= 'process' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_processElements_3_0= ruleProcessElement ) )* otherlv_4= '}' ) )
            // InternalSimplePDLDSL.g:79:2: (otherlv_0= 'process' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_processElements_3_0= ruleProcessElement ) )* otherlv_4= '}' )
            {
            // InternalSimplePDLDSL.g:79:2: (otherlv_0= 'process' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_processElements_3_0= ruleProcessElement ) )* otherlv_4= '}' )
            // InternalSimplePDLDSL.g:80:3: otherlv_0= 'process' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_processElements_3_0= ruleProcessElement ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,11,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getProcessAccess().getProcessKeyword_0());
            		
            // InternalSimplePDLDSL.g:84:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalSimplePDLDSL.g:85:4: (lv_name_1_0= RULE_ID )
            {
            // InternalSimplePDLDSL.g:85:4: (lv_name_1_0= RULE_ID )
            // InternalSimplePDLDSL.g:86:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_4); 

            					newLeafNode(lv_name_1_0, grammarAccess.getProcessAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProcessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,12,FOLLOW_5); 

            			newLeafNode(otherlv_2, grammarAccess.getProcessAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalSimplePDLDSL.g:106:3: ( (lv_processElements_3_0= ruleProcessElement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=14 && LA1_0<=15)||LA1_0==18||(LA1_0>=20 && LA1_0<=21)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalSimplePDLDSL.g:107:4: (lv_processElements_3_0= ruleProcessElement )
            	    {
            	    // InternalSimplePDLDSL.g:107:4: (lv_processElements_3_0= ruleProcessElement )
            	    // InternalSimplePDLDSL.g:108:5: lv_processElements_3_0= ruleProcessElement
            	    {

            	    					newCompositeNode(grammarAccess.getProcessAccess().getProcessElementsProcessElementParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_processElements_3_0=ruleProcessElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getProcessRule());
            	    					}
            	    					add(
            	    						current,
            	    						"processElements",
            	    						lv_processElements_3_0,
            	    						"fr.enseeiht.simplepdl.SimplePDLDSL.ProcessElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            otherlv_4=(Token)match(input,13,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getProcessAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcess"


    // $ANTLR start "entryRuleProcessElement"
    // InternalSimplePDLDSL.g:133:1: entryRuleProcessElement returns [EObject current=null] : iv_ruleProcessElement= ruleProcessElement EOF ;
    public final EObject entryRuleProcessElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessElement = null;


        try {
            // InternalSimplePDLDSL.g:133:55: (iv_ruleProcessElement= ruleProcessElement EOF )
            // InternalSimplePDLDSL.g:134:2: iv_ruleProcessElement= ruleProcessElement EOF
            {
             newCompositeNode(grammarAccess.getProcessElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProcessElement=ruleProcessElement();

            state._fsp--;

             current =iv_ruleProcessElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcessElement"


    // $ANTLR start "ruleProcessElement"
    // InternalSimplePDLDSL.g:140:1: ruleProcessElement returns [EObject current=null] : (this_WorkDefinition_0= ruleWorkDefinition | this_WorkSequence_1= ruleWorkSequence | this_Guidance_2= ruleGuidance | this_Ressource_3= ruleRessource | this_RessourceSequence_4= ruleRessourceSequence ) ;
    public final EObject ruleProcessElement() throws RecognitionException {
        EObject current = null;

        EObject this_WorkDefinition_0 = null;

        EObject this_WorkSequence_1 = null;

        EObject this_Guidance_2 = null;

        EObject this_Ressource_3 = null;

        EObject this_RessourceSequence_4 = null;



        	enterRule();

        try {
            // InternalSimplePDLDSL.g:146:2: ( (this_WorkDefinition_0= ruleWorkDefinition | this_WorkSequence_1= ruleWorkSequence | this_Guidance_2= ruleGuidance | this_Ressource_3= ruleRessource | this_RessourceSequence_4= ruleRessourceSequence ) )
            // InternalSimplePDLDSL.g:147:2: (this_WorkDefinition_0= ruleWorkDefinition | this_WorkSequence_1= ruleWorkSequence | this_Guidance_2= ruleGuidance | this_Ressource_3= ruleRessource | this_RessourceSequence_4= ruleRessourceSequence )
            {
            // InternalSimplePDLDSL.g:147:2: (this_WorkDefinition_0= ruleWorkDefinition | this_WorkSequence_1= ruleWorkSequence | this_Guidance_2= ruleGuidance | this_Ressource_3= ruleRessource | this_RessourceSequence_4= ruleRessourceSequence )
            int alt2=5;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt2=1;
                }
                break;
            case 15:
                {
                alt2=2;
                }
                break;
            case 18:
                {
                alt2=3;
                }
                break;
            case 20:
                {
                alt2=4;
                }
                break;
            case 21:
                {
                alt2=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalSimplePDLDSL.g:148:3: this_WorkDefinition_0= ruleWorkDefinition
                    {

                    			newCompositeNode(grammarAccess.getProcessElementAccess().getWorkDefinitionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_WorkDefinition_0=ruleWorkDefinition();

                    state._fsp--;


                    			current = this_WorkDefinition_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalSimplePDLDSL.g:157:3: this_WorkSequence_1= ruleWorkSequence
                    {

                    			newCompositeNode(grammarAccess.getProcessElementAccess().getWorkSequenceParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_WorkSequence_1=ruleWorkSequence();

                    state._fsp--;


                    			current = this_WorkSequence_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalSimplePDLDSL.g:166:3: this_Guidance_2= ruleGuidance
                    {

                    			newCompositeNode(grammarAccess.getProcessElementAccess().getGuidanceParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Guidance_2=ruleGuidance();

                    state._fsp--;


                    			current = this_Guidance_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalSimplePDLDSL.g:175:3: this_Ressource_3= ruleRessource
                    {

                    			newCompositeNode(grammarAccess.getProcessElementAccess().getRessourceParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Ressource_3=ruleRessource();

                    state._fsp--;


                    			current = this_Ressource_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalSimplePDLDSL.g:184:3: this_RessourceSequence_4= ruleRessourceSequence
                    {

                    			newCompositeNode(grammarAccess.getProcessElementAccess().getRessourceSequenceParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_RessourceSequence_4=ruleRessourceSequence();

                    state._fsp--;


                    			current = this_RessourceSequence_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcessElement"


    // $ANTLR start "entryRuleWorkDefinition"
    // InternalSimplePDLDSL.g:196:1: entryRuleWorkDefinition returns [EObject current=null] : iv_ruleWorkDefinition= ruleWorkDefinition EOF ;
    public final EObject entryRuleWorkDefinition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWorkDefinition = null;


        try {
            // InternalSimplePDLDSL.g:196:55: (iv_ruleWorkDefinition= ruleWorkDefinition EOF )
            // InternalSimplePDLDSL.g:197:2: iv_ruleWorkDefinition= ruleWorkDefinition EOF
            {
             newCompositeNode(grammarAccess.getWorkDefinitionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWorkDefinition=ruleWorkDefinition();

            state._fsp--;

             current =iv_ruleWorkDefinition; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWorkDefinition"


    // $ANTLR start "ruleWorkDefinition"
    // InternalSimplePDLDSL.g:203:1: ruleWorkDefinition returns [EObject current=null] : (otherlv_0= 'wd' ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleWorkDefinition() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalSimplePDLDSL.g:209:2: ( (otherlv_0= 'wd' ( (lv_name_1_0= RULE_ID ) ) ) )
            // InternalSimplePDLDSL.g:210:2: (otherlv_0= 'wd' ( (lv_name_1_0= RULE_ID ) ) )
            {
            // InternalSimplePDLDSL.g:210:2: (otherlv_0= 'wd' ( (lv_name_1_0= RULE_ID ) ) )
            // InternalSimplePDLDSL.g:211:3: otherlv_0= 'wd' ( (lv_name_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,14,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getWorkDefinitionAccess().getWdKeyword_0());
            		
            // InternalSimplePDLDSL.g:215:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalSimplePDLDSL.g:216:4: (lv_name_1_0= RULE_ID )
            {
            // InternalSimplePDLDSL.g:216:4: (lv_name_1_0= RULE_ID )
            // InternalSimplePDLDSL.g:217:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getWorkDefinitionAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWorkDefinitionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkDefinition"


    // $ANTLR start "entryRuleWorkSequence"
    // InternalSimplePDLDSL.g:237:1: entryRuleWorkSequence returns [EObject current=null] : iv_ruleWorkSequence= ruleWorkSequence EOF ;
    public final EObject entryRuleWorkSequence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWorkSequence = null;


        try {
            // InternalSimplePDLDSL.g:237:53: (iv_ruleWorkSequence= ruleWorkSequence EOF )
            // InternalSimplePDLDSL.g:238:2: iv_ruleWorkSequence= ruleWorkSequence EOF
            {
             newCompositeNode(grammarAccess.getWorkSequenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWorkSequence=ruleWorkSequence();

            state._fsp--;

             current =iv_ruleWorkSequence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWorkSequence"


    // $ANTLR start "ruleWorkSequence"
    // InternalSimplePDLDSL.g:244:1: ruleWorkSequence returns [EObject current=null] : (otherlv_0= 'ws' ( (lv_linkType_1_0= ruleWorkSequenceType ) ) otherlv_2= 'from' ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) ) ;
    public final EObject ruleWorkSequence() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Enumerator lv_linkType_1_0 = null;



        	enterRule();

        try {
            // InternalSimplePDLDSL.g:250:2: ( (otherlv_0= 'ws' ( (lv_linkType_1_0= ruleWorkSequenceType ) ) otherlv_2= 'from' ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) ) )
            // InternalSimplePDLDSL.g:251:2: (otherlv_0= 'ws' ( (lv_linkType_1_0= ruleWorkSequenceType ) ) otherlv_2= 'from' ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) )
            {
            // InternalSimplePDLDSL.g:251:2: (otherlv_0= 'ws' ( (lv_linkType_1_0= ruleWorkSequenceType ) ) otherlv_2= 'from' ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) ) )
            // InternalSimplePDLDSL.g:252:3: otherlv_0= 'ws' ( (lv_linkType_1_0= ruleWorkSequenceType ) ) otherlv_2= 'from' ( (otherlv_3= RULE_ID ) ) otherlv_4= 'to' ( (otherlv_5= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,15,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getWorkSequenceAccess().getWsKeyword_0());
            		
            // InternalSimplePDLDSL.g:256:3: ( (lv_linkType_1_0= ruleWorkSequenceType ) )
            // InternalSimplePDLDSL.g:257:4: (lv_linkType_1_0= ruleWorkSequenceType )
            {
            // InternalSimplePDLDSL.g:257:4: (lv_linkType_1_0= ruleWorkSequenceType )
            // InternalSimplePDLDSL.g:258:5: lv_linkType_1_0= ruleWorkSequenceType
            {

            					newCompositeNode(grammarAccess.getWorkSequenceAccess().getLinkTypeWorkSequenceTypeEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_7);
            lv_linkType_1_0=ruleWorkSequenceType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWorkSequenceRule());
            					}
            					set(
            						current,
            						"linkType",
            						lv_linkType_1_0,
            						"fr.enseeiht.simplepdl.SimplePDLDSL.WorkSequenceType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getWorkSequenceAccess().getFromKeyword_2());
            		
            // InternalSimplePDLDSL.g:279:3: ( (otherlv_3= RULE_ID ) )
            // InternalSimplePDLDSL.g:280:4: (otherlv_3= RULE_ID )
            {
            // InternalSimplePDLDSL.g:280:4: (otherlv_3= RULE_ID )
            // InternalSimplePDLDSL.g:281:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWorkSequenceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(otherlv_3, grammarAccess.getWorkSequenceAccess().getPredecessorWorkDefinitionCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,17,FOLLOW_3); 

            			newLeafNode(otherlv_4, grammarAccess.getWorkSequenceAccess().getToKeyword_4());
            		
            // InternalSimplePDLDSL.g:296:3: ( (otherlv_5= RULE_ID ) )
            // InternalSimplePDLDSL.g:297:4: (otherlv_5= RULE_ID )
            {
            // InternalSimplePDLDSL.g:297:4: (otherlv_5= RULE_ID )
            // InternalSimplePDLDSL.g:298:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWorkSequenceRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_5, grammarAccess.getWorkSequenceAccess().getSuccessorWorkDefinitionCrossReference_5_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkSequence"


    // $ANTLR start "entryRuleGuidance"
    // InternalSimplePDLDSL.g:313:1: entryRuleGuidance returns [EObject current=null] : iv_ruleGuidance= ruleGuidance EOF ;
    public final EObject entryRuleGuidance() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGuidance = null;


        try {
            // InternalSimplePDLDSL.g:313:49: (iv_ruleGuidance= ruleGuidance EOF )
            // InternalSimplePDLDSL.g:314:2: iv_ruleGuidance= ruleGuidance EOF
            {
             newCompositeNode(grammarAccess.getGuidanceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGuidance=ruleGuidance();

            state._fsp--;

             current =iv_ruleGuidance; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGuidance"


    // $ANTLR start "ruleGuidance"
    // InternalSimplePDLDSL.g:320:1: ruleGuidance returns [EObject current=null] : (otherlv_0= 'gd' ( (lv_text_1_0= RULE_STRING ) ) otherlv_2= 'to' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ' ' ( (otherlv_5= RULE_ID ) ) )* ) ;
    public final EObject ruleGuidance() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_text_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalSimplePDLDSL.g:326:2: ( (otherlv_0= 'gd' ( (lv_text_1_0= RULE_STRING ) ) otherlv_2= 'to' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ' ' ( (otherlv_5= RULE_ID ) ) )* ) )
            // InternalSimplePDLDSL.g:327:2: (otherlv_0= 'gd' ( (lv_text_1_0= RULE_STRING ) ) otherlv_2= 'to' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ' ' ( (otherlv_5= RULE_ID ) ) )* )
            {
            // InternalSimplePDLDSL.g:327:2: (otherlv_0= 'gd' ( (lv_text_1_0= RULE_STRING ) ) otherlv_2= 'to' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ' ' ( (otherlv_5= RULE_ID ) ) )* )
            // InternalSimplePDLDSL.g:328:3: otherlv_0= 'gd' ( (lv_text_1_0= RULE_STRING ) ) otherlv_2= 'to' ( (otherlv_3= RULE_ID ) ) (otherlv_4= ' ' ( (otherlv_5= RULE_ID ) ) )*
            {
            otherlv_0=(Token)match(input,18,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getGuidanceAccess().getGdKeyword_0());
            		
            // InternalSimplePDLDSL.g:332:3: ( (lv_text_1_0= RULE_STRING ) )
            // InternalSimplePDLDSL.g:333:4: (lv_text_1_0= RULE_STRING )
            {
            // InternalSimplePDLDSL.g:333:4: (lv_text_1_0= RULE_STRING )
            // InternalSimplePDLDSL.g:334:5: lv_text_1_0= RULE_STRING
            {
            lv_text_1_0=(Token)match(input,RULE_STRING,FOLLOW_8); 

            					newLeafNode(lv_text_1_0, grammarAccess.getGuidanceAccess().getTextSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGuidanceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_1_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_2=(Token)match(input,17,FOLLOW_3); 

            			newLeafNode(otherlv_2, grammarAccess.getGuidanceAccess().getToKeyword_2());
            		
            // InternalSimplePDLDSL.g:354:3: ( (otherlv_3= RULE_ID ) )
            // InternalSimplePDLDSL.g:355:4: (otherlv_3= RULE_ID )
            {
            // InternalSimplePDLDSL.g:355:4: (otherlv_3= RULE_ID )
            // InternalSimplePDLDSL.g:356:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGuidanceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(otherlv_3, grammarAccess.getGuidanceAccess().getElementWorkDefinitionCrossReference_3_0());
            				

            }


            }

            // InternalSimplePDLDSL.g:367:3: (otherlv_4= ' ' ( (otherlv_5= RULE_ID ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==19) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalSimplePDLDSL.g:368:4: otherlv_4= ' ' ( (otherlv_5= RULE_ID ) )
            	    {
            	    otherlv_4=(Token)match(input,19,FOLLOW_3); 

            	    				newLeafNode(otherlv_4, grammarAccess.getGuidanceAccess().getSpaceKeyword_4_0());
            	    			
            	    // InternalSimplePDLDSL.g:372:4: ( (otherlv_5= RULE_ID ) )
            	    // InternalSimplePDLDSL.g:373:5: (otherlv_5= RULE_ID )
            	    {
            	    // InternalSimplePDLDSL.g:373:5: (otherlv_5= RULE_ID )
            	    // InternalSimplePDLDSL.g:374:6: otherlv_5= RULE_ID
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getGuidanceRule());
            	    						}
            	    					
            	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_10); 

            	    						newLeafNode(otherlv_5, grammarAccess.getGuidanceAccess().getElementWorkDefinitionCrossReference_4_1_0());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGuidance"


    // $ANTLR start "entryRuleRessource"
    // InternalSimplePDLDSL.g:390:1: entryRuleRessource returns [EObject current=null] : iv_ruleRessource= ruleRessource EOF ;
    public final EObject entryRuleRessource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRessource = null;


        try {
            // InternalSimplePDLDSL.g:390:50: (iv_ruleRessource= ruleRessource EOF )
            // InternalSimplePDLDSL.g:391:2: iv_ruleRessource= ruleRessource EOF
            {
             newCompositeNode(grammarAccess.getRessourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRessource=ruleRessource();

            state._fsp--;

             current =iv_ruleRessource; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRessource"


    // $ANTLR start "ruleRessource"
    // InternalSimplePDLDSL.g:397:1: ruleRessource returns [EObject current=null] : (otherlv_0= 're' ( (lv_name_1_0= RULE_ID ) ) ( (lv_quantity_2_0= RULE_INT ) ) ) ;
    public final EObject ruleRessource() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_quantity_2_0=null;


        	enterRule();

        try {
            // InternalSimplePDLDSL.g:403:2: ( (otherlv_0= 're' ( (lv_name_1_0= RULE_ID ) ) ( (lv_quantity_2_0= RULE_INT ) ) ) )
            // InternalSimplePDLDSL.g:404:2: (otherlv_0= 're' ( (lv_name_1_0= RULE_ID ) ) ( (lv_quantity_2_0= RULE_INT ) ) )
            {
            // InternalSimplePDLDSL.g:404:2: (otherlv_0= 're' ( (lv_name_1_0= RULE_ID ) ) ( (lv_quantity_2_0= RULE_INT ) ) )
            // InternalSimplePDLDSL.g:405:3: otherlv_0= 're' ( (lv_name_1_0= RULE_ID ) ) ( (lv_quantity_2_0= RULE_INT ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getRessourceAccess().getReKeyword_0());
            		
            // InternalSimplePDLDSL.g:409:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalSimplePDLDSL.g:410:4: (lv_name_1_0= RULE_ID )
            {
            // InternalSimplePDLDSL.g:410:4: (lv_name_1_0= RULE_ID )
            // InternalSimplePDLDSL.g:411:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(lv_name_1_0, grammarAccess.getRessourceAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRessourceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalSimplePDLDSL.g:427:3: ( (lv_quantity_2_0= RULE_INT ) )
            // InternalSimplePDLDSL.g:428:4: (lv_quantity_2_0= RULE_INT )
            {
            // InternalSimplePDLDSL.g:428:4: (lv_quantity_2_0= RULE_INT )
            // InternalSimplePDLDSL.g:429:5: lv_quantity_2_0= RULE_INT
            {
            lv_quantity_2_0=(Token)match(input,RULE_INT,FOLLOW_2); 

            					newLeafNode(lv_quantity_2_0, grammarAccess.getRessourceAccess().getQuantityINTTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRessourceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"quantity",
            						lv_quantity_2_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRessource"


    // $ANTLR start "entryRuleRessourceSequence"
    // InternalSimplePDLDSL.g:449:1: entryRuleRessourceSequence returns [EObject current=null] : iv_ruleRessourceSequence= ruleRessourceSequence EOF ;
    public final EObject entryRuleRessourceSequence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRessourceSequence = null;


        try {
            // InternalSimplePDLDSL.g:449:58: (iv_ruleRessourceSequence= ruleRessourceSequence EOF )
            // InternalSimplePDLDSL.g:450:2: iv_ruleRessourceSequence= ruleRessourceSequence EOF
            {
             newCompositeNode(grammarAccess.getRessourceSequenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRessourceSequence=ruleRessourceSequence();

            state._fsp--;

             current =iv_ruleRessourceSequence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRessourceSequence"


    // $ANTLR start "ruleRessourceSequence"
    // InternalSimplePDLDSL.g:456:1: ruleRessourceSequence returns [EObject current=null] : (otherlv_0= 'rs' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'needs' ( (lv_quantity_3_0= RULE_INT ) ) ( (otherlv_4= RULE_ID ) ) ) ;
    public final EObject ruleRessourceSequence() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_quantity_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalSimplePDLDSL.g:462:2: ( (otherlv_0= 'rs' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'needs' ( (lv_quantity_3_0= RULE_INT ) ) ( (otherlv_4= RULE_ID ) ) ) )
            // InternalSimplePDLDSL.g:463:2: (otherlv_0= 'rs' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'needs' ( (lv_quantity_3_0= RULE_INT ) ) ( (otherlv_4= RULE_ID ) ) )
            {
            // InternalSimplePDLDSL.g:463:2: (otherlv_0= 'rs' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'needs' ( (lv_quantity_3_0= RULE_INT ) ) ( (otherlv_4= RULE_ID ) ) )
            // InternalSimplePDLDSL.g:464:3: otherlv_0= 'rs' ( (otherlv_1= RULE_ID ) ) otherlv_2= 'needs' ( (lv_quantity_3_0= RULE_INT ) ) ( (otherlv_4= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,21,FOLLOW_3); 

            			newLeafNode(otherlv_0, grammarAccess.getRessourceSequenceAccess().getRsKeyword_0());
            		
            // InternalSimplePDLDSL.g:468:3: ( (otherlv_1= RULE_ID ) )
            // InternalSimplePDLDSL.g:469:4: (otherlv_1= RULE_ID )
            {
            // InternalSimplePDLDSL.g:469:4: (otherlv_1= RULE_ID )
            // InternalSimplePDLDSL.g:470:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRessourceSequenceRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_12); 

            					newLeafNode(otherlv_1, grammarAccess.getRessourceSequenceAccess().getWorkdefinitionWorkDefinitionCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,22,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getRessourceSequenceAccess().getNeedsKeyword_2());
            		
            // InternalSimplePDLDSL.g:485:3: ( (lv_quantity_3_0= RULE_INT ) )
            // InternalSimplePDLDSL.g:486:4: (lv_quantity_3_0= RULE_INT )
            {
            // InternalSimplePDLDSL.g:486:4: (lv_quantity_3_0= RULE_INT )
            // InternalSimplePDLDSL.g:487:5: lv_quantity_3_0= RULE_INT
            {
            lv_quantity_3_0=(Token)match(input,RULE_INT,FOLLOW_3); 

            					newLeafNode(lv_quantity_3_0, grammarAccess.getRessourceSequenceAccess().getQuantityINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRessourceSequenceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"quantity",
            						lv_quantity_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalSimplePDLDSL.g:503:3: ( (otherlv_4= RULE_ID ) )
            // InternalSimplePDLDSL.g:504:4: (otherlv_4= RULE_ID )
            {
            // InternalSimplePDLDSL.g:504:4: (otherlv_4= RULE_ID )
            // InternalSimplePDLDSL.g:505:5: otherlv_4= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRessourceSequenceRule());
            					}
            				
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_4, grammarAccess.getRessourceSequenceAccess().getRessourceRessourceCrossReference_4_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRessourceSequence"


    // $ANTLR start "ruleWorkSequenceType"
    // InternalSimplePDLDSL.g:520:1: ruleWorkSequenceType returns [Enumerator current=null] : ( (enumLiteral_0= 's2s' ) | (enumLiteral_1= 'f2s' ) | (enumLiteral_2= 's2f' ) | (enumLiteral_3= 'f2f' ) ) ;
    public final Enumerator ruleWorkSequenceType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;


        	enterRule();

        try {
            // InternalSimplePDLDSL.g:526:2: ( ( (enumLiteral_0= 's2s' ) | (enumLiteral_1= 'f2s' ) | (enumLiteral_2= 's2f' ) | (enumLiteral_3= 'f2f' ) ) )
            // InternalSimplePDLDSL.g:527:2: ( (enumLiteral_0= 's2s' ) | (enumLiteral_1= 'f2s' ) | (enumLiteral_2= 's2f' ) | (enumLiteral_3= 'f2f' ) )
            {
            // InternalSimplePDLDSL.g:527:2: ( (enumLiteral_0= 's2s' ) | (enumLiteral_1= 'f2s' ) | (enumLiteral_2= 's2f' ) | (enumLiteral_3= 'f2f' ) )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 23:
                {
                alt4=1;
                }
                break;
            case 24:
                {
                alt4=2;
                }
                break;
            case 25:
                {
                alt4=3;
                }
                break;
            case 26:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalSimplePDLDSL.g:528:3: (enumLiteral_0= 's2s' )
                    {
                    // InternalSimplePDLDSL.g:528:3: (enumLiteral_0= 's2s' )
                    // InternalSimplePDLDSL.g:529:4: enumLiteral_0= 's2s'
                    {
                    enumLiteral_0=(Token)match(input,23,FOLLOW_2); 

                    				current = grammarAccess.getWorkSequenceTypeAccess().getStartToStartEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getWorkSequenceTypeAccess().getStartToStartEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalSimplePDLDSL.g:536:3: (enumLiteral_1= 'f2s' )
                    {
                    // InternalSimplePDLDSL.g:536:3: (enumLiteral_1= 'f2s' )
                    // InternalSimplePDLDSL.g:537:4: enumLiteral_1= 'f2s'
                    {
                    enumLiteral_1=(Token)match(input,24,FOLLOW_2); 

                    				current = grammarAccess.getWorkSequenceTypeAccess().getFinishToStartEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getWorkSequenceTypeAccess().getFinishToStartEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalSimplePDLDSL.g:544:3: (enumLiteral_2= 's2f' )
                    {
                    // InternalSimplePDLDSL.g:544:3: (enumLiteral_2= 's2f' )
                    // InternalSimplePDLDSL.g:545:4: enumLiteral_2= 's2f'
                    {
                    enumLiteral_2=(Token)match(input,25,FOLLOW_2); 

                    				current = grammarAccess.getWorkSequenceTypeAccess().getStartToFinishEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getWorkSequenceTypeAccess().getStartToFinishEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalSimplePDLDSL.g:552:3: (enumLiteral_3= 'f2f' )
                    {
                    // InternalSimplePDLDSL.g:552:3: (enumLiteral_3= 'f2f' )
                    // InternalSimplePDLDSL.g:553:4: enumLiteral_3= 'f2f'
                    {
                    enumLiteral_3=(Token)match(input,26,FOLLOW_2); 

                    				current = grammarAccess.getWorkSequenceTypeAccess().getFinishToFinishEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getWorkSequenceTypeAccess().getFinishToFinishEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkSequenceType"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x000000000034E000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000007800000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400000L});

}