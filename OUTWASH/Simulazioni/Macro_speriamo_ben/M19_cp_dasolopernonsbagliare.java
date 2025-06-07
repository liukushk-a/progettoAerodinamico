// Simcenter STAR-CCM+ macro: M19_cp_dasolopernonsbagliare.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.flow.*;

public class M19_cp_dasolopernonsbagliare extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AreaAverageReport areaAverageReport_0 = 
      simulation_0.getReportManager().createReport(AreaAverageReport.class);

    TotalPressureCoefficientFunction totalPressureCoefficientFunction_0 = 
      ((TotalPressureCoefficientFunction) simulation_0.getFieldFunctionManager().getFunction("TotalPressureCoefficient"));

    areaAverageReport_0.setFieldFunction(totalPressureCoefficientFunction_0);

    areaAverageReport_0.setPresentationName("Cp_tot");

    simulation_0.getMonitorManager().createMonitorAndPlot(new NeoObjectVector(new Object[] {areaAverageReport_0}), true, "%1$s Plot");

    ReportMonitor reportMonitor_2 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Cp_tot Monitor"));

    MonitorPlot monitorPlot_2 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_2}), "Cp_tot Monitor Plot");

    monitorPlot_2.openInteractive();

    PlotUpdate plotUpdate_2 = 
      monitorPlot_2.getPlotUpdate();

    HardcopyProperties hardcopyProperties_10 = 
      plotUpdate_2.getHardcopyProperties();

    hardcopyProperties_10.setCurrentResolutionWidth(25);

    hardcopyProperties_10.setCurrentResolutionHeight(25);

    MonitorPlot monitorPlot_1 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cl Monitor Plot"));

    PlotUpdate plotUpdate_1 = 
      monitorPlot_1.getPlotUpdate();

    HardcopyProperties hardcopyProperties_9 = 
      plotUpdate_1.getHardcopyProperties();

    hardcopyProperties_9.setCurrentResolutionWidth(1045);

    hardcopyProperties_9.setCurrentResolutionHeight(431);

    hardcopyProperties_10.setCurrentResolutionWidth(1043);

    hardcopyProperties_10.setCurrentResolutionHeight(430);
  }
}
