/**
 * 
 */
package fr.n7.stl.tam.ast.impl;

import java.util.LinkedList;
import java.util.List;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Instruction;

/**
 * @author Marc Pantel
 *
 */
public class FragmentImpl implements Fragment {

	private List<Instruction> instructions;
	
	public FragmentImpl() {
		this.instructions = new LinkedList<Instruction>();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.Fragment#add(fr.n7.stl.tam.ast.Instruction)
	 */
	@Override
	public void add(Instruction _instruction) {
		this.instructions.add(_instruction);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.Fragment#append(fr.n7.stl.tam.ast.Fragment)
	 */
	@Override
	public void append(Fragment _fragment) {
		this.instructions.addAll(((FragmentImpl)_fragment).instructions);
	}

}
