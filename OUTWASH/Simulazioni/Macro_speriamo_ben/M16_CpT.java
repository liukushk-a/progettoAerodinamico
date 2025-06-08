// Simcenter STAR-CCM+ macro: M16_CpT.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.flow.*;
import star.vis.*;

public class M16_CpT extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    PressureCoefficientFunction pressureCoefficientFunction_0 = 
      ((PressureCoefficientFunction) simulation_0.getFieldFunctionManager().getFunction("PressureCoefficient"));

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("kg/m^3"));

    pressureCoefficientFunction_0.getReferenceDensity().setValueAndUnits(1.225, units_0);

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject("m/s"));

    pressureCoefficientFunction_0.getReferenceVelocity().setValueAndUnits(20.0, units_1);

    TotalPressureCoefficientFunction totalPressureCoefficientFunction_0 = 
      ((TotalPressureCoefficientFunction) simulation_0.getFieldFunctionManager().getFunction("TotalPressureCoefficient"));

    totalPressureCoefficientFunction_0.getReferenceDensity().setValueAndUnits(1.225, units_0);

    totalPressureCoefficientFunction_0.getReferenceVelocity().setValueAndUnits(20.0, units_1);

    simulation_0.getSceneManager().createScalarScene("Scalar Scene", "Outline", "Scalar");

    Scene scene_4 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 1");

    scene_4.initializeAndWait();

    ScalarDisplayer scalarDisplayer_1 = 
      ((ScalarDisplayer) scene_4.getDisplayerManager().getObject("Scalar 1"));

    Legend legend_1 = 
      scalarDisplayer_1.getLegend();

    PredefinedLookupTable predefinedLookupTable_0 = 
      ((PredefinedLookupTable) simulation_0.get(LookupTableManager.class).getObject("blue-yellow-red"));

    legend_1.setLookupTable(predefinedLookupTable_0);

    SceneUpdate sceneUpdate_3 = 
      scene_4.getSceneUpdate();

    HardcopyProperties hardcopyProperties_3 = 
      sceneUpdate_3.getHardcopyProperties();

    hardcopyProperties_3.setCurrentResolutionWidth(25);

    hardcopyProperties_3.setCurrentResolutionHeight(25);

    hardcopyProperties_3.setCurrentResolutionWidth(1145);

    hardcopyProperties_3.setCurrentResolutionHeight(574);

    scene_4.resetCamera();

    scalarDisplayer_1.getScalarDisplayQuantity().setFieldFunction(totalPressureCoefficientFunction_0);

    Units units_2 = 
      ((Units) simulation_0.getUnitsManager().getObject(""));

    scalarDisplayer_1.getScalarDisplayQuantity().getMinimumValue().setValueAndUnits(-0.5, units_2);

    scalarDisplayer_1.getScalarDisplayQuantity().getMaximumValue().setValueAndUnits(1.3, units_2);

    legend_1.setPositionCoordinate(new DoubleVector(new double[] {0.35, 0.08}));

    legend_1.setWidth(0.5);

    legend_1.setHeight(0.04);

    legend_1.setHeight(0.05);

    legend_1.setPositionCoordinate(new DoubleVector(new double[] {0.25, 0.04}));

    IterationUpdateFrequency iterationUpdateFrequency_1 = 
      sceneUpdate_3.getIterationUpdateFrequency();

    iterationUpdateFrequency_1.setIterations(1500);

    sceneUpdate_3.setSaveAnimation(true);

    sceneUpdate_3.setAnimationFilePath(".");

    sceneUpdate_3.setSaveAnimation(false);

    sceneUpdate_3.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Scenes");

    sceneUpdate_3.setAnimationFilenameBase("CpT_overview");

    hardcopyProperties_3.setOutputWidth(1100);

    hardcopyProperties_3.setOutputHeight(574);

    hardcopyProperties_3.setUseCurrentResolution(false);

    hardcopyProperties_3.setOutputHeight(1100);

    hardcopyProperties_3.setTransparentBackground(true);

    scene_4.setPresentationName("CpT_overview");

    CurrentView currentView_3 = 
      scene_4.getCurrentView();

    currentView_3.setInput(new DoubleVector(new double[] {0.875, 0.0, 0.0}), new DoubleVector(new double[] {0.875, 0.0, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.559553347727949, 1, 30.0);

    sceneUpdate_3.setSaveAnimation(true);

    Scene scene_5 = 
      simulation_0.getSceneManager().createScene("CpT_overview");

    scene_5.copyProperties(scene_4);

    scene_5.initializeAndWait();

    scene_5.setPresentationName("Copy of CpT_overview");

    scene_5.openInteractive();

    scene_5.setPresentationName("CpT_detail");

    CurrentView currentView_4 = 
      scene_5.getCurrentView();

    currentView_4.setInput(new DoubleVector(new double[] {0.9566059432123118, -0.12324162852471575, -0.029819283956619813}), new DoubleVector(new double[] {0.9566059432123118, -0.12324162852471575, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 8.60446219528434, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.0300512921033924, -0.2341590941969599, -0.024155509031096756}), new DoubleVector(new double[] {1.0300512921033924, -0.2341590941969599, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 7.744646180058831, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.0961641253477972, -0.3340029648109588, -0.019574324129145282}), new DoubleVector(new double[] {1.0961641253477972, -0.3340029648109588, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.970641275241061, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.1556695996486355, -0.4238683749795718, -0.015858120247095542}), new DoubleVector(new double[] {1.1556695996486355, -0.4238683749795718, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.273912398051406, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.2092273884124891, -0.5047515661739628, -0.0128470001121741}), new DoubleVector(new double[] {1.2092273884124891, -0.5047515661739628, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 5.646765626303872, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.2574314852224002, -0.5775495899277061, -0.010407339558966555}), new DoubleVector(new double[] {1.2574314852224002, -0.5775495899277061, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 5.08226732257733, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.30081669407367, -0.6430701094173791, -0.008430785688446463}), new DoubleVector(new double[] {1.30081669407367, -0.6430701094173791, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 4.574170565688776, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.3398644915856475, -0.7020402525987326, -0.006829495581087031}), new DoubleVector(new double[] {1.3398644915856475, -0.7020402525987326, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 4.116848275711967, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.4066377623990245, -0.8028815187250572, -0.004481221418465964}), new DoubleVector(new double[] {1.4066377623990245, -0.8028815187250572, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.334759659734901, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.4351052229089567, -0.845873193780873, -0.003630098338440746}), new DoubleVector(new double[] {1.4351052229089567, -0.845873193780873, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.001320419544655, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.4607262508806793, -0.8845661747993929, -0.0029405010701921697}), new DoubleVector(new double[] {1.4607262508806793, -0.8845661747993929, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.7012151524563968, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.027778874528782, 0.12250707019306184, -1.1459069071406702E-7}), new DoubleVector(new double[] {1.027778874528782, 0.12250707019306184, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.7012151524563968, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {1.0800149166755868, 0.05380020394591306, -0.010169895336623824}), new DoubleVector(new double[] {1.0800149166755868, 0.05380020394591306, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.4311769650811534, 1, 30.0);

    currentView_4.setInput(new DoubleVector(new double[] {0.8428269200823042, 0.019916204432586904, -1.1459069071406702E-7}), new DoubleVector(new double[] {0.8428269200823042, 0.019916204432586904, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.4311769650811534, 1, 30.0);

    SceneUpdate sceneUpdate_4 = 
      scene_5.getSceneUpdate();

    sceneUpdate_4.setAnimationFilenameBase("CpT_detail");

    scene_5.closeInteractive();

    scene_4.closeInteractive();
  }
}
