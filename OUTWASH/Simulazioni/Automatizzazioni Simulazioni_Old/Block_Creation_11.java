// Simcenter STAR-CCM+ macro: Block_Creation_11.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.meshing.*;

public class Block_Creation_11 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.getRegionManager().updateInterfacesFromPartContacts(new NeoObjectVector(new Object[] {simulation_0.get(SimulationPartManager.class)}), RegionManager.CreateInterfaceMode.CONTACT);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    simulation_0.getSceneManager().createGeometryScene("Geometry Scene", "Outline", "Surface", 1);

    Scene scene_2 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    scene_2.initializeAndWait();

    SceneUpdate sceneUpdate_2 = 
      scene_2.getSceneUpdate();

    HardcopyProperties hardcopyProperties_2 = 
      sceneUpdate_2.getHardcopyProperties();

    hardcopyProperties_2.setCurrentResolutionWidth(25);

    hardcopyProperties_2.setCurrentResolutionHeight(25);

    hardcopyProperties_2.setCurrentResolutionWidth(1043);

    hardcopyProperties_2.setCurrentResolutionHeight(441);

    scene_2.resetCamera();

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    CurrentView currentView_1 = 
      scene_2.getCurrentView();

    currentView_1.setInput(new DoubleVector(new double[] {0.875, 0.0, 2.5}), new DoubleVector(new double[] {0.875, 0.0, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.886525556726836, 1, 30.0);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {-0.4914774287093509, 0.8129675841688537, 2.354850365064756}), new DoubleVector(new double[] {-0.4914774287093509, 0.8129675841688537, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 17.492859914290563, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {0.9838797398962498, -0.09514165014178769, 2.2352937748253296}), new DoubleVector(new double[] {0.9838797398962498, -0.09514165014178769, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.4324211018094575, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {1.041553754328014, -0.10845103808757948, 2.2342630620456987}), new DoubleVector(new double[] {1.041553754328014, -0.10845103808757948, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.7802818193357584, 1, 30.0);

    MeshPartFactory meshPartFactory_0 = 
      simulation_0.get(MeshPartFactory.class);

    SimpleCylinderPart simpleCylinderPart_0 = 
      meshPartFactory_0.createNewCylinderPart(simulation_0.get(SimulationPartManager.class));

    simpleCylinderPart_0.setDoNotRetessellate(true);

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    simpleCylinderPart_0.setCoordinateSystem(labCoordinateSystem_0);

    simpleCylinderPart_0.getStartCoordinate().setCoordinateSystem(labCoordinateSystem_0);

    simpleCylinderPart_0.getStartCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.8, -0.1, 0.0}));

    simpleCylinderPart_0.getEndCoordinate().setCoordinateSystem(labCoordinateSystem_0);

    simpleCylinderPart_0.getEndCoordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {0.8, -0.1, 1.0}));

    simpleCylinderPart_0.getRadius().setUnits(units_0);

    simpleCylinderPart_0.getRadius().setValue(1.0);

    simpleCylinderPart_0.getTessellationDensityOption().setSelected(TessellationDensityOption.Type.MEDIUM);

    simpleCylinderPart_0.rebuildSimpleShapePart();

    simpleCylinderPart_0.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {-0.6860301528986196, -0.10458619713181534, 1.8358747839966298}), new DoubleVector(new double[] {-0.6860301528986196, -0.10458619713181534, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 1.0787447604243539, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {-8.597511381970168, 2.4480652382032195, -1.2622245095534481}), new DoubleVector(new double[] {-8.597511381970168, 2.4480652382032195, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 7.8840718110059065, 1, 30.0);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {-7.901621034276792, 1.5519032812634332, 1.2597518507072596}), new DoubleVector(new double[] {16.682864299275415, -30.10779793445382, 6.284457652275043}), new DoubleVector(new double[] {0.4241694386880255, 0.4537580302576548, 0.7836988817522843}), 7.8840718110059065, 1, 30.0);

    scene_2.setViewOrientation(new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    currentView_1.setInput(new DoubleVector(new double[] {0.03608391830058366, -0.02133553816631717, 1.2597520597350496}), new DoubleVector(new double[] {0.03608391830058366, -0.02133553816631717, 41.657539767577674}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 7.8840718110059065, 1, 30.0);

    SimpleBlockPart simpleBlockPart_0 = 
      meshPartFactory_0.createNewBlockPart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_0.setDoNotRetessellate(true);

    simpleBlockPart_0.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-2.0, -6.25, 0.0}));

    simpleBlockPart_0.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {6.25, 2.0, 5.0}));

    simpleBlockPart_0.rebuildSimpleShapePart();

    simpleBlockPart_0.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    SimpleBlockPart simpleBlockPart_1 = 
      meshPartFactory_0.createNewBlockPart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_1.setDoNotRetessellate(true);

    simpleBlockPart_1.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-2.0, -4.5, 0.0}));

    simpleBlockPart_1.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {4.5, 2.0, 5.0}));

    simpleBlockPart_1.rebuildSimpleShapePart();

    simpleBlockPart_1.setDoNotRetessellate(false);

    SimpleBlockPart simpleBlockPart_2 = 
      meshPartFactory_0.createNewBlockPart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_2.setDoNotRetessellate(true);

    simpleBlockPart_2.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_2.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_2.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-2.0, -3.5, 0.0}));

    simpleBlockPart_2.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_2.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {3.5, 2.0, 5.0}));

    simpleBlockPart_2.rebuildSimpleShapePart();

    simpleBlockPart_2.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);
  }
}
