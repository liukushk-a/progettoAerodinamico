// Simcenter STAR-CCM+ macro: M21_Monitor_Creation.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.flow.*;
import star.vis.*;

public class M21_Monitor_Creation extends StarMacro {

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

    AreaAverageReport areaAverageReport_3 = 
      ((AreaAverageReport) simulation_0.getReportManager().getReport("Cp_both"));

    AreaAverageReport areaAverageReport_0 = 
      ((AreaAverageReport) simulation_0.getReportManager().getReport("CpT_both"));

    AreaAverageReport areaAverageReport_1 = 
      ((AreaAverageReport) simulation_0.getReportManager().getReport("CpT_flap1"));

    AreaAverageReport areaAverageReport_2 = 
      ((AreaAverageReport) simulation_0.getReportManager().getReport("CpT_flap2"));

    FrontalAreaReport frontalAreaReport_0 = 
      ((FrontalAreaReport) simulation_0.getReportManager().getReport("Frontal Area x"));

    FrontalAreaReport frontalAreaReport_1 = 
      ((FrontalAreaReport) simulation_0.getReportManager().getReport("Frontal Area y"));

    simulation_0.getMonitorManager().createMonitorAndPlot(new NeoObjectVector(new Object[] {forceCoefficientReport_1, forceCoefficientReport_0, areaAverageReport_3, areaAverageReport_0, areaAverageReport_1, areaAverageReport_2, frontalAreaReport_0, frontalAreaReport_1}), false, "%1$s Plot");

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

    ReportMonitor reportMonitor_2 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Cp_both Monitor"));

