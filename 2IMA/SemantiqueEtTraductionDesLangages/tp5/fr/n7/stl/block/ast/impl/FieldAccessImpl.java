/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.FieldDeclaration;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author pantel2
 *
 */
public class FieldAccessImpl implements Expression {

	private Expression record;
	private String name;
	private FieldDeclaration field;

	public FieldAccessImpl(Expression _record, String _name) {
		this.record = _record;
		this.name = _name;
	}

	public FieldAccessImpl(Expression _record, FieldDeclaration _field) {
		this.record = _record;
		this.field = _field;
	}

	@Override
	public String toString() {
		return "FieldAccessImpl [record=" + record + ", name=" + name + ", field=" + field + "]";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		return field.getType();
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
