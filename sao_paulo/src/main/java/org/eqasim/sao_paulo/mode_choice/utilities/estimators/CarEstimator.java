package org.eqasim.sao_paulo.mode_choice.utilities.estimators;

import org.eqasim.sao_paulo.mode_choice.parameters.ModeChoiceParameters;
import org.eqasim.sao_paulo.mode_choice.utilities.variables.CarVariables;
import org.eqasim.sao_paulo.mode_choice.utilities.variables.PersonVariables;

public class CarEstimator {
	private final ModeChoiceParameters parameters;

	public CarEstimator(ModeChoiceParameters parameters) {
		this.parameters = parameters;
	}

	public double estimateUtility(PersonVariables personVariables, CarVariables variables) {
		double utility = 0.0;

		utility += parameters.car.alpha;
		utility += parameters.car.betaTravelTime * variables.travelTime_min;
		utility += parameters.walk.betaTravelTime * variables.accessEgressTime_min;

		utility += parameters.betaCost //
				* Math.pow(Math.max(variables.crowflyDistance_km, 0.001) / parameters.referenceCrowflyDistance_km,
						parameters.lambdaCostCrowflyDistance) //
				* variables.cost_BRL;

		return utility;
	}
}
