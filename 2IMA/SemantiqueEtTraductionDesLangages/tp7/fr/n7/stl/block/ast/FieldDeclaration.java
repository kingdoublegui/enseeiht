package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public interface FieldDeclaration extends Declaration {

	/**
	 * Provide the type of the field in a record.
	 * @return Type of the field.
	 */
    Type getType();

}
