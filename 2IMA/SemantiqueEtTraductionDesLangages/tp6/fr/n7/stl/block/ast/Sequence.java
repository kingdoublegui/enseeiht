/**
 * 
 */
package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface Sequence extends Expression {
	
	/**
	 * Adds an expression at the end of a sequence of expression.
	 * @param _value AST of the expression added to the sequence.
	 */
	public void add(Expression _value);

}
