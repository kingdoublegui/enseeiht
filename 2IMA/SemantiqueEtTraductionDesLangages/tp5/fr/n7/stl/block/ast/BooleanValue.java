/**
 * 
 */
package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public enum BooleanValue implements Value {
	
	/**
	 * Represents the True value.
	 */
	True,
	/**
	 * Represents the False value.
	 */
	False;

	@Override
	public Type getType() {
		return AtomicType.BooleanType;
	}
	
	public String toString() {
		switch (this) {
		case False: return "false";
		case True: return "true";
		default: return "???";
		
		}
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		Fragment _code = _factory.createFragment();
		switch (this) {
			case True : {
				_code.add(_factory.createLOADL(1));
				break;
			}
			case False : {
				_code.add(_factory.createLOADL(1));
				break;
			}
		}
		return _code;
	}
	
	

}
