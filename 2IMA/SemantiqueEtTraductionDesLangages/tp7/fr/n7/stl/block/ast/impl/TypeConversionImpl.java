/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.AtomicType;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a type conversion expression.
 * @author Marc Pantel
 *
 */
public class TypeConversionImpl implements Expression {

	private Expression target;
	private Type type;

	public TypeConversionImpl(Expression _target, Type _type) {
		this.target = _target;
		this.type = _type;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		throw new SemanticsUndefinedException("Semantics getType undefined in TypeConversionImpl.");
		// <REMOVE>
		// if (this.target.getType().compatibleWith(type)) {
		//	 return this.type;
		// } else {
		// 	return AtomicType.ErrorType;
		// }
		// </REMOVE>
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		throw new SemanticsUndefinedException("Semantics getCode undefined in TypeConversionImpl.");
	}

}
