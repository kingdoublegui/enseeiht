package fr.n7.stl.block.ast;

import fr.n7.stl.tam.ast.Register;

/**
 * @author Marc Pantel
 *
 */
public interface VariableDeclaration extends Declaration, Instruction {

	/**
	 * Synthesized semantics attribute for the type of the declared variable.
	 * @return Type of the declared variable.
	 */
    Type getType();
	
	/**
	 * Synthesized semantics attribute for the register used to compute the address of the variable.
	 * @return Register used to compute the address where the declared variable will be stored.
	 */
    Register getRegister();
	
	/**
	 * Synthesized semantics attribute for the offset used to compute the address of the variable.
	 * @return Offset used to compute the address where the declared variable will be stored.
	 */
    int getOffset();

}
