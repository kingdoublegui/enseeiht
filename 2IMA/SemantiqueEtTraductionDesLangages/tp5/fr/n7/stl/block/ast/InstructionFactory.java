/**
 * 
 */
package fr.n7.stl.block.ast;

import java.lang.Iterable;

/**
 * Factory to create Abstract Syntax Tree nodes for common instructions in programming languages.
 * @author Marc Pantel
 *
 */
public interface InstructionFactory {
	
	/**
	 * @return A Block node in the Abstract Syntax Tree.
	 * To be used to create the root of the instruction part in an Abstract Syntax Tree.
	 * Then the branches corresponding to the instruction in the block will be added using the add method
	 * from the Block interface.
	 */
	public Block createBlock();
	 
	/**
	 * @param _content Abstract Syntax Tree of the instructions contained in the block.
	 * @return A Block node in the Abstract Syntax Tree.
	 * To be used when the Abstract Syntax Tree of the branches are already available.
	 */
	public Block createBlock(Iterable<Instruction> _content);
	
	/**
	 * @param _context Abstract Syntax Tree of the containing block.
	 * @return A Block node in the Abstract Syntax Tree.
	 */
	public Block createBlock(Block _context);
	
	/**
	 * @param _context Abstract Syntax Tree of the containing block.
	 * @param _content Abstract Syntax Tree of the instructions contained in the block.
	 * @return A Block node in the Abstract Syntax Tree.
	 */
	public Block createBlock(Block _context, Iterable<Instruction> _content);
	
	/**
	 * @param _name Name of the declared constant.
	 * @param _type Abstract Syntax Tree for the type of the declared constant.
	 * @param _value Abstract Syntax Tree for the initial value of the declared constant.
	 * @return A Constant Declaration node in the Abstract Syntax Tree.
	 */
	public ConstantDeclaration createConstantDeclaration(String _name, Type _type, Expression _value);
	
	/**
	 * @param _name Name of the declared variable.
	 * @param _type Abstract Syntax Tree for the type of the declared variable.
	 * @param _value Abstract Syntax Tree for the initial value of the declared variable.
	 * @return A Variable Declaration node in the Abstract Syntax Tree.
	 */
	public VariableDeclaration createVariableDeclaration(String _name, Type _type, Expression _value);
	
	/**
	 * @param _name Name of the declared type.
	 * @param _type Abstract Syntax Tree for the type associated to the name.
	 * @return A Type Declaration node in the Abstract Syntax Tree.
	 */
	public TypeDeclaration createTypeDeclaration( String _name, Type _type);

	/**
	 * @param _declaration Variable Declaration node in the Abstract Syntax Tree corresponding to the assigned variable.
	 * @param _value Abstract Syntax Tree for the expression whose value is assigned to the variable.
	 * @return An Assignment node in the Abstract Syntax Tree.
	 */
	public Instruction createAssignment(VariableDeclaration _declaration, Expression _value);
	
	/**
	 * @param _name Name of the assigned variable. 
	 * @param _value Abstract Syntax Tree for the expression whose value is assigned to the variable.
	 * @return An Assignment node in the Abstract Syntax Tree.
	 */
	public Instruction createAssignment(String _name, Expression _value);
	
	/**
	 * @param _condition Expression node in the Abstract Syntax Tree whose value is used 
	 *                   as condition in the evaluation of the conditional when the Conditional node is executed.
	 *                   This node is assigned to the Condition branch of the conditional node.
	 * @param _then Instruction node in the Abstract Syntax Tree that is executed if the condition value is true.
	 *              This node is assigned to the Then branch of the Conditional node.
	 * @param _else Instruction node in the Abstract Syntax Tree that is executed if the condition value is false.
	 *              This node is assigned to the Else branch of the Conditional node.
	 * @return A Conditional node in the Abstract Syntax Tree with both Then and Else branches.
	 */
	public Instruction createConditional(Expression _condition, Block _then, Block _else);
	
	/**
	 * @param _condition Expression node in the Abstract Syntax Tree whose value is used 
	 *                   as condition in the evaluation of the conditional when the Conditional node is executed.
	 *                   This node is assigned to the Condition branch of the Conditional node.
	 * @param _then Instruction node in the Abstract Syntax Tree that is evaluated if the condition value is true.
	 *              This node is assigned to the Then branch of the conditional node.
	 * @return A Conditional node in the Abstract Syntax Tree with only a Then branch.
	 */
	public Instruction createConditional(Expression _condition, Block _then);
	
	/**
	 * @param _condition Expression node in the Abstract Syntax Tree whose value is used 
	 *                   as condition in the evaluation of the repetition when the Repetition node is executed.
	 *                   This node is assigned to the Condition branch of the Repetition node.
	 * @param _body Instruction node in the Abstract Syntax Tree that is evaluated if the condition value is true
	 *              before repeating the repetition.
	 * @return A Repetition node in the Abstract Syntax Tree.
	 */
	public Instruction createRepetition(Expression _condition, Block _body);
	
	/**
	 * @param _value Expression node in the Abstract Syntax Tree whose value is printed when the Printer node is executed.
	 *               This node is assigned to the Value branch of the Printer node.
	 * @return A Printer node in the Abstract Syntax Tree.
	 */
	public Instruction createPrinter(Expression _value);

}
