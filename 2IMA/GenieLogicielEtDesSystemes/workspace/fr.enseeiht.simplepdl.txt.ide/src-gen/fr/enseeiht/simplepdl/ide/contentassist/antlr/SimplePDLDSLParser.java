/*
 * generated by Xtext 2.10.0
 */
package fr.enseeiht.simplepdl.ide.contentassist.antlr;

import com.google.inject.Inject;
import fr.enseeiht.simplepdl.ide.contentassist.antlr.internal.InternalSimplePDLDSLParser;
import fr.enseeiht.simplepdl.services.SimplePDLDSLGrammarAccess;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

public class SimplePDLDSLParser extends AbstractContentAssistParser {

	@Inject
	private SimplePDLDSLGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalSimplePDLDSLParser createParser() {
		InternalSimplePDLDSLParser result = new InternalSimplePDLDSLParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getProcessElementAccess().getAlternatives(), "rule__ProcessElement__Alternatives");
					put(grammarAccess.getWorkSequenceTypeAccess().getAlternatives(), "rule__WorkSequenceType__Alternatives");
					put(grammarAccess.getProcessAccess().getGroup(), "rule__Process__Group__0");
					put(grammarAccess.getWorkDefinitionAccess().getGroup(), "rule__WorkDefinition__Group__0");
					put(grammarAccess.getWorkSequenceAccess().getGroup(), "rule__WorkSequence__Group__0");
					put(grammarAccess.getGuidanceAccess().getGroup(), "rule__Guidance__Group__0");
					put(grammarAccess.getGuidanceAccess().getGroup_4(), "rule__Guidance__Group_4__0");
					put(grammarAccess.getRessourceAccess().getGroup(), "rule__Ressource__Group__0");
					put(grammarAccess.getRessourceSequenceAccess().getGroup(), "rule__RessourceSequence__Group__0");
					put(grammarAccess.getProcessAccess().getNameAssignment_1(), "rule__Process__NameAssignment_1");
					put(grammarAccess.getProcessAccess().getProcessElementsAssignment_3(), "rule__Process__ProcessElementsAssignment_3");
					put(grammarAccess.getWorkDefinitionAccess().getNameAssignment_1(), "rule__WorkDefinition__NameAssignment_1");
					put(grammarAccess.getWorkSequenceAccess().getLinkTypeAssignment_1(), "rule__WorkSequence__LinkTypeAssignment_1");
					put(grammarAccess.getWorkSequenceAccess().getPredecessorAssignment_3(), "rule__WorkSequence__PredecessorAssignment_3");
					put(grammarAccess.getWorkSequenceAccess().getSuccessorAssignment_5(), "rule__WorkSequence__SuccessorAssignment_5");
					put(grammarAccess.getGuidanceAccess().getTextAssignment_1(), "rule__Guidance__TextAssignment_1");
					put(grammarAccess.getGuidanceAccess().getElementAssignment_3(), "rule__Guidance__ElementAssignment_3");
					put(grammarAccess.getGuidanceAccess().getElementAssignment_4_1(), "rule__Guidance__ElementAssignment_4_1");
					put(grammarAccess.getRessourceAccess().getNameAssignment_1(), "rule__Ressource__NameAssignment_1");
					put(grammarAccess.getRessourceAccess().getQuantityAssignment_2(), "rule__Ressource__QuantityAssignment_2");
					put(grammarAccess.getRessourceSequenceAccess().getWorkdefinitionAssignment_1(), "rule__RessourceSequence__WorkdefinitionAssignment_1");
					put(grammarAccess.getRessourceSequenceAccess().getQuantityAssignment_3(), "rule__RessourceSequence__QuantityAssignment_3");
					put(grammarAccess.getRessourceSequenceAccess().getRessourceAssignment_4(), "rule__RessourceSequence__RessourceAssignment_4");
				}
			};
		}
		return nameMappings.get(element);
	}

	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			InternalSimplePDLDSLParser typedParser = (InternalSimplePDLDSLParser) parser;
			typedParser.entryRuleProcess();
			return typedParser.getFollowElements();
		} catch(RecognitionException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public SimplePDLDSLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(SimplePDLDSLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}