// Simcenter STAR-CCM+ macro: Domain_Creation_03.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;
import star.vis.*;

public class Domain_Creation_03 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    CanonicalSketchPlane canonicalSketchPlane_0 = 
      ((CanonicalSketchPlane) cadModel_0.getFeature("XY"));

    Sketch sketch_1 = 
      cadModel_0.getFeatureManager().createSketch(canonicalSketchPlane_0);

    cadModel_0.allowMakingPartDirty(false);

    cadModel_0.getFeatureManager().startSketchEdit(sketch_1);

    cadModel_0.getGridSpacing().setValue(25.0);

    cadModel_0.getNumberOfFineGridDivisions().setValue(4.0);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {7.8465225962632426, -0.378824526819597, -0.9999998888463963}), new DoubleVector(new double[] {7.8465225962632426, -0.378824526819597, 43.09228110694646}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738093273027922, 0, 30.0);

    sketch_1.createRectangle(new DoubleVector(new double[] {-6.25, 6.25}), new DoubleVector(new double[] {6.25, -6.25}));

    currentView_0.setInput(new DoubleVector(new double[] {7.8465225962632426, -0.378824526819597, -0.9999998888463963}), new DoubleVector(new double[] {7.8465225962632426, -0.378824526819597, 43.09228110694646}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738093273027922, 0, 30.0);

    PointSketchPrimitive pointSketchPrimitive_1 = 
      ((PointSketchPrimitive) sketch_1.getSketchPrimitive("Point 4"));

    sketch_1.translateSketchPrimitive(pointSketchPrimitive_1, new DoubleVector(new double[] {1.75, 0.0}));

    sketch_1.setIsUptoDate(true);

    sketch_1.markFeatureForEdit();

    cadModel_0.allowMakingPartDirty(true);

    cadModel_0.getFeatureManager().stopSketchEdit(sketch_1, true);

    cadModel_0.getFeatureManager().updateModelAfterFeatureEdited(sketch_1, null);

    ExtrusionMerge extrusionMerge_0 = 
      cadModel_0.getFeatureManager().createExtrusionMerge(sketch_1);

    extrusionMerge_0.setAutoPreview(true);

    cadModel_0.allowMakingPartDirty(false);

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    extrusionMerge_0.setDirectionOption(0);

    extrusionMerge_0.setExtrudedBodyTypeOption(0);

    extrusionMerge_0.getDistance().setValueAndUnits(5.0, units_0);

    extrusionMerge_0.getDistanceAsymmetric().setValueAndUnits(0.1, units_0);

    extrusionMerge_0.getOffsetDistance().setValueAndUnits(0.1, units_0);

    extrusionMerge_0.setDistanceOption(0);

    extrusionMerge_0.setCoordinateSystemOption(0);

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

    extrusionMerge_0.setSketch(sketch_1);

    extrusionMerge_0.setInteractingBodies(new NeoObjectVector(new Object[] {}));

    extrusionMerge_0.setInteractingBodiesBodyGroups(new NeoObjectVector(new Object[] {}));

    extrusionMerge_0.setInteractingBodiesCadFilters(new NeoObjectVector(new Object[] {}));

    extrusionMerge_0.setInteractingSelectedBodies(false);

    extrusionMerge_0.setPostOption(0);

    extrusionMerge_0.setExtrusionOption(0);

    extrusionMerge_0.setIsBodyGroupCreation(false);

    cadModel_0.getFeatureManager().markDependentNotUptodate(extrusionMerge_0);

    cadModel_0.allowMakingPartDirty(true);

    extrusionMerge_0.markFeatureForEdit();

    cadModel_0.getFeatureManager().execute(extrusionMerge_0);

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.USE_DISPLAYER_PROPERTY);
  }
}
