package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.*;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Library;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

import java.lang.reflect.Field;

/**
 * @author Marc Pantel
 *
 */
public class FieldAssignmentImpl extends FieldAccessImpl implements Assignable {

	/**
	 * @param _record
	 * @param _name
	 */
	public FieldAssignmentImpl(Expression _record, String _name) {
		super(_record, _name);
	}

	/**
	 * @param _record
	 * @param _field
	 */
	public FieldAssignmentImpl(Expression _record, FieldDeclaration _field) {
		super(_record, _field);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.impl.FieldAccessImpl#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment fragment = _factory.createFragment();

		Expression r = this;
		int off = 0;

		while (r instanceof FieldAssignmentImpl) {
			off += ((FieldAssignmentImpl) r).record.getType().length()- r.getType().length() - ((FieldAssignmentImpl) r).inOffset();
			r = ((FieldAssignmentImpl) r).record;
		}
		VariableDeclaration recGlobal = ((VariableAssignmentImpl)r).declaration;
		Register reg = recGlobal.getRegister();
		off += recGlobal.getOffset();

		fragment.add(_factory.createLoadA(reg, off));

		return fragment;
	}
	
}
