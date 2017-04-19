package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface TypeDeclaration extends Instruction, Declaration {
	
	/**
	 * Provide the type associated to a name in a type declaration.
	 * @return Type from the declaration.
	 */
    Type getType();

}
