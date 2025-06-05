// Simcenter STAR-CCM+ macro: Creating_Scenes_13.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class Creating_Scenes_13 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.getSceneManager().createGeometryScene("Geometry Scene", "Outline", "Surface", 1);

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    scene_0.initializeAndWait();

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(25);

    hardcopyProperties_0.setCurrentResolutionHeight(25);

    hardcopyProperties_0.setCurrentResolutionWidth(1062);

    hardcopyProperties_0.setCurrentResolutionHeight(391);

    scene_0.resetCamera();

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    simulation_0.getSceneManager().createGeometryScene("Mesh Scene", "Outline", "Mesh", 3);

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    scene_1.initializeAndWait();

    SceneUpdate sceneUpdate_1 = 
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_1 = 
      sceneUpdate_1.getHardcopyProperties();

    hardcopyProperties_1.setCurrentResolutionWidth(25);

    hardcopyProperties_1.setCurrentResolutionHeight(25);

    hardcopyProperties_0.setCurrentResolutionWidth(1064);

    hardcopyProperties_0.setCurrentResolutionHeight(392);

    hardcopyProperties_1.setCurrentResolutionWidth(1062);

    hardcopyProperties_1.setCurrentResolutionHeight(391);

    scene_1.resetCamera();
  }
}
