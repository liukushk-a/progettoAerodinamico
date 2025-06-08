// Simcenter STAR-CCM+ macro: M23_LayoutViews.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M23_LayoutViews extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    LayoutView layoutView_0 = 
      simulation_0.get(LayoutViewManager.class).createLayoutView("Layout View", LayoutPane.Axis.LEFT_TO_RIGHT, 2, 2);

    layoutView_0.openInteractive();

    LayoutViewUpdate layoutViewUpdate_0 = 
      layoutView_0.getLayoutViewUpdate();

    HardcopyProperties hardcopyProperties_15 = 
      layoutViewUpdate_0.getHardcopyProperties();

    hardcopyProperties_15.setCurrentResolutionWidth(1063);

    hardcopyProperties_15.setCurrentResolutionHeight(517);

    layoutView_0.setPresentationName("Plots");

    MonitorPlot monitorPlot_0 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cd Monitor Plot"));

    layoutView_0.getLayoutAssociationManager().createAssociation(layoutView_0.getRootLayoutPane().getPaneAt(1).getPaneAt(0), monitorPlot_0);

    MonitorPlot monitorPlot_1 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cl Monitor Plot"));

    layoutView_0.getLayoutAssociationManager().createAssociation(layoutView_0.getRootLayoutPane().getPaneAt(0).getPaneAt(0), monitorPlot_1);

    MonitorPlot monitorPlot_3 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("CpT_both Monitor Plot"));

    layoutView_0.getLayoutAssociationManager().createAssociation(layoutView_0.getRootLayoutPane().getPaneAt(0).getPaneAt(1), monitorPlot_3);

    MonitorPlot monitorPlot_2 = 
      ((MonitorPlot) simulation_0.getPlotManager().getPlot("Cp_both Monitor Plot"));

    layoutView_0.getLayoutAssociationManager().createAssociation(layoutView_0.getRootLayoutPane().getPaneAt(1).getPaneAt(1), monitorPlot_2);

    LayoutView layoutView_1 = 
      simulation_0.get(LayoutViewManager.class).createLayoutView("Layout View", LayoutPane.Axis.LEFT_TO_RIGHT, 2, 2);

    layoutView_1.openInteractive();

    hardcopyProperties_15.setCurrentResolutionWidth(1065);

    hardcopyProperties_15.setCurrentResolutionHeight(518);

    LayoutViewUpdate layoutViewUpdate_1 = 
      layoutView_1.getLayoutViewUpdate();

    HardcopyProperties hardcopyProperties_16 = 
      layoutViewUpdate_1.getHardcopyProperties();

    hardcopyProperties_16.setCurrentResolutionWidth(1063);

    hardcopyProperties_16.setCurrentResolutionHeight(517);

    layoutView_1.setPresentationName("Scenes");

    Scene scene_7 = 
      simulation_0.getSceneManager().getScene("Velocity_Vector_detail");

    layoutView_1.getLayoutAssociationManager().createAssociation(layoutView_1.getRootLayoutPane().getPaneAt(0).getPaneAt(0), scene_7);

    Scene scene_5 = 
      simulation_0.getSceneManager().getScene("CpT_detail");

    layoutView_1.getLayoutAssociationManager().createAssociation(layoutView_1.getRootLayoutPane().getPaneAt(1).getPaneAt(0), scene_5);

    Scene scene_11 = 
      simulation_0.getSceneManager().getScene("Velocity_Scalar_detail");

    layoutView_1.getLayoutAssociationManager().createAssociation(layoutView_1.getRootLayoutPane().getPaneAt(0).getPaneAt(1), scene_11);

    Scene scene_12 = 
      simulation_0.getSceneManager().getScene("Velocity_Scalar_streamlines");

    layoutView_1.getLayoutAssociationManager().createAssociation(layoutView_1.getRootLayoutPane().getPaneAt(1).getPaneAt(1), scene_12);
  }
}
