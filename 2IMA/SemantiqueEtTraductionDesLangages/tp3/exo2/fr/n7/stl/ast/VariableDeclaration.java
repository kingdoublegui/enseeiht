/**
 * 
 */
package fr.n7.stl.ast;

/**
 * @author Marc Pantel
 *
 */
public interface VariableDeclaration extends Declaration, Instruction {

	public Type getType();
	
	public String getName();
	
}
