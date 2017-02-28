/**
 * 
 */
package fr.n7.stl.ast;

/**
 * @author Marc Pantel
 *
 */
public interface Type {
	
	public boolean equalsTo(Type _other);
	
	public boolean compatibleWith(Type _other);
	
	public Type merge(Type _other);

}
