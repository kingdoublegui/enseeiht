package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.IntegerValue;
import fr.n7.stl.block.ast.PointerType;
import fr.n7.stl.block.ast.Type;

/**
 * Implementation of the Abstract Syntax Tree node for a pointer type.
 * @author Marc Pantel
 *
 */
public class PointerTypeImpl implements Type, PointerType {

	private Type element;

	public PointerTypeImpl(Type _element) {
		this.element = _element;
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

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof PointerType) {
			return this.element.equalsTo(((PointerTypeImpl) _other).element);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		if (_other instanceof PointerType) {
			return this.element.equalsTo(((PointerTypeImpl) _other).element);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		if (_other instanceof PointerTypeImpl) {
			return new PointerTypeImpl(this.element.merge(((PointerTypeImpl)_other).element));
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
		return "(" + this.element + " *)";
	}

	public Type getType() {
		return this;
	}

}
