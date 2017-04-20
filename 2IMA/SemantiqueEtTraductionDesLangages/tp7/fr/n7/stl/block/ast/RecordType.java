package fr.n7.stl.block.ast;

import java.util.List;

/**
 * Abstract Syntax Tree node specification for record type.
 * @author Marc Pantel
 *
 */
public interface RecordType extends Type, TypeDeclaration {
	
	/**
	 * Add a field to a record type.
	 * @param _field The added field.
	 */
    void add(FieldDeclaration _field);
	
	/**
	 * Add a sequence of fields to a record type.
	 * @param _fields : Sequence of fields to be added.
	 */
    void addAll(Iterable<FieldDeclaration> _fields);

	/* (non-Javadoc)
     * @see fr.n7.stl.block.ast.Scope#getFields(java.lang.String)
     */
	List<FieldDeclaration> getFields();

}
