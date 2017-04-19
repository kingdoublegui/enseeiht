package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ArrayType;
import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;

/**
 * @author Marc Pantel
 *
 */
public class ArrayTypeImpl implements Type, ArrayType {

	private Type element;
	private Expression size;

	public ArrayTypeImpl(Type _element) {
		this.element = _element;
	}

	public Type getType() {
		return this;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof ArrayTypeImpl) {
			return this.element.equalsTo(((ArrayTypeImpl)_other).element);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		if (_other instanceof ArrayTypeImpl) {
			return this.element.compatibleWith(((ArrayTypeImpl)_other).element);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		if (_other instanceof ArrayTypeImpl) {
			return new ArrayTypeImpl(this.element.merge(((ArrayTypeImpl)_other).element));
		}
		return AtomicType.ErrorType;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#length(int)
	 */
	@Override
	public int length() {
		return 1;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.element + "[]";
	}

	/**
	 * Provide the type of the element pointed.
	 *
	 * @return Type of the element pointed.
	 */
	@Override
	public Type getPointedType() {
		return this.element;
	}
}
