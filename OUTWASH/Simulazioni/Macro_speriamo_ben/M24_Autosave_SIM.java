// Simcenter STAR-CCM+ macro: M24_Autosave_SIM.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;

public class M24_Autosave_SIM extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoSave autoSave_0 = 
      simulation_0.getSimulationIterator().getAutoSave();

    StarUpdate starUpdate_0 = 
      autoSave_0.getStarUpdate();

    starUpdate_0.setEnabled(true);

    IterationUpdateFrequency iterationUpdateFrequency_11 = 
      starUpdate_0.getIterationUpdateFrequency();

    iterationUpdateFrequency_11.setIterations(250);

    StepStoppingCriterion stepStoppingCriterion_0 = 
      ((StepStoppingCriterion) simulation_0.getSolverStoppingCriterionManager().getSolverStoppingCriterion("Maximum Steps"));

    IntegerValue integerValue_0 = 
      stepStoppingCriterion_0.getMaximumNumberStepsObject();

    integerValue_0.getQuantity().setValue(3000);
  }
}
