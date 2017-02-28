/**
 * 
 */
package fr.n7.stl.ast;

/**
 * @author pantel2
 *
 */
public enum AtomicType implements Type {
	BooleanType,
	CharacterType,
	FloatingType,
	IntegerType,
	StringType,
	ErrorType
	;

	@Override
	public boolean equalsTo(Type _other) {
		// TODO Auto-generated method stub
		return this == _other;
	}

	@Override
	public boolean compatibleWith(Type _other) {
		// TODO Auto-generated method stub
		if (this.equalsTo(_other)) {
			return true;
		} else {
			switch (this) {
			case IntegerType: return (_other == FloatingType);
			default: return false;
			}
		}
	}

	@Override
	public Type merge(Type _other) {
		// TODO Auto-generated method stub
		if (this.compatibleWith(_other)) {
			return _other;
		} else {
			if (_other.compatibleWith(this)) {
				return this;
			} else {
				return ErrorType;
			}
		}
	}

}
