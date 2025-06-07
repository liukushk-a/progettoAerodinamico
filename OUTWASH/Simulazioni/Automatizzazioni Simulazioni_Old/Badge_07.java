// Simcenter STAR-CCM+ macro: Badge_07.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class Badge_07 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    MeshOperationPart meshOperationPart_0 = 
      ((MeshOperationPart) simulation_0.get(SimulationPartManager.class).getPart("Subtract"));

    PrepareFor2dOperation prepareFor2dOperation_0 = 
      (PrepareFor2dOperation) simulation_0.get(MeshOperationManager.class).createPrepareFor2dOperation(new NeoObjectVector(new Object[] {meshOperationPart_0}));

    prepareFor2dOperation_0.execute();
  }
}
