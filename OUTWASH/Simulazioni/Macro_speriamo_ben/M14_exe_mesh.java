// Simcenter STAR-CCM+ macro: M14_exe_mesh.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.meshing.*;

public class M14_exe_mesh extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    AutoMeshOperation2d autoMeshOperation2d_0 = 
      ((AutoMeshOperation2d) simulation_0.get(MeshOperationManager.class).getObject("Automated Mesh (2D)"));

    autoMeshOperation2d_0.execute();

    simulation_0.getSceneManager().createGeometryScene("Mesh Scene", "Outline", "Mesh", 3);

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    scene_1.initializeAndWait();

    SceneUpdate sceneUpdate_1 = 
      scene_1.getSceneUpdate();

    HardcopyProperties hardcopyProperties_1 = 
      sceneUpdate_1.getHardcopyProperties();

    hardcopyProperties_1.setCurrentResolutionWidth(25);

    hardcopyProperties_1.setCurrentResolutionHeight(25);

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(1045);

    hardcopyProperties_0.setCurrentResolutionHeight(431);

    hardcopyProperties_1.setCurrentResolutionWidth(1043);

    hardcopyProperties_1.setCurrentResolutionHeight(430);

    scene_1.resetCamera();

    CurrentView currentView_0 = 
      scene_1.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {0.15746096360103218, 0.8308346737251207, -0.1830062676177988}), new DoubleVector(new double[] {0.15746096360103218, 0.8308346737251207, 48.740276920042085}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.861132920922232, -1.1924490025691048, -0.08494150932798661}), new DoubleVector(new double[] {2.861132920922232, -1.1924490025691048, 18.882981904900554}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.582462233375954, -1.4118508368078058, -0.03899852043091023}), new DoubleVector(new double[] {2.582462233375954, -1.4118508368078058, 13.765693805267235}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.2744290479002225, -1.4803026558024128, -0.090832657499746}), new DoubleVector(new double[] {2.2744290479002225, -1.4803026558024128, 11.150211983026658}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.08752470123519, -1.289924250649183, -0.3181701495307401}), new DoubleVector(new double[] {2.08752470123519, -1.289924250649183, 10.035190783394585}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.7275219169132638, -0.9025945223028202, -0.3446116014280971}), new DoubleVector(new double[] {1.7275219169132638, -0.9025945223028202, 8.128504535074631}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.578099187509122, -0.7982518705833177, -0.20401282097527673}), new DoubleVector(new double[] {1.578099187509122, -0.7982518705833177, 7.315654081803426}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.3260509014673127, -0.6198259361344768, -0.16229445154783573}), new DoubleVector(new double[] {1.3260509014673127, -0.6198259361344768, 5.925679806643512}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.0728810481740327, -0.4236943501839515, -0.09555516787783613}), new DoubleVector(new double[] {1.0728810481740327, -0.4236943501839515, 4.319820578038096}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);
  }
}
