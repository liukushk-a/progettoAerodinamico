// Simcenter STAR-CCM+ macro: Rename_Bodies_Surfaces_04.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.cadmodeler.*;
import star.vis.*;

public class Rename_Bodies_Surfaces_04 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    CadModel cadModel_1 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    ExtrusionMerge extrusionMerge_0 = 
      ((ExtrusionMerge) cadModel_1.getFeature("Extrude 1"));

    Sketch3D sketch3D_0 = 
      ((Sketch3D) cadModel_1.getFeature("Sketch3D 1"));

    LineSketchPrimitive3D lineSketchPrimitive3D_0 = 
      ((LineSketchPrimitive3D) sketch3D_0.getSketchPrimitive3D("Line 1"));

    star.cadmodeler.Body cadmodelerBody_1 = 
      ((star.cadmodeler.Body) extrusionMerge_0.getBody(lineSketchPrimitive3D_0));

    cadmodelerBody_1.setPresentationName("Flap1");

    ExtrusionMerge extrusionMerge_1 = 
      ((ExtrusionMerge) cadModel_1.getFeature("Extrude 2"));

    Sketch3D sketch3D_1 = 
      ((Sketch3D) cadModel_1.getFeature("Sketch3D 2"));

    LineSketchPrimitive3D lineSketchPrimitive3D_1 = 
      ((LineSketchPrimitive3D) sketch3D_1.getSketchPrimitive3D("Line 1"));

    star.cadmodeler.Body cadmodelerBody_2 = 
      ((star.cadmodeler.Body) extrusionMerge_1.getBody(lineSketchPrimitive3D_1));

    cadmodelerBody_2.setPresentationName("Flap2");

    ExtrusionMerge extrusionMerge_2 = 
      ((ExtrusionMerge) cadModel_1.getFeature("Extrude 3"));

    Sketch sketch_0 = 
      ((Sketch) cadModel_1.getFeature("Sketch 1"));

    LineSketchPrimitive lineSketchPrimitive_2 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 1"));

    star.cadmodeler.Body cadmodelerBody_0 = 
      ((star.cadmodeler.Body) extrusionMerge_2.getBody(lineSketchPrimitive_2));

    cadmodelerBody_0.setPresentationName("Domain");

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    CurrentView currentView_0 = 
      scene_1.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {38.078109855929256, 12.759803140752124, 25.385396991165948}), new DoubleVector(new double[] {-50.609158389492876, 64.71008532717133, 99.72350289376783}), new DoubleVector(new double[] {0.30938204750231213, 0.9122666176505613, -0.268425719713457}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {30.411581484011812, 13.975865509312863, 30.454258175149256}), new DoubleVector(new double[] {-80.09760667179343, -11.895914079202342, 85.53143081547388}), new DoubleVector(new double[] {-0.19846537941760223, 0.9781901337680518, 0.06128258620093526}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {30.411581484011812, 13.975865509312863, 30.454258175149256}), new DoubleVector(new double[] {-80.09760667179343, -11.895914079202342, 85.53143081547388}), new DoubleVector(new double[] {-0.19846537941760223, 0.9781901337680518, 0.06128258620093526}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.86333902338677, 4.773866525434322, 33.47877747171958}), new DoubleVector(new double[] {-22.677572902829162, -115.33677524737408, 36.852590888418405}), new DoubleVector(new double[] {-0.8850061436733586, 0.33720620879342383, 0.32102351691361375}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.86333902338677, 4.773866525434322, 33.47877747171958}), new DoubleVector(new double[] {-22.677572902829162, -115.33677524737408, 36.852590888418405}), new DoubleVector(new double[] {-0.8850061436733586, 0.33720620879342383, 0.32102351691361375}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.86333902338677, 4.773866525434322, 33.47877747171958}), new DoubleVector(new double[] {-22.677572902829162, -115.33677524737408, 36.852590888418405}), new DoubleVector(new double[] {-0.8850061436733586, 0.33720620879342383, 0.32102351691361375}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.86333902338677, 4.773866525434322, 33.47877747171958}), new DoubleVector(new double[] {-22.677572902829162, -115.33677524737408, 36.852590888418405}), new DoubleVector(new double[] {-0.8850061436733586, 0.33720620879342383, 0.32102351691361375}), 0.764504069283022, 0, 30.0);

    LineSketchPrimitive lineSketchPrimitive_1 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 4"));

    Face face_1 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_1,"True"));

    LineSketchPrimitive lineSketchPrimitive_0 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 3"));

    Face face_0 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_0,"True"));

    LineSketchPrimitive lineSketchPrimitive_3 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 2"));

    Face face_3 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_3,"True"));

    cadModel_1.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_1, face_0, face_3}), "inlet", false);

    currentView_0.setInput(new DoubleVector(new double[] {21.99576851939039, 16.293115739134755, 27.091769053409166}), new DoubleVector(new double[] {64.98852789212629, -50.13635030716977, 128.02133117212634}), new DoubleVector(new double[] {-0.8869494387838311, 0.10818568568134541, 0.4490173164307979}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.99576851939039, 16.293115739134755, 27.091769053409166}), new DoubleVector(new double[] {64.98852789212629, -50.13635030716977, 128.02133117212634}), new DoubleVector(new double[] {-0.8869494387838311, 0.10818568568134541, 0.4490173164307979}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.99576851939039, 16.293115739134755, 27.091769053409166}), new DoubleVector(new double[] {64.98852789212629, -50.13635030716977, 128.02133117212634}), new DoubleVector(new double[] {-0.8869494387838311, 0.10818568568134541, 0.4490173164307979}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.99576851939039, 16.293115739134755, 27.091769053409166}), new DoubleVector(new double[] {64.98852789212629, -50.13635030716977, 128.02133117212634}), new DoubleVector(new double[] {-0.8869494387838311, 0.10818568568134541, 0.4490173164307979}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.99576851939039, 16.293115739134755, 27.091769053409166}), new DoubleVector(new double[] {64.98852789212629, -50.13635030716977, 128.02133117212634}), new DoubleVector(new double[] {-0.8869494387838311, 0.10818568568134541, 0.4490173164307979}), 0.764504069283022, 0, 30.0);

    Face face_2 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_2,"True"));

    cadModel_1.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_2}), "outlet", false);

    currentView_0.setInput(new DoubleVector(new double[] {21.99576851939039, 16.293115739134755, 27.091769053409166}), new DoubleVector(new double[] {64.98852789212629, -50.13635030716977, 128.02133117212634}), new DoubleVector(new double[] {-0.8869494387838311, 0.10818568568134541, 0.4490173164307979}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.99576851939039, 16.293115739134755, 27.091769053409166}), new DoubleVector(new double[] {64.98852789212629, -50.13635030716977, 128.02133117212634}), new DoubleVector(new double[] {-0.8869494387838311, 0.10818568568134541, 0.4490173164307979}), 0.764504069283022, 0, 30.0);

    Face face_4 = 
      ((Face) extrusionMerge_2.getEndCapFace(lineSketchPrimitive_2));

    cadModel_1.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_4}), "1", false);

    currentView_0.setInput(new DoubleVector(new double[] {14.201359319867088, 24.19235131047421, 8.443853890034902}), new DoubleVector(new double[] {33.357644073176736, 156.84342165518697, 67.11040226522343}), new DoubleVector(new double[] {-0.9718217919980282, 0.03741484947668452, 0.23272845472006845}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.411206586147367, -6.3216990521322725, 8.114881860616421}), new DoubleVector(new double[] {31.759385224770476, 121.18113446795248, -70.57305417722505}), new DoubleVector(new double[] {-0.9306468014643497, 0.295938437430691, 0.2152137824937545}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.411206586147367, -6.3216990521322725, 8.114881860616421}), new DoubleVector(new double[] {31.759385224770476, 121.18113446795248, -70.57305417722505}), new DoubleVector(new double[] {-0.9306468014643497, 0.295938437430691, 0.2152137824937545}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.411206586147367, -6.3216990521322725, 8.114881860616421}), new DoubleVector(new double[] {31.759385224770476, 121.18113446795248, -70.57305417722505}), new DoubleVector(new double[] {-0.9306468014643497, 0.295938437430691, 0.2152137824937545}), 0.764504069283022, 0, 30.0);

    Face face_5 = 
      ((Face) extrusionMerge_2.getStartCapFace(lineSketchPrimitive_2));

    cadModel_1.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_5}), "2", false);

    currentView_0.setInput(new DoubleVector(new double[] {17.369954625261105, 4.9871479899421844, 3.530607961143186}), new DoubleVector(new double[] {148.88076354731393, 76.78062766604043, 6.880138734014135}), new DoubleVector(new double[] {-0.4790849355975579, 0.8777604498858095, -0.0037705569559828003}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {16.656512717776813, 4.61037567431686, 3.446053372042652}), new DoubleVector(new double[] {147.83714352617338, 71.54558391229432, 31.229791621006996}), new DoubleVector(new double[] {-0.454362940526913, 0.8908162614589061, -8.405927251497738E-4}), 0.764504069283022, 0, 30.0);
  }
}
