/**
 * 
 */
package fr.n7.stl.tam.ast;

/**
 * Sequence of TAMInstruction.
 * @author Marc Pantel
 *
 */
public interface Fragment {
	
	/**
	 * Adds a TAM instruction at the end of the fragment.
	 * @param _instruction TAM instruction added at the end of the fragment.
	 */
	public void add(TAMInstruction _instruction);
	
	/**
	 * Sets the label of the first TAM instruction of the fragment.
	 * @param _label Label that is set to the first TAM instruction of the fragment.
	 */
	public void set(String _label);
	
	/**
	 * Add the instructions from the provided fragment at the end of the fragment.
	 * @param _fragment Fragment whose instructions are added at the end of the fragment.
	 */
	public void append(Fragment _fragment);

}
