/**
 * 
 */
package fr.n7.stl.ast;

import java.util.Iterator;

/**
 * @author Marc Pantel
 *
 */
public interface Block {
	
	/**
	 * @return Number of variable declaration in the block
	 */
	public int getDeclarationNumber();
	
	/**
	 * @param _position
	 * @return 
	 */
	public Declaration getDeclaration(int _position);
	
	public Iterator<Declaration> declarations();
	
	public void add(Declaration _declaration);
	
	public int getInstructionNumber();
	
	public Instruction getInstruction(int _position);
	
	public Iterator<Instruction> instructions();
	
	public void add(Instruction _instruction);
	
	public Block getContext();
	
	public void checkType();
	
	public void generateCode();

}
