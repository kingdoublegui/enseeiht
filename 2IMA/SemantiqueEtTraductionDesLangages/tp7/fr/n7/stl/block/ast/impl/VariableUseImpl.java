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
 * Implementation of the Abstract Syntax Tree node for a variable use expression.
 * @author Marc Pantel
 *
 */
public class VariableUseImpl implements Expression {

	private VariableDeclaration declaration;
	// <REMOVE>
//	private String name;
	// </REMOVE>
	
	/**
	 * Creates a variable use expression Abstract Syntax Tree node.
	 * @param _declaration Declaration of the used variable.
	 */
	public VariableUseImpl(VariableDeclaration _declaration) {
		this.declaration = _declaration;
		// <REMOVE>
//		this.name = _declaration.getName();
		// </REMOVE>
	}
	
	// <REMOVE>
//	public VariableUseImpl(String _name) {
//		this.name = _name;
//	}
	// </REMOVE>

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return /* <REMOVE> (this.declaration == null)?(this.name): </REMOVE>*/ 
				("@{" + this.declaration.getName() + "}");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		return declaration.getType();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _code = _factory.createFragment();
		_code.add(_factory.createLoad(this.declaration.getRegister(),
				this.declaration.getOffset(),
				this.declaration.getType().length()));
		return _code;
	}

}
