// Simcenter STAR-CCM+ macro: M06_subtract.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;
import star.meshing.*;

public class M06_subtract extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    SolidModelPart solidModelPart_0 = 
      ((SolidModelPart) simulation_0.get(SimulationPartManager.class).getPart("Domain"));

    SolidModelPart solidModelPart_1 = 
      ((SolidModelPart) simulation_0.get(SimulationPartManager.class).getPart("Flap1"));

    SolidModelPart solidModelPart_2 = 
      ((SolidModelPart) simulation_0.get(SimulationPartManager.class).getPart("Flap2"));

    SubtractPartsOperation subtractPartsOperation_0 = 
      (SubtractPartsOperation) simulation_0.get(MeshOperationManager.class).createSubtractPartsOperation(new NeoObjectVector(new Object[] {solidModelPart_0, solidModelPart_1, solidModelPart_2}));

    subtractPartsOperation_0.getTargetPartManager().setQuery(null);

    subtractPartsOperation_0.getTargetPartManager().setObjects(solidModelPart_0);

    subtractPartsOperation_0.setPerformCADBoolean(true);

    subtractPartsOperation_0.execute();
  }
}
