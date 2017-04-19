package fr.n7.stl.block.ast;

/**
 * Abstract Syntax Tree node specification for couple type.
 * @author Marc Pantel
 *
 */
public interface PointerType extends Type {

	/**
	 * Provide the type of the element pointed.
	 * @return Type of the element pointed.
	 */
    Type getPointedType();

}
