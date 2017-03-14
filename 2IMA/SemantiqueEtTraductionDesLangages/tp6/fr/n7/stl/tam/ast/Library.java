/**
 * 
 */
package fr.n7.stl.tam.ast;

import java.util.Optional;

/**
 * Atomic operators provided by the TAM virtual machine for basic types : Boolean, Character, Integer, Memory and String.
 * @author Marc Pantel
 *
 */
public enum Library implements TAMInstruction {
	/**
	 * Pops a Boolean value from the stack, and pushes its negation on the stack.
	 */
	BNeg, 
	/**
	 * Pops two Boolean values from the stack, and pushes their disjunction on the stack.
	 */
	BOr, 
	/**
	 * Pops two Boolean values from the stack, and pushes their conjunction on the stack.
	 */
	BAnd, 
	/**
	 * Pops a Boolean value from the stack, and writes its value on the console.
	 */
	BOut, 
	/**
	 * Reads a Boolean value from the console, and pushes it on the stack.
	 */
	BIn, 
	/**
	 * Pops a Boolean value from the stack, converts it to a Character (true is 1, false is 0) 
	 * and pushes that one on the stack.
	 */
	B2C, 
	/**
	 * Pops a Boolean value from the stack, converts it to an Integer (true is 1, false is 0)
	 * and pushes that one on the stack.
	 */
	B2I, 
	/**
	 * Pops a Boolean value from the stack, converts it to a String and pushes that one on the stack.
	 */
	B2S,
	/**
	 * Pops a Character value from the stack, and writes its value on the console.
	 */
	COut, 
	/**
	 * Reads a Character value from the console, and pushes it on the stack.
	 */
	CIn, 
	/**
	 * Pops a Character value from the stack, converts it to a Boolean and pushes that one on the stack.
	 */
	C2B, 
	/**
	 * Pops a Character value from the stack, converts it to an Integer and pushes that one on the stack.
	 */
	C2I, 
	/**
	 * Pops a Character value from the stack, converts it to a String and pushes that one on the stack.
	 */
	C2S,
	/**
	 * Pops an Integer value from the stack, and pushes its opposite on the stack.
	 */
	INeg, 
	/**
	 * Pops two Integer values from the stack, and pushes their sum on the stack.
	 */
	IAdd, 
	/**
	 * Pops two Integer values from the stack, and pushes their subtraction on the stack.
	 */
	ISub, 
	/**
	 * Pops two Integer values from the stack, and pushes their product on the stack.
	 */
	IMul, 
	/**
	 * Pops two Integer values from the stack, and pushes their quotient (Euclidean division) on the stack.
	 */
	IDiv, 
	/**
	 * Pops two Integer values from the stack, and pushes their rest (Euclidean division) on the stack.
	 */
	IMod, 
	/**
	 * Pops two Integer values from the stack, compares them and pushes the true Boolean value
	 * on the stack if they are equal and false if not.
	 */
	IEq, 
	/**
	 * Pops two Integer values from the stack, compares them and pushes the true Boolean value
	 * on the stack if they are different and false if not.
	 */
	INeq, 
	/**
	 * Pops two Integer values from the stack, compares them and pushes the true Boolean value
	 * on the stack if the first on is strictly lesser than the second one and false if not.
	 */
	ILss, 
	/**
	 * Pops two Integer values from the stack, compares them and pushes the true Boolean value
	 * on the stack if the first on is lesser or equal than the second one and false if not.
	 */
	ILeq, 
	/**
	 * Pops two Integer values from the stack, compares them and pushes the true Boolean value
	 * on the stack if the first on is strictly greater than the second one and false if not.
	 */
	IGtr, 
	/**
	 * Pops two Integer values from the stack, compares them and pushes the true Boolean value
	 * on the stack if the first on is greater or equal than the second one and false if not.
	 */
	IGeq, 
	/**
	 * Pops an Integer value from the stack, and writes its value on the console.
	 */
	IOut, 
	/**
	 * Reads an Integer value from the console, and pushes it on the stack.
	 */
	IIn, 
	/**
	 * Pops an Integer value from the stack, converts it to a Boolean (1 is true, 0 is false) 
	 * and pushes that one on the stack.
	 */
	I2B, 
	/**
	 * Pops an Integer value from the stack, converts it to a Boolean (1 is true, 0 is false) 
	 * and pushes that one on the stack.
	 */
	I2C, 
	/**
	 * Pops an Integer value from the stack, converts it to a String and pushes that one on the stack.
	 */
	I2S,
	/**
	 * Pushes the void address on the stack.
	 */
	MVoid, 
	/**
	 * Pops an Integer value from the stack, allocates a memory chunk of that value size in the heap, 
	 * and pushes its address on the stack.
	 */
	MAlloc, 
	/**
	 * Pops an Integer value from the stack that must be an address in the heap and free the associated
	 * memory chunk.
	 */
	MFree, 
	/**
	 * Pops two Integer values from the stack that are addresses of memory chunks of the same size in the heap 
	 * and one Integer value that is the size of these chunk, then it pushes on the stack a Boolean value that
	 * is the result of the comparison of both chunks.
	 */
	MCompare, 
	/**
	 * Pops two Integer values from the stack that are addresses of memory chunks of the same size in the heap 
	 * and one Integer value that is the size of these chunk, then it copies the content of the first chunk in 
	 * the second chunk.
	 */
	MCopy,
	/**
	 * Allocation a String slot in the TAM virtual machine and pushes the slot number on the stack.
	 */
	SAlloc, 
	/**
	 * Pops two Integer values from the stack that are String slots of the same size in the TAM virtual machine 
	 * then it copies the content of the first String in the second one.
	 */
	SCopy, 
	/**
	 * Pops two Integer values from the stack that are String slots in the TAM virtual machine, then it allocates
	 * a new String slot in the TAM virtual machine, copies the content of the two Strings in the new one and 
	 * pushes the slot number of the new one on the stack.
	 */
	SConcat, 
	/**
	 * Pops a String value from the stack, and writes its value on the console.
	 */
	SOut, 
	/**
	 * Reads a String value from the console, and pushes it on the stack.
	 */
	SIn, 
	/**
	 * Pops an Integer value from the stack which is a String slot in the TAM virtual machine, converts the
	 * content of that String to a Boolean and pushes that one on the stack.
	 */
	S2B, 
	/**
	 * Pops an Integer value from the stack which is a String slot in the TAM virtual machine, converts the
	 * content of that String to a Character and pushes that one on the stack.
	 */
	S2C, 
	/**
	 * Pops an Integer value from the stack which is a String slot in the TAM virtual machine, converts the
	 * content of that String to an Integer and pushes that one on the stack.
	 */
	S2I;
	
	/**
	 * Constructor that assigns an empty label for each enum value object.
	 */
	private Library() {
		this.label = Optional.empty(); 
	}
	
	/**
	 * Attribute that store the label of the instruction.
	 */
	private Optional<String> label;
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		if (this.label.isPresent()) {
			return this.label.get() + " SUBR " + this.name();
		} else {
			return "SUBR " + this.name();
		}
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.TAMInstruction#set(fr.n7.stl.tam.ast.Label)
	 */
	@Override
	public void set(String _label) {
		this.label = Optional.of(_label);
	}
}
