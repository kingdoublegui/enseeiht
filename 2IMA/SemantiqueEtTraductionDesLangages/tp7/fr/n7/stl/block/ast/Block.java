package fr.n7.stl.block.ast;

import java.util.Iterator;

import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Represents a Block node in the Abstract Syntax Tree node for the Bloc language.
 * Declares the various semantics attributes for the node.
 * 
 * A block contains declarations. It is thus a Scope even if a separate SymbolTable is used in
 * the attributed semantics in order to manage declarations.
 * @author Marc Pantel
 *
 */
public interface Block { 
	// <REMOVE>
	// extends HierarchicalScope<Declaration> {
	// </REMOVE>
	
	/**
	 * Add an instruction at then end of a block.
	 * @param _instruction Instruction node in the AST added to the block node.
	 */
    void add(Instruction _instruction);
	
	/**
	 * Add a sequence of instruction in a block.
	 * @param _instructions Sequence of instruction nodes in the AST added to the block node.
	 */
    void addAll(Iterable<Instruction> _instructions);
	
	/**
	 * Synthesized Semantics attribute to check that an instruction if well typed.
	 * @return Synthesized True if the instruction is well typed, False if not.
	 */
    boolean checkType();
	
	/**
	 * Inherited Semantics attribute to allocate memory for the variables declared in the instruction.
	 * Synthesized Semantics attribute that compute the size of the allocated memory. 
	 * @param _register Inherited Register associated to the address of the variables.
	 * @param _offset Inherited Current offset for the address of the variables.
	 * @return Synthesized Size of the memory allocated to the variables.
	 */
    int allocateMemory(Register _register, int _offset);
	
	/**
	 * Inherited Semantics attribute to build the nodes of the abstract syntax tree for the generated TAM code.
	 * Synthesized Semantics attribute that provide the generated TAM code.
	 * @param _factory Inherited Factory to build AST nodes for TAM code.
	 * @return Synthesized AST for the generated TAM code.
	 */
    Fragment getCode(TAMFactory _factory);

}
