// Simcenter STAR-CCM+ macro: M11_blocchi_vari.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.meshing.*;

public class M11_blocchi_vari extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.getSceneManager().createGeometryScene("Geometry Scene", "Outline", "Surface", 1);

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    scene_1.initializeAndWait();

    SceneUpdate sceneUpdate_0 = 
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(25);

    hardcopyProperties_0.setCurrentResolutionHeight(25);

    hardcopyProperties_0.setCurrentResolutionWidth(1043);

    hardcopyProperties_0.setCurrentResolutionHeight(430);

    scene_1.resetCamera();

    CurrentView currentView_1 = 
      scene_1.getCurrentView();

    currentView_1.setInput(new DoubleVector(new double[] {0.875, 0.0, 2.5}), new DoubleVector(new double[] {0.875, 0.0, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.801944960057671, 0, 30.0);

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {0.875, 0.0, 2.5}), new DoubleVector(new double[] {0.875, 0.0, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.886525556726836, 1, 30.0);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {0.6832400896643724, -0.17874952661807822, 1.779360645028511}), new DoubleVector(new double[] {0.6832400896643724, -0.17874952661807822, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.312560756339465, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {1.000878288509319, -0.2080249366498705, 1.713321288078241}), new DoubleVector(new double[] {1.000878288509319, -0.2080249366498705, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.683740329406874, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {1.1101002786595988, -0.2105214392818769, 1.697887262439643}), new DoubleVector(new double[] {1.1101002786595988, -0.2105214392818769, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.41548529855393, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {0.9017872754620933, -0.14373563316538843, 1.6649658217626921}), new DoubleVector(new double[] {0.9017872754620933, -0.14373563316538843, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 1.1098437356494317, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {0.9190801894826774, -0.15147872899550074, 1.664501858840083}), new DoubleVector(new double[] {0.9190801894826774, -0.15147872899550074, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 1.2208269004238754, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {0.9381023760706774, -0.15999612597520219, 1.6639406080546024}), new DoubleVector(new double[] {0.9381023760706774, -0.15999612597520219, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 1.3429079816153533, 1, 30.0);

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

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {0.7832194387078697, -0.1977416851367904, 1.6529205078187488}), new DoubleVector(new double[] {0.7832194387078697, -0.1977416851367904, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.9790143211557284, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {0.3680143846295129, 2.8393091358968885, 1.0932506555704862}), new DoubleVector(new double[] {0.3680143846295129, 2.8393091358968885, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 8.768233442485561, 1, 30.0);

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {3.1004406201947834, -0.42328935433029646, 1.0932504666406686}), new DoubleVector(new double[] {3.1004406201947834, -0.42328935433029646, 40.3718071391347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 8.768233442485561, 1, 30.0);

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

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);
  }
}
