// Simcenter STAR-CCM+ macro: M17_velocity_vector.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M17_velocity_vector extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.getSceneManager().createVectorScene("Vector Scene", "Outline", "Vector");

    Scene scene_6 = 
      simulation_0.getSceneManager().getScene("Vector Scene 1");

    scene_6.initializeAndWait();

    VectorDisplayer vectorDisplayer_0 = 
      ((VectorDisplayer) scene_6.getDisplayerManager().getObject("Vector 1"));

    Legend legend_2 = 
      vectorDisplayer_0.getLegend();

    PredefinedLookupTable predefinedLookupTable_0 = 
      ((PredefinedLookupTable) simulation_0.get(LookupTableManager.class).getObject("blue-yellow-red"));

    legend_2.setLookupTable(predefinedLookupTable_0);

    SceneUpdate sceneUpdate_5 = 
      scene_6.getSceneUpdate();

    HardcopyProperties hardcopyProperties_4 = 
      sceneUpdate_5.getHardcopyProperties();

    hardcopyProperties_4.setCurrentResolutionWidth(25);

    hardcopyProperties_4.setCurrentResolutionHeight(25);

    hardcopyProperties_4.setCurrentResolutionWidth(1165);

    hardcopyProperties_4.setCurrentResolutionHeight(517);

    scene_6.resetCamera();

    scene_6.setPresentationName("Velocity_Vector");

    vectorDisplayer_0.setDisplayMode(VectorDisplayMode.VECTOR_DISPLAY_MODE_LIC);

    legend_2.setHeight(0.04);

    legend_2.setHeight(0.05);

    legend_2.setPositionCoordinate(new DoubleVector(new double[] {0.35, 0.08}));

    legend_2.setWidth(0.5);

    legend_2.setPositionCoordinate(new DoubleVector(new double[] {0.25, 0.04}));

    CurrentView currentView_5 = 
      scene_6.getCurrentView();

    currentView_5.setInput(new DoubleVector(new double[] {0.875, 0.0, 0.0}), new DoubleVector(new double[] {0.875, 0.0, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.559553347727949, 1, 30.0);

    sceneUpdate_5.setSaveAnimation(true);

    sceneUpdate_5.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Scenes");

    sceneUpdate_5.setAnimationFilenameBase("Overview");

    IterationUpdateFrequency iterationUpdateFrequency_2 = 
      sceneUpdate_5.getIterationUpdateFrequency();

    iterationUpdateFrequency_2.setIterations(1500);

    hardcopyProperties_4.setOutputWidth(1100);

    hardcopyProperties_4.setOutputHeight(517);

    hardcopyProperties_4.setUseCurrentResolution(false);

    hardcopyProperties_4.setOutputHeight(1100);

    hardcopyProperties_4.setTransparentBackground(true);

    Scene scene_7 = 
      simulation_0.getSceneManager().createScene("Velocity_Vector");

    scene_7.copyProperties(scene_6);

    scene_7.initializeAndWait();

    scene_7.setPresentationName("Copy of Velocity_Vector");

    scene_7.openInteractive();

    scene_7.setPresentationName("Velocity_Vector_detail");

    CurrentView currentView_6 = 
      scene_7.getCurrentView();

    currentView_6.setInput(new DoubleVector(new double[] {0.9566059432123118, -0.12324162852471575, -0.029819283956619813}), new DoubleVector(new double[] {0.9566059432123118, -0.12324162852471575, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 8.60446219528434, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.0300512921033924, -0.2341590941969599, -0.024155509031096756}), new DoubleVector(new double[] {1.0300512921033924, -0.2341590941969599, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 7.744646180058831, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.0961641253477972, -0.3340029648109588, -0.019574324129145282}), new DoubleVector(new double[] {1.0961641253477972, -0.3340029648109588, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.970641275241061, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.1556695996486355, -0.4238683749795718, -0.015858120247095542}), new DoubleVector(new double[] {1.1556695996486355, -0.4238683749795718, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.273912398051406, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.2092273884124891, -0.5047515661739628, -0.0128470001121741}), new DoubleVector(new double[] {1.2092273884124891, -0.5047515661739628, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 5.646765626303872, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.2574314852224002, -0.5775495899277061, -0.010407339558966555}), new DoubleVector(new double[] {1.2574314852224002, -0.5775495899277061, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 5.08226732257733, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.30081669407367, -0.6430701094173791, -0.008430785688446463}), new DoubleVector(new double[] {1.30081669407367, -0.6430701094173791, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 4.574170565688776, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.3398644915856475, -0.7020402525987326, -0.006829495581087031}), new DoubleVector(new double[] {1.3398644915856475, -0.7020402525987326, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 4.116848275711967, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.4066377623990245, -0.8028815187250572, -0.004481221418465964}), new DoubleVector(new double[] {1.4066377623990245, -0.8028815187250572, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.334759659734901, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.4351052229089567, -0.845873193780873, -0.003630098338440746}), new DoubleVector(new double[] {1.4351052229089567, -0.845873193780873, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 3.001320419544655, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.4607262508806793, -0.8845661747993929, -0.0029405010701921697}), new DoubleVector(new double[] {1.4607262508806793, -0.8845661747993929, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.7012151524563968, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.027778874528782, 0.12250707019306184, -1.1459069071406702E-7}), new DoubleVector(new double[] {1.027778874528782, 0.12250707019306184, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.7012151524563968, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {1.0800149166755868, 0.05380020394591306, -0.010169895336623824}), new DoubleVector(new double[] {1.0800149166755868, 0.05380020394591306, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.4311769650811534, 1, 30.0);

    currentView_6.setInput(new DoubleVector(new double[] {0.8428269200823042, 0.019916204432586904, -1.1459069071406702E-7}), new DoubleVector(new double[] {0.8428269200823042, 0.019916204432586904, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.4311769650811534, 1, 30.0);

    SceneUpdate sceneUpdate_6 = 
      scene_7.getSceneUpdate();

    sceneUpdate_6.setAnimationFilenameBase("detail");

    scene_7.closeInteractive();

    scene_6.closeInteractive();
  }
}
