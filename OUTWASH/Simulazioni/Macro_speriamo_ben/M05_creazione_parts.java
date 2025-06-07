// Simcenter STAR-CCM+ macro: M05_creazione_parts.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;

public class M05_creazione_parts extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    star.cadmodeler.Body cadmodelerBody_2 = 
      ((star.cadmodeler.Body) cadModel_0.getBody("Domain"));

    star.cadmodeler.Body cadmodelerBody_0 = 
      ((star.cadmodeler.Body) cadModel_0.getBody("Flap1"));

    star.cadmodeler.Body cadmodelerBody_1 = 
      ((star.cadmodeler.Body) cadModel_0.getBody("Flap2"));

    cadModel_0.createParts(new NeoObjectVector(new Object[] {cadmodelerBody_2, cadmodelerBody_0, cadmodelerBody_1}), new NeoObjectVector(new Object[] {}), true, false, 1, false, false, 3, "SharpEdges", 30.0, 4, true, 1.0E-5, false);
  }
}
