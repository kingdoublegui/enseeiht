package fr.n7.stl.block.ast.impl;

import fr.n7.stl.block.ast.ArrayAllocation;
import fr.n7.stl.block.ast.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Created by thibault on 28/03/17.
 */
public class ArrayAllocationImpl implements ArrayAllocation {
    /**
     * Synthesized Semantics attribute to compute the type of an expression.
     *
     * @return Synthesized Type of the expression.
     */
    @Override
    public Type getType() {
        return null;
    }

    /**
     * Inherited Semantics attribute to build the nodes of the abstract syntax tree for the generated TAM code.
     * Synthesized Semantics attribute that provide the generated TAM code.
     *
     * @param _factory Inherited Factory to build AST nodes for TAM code.
     * @return Synthesized AST for the generated TAM code.
     */
    @Override
    public Fragment getCode(TAMFactory _factory) {
        return null;
    }
}
