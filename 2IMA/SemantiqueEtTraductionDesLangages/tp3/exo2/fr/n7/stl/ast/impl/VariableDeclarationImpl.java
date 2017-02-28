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
public class VariableDeclarationImpl implements VariableDeclaration {

	private String name;
	private Type type;
	private Expression initialValue;
	/**
	 * 
	 */
	public VariableDeclarationImpl(String _name, Type _type, Expression _initialValue) {
		this.name = _name;
		this.type = _type;
		this.initialValue = _initialValue;
	}

	@Override
	public String toString() {
		return "VariableDeclarationImpl [name=" + name + ", type=" + type + ", initialValue=" + initialValue + "]";
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.VariableDeclaration#getType()
	 */
	@Override
	public Type getType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.VariableDeclaration#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

}
