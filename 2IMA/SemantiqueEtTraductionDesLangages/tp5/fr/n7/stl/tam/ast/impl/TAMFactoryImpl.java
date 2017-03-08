/**
 * 
 */
package fr.n7.stl.tam.ast.impl;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Instruction;
import fr.n7.stl.tam.ast.Program;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * @author Marc Pantel
 *
 */
public class TAMFactoryImpl implements TAMFactory {

	@Override
	public Program createProgram(String _name) {
		return new ProgramImpl(_name);
	}

	@Override
	public Instruction createPush() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instruction createPop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instruction createLoad() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Instruction createLOADL(int _value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Fragment createFragment() {
		// TODO Auto-generated method stub
		return null;
	}

}
