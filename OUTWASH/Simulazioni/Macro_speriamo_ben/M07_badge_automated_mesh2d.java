// Simcenter STAR-CCM+ macro: M07_badge_automated_mesh2d.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class M07_badge_automated_mesh2d extends StarMacro {

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

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      simulation_0.get(MeshOperationManager.class).createAutoMeshOperation2d(new StringVector(new String[] {"star.twodmesher.DualAutoMesher2d", "star.prismmesher.PrismAutoMesher"}), new NeoObjectVector(new Object[] {meshOperationPart_0}));
  }
}
