package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Assignable;
import fr.n7.stl.block.ast.VariableDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class VariableAssignmentImpl extends VariableUseImpl implements Assignable {

	public VariableAssignmentImpl(VariableDeclaration _declaration) {
		super(_declaration);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.impl.VariableUseImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		fragment.add(_factory.createLoadA(
				this.declaration.getRegister(),
				this.declaration.getOffset()));

		return fragment;
	}

}
