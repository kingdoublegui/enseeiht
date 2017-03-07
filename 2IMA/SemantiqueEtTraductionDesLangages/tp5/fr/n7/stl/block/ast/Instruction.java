/**
 * 
 */
package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public interface Instruction {
	
	public boolean checkType();
	
	public int allocateMemory(int _offset);
	
	public Fragment getCode(TAMFactory _factory);

}
