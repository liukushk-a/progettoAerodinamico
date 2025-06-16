// Simcenter STAR-CCM+ macro: M26_Line.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M26_Line extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(1061);

    hardcopyProperties_0.setCurrentResolutionHeight(500);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_0.getCreatorGroup().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    scene_0.getCreatorGroup().setObjects(region_0);

    scene_0.getCreatorGroup().setQuery(null);

    scene_0.getCreatorGroup().setObjects(region_0);

    PartDisplayer partDisplayer_0 = 
      scene_0.getDisplayerManager().createPartDisplayer("Probe Surface", -1, 1);

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    LinePart linePart_0 = 
      simulation_0.getPartManager().createLinePart(new NeoObjectVector(new Object[] {}), new DoubleVector(new double[] {0.0, 0.0, 0.0}), new DoubleVector(new double[] {1.0, 0.0, 0.0}), 20);

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    linePart_0.getPoint1Coordinate().setCoordinateSystem(labCoordinateSystem_0);

    linePart_0.getPoint1Coordinate().setUnits0(units_0);

    linePart_0.getPoint1Coordinate().setUnits1(units_0);

    linePart_0.getPoint1Coordinate().setUnits2(units_0);

    linePart_0.getPoint1Coordinate().setDefinition("");

    linePart_0.getPoint1Coordinate().setValue(new DoubleVector(new double[] {4.1, 0.0, 0.0}));

    linePart_0.getPoint1Coordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {4.1, 0.0, 0.0}));

    linePart_0.getPoint2Coordinate().setCoordinateSystem(labCoordinateSystem_0);

    linePart_0.getPoint2Coordinate().setUnits0(units_0);

    linePart_0.getPoint2Coordinate().setUnits1(units_0);

    linePart_0.getPoint2Coordinate().setUnits2(units_0);

    linePart_0.getPoint2Coordinate().setDefinition("");

    linePart_0.getPoint2Coordinate().setValue(new DoubleVector(new double[] {4.1, -6.0, 0.0}));

    linePart_0.getPoint2Coordinate().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {4.1, -6.0, 0.0}));

    linePart_0.setCoordinateSystem(labCoordinateSystem_0);

    linePart_0.getInputParts().setQuery(null);

    linePart_0.getInputParts().setObjects(region_0);

    linePart_0.setResolution(100);

    partDisplayer_0.getVisibleParts().addParts(linePart_0);

    partDisplayer_0.getHiddenParts().addParts();

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    XYPlot xYPlot_0 = 
      simulation_0.getPlotManager().createPlot(XYPlot.class);

    xYPlot_0.openInteractive();

    PlotUpdate plotUpdate_0 = 
      xYPlot_0.getPlotUpdate();

    HardcopyProperties hardcopyProperties_3 = 
      plotUpdate_0.getHardcopyProperties();

    hardcopyProperties_3.setCurrentResolutionWidth(25);

    hardcopyProperties_3.setCurrentResolutionHeight(25);

    hardcopyProperties_0.setCurrentResolutionWidth(1063);

    hardcopyProperties_0.setCurrentResolutionHeight(501);

    hardcopyProperties_3.setCurrentResolutionWidth(1061);

    hardcopyProperties_3.setCurrentResolutionHeight(500);

    AxisType axisType_0 = 
      xYPlot_0.getXAxisType();

    axisType_0.getDirectionVector().setComponentsAndUnits(0.0, 1.0, 0.0, units_0);

    xYPlot_0.getParts().setQuery(null);

    xYPlot_0.getParts().setObjects(linePart_0);

    YAxisType yAxisType_0 = 
      ((YAxisType) xYPlot_0.getYAxes().getAxisType("Y Type 1"));

    FieldFunctionUnits fieldFunctionUnits_0 = 
      yAxisType_0.getScalarFunction();

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

    VectorComponentFieldFunction vectorComponentFieldFunction_0 = 
      ((VectorComponentFieldFunction) primitiveFieldFunction_0.getComponentFunction(1));

    fieldFunctionUnits_0.setFieldFunction(vectorComponentFieldFunction_0);

    xYPlot_0.setPresentationName("Y Direction");

    XYPlot xYPlot_1 = 
      simulation_0.getPlotManager().createPlot(XYPlot.class);

    xYPlot_1.openInteractive();

    xYPlot_1.copyProperties(xYPlot_0);

    xYPlot_1.setPresentationName("Copy of Y Direction");

    PlotUpdate plotUpdate_1 = 
      xYPlot_1.getPlotUpdate();

    HardcopyProperties hardcopyProperties_4 = 
      plotUpdate_1.getHardcopyProperties();

    hardcopyProperties_4.setCurrentResolutionWidth(25);

    hardcopyProperties_4.setCurrentResolutionHeight(25);

    hardcopyProperties_3.setCurrentResolutionWidth(1063);

    hardcopyProperties_3.setCurrentResolutionHeight(501);

    hardcopyProperties_4.setCurrentResolutionWidth(1061);

    hardcopyProperties_4.setCurrentResolutionHeight(500);

    xYPlot_1.setPresentationName("X Direction");

    YAxisType yAxisType_1 = 
      ((YAxisType) xYPlot_1.getYAxes().getAxisType("Y Type 1"));

    FieldFunctionUnits fieldFunctionUnits_1 = 
      yAxisType_1.getScalarFunction();

    VectorComponentFieldFunction vectorComponentFieldFunction_1 = 
      ((VectorComponentFieldFunction) primitiveFieldFunction_0.getComponentFunction(0));

    fieldFunctionUnits_1.setFieldFunction(vectorComponentFieldFunction_1);

    xYPlot_1.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\X_Direction.csv"), ",");

    xYPlot_0.export(resolvePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Plot\\Y_Direction.csv"), ",");
  }
}