/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Type;

/**
 * @author pantel2
 *
 */
public class CoupleImpl implements Expression {
	
	@Override
	public String toString() {
		return "CoupleImpl [first=" + first + ", second=" + second + "]";
	}

	private Expression first;
	
	private Expression second;

	/**
	 * 
	 */
	public CoupleImpl(Expression _first, Expression _second) {
		this.first = _first;
		this.second = _second;
	}

	@Override
	public Type getType() {
		return new CoupleTypeImpl( this.first.getType(), this.second.getType());
	}

}
