/**
 * 
 */
package fr.n7.stl.block.ast;

import java.util.Iterator;

/**
 * A block contains declarations. It is thus a Scope even if a separate SymbolTable is used in
 * the attributed semantics in order to manage declarations.
 * @author Marc Pantel
 *
 */
public interface Block extends HierarchicalScope<Declaration> {
	
	public int getInstructionNumber();
	
	public Instruction getInstruction(int _position);
	
	public Iterator<Instruction> instructions();
	
	public void add(Instruction _instruction);
	
	public void addAll(Iterable<Instruction> _instructions);

}
