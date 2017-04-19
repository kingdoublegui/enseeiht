package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Assignable;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class ArrayAssignmentImpl extends ArrayAccessImpl implements Assignable {

	/**
	 * @param _array
	 * @param _index
	 */
	public ArrayAssignmentImpl(Expression _array, Expression _index) {
		super(_array, _index);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.impl.ArrayAccessImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		fragment.append(this.array.getCode(_factory));
		fragment.add(_factory.createLoadI(1));
		fragment.append(this.index.getCode(_factory));
		fragment.add(_factory.createLoadL(this.getType().length()));
		fragment.add(Library.IMul);
		fragment.add(Library.IAdd);

		return fragment;
	}
	
}
