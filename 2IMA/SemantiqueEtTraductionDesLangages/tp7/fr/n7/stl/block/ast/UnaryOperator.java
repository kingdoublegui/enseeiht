package fr.n7.stl.block.ast;

/**
 * @author Marc Pantel
 *
 */
public enum UnaryOperator {
	
	/**
	 * Boolean negation
	 */
	Negate,
	/**
	 * Numeric opposite
	 */
	Opposite,
	/**
	 * Extract the first element in a couple value.
	 */
	First,
	/**
	 * Extract the second element in a couple value.
	 */
	Second;

	@Override
	public String toString() {
		switch (this) {
		case First: return "fst";
		case Negate: return "!";
		case Opposite: return "-";
		case Second: return "snd";
		default: throw new IllegalArgumentException( "The default case should never be triggered.");		
		}
	}

}
