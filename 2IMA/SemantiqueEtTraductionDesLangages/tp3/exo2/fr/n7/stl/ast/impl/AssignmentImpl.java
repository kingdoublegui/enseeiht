/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Instruction;
import fr.n7.stl.ast.VariableDeclaration;

/**
 * @author pantel2
 *
 */
public class AssignmentImpl implements Instruction {

	@Override
	public String toString() {
		return "AssignmentImpl [declaration=" + declaration + ", value=" + value + ", name=" + name + "]";
	}

	private VariableDeclaration declaration;
	private Expression value;
	private String name;

	/**
	 * 
	 */
	public AssignmentImpl(VariableDeclaration _declaration, Expression _value) {
		this.declaration = _declaration;
		this.value = _value;
	}

	public AssignmentImpl(String _name, Expression _value) {
		this.name = _name;
		this.value = _value;
	}

}
