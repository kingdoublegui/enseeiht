/**
 * 
 */
package fr.n7.stl.block.ast;

/**
 * Abstract Syntax Tree node specification for couple type.
 * @author Marc Pantel
 *
 */
public interface CoupleType extends Type {
	
	/**
	 * Provide the type of the first element in the couple.
	 * @return Type of the first element.
	 */
	public Type getFirst();
	
	/**
	 * Provide the type of the second element in the couple.
	 * @return Type of the second element.
	 */
	public Type getSecond();

}
