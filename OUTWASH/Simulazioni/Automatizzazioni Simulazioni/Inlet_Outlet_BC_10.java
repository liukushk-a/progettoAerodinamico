// Simcenter STAR-CCM+ macro: Inlet_Outlet_BC_10.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.flow.*;

public class Inlet_Outlet_BC_10 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Subtract");

    Boundary boundary_0 = 
      region_0.getBoundaryManager().getBoundary("Domain.inlet");

    InletBoundary inletBoundary_0 = 
      ((InletBoundary) simulation_0.get(ConditionTypeManager.class).get(InletBoundary.class));

    boundary_0.setBoundaryType(inletBoundary_0);

    boundary_0.getConditions().get(FlowDirectionOption.class).setSelected(FlowDirectionOption.Type.COMPONENTS);

    VelocityMagnitudeProfile velocityMagnitudeProfile_0 = 
      boundary_0.getValues().get(VelocityMagnitudeProfile.class);

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject("m/s"));

    velocityMagnitudeProfile_0.getMethod(ConstantScalarProfileMethod.class).getQuantity().setValueAndUnits(20.0, units_2);

    Boundary boundary_1 = 
      region_0.getBoundaryManager().getBoundary("Domain.outlet");

    PressureBoundary pressureBoundary_0 = 
      ((PressureBoundary) simulation_0.get(ConditionTypeManager.class).get(PressureBoundary.class));

    boundary_1.setBoundaryType(pressureBoundary_0);
  }
}
