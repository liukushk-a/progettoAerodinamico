// Simcenter STAR-CCM+ macro: M12_mesh_default_controls.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.resurfacer.*;
import star.prismmesher.*;
import star.meshing.*;

public class M12_mesh_default_controls extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      ((AutoMeshOperation2d) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh (2D)"));

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("cm"));

    autoMeshOperation2d_0.getDefaultValues().get(BaseSize.class).setValueAndUnits(0.7, units_0);

    SurfaceCurvature surfaceCurvature_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(SurfaceCurvature.class);

    surfaceCurvature_0.setNumPointsAroundCircle(100.0);

    SurfaceGrowthRate surfaceGrowthRate_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(SurfaceGrowthRate.class);

    surfaceGrowthRate_0.setGrowthRateOption(SurfaceGrowthRate.GrowthRateOption.USER_SPECIFIED);

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject(""));

    surfaceGrowthRate_0.getGrowthRateScalar().setValueAndUnits(1.1, units_1);

    NumPrismLayers numPrismLayers_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(NumPrismLayers.class);

    IntegerValue integerValue_0 = 
      numPrismLayers_0.getNumLayersValue();

    integerValue_0.getQuantity().setValue(8.0);

    PrismThickness prismThickness_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(PrismThickness.class);

    prismThickness_0.getRelativeSizeScalar().setValueAndUnits(100.0, units_1);
  }
}
