/**
 * 
 */
package fr.n7.stl.ast;

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
	
	

}
