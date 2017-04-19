package fr.n7.stl.block.ast.impl;

import java.util.Optional;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Instruction;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a conditional instruction.
 * @author Marc Pantel
 *
 */
public class ConditionalImpl implements Instruction {

	private Expression condition;
	private Block thenBranch;
	private Optional<Block> elseBranch;

	public ConditionalImpl(Expression _condition, Block _then, Block _else) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = Optional.of(_else);
	}

	public ConditionalImpl(Expression _condition, Block _then) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = Optional.empty();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "if (" + this.condition + " )" + this.thenBranch + (elseBranch.map(block -> (" else " + block)).orElse(""));
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		return this.condition.getType().compatibleWith(AtomicType.BooleanType) 
				&& this.thenBranch.checkType() 
				&& (elseBranch.map(Block::checkType).orElse(true));
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		this.thenBranch.allocateMemory(_register, _offset);
        elseBranch.ifPresent(block -> block.allocateMemory(_register, _offset));
		return 0;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();
		int id = _factory.createLabelNumber();

		fragment.append(condition.getCode(_factory));
		fragment.add(_factory.createJumpIf("else" + id, 0));
		fragment.append(this.thenBranch.getCode(_factory));
		fragment.add(_factory.createJump("fin_cond" + id));
		fragment.addSuffix("else" + id);
        elseBranch.ifPresent(block -> fragment.append(block.getCode(_factory)));
		fragment.addSuffix("fin_cond"+id);

		return fragment;
	}

}
