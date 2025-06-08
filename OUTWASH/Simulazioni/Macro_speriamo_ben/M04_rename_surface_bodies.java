// Simcenter STAR-CCM+ macro: M04_rename_surface_bodies.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.cadmodeler.*;

public class M04_rename_surface_bodies extends StarMacro {

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

    currentView_0.setInput(new DoubleVector(new double[] {10.750784691815355, 5.070817463246451, 16.71018741661059}), new DoubleVector(new double[] {-12.485587587576617, 14.246931270962724, 46.34178614516345}), new DoubleVector(new double[] {0.13259898026724598, 0.97142675477359, -0.19684402592442568}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.750784691815355, 5.070817463246451, 16.71018741661059}), new DoubleVector(new double[] {-12.485587587576617, 14.246931270962724, 46.34178614516345}), new DoubleVector(new double[] {0.13259898026724598, 0.97142675477359, -0.19684402592442568}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.750784691815355, 5.070817463246451, 16.71018741661059}), new DoubleVector(new double[] {-12.485587587576617, 14.246931270962724, 46.34178614516345}), new DoubleVector(new double[] {0.13259898026724598, 0.97142675477359, -0.19684402592442568}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.750784691815355, 5.070817463246451, 16.71018741661059}), new DoubleVector(new double[] {-12.485587587576617, 14.246931270962724, 46.34178614516345}), new DoubleVector(new double[] {0.13259898026724598, 0.97142675477359, -0.19684402592442568}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.1955017457388, 0.5554995260080386, 19.323800301511987}), new DoubleVector(new double[] {6.153661250326278, -39.717756977710714, 17.131766261523943}), new DoubleVector(new double[] {-0.7006378285336634, -0.0033135186437703835, 0.7135093929457601}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.061584503040852, -0.44717117585930266, 19.375604079204493}), new DoubleVector(new double[] {-14.726115171367237, -18.245533098884174, 46.428764600274434}), new DoubleVector(new double[] {-0.31282972786639324, 0.8928695940307133, 0.3239158060645792}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.061584503040852, -0.44717117585930266, 19.375604079204493}), new DoubleVector(new double[] {-14.726115171367237, -18.245533098884174, 46.428764600274434}), new DoubleVector(new double[] {-0.31282972786639324, 0.8928695940307133, 0.3239158060645792}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.061584503040852, -0.44717117585930266, 19.375604079204493}), new DoubleVector(new double[] {-14.726115171367237, -18.245533098884174, 46.428764600274434}), new DoubleVector(new double[] {-0.31282972786639324, 0.8928695940307133, 0.3239158060645792}), 0.7738284541142865, 0, 30.0);

    CadModel cadModel_0 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    ExtrusionMerge extrusionMerge_0 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 1"));

    Sketch3D sketch3D_0 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 1"));

    SplineSketchPrimitive3D splineSketchPrimitive3D_0 = 
      ((SplineSketchPrimitive3D) sketch3D_0.getSketchPrimitive3D("Spline 1"));

    star.cadmodeler.Body cadmodelerBody_0 = 
      ((star.cadmodeler.Body) extrusionMerge_0.getBody(splineSketchPrimitive3D_0));

    cadmodelerBody_0.setPresentationName("Flap1");

    ExtrusionMerge extrusionMerge_1 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 2"));

    Sketch3D sketch3D_1 = 
      ((Sketch3D) cadModel_0.getFeature("Sketch3D 2"));

    SplineSketchPrimitive3D splineSketchPrimitive3D_1 = 
      ((SplineSketchPrimitive3D) sketch3D_1.getSketchPrimitive3D("Spline 1"));

    star.cadmodeler.Body cadmodelerBody_1 = 
      ((star.cadmodeler.Body) extrusionMerge_1.getBody(splineSketchPrimitive3D_1));

    cadmodelerBody_1.setPresentationName("Flap2");

    ExtrusionMerge extrusionMerge_2 = 
      ((ExtrusionMerge) cadModel_0.getFeature("Extrude 3"));

    Sketch sketch_0 = 
      ((Sketch) cadModel_0.getFeature("Sketch 1"));

    LineSketchPrimitive lineSketchPrimitive_0 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 1"));

    star.cadmodeler.Body cadmodelerBody_2 = 
      ((star.cadmodeler.Body) extrusionMerge_2.getBody(lineSketchPrimitive_0));

    cadmodelerBody_2.setPresentationName("Domain");

    currentView_0.setInput(new DoubleVector(new double[] {8.761052437389761, 2.3693608766195595, 18.450442814818928}), new DoubleVector(new double[] {-17.199517442670746, 25.952880949085923, 47.43953594628942}), new DoubleVector(new double[] {0.32178843711023575, 0.8547698923692955, -0.40721079656749415}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.761052437389761, 2.3693608766195595, 18.450442814818928}), new DoubleVector(new double[] {-17.199517442670746, 25.952880949085923, 47.43953594628942}), new DoubleVector(new double[] {0.32178843711023575, 0.8547698923692955, -0.40721079656749415}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.025264247218328, 2.1185728952953857, 18.154592475548583}), new DoubleVector(new double[] {-22.751535043003365, 1.6541073493810239, 51.41588346163636}), new DoubleVector(new double[] {-0.036465527445554466, 0.9991166254757937, -0.020886215693077324}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.025264247218328, 2.1185728952953857, 18.154592475548583}), new DoubleVector(new double[] {-22.751535043003365, 1.6541073493810239, 51.41588346163636}), new DoubleVector(new double[] {-0.036465527445554466, 0.9991166254757937, -0.020886215693077324}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.226123153362842, 1.3471782671738626, 16.860920417640763}), new DoubleVector(new double[] {-10.398562145686824, 35.29876442930268, 40.04307515298384}), new DoubleVector(new double[] {0.47159365651876806, 0.674404081884204, -0.5681360378194462}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.226123153362842, 1.3471782671738626, 16.860920417640763}), new DoubleVector(new double[] {-10.398562145686824, 35.29876442930268, 40.04307515298384}), new DoubleVector(new double[] {0.47159365651876806, 0.674404081884204, -0.5681360378194462}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.201582910549352, 1.3875754977856758, 16.888503660628302}), new DoubleVector(new double[] {-10.398562145686824, 35.29876442930268, 40.04307515298384}), new DoubleVector(new double[] {0.7417163163123576, 0.6219693916253647, -0.2510198836689994}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {12.445819621812856, -2.306803105614705, 14.36598067669481}), new DoubleVector(new double[] {-10.398562145686824, 35.29876442930268, 40.04307515298384}), new DoubleVector(new double[] {0.7572195421258101, 0.6133360222554141, -0.22458292193905524}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.429345181797009, 4.0634285798691385, 21.118229476410434}), new DoubleVector(new double[] {-26.193089859500805, -18.23194058919981, 40.381198073447486}), new DoubleVector(new double[] {-0.2199783538882909, 0.7974467408376283, 0.5618613880202515}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.685040404814647, 7.7689517340557295, 17.916695758201506}), new DoubleVector(new double[] {-26.193089859500805, -18.23194058919981, 40.381198073447486}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {15.442098007343006, 12.612280190561924, 12.18011565396684}), new DoubleVector(new double[] {-23.77289277721063, -15.03621352478409, 36.06812854827531}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {13.698776182398035, 12.72358508769493, 10.68715445239631}), new DoubleVector(new double[] {-21.594715410156923, -12.160059176062871, 32.18636598810844}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {14.534281496831749, 12.106268196439302, 12.477625320546231}), new DoubleVector(new double[] {-23.55507507798367, -14.748598139399995, 35.679952359049466}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {16.18682109778988, 11.944361686970524, 14.000337854889466}), new DoubleVector(new double[] {-25.711470689700036, -17.595990968841903, 39.52289732628653}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {18.00461399462872, 11.766264095282143, 15.675321976693564}), new DoubleVector(new double[] {-28.08350580241249, -20.72812300176975, 43.750136683007554}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {20.004186276152424, 11.570356847273956, 17.517804384440684}), new DoubleVector(new double[] {-30.69274436811026, -24.173468161027266, 48.40009987152846}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {22.20371941118257, 11.354861347277673, 19.544532983217813}), new DoubleVector(new double[] {-33.56290692564104, -27.963348014817623, 53.51505961995608}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {24.623202356210037, 11.11781385636791, 21.773936520338296}), new DoubleVector(new double[] {-36.72008569142725, -32.13221579126919, 59.1415152585802}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {27.284635046221894, 10.857062621410321, 24.22627956118233}), new DoubleVector(new double[] {-40.192982362420366, -36.71797038316787, 65.33061651208563}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {30.212208675869924, 10.57023467984694, 26.923858212205296}), new DoubleVector(new double[] {-44.01316860430308, -41.76230030721718, 72.13862771948483}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {33.43254316560172, 10.25472635541415, 29.891192701642673}), new DoubleVector(new double[] {-48.21537355869773, -47.311063340297586, 79.62744020502686}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {36.97490923052021, 9.907665882691646, 33.155261771415105}), new DoubleVector(new double[] {-52.83779899997626, -53.4147026653889, 87.86513392387607}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {40.871510264156726, 9.525898268223791, 36.745738631007455}), new DoubleVector(new double[] {-57.92246688749722, -60.128705793737446, 96.92659684016714}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {40.871510264156726, 9.525898268223791, 36.745738631007455}), new DoubleVector(new double[] {-57.92246688749722, -60.128705793737446, 96.92659684016714}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {40.871510264156726, 9.525898268223791, 36.745738631007455}), new DoubleVector(new double[] {-57.92246688749722, -60.128705793737446, 96.92659684016714}), new DoubleVector(new double[] {-0.1701586217971957, 0.771267243014264, 0.6133456474788554}), 0.7738284541142865, 0, 30.0);

    Face face_0 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_0,"True"));

    LineSketchPrimitive lineSketchPrimitive_1 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 4"));

    Face face_1 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_1,"True"));

    LineSketchPrimitive lineSketchPrimitive_2 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 2"));

    Face face_2 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_2,"True"));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_0, face_1, face_2}), "inlet", false);

    currentView_0.setInput(new DoubleVector(new double[] {45.666078097462815, 15.772417966886596, 12.560819444738847}), new DoubleVector(new double[] {55.643909938073925, 5.4627606413513785, 135.16528898194574}), new DoubleVector(new double[] {0.07208712371208997, 0.9943634489255432, 0.07774817062680324}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {45.666078097462815, 15.772417966886596, 12.560819444738847}), new DoubleVector(new double[] {55.643909938073925, 5.4627606413513785, 135.16528898194574}), new DoubleVector(new double[] {0.07208712371208997, 0.9943634489255432, 0.07774817062680324}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {45.666078097462815, 15.772417966886596, 12.560819444738847}), new DoubleVector(new double[] {55.643909938073925, 5.4627606413513785, 135.16528898194574}), new DoubleVector(new double[] {0.07208712371208997, 0.9943634489255432, 0.07774817062680324}), 0.7738284541142865, 0, 30.0);

    LineSketchPrimitive lineSketchPrimitive_3 = 
      ((LineSketchPrimitive) sketch_0.getSketchPrimitive("Line 3"));

    Face face_3 = 
      ((Face) extrusionMerge_2.getSideFace(lineSketchPrimitive_3,"True"));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_3}), "outlet", false);

    currentView_0.setInput(new DoubleVector(new double[] {45.666078097462815, 15.772417966886596, 12.560819444738847}), new DoubleVector(new double[] {55.643909938073925, 5.4627606413513785, 135.16528898194574}), new DoubleVector(new double[] {0.07208712371208997, 0.9943634489255432, 0.07774817062680324}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {45.666078097462815, 15.772417966886596, 12.560819444738847}), new DoubleVector(new double[] {55.643909938073925, 5.4627606413513785, 135.16528898194574}), new DoubleVector(new double[] {0.07208712371208997, 0.9943634489255432, 0.07774817062680324}), 0.7738284541142865, 0, 30.0);

    Face face_4 = 
      ((Face) extrusionMerge_2.getEndCapFace(lineSketchPrimitive_0));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_4}), "1", false);

    currentView_0.setInput(new DoubleVector(new double[] {34.12345422559518, 14.151756683316489, -18.753463695771977}), new DoubleVector(new double[] {131.0340624452967, 49.66507555359231, 65.44488484540355}), new DoubleVector(new double[] {-0.10602274723844979, 0.9540220341087191, -0.2803589404728749}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-10.241044895554824, 4.209508300364264, -38.05007551423543}), new DoubleVector(new double[] {131.78822798994287, 15.023932417322452, -40.32447558070679}), new DoubleVector(new double[] {-0.07740002128623683, 0.9597070875520721, -0.27013245419092896}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-10.241044895554824, 4.209508300364264, -38.05007551423543}), new DoubleVector(new double[] {131.78822798994287, 15.023932417322452, -40.32447558070679}), new DoubleVector(new double[] {-0.07740002128623683, 0.9597070875520721, -0.27013245419092896}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-10.241044895554824, 4.209508300364264, -38.05007551423543}), new DoubleVector(new double[] {131.78822798994287, 15.023932417322452, -40.32447558070679}), new DoubleVector(new double[] {-0.07740002128623683, 0.9597070875520721, -0.27013245419092896}), 0.7738284541142865, 0, 30.0);

    Face face_5 = 
      ((Face) extrusionMerge_2.getStartCapFace(lineSketchPrimitive_0));

    cadModel_0.setFaceNameAttributes(new NeoObjectVector(new Object[] {face_5}), "2", false);

    simulation_0.get(SolidModelManager.class).endEditCadModel(cadModel_0);

    simulation_0.getSceneManager().deleteScenes(new NeoObjectVector(new Object[] {scene_0}));
  }
}
