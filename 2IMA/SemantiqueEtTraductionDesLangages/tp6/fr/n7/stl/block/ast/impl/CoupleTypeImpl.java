/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.CoupleType;
import fr.n7.stl.block.ast.Type;

/**
 * Implementation of the Abstract Syntax Tree node for a couple type.
 * @author Marc Pantel
 *
 */
public class CoupleTypeImpl implements CoupleType {

	private Type first;
	private Type second;

	/**
	 * Builds a couple type.
	 * @param _first Type of the first part of the couple.
	 * @param _second Type of the second part of the couple.
	 */
	public CoupleTypeImpl(Type _first, Type _second) {
		this.first = _first;
		this.second = _second;
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Type#equalsTo(fr.n7.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof CoupleType) {
			return this.first.equalsTo(((CoupleTypeImpl) _other).first)
					&& this.second.equalsTo(((CoupleTypeImpl) _other).second);
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Type#compatibleWith(fr.n7.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		if (_other instanceof CoupleType) {
			return this.first.compatibleWith(((CoupleTypeImpl) _other).first)
					&& this.second.compatibleWith(((CoupleTypeImpl) _other).second);
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Type#merge(fr.n7.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		if (_other instanceof CoupleType) {
			return new CoupleTypeImpl( 
					this.first.merge(((CoupleTypeImpl) _other).first),
					this.second.merge(((CoupleTypeImpl) _other).second));
		} else {
			return AtomicType.ErrorType;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#length()
	 */
	@Override
	public int length() {
		return this.first.length() + this.second.length();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "< " + this.first + ", " + this.second + ">";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.CoupleType#getFirst()
	 */
	@Override
	public Type getFirst() {
		return this.first;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.CoupleType#getSecond()
	 */
	@Override
	public Type getSecond() {
		return this.second;
	}

}
