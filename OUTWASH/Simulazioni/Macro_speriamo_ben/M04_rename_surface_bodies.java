// Simcenter STAR-CCM+ macro: M04_rename_surface_bodies.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;
import star.vis.*;

public class M04_rename_surface_bodies extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    ExtrusionMerge extrusionMerge_1 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 1"));

    Sketch3D sketch3D_0 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 1"));

    LineSketchPrimitive3D lineSketchPrimitive3D_0 = 
      ((LineSketchPrimitive3D) sketch3D_0.getSketchPrimitive3D("Line 1"));

    star.cadmodeler.Body cadmodelerBody_0 = 
      ((star.cadmodeler.Body) extrusionMerge_1.getBody(lineSketchPrimitive3D_0));

    cadmodelerBody_0.setPresentationName("Flap1");

    ExtrusionMerge extrusionMerge_2 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 2"));

    Sketch3D sketch3D_1 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 2"));

    LineSketchPrimitive3D lineSketchPrimitive3D_1 = 
      ((LineSketchPrimitive3D) sketch3D_1.getSketchPrimitive3D("Line 1"));

    star.cadmodeler.Body cadmodelerBody_1 = 
      ((star.cadmodeler.Body) extrusionMerge_2.getBody(lineSketchPrimitive3D_1));

    cadmodelerBody_1.setPresentationName("Flap2");

    ExtrusionMerge extrusionMerge_0 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 3"));

    Sketch sketch_0 = 
      ((Sketch) cadModel_0.getFeature("Sketch 1"));

    LineSketchPrimitive lineSketchPrimitive_0 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 1"));

    star.cadmodeler.Body cadmodelerBody_2 = 
      ((star.cadmodeler.Body) extrusionMerge_0.getBody(lineSketchPrimitive_0));

    cadmodelerBody_2.setPresentationName("Domain");

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {6.132975318660339, 0.3594350128338098, -1.024858489255501}), new DoubleVector(new double[] {-561.2230274007142, -44.76083268341545, 979.0718445182176}), new DoubleVector(new double[] {-0.010687167689260571, 0.9991500788486554, 0.03981085760831466}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.401194282383502, 0.43299152731755475, 0.2522167150139234}), new DoubleVector(new double[] {-494.7305770526952, 448.5976616353958, 912.1330876549993}), new DoubleVector(new double[] {0.2034470191909346, 0.918260133080914, -0.33971699747958906}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.401194282383502, 0.43299152731755475, 0.2522167150139234}), new DoubleVector(new double[] {-494.7305770526952, 448.5976616353958, 912.1330876549993}), new DoubleVector(new double[] {0.2034470191909346, 0.918260133080914, -0.33971699747958906}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.034232842818913, 0.25932515500066683, 0.9312433077324371}), new DoubleVector(new double[] {-344.8333509862714, -850.1722404774654, 661.8918384919947}), new DoubleVector(new double[] {-0.34157979238156094, 0.6603231802783907, 0.668802319840181}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.034232842818913, 0.25932515500066683, 0.9312433077324371}), new DoubleVector(new double[] {-344.8333509862714, -850.1722404774654, 661.8918384919947}), new DoubleVector(new double[] {-0.34157979238156094, 0.6603231802783907, 0.668802319840181}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.034232842818913, 0.25932515500066683, 0.9312433077324371}), new DoubleVector(new double[] {-344.8333509862714, -850.1722404774654, 661.8918384919947}), new DoubleVector(new double[] {-0.34157979238156094, 0.6603231802783907, 0.668802319840181}), 9.498432929747318, 1, 30.0);

    Face face_0 = 
      ((Face) extrusionMerge_0.getSideFace(lineSketchPrimitive_0,"True"));

    LineSketchPrimitive lineSketchPrimitive_1 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 4"));

    Face face_1 = 
      ((Face) extrusionMerge_0.getSideFace(lineSketchPrimitive_1,"True"));

    LineSketchPrimitive lineSketchPrimitive_2 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 2"));

    Face face_2 = 
      ((Face) extrusionMerge_0.getSideFace(lineSketchPrimitive_2,"True"));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_0, face_1, face_2}), "inlet", false);

    currentView_0.setInput(new DoubleVector(new double[] {4.086886388147673, -0.19790163507442093, 2.407185726028536}), new DoubleVector(new double[] {-624.1702638965537, 378.1518520458513, 865.3408449343325}), new DoubleVector(new double[] {0.2402741623401053, 0.9411489888632023, -0.23771181643650452}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.086886388147673, -0.19790163507442093, 2.407185726028536}), new DoubleVector(new double[] {-624.1702638965537, 378.1518520458513, 865.3408449343325}), new DoubleVector(new double[] {0.2402741623401053, 0.9411489888632023, -0.23771181643650452}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.5181781075733056, 1.424696037408265, 9.50696585108954}), new DoubleVector(new double[] {194.02287287557837, 74.83099643660123, 1123.2607767649422}), new DoubleVector(new double[] {0.20988869138054336, 0.9725782808679448, -0.10019094177882036}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.5181781075733056, 1.424696037408265, 9.50696585108954}), new DoubleVector(new double[] {194.02287287557837, 74.83099643660123, 1123.2607767649422}), new DoubleVector(new double[] {0.20988869138054336, 0.9725782808679448, -0.10019094177882036}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.5181781075733056, 1.424696037408265, 9.50696585108954}), new DoubleVector(new double[] {194.02287287557837, 74.83099643660123, 1123.2607767649422}), new DoubleVector(new double[] {0.20988869138054336, 0.9725782808679448, -0.10019094177882036}), 9.498432929747318, 1, 30.0);

    LineSketchPrimitive lineSketchPrimitive_3 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 3"));

    Face face_3 = 
      ((Face) extrusionMerge_0.getSideFace(lineSketchPrimitive_3,"True"));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_3}), "outlet", false);

    currentView_0.setInput(new DoubleVector(new double[] {2.5181781075733056, 1.424696037408265, 9.50696585108954}), new DoubleVector(new double[] {194.02287287557837, 74.83099643660123, 1123.2607767649422}), new DoubleVector(new double[] {0.20988869138054336, 0.9725782808679448, -0.10019094177882036}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.014006598094134, 0.9988221949036998, -3.689822977505111}), new DoubleVector(new double[] {-699.061791737134, -41.170730758076935, 888.7647606061657}), new DoubleVector(new double[] {0.14390525778193947, 0.9766502807159789, 0.15951647551320447}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.184090903574891, 0.9560292104882024, -5.721233980115461}), new DoubleVector(new double[] {-207.5820993554815, 12.92565898530545, 1108.2820126665238}), new DoubleVector(new double[] {0.2037239996018431, 0.9786217974188905, 0.02821186989351799}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.159168595210595, 1.0035792256894611, -0.3640382822948167}), new DoubleVector(new double[] {-119.51132017602811, 148.58928834569355, 1111.5921102498644}), new DoubleVector(new double[] {0.2235695882081509, 0.9691274532819963, -0.10396450607608085}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.159168595210595, 1.0035792256894611, -0.3640382822948167}), new DoubleVector(new double[] {-119.51132017602811, 148.58928834569355, 1111.5921102498644}), new DoubleVector(new double[] {0.2235695882081509, 0.9691274532819963, -0.10396450607608085}), 9.498432929747318, 1, 30.0);

    Face face_4 = 
      ((Face) extrusionMerge_0.getEndCapFace(lineSketchPrimitive_0));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_4}), "1", false);

    currentView_0.setInput(new DoubleVector(new double[] {15.778242271481897, 3.5905158693762322, 12.367353227985193}), new DoubleVector(new double[] {1134.1307330476434, 55.856868171303866, -125.55486892101109}), new DoubleVector(new double[] {-0.09336251608139318, 0.9055306587154475, -0.4138812229581976}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {15.778242271481897, 3.5905158693762322, 12.367353227985193}), new DoubleVector(new double[] {1134.1307330476434, 55.856868171303866, -125.55486892101109}), new DoubleVector(new double[] {-0.09336251608139318, 0.9055306587154475, -0.4138812229581976}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {15.778242271481897, 3.5905158693762322, 12.367353227985193}), new DoubleVector(new double[] {1134.1307330476434, 55.856868171303866, -125.55486892101109}), new DoubleVector(new double[] {-0.09336251608139318, 0.9055306587154475, -0.4138812229581976}), 9.498432929747318, 1, 30.0);

    Face face_5 = 
      ((Face) extrusionMerge_0.getStartCapFace(lineSketchPrimitive_0));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_5}), "2", false);

    currentView_0.setInput(new DoubleVector(new double[] {7.466730726723006, 2.9564550494436896, 12.461758605126008}), new DoubleVector(new double[] {573.0671073988991, 295.7095961835158, 951.7541470231624}), new DoubleVector(new double[] {0.07153510423643498, 0.9392118312666954, -0.33580331277489367}), 9.498432929747318, 1, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {7.466730726723006, 2.9564550494436896, 12.461758605126008}), new DoubleVector(new double[] {573.0671073988991, 295.7095961835158, 951.7541470231624}), new DoubleVector(new double[] {0.07153510423643498, 0.9392118312666954, -0.33580331277489367}), 9.498432929747318, 1, 30.0);

    cadmodelerBody_0.setUnNamedFacesDefaultAttributeName("Flap1");

    cadmodelerBody_0.getUnNamedEdgesDefaultAttributeName();

    cadmodelerBody_0.getUnNamedEdgesDefaultAttributeName();

    cadmodelerBody_1.setUnNamedFacesDefaultAttributeName("Flap2");

    scene_0.setViewOrientation(new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    currentView_0.setInput(new DoubleVector(new double[] {2.7690038891297366, -0.2577508437080046, 4.999999525644398}), new DoubleVector(new double[] {2.7690038891297366, -0.2577508437080046, 1151.0248583696132}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 9.498432929747318, 1, 30.0);

    simulation_0.get(SolidModelManager.class).endEditCadModel(cadModel_0);

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_0}));
  }
}
