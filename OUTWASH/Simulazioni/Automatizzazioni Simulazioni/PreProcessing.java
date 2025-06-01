// Simcenter STAR-CCM+ macro: All.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import java.io.*;
import star.vis.*;

public class PreProcessing extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    new StarScript(getActiveRootObject(), new File(resolvePath("Import_Point_01.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Profile_Extrusion_02.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Domain_Creation_03.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Rename_Bodies_Surfaces_04.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Creation_Geometry_Part_05.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Subtract_06.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Badge_07.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Assign_Part_Regions_08.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Physics_Continua_09.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Inlet_Outlet_BC_10.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Block_Creation_11.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Mesh_12.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Executing_Mesh_13.java"))).play();


    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.getSceneManager().createGeometryScene("Mesh Scene", "Outline", "Mesh", 3);

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 2");

    scene_0.initializeAndWait();

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(25);

    hardcopyProperties_0.setCurrentResolutionHeight(25);

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    SceneUpdate sceneUpdate_1 = 
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_1 = 
      sceneUpdate_1.getHardcopyProperties();

    hardcopyProperties_1.setCurrentResolutionWidth(1213);

    hardcopyProperties_1.setCurrentResolutionHeight(476);

    hardcopyProperties_0.setCurrentResolutionWidth(1211);

    hardcopyProperties_0.setCurrentResolutionHeight(475);

    scene_0.resetCamera();

    scene_0.closeInteractive();

    scene_1.closeInteractive();

    Scene scene_2 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    scene_2.closeInteractive();
  }
}
