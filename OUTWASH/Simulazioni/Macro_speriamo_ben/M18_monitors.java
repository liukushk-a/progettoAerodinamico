// Simcenter STAR-CCM+ macro: M18_monitors.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.flow.*;
import star.vis.*;

public class M18_monitors extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    ForceCoefficientReport forceCoefficientReport_1 = 
      ((ForceCoefficientReport) simulation_0.getReportManager().getReport("Cd"));

    ForceCoefficientReport forceCoefficientReport_0 = 
      ((ForceCoefficientReport) simulation_0.getReportManager().getReport("Cl"));

    simulation_0.getMonitorManager().createMonitorAndPlot(new NeoObjectVector(new Object[] {forceCoefficientReport_1, forceCoefficientReport_0}), false, "%1$s Plot");

    ReportMonitor reportMonitor_0 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Cd Monitor"));

    MonitorPlot monitorPlot_0 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_0}), "Cd Monitor Plot");

    monitorPlot_0.openInteractive();

    ReportMonitor reportMonitor_1 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Cl Monitor"));

    MonitorPlot monitorPlot_1 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_1}), "Cl Monitor Plot");

    monitorPlot_1.openInteractive();

    PlotUpdate plotUpdate_0 = 
      monitorPlot_0.getPlotUpdate();

    HardcopyProperties hardcopyProperties_8 = 
      plotUpdate_0.getHardcopyProperties();

    hardcopyProperties_8.setCurrentResolutionWidth(25);

    hardcopyProperties_8.setCurrentResolutionHeight(25);

    hardcopyProperties_8.setCurrentResolutionWidth(1043);

    hardcopyProperties_8.setCurrentResolutionHeight(430);

    PlotUpdate plotUpdate_1 = 
      monitorPlot_1.getPlotUpdate();

    HardcopyProperties hardcopyProperties_9 = 
      plotUpdate_1.getHardcopyProperties();

    hardcopyProperties_9.setCurrentResolutionWidth(25);

    hardcopyProperties_9.setCurrentResolutionHeight(25);

    Scene scene_7 = 
      simulation_0.getSceneManager().getScene("Cp_tot Scene");

    SceneUpdate sceneUpdate_7 = 
      scene_7.getSceneUpdate();

    HardcopyProperties hardcopyProperties_7 = 
      sceneUpdate_7.getHardcopyProperties();

    hardcopyProperties_7.setCurrentResolutionWidth(1045);

    hardcopyProperties_7.setCurrentResolutionHeight(431);

    hardcopyProperties_8.setCurrentResolutionWidth(1045);

    hardcopyProperties_8.setCurrentResolutionHeight(431);

    hardcopyProperties_9.setCurrentResolutionWidth(1043);

    hardcopyProperties_9.setCurrentResolutionHeight(430);

    Cartesian2DAxisManager cartesian2DAxisManager_0 = 
      ((Cartesian2DAxisManager) monitorPlot_0.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_0 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_0.getAxis("Left Axis"));

    cartesian2DAxis_0.setMaximum(1.5);

    Cartesian2DAxisManager cartesian2DAxisManager_1 = 
      ((Cartesian2DAxisManager) monitorPlot_1.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_1 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_1.getAxis("Left Axis"));

    cartesian2DAxis_1.setMaximum(4.0);

    cartesian2DAxis_1.setLockMinimum(true);

    cartesian2DAxis_1.setLockMinimum(false);

    cartesian2DAxis_1.setLockMinimum(true);

    cartesian2DAxis_0.setLockMinimum(true);
  }
}
