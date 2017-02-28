/**
 * 
 */
package fr.n7.stl.ast.impl;

import fr.n7.stl.ast.Expression;
import fr.n7.stl.ast.Instruction;

/**
 * @author pantel2
 *
 */
public class PrinterImpl implements Instruction {

	private Expression parameter;

	public PrinterImpl(Expression _value) {
		this.parameter = _value;
	}

	@Override
	public String toString() {
		return "PrinterImpl [parameter=" + parameter + "]";
	}

}
