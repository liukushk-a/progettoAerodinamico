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

    Sketch sketch_0 = 
      cadModel_0.getFeatureManager().createSketch(canonicalSketchPlane_0);

    cadModel_0.allowMakingPartDirty(false);

    cadModel_0.getFeatureManager().startSketchEdit(sketch_0);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {0.729529195, -0.20769293, 0.0}), new DoubleVector(new double[] {0.729529195, -0.20769293, 2.9907922802457882}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    sketch_0.createRectangle(new DoubleVector(new double[] {-0.84, 0.41000000000000003}), new DoubleVector(new double[] {1.84, -0.8}));

    currentView_0.setInput(new DoubleVector(new double[] {0.729529195, -0.20769293, 0.0}), new DoubleVector(new double[] {0.729529195, -0.20769293, 2.9907922802457882}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.8397958636766565, -0.267335310269344, -0.25478114645045125}), new DoubleVector(new double[] {0.8397958636766565, -0.267335310269344, 2.6917130299407424}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.731162417725359, -0.20857632742208085, -0.5053447518725611}), new DoubleVector(new double[] {0.731162417725359, -0.20857632742208085, 2.986362446333657}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.6024276586890056, -0.13894470125185998, -0.8022724401224934}), new DoubleVector(new double[] {0.6024276586890056, -0.13894470125185998, 3.335533194691852}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.4498720881013853, -0.05642858407528471, -1.1541427962987956}), new DoubleVector(new double[] {0.4498720881013853, -0.05642858407528471, 3.749313770715212}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.2747709969234767, 0.038282229182654684, -1.4038717998254358}), new DoubleVector(new double[] {0.2747709969234767, 0.038282229182654684, 4.224245168440215}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.08215979611278945, 0.1424641240449409, -1.4442589743104737}), new DoubleVector(new double[] {0.08215979611278945, 0.1424641240449409, 4.746669707334535}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    PointSketchPrimitive pointSketchPrimitive_0 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 4"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {-3.84, 0.0}));

    PointSketchPrimitive pointSketchPrimitive_1 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 1"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_1, new DoubleVector(new double[] {3.84, 0.0}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {0.0, 1.5899999999999999}));

    PointSketchPrimitive pointSketchPrimitive_2 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 3"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_2, new DoubleVector(new double[] {0.0, -1.2000000000000002}));

    currentView_0.setInput(new DoubleVector(new double[] {-0.22895383321877616, 0.02235063423971617, -1.9067392054621486}), new DoubleVector(new double[] {-0.22895383321877616, 0.02235063423971617, 4.172002717723537}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.5089560769142951, -0.08575149781994616, -1.8160648182743686}), new DoubleVector(new double[] {-0.5089560769142951, -0.08575149781994616, 3.654802469008804}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.2569540391318684, 0.011540428159345209, -1.7332794346585132}), new DoubleVector(new double[] {-0.2569540391318684, 0.011540428159345209, 4.1202827269435245}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.3251706523862112, 0.23628477108721138, -1.8872679392447447}), new DoubleVector(new double[] {0.3251706523862112, 0.23628477108721138, 5.195542093663222}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.0295415339123115, 0.5082254278789337, -2.073594355153384}), new DoubleVector(new double[] {1.0295415339123115, 0.5082254278789337, 6.496605936240369}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.435393336262477, 0.6649150477736178, -2.1809538607232337}), new DoubleVector(new double[] {1.435393336262477, 0.6649150477736178, 7.246266547335635}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.8818303002955896, 0.8372736224952626, -2.299048832660599}), new DoubleVector(new double[] {1.8818303002955896, 0.8372736224952626, 8.070893185272363}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.3729109699604294, 1.0268680582519412, -2.4289539300372187}), new DoubleVector(new double[] {2.3729109699604294, 1.0268680582519412, 8.977982504048837}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.913099712851852, 1.2354219400011612, -2.5718494566395247}), new DoubleVector(new double[] {2.913099712851852, 1.2354219400011612, 9.975780766266169}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.5073073253171594, 1.4648312081048558, -2.729034289673619}), new DoubleVector(new double[] {3.5073073253171594, 1.4648312081048558, 11.073358845995545}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.5073073253171594, 1.4648312081048558, -2.729034289673619}), new DoubleVector(new double[] {3.5073073253171594, 1.4648312081048558, 11.073358845995545}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.5073073253171594, 1.4648312081048558, -2.729034289673619}), new DoubleVector(new double[] {3.5073073253171594, 1.4648312081048558, 11.073358845995545}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7740740020732055, 0, 30.0);

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {-1.0000000000000002, 0.0}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_1, new DoubleVector(new double[] {1.0, 0.0}));

    sketch_0.setIsUptoDate(true);

    sketch_0.markFeatureForEdit();

    cadModel_0.allowMakingPartDirty(true);

    cadModel_0.getFeatureManager().stopSketchEdit(sketch_0, true);

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
  }
}
