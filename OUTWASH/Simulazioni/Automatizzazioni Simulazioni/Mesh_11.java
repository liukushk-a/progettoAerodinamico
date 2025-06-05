// Simcenter STAR-CCM+ macro: Mesh_11.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.prismmesher.*;
import star.meshing.*;

public class Mesh_11 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    MeshOperationPart meshOperationPart_0 = 
      ((MeshOperationPart) simulation_0.get(SimulationPartManager.class).getPart("Subtract"));

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      simulation_0.get(MeshOperationManager.class).createAutoMeshOperation2d(new StringVector(new String[] {"star.twodmesher.DualAutoMesher2d", "star.prismmesher.PrismAutoMesher"}), new NeoObjectVector(new Object[] {meshOperationPart_0}));

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject("mm"));

    autoMeshOperation2d_0.getDefaultValues().get(BaseSize.class).setValueAndUnits(5.0, units_2);

    SurfaceCurvature surfaceCurvature_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(SurfaceCurvature.class);

    surfaceCurvature_0.setNumPointsAroundCircle(100.0);

    NumPrismLayers numPrismLayers_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(NumPrismLayers.class);

    IntegerValue integerValue_0 = 
      numPrismLayers_0.getNumLayersValue();

    integerValue_0.getQuantity().setValue(5.0);

    PrismThickness prismThickness_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(PrismThickness.class);

    Units units_3 = 
      ((Units) simulation_0.getUnitsManager().getObject(""));

    prismThickness_0.getRelativeSizeScalar().setValueAndUnits(70.0, units_3);

    SurfaceCustomMeshControl surfaceCustomMeshControl_0 = 
      autoMeshOperation2d_0.getCustomMeshControls().createSurfaceControl();

    surfaceCustomMeshControl_0.getGeometryObjects().setQuery(null);

    PartSurface partSurface_0 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Flap1.Default"));

    PartSurface partSurface_1 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Flap2.Default"));

    surfaceCustomMeshControl_0.getGeometryObjects().setObjects(partSurface_0, partSurface_1);

    PartsRemesherTetPolyWakeRefinementOption partsRemesherTetPolyWakeRefinementOption_0 = 
      surfaceCustomMeshControl_0.getCustomConditions().get(PartsRemesherTetPolyWakeRefinementOption.class);

    partsRemesherTetPolyWakeRefinementOption_0.setPartsWakeRefinementOption(true);

    PartsWakeRefinementSize partsWakeRefinementSize_0 = 
      surfaceCustomMeshControl_0.getCustomValues().get(PartsWakeRefinementValuesManager.class).getIsotropicSize();

    partsWakeRefinementSize_0.getRelativeSizeScalar().setValueAndUnits(80.0, units_3);
  }
}
