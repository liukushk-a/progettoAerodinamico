// Simcenter STAR-CCM+ macro: M16_rinominolesceneperchesonostupido.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M16_rinominolesceneperchesonostupido extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_5 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 1");

    scene_5.setPresentationName("Velocity Magnitude Scene");

    Scene scene_7 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 2");

    scene_7.setPresentationName("Cp_tot scene");

    Scene scene_6 = 
      simulation_0.getSceneManager().getScene("Vector Scene 1");

    scene_6.setPresentationName("Velocity Vectors Scene");

    scene_7.setPresentationName("Cp_tot Scene");
  }
}
