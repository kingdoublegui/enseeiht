/**
 * 
 */
package fr.n7.stl.tam.ast.impl;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Instruction;
import fr.n7.stl.tam.ast.Program;

/**
 * @author Marc Pantel
 *
 */
public class ProgramImpl implements Program {

	private Fragment body;
	private String name;
	
	public ProgramImpl(String _name) {
		this.name = _name;
		this.body = new FragmentImpl();
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.Program#add(fr.n7.stl.tam.ast.Instruction)
	 */
	@Override
	public void add(Instruction _instruction) {
		this.body.add(_instruction);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.tam.ast.Program#add(fr.n7.stl.tam.ast.Fragment)
	 */
	@Override
	public void add(Fragment _fragment) {
		this.body.append(_fragment);
	}

}
