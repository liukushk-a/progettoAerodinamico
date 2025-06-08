// Simcenter STAR-CCM+ macro: M18_Velocity_Scalar.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M18_Velocity_Scalar extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.getSceneManager().createScalarScene("Scalar Scene", "Outline", "Scalar");

    Scene scene_10 = 
      simulation_0.getSceneManager().getScene("Scalar Scene 1");

    scene_10.initializeAndWait();

    ScalarDisplayer scalarDisplayer_2 = 
      ((ScalarDisplayer) scene_10.getDisplayerManager().getObject("Scalar 1"));

    Legend legend_3 = 
      scalarDisplayer_2.getLegend();

    PredefinedLookupTable predefinedLookupTable_0 = 
      ((PredefinedLookupTable) simulation_0.get(LookupTableManager.class).getObject("blue-yellow-red"));

    legend_3.setLookupTable(predefinedLookupTable_0);

    SceneUpdate sceneUpdate_7 = 
      scene_10.getSceneUpdate();

    HardcopyProperties hardcopyProperties_6 = 
      sceneUpdate_7.getHardcopyProperties();

    hardcopyProperties_6.setCurrentResolutionWidth(25);

    hardcopyProperties_6.setCurrentResolutionHeight(25);

    hardcopyProperties_6.setCurrentResolutionWidth(1165);

    hardcopyProperties_6.setCurrentResolutionHeight(517);

    scene_10.resetCamera();

    scene_10.setPresentationName("Velocity_Scalar");

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

    VectorMagnitudeFieldFunction vectorMagnitudeFieldFunction_0 = 
      ((VectorMagnitudeFieldFunction) primitiveFieldFunction_0.getMagnitudeFunction());

    scalarDisplayer_2.getScalarDisplayQuantity().setFieldFunction(vectorMagnitudeFieldFunction_0);

    PredefinedLookupTable predefinedLookupTable_1 = 
      ((PredefinedLookupTable) simulation_0.get(LookupTableManager.class).getObject("purple-red"));

    legend_3.setLookupTable(predefinedLookupTable_1);

    legend_3.setWidth(0.5);

    legend_3.setHeight(0.025);

    legend_3.setHeight(0.04);

    legend_3.setPositionCoordinate(new DoubleVector(new double[] {0.24, 0.04}));

    CurrentView currentView_7 = 
      scene_10.getCurrentView();

    currentView_7.setInput(new DoubleVector(new double[] {0.875, 0.0, 0.0}), new DoubleVector(new double[] {0.875, 0.0, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.559553347727949, 1, 30.0);

    sceneUpdate_7.setSaveAnimation(true);

    sceneUpdate_7.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Scenes");

    sceneUpdate_7.setAnimationFilenameBase("overview");

    IterationUpdateFrequency iterationUpdateFrequency_3 = 
      sceneUpdate_7.getIterationUpdateFrequency();

    iterationUpdateFrequency_3.setIterations(1500);

    hardcopyProperties_6.setOutputWidth(1100);

    hardcopyProperties_6.setOutputHeight(517);

    hardcopyProperties_6.setUseCurrentResolution(false);

    hardcopyProperties_6.setOutputHeight(1100);

    hardcopyProperties_6.setTransparentBackground(true);

    Scene scene_11 = 
      simulation_0.getSceneManager().createScene("Velocity_Scalar");

    scene_11.copyProperties(scene_10);

    scene_11.initializeAndWait();

    scene_11.setPresentationName("Copy of Velocity_Scalar");

    scene_11.openInteractive();

    scene_11.setPresentationName("Velocity_Scalar_detail");

    CurrentView currentView_8 = 
      scene_11.getCurrentView();

    currentView_8.setInput(new DoubleVector(new double[] {0.9566059432123118, -0.12324162852471575, -0.029819283956619813}), new DoubleVector(new double[] {0.9566059432123118, -0.12324162852471575, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 8.60446219528434, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.0300512921033924, -0.2341590941969599, -0.024155509031096756}), new DoubleVector(new double[] {1.0300512921033924, -0.2341590941969599, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 7.744646180058831, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.0961641253477972, -0.3340029648109588, -0.019574324129145282}), new DoubleVector(new double[] {1.0961641253477972, -0.3340029648109588, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.970641275241061, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.1556695996486355, -0.4238683749795718, -0.015858120247095542}), new DoubleVector(new double[] {1.1556695996486355, -0.4238683749795718, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.273912398051406, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.2092273884124891, -0.5047515661739628, -0.0128470001121741}), new DoubleVector(new double[] {1.2092273884124891, -0.5047515661739628, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 5.646765626303872, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.2574314852224002, -0.5775495899277061, -0.010407339558966555}), new DoubleVector(new double[] {1.2574314852224002, -0.5775495899277061, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 5.08226732257733, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.30081669407367, -0.6430701094173791, -0.008430785688446463}), new DoubleVector(new double[] {1.30081669407367, -0.6430701094173791, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 4.574170565688776, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.3398644915856475, -0.7020402525987326, -0.006829495581087031}), new DoubleVector(new double[] {1.3398644915856475, -0.7020402525987326, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 4.116848275711967, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.4066377623990245, -0.8028815187250572, -0.004481221418465964}), new DoubleVector(new double[] {1.4066377623990245, -0.8028815187250572, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.334759659734901, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.4351052229089567, -0.845873193780873, -0.003630098338440746}), new DoubleVector(new double[] {1.4351052229089567, -0.845873193780873, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.001320419544655, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.4607262508806793, -0.8845661747993929, -0.0029405010701921697}), new DoubleVector(new double[] {1.4607262508806793, -0.8845661747993929, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.7012151524563968, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.027778874528782, 0.12250707019306184, -1.1459069071406702E-7}), new DoubleVector(new double[] {1.027778874528782, 0.12250707019306184, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.7012151524563968, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {1.0800149166755868, 0.05380020394591306, -0.010169895336623824}), new DoubleVector(new double[] {1.0800149166755868, 0.05380020394591306, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.4311769650811534, 1, 30.0);

    currentView_8.setInput(new DoubleVector(new double[] {0.8428269200823042, 0.019916204432586904, -1.1459069071406702E-7}), new DoubleVector(new double[] {0.8428269200823042, 0.019916204432586904, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.4311769650811534, 1, 30.0);

    SceneUpdate sceneUpdate_8 = 
      scene_11.getSceneUpdate();

    sceneUpdate_8.setAnimationFilenameBase("detail");

    scene_11.closeInteractive();

    scene_10.closeInteractive();
  }
}
