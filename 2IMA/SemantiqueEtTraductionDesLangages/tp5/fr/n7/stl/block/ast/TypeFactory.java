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
	
	public Type createIntegerType();
	
	public Type createBooleanType();
	
	public Type createCoupleType(Type _first, Type _second);
	
	public Type createArrayType(Type _element);
	
	public Type createPointerType(Type _element);
	
	public Type createFunctionType(Type _result, Iterable<Type> _parameters);
	
	public Type createNamedType(TypeDeclaration _declaration);
	
	public Type createRecordType();

	public Type createRecordType(Iterable<FieldDeclaration> _fields);
	
	public FieldDeclaration createFieldDeclaration(String _name, Type _type);

}
