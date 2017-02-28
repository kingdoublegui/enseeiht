/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.AtomicType;
import fr.n7.stl.ast.BinaryOperator;
import fr.n7.stl.ast.Block;
import fr.n7.stl.ast.BlockFactory;
import fr.n7.stl.ast.BooleanValue;
import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Instruction;
import fr.n7.stl.ast.Type;
import fr.n7.stl.ast.UnaryOperator;
import fr.n7.stl.ast.Value;
import fr.n7.stl.ast.VariableDeclaration;

/**
 * @author Marc Pantel
 *
 */
public class BlockFactoryImpl implements BlockFactory {

	/**
	 * 
	 */
	public BlockFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createBinaryExpression(fr.n7.block.ast.Expression, fr.n7.block.ast.Expression)
	 */
	@Override
	public Expression createBinaryExpression(Expression _left, BinaryOperator _operator, Expression _right) {
		return new BinaryExpressionImpl(_left,_operator,_right);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createIntegerValue()
	 */
	@Override
	public Value createIntegerValue(String _texte) {
		return new IntegerValueImpl(_texte);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createBlock()
	 */
	@Override
	public Block createBlock(Block _context) {
		return new BlockImpl(_context);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createVariableDeclaration(java.lang.String, fr.n7.block.ast.Type, fr.n7.block.ast.Expression)
	 */
	@Override
	public VariableDeclaration createVariableDeclaration(String _name, Type _type, Expression _initialValue) {
		return new VariableDeclarationImpl(_name,_type,_initialValue);
	}

	/* (non-Javadoc)
	 * @see fr.n7.block.ast.ASTFactory#createIntegerType()
	 */
	@Override
	public Type createIntegerType() {
		return AtomicType.IntegerType;
	}

	@Override
	public Expression createUnaryExpression(UnaryOperator _operator, Expression _parameter) {
		return new UnaryExpressionImpl(_operator,_parameter);
	}

	@Override
	public Instruction createConditional(Expression _condition, Block _then, Block _else) {
		return new ConditionalImpl(_condition,_then,_else);
	}

	@Override
	public Instruction createRepetition(Expression _condition, Block _body) {
		return new RepetitionImpl(_condition,_body);
	}

	@Override
	public Type createBooleanType() {
		return AtomicType.BooleanType;
	}

	@Override
	public Type createCoupleType(Type _first, Type _second) {
		return new CoupleTypeImpl( _first, _second);
	}

	@Override
	public Expression createCouple(Expression _first, Expression _second) {
		return new CoupleImpl(_first, _second);
	}

	@Override
	public Instruction createPrinter(Expression _value) {
		return new PrinterImpl(_value);
	}

	@Override
	public Expression createFirst(Expression _parameter) {
		return new UnaryExpressionImpl(UnaryOperator.First,_parameter);
	}

	@Override
	public Expression createSecond(Expression _parameter) {
		return new UnaryExpressionImpl(UnaryOperator.Second,_parameter);
	}

	@Override
	public Instruction createAssignment(VariableDeclaration _declaration, Expression _value) {
		return new AssignmentImpl(_declaration,_value);
	}

	@Override
	public Value createBooleanValue(boolean _value) {
		if (_value) {
			return BooleanValue.True;
		} else {
			return BooleanValue.False;
		}
	}

	@Override
	public Block createBlock() {
		return createBlock((Block)null);
	}

	@Override
	public Block createBlock(Iterable<Instruction> _content) {
		return createBlock(null,_content);
	}

	@Override
	public Block createBlock(Block _context, Iterable<Instruction> _content) {
		Block _local = createBlock(_context);
		for (Instruction i : _content) {
			_local.add(i);
		}
		return _local;
	}

	@Override
	public Instruction createAssignment(String _name, Expression _value) {
		return new AssignmentImpl(_name,_value);
	}

	@Override
	public Instruction createConditional(Expression _condition, Block _then) {
		return new ConditionalImpl(_condition,_then);
	}

	@Override
	public Expression createVariableUse(String _name) {
		return new VariableUseImpl(_name);
	}

}
