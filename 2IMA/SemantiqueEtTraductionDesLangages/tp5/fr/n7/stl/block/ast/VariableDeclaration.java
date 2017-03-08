/**
 * 
 */
package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Location;

/**
 * @author Marc Pantel
 *
 */
public interface VariableDeclaration extends Declaration, Instruction {

	public Type getType();
	
	public Location getLocation();

}
