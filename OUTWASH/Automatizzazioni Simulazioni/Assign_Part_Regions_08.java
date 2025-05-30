// Simcenter STAR-CCM+ macro: Assign_Part_Regions_08.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class Assign_Part_Regions_08 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    MeshOperationPart meshOperationPart_0 = 
      ((MeshOperationPart) simulation_0.get(SimulationPartManager.class).getPart("Subtract"));

    simulation_0.getRegionManager().newRegionsFromParts(new NeoObjectVector(new Object[] {meshOperationPart_0}), "OneRegionPerPart", null, "OneBoundaryPerPartSurface", null, "OneFeatureCurvePerPartCurve", null, RegionManager.CreateInterfaceMode.BOUNDARY, "OneEdgeBoundaryPerPart", null);
  }
}
