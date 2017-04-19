package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.FieldDeclaration;
import fr.n7.stl.block.ast.Type;

/**
 * Implementation of the Abstract Syntax Tree node for a field in a record.
 * @author Marc Pantel
 *
 */
public class FieldDeclarationImpl implements FieldDeclaration {

	private String name;
	private Type type;

	public FieldDeclarationImpl(String _name, Type _type) {
		this.name = _name;
		this.type = _type;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Declaration#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.FieldDeclaration#getType()
	 */
	@Override
	public Type getType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.type + " " + this.name + ";";
	}

}
