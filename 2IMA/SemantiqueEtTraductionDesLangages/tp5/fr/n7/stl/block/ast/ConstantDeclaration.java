/**
 * 
 */
package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Fragment;

/**
 * @author Marc Pantel
 *
 */
public interface ConstantDeclaration extends Instruction, Declaration {
	
	public Type getType();
	
	public Expression getValue();
	
	public Fragment getCode();

}
