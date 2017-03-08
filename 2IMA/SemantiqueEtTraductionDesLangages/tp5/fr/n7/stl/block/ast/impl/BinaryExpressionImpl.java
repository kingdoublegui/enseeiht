/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.BinaryOperator;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class BinaryExpressionImpl implements Expression {

	/**
	 * 
	 */
	private Expression left;
	
	private Expression right;
	
	private BinaryOperator operator;
	
	/**
	 * @param _left
	 * @param _operator
	 * @param _right
	 */
	BinaryExpressionImpl(Expression _left, BinaryOperator _operator, Expression _right) {
		this.left = _left;
		this.right = _right;
		this.operator = _operator;
	}

	@Override
	public String toString() {
		return "(" + this.left + " " + this.operator + " " + this.right + ")";
	}
	
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		Type resultType = this.left.getType().merge(this.right.getType());
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

	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _code = this.left.getCode(null);
		_code.append(this.right.getCode(null));
		_code.add(TAMFactory.createBinaryOperator(this.operator));
		return _code;
	}

}
