/**
 * 
 */
package fr.n7.stl.block.ast;

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

	/**
	 * @param _declaration Abstract Syntax Tree node for the declaration of the variable.
	 * @return Abstract Syntax Tree node for the access to a variable.
	 */
	public Expression createVariableUse(VariableDeclaration _declaration);

	/**
	 * @param _left Abstract Syntax Tree node for the left part of the couple.
	 * @param _right Abstract Syntax Tree node for the right part of the couple.
	 * @return Abstract Syntax Tree node for a couple containing the _left and _right parts.
	 */
	public Expression createCouple(Expression _left, Expression _right);
	
	/**
	 * @param _couple Abstract Syntax Tree node for the couple.
	 * @return Abstract Syntax Tree node for the left part of the _couple.
	 */
	public Expression createFirst(Expression _couple);
	
	/**
	 * @param _couple Abstract Syntax Tree node for the couple.
	 * @return Abstract Syntax Tree node for the right part of the _couple.
	 */
	public Expression createSecond(Expression _couple);
	
	/**
	 * @param _called Abstract Syntax Tree node for the called function.
	 * @return Abstract Syntax Tree node for the call of the _called function.
	 */
	public FunctionCall createFunctionCall(Expression _called);
	
	/**
	 * @param _array Abstract Syntax Tree node for the array.
	 * @param _index Abstract Syntax Tree node for the index in the array.
	 * @return Abstract Syntax Tree node for the access of the _index cell in the _array.
	 */
	public Expression createArrayAccess(Expression _array, Expression _index);
	
	/**
	 * @param _record Abstract Syntax Tree node for the record.
	 * @param _field Abstract Syntax Tree node for the field in the record.
	 * @return Abstract Syntax Tree node for the access of the _field in the _record.
	 */
	public Expression createFieldAccess(Expression _record, FieldDeclaration _field);
	
	/**
	 * @param _target Abstract Syntax Tree for the expression whose value is converted.
	 * @param _type Abstract Syntax Tree for the type toward which the expression value is converted.
	 * @return Abstract Syntax Tree node for the conversion of the value of a target expression to a type.
	 */
	public Expression createTypeConversion(Expression _target, Type _type);

	/**
	 * @return Abstract Syntax Tree node for the creation of a collection of values.
	 */
	public Collection createCollection();

	/**
	 * @param _values Abstract Syntax Tree nodes for the values in the collection.
	 * @return Abstract Syntax Tree node for the creation of a collection of values.
	 */
	public Collection createCollection(Iterable<Expression> _values);
}
