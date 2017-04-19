package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.TypeDeclaration;

/**
 * Implementation of the Abstract Syntax Tree node for a named type.
 * @author Marc Pantel
 *
 */
public class NamedTypeImpl implements Type {

	private TypeDeclaration declaration;

	public NamedTypeImpl(TypeDeclaration _declaration) {
		this.declaration = _declaration;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof NamedTypeImpl) {
			return (this.declaration.getName().equals(((NamedTypeImpl) _other).declaration.getName()));
		} else {
			return (this.declaration.getType().equalsTo(_other));
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		if (_other instanceof NamedTypeImpl) {
			return (this.declaration.getName().equals(((NamedTypeImpl) _other).declaration.getName()));
		} else {
			return (this.declaration.getType().compatibleWith(_other));
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		if (_other instanceof NamedTypeImpl) {
			if (this.declaration.getName().equals(((NamedTypeImpl) _other).declaration.getName())) {
				return this;
			} else {
				return AtomicType.ErrorType;
			}
		} else {
			return (this.declaration.getType().merge(_other));
		}
	}
	
	/**
	 * Provide the target type of the named type (i.e. type associated to the name).
	 * @return Type associated to the name.
	 */
	public Type getType() {
		Type _result = this.declaration.getType();
		if (_result instanceof NamedTypeImpl) {
			return ((NamedTypeImpl) _result).getType();
		} else {
			return _result;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#length(int)
	 */
	@Override
	public int length() {
		return this.declaration.getType().length();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "typedef " + this.declaration.getType() + " " + this.declaration.getName() + ";";
	}


}
