/*
 * generated by Xtext 2.10.0
 */
package fr.enseeiht.ui.tests;

import com.google.inject.Injector;
import fr.enseeiht.pdl1.ui.internal.Pdl1Activator;
import org.eclipse.xtext.junit4.IInjectorProvider;

public class PDL1UiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return Pdl1Activator.getInstance().getInjector("fr.enseeiht.PDL1");
	}

}