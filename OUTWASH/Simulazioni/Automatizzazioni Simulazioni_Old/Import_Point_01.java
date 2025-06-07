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

    Scene scene_0 = 
      simulation_0.getSceneManager().createScene("3D-CAD View");

    scene_0.initializeAndWait();

    CadModel cadModel_0 = 
      simulation_0.get(SolidModelManager.class).createSolidModel(scene_0);

    cadModel_0.resetSystemOptions();

    scene_0.openInteractive();

    scene_0.setAdvancedRenderingEnabled(false);

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(25);

    hardcopyProperties_0.setCurrentResolutionHeight(25);

    hardcopyProperties_0.setCurrentResolutionWidth(1082);

    hardcopyProperties_0.setCurrentResolutionHeight(505);

    scene_0.resetCamera();

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    cadModel_0.getFeatureManager().create3DSketches_2("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\profili_accoppiati.csv", labCoordinateSystem_0, true, false);

    cadModel_0.update();

    scene_0.resetCamera();
  }
}
