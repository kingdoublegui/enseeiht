/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ConstantDeclaration;
import fr.n7.stl.block.ast.Expression;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author pantel2
 *
 */
public class ConstantDeclarationImpl implements ConstantDeclaration {

	private String name;
	private Type type;
	private Expression value;

	public ConstantDeclarationImpl(String _name, Type _type, Expression _value) {
		this.name = _name;
		this.type = _type;
		this.value = _value;
	}
	
	public String toString() {
		return "const " + this.type + " " + this.name + " = " + this.value + ";\n";
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Declaration#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ConstantDeclaration#getType()
	 */
	@Override
	public Type getType() {
		return this.type;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ConstantDeclaration#getValue()
	 */
	@Override
	public Expression getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.ConstantDeclaration#getCode()
	 */
	@Override
	public Fragment getCode() {
		// TODO Auto-generated method stub
		return null;
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
