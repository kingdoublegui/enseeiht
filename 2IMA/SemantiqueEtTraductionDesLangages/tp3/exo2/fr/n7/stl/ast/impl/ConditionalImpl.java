/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.Block;
import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Instruction;

/**
 * @author pantel2
 *
 */
public class ConditionalImpl implements Instruction {

	@Override
	public String toString() {
		return "ConditionalImpl [condition=" + condition + ", thenBranch=" + thenBranch + ", elseBranch=" + elseBranch
				+ "]";
	}

	private Expression condition;
	private Block thenBranch;
	private Block elseBranch;

	public ConditionalImpl(Expression _condition, Block _then, Block _else) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = _else;
	}

	public ConditionalImpl(Expression _condition, Block _then) {
		this(_condition,_then,null);
	}

}
