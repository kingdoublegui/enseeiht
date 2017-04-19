package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Assignable;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class PointerAssignmentImpl extends PointerAccessImpl implements Assignable {

	/**
	 * @param _pointer
	 */
	public PointerAssignmentImpl(Expression _pointer) {
		super(_pointer);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.impl.PointerAccessImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		fragment.append(this.pointer.getCode(_factory));
		fragment.add(_factory.createLoadI(this.getType().length()));

		return fragment;
	}
	
}
