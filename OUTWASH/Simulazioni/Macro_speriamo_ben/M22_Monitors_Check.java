// Simcenter STAR-CCM+ macro: M22_Monitors_Check.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.report.*;

public class M22_Monitors_Check extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    ResidualMonitor residualMonitor_0 = 
      ((ResidualMonitor) simulation_0.getMonitorManager().getMonitor("Continuity"));

    residualMonitor_0.getNormalizeOption().setSelected(MonitorNormalizeOption.Type.OFF);

    ResidualMonitor residualMonitor_1 = 
      ((ResidualMonitor) simulation_0.getMonitorManager().getMonitor("Sdr"));

    residualMonitor_1.getNormalizeOption().setSelected(MonitorNormalizeOption.Type.OFF);

    ResidualMonitor residualMonitor_2 = 
      ((ResidualMonitor) simulation_0.getMonitorManager().getMonitor("Tke"));

    residualMonitor_2.getNormalizeOption().setSelected(MonitorNormalizeOption.Type.OFF);

    ResidualMonitor residualMonitor_3 = 
      ((ResidualMonitor) simulation_0.getMonitorManager().getMonitor("X-momentum"));

    residualMonitor_3.getNormalizeOption().setSelected(MonitorNormalizeOption.Type.OFF);

    ResidualMonitor residualMonitor_4 = 
      ((ResidualMonitor) simulation_0.getMonitorManager().getMonitor("Y-momentum"));

    residualMonitor_4.getNormalizeOption().setSelected(MonitorNormalizeOption.Type.OFF);
  }
}
