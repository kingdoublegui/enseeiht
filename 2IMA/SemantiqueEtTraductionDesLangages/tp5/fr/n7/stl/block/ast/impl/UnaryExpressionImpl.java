/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.UnaryOperator;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class UnaryExpressionImpl implements Expression {

	private UnaryOperator operator;
	private Expression parameter;
	
	/**
	 * 
	 */
	public UnaryExpressionImpl(UnaryOperator _operator, Expression _parameter) {
		this.operator = _operator;
		this.parameter = _parameter;
	}

	@Override
	public String toString() {
		return "(" + this.operator + " " + this.parameter + ")";
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
