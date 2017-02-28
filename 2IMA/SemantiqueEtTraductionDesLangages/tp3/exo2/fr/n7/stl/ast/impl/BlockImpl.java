/**
 * 
 */
package fr.n7.stl.ast.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.ast.Block;
import fr.n7.stl.ast.Declaration;
import fr.n7.stl.ast.Instruction;

/**
 * @author Marc Pantel
 *
 */
public class BlockImpl implements Block {
	
	@Override
	public String toString() {
		return "BlockImpl [instructions=" + instructions + ", declarations=" + declarations + ", context=" + context
				+ "]";
	}

	private List<Instruction> instructions;
	private List<Declaration> declarations;
	private Block context;

	/**
	 * 
	 */
	public BlockImpl(Block _context) {
		// TODO Auto-generated constructor stub
		this.instructions = new LinkedList<Instruction>();
		this.declarations = new LinkedList<Declaration>();
		this.context = _context;
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Block#getInstructionNumber()
	 */
	@Override
	public int getInstructionNumber() {
		// TODO Auto-generated method stub
		return instructions.size();
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Block#getInstruction(int)
	 */
	@Override
	public Instruction getInstruction(int _position) {
		// TODO Auto-generated method stub
		return instructions.get(_position);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Block#add(fr.n7.block.ast.Instruction)
	 */
	@Override
	public void add(Instruction _instruction) {
		// TODO Auto-generated method stub
		instructions.add(_instruction);
	}

	@Override
	public int getDeclarationNumber() {
		// TODO Auto-generated method stub
		return declarations.size();
	}

	@Override
	public Declaration getDeclaration(int _position) {
		// TODO Auto-generated method stub
		return declarations.get(_position);
	}

	@Override
	public Iterator<Declaration> declarations() {
		// TODO Auto-generated method stub
		return declarations.iterator();
	}

	@Override
	public void add(Declaration _declaration) {
		// TODO Auto-generated method stub
		declarations.add(_declaration);
	}

	@Override
	public Iterator<Instruction> instructions() {
		// TODO Auto-generated method stub
		return instructions.iterator();
	}

	@Override
	public Block getContext() {
		// TODO Auto-generated method stub
		return this.context;
	}

	@Override
	public void checkType() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generateCode() {
		// TODO Auto-generated method stub
		
	}

}
