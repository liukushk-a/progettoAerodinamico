// Simcenter STAR-CCM+ macro: Executing_Mesh_13.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.meshing.*;

public class Executing_Mesh_12 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      ((AutoMeshOperation2d) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh (2D)"));

    autoMeshOperation2d_0.execute();

    simulation_0.getSceneManager().createGeometryScene("Mesh Scene", "Outline", "Mesh", 3);

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    scene_0.initializeAndWait();

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(25);

    hardcopyProperties_0.setCurrentResolutionHeight(25);

    hardcopyProperties_0.setCurrentResolutionWidth(1084);

    hardcopyProperties_0.setCurrentResolutionHeight(462);

    scene_0.resetCamera();
  }
}
