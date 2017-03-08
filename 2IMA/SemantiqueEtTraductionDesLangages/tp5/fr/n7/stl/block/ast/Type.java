/**
 * 
 */
package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface Type {
	
	/**
	 * Compare the self type with an _other type semantically.
	 * @param _other : Type compared with self.
	 * @return : True if the self type is semantically equal to the _other type, false if not.
	 */
	public boolean equalsTo(Type _other);
	
	/**
	 * Check that the self type is compatible with the _other type, i.e. all values of self can be
	 * used as a value of _other.
	 * @param _other : Type that self is compatible with.
	 * @return : True if the self type is semantically compatible with the _other type, false if not.
	 */
	public boolean compatibleWith(Type _other);
	
	/**
	 * Builds a new type that results from the merging of self and _other according to a subtyping relation.
	 * @param _other : Type that is merged with self to build the returned type.
	 * @return : A type resulting from the merging of the self type with the _other type.
	 */
	public Type merge(Type _other);
	
	/**
	 * Compute the size in bites needed to store a value of the _self type with an alignment on _padding bites.
	 * @param _padding
	 * @return
	 */
	public int length(int _padding);

}
