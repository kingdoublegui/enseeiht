/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Instruction;
import fr.n7.stl.block.ast.VariableDeclaration;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author pantel2
 *
 */
public class AssignmentImpl implements Instruction {

	private VariableDeclaration declaration;
	private Expression value;
	private String name;

	/**
	 * 
	 */
	public AssignmentImpl(VariableDeclaration _declaration, Expression _value) {
		this.declaration = _declaration;
		this.value = _value;
	}

	public AssignmentImpl(String _name, Expression _value) {
		this.name = _name;
		this.value = _value;
	}

	@Override
	public String toString() {
		return ((this.declaration != null)?this.declaration.getName():this.name) + " = " + this.value.toString() + ";\n";
	}

	@Override
	public boolean checkType() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int allocateMemory(int _offset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		return null;
	}

}
