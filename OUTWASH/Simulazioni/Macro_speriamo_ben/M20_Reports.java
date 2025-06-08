// Simcenter STAR-CCM+ macro: M20_Reports.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.base.report.*;
import star.vis.*;
import star.flow.*;

public class M20_Reports extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    FrontalAreaReport frontalAreaReport_0 = 
      simulation_0.getReportManager().createReport(FrontalAreaReport.class);

    frontalAreaReport_0.setPresentationName("Frontal Area x");

    Units units_3 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    frontalAreaReport_0.getViewUpCoordinate().setCoordinate(units_3, units_3, units_3, new DoubleVector(new double[] {0.0, 0.0, 1.0}));

    frontalAreaReport_0.getNormalCoordinate().setCoordinate(units_3, units_3, units_3, new DoubleVector(new double[] {1.0, 0.0, 0.0}));

    frontalAreaReport_0.getParts().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    Boundary boundary_2 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Flap1.Default");

    Boundary boundary_3 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Flap2.Default");

    frontalAreaReport_0.getParts().setObjects(boundary_2, boundary_3);

    FrontalAreaReport frontalAreaReport_1 = 
      simulation_0.getReportManager().createReport(FrontalAreaReport.class);

    frontalAreaReport_1.copyProperties(frontalAreaReport_0);

    frontalAreaReport_1.setPresentationName("Copy of Frontal Area x");

    frontalAreaReport_1.setPresentationName("Frontal Area y");

    frontalAreaReport_1.getNormalCoordinate().setCoordinate(units_3, units_3, units_3, new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    ForceCoefficientReport forceCoefficientReport_0 = 
      simulation_0.getReportManager().createReport(ForceCoefficientReport.class);

    forceCoefficientReport_0.setPresentationName("Cl");

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject(""));

    forceCoefficientReport_0.getDirection().setComponentsAndUnits(0.0, 1.0, 0.0, units_2);

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("kg/m^3"));

    forceCoefficientReport_0.getReferenceDensity().setValueAndUnits(1.225, units_0);

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject("m/s"));

    forceCoefficientReport_0.getReferenceVelocity().setValueAndUnits(20.0, units_1);

    Units units_4 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(2).build());

    forceCoefficientReport_0.getReferenceArea().setDefinition("${FrontalAreayReport}");

    forceCoefficientReport_0.getParts().setQuery(null);

    forceCoefficientReport_0.getParts().setObjects(boundary_2, boundary_3);

    ForceCoefficientReport forceCoefficientReport_1 = 
      simulation_0.getReportManager().createReport(ForceCoefficientReport.class);

    forceCoefficientReport_1.copyProperties(forceCoefficientReport_0);

    forceCoefficientReport_1.setPresentationName("Copy of Cl");

    forceCoefficientReport_1.setPresentationName("Cd");

    forceCoefficientReport_1.getDirection().setComponentsAndUnits(1.0, 0.0, 0.0, units_2);

    forceCoefficientReport_1.getReferenceArea().setDefinition("${FrontalAreaxReport}");

    AreaAverageReport areaAverageReport_0 = 
      simulation_0.getReportManager().createReport(AreaAverageReport.class);

    areaAverageReport_0.setPresentationName("CpT_both");

    TotalPressureCoefficientFunction totalPressureCoefficientFunction_0 = 
      ((TotalPressureCoefficientFunction) simulation_0.getFieldFunctionManager().getFunction("TotalPressureCoefficient"));

    areaAverageReport_0.setFieldFunction(totalPressureCoefficientFunction_0);

    areaAverageReport_0.getParts().setQuery(null);

    areaAverageReport_0.getParts().setObjects(boundary_2, boundary_3);

    AreaAverageReport areaAverageReport_1 = 
      simulation_0.getReportManager().createReport(AreaAverageReport.class);

    areaAverageReport_1.copyProperties(areaAverageReport_0);

    areaAverageReport_1.setPresentationName("Copy of CpT_both");

    AreaAverageReport areaAverageReport_2 = 
      simulation_0.getReportManager().createReport(AreaAverageReport.class);

    areaAverageReport_2.copyProperties(areaAverageReport_0);

    areaAverageReport_2.setPresentationName("Copy of CpT_both");

    areaAverageReport_1.setPresentationName("CpT_flap1");

    areaAverageReport_1.getParts().setQuery(null);

    areaAverageReport_1.getParts().setObjects(boundary_2);

    areaAverageReport_2.setPresentationName("CpT_flap2");

    AreaAverageReport areaAverageReport_3 = 
      simulation_0.getReportManager().createReport(AreaAverageReport.class);

    areaAverageReport_3.copyProperties(areaAverageReport_0);

    areaAverageReport_3.setPresentationName("Copy of CpT_both");

    areaAverageReport_3.setPresentationName("Cp_both");

    PressureCoefficientFunction pressureCoefficientFunction_0 = 
      ((PressureCoefficientFunction) simulation_0.getFieldFunctionManager().getFunction("PressureCoefficient"));

    areaAverageReport_3.setFieldFunction(pressureCoefficientFunction_0);
  }
}
