// Simcenter STAR-CCM+ macro: LaMacroDefinitiva.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import java.io.*;
import star.vis.*;
import star.cadmodeler.*;

public class LaMacroDefinitiva extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    new StarScript(getActiveRootObject(), new File(resolvePath("M01_importa_punti.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M02_estrusione_profili.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M03_creazione_dominio.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M04_rename_surface_bodies.java"))).play();


    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_0 = 
      simulation_0.getSceneManager().createScene("3D-CAD View");

    scene_0.initializeAndWait();

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    simulation_0.get(SolidModelManager.class).editCadModel(cadModel_0, scene_0);

    scene_0.openInteractive();

    scene_0.setAdvancedRenderingEnabled(false);

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(25);

    hardcopyProperties_0.setCurrentResolutionHeight(25);

    hardcopyProperties_0.setCurrentResolutionWidth(1043);

    hardcopyProperties_0.setCurrentResolutionHeight(430);

    scene_0.resetCamera();

    new StarScript(getActiveRootObject(), new File(resolvePath("M04_rename_surface_bodies.java"))).play();


    simulation_0.get(SolidModelManager.class).endEditCadModel(cadModel_0);

    new StarScript(getActiveRootObject(), new File(resolvePath("M05_creazione_parts.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M06_subtract.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M07_badge_automated_mesh2d.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M08_assign_parts_to_regions.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M09_continua.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M10_inlet_outlet.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M11_blocchi_vari.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M12_mesh_default_controls.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M13_mesh_custom_controls.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M14_exe_mesh.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M15_scenes.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M16_rinominolesceneperchesonostupido.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M17_reports.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M18_monitors.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M19_cp_dasolopernonsbagliare.java"))).play();

  }
}
