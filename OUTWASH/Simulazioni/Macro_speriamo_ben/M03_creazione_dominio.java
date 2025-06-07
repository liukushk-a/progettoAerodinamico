// Simcenter STAR-CCM+ macro: M03_creazione_dominio.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.cadmodeler.*;

public class M03_creazione_dominio extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {0.5267413965563038, -0.22331623951554933, -0.02669436971909489}), new DoubleVector(new double[] {0.5267413965563038, -0.22331623951554933, 2.9897696554605506}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.5153366151594935, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.0079008269025493, -0.20711558529513696, -0.09411237571468867}), new DoubleVector(new double[] {1.0079008269025493, -0.20711558529513696, 2.9897696554605506}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 1.2003526258800465, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.184067221474543, -0.2036039628119743, -0.13881939679778865}), new DoubleVector(new double[] {1.184067221474543, -0.2036039628119743, 2.9897696554605506}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 1.4426217323219088, 1, 30.0);

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    CanonicalSketchPlane canonicalSketchPlane_0 = 
      ((CanonicalSketchPlane) cadModel_0.getFeature("XY"));

    Sketch sketch_0 = 
      cadModel_0.getFeatureManager().createSketch(canonicalSketchPlane_0);

    cadModel_0.allowMakingPartDirty(false);

    cadModel_0.getFeatureManager().startSketchEdit(sketch_0);

    cadModel_0.getGridSpacing().setValue(25.0);

    cadModel_0.getNumberOfFineGridDivisions().setValue(4.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.720385370989451, -0.28960009548339494, -0.42387813225263926}), new DoubleVector(new double[] {0.720385370989451, -0.28960009548339494, 1132.372617664048}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.9561605047680191, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {6.986648630626549, 0.38326898312229896, -0.5384261019389669}), new DoubleVector(new double[] {6.986648630626549, 0.38326898312229896, 1132.372617664048}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.94711686156729, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.010574227457422, -1.0384665606403087, -0.5384259880813715}), new DoubleVector(new double[] {2.010574227457422, -1.0384665606403087, 1132.828635117206}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 6.94711686156729, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.381013332196228, -0.9714188427539923, -0.5662635670919371}), new DoubleVector(new double[] {3.381013332196228, -0.9714188427539923, 1132.828635117206}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.246593773214894, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.9293578466543204, -0.9499151363046552, -0.5795482648470625}), new DoubleVector(new double[] {3.9293578466543204, -0.9499151363046552, 1132.828635117206}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 10.17124329764836, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.165898388460095, -0.24029351088732764, -0.5795486283498121}), new DoubleVector(new double[] {4.165898388460095, -0.24029351088732764, 1133.5077263822664}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 10.17124329764836, 1, 30.0);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    currentView_0.setInput(new DoubleVector(new double[] {4.165898388460095, -0.24029351088732764, -0.5795486283498121}), new DoubleVector(new double[] {4.165898388460095, -0.24029351088732764, 1133.5077263822664}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 10.17124329764836, 1, 30.0);

    sketch_0.createRectangle(new DoubleVector(new double[] {-6.25, 6.25}), new DoubleVector(new double[] {6.25, -6.25}));

    currentView_0.setInput(new DoubleVector(new double[] {4.165898388460095, -0.24029351088732764, -0.5795486283498121}), new DoubleVector(new double[] {4.165898388460095, -0.24029351088732764, 1133.5077263822664}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 10.17124329764836, 1, 30.0);

    sketch_0.setIsUptoDate(true);

    sketch_0.markFeatureForEdit();

    cadModel_0.allowMakingPartDirty(true);

    cadModel_0.getFeatureManager().stopSketchEdit(sketch_0, true);

    cadModel_0.getFeatureManager().updateModelAfterFeatureEdited(sketch_0, null);

    cadModel_0.allowMakingPartDirty(false);

    cadModel_0.getFeatureManager().updateModelForEditingFeature(sketch_0);

    cadModel_0.getFeatureManager().startSketchEdit(sketch_0);

    PointSketchPrimitive pointSketchPrimitive_0 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 4"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {1.75, 0.0}));

    cadModel_0.getFeatureManager().markDependentNotUptodate(sketch_0);

    sketch_0.markFeatureForEdit();

    cadModel_0.allowMakingPartDirty(true);

    cadModel_0.getFeatureManager().stopSketchEdit(sketch_0, false);

    cadModel_0.getFeatureManager().updateModelAfterFeatureEdited(sketch_0, null);

    ExtrusionMerge extrusionMerge_0 = 
      cadModel_0.getFeatureManager().createExtrusionMerge(sketch_0);

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

    extrusionMerge_0.setSketch(sketch_0);

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

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_0.setInput(new DoubleVector(new double[] {3.807770049928026, 0.12028625462197007, 3.1394042371100994}), new DoubleVector(new double[] {-248.19873128481134, 253.85179918252712, 1078.7568123914677}), new DoubleVector(new double[] {0.025535322798520804, 0.9742898851717938, -0.22384630205011732}), 10.17124329764836, 1, 30.0);

    simulation_0.get(SolidModelManager.class).endEditCadModel(cadModel_0);

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_0}));
  }
}