    MonitorPlot monitorPlot_2 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_2}), "Cp_both Monitor Plot");

    monitorPlot_2.openInteractive();

    ReportMonitor reportMonitor_3 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("CpT_both Monitor"));

    MonitorPlot monitorPlot_3 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_3}), "CpT_both Monitor Plot");

    monitorPlot_3.openInteractive();

    ReportMonitor reportMonitor_4 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("CpT_flap1 Monitor"));

    MonitorPlot monitorPlot_4 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_4}), "CpT_flap1 Monitor Plot");

    monitorPlot_4.openInteractive();

    ReportMonitor reportMonitor_5 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("CpT_flap2 Monitor"));

    MonitorPlot monitorPlot_5 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_5}), "CpT_flap2 Monitor Plot");

    monitorPlot_5.openInteractive();

    ReportMonitor reportMonitor_6 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Frontal Area x Monitor"));

    MonitorPlot monitorPlot_6 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_6}), "Frontal Area x Monitor Plot");

    monitorPlot_6.openInteractive();

    ReportMonitor reportMonitor_7 = 
      ((ReportMonitor) simulation_0.getMonitorManager().getMonitor("Frontal Area y Monitor"));

    MonitorPlot monitorPlot_7 = 
      simulation_0.getPlotManager().createMonitorPlot(new NeoObjectVector(new Object[] {reportMonitor_7}), "Frontal Area y Monitor Plot");

    monitorPlot_7.openInteractive();

    PlotUpdate plotUpdate_1 = 
      monitorPlot_0.getPlotUpdate();

    HardcopyProperties hardcopyProperties_7 = 
      plotUpdate_1.getHardcopyProperties();

    hardcopyProperties_7.setCurrentResolutionWidth(25);

    hardcopyProperties_7.setCurrentResolutionHeight(25);

    PlotUpdate plotUpdate_2 = 
      monitorPlot_1.getPlotUpdate();

    HardcopyProperties hardcopyProperties_8 = 
      plotUpdate_2.getHardcopyProperties();

    hardcopyProperties_8.setCurrentResolutionWidth(25);

    hardcopyProperties_8.setCurrentResolutionHeight(25);

    hardcopyProperties_7.setCurrentResolutionWidth(11);

    hardcopyProperties_7.setCurrentResolutionHeight(11);

    hardcopyProperties_8.setCurrentResolutionWidth(1063);

    hardcopyProperties_8.setCurrentResolutionHeight(517);

    PlotUpdate plotUpdate_3 = 
      monitorPlot_2.getPlotUpdate();

    HardcopyProperties hardcopyProperties_9 = 
      plotUpdate_3.getHardcopyProperties();

    hardcopyProperties_9.setCurrentResolutionWidth(25);

    hardcopyProperties_9.setCurrentResolutionHeight(25);

    PlotUpdate plotUpdate_4 = 
      monitorPlot_3.getPlotUpdate();

    HardcopyProperties hardcopyProperties_10 = 
      plotUpdate_4.getHardcopyProperties();

    hardcopyProperties_10.setCurrentResolutionWidth(25);

    hardcopyProperties_10.setCurrentResolutionHeight(25);

    PlotUpdate plotUpdate_5 = 
      monitorPlot_4.getPlotUpdate();

    HardcopyProperties hardcopyProperties_11 = 
      plotUpdate_5.getHardcopyProperties();

    hardcopyProperties_11.setCurrentResolutionWidth(25);

    hardcopyProperties_11.setCurrentResolutionHeight(25);

    PlotUpdate plotUpdate_6 = 
      monitorPlot_5.getPlotUpdate();

    HardcopyProperties hardcopyProperties_12 = 
      plotUpdate_6.getHardcopyProperties();

    hardcopyProperties_12.setCurrentResolutionWidth(25);

    hardcopyProperties_12.setCurrentResolutionHeight(25);

    PlotUpdate plotUpdate_7 = 
      monitorPlot_6.getPlotUpdate();

    HardcopyProperties hardcopyProperties_13 = 
      plotUpdate_7.getHardcopyProperties();

    hardcopyProperties_13.setCurrentResolutionWidth(25);

    hardcopyProperties_13.setCurrentResolutionHeight(25);

    PlotUpdate plotUpdate_8 = 
      monitorPlot_7.getPlotUpdate();

    HardcopyProperties hardcopyProperties_14 = 
      plotUpdate_8.getHardcopyProperties();

    hardcopyProperties_14.setCurrentResolutionWidth(25);

    hardcopyProperties_14.setCurrentResolutionHeight(25);

    hardcopyProperties_8.setCurrentResolutionWidth(1065);

    hardcopyProperties_8.setCurrentResolutionHeight(518);

    hardcopyProperties_9.setCurrentResolutionWidth(1065);

    hardcopyProperties_9.setCurrentResolutionHeight(518);

    hardcopyProperties_10.setCurrentResolutionWidth(1065);

    hardcopyProperties_10.setCurrentResolutionHeight(518);

    hardcopyProperties_11.setCurrentResolutionWidth(1065);

    hardcopyProperties_11.setCurrentResolutionHeight(518);

    hardcopyProperties_12.setCurrentResolutionWidth(1065);

    hardcopyProperties_12.setCurrentResolutionHeight(518);

    hardcopyProperties_13.setCurrentResolutionWidth(1065);

    hardcopyProperties_13.setCurrentResolutionHeight(518);

    hardcopyProperties_14.setCurrentResolutionWidth(1063);

    hardcopyProperties_14.setCurrentResolutionHeight(517);

    monitorPlot_7.closeInteractive();

    hardcopyProperties_13.setCurrentResolutionWidth(1063);

    hardcopyProperties_13.setCurrentResolutionHeight(517);

    monitorPlot_6.closeInteractive();

    hardcopyProperties_12.setCurrentResolutionWidth(1063);

    hardcopyProperties_12.setCurrentResolutionHeight(517);

    monitorPlot_5.closeInteractive();

    hardcopyProperties_11.setCurrentResolutionWidth(1063);

    hardcopyProperties_11.setCurrentResolutionHeight(517);

    monitorPlot_4.closeInteractive();

    hardcopyProperties_10.setCurrentResolutionWidth(1063);

    hardcopyProperties_10.setCurrentResolutionHeight(517);

    monitorPlot_3.closeInteractive();

    hardcopyProperties_9.setCurrentResolutionWidth(1063);

    hardcopyProperties_9.setCurrentResolutionHeight(517);

    monitorPlot_2.closeInteractive();

    hardcopyProperties_8.setCurrentResolutionWidth(1063);

    hardcopyProperties_8.setCurrentResolutionHeight(517);

    monitorPlot_1.closeInteractive();

    hardcopyProperties_7.setCurrentResolutionWidth(1063);

    hardcopyProperties_7.setCurrentResolutionHeight(517);

    monitorPlot_0.closeInteractive();
  }
}
