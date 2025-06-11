// Simcenter STAR-CCM+ macro: M15_autosave_mesh.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M15_autosave_mesh extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("Mesh Scene 1");

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {1.5406299886203951, -0.13706926030282685, -0.1372866432782116}), new DoubleVector(new double[] {1.5406299886203951, -0.13706926030282685, 6.957114240092789}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.7112614313113284, 0.6301445775223079, -0.27653488816046945}), new DoubleVector(new double[] {2.7112614313113284, 0.6301445775223079, 13.557447490371457}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.566511770370145, 1.8460471074285478, -0.4898984229928267}), new DoubleVector(new double[] {4.566511770370145, 1.8460471074285478, 24.01784523292008}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.461068028383446, 2.4323255165264963, -0.5927771318473312}), new DoubleVector(new double[] {5.461068028383446, 2.4323255165264963, 29.06159273421534}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.982253558897511, 2.639046154644039, 3.237628511814492}), new DoubleVector(new double[] {4.982253558897511, 2.639046154644039, 32.29922123758299}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.290121535582042, 0.171353620642271, 9.387868260546384E-9}), new DoubleVector(new double[] {3.290121535582042, 0.171353620642271, 32.29922123758299}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.9405347820439367, 0.08850965700078312, -0.6660081160447326}), new DoubleVector(new double[] {3.9405347820439367, 0.08850965700078312, 29.069299114763478}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.529246648741477, 0.12023713243897322, 8.449084987205424E-9}), new DoubleVector(new double[] {1.529246648741477, 0.12023713243897322, 29.069299114763478}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.2796014428546756, -0.02412288080479222, -0.9982538015527638}), new DoubleVector(new double[] {2.2796014428546756, -0.02412288080479222, 26.162369204132037}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.6799521570535139, -0.02412288080479244, 7.60417506739941E-9}), new DoubleVector(new double[] {1.6799521570535139, -0.02412288080479244, 26.162369204132037}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.4038145091992007, -0.17689067504461195, -1.0370169593619138}), new DoubleVector(new double[] {2.4038145091992007, -0.17689067504461195, 23.54613228447925}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.530039835575007, -0.04839439951164137, 6.84375578430263E-9}), new DoubleVector(new double[] {1.530039835575007, -0.04839439951164137, 23.54613228447925}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    SceneUpdate sceneUpdate_0 = 
      scene_0.getSceneUpdate();

    HardcopyProperties hardcopyProperties_0 = 
      sceneUpdate_0.getHardcopyProperties();

    hardcopyProperties_0.setCurrentResolutionWidth(1163);

    hardcopyProperties_0.setCurrentResolutionHeight(497);

    hardcopyProperties_0.setCurrentResolutionHeight(520);

    hardcopyProperties_0.setCurrentResolutionHeight(538);

    hardcopyProperties_0.setCurrentResolutionHeight(561);

    hardcopyProperties_0.setCurrentResolutionHeight(566);

    hardcopyProperties_0.setCurrentResolutionHeight(567);

    currentView_0.setInput(new DoubleVector(new double[] {0.8612903744938843, 0.1774893618851437, -1.0445159190480098}), new DoubleVector(new double[] {0.8612903744938843, 0.1774893618851437, 25.9007455122428}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.2040105641902974, 0.2019693754348873, 7.528136336532043E-9}), new DoubleVector(new double[] {1.2040105641902974, 0.2019693754348873, 25.9007455122428}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.477770043633681, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.2040105641902974, 0.2019693754348873, 7.528136336532043E-9}), new DoubleVector(new double[] {1.2040105641902974, 0.2019693754348873, 25.9007455122428}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.761451372782045, 1, 30.0);

    sceneUpdate_0.setSaveAnimation(true);

    sceneUpdate_0.setAnimationFilePath("C:\\Users\\silvi\\OneDrive\\Documenti\\GitHub\\progettoAerodinamico\\OUTWASH\\Simulazioni\\SIM results\\Scenes");

    sceneUpdate_0.setAnimationFilenameBase("mesh");

    IterationUpdateFrequency iterationUpdateFrequency_0 = 
      sceneUpdate_0.getIterationUpdateFrequency();

    iterationUpdateFrequency_0.setIterations(1500);

    hardcopyProperties_0.setOutputWidth(2200);

    hardcopyProperties_0.setOutputHeight(567);

    hardcopyProperties_0.setUseCurrentResolution(false);

    hardcopyProperties_0.setOutputHeight(1100);

    hardcopyProperties_0.setTransparentBackground(true);
  }
}
