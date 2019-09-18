package org.eqasim.san_francisco.mode_choice.utilities.variables;

import org.eqasim.core.simulation.mode_choice.utilities.variables.BaseVariables;

public class SanFranciscoPersonVariables implements BaseVariables {
	public final boolean hasSubscription;

	public SanFranciscoPersonVariables(boolean hasSubscription) {
		this.hasSubscription = hasSubscription;
	}
}
