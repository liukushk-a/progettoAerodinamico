// Simcenter STAR-CCM+ macro: M19_Streamlines.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;

public class M19_Streamlines extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_12 = 
      simulation_0.getSceneManager().createScene("Velocity_Scalar_detail");

    Scene scene_11 = 
      simulation_0.getSceneManager().getScene("Velocity_Scalar_detail");

    scene_12.copyProperties(scene_11);

    scene_12.setPresentationName("Copy of Velocity_Scalar_detail");

    scene_12.initializeAndWait();

    scene_12.setPresentationName("Velocity_Scalar_streamlines");

    scene_12.openInteractive();

    CurrentView currentView_9 = 
      scene_12.getCurrentView();

    currentView_9.setInput(new DoubleVector(new double[] {1.9123941068044958, -1.3810747589554748, -1.1459069071406702E-7}), new DoubleVector(new double[] {1.9123941068044958, -1.3810747589554748, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.325359351892381, 1, 30.0);

    currentView_9.setInput(new DoubleVector(new double[] {2.1998031253459334, -1.362633804714162, -0.11307732696960926}), new DoubleVector(new double[] {2.1998031253459334, -1.362633804714162, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.557244440234902, 1, 30.0);

    currentView_9.setInput(new DoubleVector(new double[] {2.1998031253459334, -1.2439222639682477, -1.1459069071406702E-7}), new DoubleVector(new double[] {2.1998031253459334, -1.2439222639682477, 36.619291443098575}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 2.557244440234902, 1, 30.0);

    Units units_3 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    scene_12.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_12.getCreatorGroup().setQuery(null);

    Region region_0 = 
      simulation_0.getRegionManager().getRegion("Region");

    scene_12.getCreatorGroup().setObjects(region_0);

    scene_12.getCreatorGroup().setQuery(null);

    scene_12.getCreatorGroup().setObjects(region_0);

    scene_12.getCreatorGroup().setQuery(null);

    scene_12.getCreatorGroup().setObjects(region_0);

    StreamDisplayer streamDisplayer_0 = 
      scene_12.getDisplayerManager().createStreamDisplayer("Streamline Stream", ClipMode.NONE);

    Legend legend_4 = 
      streamDisplayer_0.getLegend();

    PredefinedLookupTable predefinedLookupTable_0 = 
      ((PredefinedLookupTable) simulation_0.get(LookupTableManager.class).getObject("blue-yellow-red"));

    legend_4.setLookupTable(predefinedLookupTable_0);

    scene_12.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    Boundary boundary_0 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Domain.inlet");

    Boundary boundary_1 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Domain.outlet");

    Boundary boundary_2 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Flap1.Default");

    Boundary boundary_3 = 
      region_0.getBoundaryManager().getBoundary("Subtract.Flap2.Default");

    PrimitiveFieldFunction primitiveFieldFunction_0 = 
      ((PrimitiveFieldFunction) simulation_0.getFieldFunctionManager().getFunction("Velocity"));

    StreamPart streamPart_1 = 
      simulation_0.getPartManager().createStreamPart(new NeoObjectVector(new Object[] {region_0}), new NeoObjectVector(new Object[] {boundary_0, boundary_1, boundary_2, boundary_3}), primitiveFieldFunction_0, 100, 100, 0);

    streamDisplayer_0.getVisibleParts().addParts(streamPart_1);

    streamDisplayer_0.getHiddenParts().addParts();

    scene_12.setTransparencyOverrideMode(SceneTransparencyOverride.USE_DISPLAYER_PROPERTY);

    legend_4.setShadow(false);

    legend_4.setShadow(true);

    legend_4.setVisible(false);

    scene_12.closeInteractive();
  }
}
