/**
 * 
 */
package fr.n7.stl.tam.ast.impl;

import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMInstruction;

/**
 * Implementation of a TAM fragment using a linked list of TAM Instruction.
 * @author Marc Pantel
 *
 */
public class FragmentImpl implements Fragment {

	/**
	 * The sequence of TAM Instruction in the Fragment.
	 */
	private List<TAMInstruction> instructions;
	
	/**
	 * Create an empty fragment.
	 */
	public FragmentImpl() {
		this.instructions = new LinkedList<TAMInstruction>();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.Fragment#add(fr.n7.stl.tam.ast.TAMInstruction)
	 */
	@Override
	public void add(TAMInstruction _instruction) {
		this.instructions.add(_instruction);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.Fragment#append(fr.n7.stl.tam.ast.Fragment)
	 */
	@Override
	public void append(Fragment _fragment) {
		this.instructions.addAll(((FragmentImpl)_fragment).instructions);
	}
	
	public String toString() {
		String _result = "";
		for (TAMInstruction _instruction : this.instructions) {
			_result += _instruction + "\n";
		}
		return _result;
	}

	@Override
	public void set(String _label) {
		if (this.instructions.size() > 0) { 
			this.instructions.get(0).set(_label);
		} else {
			throw new IllegalArgumentException( "Fragment size is 0. No label can be set.");
		}
	}

}
