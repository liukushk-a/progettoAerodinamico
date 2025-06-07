// Simcenter STAR-CCM+ macro: M13_mesh_custom_controls.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.resurfacer.*;
import star.meshing.*;

public class M13_mesh_custom_controls extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      ((AutoMeshOperation2d) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh (2D)"));

    SurfaceCustomMeshControl surfaceCustomMeshControl_1 = 
      autoMeshOperation2d_0.getCustomMeshControls().createSurfaceControl();

    surfaceCustomMeshControl_1.getGeometryObjects().setQuery(null);

    MeshOperationPart meshOperationPart_0 = 
      ((MeshOperationPart) simulation_0.get(SimulationPartManager.class).getPart("Subtract"));

    PartSurface partSurface_0 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Domain.inlet"));

    PartSurface partSurface_1 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Domain.outlet"));

    surfaceCustomMeshControl_1.getGeometryObjects().setObjects(partSurface_0, partSurface_1);

    surfaceCustomMeshControl_1.getCustomConditions().get(PartsTargetSurfaceSizeOption.class).setSelected(PartsTargetSurfaceSizeOption.Type.CUSTOM);

    PartsTargetSurfaceSize partsTargetSurfaceSize_0 = 
      surfaceCustomMeshControl_1.getCustomValues().get(PartsTargetSurfaceSize.class);

    partsTargetSurfaceSize_0.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    ((ScalarPhysicalQuantity) partsTargetSurfaceSize_0.getAbsoluteSizeValue()).setValueAndUnits(0.1, units_2);

    VolumeCustomMeshControl volumeCustomMeshControl_0 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_0.getGeometryObjects().setQuery(null);

    SimpleCylinderPart simpleCylinderPart_0 = 
      ((SimpleCylinderPart) simulation_0.get(SimulationPartManager.class).getPart("Cylinder"));

    volumeCustomMeshControl_0.getGeometryObjects().setObjects(simpleCylinderPart_0);

    VolumeControlResurfacerSizeOption volumeControlResurfacerSizeOption_0 = 
      volumeCustomMeshControl_0.getCustomConditions().get(VolumeControlResurfacerSizeOption.class);

    volumeControlResurfacerSizeOption_0.setVolumeControlBaseSizeOption(true);

    VolumeControlSize volumeControlSize_0 = 
      volumeCustomMeshControl_0.getCustomValues().get(VolumeControlSize.class);

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject(""));

    volumeControlSize_0.getRelativeSizeScalar().setValueAndUnits(40.0, units_1);

    VolumeCustomMeshControl volumeCustomMeshControl_1 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_1.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_0 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Block"));

    volumeCustomMeshControl_1.getGeometryObjects().setObjects(simpleBlockPart_0);

    VolumeControlResurfacerSizeOption volumeControlResurfacerSizeOption_1 = 
      volumeCustomMeshControl_1.getCustomConditions().get(VolumeControlResurfacerSizeOption.class);

    volumeControlResurfacerSizeOption_1.setVolumeControlBaseSizeOption(true);

    VolumeControlSize volumeControlSize_1 = 
      volumeCustomMeshControl_1.getCustomValues().get(VolumeControlSize.class);

    volumeControlSize_1.getRelativeSizeScalar().setValueAndUnits(500.0, units_1);

    VolumeCustomMeshControl volumeCustomMeshControl_2 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_2.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_1 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Block 2"));

    volumeCustomMeshControl_2.getGeometryObjects().setObjects(simpleBlockPart_1);

    VolumeControlResurfacerSizeOption volumeControlResurfacerSizeOption_2 = 
      volumeCustomMeshControl_2.getCustomConditions().get(VolumeControlResurfacerSizeOption.class);

    volumeControlResurfacerSizeOption_2.setVolumeControlBaseSizeOption(true);

    VolumeControlSize volumeControlSize_2 = 
      volumeCustomMeshControl_2.getCustomValues().get(VolumeControlSize.class);

    volumeControlSize_2.getRelativeSizeScalar().setValueAndUnits(350.0, units_1);

    VolumeCustomMeshControl volumeCustomMeshControl_3 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_3.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_2 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Block 3"));

    volumeCustomMeshControl_3.getGeometryObjects().setObjects(simpleBlockPart_2);

    VolumeControlResurfacerSizeOption volumeControlResurfacerSizeOption_3 = 
      volumeCustomMeshControl_3.getCustomConditions().get(VolumeControlResurfacerSizeOption.class);

    volumeControlResurfacerSizeOption_3.setVolumeControlBaseSizeOption(true);

    VolumeControlSize volumeControlSize_3 = 
      volumeCustomMeshControl_3.getCustomValues().get(VolumeControlSize.class);

    volumeControlSize_3.getRelativeSizeScalar().setValueAndUnits(200.0, units_1);
  }
}
