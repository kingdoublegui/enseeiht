/**
 * 
 */
package fr.n7.stl.block.ast;

/**
 * Factory to create nodes for types.
 * @author Marc Pantel
 *
 */
public interface TypeFactory {

	/**
	 * Create an Abstract Syntax Tree node for the integer type.
	 * @return Abstract Syntax Tree node for the integer type.
	 */
	public Type createIntegerType();
	
	/**
	 * Create an Abstract Syntax Tree node for the boolean type.
	 * @return Abstract Syntax Tree node for the boolean type.
	 */
	public Type createBooleanType();
	
	/**
	 * Create an Abstract Syntax Tree node for the float type.
	 * @return Abstract Syntax Tree node for the float type.
	 */
	public Type createFloatingType();
	
	/**
	 * Create an Abstract Syntax Tree node for the char type.
	 * @return Abstract Syntax Tree node for the char type.
	 */
	public Type createCharType();

	/**
	 * Create an Abstract Syntax Tree node for the String type.
	 * @return Abstract Syntax Tree node for the String type.
	 */
	public Type createStringType();
	
	/**
	 * Create an Abstract Syntax Tree node for a couple type.
	 * @param _first Abstract Syntax Tree for the type of the first element of the couple.
	 * @param _second Abstract Syntax Tree for the type of the second element of the couple.
	 * @return Abstract Syntax Tree node for a couple type.
	 */
	public Type createCoupleType(Type _first, Type _second);
	
	/** 
	 * Create an Abstract Syntax Tree node for an array type.
	 * @param _element Abstract Syntax Tree for the type of the elements in the array type.
	 * @return Abstract Syntax Tree node for an array type.
	 */
	public Type createArrayType(Type _element);
	
	/**
	 * Create an Abstract Syntax Tree node for a pointer type.
	 * @param _element Abstract Syntax Tree for the type of the target element in the pointer type.
	 * @return Abstract Syntax Tree node for a pointer type.
	 */
	public Type createPointerType(Type _element);
	
	/**
	 * Create an Abstract Syntax Tree node for a function type.
	 * @param _result Abstract Syntax Tree for the type of the result in the function type.
	 * @param _parameters Abstract Syntax Trees for the types of the sequence of parameters in the function type.
	 * @return Abstract Syntax Tree node for a function type.
	 */
	public Type createFunctionType(Type _result, Iterable<Type> _parameters);
	
	/**
	 * Create an Abstract Syntax Tree node for a named type (e.g. in a type definition).
	 * @param _declaration Abstract Syntax Tree for the target type in the named type.
	 * @return Abstract Syntax Tree node for a named type.
	 */
	public Type createNamedType(TypeDeclaration _declaration);
	
	/**
	 * Create an Abstract Syntax Tree node for an empty (i.e. without fields) record type.
	 * The fields can be added later on.
	 * @param _name Name of the record type.
	 * @return Abstract Syntax Tree node for a record type.
	 */
	public RecordType createRecordType(String _name);

	/**
	 * Create an Abstract Syntax Tree node for record type containing fields.
	 * @param _name Name of the record type.
	 * @param _fields Abstract Syntax Tree nodes for the fields in the record type.
	 * @return Abstract Syntax Tree node for a record type.
	 */
	public RecordType createRecordType(String _name, Iterable<FieldDeclaration> _fields);
	
	/**
	 * Create an Abstract Syntax Tree node for a field declaration in a record type.
	 * @param _name Name of the record field.
	 * @param _type Type of the record field.
	 * @return  Abstract Syntax Tree node for a field declaration in a record type.
	 */
	public FieldDeclaration createFieldDeclaration(String _name, Type _type);

}
