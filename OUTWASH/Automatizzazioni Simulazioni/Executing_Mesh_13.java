// Simcenter STAR-CCM+ macro: Executing_Mesh_13.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class Executing_Mesh_13 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoSave autoSave_0 = 
      simulation_0.getSimulationIterator().getAutoSave();

    autoSave_0.setAutoSaveMesh(true);

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      ((AutoMeshOperation2d) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh (2D)"));

    autoMeshOperation2d_0.execute();
  }
}
