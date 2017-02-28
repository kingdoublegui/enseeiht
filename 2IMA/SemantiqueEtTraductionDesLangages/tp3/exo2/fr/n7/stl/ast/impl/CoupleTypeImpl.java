/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.AtomicType;
import fr.n7.stl.ast.CoupleType;
import fr.n7.stl.ast.Type;

/**
 * @author Marc Pantel
 *
 */
public class CoupleTypeImpl implements CoupleType {

	@Override
	public String toString() {
		return "CoupleTypeImpl [first=" + first + ", second=" + second + "]";
	}

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

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.CoupleType#getFirst()
	 */
//	@Override
//	public Type getFirst() {
//		// TODO Auto-generated method stub
//		return this.first;
//	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.CoupleType#getSecond()
	 */
//	@Override
//	public Type getSecond() {
//		// TODO Auto-generated method stub
//		return this.second;
//	}

}
