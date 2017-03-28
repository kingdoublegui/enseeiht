/**
 * 
 */
package fr.n7.stl.block.ast;

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
	public void add(FieldDeclaration _field);
	
	/**
	 * Add a sequence of fields to a record type.
	 * @param _fields : Sequence of fields to be added.
	 */
	public void addAll(Iterable<FieldDeclaration> _fields);

}
