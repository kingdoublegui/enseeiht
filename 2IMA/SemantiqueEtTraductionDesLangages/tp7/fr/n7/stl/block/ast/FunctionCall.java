/**
 * 
 */
package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface FunctionCall extends Expression {
	
	/**
	 * Add a parameter at the end of the list of parameters in a function call.
	 * @param _parameter AST for the _parameter added to the function call node.
	 */
	public void add(Expression _parameter);

}
