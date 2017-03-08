/**
 * 
 */
package fr.n7.stl.tam.ast;

/**
 * @author pantel2
 *
 */
public interface Fragment {
	
	public void add(Instruction _instruction);
	
	public void append(Fragment _fragment);

}
