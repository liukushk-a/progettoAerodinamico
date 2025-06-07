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

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    ExtrusionMerge extrusionMerge_0 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 1"));

    Sketch3D sketch3D_0 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 1"));

    LineSketchPrimitive3D lineSketchPrimitive3D_0 = 
      ((LineSketchPrimitive3D) sketch3D_0.getSketchPrimitive3D("Line 1"));

    star.cadmodeler.Body cadmodelerBody_0 = 
      ((star.cadmodeler.Body) extrusionMerge_0.getBody(lineSketchPrimitive3D_0));

    cadmodelerBody_0.setPresentationName("Flap1");

    ExtrusionMerge extrusionMerge_1 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 3"));

    Sketch sketch_0 = 
      ((Sketch) cadModel_0.getFeature("Sketch 1"));

    LineSketchPrimitive lineSketchPrimitive_0 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 1"));

    star.cadmodeler.Body cadmodelerBody_1 = 
      ((star.cadmodeler.Body) extrusionMerge_1.getBody(lineSketchPrimitive_0));

    cadmodelerBody_1.setPresentationName("Domain");

    ExtrusionMerge extrusionMerge_2 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 2"));

    Sketch3D sketch3D_1 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 2"));

    LineSketchPrimitive3D lineSketchPrimitive3D_1 = 
      ((LineSketchPrimitive3D) sketch3D_1.getSketchPrimitive3D("Line 1"));

    star.cadmodeler.Body cadmodelerBody_2 = 
      ((star.cadmodeler.Body) extrusionMerge_2.getBody(lineSketchPrimitive3D_1));

    cadmodelerBody_2.setPresentationName("Flap2");

    Scene scene_0 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    CurrentView currentView_0 = 
      scene_0.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {4.026792113432505, 2.6103553153116392, 12.296902053438808}), new DoubleVector(new double[] {-19.140071053960853, 20.739885840215095, 43.78596755979206}), new DoubleVector(new double[] {0.13068450285797092, 0.8977311400953906, -0.42071410817299215}), 0.7738093273027922, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.026792113432505, 2.6103553153116392, 12.296902053438808}), new DoubleVector(new double[] {-19.140071053960853, 20.739885840215095, 43.78596755979206}), new DoubleVector(new double[] {0.13068450285797092, 0.8977311400953906, -0.42071410817299215}), 0.7738093273027922, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.026792113432505, 2.6103553153116392, 12.296902053438808}), new DoubleVector(new double[] {-19.140071053960853, 20.739885840215095, 43.78596755979206}), new DoubleVector(new double[] {0.13068450285797092, 0.8977311400953906, -0.42071410817299215}), 0.7738093273027922, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.056262838910709, -1.7221772319578423, 13.500387776307111}), new DoubleVector(new double[] {-1.182488139043743, -45.12364431428902, 34.32799987664407}), new DoubleVector(new double[] {-0.5179140773792565, 0.4201075362377368, 0.7451675425356757}), 0.7738093273027922, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.056262838910709, -1.7221772319578423, 13.500387776307111}), new DoubleVector(new double[] {-1.182488139043743, -45.12364431428902, 34.32799987664407}), new DoubleVector(new double[] {-0.5179140773792565, 0.4201075362377368, 0.7451675425356757}), 0.7738093273027922, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.056262838910709, -1.7221772319578423, 13.500387776307111}), new DoubleVector(new double[] {-1.182488139043743, -45.12364431428902, 34.32799987664407}), new DoubleVector(new double[] {-0.5179140773792565, 0.4201075362377368, 0.7451675425356757}), 0.7738093273027922, 0, 30.0);

    Face face_0 = 
      ((Face) extrusionMerge_1.getSideFace(lineSketchPrimitive_0,"True"));

    LineSketchPrimitive lineSketchPrimitive_1 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 4"));

    Face face_1 = 
      ((Face) extrusionMerge_1.getSideFace(lineSketchPrimitive_1,"True"));

    LineSketchPrimitive lineSketchPrimitive_2 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 2"));

    Face face_2 = 
      ((Face) extrusionMerge_1.getSideFace(lineSketchPrimitive_2,"True"));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_0, face_1, face_2}), "inlet", false);

    scene_0.setViewOrientation(new DoubleVector(new double[] {0.0, 0.0, 1.0}), new DoubleVector(new double[] {0.0, 1.0, 0.0}));

    currentView_0.setInput(new DoubleVector(new double[] {4.67327942549601, -1.817348527404232, 8.094178870180349}), new DoubleVector(new double[] {26.488142974454163, -5.182167025121393, 60.56531128175886}), new DoubleVector(new double[] {0.011787229669542432, 0.9981818869350294, 0.059109912971870536}), 0.7738093273027922, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.67327942549601, -1.817348527404232, 8.094178870180349}), new DoubleVector(new double[] {26.488142974454163, -5.182167025121393, 60.56531128175886}), new DoubleVector(new double[] {0.011787229669542432, 0.9981818869350294, 0.059109912971870536}), 0.7738093273027922, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.67327942549601, -1.817348527404232, 8.094178870180349}), new DoubleVector(new double[] {26.488142974454163, -5.182167025121393, 60.56531128175886}), new DoubleVector(new double[] {0.011787229669542432, 0.9981818869350294, 0.059109912971870536}), 0.7738093273027922, 0, 30.0);

    LineSketchPrimitive lineSketchPrimitive_3 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 3"));

    Face face_3 = 
      ((Face) extrusionMerge_1.getSideFace(lineSketchPrimitive_3,"True"));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_3}), "outlet", false);
  }
}
