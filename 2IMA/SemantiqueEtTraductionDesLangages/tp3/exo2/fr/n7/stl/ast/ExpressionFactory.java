/**
 * 
 */
package fr.n7.stl.ast;

/**
 * Factory to create Abstract Syntax Tree nodes for common expressions in programming languages.
 * @author Marc Pantel
 *
 */
public interface ExpressionFactory {

	/**
	 * @param _left Abstract Syntax Tree for the left parameter of the binary operation.
	 * @param _operator Enum value for the binary operator.
	 * @param _right Abstract Syntax Tree for the right parameter of the binary operation.
	 * @return Abstract Syntax Tree node for the binary operation.
	 */
	public Expression createBinaryExpression(Expression _left, BinaryOperator _operator, Expression _right);

	/**
	 * @param _operator Enum value for the unary operator.
	 * @param _parameter Abstract Syntax Tree for the parameter of the unary operation.
	 * @return Abstract Syntax Tree node for the unary operation.
	 */
	public Expression createUnaryExpression(UnaryOperator _operator, Expression _parameter);
	
	/**
	 * @param _texte Textual representation of the value of the Abstract Syntax Tree IntegerValue node.
	 * @return Abstract Syntax Tree node for the integer constant.
	 */
	public Value createIntegerValue(String _texte);
	
	/**
	 * @param _value Boolean value for the Abstract Syntax Tree BooleanValue node.
	 * @return Abstract Syntax Tree node for the boolean constant.
	 */
	public Value createBooleanValue(boolean _value);
	
	/**
	 * @param _name Name of the accessed variable.
	 * @return Abstract Syntax Tree node for the access to a variable.
	 */
	public Expression createVariableUse(String _name);
	
	public Expression createCouple(Expression _left, Expression _right);
	
	public Expression createFirst(Expression _parameter);
	
	public Expression createSecond(Expression _parameter);
	
}
