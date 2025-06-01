// Simcenter STAR-CCM+ macro: Mesh_12.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.resurfacer.*;
import star.prismmesher.*;
import star.cadmodeler.*;
import star.meshing.*;

public class Mesh_12 extends StarMacro {

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

    Units units_0 = 
      simulation_0.getUnitsManager().getInternalUnits(new IntVector(new int[] {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject("cm"));

    autoMeshOperation2d_0.getDefaultValues().get(BaseSize.class).setValueAndUnits(0.7, units_1);

    NumPrismLayers numPrismLayers_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(NumPrismLayers.class);

    IntegerValue integerValue_0 = 
      numPrismLayers_0.getNumLayersValue();

    integerValue_0.getQuantity().setValue(5.0);

    PrismThickness prismThickness_0 = 
      autoMeshOperation2d_0.getDefaultValues().get(PrismThickness.class);

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject(""));

    prismThickness_0.getRelativeSizeScalar().setValueAndUnits(70.0, units_2);

    SurfaceCustomMeshControl surfaceCustomMeshControl_0 = 
      autoMeshOperation2d_0.getCustomMeshControls().createSurfaceControl();

    surfaceCustomMeshControl_0.getCustomConditions().get(PartsTargetSurfaceSizeOption.class).setSelected(PartsTargetSurfaceSizeOption.Type.CUSTOM);

    surfaceCustomMeshControl_0.getCustomConditions().get(PartsMinimumSurfaceSizeOption.class).setSelected(PartsMinimumSurfaceSizeOption.Type.CUSTOM);

    SolidModelPart solidModelPart_0 = 
      ((SolidModelPart) simulation_0.get(SimulationPartManager.class).getPart("Flap1"));

    GeometryObjectProxy geometryObjectProxy_0 = 
      meshOperationPart_0.getOrCreateProxyForObject(solidModelPart_0);

    SolidModelPart solidModelPart_1 = 
      ((SolidModelPart) simulation_0.get(SimulationPartManager.class).getPart("Flap2"));

    GeometryObjectProxy geometryObjectProxy_1 = 
      meshOperationPart_0.getOrCreateProxyForObject(solidModelPart_1);

    surfaceCustomMeshControl_0.getGeometryObjects().setQuery(null);

    PartSurface partSurface_0 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Flap1.Default"));

    PartSurface partSurface_1 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Flap2.Default"));

    surfaceCustomMeshControl_0.getGeometryObjects().setObjects(geometryObjectProxy_0, partSurface_0, geometryObjectProxy_1, partSurface_1);

    PartsTargetSurfaceSize partsTargetSurfaceSize_0 = 
      surfaceCustomMeshControl_0.getCustomValues().get(PartsTargetSurfaceSize.class);

    partsTargetSurfaceSize_0.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    surfaceCustomMeshControl_0.getGeometryObjects().setQuery(null);

    PartSurface partSurface_2 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Domain.inlet"));

    PartSurface partSurface_3 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Domain.outlet"));

    surfaceCustomMeshControl_0.getGeometryObjects().setObjects(partSurface_2, partSurface_3, partSurface_0);

    ((ScalarPhysicalQuantity) partsTargetSurfaceSize_0.getAbsoluteSizeValue()).setValueAndUnits(3.0, units_0);

    PartsMinimumSurfaceSize partsMinimumSurfaceSize_0 = 
      surfaceCustomMeshControl_0.getCustomValues().get(PartsMinimumSurfaceSize.class);

    partsMinimumSurfaceSize_0.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    ((ScalarPhysicalQuantity) partsTargetSurfaceSize_0.getAbsoluteSizeValue()).setValueAndUnits(2.0, units_0);

    ((ScalarPhysicalQuantity) partsMinimumSurfaceSize_0.getAbsoluteSizeValue()).setValueAndUnits(2.0, units_0);

    PartsCustomizePrismMesh partsCustomizePrismMesh_0 = 
      surfaceCustomMeshControl_0.getCustomConditions().get(PartsCustomizePrismMesh.class);

    partsCustomizePrismMesh_0.getCustomPrismOptions().setSelected(PartsCustomPrismsOption.Type.DISABLE);

    VolumeCustomMeshControl volumeCustomMeshControl_0 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_0.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_0 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Box10"));

    volumeCustomMeshControl_0.getGeometryObjects().setObjects(simpleBlockPart_0);

    VolumeControlResurfacerSizeOption volumeControlResurfacerSizeOption_0 = 
      volumeCustomMeshControl_0.getCustomConditions().get(VolumeControlResurfacerSizeOption.class);

    volumeControlResurfacerSizeOption_0.setVolumeControlBaseSizeOption(true);

    VolumeControlSize volumeControlSize_0 = 
      volumeCustomMeshControl_0.getCustomValues().get(VolumeControlSize.class);

    volumeControlSize_0.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    ((ScalarPhysicalQuantity) volumeControlSize_0.getAbsoluteSizeValue()).setValueAndUnits(10.0, units_1);

    ((ScalarPhysicalQuantity) volumeControlSize_0.getAbsoluteSizeValue()).setValueAndUnits(7.0, units_1);

    VolumeCustomMeshControl volumeCustomMeshControl_1 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_1.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_1 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Box50"));

    volumeCustomMeshControl_1.getGeometryObjects().setObjects(simpleBlockPart_1);

    VolumeControlResurfacerSizeOption volumeControlResurfacerSizeOption_1 = 
      volumeCustomMeshControl_1.getCustomConditions().get(VolumeControlResurfacerSizeOption.class);

    volumeControlResurfacerSizeOption_1.setVolumeControlBaseSizeOption(true);

    VolumeControlSize volumeControlSize_1 = 
      volumeCustomMeshControl_1.getCustomValues().get(VolumeControlSize.class);

    volumeControlSize_1.getRelativeOrAbsoluteOption().setSelected(RelativeOrAbsoluteOption.Type.ABSOLUTE);

    ((ScalarPhysicalQuantity) volumeControlSize_1.getAbsoluteSizeValue()).setValueAndUnits(30.0, units_1);
  }
}
