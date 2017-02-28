/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Type;
import fr.n7.stl.ast.UnaryOperator;

/**
 * @author Marc Pantel
 *
 */
public class UnaryExpressionImpl implements Expression {

	@Override
	public String toString() {
		return "UnaryExpressionImpl [operator=" + operator + ", parameter=" + parameter + "]";
	}

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
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
