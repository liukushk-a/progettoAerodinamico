// Simcenter STAR-CCM+ macro: Mesh_12.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.resurfacer.*;
import star.prismmesher.*;
import star.vis.*;
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

    Units units_4 = 
      ((Units) simulation_0.getUnitsManager().getObject("cm"));

    autoMeshOperation2d_0.getDefaultValues().get(BaseSize.class).setValueAndUnits(1.0, units_4);

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
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Domain.inlet"));

    PartSurface partSurface_1 = 
      ((PartSurface) meshOperationPart_0.getPartSurfaceManager().getPartSurface("Domain.outlet"));

    surfaceCustomMeshControl_0.getGeometryObjects().setObjects(partSurface_0, partSurface_1);

    PartsCustomizePrismMesh partsCustomizePrismMesh_0 = 
      surfaceCustomMeshControl_0.getCustomConditions().get(PartsCustomizePrismMesh.class);

    partsCustomizePrismMesh_0.getCustomPrismOptions().setSelected(PartsCustomPrismsOption.Type.DISABLE);

    surfaceCustomMeshControl_0.setPresentationName("BLdisable");

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

    ((ScalarPhysicalQuantity) volumeControlSize_0.getAbsoluteSizeValue()).setValueAndUnits(10.0, units_4);

    volumeCustomMeshControl_0.setPresentationName("Box 10");

    VolumeCustomMeshControl volumeCustomMeshControl_1 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_1.setPresentationName("Copy of Box 10");

    volumeCustomMeshControl_1.copyProperties(volumeCustomMeshControl_0);

    volumeCustomMeshControl_1.setPresentationName("Box 25");

    volumeCustomMeshControl_1.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_1 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Box25"));

    volumeCustomMeshControl_1.getGeometryObjects().setObjects(simpleBlockPart_0, simpleBlockPart_1);

    VolumeControlSize volumeControlSize_1 = 
      volumeCustomMeshControl_1.getCustomValues().get(VolumeControlSize.class);

    ((ScalarPhysicalQuantity) volumeControlSize_1.getAbsoluteSizeValue()).setValueAndUnits(25.0, units_4);

    VolumeCustomMeshControl volumeCustomMeshControl_2 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_2.setPresentationName("Copy of Box 10");

    volumeCustomMeshControl_2.copyProperties(volumeCustomMeshControl_0);

    volumeCustomMeshControl_2.setPresentationName("Box 50");

    volumeCustomMeshControl_2.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_2 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Box50"));

    volumeCustomMeshControl_2.getGeometryObjects().setObjects(simpleBlockPart_0, simpleBlockPart_2);

    VolumeControlSize volumeControlSize_2 = 
      volumeCustomMeshControl_2.getCustomValues().get(VolumeControlSize.class);

    ((ScalarPhysicalQuantity) volumeControlSize_2.getAbsoluteSizeValue()).setValueAndUnits(50.0, units_4);

    VolumeCustomMeshControl volumeCustomMeshControl_3 = 
      autoMeshOperation2d_0.getCustomMeshControls().createVolumeControl();

    volumeCustomMeshControl_3.setPresentationName("Copy of Box 10");

    volumeCustomMeshControl_3.copyProperties(volumeCustomMeshControl_0);

    volumeCustomMeshControl_3.setPresentationName("Box 80");

    volumeCustomMeshControl_3.getGeometryObjects().setQuery(null);

    SimpleBlockPart simpleBlockPart_3 = 
      ((SimpleBlockPart) simulation_0.get(SimulationPartManager.class).getPart("Box80"));

    volumeCustomMeshControl_3.getGeometryObjects().setObjects(simpleBlockPart_0, simpleBlockPart_3);

    VolumeControlSize volumeControlSize_3 = 
      volumeCustomMeshControl_3.getCustomValues().get(VolumeControlSize.class);

    ((ScalarPhysicalQuantity) volumeControlSize_3.getAbsoluteSizeValue()).setValueAndUnits(80.0, units_4);

    simulation_0.getSceneManager().createGeometryScene("Mesh Scene", "Outline", "Mesh", 3);

    Scene scene_3 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    scene_3.initializeAndWait();

    SceneUpdate sceneUpdate_3 = 
      scene_3.getSceneUpdate();

    HardcopyProperties hardcopyProperties_3 = 
      sceneUpdate_3.getHardcopyProperties();

    hardcopyProperties_3.setCurrentResolutionWidth(25);

    hardcopyProperties_3.setCurrentResolutionHeight(25);

    hardcopyProperties_3.setCurrentResolutionWidth(1082);

    hardcopyProperties_3.setCurrentResolutionHeight(473);

    scene_3.resetCamera();
  }
}
