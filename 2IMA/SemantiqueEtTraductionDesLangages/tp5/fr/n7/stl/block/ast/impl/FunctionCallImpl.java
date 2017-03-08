/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import java.util.List;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.FunctionCall;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author pantel2
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
		return "FunctionCallImpl [function=" + function + ", parameters=" + parameters + "]";
	}

	@Override
	public void add(Expression _parameter) {
		// TODO Auto-generated method stub
		this.parameters.add(_parameter);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
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
