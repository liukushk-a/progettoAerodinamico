// Simcenter STAR-CCM+ macro: PreProcessing.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import java.io.*;

public class PreProcessing extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    new StarScript(getActiveRootObject(), new File(resolvePath("M01_importa_punti.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M02_estrusione_profili.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M03_creazione_dominio.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M04_rename_surface_bodies.java"))).play();


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

  }
}
