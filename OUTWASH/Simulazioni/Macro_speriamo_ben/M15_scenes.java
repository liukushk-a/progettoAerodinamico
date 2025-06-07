// Simcenter STAR-CCM+ macro: M15_scenes.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.flow.*;

public class M15_scenes extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    CurrentView currentView_0 = 
      scene_1.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {-5.745945674357269, -1.3004136389423884, -1.6262574189938874}), new DoubleVector(new double[] {-5.745945674357269, -1.3004136389423884, 15.701638799620607}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-1.7496533680700432, -0.6238892161974955, -2.3913682222413364}), new DoubleVector(new double[] {-1.7496533680700432, -0.6238892161974955, 24.87139585593382}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    simulation_0.getSceneManager().createScalarScene("Scalar Scene", "Outline", "Scalar");

    Scene scene_5 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 1");

    scene_5.initializeAndWait();

    ScalarDisplayer scalarDisplayer_2 = 
      ((ScalarDisplayer) scene_5.getDisplayerManager().getObject("Scalar 1"));

    Legend legend_2 = 
      scalarDisplayer_2.getLegend();

    PredefinedLookupTable predefinedLookupTable_0 = 
      ((PredefinedLookupTable) simulation_0.get(LookupTableManager.class).getObject("blue-yellow-red"));

    legend_2.setLookupTable(predefinedLookupTable_0);

    SceneUpdate sceneUpdate_5 = 
      scene_5.getSceneUpdate();

    HardcopyProperties hardcopyProperties_5 = 
      sceneUpdate_5.getHardcopyProperties();

    hardcopyProperties_5.setCurrentResolutionWidth(25);

    hardcopyProperties_5.setCurrentResolutionHeight(25);

    SceneUpdate sceneUpdate_1 = 
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_1 = 
      sceneUpdate_1.getHardcopyProperties();

    hardcopyProperties_1.setCurrentResolutionWidth(1045);

    hardcopyProperties_1.setCurrentResolutionHeight(431);

    hardcopyProperties_5.setCurrentResolutionWidth(1043);

    hardcopyProperties_5.setCurrentResolutionHeight(430);

    scene_5.resetCamera();

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

    VectorMagnitudeFieldFunction vectorMagnitudeFieldFunction_0 = 
      ((VectorMagnitudeFieldFunction) primitiveFieldFunction_0.getMagnitudeFunction());

    scalarDisplayer_2.getScalarDisplayQuantity().setFieldFunction(vectorMagnitudeFieldFunction_0);

    simulation_0.getSceneManager().createVectorScene("Vector Scene", "Outline", "Vector");

    Scene scene_6 = 
      simulation_0.getSceneManager().getScene("Vector Scene 1");

    scene_6.initializeAndWait();

    VectorDisplayer vectorDisplayer_0 = 
      ((VectorDisplayer) scene_6.getDisplayerManager().getObject("Vector 1"));

    Legend legend_3 = 
      vectorDisplayer_0.getLegend();

    legend_3.setLookupTable(predefinedLookupTable_0);

    SceneUpdate sceneUpdate_6 = 
      scene_6.getSceneUpdate();

    HardcopyProperties hardcopyProperties_6 = 
      sceneUpdate_6.getHardcopyProperties();

    hardcopyProperties_6.setCurrentResolutionWidth(25);

    hardcopyProperties_6.setCurrentResolutionHeight(25);

    hardcopyProperties_5.setCurrentResolutionWidth(1045);

    hardcopyProperties_5.setCurrentResolutionHeight(431);

    hardcopyProperties_6.setCurrentResolutionWidth(1043);

    hardcopyProperties_6.setCurrentResolutionHeight(430);

    scene_6.resetCamera();

    vectorDisplayer_0.setDisplayMode(VectorDisplayMode.VECTOR_DISPLAY_MODE_LIC);

    simulation_0.getSceneManager().createScalarScene("Scalar Scene", "Outline", "Scalar");

    Scene scene_7 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 2");

    scene_7.initializeAndWait();

    ScalarDisplayer scalarDisplayer_3 = 
      ((ScalarDisplayer) scene_7.getDisplayerManager().getObject("Scalar 1"));

    Legend legend_4 = 
      scalarDisplayer_3.getLegend();

    legend_4.setLookupTable(predefinedLookupTable_0);

    SceneUpdate sceneUpdate_7 = 
      scene_7.getSceneUpdate();

    HardcopyProperties hardcopyProperties_7 = 
      sceneUpdate_7.getHardcopyProperties();

    hardcopyProperties_7.setCurrentResolutionWidth(25);

    hardcopyProperties_7.setCurrentResolutionHeight(25);

    hardcopyProperties_6.setCurrentResolutionWidth(1045);

    hardcopyProperties_6.setCurrentResolutionHeight(431);

    hardcopyProperties_7.setCurrentResolutionWidth(1043);

    hardcopyProperties_7.setCurrentResolutionHeight(430);

    scene_7.resetCamera();

    TotalPressureCoefficientFunction totalPressureCoefficientFunction_0 = 
      ((TotalPressureCoefficientFunction) simulation_0.getFieldFunctionManager().getFunction("TotalPressureCoefficient"));

    scalarDisplayer_3.getScalarDisplayQuantity().setFieldFunction(totalPressureCoefficientFunction_0);
  }
}
