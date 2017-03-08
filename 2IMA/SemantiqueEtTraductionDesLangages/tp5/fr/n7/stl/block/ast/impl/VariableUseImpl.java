/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.VariableDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class VariableUseImpl implements Expression {

	private VariableDeclaration declaration;
	private String name;
	
	/**
	 * 
	 */
	public VariableUseImpl(VariableDeclaration _declaration) {
		this.declaration = _declaration;
		this.name = _declaration.getName();
	}
	
	public VariableUseImpl(String _name) {
		this.name = _name;
	}

	@Override
	public String toString() {
		return (this.declaration == null)?(this.name):("@{" + this.declaration.getName() + "}");
	}

	@Override
	public Type getType() {
		return declaration.getType();
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
