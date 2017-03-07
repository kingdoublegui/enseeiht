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
public class ArrayAccessImpl implements Expression {

	private Expression array;
	private Expression index;

	public ArrayAccessImpl(Expression _array, Expression _index) {
		this.array = _array;
		this.index = _index;
	}

	@Override
	public String toString() {
		return "ArrayAccessImpl [array=" + array + ", index=" + index + "]";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
