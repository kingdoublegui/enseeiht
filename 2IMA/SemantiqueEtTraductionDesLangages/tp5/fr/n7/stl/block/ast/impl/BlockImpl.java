/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.ConstantDeclaration;
import fr.n7.stl.block.ast.Declaration;
import fr.n7.stl.block.ast.ForbiddenDeclarationException;
import fr.n7.stl.block.ast.Instruction;
import fr.n7.stl.block.ast.TypeDeclaration;
import fr.n7.stl.block.ast.VariableDeclaration;

/**
 * @author Marc Pantel
 *
 */
public class BlockImpl implements Block {
	
	@Override
	public String toString() {
		String _local = "";
		for (Instruction _instruction : this.instructions) {
			_local += _instruction;
		}
		return "{\n" + _local + "}\n" ;
	}

	private List<Instruction> instructions;
	// private List<Declaration> declarations;
	private Optional<Block> context;
	private List<VariableDeclaration> variables;
	private List<ConstantDeclaration> constants;
	private List<TypeDeclaration> types;

	/**
	 * 
	 */
	public BlockImpl(Block _context) {
		assert( _context != null);
		this.instructions = new LinkedList<Instruction>();
		this.variables = new LinkedList<VariableDeclaration>();
		this.constants = new LinkedList<ConstantDeclaration>();
		this.types = new LinkedList<TypeDeclaration>();
		if (_context == null) {
			this.context = Optional.empty();
		} else {
			this.context = Optional.of(_context);
		}
	}
	
	/**
	 * 
	 */
	public BlockImpl() {
		this.instructions = new LinkedList<Instruction>();
		this.variables = new LinkedList<VariableDeclaration>();
		this.constants = new LinkedList<ConstantDeclaration>();
		this.types = new LinkedList<TypeDeclaration>();
		this.context = Optional.empty();
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Block#getInstructionNumber()
	 */
	@Override
	public int getInstructionNumber() {
		return instructions.size();
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Block#getInstruction(int)
	 */
	@Override
	public Instruction getInstruction(int _position) {
		return instructions.get(_position);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.Block#add(fr.n7.block.ast.Instruction)
	 */
	@Override
	public void add(Instruction _instruction) {
		this.instructions.add(_instruction);
		if (_instruction instanceof Declaration) {
			this.register((Declaration)_instruction);
		}
	}

	@Override
	public void register(Declaration _declaration) {
		if (_declaration instanceof VariableDeclaration) {
			this.variables.add((VariableDeclaration)_declaration);
		} else {
			if (_declaration instanceof ConstantDeclaration) {
				this.constants.add((ConstantDeclaration) _declaration);
			} else {
				if (_declaration instanceof TypeDeclaration) {
					this.types.add((TypeDeclaration) _declaration);
				}
			}
		}
	}

	@Override
	public Iterator<Instruction> instructions() {
		return instructions.iterator();
	}

	@Override
	public boolean knows(String _name) {
		if (this.contains(_name)) {
			return true;
		} else {
			if (context.isPresent()) {
				return context.get().knows(_name);
			} else {
				return false;
			}
		}
	}

	@Override
	public Optional<Declaration> get(String _name) {
		for (Declaration _declaration : this.variables) {
			if (_declaration.getName().equals(_name)) {
				return Optional.of(_declaration);
			}
		}
		for (Declaration _declaration : this.constants) {
			if (_declaration.getName().equals(_name)) {
				return Optional.of(_declaration);
			}
		}
		for (Declaration _declaration : this.types) {
			if (_declaration.getName().equals(_name)) {
				return Optional.of(_declaration);
			}
		}
		return Optional.empty();
	}

	@Override
	public boolean contains(String _name) {
		for (Declaration _declaration : this.variables) {
			if (_declaration.getName().contentEquals(_name)) {
				return true;
			}
		}
		for (Declaration _declaration : this.constants) {
			if (_declaration.getName().contentEquals(_name)) {
				return true;
			}
		}
		for (Declaration _declaration : this.types) {
			if (_declaration.getName().contentEquals(_name)) {
				return true;
			}
		}
		return false;		
	}

	@Override
	public boolean accepts(Declaration _declaration) {
		return (! this.contains(_declaration.getName()));
	}

	@Override
	public void addAll(Iterable<Instruction> _instructions) {
		for (Instruction i : _instructions) {
			this.instructions.add(i);
		}
	}

}
