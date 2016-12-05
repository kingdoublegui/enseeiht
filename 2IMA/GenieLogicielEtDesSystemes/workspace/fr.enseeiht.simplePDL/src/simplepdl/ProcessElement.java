/**
 */
package simplepdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Process Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link simplepdl.ProcessElement#getProcess <em>Process</em>}</li>
 * </ul>
 *
 * @see simplepdl.SimplepdlPackage#getProcessElement()
 * @model abstract="true"
 * @generated
 */
public interface ProcessElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Process</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' reference.
	 * @see simplepdl.SimplepdlPackage#getProcessElement_Process()
	 * @model resolveProxies="false" required="true" transient="true" changeable="false" volatile="true" derived="true"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot derivation='Process.allInstances()\n\t\t\t\t\t->select(p | p.processElements->includes(self))\n\t\t\t\t\t->asSequence()->first()'"
	 * @generated
	 */
	simplepdl.Process getProcess();

} // ProcessElement
