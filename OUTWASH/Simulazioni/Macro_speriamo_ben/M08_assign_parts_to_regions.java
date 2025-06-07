// Simcenter STAR-CCM+ macro: M08_assign_parts_to_regions.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.meshing.*;

public class M08_assign_parts_to_regions extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Region region_0 = 
      simulation_0.getRegionManager().createEmptyRegion();

    region_0.setPresentationName("Region");

    Boundary boundary_0 = 
      region_0.getBoundaryManager().getBoundary("Default");

    region_0.getBoundaryManager().removeBoundaries(new NeoObjectVector(new Object[] {boundary_0}));

    FeatureCurve featureCurve_0 = 
      ((FeatureCurve) region_0.getFeatureCurveManager().getObject("Default"));

    region_0.getFeatureCurveManager().removeObjects(featureCurve_0);

    FeatureCurve featureCurve_1 = 
      region_0.getFeatureCurveManager().createEmptyFeatureCurveWithName("Feature Curve");

    MeshOperationPart meshOperationPart_0 = 
      ((MeshOperationPart) simulation_0.get(SimulationPartManager.class).getPart("Subtract"));

    simulation_0.getRegionManager().newRegionsFromParts(new NeoObjectVector(new Object[] {meshOperationPart_0}), "OneRegion", region_0, "OneBoundaryPerPartSurface", null, "OneFeatureCurve", featureCurve_1, RegionManager.CreateInterfaceMode.BOUNDARY, "OneEdgeBoundaryPerPart", null);
  }
}
