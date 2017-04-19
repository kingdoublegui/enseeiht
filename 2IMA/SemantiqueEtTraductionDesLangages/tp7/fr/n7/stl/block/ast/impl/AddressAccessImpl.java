package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AddressAccess;
import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a pointer access expression.
 * @author Marc Pantel
 *
 */
public class AddressAccessImpl implements AddressAccess {

	private Expression variable;

	public AddressAccessImpl(Expression _variable) {
		this.variable = _variable;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "&(" + this.variable + ")";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		Type _type = this.variable.getType();
		return new PointerTypeImpl(_type);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		return variable.getCode(_factory);
	}

}
