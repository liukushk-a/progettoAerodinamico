// Simcenter STAR-CCM+ macro: M17_reports.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.flow.*;
import star.vis.*;

public class M17_reports extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    ForceCoefficientReport forceCoefficientReport_0 = 
      simulation_0.getReportManager().createReport(ForceCoefficientReport.class);

    forceCoefficientReport_0.setPresentationName("Cl");

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject(""));

    forceCoefficientReport_0.getDirection().setComponentsAndUnits(0.0, 1.0, 0.0, units_1);

    Units units_3 = 
      ((Units) simulation_0.getUnitsManager().getObject("kg/m^3"));

    forceCoefficientReport_0.getReferenceDensity().setValueAndUnits(1.225, units_3);

    Units units_4 = 
      ((Units) simulation_0.getUnitsManager().getObject("m/s"));

    forceCoefficientReport_0.getReferenceVelocity().setValueAndUnits(20.0, units_4);

    FrontalAreaReport frontalAreaReport_0 = 
      simulation_0.getReportManager().createReport(FrontalAreaReport.class);

    frontalAreaReport_0.setPresentationName("Frontal Area Y");

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    frontalAreaReport_0.getViewUpCoordinate().setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {0.0, 0.0, 1.0}));

    frontalAreaReport_0.getNormalCoordinate().setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    frontalAreaReport_0.getParts().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    Boundary boundary_0 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Flap1.Flap1");

    Boundary boundary_1 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Flap2.Flap2");

    frontalAreaReport_0.getParts().setObjects(boundary_0, boundary_1);

    Units units_5 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(2).build());

    forceCoefficientReport_0.getReferenceArea().setDefinition("${FrontalAreaYReport}");

    forceCoefficientReport_0.getParts().setQuery(null);

    forceCoefficientReport_0.getParts().setObjects(boundary_0, boundary_1);

    ForceCoefficientReport forceCoefficientReport_1 = 
      simulation_0.getReportManager().createReport(ForceCoefficientReport.class);

    forceCoefficientReport_1.copyProperties(forceCoefficientReport_0);

    forceCoefficientReport_1.setPresentationName("Copy of Cl");

    forceCoefficientReport_1.setPresentationName("Cd");

    forceCoefficientReport_1.getDirection().setComponentsAndUnits(1.0, 0.0, 0.0, units_1);

    FrontalAreaReport frontalAreaReport_1 = 
      simulation_0.getReportManager().createReport(FrontalAreaReport.class);

    frontalAreaReport_1.copyProperties(frontalAreaReport_0);

    frontalAreaReport_1.setPresentationName("Copy of Frontal Area Y");

    frontalAreaReport_1.setPresentationName("Frontal Area X");

    frontalAreaReport_1.getNormalCoordinate().setCoordinate(units_2, units_2, units_2, new DoubleVector(new double[] {1.0, 0.0, 0.0}));

    forceCoefficientReport_1.getReferenceArea().setDefinition("${FrontalAreaXReport}");
  }
}
