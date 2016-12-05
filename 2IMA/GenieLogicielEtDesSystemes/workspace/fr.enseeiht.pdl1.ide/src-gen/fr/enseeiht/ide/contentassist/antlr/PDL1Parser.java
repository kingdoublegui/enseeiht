/*
 * generated by Xtext 2.10.0
 */
package fr.enseeiht.ide.contentassist.antlr;

import com.google.inject.Inject;
import fr.enseeiht.ide.contentassist.antlr.internal.InternalPDL1Parser;
import fr.enseeiht.services.PDL1GrammarAccess;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.antlr.runtime.RecognitionException;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;

public class PDL1Parser extends AbstractContentAssistParser {

	@Inject
	private PDL1GrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalPDL1Parser createParser() {
		InternalPDL1Parser result = new InternalPDL1Parser(null);
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
					put(grammarAccess.getProcessAccess().getNameAssignment_1(), "rule__Process__NameAssignment_1");
					put(grammarAccess.getProcessAccess().getProcessElementsAssignment_3(), "rule__Process__ProcessElementsAssignment_3");
					put(grammarAccess.getWorkDefinitionAccess().getNameAssignment_1(), "rule__WorkDefinition__NameAssignment_1");
					put(grammarAccess.getWorkSequenceAccess().getLinkTypeAssignment_1(), "rule__WorkSequence__LinkTypeAssignment_1");
					put(grammarAccess.getWorkSequenceAccess().getPredecessorAssignment_3(), "rule__WorkSequence__PredecessorAssignment_3");
					put(grammarAccess.getWorkSequenceAccess().getSuccessorAssignment_5(), "rule__WorkSequence__SuccessorAssignment_5");
					put(grammarAccess.getGuidanceAccess().getTextAssignment(), "rule__Guidance__TextAssignment");
				}
			};
		}
		return nameMappings.get(element);
	}

	@Override
	protected Collection<FollowElement> getFollowElements(AbstractInternalContentAssistParser parser) {
		try {
			InternalPDL1Parser typedParser = (InternalPDL1Parser) parser;
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

	public PDL1GrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(PDL1GrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}