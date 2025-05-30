// Simcenter STAR-CCM+ macro: Physics_Continua_09.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.segregatedflow.*;
import star.base.neo.*;
import star.material.*;
import star.turbulence.*;
import star.flow.*;
import star.kwturb.*;
import star.metrics.*;

public class Physics_Continua_09 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    PhysicsContinuum physicsContinuum_0 = 
      simulation_0.getContinuumManager().createContinuum(PhysicsContinuum.class);

    physicsContinuum_0.enable(TwoDimensionalModel.class);

    physicsContinuum_0.enable(SteadyModel.class);

    physicsContinuum_0.enable(SingleComponentGasModel.class);

    physicsContinuum_0.enable(SegregatedFlowModel.class);

    physicsContinuum_0.enable(ConstantDensityModel.class);

    physicsContinuum_0.enable(TurbulentModel.class);

    physicsContinuum_0.enable(RansTurbulenceModel.class);

    physicsContinuum_0.enable(KOmegaTurbulence.class);

    physicsContinuum_0.enable(SstKwTurbModel.class);

    physicsContinuum_0.enable(KwAllYplusWallTreatment.class);

    VelocityProfile velocityProfile_0 = 
      physicsContinuum_0.getInitialConditions().get(VelocityProfile.class);

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject("m/s"));

    velocityProfile_0.getMethod(ConstantVectorProfileMethod.class).getQuantity().setComponentsAndUnits(20.0, 0.0, 0.0, units_2);
  }
}
