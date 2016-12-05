/**
 */
package simplepdl.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import simplepdl.Guidance;
import simplepdl.ProcessElement;
import simplepdl.Ressource;
import simplepdl.RessourceSequence;
import simplepdl.SimplepdlPackage;
import simplepdl.WorkDefinition;
import simplepdl.WorkSequence;
import simplepdl.WorkSequenceType;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see simplepdl.SimplepdlPackage
 * @generated
 */
public class SimplepdlValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final SimplepdlValidator INSTANCE = new SimplepdlValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "simplepdl";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimplepdlValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return SimplepdlPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case SimplepdlPackage.PROCESS:
				return validateProcess((simplepdl.Process)value, diagnostics, context);
			case SimplepdlPackage.PROCESS_ELEMENT:
				return validateProcessElement((ProcessElement)value, diagnostics, context);
			case SimplepdlPackage.WORK_DEFINITION:
				return validateWorkDefinition((WorkDefinition)value, diagnostics, context);
			case SimplepdlPackage.WORK_SEQUENCE:
				return validateWorkSequence((WorkSequence)value, diagnostics, context);
			case SimplepdlPackage.GUIDANCE:
				return validateGuidance((Guidance)value, diagnostics, context);
			case SimplepdlPackage.RESSOURCE:
				return validateRessource((Ressource)value, diagnostics, context);
			case SimplepdlPackage.RESSOURCE_SEQUENCE:
				return validateRessourceSequence((RessourceSequence)value, diagnostics, context);
			case SimplepdlPackage.WORK_SEQUENCE_TYPE:
				return validateWorkSequenceType((WorkSequenceType)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess(simplepdl.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(process, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(process, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(process, diagnostics, context);
		if (result || diagnostics != null) result &= validateProcess_nameForbidden(process, diagnostics, context);
		if (result || diagnostics != null) result &= validateProcess_differentNames(process, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the nameForbidden constraint of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String PROCESS__NAME_FORBIDDEN__EEXPRESSION = "name <> 'Process'";

	/**
	 * Validates the nameForbidden constraint of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess_nameForbidden(simplepdl.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.PROCESS,
				 process,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "nameForbidden",
				 PROCESS__NAME_FORBIDDEN__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the differentNames constraint of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String PROCESS__DIFFERENT_NAMES__EEXPRESSION = "self.processElements\n" +
		"\t\t\t\t\t\t\t\t\t\t-> select(pe|pe.oclIsTypeOf(WorkDefinition))\n" +
		"\t\t\t\t\t\t\t\t\t\t-> collect(pe|pe.oclAsType(WorkDefinition))\n" +
		"\t\t\t\t\t\t\t\t\t\t-> forAll(a,b|a <> b implies a.name <> b.name)";

	/**
	 * Validates the differentNames constraint of '<em>Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcess_differentNames(simplepdl.Process process, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.PROCESS,
				 process,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "differentNames",
				 PROCESS__DIFFERENT_NAMES__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateProcessElement(ProcessElement processElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(processElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkDefinition(WorkDefinition workDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(workDefinition, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(workDefinition, diagnostics, context);
		if (result || diagnostics != null) result &= validateWorkDefinition_nameNotEmpty(workDefinition, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the nameNotEmpty constraint of '<em>Work Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String WORK_DEFINITION__NAME_NOT_EMPTY__EEXPRESSION = "self.name.size() > 0";

	/**
	 * Validates the nameNotEmpty constraint of '<em>Work Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkDefinition_nameNotEmpty(WorkDefinition workDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.WORK_DEFINITION,
				 workDefinition,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "nameNotEmpty",
				 WORK_DEFINITION__NAME_NOT_EMPTY__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkSequence(WorkSequence workSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(workSequence, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validateWorkSequence_previousWDinSameProcess(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validateWorkSequence_nextWDinSameProcess(workSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validateWorkSequence_notReflexive(workSequence, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the previousWDinSameProcess constraint of '<em>Work Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String WORK_SEQUENCE__PREVIOUS_WDIN_SAME_PROCESS__EEXPRESSION = "self.process = self.predecessor.process";

	/**
	 * Validates the previousWDinSameProcess constraint of '<em>Work Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkSequence_previousWDinSameProcess(WorkSequence workSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.WORK_SEQUENCE,
				 workSequence,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "previousWDinSameProcess",
				 WORK_SEQUENCE__PREVIOUS_WDIN_SAME_PROCESS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the nextWDinSameProcess constraint of '<em>Work Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String WORK_SEQUENCE__NEXT_WDIN_SAME_PROCESS__EEXPRESSION = "self.process = self.successor.process";

	/**
	 * Validates the nextWDinSameProcess constraint of '<em>Work Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkSequence_nextWDinSameProcess(WorkSequence workSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.WORK_SEQUENCE,
				 workSequence,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "nextWDinSameProcess",
				 WORK_SEQUENCE__NEXT_WDIN_SAME_PROCESS__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the notReflexive constraint of '<em>Work Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String WORK_SEQUENCE__NOT_REFLEXIVE__EEXPRESSION = "self.predecessor <> self.successor";

	/**
	 * Validates the notReflexive constraint of '<em>Work Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkSequence_notReflexive(WorkSequence workSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.WORK_SEQUENCE,
				 workSequence,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "notReflexive",
				 WORK_SEQUENCE__NOT_REFLEXIVE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGuidance(Guidance guidance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(guidance, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRessource(Ressource ressource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(ressource, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validateRessource_nameNotEmpty(ressource, diagnostics, context);
		if (result || diagnostics != null) result &= validateRessource_quantityPositive(ressource, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the nameNotEmpty constraint of '<em>Ressource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String RESSOURCE__NAME_NOT_EMPTY__EEXPRESSION = "self.name.size() > 0";

	/**
	 * Validates the nameNotEmpty constraint of '<em>Ressource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRessource_nameNotEmpty(Ressource ressource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.RESSOURCE,
				 ressource,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "nameNotEmpty",
				 RESSOURCE__NAME_NOT_EMPTY__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the quantityPositive constraint of '<em>Ressource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String RESSOURCE__QUANTITY_POSITIVE__EEXPRESSION = "self.quantity > 0";

	/**
	 * Validates the quantityPositive constraint of '<em>Ressource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRessource_quantityPositive(Ressource ressource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.RESSOURCE,
				 ressource,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "quantityPositive",
				 RESSOURCE__QUANTITY_POSITIVE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRessourceSequence(RessourceSequence ressourceSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(ressourceSequence, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validateRessourceSequence_quantityPositive(ressourceSequence, diagnostics, context);
		if (result || diagnostics != null) result &= validateRessourceSequence_quantityAvailable(ressourceSequence, diagnostics, context);
		return result;
	}

	/**
	 * The cached validation expression for the quantityPositive constraint of '<em>Ressource Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String RESSOURCE_SEQUENCE__QUANTITY_POSITIVE__EEXPRESSION = "self.quantity > 0";

	/**
	 * Validates the quantityPositive constraint of '<em>Ressource Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRessourceSequence_quantityPositive(RessourceSequence ressourceSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.RESSOURCE_SEQUENCE,
				 ressourceSequence,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "quantityPositive",
				 RESSOURCE_SEQUENCE__QUANTITY_POSITIVE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * The cached validation expression for the quantityAvailable constraint of '<em>Ressource Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final String RESSOURCE_SEQUENCE__QUANTITY_AVAILABLE__EEXPRESSION = "self.quantity <= self.ressource.quantity";

	/**
	 * Validates the quantityAvailable constraint of '<em>Ressource Sequence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRessourceSequence_quantityAvailable(RessourceSequence ressourceSequence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return
			validate
				(SimplepdlPackage.Literals.RESSOURCE_SEQUENCE,
				 ressourceSequence,
				 diagnostics,
				 context,
				 "http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot",
				 "quantityAvailable",
				 RESSOURCE_SEQUENCE__QUANTITY_AVAILABLE__EEXPRESSION,
				 Diagnostic.ERROR,
				 DIAGNOSTIC_SOURCE,
				 0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWorkSequenceType(WorkSequenceType workSequenceType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //SimplepdlValidator
