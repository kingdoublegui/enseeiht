/**
 * 
 */
package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.IntegerValue;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author pantel2
 *
 */
public class IntegerValueImpl implements IntegerValue {

	private String text;
	
	/**
	 * 
	 */
	public IntegerValueImpl(String _text) {
		// TODO Auto-generated constructor stub
		text = _text;
	}

	@Override
	public String toString() {
		return this.text;
	}

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
