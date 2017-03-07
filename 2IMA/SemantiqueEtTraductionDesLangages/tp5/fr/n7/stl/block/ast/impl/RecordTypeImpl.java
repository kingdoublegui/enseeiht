/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import fr.n7.stl.block.ast.FieldDeclaration;
import fr.n7.stl.block.ast.ForbiddenDeclarationException;
import fr.n7.stl.block.ast.Scope;
import fr.n7.stl.block.ast.Type;

/**
 * @author pantel2
 *
 */
public class RecordTypeImpl implements Type, Scope<FieldDeclaration> {

	private List<FieldDeclaration> fields;

	public RecordTypeImpl(Iterable<FieldDeclaration> _fields) {
		this.fields = new LinkedList<FieldDeclaration>();
		for (FieldDeclaration _field : _fields) {
			this.fields.add(_field);
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#equalsTo(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean equalsTo(Type _other) {
		if (_other instanceof RecordTypeImpl) {
			return true;
		} else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#compatibleWith(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public boolean compatibleWith(Type _other) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Type#merge(fr.n7.stl.block.ast.Type)
	 */
	@Override
	public Type merge(Type _other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<FieldDeclaration> get(String _name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(String _name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accepts(FieldDeclaration _declaration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void register(FieldDeclaration _declaration) throws ForbiddenDeclarationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int length(int _padding) {
		int _length = 0;
		for (FieldDeclaration f : this.fields) {
			_length += f.getType().length(_padding);
		}
		return _length;
	}

}
