/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Type;
import fr.n7.stl.ast.VariableDeclaration;

/**
 * @author Marc Pantel
 *
 */
public class VariableUseImpl implements Expression {

	@Override
	public String toString() {
		return "VariableUseImpl [declaration=" + declaration + ", name=" + name + "]";
	}

	private VariableDeclaration declaration;
	private String name;
	/**
	 * 
	 */
	public VariableUseImpl(VariableDeclaration _declaration) {
		this.declaration = _declaration;
	}

	public VariableUseImpl(String _name) {
		this.name = _name;
	}

	@Override
	public Type getType() {
		return declaration.getType();
	}

}
