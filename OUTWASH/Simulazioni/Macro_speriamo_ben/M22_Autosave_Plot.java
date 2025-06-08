// Simcenter STAR-CCM+ macro: M22_Autosave_Plot.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.common.graph.*;

public class M22_Autosave_Plot extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    MonitorPlot monitorPlot_0 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cd Monitor Plot"));

    Cartesian2DAxisManager cartesian2DAxisManager_0 = 
      ((Cartesian2DAxisManager) monitorPlot_0.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_0 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_0.getAxis("Left Axis"));

    cartesian2DAxis_0.setMaximum(2.0);

    PlotUpdate plotUpdate_1 = 
      monitorPlot_0.getPlotUpdate();

    plotUpdate_1.setSaveAnimation(true);

    plotUpdate_1.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot");

    IterationUpdateFrequency iterationUpdateFrequency_4 = 
      plotUpdate_1.getIterationUpdateFrequency();

    iterationUpdateFrequency_4.setIterations(1500);

    plotUpdate_1.setAnimationFilenameBase("plot");

    HardcopyProperties hardcopyProperties_7 = 
      plotUpdate_1.getHardcopyProperties();

    hardcopyProperties_7.setOutputWidth(1100);

    hardcopyProperties_7.setOutputHeight(517);

    hardcopyProperties_7.setUseCurrentResolution(false);

    hardcopyProperties_7.setOutputHeight(520);

    ChartStyleAssignment chartStyleAssignment_0 = 
      monitorPlot_0.getChartStyleAssignment();

    PredefinedColorPalette predefinedColorPalette_0 = 
      ((PredefinedColorPalette) simulation_0.get(ColorPaletteManager.class).getObject("Legacy Plot Color Palette"));

    chartStyleAssignment_0.setColorPalette(predefinedColorPalette_0);

    PredefinedColorPalette predefinedColorPalette_1 = 
      ((PredefinedColorPalette) simulation_0.get(ColorPaletteManager.class).getObject("High Contrast Color Palette"));

    chartStyleAssignment_0.setColorPalette(predefinedColorPalette_1);

    MonitorPlot monitorPlot_1 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cl Monitor Plot"));

    Cartesian2DAxisManager cartesian2DAxisManager_1 = 
      ((Cartesian2DAxisManager) monitorPlot_1.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_1 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_1.getAxis("Left Axis"));

    cartesian2DAxis_1.setMaximum(3.0);

    PlotUpdate plotUpdate_2 = 
      monitorPlot_1.getPlotUpdate();

    plotUpdate_2.setSaveAnimation(true);

    plotUpdate_2.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot");

    plotUpdate_2.setAnimationFilenameBase("plot");

    IterationUpdateFrequency iterationUpdateFrequency_5 = 
      plotUpdate_2.getIterationUpdateFrequency();

    iterationUpdateFrequency_5.setIterations(1500);

    HardcopyProperties hardcopyProperties_8 = 
      plotUpdate_2.getHardcopyProperties();

    hardcopyProperties_8.setOutputWidth(1100);

    hardcopyProperties_8.setOutputHeight(517);

    hardcopyProperties_8.setUseCurrentResolution(false);

    hardcopyProperties_8.setOutputHeight(520);

    MonitorPlot monitorPlot_2 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cp_both Monitor Plot"));

    Cartesian2DAxisManager cartesian2DAxisManager_2 = 
      ((Cartesian2DAxisManager) monitorPlot_2.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_2 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_2.getAxis("Left Axis"));

    cartesian2DAxis_2.setMinimum(-10.0);

    PlotUpdate plotUpdate_3 = 
      monitorPlot_2.getPlotUpdate();

    plotUpdate_3.setSaveAnimation(true);

    plotUpdate_3.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot");

    plotUpdate_3.setAnimationFilenameBase("plot");

    IterationUpdateFrequency iterationUpdateFrequency_6 = 
      plotUpdate_3.getIterationUpdateFrequency();

    iterationUpdateFrequency_6.setIterations(1500);

    HardcopyProperties hardcopyProperties_9 = 
      plotUpdate_3.getHardcopyProperties();

    hardcopyProperties_9.setOutputWidth(1100);

    hardcopyProperties_9.setOutputHeight(517);

    hardcopyProperties_9.setUseCurrentResolution(false);

    hardcopyProperties_9.setOutputHeight(520);

    hardcopyProperties_9.setTransparentBackground(true);

    hardcopyProperties_9.setTransparentBackground(false);

    MonitorPlot monitorPlot_3 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("CpT_both Monitor Plot"));

    Cartesian2DAxisManager cartesian2DAxisManager_3 = 
      ((Cartesian2DAxisManager) monitorPlot_3.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_3 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_3.getAxis("Left Axis"));

    cartesian2DAxis_3.setMinimum(-10.0);

    PredefinedColorPalette predefinedColorPalette_2 = 
      ((PredefinedColorPalette) simulation_0.get(ColorPaletteManager.class).getObject("Siemens Color Palette"));

    chartStyleAssignment_0.setColorPalette(predefinedColorPalette_2);

    PlotUpdate plotUpdate_4 = 
      monitorPlot_3.getPlotUpdate();

    plotUpdate_4.setSaveAnimation(true);

    plotUpdate_4.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot");

    plotUpdate_4.setAnimationFilenameBase("plot");

    IterationUpdateFrequency iterationUpdateFrequency_7 = 
      plotUpdate_4.getIterationUpdateFrequency();

    iterationUpdateFrequency_7.setIterations(1500);

    HardcopyProperties hardcopyProperties_10 = 
      plotUpdate_4.getHardcopyProperties();

    hardcopyProperties_10.setOutputWidth(1100);

    hardcopyProperties_10.setOutputHeight(517);

    hardcopyProperties_10.setUseCurrentResolution(false);

    hardcopyProperties_10.setOutputHeight(520);

    MonitorPlot monitorPlot_4 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("CpT_flap1 Monitor Plot"));

    Cartesian2DAxisManager cartesian2DAxisManager_4 = 
      ((Cartesian2DAxisManager) monitorPlot_4.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_4 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_4.getAxis("Left Axis"));

    cartesian2DAxis_4.setMinimum(-10.0);

    PlotUpdate plotUpdate_5 = 
      monitorPlot_4.getPlotUpdate();

    plotUpdate_5.setSaveAnimation(true);

    plotUpdate_5.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot");

    plotUpdate_5.setAnimationFilenameBase("plot");

    IterationUpdateFrequency iterationUpdateFrequency_8 = 
      plotUpdate_5.getIterationUpdateFrequency();

    iterationUpdateFrequency_8.setIterations(1500);

    HardcopyProperties hardcopyProperties_11 = 
      plotUpdate_5.getHardcopyProperties();

    hardcopyProperties_11.setOutputWidth(1100);

    hardcopyProperties_11.setOutputHeight(517);

    hardcopyProperties_11.setUseCurrentResolution(false);

    hardcopyProperties_11.setOutputHeight(520);

    MonitorPlot monitorPlot_5 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("CpT_flap2 Monitor Plot"));

    Cartesian2DAxisManager cartesian2DAxisManager_5 = 
      ((Cartesian2DAxisManager) monitorPlot_5.getAxisManager());

    Cartesian2DAxis cartesian2DAxis_5 = 
      ((Cartesian2DAxis) cartesian2DAxisManager_5.getAxis("Left Axis"));

    cartesian2DAxis_5.setMinimum(-10.0);

    PlotUpdate plotUpdate_6 = 
      monitorPlot_5.getPlotUpdate();

    plotUpdate_6.setSaveAnimation(true);

    IterationUpdateFrequency iterationUpdateFrequency_9 = 
      plotUpdate_6.getIterationUpdateFrequency();

    iterationUpdateFrequency_9.setIterations(1500);

    plotUpdate_6.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot");

    HardcopyProperties hardcopyProperties_12 = 
      plotUpdate_6.getHardcopyProperties();

    hardcopyProperties_12.setOutputWidth(1100);

    hardcopyProperties_12.setOutputHeight(517);

    hardcopyProperties_12.setUseCurrentResolution(false);

    hardcopyProperties_12.setOutputHeight(520);

    ResidualPlot residualPlot_0 = 
      ((ResidualPlot) simulation_0.getPlotManager().getPlot("Residuals"));

    PlotUpdate plotUpdate_0 = 
      residualPlot_0.getPlotUpdate();

    plotUpdate_0.setSaveAnimation(true);

    plotUpdate_0.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot");

    plotUpdate_0.setAnimationFilenameBase("plot");

    IterationUpdateFrequency iterationUpdateFrequency_10 = 
      plotUpdate_0.getIterationUpdateFrequency();

    iterationUpdateFrequency_10.setIterations(1500);

    HardcopyProperties hardcopyProperties_5 = 
      plotUpdate_0.getHardcopyProperties();

    hardcopyProperties_5.setOutputWidth(1100);

    hardcopyProperties_5.setOutputHeight(518);

    hardcopyProperties_5.setUseCurrentResolution(false);

    hardcopyProperties_5.setOutputHeight(520);
  }
}
