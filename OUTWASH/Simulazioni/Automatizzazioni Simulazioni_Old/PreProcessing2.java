// Simcenter STAR-CCM+ macro: PreProcessing.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import java.io.*;
import star.vis.*;

public class PreProcessing2 extends StarMacro {

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


    new StarScript(getActiveRootObject(), new File(resolvePath("Mesh_11.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Executing_Mesh_12.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Creating_Scenes_13.java"))).play();


    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 2");

    scene_0.resetCamera();
  }
}
