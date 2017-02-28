/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.AtomicType;
import fr.n7.stl.ast.BinaryOperator;
import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Type;

/**
 * @author Marc Pantel
 *
 */
public class BinaryExpressionImpl implements Expression {

	@Override
	public String toString() {
		return "BinaryExpressionImpl [leftParameter=" + leftParameter + ", rightParameter=" + rightParameter
				+ ", operator=" + operator + "]";
	}

	/**
	 * 
	 */
	private Expression leftParameter;
	
	private Expression rightParameter;
	
	private BinaryOperator operator;
	
	/**
	 * @param _left
	 * @param _operator
	 * @param _right
	 */
	BinaryExpressionImpl(Expression _left, BinaryOperator _operator, Expression _right) {
		this.leftParameter = _left;
		this.rightParameter = _right;
		this.operator = _operator;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		Type resultType = leftParameter.getType().merge(rightParameter.getType());
		switch (this.operator) {
			case Add: {
				if (resultType.compatibleWith(AtomicType.FloatingType) 
						|| resultType.compatibleWith(AtomicType.StringType))  {
					return resultType;
				} else {
					return AtomicType.ErrorType;
				}
			}
			case Substract:
			case Multiply:
			case Divide: {
				if (resultType.compatibleWith(AtomicType.FloatingType)) {
					return resultType;
				} else {
					return AtomicType.ErrorType;
				}
			}
			case Modulo: {
				if (resultType.compatibleWith(AtomicType.IntegerType)) {
					return resultType;
				} else {
					return AtomicType.ErrorType;
				}
			}
			case Lesser:
			case Greater:
			case LesserOrEqual:
			case GreaterOrEqual: {
				if (resultType.compatibleWith(AtomicType.FloatingType)) {
					return AtomicType.BooleanType;
				} else {
					return AtomicType.ErrorType;
				}				
			}
			case Equals:
			case Different: {
				if (resultType.equals(AtomicType.ErrorType)) {
					return resultType;
				} else {
					return AtomicType.BooleanType;
				}
			}
			default : return AtomicType.ErrorType;
		}
	}

}
