/**
 * 
 */
package fr.n7.stl.tam.ast;

/**
 * A TAM instruction than can be prefixed by a label used to resolve the address of the instruction
 * in the code part of the memory in the TAM virtual machine.
 * @author Marc Pantel
 *
 */
public interface TAMInstruction {
	
	/**
	 * Sets the label in the instruction.
	 * @param _label Label to be assigned to the instruction.
	 */
	public void set(String _label);

}
