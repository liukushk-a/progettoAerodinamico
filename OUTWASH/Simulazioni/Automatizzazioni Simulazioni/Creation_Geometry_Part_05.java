// Simcenter STAR-CCM+ macro: Creation_Geometry_Part_05.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;
import star.vis.*;

public class Creation_Geometry_Part_05 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    CadModel cadModel_1 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    simulation_0.get(SolidModelManager.class).endEditCadModel(cadModel_1);

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_1}));

    star.cadmodeler.Body cadmodelerBody_0 = 
      ((star.cadmodeler.Body) cadModel_1.getBody("Domain"));

    star.cadmodeler.Body cadmodelerBody_1 = 
      ((star.cadmodeler.Body) cadModel_1.getBody("Flap1"));

    star.cadmodeler.Body cadmodelerBody_2 = 
      ((star.cadmodeler.Body) cadModel_1.getBody("Flap2"));

    cadModel_1.createParts(new NeoObjectVector(new Object[] {cadmodelerBody_0, cadmodelerBody_1, cadmodelerBody_2}), new NeoObjectVector(new Object[] {}), true, false, 1, false, false, 3, "SharpEdges", 30.0, 4, true, 1.0E-5, false);
  }
}
