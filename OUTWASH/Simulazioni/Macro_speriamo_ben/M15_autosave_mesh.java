// Simcenter STAR-CCM+ macro: M15_autosave_mesh.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M15_autosave_mesh extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    SceneUpdate sceneUpdate_2 = 
      scene_1.getSceneUpdate();

    sceneUpdate_2.setSaveAnimation(true);

    sceneUpdate_2.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Scenes");

    sceneUpdate_2.setAnimationFilenameBase("Mesh");

    IterationUpdateFrequency iterationUpdateFrequency_0 = 
      sceneUpdate_2.getIterationUpdateFrequency();

    iterationUpdateFrequency_0.setIterations(1500);

    HardcopyProperties hardcopyProperties_2 = 
      sceneUpdate_2.getHardcopyProperties();

    hardcopyProperties_2.setOutputWidth(1250);

    hardcopyProperties_2.setOutputHeight(574);

    hardcopyProperties_2.setUseCurrentResolution(false);

    hardcopyProperties_2.setOutputHeight(1250);

    hardcopyProperties_2.setTransparentBackground(true);
  }
}
