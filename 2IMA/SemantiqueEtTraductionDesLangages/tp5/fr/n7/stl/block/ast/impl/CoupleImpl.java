/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author pantel2
 *
 */
public class CoupleImpl implements Expression {

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
	public String toString() {
		return "< " + this.first + ", " + this.second + ">";
	}

	@Override
	public Type getType() {
		return new CoupleTypeImpl( this.first.getType(), this.second.getType());
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
