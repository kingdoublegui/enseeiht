package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for building a couple value.
 * @author Marc Pantel
 *
 */
public class CoupleImpl implements Expression {

	private Expression first;
	
	private Expression second;

	/**
	 * Construction for a couple expression implementation.
	 * @param _first First part of the couple.
	 * @param _second Second part of the couple.
	 */
	public CoupleImpl(Expression _first, Expression _second) {
		this.first = _first;
		this.second = _second;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "< " + this.first + ", " + this.second + ">";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		return new CoupleTypeImpl( this.first.getType(), this.second.getType());
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		throw new SemanticsUndefinedException( "getCode is undefined in CoupleImpl.");
	}

}
