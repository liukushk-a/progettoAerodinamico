// Simcenter STAR-CCM+ macro: M02_estrusione_profili.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;
import star.vis.*;

public class M02_estrusione_profili extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    Sketch3D sketch3D_0 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 1"));

    ExtrusionMerge extrusionMerge_0 = 
      cadModel_0.getFeatureManager().createExtrusionMerge(sketch3D_0);

    extrusionMerge_0.setAutoPreview(true);

    cadModel_0.allowMakingPartDirty(false);

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    extrusionMerge_0.setDirectionOption(2);

    extrusionMerge_0.setExtrudedBodyTypeOption(0);

    Units units_0 = 
      ((Units) simulation_0.getUnitsManager().getObject("m"));

    extrusionMerge_0.getDistance().setValueAndUnits(0.1, units_0);

    extrusionMerge_0.getDistanceAsymmetric().setValueAndUnits(0.1, units_0);

    extrusionMerge_0.getOffsetDistance().setValueAndUnits(0.1, units_0);

    extrusionMerge_0.setDistanceOption(0);

    extrusionMerge_0.setCoordinateSystemOption(1);

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject("deg"));

    extrusionMerge_0.getDraftAngle().setValueAndUnits(10.0, units_1);

    extrusionMerge_0.setDraftOption(0);

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    extrusionMerge_0.setImportedCoordinateSystem(labCoordinateSystem_0);

    extrusionMerge_0.getDirectionAxis().setCoordinateSystem(labCoordinateSystem_0);

    extrusionMerge_0.getDirectionAxis().setUnits0(units_0);

    extrusionMerge_0.getDirectionAxis().setUnits1(units_0);

    extrusionMerge_0.getDirectionAxis().setUnits2(units_0);

    extrusionMerge_0.getDirectionAxis().setDefinition("");

    extrusionMerge_0.getDirectionAxis().setValue(new DoubleVector(new double[] {0.0, 0.0, 1.0}));

    extrusionMerge_0.setFace(null);

    extrusionMerge_0.setBody(null);

    extrusionMerge_0.setFeatureInputType(0);

    extrusionMerge_0.setInputFeatureEdges(new NeoObjectVector(new Object[] {}));

    extrusionMerge_0.setSketch(sketch3D_0);

    extrusionMerge_0.setInteractingBodies(new NeoObjectVector(new Object[] {}));

    extrusionMerge_0.setInteractingBodiesBodyGroups(new NeoObjectVector(new Object[] {}));

    extrusionMerge_0.setInteractingBodiesCadFilters(new NeoObjectVector(new Object[] {}));

    extrusionMerge_0.setInteractingSelectedBodies(false);

    extrusionMerge_0.setPostOption(1);

    extrusionMerge_0.setExtrusionOption(0);

    extrusionMerge_0.setIsBodyGroupCreation(false);

    cadModel_0.getFeatureManager().markDependentNotUptodate(extrusionMerge_0);

    cadModel_0.allowMakingPartDirty(true);

    extrusionMerge_0.markFeatureForEdit();

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.USE_DISPLAYER_PROPERTY);

    cadModel_0.getFeatureManager().execute(extrusionMerge_0);

    Sketch3D sketch3D_1 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 2"));

    ExtrusionMerge extrusionMerge_1 = 
      cadModel_0.getFeatureManager().createExtrusionMerge(sketch3D_1);

    extrusionMerge_1.setAutoPreview(true);

    cadModel_0.allowMakingPartDirty(false);

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    extrusionMerge_1.setDirectionOption(2);

    extrusionMerge_1.setExtrudedBodyTypeOption(0);

    extrusionMerge_1.getDistance().setValueAndUnits(0.1, units_0);

    extrusionMerge_1.getDistanceAsymmetric().setValueAndUnits(0.1, units_0);

    extrusionMerge_1.getOffsetDistance().setValueAndUnits(0.1, units_0);

    extrusionMerge_1.setDistanceOption(0);

    extrusionMerge_1.setCoordinateSystemOption(1);

    extrusionMerge_1.getDraftAngle().setValueAndUnits(10.0, units_1);

    extrusionMerge_1.setDraftOption(0);

    extrusionMerge_1.setImportedCoordinateSystem(labCoordinateSystem_0);

    extrusionMerge_1.getDirectionAxis().setCoordinateSystem(labCoordinateSystem_0);

    extrusionMerge_1.getDirectionAxis().setUnits0(units_0);

    extrusionMerge_1.getDirectionAxis().setUnits1(units_0);

    extrusionMerge_1.getDirectionAxis().setUnits2(units_0);

    extrusionMerge_1.getDirectionAxis().setDefinition("");

    extrusionMerge_1.getDirectionAxis().setValue(new DoubleVector(new double[] {0.0, 0.0, 1.0}));

    extrusionMerge_1.setFace(null);

    extrusionMerge_1.setBody(null);

    extrusionMerge_1.setFeatureInputType(0);

    extrusionMerge_1.setInputFeatureEdges(new NeoObjectVector(new Object[] {}));

    extrusionMerge_1.setSketch(sketch3D_1);

    extrusionMerge_1.setInteractingBodies(new NeoObjectVector(new Object[] {}));

    extrusionMerge_1.setInteractingBodiesBodyGroups(new NeoObjectVector(new Object[] {}));

    extrusionMerge_1.setInteractingBodiesCadFilters(new NeoObjectVector(new Object[] {}));

    extrusionMerge_1.setInteractingSelectedBodies(false);

    extrusionMerge_1.setPostOption(1);

    extrusionMerge_1.setExtrusionOption(0);

    extrusionMerge_1.setIsBodyGroupCreation(false);

    cadModel_0.getFeatureManager().markDependentNotUptodate(extrusionMerge_1);

    cadModel_0.allowMakingPartDirty(true);

    extrusionMerge_1.markFeatureForEdit();

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.USE_DISPLAYER_PROPERTY);

    cadModel_0.getFeatureManager().execute(extrusionMerge_1);

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {0.7186800649999999, -0.22684185499999998, 0.0}), new DoubleVector(new double[] {0.7186800649999999, -0.22684185499999998, 2.9897696554605506}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7804864974846424, 1, 30.0);
  }
}
