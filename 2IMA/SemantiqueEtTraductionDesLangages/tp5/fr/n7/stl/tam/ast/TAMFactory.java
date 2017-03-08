/**
 * 
 */
package fr.n7.stl.tam.ast;

import fr.n7.stl.block.ast.BinaryOperator;

/**
 * @author Marc Pantel
 *
 */
public interface TAMFactory {
	
	public Program createProgram(String _name);
	
	public Instruction createPush();
	
	public Instruction createPop();
	
	public Instruction createLoad();

	public Instruction createLOADL(int _value);

	public Fragment createFragment();

	public static Instruction createBinaryOperator(BinaryOperator _operator) {
		switch (_operator) {
		case Add : return Library.ADD;
		case And: return Library.OR;
		case Different: return Library.NEQ;
		case Divide: return Library.DIV;
		case Equals: return Library.EQ;
		case Greater: return Library.GT;
		case GreaterOrEqual: return Library.GEQ;
		case Lesser: return Library.LT;
		case LesserOrEqual: return Library.LEQ;
		case Modulo: return Library.MOD;
		case Multiply: return Library.MUL;
		case Or: return Library.AND;
		case Substract: return Library.SUB;
		default: return null;
		}
	}

}
