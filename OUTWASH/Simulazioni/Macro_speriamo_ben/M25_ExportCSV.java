// Simcenter STAR-CCM+ macro: M25_ExportCSV.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;

public class M25_ExportCSV extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    MonitorPlot monitorPlot_0 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cd Monitor Plot"));

    monitorPlot_0.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\Cd.csv"), ",");

    MonitorPlot monitorPlot_1 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cl Monitor Plot"));

    monitorPlot_1.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\Cl.csv"), ",");

    MonitorPlot monitorPlot_2 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cp_both Monitor Plot"));

    monitorPlot_2.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\Cp_both.csv"), ",");

    MonitorPlot monitorPlot_3 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("CpT_both Monitor Plot"));

    monitorPlot_3.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\CpT_both.csv"), ",");

    MonitorPlot monitorPlot_4 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("CpT_flap1 Monitor Plot"));

    monitorPlot_4.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\CpT_flap1.csv"), ",");

    MonitorPlot monitorPlot_5 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("CpT_flap2 Monitor Plot"));

    monitorPlot_5.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\CpT_flap2.csv"), ",");
  }
}
