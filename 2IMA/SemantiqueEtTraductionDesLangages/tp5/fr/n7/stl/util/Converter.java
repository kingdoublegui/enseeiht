/**
 * 
 */
package fr.n7.stl.util;

import java.util.Optional;

/**
 * @author Marc Pantel
 *
 */
public class Converter  {
	
	public static <T,S extends T> Optional<S> to(Optional<T> _value) {
		try {
			if (_value.isPresent()) {
				return Optional.of((S) _value.get());
			} else {
				return Optional.empty();
			}
		} 
		catch (ClassCastException _e){
			return Optional.empty();
		}
	}

}
