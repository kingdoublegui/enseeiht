/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.IntegerValue;
import fr.n7.stl.ast.Type;

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
	public Type getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "IntegerValueImpl [text=" + text + "]";
	}

}
