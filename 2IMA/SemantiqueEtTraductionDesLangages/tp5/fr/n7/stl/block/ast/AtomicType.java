/**
 * 
 */
package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public enum AtomicType implements Type {
	BooleanType,
	CharacterType,
	FloatingType,
	IntegerType,
	StringType,
	VoidType,
	ErrorType
	;

	@Override
	public boolean equalsTo(Type _other) {
		return this == _other;
	}

	@Override
	public boolean compatibleWith(Type _other) {
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
	
	@Override
	public int length(int _padding) throws IllegalArgumentException {
		switch (this) {
			case BooleanType : return _padding;
			default : throw new IllegalArgumentException(); 
		}
	}
	
	@Override
	public String toString() {
		switch (this) {
		case BooleanType: return "boolean";
		case CharacterType: return "char";
		case ErrorType: return "error";
		case FloatingType: return "float";
		case IntegerType: return "int";
		case StringType: return "String";
		case VoidType: return "void";
		default: return "???";
		}
	}

}
