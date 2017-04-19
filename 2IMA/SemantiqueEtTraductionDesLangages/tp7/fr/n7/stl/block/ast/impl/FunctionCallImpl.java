package fr.n7.stl.block.ast.impl;

import java.util.Iterator;
import java.util.List;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.FunctionCall;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a function call expression.
 * @author Marc Pantel
 *
 */
public class FunctionCallImpl implements FunctionCall {

	private Expression function;
	private List<Expression> parameters;

	public FunctionCallImpl(Expression _function) {
		this.function = _function;
	}

	@Override
	public String toString() {
		StringBuilder _result = new StringBuilder(function + "( ");
		Iterator<Expression> _iter = this.parameters.iterator();
		if (_iter.hasNext()) {
			_result.append(_iter.next());
		}
		while (_iter.hasNext()) {
			_result.append(" ,").append(_iter.next());
		}
		return  _result + ")";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.FunctionCall#add(fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public void add(Expression _parameter) {
		this.parameters.add(_parameter);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		throw new SemanticsUndefinedException( "getType is undefined in FunctionCallImpl.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		throw new SemanticsUndefinedException( "getCode is undefined in FunctionCallImpl.");
	}

}
