/**
 * 
 */
package fr.n7.stl.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import fr.n7.stl.block.ast.Declaration;
import fr.n7.stl.block.ast.ForbiddenDeclarationException;
import fr.n7.stl.block.ast.HierarchicalScope;

/**
 * @author pantel2
 *
 */
public class SymbolTable implements HierarchicalScope<Declaration> {
	
	private Map<String, Declaration> declarations;
	private Optional<SymbolTable> context;

	public SymbolTable() {
		this.declarations = new HashMap<String,Declaration>();
		this.context = Optional.empty();
	}
	
	public SymbolTable(SymbolTable _context) {
		this();
		if (_context != null) {
			this.context = Optional.of(_context);
		}
	}

	@Override
	public Optional<Declaration> get(String _name) {
		if (this.declarations.containsKey(_name)) {
			return Optional.of(this.declarations.get(_name));
		} else {
			if (this.context.isPresent()) {
				return this.context.get().get(_name);
			} else {
				return Optional.empty();
			}
		}
	}

	@Override
	public boolean contains(String _name) {
		// System.out.println("Contains( " + _name + " )");
		return (this.declarations.containsKey(_name));
	}

	@Override
	public boolean accepts(Declaration _declaration) {
		// System.out.println("Accepts( " + _declaration.getName() + " )");
		return (! this.contains(_declaration.getName()));
	}

	@Override
	public void register(Declaration _declaration) throws ForbiddenDeclarationException {
		// System.out.println("Register( " + _declaration.getName() + " )");
		if (this.accepts(_declaration)) {
			this.declarations.put(_declaration.getName(), _declaration);
		} else {
			throw new ForbiddenDeclarationException();
		}
	}

	@Override
	public boolean knows(String _name) {
		// System.out.println("Knows( " + _name + " )");
		if (this.contains(_name)) {
			return true;
		} else {
			if (this.context.isPresent()) {
				return this.context.get().knows(_name);
			} else {
				return false;
			}
		}
	}
	
	@Override
	public String toString() {
		String _local = "";
		if (this.context.isPresent()) {
			_local += "Hierarchical definitions :\n" + this.context.get().toString();
		}
		_local += "Local definitions : ";
		for (Entry<String,Declaration> _entry : this.declarations.entrySet()) {
			_local += _entry.getKey() + " -> " + _entry.getValue().toString() + "\n";
		}
		return _local;
	}

}
