package fr.enseeiht.simplepdl.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import fr.enseeiht.simplepdl.services.SimplePDLDSLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSimplePDLDSLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'s2s'", "'f2s'", "'s2f'", "'f2f'", "'process'", "'{'", "'}'", "'wd'", "'ws'", "'from'", "'to'", "'gd'", "' '", "'re'", "'rs'", "'needs'"
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

    	public void setGrammarAccess(SimplePDLDSLGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleProcess"
    // InternalSimplePDLDSL.g:53:1: entryRuleProcess : ruleProcess EOF ;
    public final void entryRuleProcess() throws RecognitionException {
        try {
            // InternalSimplePDLDSL.g:54:1: ( ruleProcess EOF )
            // InternalSimplePDLDSL.g:55:1: ruleProcess EOF
            {
             before(grammarAccess.getProcessRule()); 
            pushFollow(FOLLOW_1);
            ruleProcess();

            state._fsp--;

             after(grammarAccess.getProcessRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleProcess"


    // $ANTLR start "ruleProcess"
    // InternalSimplePDLDSL.g:62:1: ruleProcess : ( ( rule__Process__Group__0 ) ) ;
    public final void ruleProcess() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:66:2: ( ( ( rule__Process__Group__0 ) ) )
            // InternalSimplePDLDSL.g:67:2: ( ( rule__Process__Group__0 ) )
            {
            // InternalSimplePDLDSL.g:67:2: ( ( rule__Process__Group__0 ) )
            // InternalSimplePDLDSL.g:68:3: ( rule__Process__Group__0 )
            {
             before(grammarAccess.getProcessAccess().getGroup()); 
            // InternalSimplePDLDSL.g:69:3: ( rule__Process__Group__0 )
            // InternalSimplePDLDSL.g:69:4: rule__Process__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Process__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getProcessAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleProcess"


    // $ANTLR start "entryRuleProcessElement"
    // InternalSimplePDLDSL.g:78:1: entryRuleProcessElement : ruleProcessElement EOF ;
    public final void entryRuleProcessElement() throws RecognitionException {
        try {
            // InternalSimplePDLDSL.g:79:1: ( ruleProcessElement EOF )
            // InternalSimplePDLDSL.g:80:1: ruleProcessElement EOF
            {
             before(grammarAccess.getProcessElementRule()); 
            pushFollow(FOLLOW_1);
            ruleProcessElement();

            state._fsp--;

             after(grammarAccess.getProcessElementRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleProcessElement"


    // $ANTLR start "ruleProcessElement"
    // InternalSimplePDLDSL.g:87:1: ruleProcessElement : ( ( rule__ProcessElement__Alternatives ) ) ;
    public final void ruleProcessElement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:91:2: ( ( ( rule__ProcessElement__Alternatives ) ) )
            // InternalSimplePDLDSL.g:92:2: ( ( rule__ProcessElement__Alternatives ) )
            {
            // InternalSimplePDLDSL.g:92:2: ( ( rule__ProcessElement__Alternatives ) )
            // InternalSimplePDLDSL.g:93:3: ( rule__ProcessElement__Alternatives )
            {
             before(grammarAccess.getProcessElementAccess().getAlternatives()); 
            // InternalSimplePDLDSL.g:94:3: ( rule__ProcessElement__Alternatives )
            // InternalSimplePDLDSL.g:94:4: rule__ProcessElement__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ProcessElement__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getProcessElementAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleProcessElement"


    // $ANTLR start "entryRuleWorkDefinition"
    // InternalSimplePDLDSL.g:103:1: entryRuleWorkDefinition : ruleWorkDefinition EOF ;
    public final void entryRuleWorkDefinition() throws RecognitionException {
        try {
            // InternalSimplePDLDSL.g:104:1: ( ruleWorkDefinition EOF )
            // InternalSimplePDLDSL.g:105:1: ruleWorkDefinition EOF
            {
             before(grammarAccess.getWorkDefinitionRule()); 
            pushFollow(FOLLOW_1);
            ruleWorkDefinition();

            state._fsp--;

             after(grammarAccess.getWorkDefinitionRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWorkDefinition"


    // $ANTLR start "ruleWorkDefinition"
    // InternalSimplePDLDSL.g:112:1: ruleWorkDefinition : ( ( rule__WorkDefinition__Group__0 ) ) ;
    public final void ruleWorkDefinition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:116:2: ( ( ( rule__WorkDefinition__Group__0 ) ) )
            // InternalSimplePDLDSL.g:117:2: ( ( rule__WorkDefinition__Group__0 ) )
            {
            // InternalSimplePDLDSL.g:117:2: ( ( rule__WorkDefinition__Group__0 ) )
            // InternalSimplePDLDSL.g:118:3: ( rule__WorkDefinition__Group__0 )
            {
             before(grammarAccess.getWorkDefinitionAccess().getGroup()); 
            // InternalSimplePDLDSL.g:119:3: ( rule__WorkDefinition__Group__0 )
            // InternalSimplePDLDSL.g:119:4: rule__WorkDefinition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__WorkDefinition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getWorkDefinitionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWorkDefinition"


    // $ANTLR start "entryRuleWorkSequence"
    // InternalSimplePDLDSL.g:128:1: entryRuleWorkSequence : ruleWorkSequence EOF ;
    public final void entryRuleWorkSequence() throws RecognitionException {
        try {
            // InternalSimplePDLDSL.g:129:1: ( ruleWorkSequence EOF )
            // InternalSimplePDLDSL.g:130:1: ruleWorkSequence EOF
            {
             before(grammarAccess.getWorkSequenceRule()); 
            pushFollow(FOLLOW_1);
            ruleWorkSequence();

            state._fsp--;

             after(grammarAccess.getWorkSequenceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleWorkSequence"


    // $ANTLR start "ruleWorkSequence"
    // InternalSimplePDLDSL.g:137:1: ruleWorkSequence : ( ( rule__WorkSequence__Group__0 ) ) ;
    public final void ruleWorkSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:141:2: ( ( ( rule__WorkSequence__Group__0 ) ) )
            // InternalSimplePDLDSL.g:142:2: ( ( rule__WorkSequence__Group__0 ) )
            {
            // InternalSimplePDLDSL.g:142:2: ( ( rule__WorkSequence__Group__0 ) )
            // InternalSimplePDLDSL.g:143:3: ( rule__WorkSequence__Group__0 )
            {
             before(grammarAccess.getWorkSequenceAccess().getGroup()); 
            // InternalSimplePDLDSL.g:144:3: ( rule__WorkSequence__Group__0 )
            // InternalSimplePDLDSL.g:144:4: rule__WorkSequence__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__WorkSequence__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getWorkSequenceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWorkSequence"


    // $ANTLR start "entryRuleGuidance"
    // InternalSimplePDLDSL.g:153:1: entryRuleGuidance : ruleGuidance EOF ;
    public final void entryRuleGuidance() throws RecognitionException {
        try {
            // InternalSimplePDLDSL.g:154:1: ( ruleGuidance EOF )
            // InternalSimplePDLDSL.g:155:1: ruleGuidance EOF
            {
             before(grammarAccess.getGuidanceRule()); 
            pushFollow(FOLLOW_1);
            ruleGuidance();

            state._fsp--;

             after(grammarAccess.getGuidanceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleGuidance"


    // $ANTLR start "ruleGuidance"
    // InternalSimplePDLDSL.g:162:1: ruleGuidance : ( ( rule__Guidance__Group__0 ) ) ;
    public final void ruleGuidance() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:166:2: ( ( ( rule__Guidance__Group__0 ) ) )
            // InternalSimplePDLDSL.g:167:2: ( ( rule__Guidance__Group__0 ) )
            {
            // InternalSimplePDLDSL.g:167:2: ( ( rule__Guidance__Group__0 ) )
            // InternalSimplePDLDSL.g:168:3: ( rule__Guidance__Group__0 )
            {
             before(grammarAccess.getGuidanceAccess().getGroup()); 
            // InternalSimplePDLDSL.g:169:3: ( rule__Guidance__Group__0 )
            // InternalSimplePDLDSL.g:169:4: rule__Guidance__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Guidance__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getGuidanceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleGuidance"


    // $ANTLR start "entryRuleRessource"
    // InternalSimplePDLDSL.g:178:1: entryRuleRessource : ruleRessource EOF ;
    public final void entryRuleRessource() throws RecognitionException {
        try {
            // InternalSimplePDLDSL.g:179:1: ( ruleRessource EOF )
            // InternalSimplePDLDSL.g:180:1: ruleRessource EOF
            {
             before(grammarAccess.getRessourceRule()); 
            pushFollow(FOLLOW_1);
            ruleRessource();

            state._fsp--;

             after(grammarAccess.getRessourceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRessource"


    // $ANTLR start "ruleRessource"
    // InternalSimplePDLDSL.g:187:1: ruleRessource : ( ( rule__Ressource__Group__0 ) ) ;
    public final void ruleRessource() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:191:2: ( ( ( rule__Ressource__Group__0 ) ) )
            // InternalSimplePDLDSL.g:192:2: ( ( rule__Ressource__Group__0 ) )
            {
            // InternalSimplePDLDSL.g:192:2: ( ( rule__Ressource__Group__0 ) )
            // InternalSimplePDLDSL.g:193:3: ( rule__Ressource__Group__0 )
            {
             before(grammarAccess.getRessourceAccess().getGroup()); 
            // InternalSimplePDLDSL.g:194:3: ( rule__Ressource__Group__0 )
            // InternalSimplePDLDSL.g:194:4: rule__Ressource__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Ressource__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRessourceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRessource"


    // $ANTLR start "entryRuleRessourceSequence"
    // InternalSimplePDLDSL.g:203:1: entryRuleRessourceSequence : ruleRessourceSequence EOF ;
    public final void entryRuleRessourceSequence() throws RecognitionException {
        try {
            // InternalSimplePDLDSL.g:204:1: ( ruleRessourceSequence EOF )
            // InternalSimplePDLDSL.g:205:1: ruleRessourceSequence EOF
            {
             before(grammarAccess.getRessourceSequenceRule()); 
            pushFollow(FOLLOW_1);
            ruleRessourceSequence();

            state._fsp--;

             after(grammarAccess.getRessourceSequenceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRessourceSequence"


    // $ANTLR start "ruleRessourceSequence"
    // InternalSimplePDLDSL.g:212:1: ruleRessourceSequence : ( ( rule__RessourceSequence__Group__0 ) ) ;
    public final void ruleRessourceSequence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:216:2: ( ( ( rule__RessourceSequence__Group__0 ) ) )
            // InternalSimplePDLDSL.g:217:2: ( ( rule__RessourceSequence__Group__0 ) )
            {
            // InternalSimplePDLDSL.g:217:2: ( ( rule__RessourceSequence__Group__0 ) )
            // InternalSimplePDLDSL.g:218:3: ( rule__RessourceSequence__Group__0 )
            {
             before(grammarAccess.getRessourceSequenceAccess().getGroup()); 
            // InternalSimplePDLDSL.g:219:3: ( rule__RessourceSequence__Group__0 )
            // InternalSimplePDLDSL.g:219:4: rule__RessourceSequence__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__RessourceSequence__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRessourceSequenceAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRessourceSequence"


    // $ANTLR start "ruleWorkSequenceType"
    // InternalSimplePDLDSL.g:228:1: ruleWorkSequenceType : ( ( rule__WorkSequenceType__Alternatives ) ) ;
    public final void ruleWorkSequenceType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:232:1: ( ( ( rule__WorkSequenceType__Alternatives ) ) )
            // InternalSimplePDLDSL.g:233:2: ( ( rule__WorkSequenceType__Alternatives ) )
            {
            // InternalSimplePDLDSL.g:233:2: ( ( rule__WorkSequenceType__Alternatives ) )
            // InternalSimplePDLDSL.g:234:3: ( rule__WorkSequenceType__Alternatives )
            {
             before(grammarAccess.getWorkSequenceTypeAccess().getAlternatives()); 
            // InternalSimplePDLDSL.g:235:3: ( rule__WorkSequenceType__Alternatives )
            // InternalSimplePDLDSL.g:235:4: rule__WorkSequenceType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__WorkSequenceType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getWorkSequenceTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleWorkSequenceType"


    // $ANTLR start "rule__ProcessElement__Alternatives"
    // InternalSimplePDLDSL.g:243:1: rule__ProcessElement__Alternatives : ( ( ruleWorkDefinition ) | ( ruleWorkSequence ) | ( ruleGuidance ) | ( ruleRessource ) | ( ruleRessourceSequence ) );
    public final void rule__ProcessElement__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:247:1: ( ( ruleWorkDefinition ) | ( ruleWorkSequence ) | ( ruleGuidance ) | ( ruleRessource ) | ( ruleRessourceSequence ) )
            int alt1=5;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt1=1;
                }
                break;
            case 19:
                {
                alt1=2;
                }
                break;
            case 22:
                {
                alt1=3;
                }
                break;
            case 24:
                {
                alt1=4;
                }
                break;
            case 25:
                {
                alt1=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalSimplePDLDSL.g:248:2: ( ruleWorkDefinition )
                    {
                    // InternalSimplePDLDSL.g:248:2: ( ruleWorkDefinition )
                    // InternalSimplePDLDSL.g:249:3: ruleWorkDefinition
                    {
                     before(grammarAccess.getProcessElementAccess().getWorkDefinitionParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleWorkDefinition();

                    state._fsp--;

                     after(grammarAccess.getProcessElementAccess().getWorkDefinitionParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSimplePDLDSL.g:254:2: ( ruleWorkSequence )
                    {
                    // InternalSimplePDLDSL.g:254:2: ( ruleWorkSequence )
                    // InternalSimplePDLDSL.g:255:3: ruleWorkSequence
                    {
                     before(grammarAccess.getProcessElementAccess().getWorkSequenceParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleWorkSequence();

                    state._fsp--;

                     after(grammarAccess.getProcessElementAccess().getWorkSequenceParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalSimplePDLDSL.g:260:2: ( ruleGuidance )
                    {
                    // InternalSimplePDLDSL.g:260:2: ( ruleGuidance )
                    // InternalSimplePDLDSL.g:261:3: ruleGuidance
                    {
                     before(grammarAccess.getProcessElementAccess().getGuidanceParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleGuidance();

                    state._fsp--;

                     after(grammarAccess.getProcessElementAccess().getGuidanceParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalSimplePDLDSL.g:266:2: ( ruleRessource )
                    {
                    // InternalSimplePDLDSL.g:266:2: ( ruleRessource )
                    // InternalSimplePDLDSL.g:267:3: ruleRessource
                    {
                     before(grammarAccess.getProcessElementAccess().getRessourceParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleRessource();

                    state._fsp--;

                     after(grammarAccess.getProcessElementAccess().getRessourceParserRuleCall_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalSimplePDLDSL.g:272:2: ( ruleRessourceSequence )
                    {
                    // InternalSimplePDLDSL.g:272:2: ( ruleRessourceSequence )
                    // InternalSimplePDLDSL.g:273:3: ruleRessourceSequence
                    {
                     before(grammarAccess.getProcessElementAccess().getRessourceSequenceParserRuleCall_4()); 
                    pushFollow(FOLLOW_2);
                    ruleRessourceSequence();

                    state._fsp--;

                     after(grammarAccess.getProcessElementAccess().getRessourceSequenceParserRuleCall_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ProcessElement__Alternatives"


    // $ANTLR start "rule__WorkSequenceType__Alternatives"
    // InternalSimplePDLDSL.g:282:1: rule__WorkSequenceType__Alternatives : ( ( ( 's2s' ) ) | ( ( 'f2s' ) ) | ( ( 's2f' ) ) | ( ( 'f2f' ) ) );
    public final void rule__WorkSequenceType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:286:1: ( ( ( 's2s' ) ) | ( ( 'f2s' ) ) | ( ( 's2f' ) ) | ( ( 'f2f' ) ) )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt2=1;
                }
                break;
            case 12:
                {
                alt2=2;
                }
                break;
            case 13:
                {
                alt2=3;
                }
                break;
            case 14:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalSimplePDLDSL.g:287:2: ( ( 's2s' ) )
                    {
                    // InternalSimplePDLDSL.g:287:2: ( ( 's2s' ) )
                    // InternalSimplePDLDSL.g:288:3: ( 's2s' )
                    {
                     before(grammarAccess.getWorkSequenceTypeAccess().getStartToStartEnumLiteralDeclaration_0()); 
                    // InternalSimplePDLDSL.g:289:3: ( 's2s' )
                    // InternalSimplePDLDSL.g:289:4: 's2s'
                    {
                    match(input,11,FOLLOW_2); 

                    }

                     after(grammarAccess.getWorkSequenceTypeAccess().getStartToStartEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSimplePDLDSL.g:293:2: ( ( 'f2s' ) )
                    {
                    // InternalSimplePDLDSL.g:293:2: ( ( 'f2s' ) )
                    // InternalSimplePDLDSL.g:294:3: ( 'f2s' )
                    {
                     before(grammarAccess.getWorkSequenceTypeAccess().getFinishToStartEnumLiteralDeclaration_1()); 
                    // InternalSimplePDLDSL.g:295:3: ( 'f2s' )
                    // InternalSimplePDLDSL.g:295:4: 'f2s'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getWorkSequenceTypeAccess().getFinishToStartEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalSimplePDLDSL.g:299:2: ( ( 's2f' ) )
                    {
                    // InternalSimplePDLDSL.g:299:2: ( ( 's2f' ) )
                    // InternalSimplePDLDSL.g:300:3: ( 's2f' )
                    {
                     before(grammarAccess.getWorkSequenceTypeAccess().getStartToFinishEnumLiteralDeclaration_2()); 
                    // InternalSimplePDLDSL.g:301:3: ( 's2f' )
                    // InternalSimplePDLDSL.g:301:4: 's2f'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getWorkSequenceTypeAccess().getStartToFinishEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalSimplePDLDSL.g:305:2: ( ( 'f2f' ) )
                    {
                    // InternalSimplePDLDSL.g:305:2: ( ( 'f2f' ) )
                    // InternalSimplePDLDSL.g:306:3: ( 'f2f' )
                    {
                     before(grammarAccess.getWorkSequenceTypeAccess().getFinishToFinishEnumLiteralDeclaration_3()); 
                    // InternalSimplePDLDSL.g:307:3: ( 'f2f' )
                    // InternalSimplePDLDSL.g:307:4: 'f2f'
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getWorkSequenceTypeAccess().getFinishToFinishEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequenceType__Alternatives"


    // $ANTLR start "rule__Process__Group__0"
    // InternalSimplePDLDSL.g:315:1: rule__Process__Group__0 : rule__Process__Group__0__Impl rule__Process__Group__1 ;
    public final void rule__Process__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:319:1: ( rule__Process__Group__0__Impl rule__Process__Group__1 )
            // InternalSimplePDLDSL.g:320:2: rule__Process__Group__0__Impl rule__Process__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Process__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Process__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__0"


    // $ANTLR start "rule__Process__Group__0__Impl"
    // InternalSimplePDLDSL.g:327:1: rule__Process__Group__0__Impl : ( 'process' ) ;
    public final void rule__Process__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:331:1: ( ( 'process' ) )
            // InternalSimplePDLDSL.g:332:1: ( 'process' )
            {
            // InternalSimplePDLDSL.g:332:1: ( 'process' )
            // InternalSimplePDLDSL.g:333:2: 'process'
            {
             before(grammarAccess.getProcessAccess().getProcessKeyword_0()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getProcessAccess().getProcessKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__0__Impl"


    // $ANTLR start "rule__Process__Group__1"
    // InternalSimplePDLDSL.g:342:1: rule__Process__Group__1 : rule__Process__Group__1__Impl rule__Process__Group__2 ;
    public final void rule__Process__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:346:1: ( rule__Process__Group__1__Impl rule__Process__Group__2 )
            // InternalSimplePDLDSL.g:347:2: rule__Process__Group__1__Impl rule__Process__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Process__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Process__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__1"


    // $ANTLR start "rule__Process__Group__1__Impl"
    // InternalSimplePDLDSL.g:354:1: rule__Process__Group__1__Impl : ( ( rule__Process__NameAssignment_1 ) ) ;
    public final void rule__Process__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:358:1: ( ( ( rule__Process__NameAssignment_1 ) ) )
            // InternalSimplePDLDSL.g:359:1: ( ( rule__Process__NameAssignment_1 ) )
            {
            // InternalSimplePDLDSL.g:359:1: ( ( rule__Process__NameAssignment_1 ) )
            // InternalSimplePDLDSL.g:360:2: ( rule__Process__NameAssignment_1 )
            {
             before(grammarAccess.getProcessAccess().getNameAssignment_1()); 
            // InternalSimplePDLDSL.g:361:2: ( rule__Process__NameAssignment_1 )
            // InternalSimplePDLDSL.g:361:3: rule__Process__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Process__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getProcessAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__1__Impl"


    // $ANTLR start "rule__Process__Group__2"
    // InternalSimplePDLDSL.g:369:1: rule__Process__Group__2 : rule__Process__Group__2__Impl rule__Process__Group__3 ;
    public final void rule__Process__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:373:1: ( rule__Process__Group__2__Impl rule__Process__Group__3 )
            // InternalSimplePDLDSL.g:374:2: rule__Process__Group__2__Impl rule__Process__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Process__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Process__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__2"


    // $ANTLR start "rule__Process__Group__2__Impl"
    // InternalSimplePDLDSL.g:381:1: rule__Process__Group__2__Impl : ( '{' ) ;
    public final void rule__Process__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:385:1: ( ( '{' ) )
            // InternalSimplePDLDSL.g:386:1: ( '{' )
            {
            // InternalSimplePDLDSL.g:386:1: ( '{' )
            // InternalSimplePDLDSL.g:387:2: '{'
            {
             before(grammarAccess.getProcessAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getProcessAccess().getLeftCurlyBracketKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__2__Impl"


    // $ANTLR start "rule__Process__Group__3"
    // InternalSimplePDLDSL.g:396:1: rule__Process__Group__3 : rule__Process__Group__3__Impl rule__Process__Group__4 ;
    public final void rule__Process__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:400:1: ( rule__Process__Group__3__Impl rule__Process__Group__4 )
            // InternalSimplePDLDSL.g:401:2: rule__Process__Group__3__Impl rule__Process__Group__4
            {
            pushFollow(FOLLOW_5);
            rule__Process__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Process__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__3"


    // $ANTLR start "rule__Process__Group__3__Impl"
    // InternalSimplePDLDSL.g:408:1: rule__Process__Group__3__Impl : ( ( rule__Process__ProcessElementsAssignment_3 )* ) ;
    public final void rule__Process__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:412:1: ( ( ( rule__Process__ProcessElementsAssignment_3 )* ) )
            // InternalSimplePDLDSL.g:413:1: ( ( rule__Process__ProcessElementsAssignment_3 )* )
            {
            // InternalSimplePDLDSL.g:413:1: ( ( rule__Process__ProcessElementsAssignment_3 )* )
            // InternalSimplePDLDSL.g:414:2: ( rule__Process__ProcessElementsAssignment_3 )*
            {
             before(grammarAccess.getProcessAccess().getProcessElementsAssignment_3()); 
            // InternalSimplePDLDSL.g:415:2: ( rule__Process__ProcessElementsAssignment_3 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=18 && LA3_0<=19)||LA3_0==22||(LA3_0>=24 && LA3_0<=25)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalSimplePDLDSL.g:415:3: rule__Process__ProcessElementsAssignment_3
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Process__ProcessElementsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getProcessAccess().getProcessElementsAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__3__Impl"


    // $ANTLR start "rule__Process__Group__4"
    // InternalSimplePDLDSL.g:423:1: rule__Process__Group__4 : rule__Process__Group__4__Impl ;
    public final void rule__Process__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:427:1: ( rule__Process__Group__4__Impl )
            // InternalSimplePDLDSL.g:428:2: rule__Process__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Process__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__4"


    // $ANTLR start "rule__Process__Group__4__Impl"
    // InternalSimplePDLDSL.g:434:1: rule__Process__Group__4__Impl : ( '}' ) ;
    public final void rule__Process__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:438:1: ( ( '}' ) )
            // InternalSimplePDLDSL.g:439:1: ( '}' )
            {
            // InternalSimplePDLDSL.g:439:1: ( '}' )
            // InternalSimplePDLDSL.g:440:2: '}'
            {
             before(grammarAccess.getProcessAccess().getRightCurlyBracketKeyword_4()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getProcessAccess().getRightCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__Group__4__Impl"


    // $ANTLR start "rule__WorkDefinition__Group__0"
    // InternalSimplePDLDSL.g:450:1: rule__WorkDefinition__Group__0 : rule__WorkDefinition__Group__0__Impl rule__WorkDefinition__Group__1 ;
    public final void rule__WorkDefinition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:454:1: ( rule__WorkDefinition__Group__0__Impl rule__WorkDefinition__Group__1 )
            // InternalSimplePDLDSL.g:455:2: rule__WorkDefinition__Group__0__Impl rule__WorkDefinition__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__WorkDefinition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkDefinition__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkDefinition__Group__0"


    // $ANTLR start "rule__WorkDefinition__Group__0__Impl"
    // InternalSimplePDLDSL.g:462:1: rule__WorkDefinition__Group__0__Impl : ( 'wd' ) ;
    public final void rule__WorkDefinition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:466:1: ( ( 'wd' ) )
            // InternalSimplePDLDSL.g:467:1: ( 'wd' )
            {
            // InternalSimplePDLDSL.g:467:1: ( 'wd' )
            // InternalSimplePDLDSL.g:468:2: 'wd'
            {
             before(grammarAccess.getWorkDefinitionAccess().getWdKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getWorkDefinitionAccess().getWdKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkDefinition__Group__0__Impl"


    // $ANTLR start "rule__WorkDefinition__Group__1"
    // InternalSimplePDLDSL.g:477:1: rule__WorkDefinition__Group__1 : rule__WorkDefinition__Group__1__Impl ;
    public final void rule__WorkDefinition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:481:1: ( rule__WorkDefinition__Group__1__Impl )
            // InternalSimplePDLDSL.g:482:2: rule__WorkDefinition__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WorkDefinition__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkDefinition__Group__1"


    // $ANTLR start "rule__WorkDefinition__Group__1__Impl"
    // InternalSimplePDLDSL.g:488:1: rule__WorkDefinition__Group__1__Impl : ( ( rule__WorkDefinition__NameAssignment_1 ) ) ;
    public final void rule__WorkDefinition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:492:1: ( ( ( rule__WorkDefinition__NameAssignment_1 ) ) )
            // InternalSimplePDLDSL.g:493:1: ( ( rule__WorkDefinition__NameAssignment_1 ) )
            {
            // InternalSimplePDLDSL.g:493:1: ( ( rule__WorkDefinition__NameAssignment_1 ) )
            // InternalSimplePDLDSL.g:494:2: ( rule__WorkDefinition__NameAssignment_1 )
            {
             before(grammarAccess.getWorkDefinitionAccess().getNameAssignment_1()); 
            // InternalSimplePDLDSL.g:495:2: ( rule__WorkDefinition__NameAssignment_1 )
            // InternalSimplePDLDSL.g:495:3: rule__WorkDefinition__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__WorkDefinition__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getWorkDefinitionAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkDefinition__Group__1__Impl"


    // $ANTLR start "rule__WorkSequence__Group__0"
    // InternalSimplePDLDSL.g:504:1: rule__WorkSequence__Group__0 : rule__WorkSequence__Group__0__Impl rule__WorkSequence__Group__1 ;
    public final void rule__WorkSequence__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:508:1: ( rule__WorkSequence__Group__0__Impl rule__WorkSequence__Group__1 )
            // InternalSimplePDLDSL.g:509:2: rule__WorkSequence__Group__0__Impl rule__WorkSequence__Group__1
            {
            pushFollow(FOLLOW_7);
            rule__WorkSequence__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkSequence__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__0"


    // $ANTLR start "rule__WorkSequence__Group__0__Impl"
    // InternalSimplePDLDSL.g:516:1: rule__WorkSequence__Group__0__Impl : ( 'ws' ) ;
    public final void rule__WorkSequence__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:520:1: ( ( 'ws' ) )
            // InternalSimplePDLDSL.g:521:1: ( 'ws' )
            {
            // InternalSimplePDLDSL.g:521:1: ( 'ws' )
            // InternalSimplePDLDSL.g:522:2: 'ws'
            {
             before(grammarAccess.getWorkSequenceAccess().getWsKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getWorkSequenceAccess().getWsKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__0__Impl"


    // $ANTLR start "rule__WorkSequence__Group__1"
    // InternalSimplePDLDSL.g:531:1: rule__WorkSequence__Group__1 : rule__WorkSequence__Group__1__Impl rule__WorkSequence__Group__2 ;
    public final void rule__WorkSequence__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:535:1: ( rule__WorkSequence__Group__1__Impl rule__WorkSequence__Group__2 )
            // InternalSimplePDLDSL.g:536:2: rule__WorkSequence__Group__1__Impl rule__WorkSequence__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__WorkSequence__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkSequence__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__1"


    // $ANTLR start "rule__WorkSequence__Group__1__Impl"
    // InternalSimplePDLDSL.g:543:1: rule__WorkSequence__Group__1__Impl : ( ( rule__WorkSequence__LinkTypeAssignment_1 ) ) ;
    public final void rule__WorkSequence__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:547:1: ( ( ( rule__WorkSequence__LinkTypeAssignment_1 ) ) )
            // InternalSimplePDLDSL.g:548:1: ( ( rule__WorkSequence__LinkTypeAssignment_1 ) )
            {
            // InternalSimplePDLDSL.g:548:1: ( ( rule__WorkSequence__LinkTypeAssignment_1 ) )
            // InternalSimplePDLDSL.g:549:2: ( rule__WorkSequence__LinkTypeAssignment_1 )
            {
             before(grammarAccess.getWorkSequenceAccess().getLinkTypeAssignment_1()); 
            // InternalSimplePDLDSL.g:550:2: ( rule__WorkSequence__LinkTypeAssignment_1 )
            // InternalSimplePDLDSL.g:550:3: rule__WorkSequence__LinkTypeAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__WorkSequence__LinkTypeAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getWorkSequenceAccess().getLinkTypeAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__1__Impl"


    // $ANTLR start "rule__WorkSequence__Group__2"
    // InternalSimplePDLDSL.g:558:1: rule__WorkSequence__Group__2 : rule__WorkSequence__Group__2__Impl rule__WorkSequence__Group__3 ;
    public final void rule__WorkSequence__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:562:1: ( rule__WorkSequence__Group__2__Impl rule__WorkSequence__Group__3 )
            // InternalSimplePDLDSL.g:563:2: rule__WorkSequence__Group__2__Impl rule__WorkSequence__Group__3
            {
            pushFollow(FOLLOW_3);
            rule__WorkSequence__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkSequence__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__2"


    // $ANTLR start "rule__WorkSequence__Group__2__Impl"
    // InternalSimplePDLDSL.g:570:1: rule__WorkSequence__Group__2__Impl : ( 'from' ) ;
    public final void rule__WorkSequence__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:574:1: ( ( 'from' ) )
            // InternalSimplePDLDSL.g:575:1: ( 'from' )
            {
            // InternalSimplePDLDSL.g:575:1: ( 'from' )
            // InternalSimplePDLDSL.g:576:2: 'from'
            {
             before(grammarAccess.getWorkSequenceAccess().getFromKeyword_2()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getWorkSequenceAccess().getFromKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__2__Impl"


    // $ANTLR start "rule__WorkSequence__Group__3"
    // InternalSimplePDLDSL.g:585:1: rule__WorkSequence__Group__3 : rule__WorkSequence__Group__3__Impl rule__WorkSequence__Group__4 ;
    public final void rule__WorkSequence__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:589:1: ( rule__WorkSequence__Group__3__Impl rule__WorkSequence__Group__4 )
            // InternalSimplePDLDSL.g:590:2: rule__WorkSequence__Group__3__Impl rule__WorkSequence__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__WorkSequence__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkSequence__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__3"


    // $ANTLR start "rule__WorkSequence__Group__3__Impl"
    // InternalSimplePDLDSL.g:597:1: rule__WorkSequence__Group__3__Impl : ( ( rule__WorkSequence__PredecessorAssignment_3 ) ) ;
    public final void rule__WorkSequence__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:601:1: ( ( ( rule__WorkSequence__PredecessorAssignment_3 ) ) )
            // InternalSimplePDLDSL.g:602:1: ( ( rule__WorkSequence__PredecessorAssignment_3 ) )
            {
            // InternalSimplePDLDSL.g:602:1: ( ( rule__WorkSequence__PredecessorAssignment_3 ) )
            // InternalSimplePDLDSL.g:603:2: ( rule__WorkSequence__PredecessorAssignment_3 )
            {
             before(grammarAccess.getWorkSequenceAccess().getPredecessorAssignment_3()); 
            // InternalSimplePDLDSL.g:604:2: ( rule__WorkSequence__PredecessorAssignment_3 )
            // InternalSimplePDLDSL.g:604:3: rule__WorkSequence__PredecessorAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__WorkSequence__PredecessorAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getWorkSequenceAccess().getPredecessorAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__3__Impl"


    // $ANTLR start "rule__WorkSequence__Group__4"
    // InternalSimplePDLDSL.g:612:1: rule__WorkSequence__Group__4 : rule__WorkSequence__Group__4__Impl rule__WorkSequence__Group__5 ;
    public final void rule__WorkSequence__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:616:1: ( rule__WorkSequence__Group__4__Impl rule__WorkSequence__Group__5 )
            // InternalSimplePDLDSL.g:617:2: rule__WorkSequence__Group__4__Impl rule__WorkSequence__Group__5
            {
            pushFollow(FOLLOW_3);
            rule__WorkSequence__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__WorkSequence__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__4"


    // $ANTLR start "rule__WorkSequence__Group__4__Impl"
    // InternalSimplePDLDSL.g:624:1: rule__WorkSequence__Group__4__Impl : ( 'to' ) ;
    public final void rule__WorkSequence__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:628:1: ( ( 'to' ) )
            // InternalSimplePDLDSL.g:629:1: ( 'to' )
            {
            // InternalSimplePDLDSL.g:629:1: ( 'to' )
            // InternalSimplePDLDSL.g:630:2: 'to'
            {
             before(grammarAccess.getWorkSequenceAccess().getToKeyword_4()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getWorkSequenceAccess().getToKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__4__Impl"


    // $ANTLR start "rule__WorkSequence__Group__5"
    // InternalSimplePDLDSL.g:639:1: rule__WorkSequence__Group__5 : rule__WorkSequence__Group__5__Impl ;
    public final void rule__WorkSequence__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:643:1: ( rule__WorkSequence__Group__5__Impl )
            // InternalSimplePDLDSL.g:644:2: rule__WorkSequence__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__WorkSequence__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__5"


    // $ANTLR start "rule__WorkSequence__Group__5__Impl"
    // InternalSimplePDLDSL.g:650:1: rule__WorkSequence__Group__5__Impl : ( ( rule__WorkSequence__SuccessorAssignment_5 ) ) ;
    public final void rule__WorkSequence__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:654:1: ( ( ( rule__WorkSequence__SuccessorAssignment_5 ) ) )
            // InternalSimplePDLDSL.g:655:1: ( ( rule__WorkSequence__SuccessorAssignment_5 ) )
            {
            // InternalSimplePDLDSL.g:655:1: ( ( rule__WorkSequence__SuccessorAssignment_5 ) )
            // InternalSimplePDLDSL.g:656:2: ( rule__WorkSequence__SuccessorAssignment_5 )
            {
             before(grammarAccess.getWorkSequenceAccess().getSuccessorAssignment_5()); 
            // InternalSimplePDLDSL.g:657:2: ( rule__WorkSequence__SuccessorAssignment_5 )
            // InternalSimplePDLDSL.g:657:3: rule__WorkSequence__SuccessorAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__WorkSequence__SuccessorAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getWorkSequenceAccess().getSuccessorAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__Group__5__Impl"


    // $ANTLR start "rule__Guidance__Group__0"
    // InternalSimplePDLDSL.g:666:1: rule__Guidance__Group__0 : rule__Guidance__Group__0__Impl rule__Guidance__Group__1 ;
    public final void rule__Guidance__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:670:1: ( rule__Guidance__Group__0__Impl rule__Guidance__Group__1 )
            // InternalSimplePDLDSL.g:671:2: rule__Guidance__Group__0__Impl rule__Guidance__Group__1
            {
            pushFollow(FOLLOW_10);
            rule__Guidance__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Guidance__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__0"


    // $ANTLR start "rule__Guidance__Group__0__Impl"
    // InternalSimplePDLDSL.g:678:1: rule__Guidance__Group__0__Impl : ( 'gd' ) ;
    public final void rule__Guidance__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:682:1: ( ( 'gd' ) )
            // InternalSimplePDLDSL.g:683:1: ( 'gd' )
            {
            // InternalSimplePDLDSL.g:683:1: ( 'gd' )
            // InternalSimplePDLDSL.g:684:2: 'gd'
            {
             before(grammarAccess.getGuidanceAccess().getGdKeyword_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getGuidanceAccess().getGdKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__0__Impl"


    // $ANTLR start "rule__Guidance__Group__1"
    // InternalSimplePDLDSL.g:693:1: rule__Guidance__Group__1 : rule__Guidance__Group__1__Impl rule__Guidance__Group__2 ;
    public final void rule__Guidance__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:697:1: ( rule__Guidance__Group__1__Impl rule__Guidance__Group__2 )
            // InternalSimplePDLDSL.g:698:2: rule__Guidance__Group__1__Impl rule__Guidance__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__Guidance__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Guidance__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__1"


    // $ANTLR start "rule__Guidance__Group__1__Impl"
    // InternalSimplePDLDSL.g:705:1: rule__Guidance__Group__1__Impl : ( ( rule__Guidance__TextAssignment_1 ) ) ;
    public final void rule__Guidance__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:709:1: ( ( ( rule__Guidance__TextAssignment_1 ) ) )
            // InternalSimplePDLDSL.g:710:1: ( ( rule__Guidance__TextAssignment_1 ) )
            {
            // InternalSimplePDLDSL.g:710:1: ( ( rule__Guidance__TextAssignment_1 ) )
            // InternalSimplePDLDSL.g:711:2: ( rule__Guidance__TextAssignment_1 )
            {
             before(grammarAccess.getGuidanceAccess().getTextAssignment_1()); 
            // InternalSimplePDLDSL.g:712:2: ( rule__Guidance__TextAssignment_1 )
            // InternalSimplePDLDSL.g:712:3: rule__Guidance__TextAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Guidance__TextAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getGuidanceAccess().getTextAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__1__Impl"


    // $ANTLR start "rule__Guidance__Group__2"
    // InternalSimplePDLDSL.g:720:1: rule__Guidance__Group__2 : rule__Guidance__Group__2__Impl rule__Guidance__Group__3 ;
    public final void rule__Guidance__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:724:1: ( rule__Guidance__Group__2__Impl rule__Guidance__Group__3 )
            // InternalSimplePDLDSL.g:725:2: rule__Guidance__Group__2__Impl rule__Guidance__Group__3
            {
            pushFollow(FOLLOW_3);
            rule__Guidance__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Guidance__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__2"


    // $ANTLR start "rule__Guidance__Group__2__Impl"
    // InternalSimplePDLDSL.g:732:1: rule__Guidance__Group__2__Impl : ( 'to' ) ;
    public final void rule__Guidance__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:736:1: ( ( 'to' ) )
            // InternalSimplePDLDSL.g:737:1: ( 'to' )
            {
            // InternalSimplePDLDSL.g:737:1: ( 'to' )
            // InternalSimplePDLDSL.g:738:2: 'to'
            {
             before(grammarAccess.getGuidanceAccess().getToKeyword_2()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getGuidanceAccess().getToKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__2__Impl"


    // $ANTLR start "rule__Guidance__Group__3"
    // InternalSimplePDLDSL.g:747:1: rule__Guidance__Group__3 : rule__Guidance__Group__3__Impl rule__Guidance__Group__4 ;
    public final void rule__Guidance__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:751:1: ( rule__Guidance__Group__3__Impl rule__Guidance__Group__4 )
            // InternalSimplePDLDSL.g:752:2: rule__Guidance__Group__3__Impl rule__Guidance__Group__4
            {
            pushFollow(FOLLOW_11);
            rule__Guidance__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Guidance__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__3"


    // $ANTLR start "rule__Guidance__Group__3__Impl"
    // InternalSimplePDLDSL.g:759:1: rule__Guidance__Group__3__Impl : ( ( rule__Guidance__ElementAssignment_3 ) ) ;
    public final void rule__Guidance__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:763:1: ( ( ( rule__Guidance__ElementAssignment_3 ) ) )
            // InternalSimplePDLDSL.g:764:1: ( ( rule__Guidance__ElementAssignment_3 ) )
            {
            // InternalSimplePDLDSL.g:764:1: ( ( rule__Guidance__ElementAssignment_3 ) )
            // InternalSimplePDLDSL.g:765:2: ( rule__Guidance__ElementAssignment_3 )
            {
             before(grammarAccess.getGuidanceAccess().getElementAssignment_3()); 
            // InternalSimplePDLDSL.g:766:2: ( rule__Guidance__ElementAssignment_3 )
            // InternalSimplePDLDSL.g:766:3: rule__Guidance__ElementAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Guidance__ElementAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getGuidanceAccess().getElementAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__3__Impl"


    // $ANTLR start "rule__Guidance__Group__4"
    // InternalSimplePDLDSL.g:774:1: rule__Guidance__Group__4 : rule__Guidance__Group__4__Impl ;
    public final void rule__Guidance__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:778:1: ( rule__Guidance__Group__4__Impl )
            // InternalSimplePDLDSL.g:779:2: rule__Guidance__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Guidance__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__4"


    // $ANTLR start "rule__Guidance__Group__4__Impl"
    // InternalSimplePDLDSL.g:785:1: rule__Guidance__Group__4__Impl : ( ( rule__Guidance__Group_4__0 )* ) ;
    public final void rule__Guidance__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:789:1: ( ( ( rule__Guidance__Group_4__0 )* ) )
            // InternalSimplePDLDSL.g:790:1: ( ( rule__Guidance__Group_4__0 )* )
            {
            // InternalSimplePDLDSL.g:790:1: ( ( rule__Guidance__Group_4__0 )* )
            // InternalSimplePDLDSL.g:791:2: ( rule__Guidance__Group_4__0 )*
            {
             before(grammarAccess.getGuidanceAccess().getGroup_4()); 
            // InternalSimplePDLDSL.g:792:2: ( rule__Guidance__Group_4__0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==23) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalSimplePDLDSL.g:792:3: rule__Guidance__Group_4__0
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__Guidance__Group_4__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getGuidanceAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group__4__Impl"


    // $ANTLR start "rule__Guidance__Group_4__0"
    // InternalSimplePDLDSL.g:801:1: rule__Guidance__Group_4__0 : rule__Guidance__Group_4__0__Impl rule__Guidance__Group_4__1 ;
    public final void rule__Guidance__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:805:1: ( rule__Guidance__Group_4__0__Impl rule__Guidance__Group_4__1 )
            // InternalSimplePDLDSL.g:806:2: rule__Guidance__Group_4__0__Impl rule__Guidance__Group_4__1
            {
            pushFollow(FOLLOW_3);
            rule__Guidance__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Guidance__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group_4__0"


    // $ANTLR start "rule__Guidance__Group_4__0__Impl"
    // InternalSimplePDLDSL.g:813:1: rule__Guidance__Group_4__0__Impl : ( ' ' ) ;
    public final void rule__Guidance__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:817:1: ( ( ' ' ) )
            // InternalSimplePDLDSL.g:818:1: ( ' ' )
            {
            // InternalSimplePDLDSL.g:818:1: ( ' ' )
            // InternalSimplePDLDSL.g:819:2: ' '
            {
             before(grammarAccess.getGuidanceAccess().getSpaceKeyword_4_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getGuidanceAccess().getSpaceKeyword_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group_4__0__Impl"


    // $ANTLR start "rule__Guidance__Group_4__1"
    // InternalSimplePDLDSL.g:828:1: rule__Guidance__Group_4__1 : rule__Guidance__Group_4__1__Impl ;
    public final void rule__Guidance__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:832:1: ( rule__Guidance__Group_4__1__Impl )
            // InternalSimplePDLDSL.g:833:2: rule__Guidance__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Guidance__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group_4__1"


    // $ANTLR start "rule__Guidance__Group_4__1__Impl"
    // InternalSimplePDLDSL.g:839:1: rule__Guidance__Group_4__1__Impl : ( ( rule__Guidance__ElementAssignment_4_1 ) ) ;
    public final void rule__Guidance__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:843:1: ( ( ( rule__Guidance__ElementAssignment_4_1 ) ) )
            // InternalSimplePDLDSL.g:844:1: ( ( rule__Guidance__ElementAssignment_4_1 ) )
            {
            // InternalSimplePDLDSL.g:844:1: ( ( rule__Guidance__ElementAssignment_4_1 ) )
            // InternalSimplePDLDSL.g:845:2: ( rule__Guidance__ElementAssignment_4_1 )
            {
             before(grammarAccess.getGuidanceAccess().getElementAssignment_4_1()); 
            // InternalSimplePDLDSL.g:846:2: ( rule__Guidance__ElementAssignment_4_1 )
            // InternalSimplePDLDSL.g:846:3: rule__Guidance__ElementAssignment_4_1
            {
            pushFollow(FOLLOW_2);
            rule__Guidance__ElementAssignment_4_1();

            state._fsp--;


            }

             after(grammarAccess.getGuidanceAccess().getElementAssignment_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__Group_4__1__Impl"


    // $ANTLR start "rule__Ressource__Group__0"
    // InternalSimplePDLDSL.g:855:1: rule__Ressource__Group__0 : rule__Ressource__Group__0__Impl rule__Ressource__Group__1 ;
    public final void rule__Ressource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:859:1: ( rule__Ressource__Group__0__Impl rule__Ressource__Group__1 )
            // InternalSimplePDLDSL.g:860:2: rule__Ressource__Group__0__Impl rule__Ressource__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Ressource__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Ressource__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__Group__0"


    // $ANTLR start "rule__Ressource__Group__0__Impl"
    // InternalSimplePDLDSL.g:867:1: rule__Ressource__Group__0__Impl : ( 're' ) ;
    public final void rule__Ressource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:871:1: ( ( 're' ) )
            // InternalSimplePDLDSL.g:872:1: ( 're' )
            {
            // InternalSimplePDLDSL.g:872:1: ( 're' )
            // InternalSimplePDLDSL.g:873:2: 're'
            {
             before(grammarAccess.getRessourceAccess().getReKeyword_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getRessourceAccess().getReKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__Group__0__Impl"


    // $ANTLR start "rule__Ressource__Group__1"
    // InternalSimplePDLDSL.g:882:1: rule__Ressource__Group__1 : rule__Ressource__Group__1__Impl rule__Ressource__Group__2 ;
    public final void rule__Ressource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:886:1: ( rule__Ressource__Group__1__Impl rule__Ressource__Group__2 )
            // InternalSimplePDLDSL.g:887:2: rule__Ressource__Group__1__Impl rule__Ressource__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__Ressource__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Ressource__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__Group__1"


    // $ANTLR start "rule__Ressource__Group__1__Impl"
    // InternalSimplePDLDSL.g:894:1: rule__Ressource__Group__1__Impl : ( ( rule__Ressource__NameAssignment_1 ) ) ;
    public final void rule__Ressource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:898:1: ( ( ( rule__Ressource__NameAssignment_1 ) ) )
            // InternalSimplePDLDSL.g:899:1: ( ( rule__Ressource__NameAssignment_1 ) )
            {
            // InternalSimplePDLDSL.g:899:1: ( ( rule__Ressource__NameAssignment_1 ) )
            // InternalSimplePDLDSL.g:900:2: ( rule__Ressource__NameAssignment_1 )
            {
             before(grammarAccess.getRessourceAccess().getNameAssignment_1()); 
            // InternalSimplePDLDSL.g:901:2: ( rule__Ressource__NameAssignment_1 )
            // InternalSimplePDLDSL.g:901:3: rule__Ressource__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Ressource__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRessourceAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__Group__1__Impl"


    // $ANTLR start "rule__Ressource__Group__2"
    // InternalSimplePDLDSL.g:909:1: rule__Ressource__Group__2 : rule__Ressource__Group__2__Impl ;
    public final void rule__Ressource__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:913:1: ( rule__Ressource__Group__2__Impl )
            // InternalSimplePDLDSL.g:914:2: rule__Ressource__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Ressource__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__Group__2"


    // $ANTLR start "rule__Ressource__Group__2__Impl"
    // InternalSimplePDLDSL.g:920:1: rule__Ressource__Group__2__Impl : ( ( rule__Ressource__QuantityAssignment_2 ) ) ;
    public final void rule__Ressource__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:924:1: ( ( ( rule__Ressource__QuantityAssignment_2 ) ) )
            // InternalSimplePDLDSL.g:925:1: ( ( rule__Ressource__QuantityAssignment_2 ) )
            {
            // InternalSimplePDLDSL.g:925:1: ( ( rule__Ressource__QuantityAssignment_2 ) )
            // InternalSimplePDLDSL.g:926:2: ( rule__Ressource__QuantityAssignment_2 )
            {
             before(grammarAccess.getRessourceAccess().getQuantityAssignment_2()); 
            // InternalSimplePDLDSL.g:927:2: ( rule__Ressource__QuantityAssignment_2 )
            // InternalSimplePDLDSL.g:927:3: rule__Ressource__QuantityAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Ressource__QuantityAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getRessourceAccess().getQuantityAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__Group__2__Impl"


    // $ANTLR start "rule__RessourceSequence__Group__0"
    // InternalSimplePDLDSL.g:936:1: rule__RessourceSequence__Group__0 : rule__RessourceSequence__Group__0__Impl rule__RessourceSequence__Group__1 ;
    public final void rule__RessourceSequence__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:940:1: ( rule__RessourceSequence__Group__0__Impl rule__RessourceSequence__Group__1 )
            // InternalSimplePDLDSL.g:941:2: rule__RessourceSequence__Group__0__Impl rule__RessourceSequence__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__RessourceSequence__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RessourceSequence__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__0"


    // $ANTLR start "rule__RessourceSequence__Group__0__Impl"
    // InternalSimplePDLDSL.g:948:1: rule__RessourceSequence__Group__0__Impl : ( 'rs' ) ;
    public final void rule__RessourceSequence__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:952:1: ( ( 'rs' ) )
            // InternalSimplePDLDSL.g:953:1: ( 'rs' )
            {
            // InternalSimplePDLDSL.g:953:1: ( 'rs' )
            // InternalSimplePDLDSL.g:954:2: 'rs'
            {
             before(grammarAccess.getRessourceSequenceAccess().getRsKeyword_0()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getRessourceSequenceAccess().getRsKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__0__Impl"


    // $ANTLR start "rule__RessourceSequence__Group__1"
    // InternalSimplePDLDSL.g:963:1: rule__RessourceSequence__Group__1 : rule__RessourceSequence__Group__1__Impl rule__RessourceSequence__Group__2 ;
    public final void rule__RessourceSequence__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:967:1: ( rule__RessourceSequence__Group__1__Impl rule__RessourceSequence__Group__2 )
            // InternalSimplePDLDSL.g:968:2: rule__RessourceSequence__Group__1__Impl rule__RessourceSequence__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__RessourceSequence__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RessourceSequence__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__1"


    // $ANTLR start "rule__RessourceSequence__Group__1__Impl"
    // InternalSimplePDLDSL.g:975:1: rule__RessourceSequence__Group__1__Impl : ( ( rule__RessourceSequence__WorkdefinitionAssignment_1 ) ) ;
    public final void rule__RessourceSequence__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:979:1: ( ( ( rule__RessourceSequence__WorkdefinitionAssignment_1 ) ) )
            // InternalSimplePDLDSL.g:980:1: ( ( rule__RessourceSequence__WorkdefinitionAssignment_1 ) )
            {
            // InternalSimplePDLDSL.g:980:1: ( ( rule__RessourceSequence__WorkdefinitionAssignment_1 ) )
            // InternalSimplePDLDSL.g:981:2: ( rule__RessourceSequence__WorkdefinitionAssignment_1 )
            {
             before(grammarAccess.getRessourceSequenceAccess().getWorkdefinitionAssignment_1()); 
            // InternalSimplePDLDSL.g:982:2: ( rule__RessourceSequence__WorkdefinitionAssignment_1 )
            // InternalSimplePDLDSL.g:982:3: rule__RessourceSequence__WorkdefinitionAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__RessourceSequence__WorkdefinitionAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRessourceSequenceAccess().getWorkdefinitionAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__1__Impl"


    // $ANTLR start "rule__RessourceSequence__Group__2"
    // InternalSimplePDLDSL.g:990:1: rule__RessourceSequence__Group__2 : rule__RessourceSequence__Group__2__Impl rule__RessourceSequence__Group__3 ;
    public final void rule__RessourceSequence__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:994:1: ( rule__RessourceSequence__Group__2__Impl rule__RessourceSequence__Group__3 )
            // InternalSimplePDLDSL.g:995:2: rule__RessourceSequence__Group__2__Impl rule__RessourceSequence__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__RessourceSequence__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RessourceSequence__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__2"


    // $ANTLR start "rule__RessourceSequence__Group__2__Impl"
    // InternalSimplePDLDSL.g:1002:1: rule__RessourceSequence__Group__2__Impl : ( 'needs' ) ;
    public final void rule__RessourceSequence__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1006:1: ( ( 'needs' ) )
            // InternalSimplePDLDSL.g:1007:1: ( 'needs' )
            {
            // InternalSimplePDLDSL.g:1007:1: ( 'needs' )
            // InternalSimplePDLDSL.g:1008:2: 'needs'
            {
             before(grammarAccess.getRessourceSequenceAccess().getNeedsKeyword_2()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getRessourceSequenceAccess().getNeedsKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__2__Impl"


    // $ANTLR start "rule__RessourceSequence__Group__3"
    // InternalSimplePDLDSL.g:1017:1: rule__RessourceSequence__Group__3 : rule__RessourceSequence__Group__3__Impl rule__RessourceSequence__Group__4 ;
    public final void rule__RessourceSequence__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1021:1: ( rule__RessourceSequence__Group__3__Impl rule__RessourceSequence__Group__4 )
            // InternalSimplePDLDSL.g:1022:2: rule__RessourceSequence__Group__3__Impl rule__RessourceSequence__Group__4
            {
            pushFollow(FOLLOW_3);
            rule__RessourceSequence__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RessourceSequence__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__3"


    // $ANTLR start "rule__RessourceSequence__Group__3__Impl"
    // InternalSimplePDLDSL.g:1029:1: rule__RessourceSequence__Group__3__Impl : ( ( rule__RessourceSequence__QuantityAssignment_3 ) ) ;
    public final void rule__RessourceSequence__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1033:1: ( ( ( rule__RessourceSequence__QuantityAssignment_3 ) ) )
            // InternalSimplePDLDSL.g:1034:1: ( ( rule__RessourceSequence__QuantityAssignment_3 ) )
            {
            // InternalSimplePDLDSL.g:1034:1: ( ( rule__RessourceSequence__QuantityAssignment_3 ) )
            // InternalSimplePDLDSL.g:1035:2: ( rule__RessourceSequence__QuantityAssignment_3 )
            {
             before(grammarAccess.getRessourceSequenceAccess().getQuantityAssignment_3()); 
            // InternalSimplePDLDSL.g:1036:2: ( rule__RessourceSequence__QuantityAssignment_3 )
            // InternalSimplePDLDSL.g:1036:3: rule__RessourceSequence__QuantityAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__RessourceSequence__QuantityAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getRessourceSequenceAccess().getQuantityAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__3__Impl"


    // $ANTLR start "rule__RessourceSequence__Group__4"
    // InternalSimplePDLDSL.g:1044:1: rule__RessourceSequence__Group__4 : rule__RessourceSequence__Group__4__Impl ;
    public final void rule__RessourceSequence__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1048:1: ( rule__RessourceSequence__Group__4__Impl )
            // InternalSimplePDLDSL.g:1049:2: rule__RessourceSequence__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RessourceSequence__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__4"


    // $ANTLR start "rule__RessourceSequence__Group__4__Impl"
    // InternalSimplePDLDSL.g:1055:1: rule__RessourceSequence__Group__4__Impl : ( ( rule__RessourceSequence__RessourceAssignment_4 ) ) ;
    public final void rule__RessourceSequence__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1059:1: ( ( ( rule__RessourceSequence__RessourceAssignment_4 ) ) )
            // InternalSimplePDLDSL.g:1060:1: ( ( rule__RessourceSequence__RessourceAssignment_4 ) )
            {
            // InternalSimplePDLDSL.g:1060:1: ( ( rule__RessourceSequence__RessourceAssignment_4 ) )
            // InternalSimplePDLDSL.g:1061:2: ( rule__RessourceSequence__RessourceAssignment_4 )
            {
             before(grammarAccess.getRessourceSequenceAccess().getRessourceAssignment_4()); 
            // InternalSimplePDLDSL.g:1062:2: ( rule__RessourceSequence__RessourceAssignment_4 )
            // InternalSimplePDLDSL.g:1062:3: rule__RessourceSequence__RessourceAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__RessourceSequence__RessourceAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getRessourceSequenceAccess().getRessourceAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__Group__4__Impl"


    // $ANTLR start "rule__Process__NameAssignment_1"
    // InternalSimplePDLDSL.g:1071:1: rule__Process__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Process__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1075:1: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1076:2: ( RULE_ID )
            {
            // InternalSimplePDLDSL.g:1076:2: ( RULE_ID )
            // InternalSimplePDLDSL.g:1077:3: RULE_ID
            {
             before(grammarAccess.getProcessAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getProcessAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__NameAssignment_1"


    // $ANTLR start "rule__Process__ProcessElementsAssignment_3"
    // InternalSimplePDLDSL.g:1086:1: rule__Process__ProcessElementsAssignment_3 : ( ruleProcessElement ) ;
    public final void rule__Process__ProcessElementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1090:1: ( ( ruleProcessElement ) )
            // InternalSimplePDLDSL.g:1091:2: ( ruleProcessElement )
            {
            // InternalSimplePDLDSL.g:1091:2: ( ruleProcessElement )
            // InternalSimplePDLDSL.g:1092:3: ruleProcessElement
            {
             before(grammarAccess.getProcessAccess().getProcessElementsProcessElementParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleProcessElement();

            state._fsp--;

             after(grammarAccess.getProcessAccess().getProcessElementsProcessElementParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Process__ProcessElementsAssignment_3"


    // $ANTLR start "rule__WorkDefinition__NameAssignment_1"
    // InternalSimplePDLDSL.g:1101:1: rule__WorkDefinition__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__WorkDefinition__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1105:1: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1106:2: ( RULE_ID )
            {
            // InternalSimplePDLDSL.g:1106:2: ( RULE_ID )
            // InternalSimplePDLDSL.g:1107:3: RULE_ID
            {
             before(grammarAccess.getWorkDefinitionAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getWorkDefinitionAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkDefinition__NameAssignment_1"


    // $ANTLR start "rule__WorkSequence__LinkTypeAssignment_1"
    // InternalSimplePDLDSL.g:1116:1: rule__WorkSequence__LinkTypeAssignment_1 : ( ruleWorkSequenceType ) ;
    public final void rule__WorkSequence__LinkTypeAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1120:1: ( ( ruleWorkSequenceType ) )
            // InternalSimplePDLDSL.g:1121:2: ( ruleWorkSequenceType )
            {
            // InternalSimplePDLDSL.g:1121:2: ( ruleWorkSequenceType )
            // InternalSimplePDLDSL.g:1122:3: ruleWorkSequenceType
            {
             before(grammarAccess.getWorkSequenceAccess().getLinkTypeWorkSequenceTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleWorkSequenceType();

            state._fsp--;

             after(grammarAccess.getWorkSequenceAccess().getLinkTypeWorkSequenceTypeEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__LinkTypeAssignment_1"


    // $ANTLR start "rule__WorkSequence__PredecessorAssignment_3"
    // InternalSimplePDLDSL.g:1131:1: rule__WorkSequence__PredecessorAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__WorkSequence__PredecessorAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1135:1: ( ( ( RULE_ID ) ) )
            // InternalSimplePDLDSL.g:1136:2: ( ( RULE_ID ) )
            {
            // InternalSimplePDLDSL.g:1136:2: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1137:3: ( RULE_ID )
            {
             before(grammarAccess.getWorkSequenceAccess().getPredecessorWorkDefinitionCrossReference_3_0()); 
            // InternalSimplePDLDSL.g:1138:3: ( RULE_ID )
            // InternalSimplePDLDSL.g:1139:4: RULE_ID
            {
             before(grammarAccess.getWorkSequenceAccess().getPredecessorWorkDefinitionIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getWorkSequenceAccess().getPredecessorWorkDefinitionIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getWorkSequenceAccess().getPredecessorWorkDefinitionCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__PredecessorAssignment_3"


    // $ANTLR start "rule__WorkSequence__SuccessorAssignment_5"
    // InternalSimplePDLDSL.g:1150:1: rule__WorkSequence__SuccessorAssignment_5 : ( ( RULE_ID ) ) ;
    public final void rule__WorkSequence__SuccessorAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1154:1: ( ( ( RULE_ID ) ) )
            // InternalSimplePDLDSL.g:1155:2: ( ( RULE_ID ) )
            {
            // InternalSimplePDLDSL.g:1155:2: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1156:3: ( RULE_ID )
            {
             before(grammarAccess.getWorkSequenceAccess().getSuccessorWorkDefinitionCrossReference_5_0()); 
            // InternalSimplePDLDSL.g:1157:3: ( RULE_ID )
            // InternalSimplePDLDSL.g:1158:4: RULE_ID
            {
             before(grammarAccess.getWorkSequenceAccess().getSuccessorWorkDefinitionIDTerminalRuleCall_5_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getWorkSequenceAccess().getSuccessorWorkDefinitionIDTerminalRuleCall_5_0_1()); 

            }

             after(grammarAccess.getWorkSequenceAccess().getSuccessorWorkDefinitionCrossReference_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__WorkSequence__SuccessorAssignment_5"


    // $ANTLR start "rule__Guidance__TextAssignment_1"
    // InternalSimplePDLDSL.g:1169:1: rule__Guidance__TextAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Guidance__TextAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1173:1: ( ( RULE_STRING ) )
            // InternalSimplePDLDSL.g:1174:2: ( RULE_STRING )
            {
            // InternalSimplePDLDSL.g:1174:2: ( RULE_STRING )
            // InternalSimplePDLDSL.g:1175:3: RULE_STRING
            {
             before(grammarAccess.getGuidanceAccess().getTextSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getGuidanceAccess().getTextSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__TextAssignment_1"


    // $ANTLR start "rule__Guidance__ElementAssignment_3"
    // InternalSimplePDLDSL.g:1184:1: rule__Guidance__ElementAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__Guidance__ElementAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1188:1: ( ( ( RULE_ID ) ) )
            // InternalSimplePDLDSL.g:1189:2: ( ( RULE_ID ) )
            {
            // InternalSimplePDLDSL.g:1189:2: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1190:3: ( RULE_ID )
            {
             before(grammarAccess.getGuidanceAccess().getElementWorkDefinitionCrossReference_3_0()); 
            // InternalSimplePDLDSL.g:1191:3: ( RULE_ID )
            // InternalSimplePDLDSL.g:1192:4: RULE_ID
            {
             before(grammarAccess.getGuidanceAccess().getElementWorkDefinitionIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getGuidanceAccess().getElementWorkDefinitionIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getGuidanceAccess().getElementWorkDefinitionCrossReference_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__ElementAssignment_3"


    // $ANTLR start "rule__Guidance__ElementAssignment_4_1"
    // InternalSimplePDLDSL.g:1203:1: rule__Guidance__ElementAssignment_4_1 : ( ( RULE_ID ) ) ;
    public final void rule__Guidance__ElementAssignment_4_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1207:1: ( ( ( RULE_ID ) ) )
            // InternalSimplePDLDSL.g:1208:2: ( ( RULE_ID ) )
            {
            // InternalSimplePDLDSL.g:1208:2: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1209:3: ( RULE_ID )
            {
             before(grammarAccess.getGuidanceAccess().getElementWorkDefinitionCrossReference_4_1_0()); 
            // InternalSimplePDLDSL.g:1210:3: ( RULE_ID )
            // InternalSimplePDLDSL.g:1211:4: RULE_ID
            {
             before(grammarAccess.getGuidanceAccess().getElementWorkDefinitionIDTerminalRuleCall_4_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getGuidanceAccess().getElementWorkDefinitionIDTerminalRuleCall_4_1_0_1()); 

            }

             after(grammarAccess.getGuidanceAccess().getElementWorkDefinitionCrossReference_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Guidance__ElementAssignment_4_1"


    // $ANTLR start "rule__Ressource__NameAssignment_1"
    // InternalSimplePDLDSL.g:1222:1: rule__Ressource__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Ressource__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1226:1: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1227:2: ( RULE_ID )
            {
            // InternalSimplePDLDSL.g:1227:2: ( RULE_ID )
            // InternalSimplePDLDSL.g:1228:3: RULE_ID
            {
             before(grammarAccess.getRessourceAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getRessourceAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__NameAssignment_1"


    // $ANTLR start "rule__Ressource__QuantityAssignment_2"
    // InternalSimplePDLDSL.g:1237:1: rule__Ressource__QuantityAssignment_2 : ( RULE_INT ) ;
    public final void rule__Ressource__QuantityAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1241:1: ( ( RULE_INT ) )
            // InternalSimplePDLDSL.g:1242:2: ( RULE_INT )
            {
            // InternalSimplePDLDSL.g:1242:2: ( RULE_INT )
            // InternalSimplePDLDSL.g:1243:3: RULE_INT
            {
             before(grammarAccess.getRessourceAccess().getQuantityINTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getRessourceAccess().getQuantityINTTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Ressource__QuantityAssignment_2"


    // $ANTLR start "rule__RessourceSequence__WorkdefinitionAssignment_1"
    // InternalSimplePDLDSL.g:1252:1: rule__RessourceSequence__WorkdefinitionAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__RessourceSequence__WorkdefinitionAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1256:1: ( ( ( RULE_ID ) ) )
            // InternalSimplePDLDSL.g:1257:2: ( ( RULE_ID ) )
            {
            // InternalSimplePDLDSL.g:1257:2: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1258:3: ( RULE_ID )
            {
             before(grammarAccess.getRessourceSequenceAccess().getWorkdefinitionWorkDefinitionCrossReference_1_0()); 
            // InternalSimplePDLDSL.g:1259:3: ( RULE_ID )
            // InternalSimplePDLDSL.g:1260:4: RULE_ID
            {
             before(grammarAccess.getRessourceSequenceAccess().getWorkdefinitionWorkDefinitionIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getRessourceSequenceAccess().getWorkdefinitionWorkDefinitionIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getRessourceSequenceAccess().getWorkdefinitionWorkDefinitionCrossReference_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__WorkdefinitionAssignment_1"


    // $ANTLR start "rule__RessourceSequence__QuantityAssignment_3"
    // InternalSimplePDLDSL.g:1271:1: rule__RessourceSequence__QuantityAssignment_3 : ( RULE_INT ) ;
    public final void rule__RessourceSequence__QuantityAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1275:1: ( ( RULE_INT ) )
            // InternalSimplePDLDSL.g:1276:2: ( RULE_INT )
            {
            // InternalSimplePDLDSL.g:1276:2: ( RULE_INT )
            // InternalSimplePDLDSL.g:1277:3: RULE_INT
            {
             before(grammarAccess.getRessourceSequenceAccess().getQuantityINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getRessourceSequenceAccess().getQuantityINTTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__QuantityAssignment_3"


    // $ANTLR start "rule__RessourceSequence__RessourceAssignment_4"
    // InternalSimplePDLDSL.g:1286:1: rule__RessourceSequence__RessourceAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__RessourceSequence__RessourceAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSimplePDLDSL.g:1290:1: ( ( ( RULE_ID ) ) )
            // InternalSimplePDLDSL.g:1291:2: ( ( RULE_ID ) )
            {
            // InternalSimplePDLDSL.g:1291:2: ( ( RULE_ID ) )
            // InternalSimplePDLDSL.g:1292:3: ( RULE_ID )
            {
             before(grammarAccess.getRessourceSequenceAccess().getRessourceRessourceCrossReference_4_0()); 
            // InternalSimplePDLDSL.g:1293:3: ( RULE_ID )
            // InternalSimplePDLDSL.g:1294:4: RULE_ID
            {
             before(grammarAccess.getRessourceSequenceAccess().getRessourceRessourceIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getRessourceSequenceAccess().getRessourceRessourceIDTerminalRuleCall_4_0_1()); 

            }

             after(grammarAccess.getRessourceSequenceAccess().getRessourceRessourceCrossReference_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RessourceSequence__RessourceAssignment_4"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x00000000034E0000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000000034C0002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000007800L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000004000000L});

}