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
public class RepetitionImpl implements Instruction {

	@Override
	public String toString() {
		return "RepetitionImpl [condition=" + condition + ", body=" + body + "]";
	}

	private Expression condition;
	private Block body;

	public RepetitionImpl(Expression _condition, Block _body) {
		this.condition = _condition;
		this.body = _body;
	}

}
