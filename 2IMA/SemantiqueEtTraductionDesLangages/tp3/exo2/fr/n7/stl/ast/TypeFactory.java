/**
 * 
 */
package fr.n7.stl.ast;

/**
 * @author Marc Pantel
 *
 */
public interface TypeFactory {
	
	public Type createIntegerType();
	
	public Type createBooleanType();
	
	public Type createCoupleType(Type _first, Type _second);

}
