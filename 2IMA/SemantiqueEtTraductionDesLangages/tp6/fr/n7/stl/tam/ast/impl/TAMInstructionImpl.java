/**
 * 
 */
package fr.n7.stl.tam.ast.impl;

import java.util.Optional;

import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMInstruction;

/**
 * Abstract class that gathers the common attributes for all TAM instructions.
 * @author Marc Pantel
 *
 */
class TAMInstructionImpl implements TAMInstruction {

	/**
	 * Each TAM instruction has a unique kind, i.e. the name of the instruction.
	 */
	private TAMInstructionKind kind;
	
	/**
	 * Each TAM instruction can have a label using for computing locations to that instruction 
	 * for JUMP and CALL instructions.
	 */
	protected Optional<String> label;
	
	/**
	 * Some TAM instructions manipulate explicitly the memory (LOAD and STORE instructions).
	 * This is the register of the manipulated memory.
	 */
	protected Optional<Register> register;

	/**
	 * Some TAM instructions manipulate explicitly the memory (LOAD and STORE instructions).
	 * This is the offset of the register of the manipulated memory.
	 */
	protected Optional<Integer> offset;

	/**
	 * Some TAM instructions use labels that are resolved by the TAM assembler tool (JUMP,
	 * JUMPIF, CALL).
	 * This is the target label for these instructions.
	 */	
	protected Optional<String> target;

	/**
	 * Some TAM instructions manipulate explicitly the memory (LOAD and STORE instructions).
	 * This is the size the manipulated memory chunk.
	 * It is also used by other instructions like PUSH and POP that manipulates the stack.
	 */
	protected Optional<Integer> size;



	/**
	 * Construction for a full TAM instruction with kind, label, location and size.
	 * @param _kind Kind for the TAM instruction.
	 * @param _label Optional Label for the TAM instruction.
	 * @param _location Optional Register for the TAM instruction.
	 * @param _offset Optional Integer offset for the TAM instruction.
	 * @param _target Optional Label target for the TAM instruction.
	 * @param _size Optional Integer size for the TAM instruction.
	 */
	public TAMInstructionImpl(TAMInstructionKind _kind, Optional<String> _label, 
			Optional<Register> _register, Optional<Integer> _offset, 
			Optional<String> _target, Optional<Integer> _size) {
		this.kind = _kind;
		this.label = _label;
		this.register = _register;
		this.offset = _offset;
		this.target = _target;
		this.size = _size;
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.TAMInstruction#add(fr.n7.stl.tam.ast.Label)
	 */
	@Override
	public void set(String _label) {
		this.label = Optional.of(_label);
	}
	
	public String toString() {
		return ((this.label.isPresent())?(this.label.get() + " : "):"") 
				+ this.kind
				+ ((this.size.isPresent())?(" (" + this.size.get() + ")"):"")
				+ ((this.offset.isPresent())?(" " + this.offset.get()):"")
				+ ((this.register.isPresent())?("[" + this.register.get() + "]"):"");
	}

}
