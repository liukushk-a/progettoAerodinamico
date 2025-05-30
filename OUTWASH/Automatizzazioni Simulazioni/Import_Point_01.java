// Simcenter STAR-CCM+ macro: Import_Point_01.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.cadmodeler.*;

public class Import_Point_01 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_1 = 
      simulation_0.getSceneManager().createScene("3D-CAD View");

    scene_1.initializeAndWait();

    CadModel cadModel_1 = 
      simulation_0.get(SolidModelManager.class).createSolidModel(scene_1);

    cadModel_1.resetSystemOptions();

    scene_1.openInteractive();

    scene_1.setAdvancedRenderingEnabled(false);

    SceneUpdate sceneUpdate_1 = 
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_1 = 
      sceneUpdate_1.getHardcopyProperties();

    hardcopyProperties_1.setCurrentResolutionWidth(25);

    hardcopyProperties_1.setCurrentResolutionHeight(25);

    hardcopyProperties_1.setCurrentResolutionWidth(1082);

    hardcopyProperties_1.setCurrentResolutionHeight(473);

    scene_1.resetCamera();

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    cadModel_1.getFeatureManager().create3DSketches_2("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\profili\\simulazione_7_7\\profili_accoppiati.csv", labCoordinateSystem_0, true, false);

    cadModel_1.update();

    scene_1.resetCamera();
  }
}
