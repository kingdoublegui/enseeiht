package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ArrayType;
import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for accessing an array element.
 * @author Marc Pantel
 *
 */
public class ArrayAccessImpl implements Expression {

	protected Expression array;
	protected Expression index;

	public ArrayAccessImpl(Expression _array, Expression _index) {
		this.array = _array;
		this.index = _index;
	}

	@Override
	public String toString() {
		return (this.array + "[ " + this.index + " ]");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		if (this.array.getType() instanceof ArrayType) {
			return ((ArrayTypeImpl)this.array.getType()).getPointedType();
		}
		return AtomicType.ErrorType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		fragment.append(this.array.getCode(_factory));
		fragment.append(this.index.getCode(_factory));
		fragment.add(_factory.createLoadL(this.getType().length()));
		fragment.add(Library.IMul);
		fragment.add(Library.IAdd);
		fragment.add(_factory.createLoadI(this.getType().length()));

		return fragment;
	}

}
