/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.CoupleType;
import fr.n7.stl.block.ast.Type;

/**
 * @author Marc Pantel
 *
 */
public class CoupleTypeImpl implements CoupleType {

	private Type first;
	private Type second;

	/**
	 * 
	 */
	public CoupleTypeImpl(Type _first, Type _second) {
		// TODO Auto-generated constructor stub
		this.first = _first;
		this.second = _second;
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Type#equalsTo(fr.n7.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if (_other instanceof CoupleType) {
			return new CoupleTypeImpl( 
					this.first.merge(((CoupleTypeImpl) _other).first),
					this.second.merge(((CoupleTypeImpl) _other).second));
		} else {
			return AtomicType.ErrorType;
		}
	}

	@Override
	public int length(int _padding) {
		return this.first.length(_padding) + this.second.length(_padding);
	}

	@Override
	public String toString() {
		return "< " + this.first + ", " + this.second + ">";
	}

}
