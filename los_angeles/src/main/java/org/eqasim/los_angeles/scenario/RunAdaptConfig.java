package org.eqasim.los_angeles.scenario;

import org.eqasim.core.components.config.ConfigAdapter;
import org.eqasim.core.components.config.EqasimConfigGroup;
import org.eqasim.core.simulation.EqasimConfigurator;
import org.eqasim.los_angeles.mode_choice.LosAngelesModeChoiceModule;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.core.config.CommandLine.ConfigurationException;
import org.matsim.core.config.Config;

import ch.ethz.matsim.discrete_mode_choice.modules.config.DiscreteModeChoiceConfigGroup;

public class RunAdaptConfig {
	static public void main(String[] args) throws ConfigurationException {
		ConfigAdapter.run(args, EqasimConfigurator.getConfigGroups(), RunAdaptConfig::adaptConfiguration);
	}

	static public void adaptConfiguration(Config config) {
		// Ignore some input files
		config.transit().setVehiclesFile(null);
		config.households().setInputFile(null);

		// Set up mode choice
		EqasimConfigGroup eqasimConfig = EqasimConfigGroup.get(config);

		eqasimConfig.setCostModel(TransportMode.car, LosAngelesModeChoiceModule.CAR_COST_MODEL_NAME);
		eqasimConfig.setCostModel(TransportMode.pt, LosAngelesModeChoiceModule.PT_COST_MODEL_NAME);

		DiscreteModeChoiceConfigGroup dmcConfig = (DiscreteModeChoiceConfigGroup) config.getModules()
				.get(DiscreteModeChoiceConfigGroup.GROUP_NAME);

		dmcConfig.setModeAvailability(LosAngelesModeChoiceModule.MODE_AVAILABILITY_NAME);
		dmcConfig.setTourConstraintsAsString("FromTripBased, VehicleTourConstraintWithCarPassenger");
	}
}
