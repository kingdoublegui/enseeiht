/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import java.util.LinkedList;

import fr.n7.stl.block.ast.Collection;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class CollectionImpl implements Collection {

	private LinkedList<Expression> values;

	public CollectionImpl(Iterable<Expression> _values) {
		this();
		for (Expression _value : _values) {
			this.values.add(_value);
		}
	}

	public CollectionImpl() {
		this.values = new LinkedList<Expression>();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Collection#add(fr.n7.stl.block.ast.Expression)
	 */
	@Override
	public void add(Expression _value) {
		// TODO Auto-generated method stub

	}

}
