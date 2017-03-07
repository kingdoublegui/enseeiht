/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.BinaryOperator;
import fr.n7.stl.block.ast.Block;
import fr.n7.stl.block.ast.BlockFactory;
import fr.n7.stl.block.ast.BooleanValue;
import fr.n7.stl.block.ast.Collection;
import fr.n7.stl.block.ast.ConstantDeclaration;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.FieldDeclaration;
import fr.n7.stl.block.ast.FunctionCall;
import fr.n7.stl.block.ast.Instruction;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.block.ast.TypeDeclaration;
import fr.n7.stl.block.ast.UnaryOperator;
import fr.n7.stl.block.ast.Value;
import fr.n7.stl.block.ast.VariableDeclaration;

/**
 * @author Marc Pantel
 *
 */
public class BlockFactoryImpl implements BlockFactory {

	/**
	 * 
	 */
	public BlockFactoryImpl() {
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
	public Expression createVariableUse(VariableDeclaration _declaration) {
		return new VariableUseImpl(_declaration);
	}

	@Override
	public Expression createVariableUse(String _name) {
		return new VariableUseImpl(_name);
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
	public FunctionCall createFunctionCall(Expression _function) {
		return new FunctionCallImpl(_function);
	}

	@Override
	public Expression createArrayAccess(Expression _array, Expression _index) {
		return new ArrayAccessImpl(_array,_index);
	}

	@Override
	public Expression createFieldAccess(Expression _record, FieldDeclaration _field) {
		return new FieldAccessImpl(_record,_field);
	}

	@Override
	public Block createBlock() {
		return createBlock((Block)null);
	}

	@Override
	public Block createBlock(Iterable<Instruction> _content) {
		Block _local = createBlock();
		_local.addAll(_content);
		return _local;
	}

	@Override
	public Block createBlock(Block _context, Iterable<Instruction> _content) {
		Block _local = createBlock(_context);
		_local.addAll(_content);
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
	public Type createRecordType(Iterable<FieldDeclaration> _fields) {
		return new RecordTypeImpl(_fields);
	}

	@Override
	public Type createRecordType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstantDeclaration createConstantDeclaration(String _name, Type _type, Expression _value) {
		return new ConstantDeclarationImpl(_name,_type,_value);
	}

	@Override
	public TypeDeclaration createTypeDeclaration(String _name, Type _type) {
		return new TypeDeclarationImpl(_name,_type);
	}

	@Override
	public Type createArrayType(Type _element) {
		return new ArrayTypeImpl(_element);
	}

	@Override
	public Type createPointerType(Type _element) {
		return new PointerTypeImpl(_element);
	}

	@Override
	public Type createFunctionType(Type _result, Iterable<Type> _parameters) {
		return new FunctionTypeImpl(_result,_parameters);
	}

	@Override
	public FieldDeclaration createFieldDeclaration(String _name, Type _type) {
		return new FieldDeclarationImpl(_name,_type);
	}

	@Override
	public Expression createTypeConversion(Expression _target, Type _type) {
		return new TypeConversionImpl(_target,_type);
	}

	@Override
	public Collection createCollection() {
		return new CollectionImpl();
	}

	@Override
	public Collection createCollection(Iterable<Expression> _values) {
		return new CollectionImpl(_values);
	}

	@Override
	public Type createNamedType(TypeDeclaration _declaration) {
		// TODO Auto-generated method stub
		return new NamedTypeImpl(_declaration);
	}


}
