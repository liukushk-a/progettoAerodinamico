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

    CadModel cadModel_1 = 
      ((CadModel) simulation_0.get(SolidModelManager.class).getObject("3D-CAD Model 1"));

    CanonicalSketchPlane canonicalSketchPlane_0 = 
      ((CanonicalSketchPlane) cadModel_1.getFeature("XY"));

    Sketch sketch_0 = 
      cadModel_1.getFeatureManager().createSketch(canonicalSketchPlane_0);

    cadModel_1.allowMakingPartDirty(false);

    cadModel_1.getFeatureManager().startSketchEdit(sketch_0);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    Scene scene_1 = 
      simulation_0.getSceneManager().getScene("3D-CAD View 1");

    CurrentView currentView_0 = 
      scene_1.getCurrentView();

    currentView_0.setInput(new DoubleVector(new double[] {2.0443955968450283, -0.5138943422855244, -0.3193335060098983}), new DoubleVector(new double[] {1.3685288304539882, 1.363467980426162, 5.31696259834}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    sketch_0.createRectangle(new DoubleVector(new double[] {-0.61, 0.59}), new DoubleVector(new double[] {2.14, -1.3900000000000001}));

    currentView_0.setInput(new DoubleVector(new double[] {2.0443955968450283, -0.5138943422855244, -0.3193335060098983}), new DoubleVector(new double[] {1.3685288304539882, 1.363467980426162, 5.31696259834}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    PointSketchPrimitive pointSketchPrimitive_0 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 4"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {-27.14, 0.0}));

    PointSketchPrimitive pointSketchPrimitive_1 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 1"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_1, new DoubleVector(new double[] {25.61, 0.0}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {0.0, 24.41}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {0.0, -25.0}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {0.0, 25.0}));

    currentView_0.setInput(new DoubleVector(new double[] {1.8699726362038975, -0.709460722421378, -0.7384155224889062}), new DoubleVector(new double[] {1.2123223629083433, 1.1173014532050827, 4.745966692652836}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.135709773484759, -0.7858509704675141, -1.1312878448569652}), new DoubleVector(new double[] {1.3643226100811696, 1.35683937741011, 5.301587213126961}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.4277478377473427, -0.8349387172945055, -1.4669919043875854}), new DoubleVector(new double[] {1.5393747015475057, 1.632704814457201, 5.941471265815455}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.7168003907784133, -0.7940345242947675, -1.5527076620659042}), new DoubleVector(new double[] {1.7331925874541882, 1.9381433576662408, 6.649951657444005}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.0315661689813087, -0.7378762633648881, -1.614046677392838}), new DoubleVector(new double[] {1.9469198952497775, 2.274957255061235, 7.431208794887046}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.3779655248985807, -0.6745067805727696, -1.6772318486636788}), new DoubleVector(new double[] {2.1824865565781844, 2.6461878952480355, 8.292297335255393}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.7596499216055594, -0.6044328586196164, -1.7461658680879495}), new DoubleVector(new double[] {2.442105887067394, 3.0553232530226384, 9.241307815268701}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.180337582669361, -0.5272682210420174, -1.822336764684339}), new DoubleVector(new double[] {2.728238927597067, 3.5062416946027697, 10.287236304603658}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.6440157442241805, -0.4422800742882025, -1.906462525826905}), new DoubleVector(new double[] {3.0435979484366467, 4.003217499815677, 11.43999721489443}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.155010942941966, -0.348521230690789, -1.99890335964154}), new DoubleVector(new double[] {3.3911609467757806, 4.550943708826097, 12.710476293887835}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.71822862492818, -0.24527122869490814, -2.1010415087811296}), new DoubleVector(new double[] {3.77422320018722, 5.154613365488908, 14.110719073574682}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {6.3390156963815265, -0.13156554429486444, -2.2138898212096247}), new DoubleVector(new double[] {4.196417708043235, 5.819951731074289, 15.654005591585358}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {7.023081569138599, -0.0059585169265314875, -2.3373843855306156}), new DoubleVector(new double[] {4.661719271221925, 6.553222713499216, 17.354865304116082}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {7.777305885134485, 0.1318267235663253, -2.4754847243652165}), new DoubleVector(new double[] {5.1745808610364445, 7.361443771778248, 19.229575567843668}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.608176452934067, 0.28461372429781573, -2.62486498583225}), new DoubleVector(new double[] {5.739790406544543, 8.252160226455606, 21.295638141381737}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.523861473096797, 0.45298087966004097, -2.789538450175609}), new DoubleVector(new double[] {6.3626922685879155, 9.23379428161358, 23.572588759753845}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.53326075383851, 0.6380224204378226, -2.97259939548875}), new DoubleVector(new double[] {7.049216075766247, 10.315690467623142, 26.082102541191674}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {11.645749908189512, 0.8419805128726061, -3.1743059976247565}), new DoubleVector(new double[] {7.805858705570879, 11.508087245123756, 28.84792812111111}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {12.871789183508536, 1.0669248935355533, -3.396136013985412}), new DoubleVector(new double[] {8.639769452011034, 12.822251221105025, 31.8961989731895}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {14.223225557926092, 1.314439983122119, -3.6418561893708983}), new DoubleVector(new double[] {9.558870902232858, 14.270667638939297, 35.255875260823714}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {15.712195683907964, 1.588325603778058, -3.9093284008775555}), new DoubleVector(new double[] {10.571779327272735, 15.86691492180108, 38.95845283409537}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {17.353704054009306, 1.8889937015015423, -4.20771709275823}), new DoubleVector(new double[] {11.688162573312878, 17.626228675214946, 43.03927146268524}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {19.16352048244592, 2.2191033888715914, -4.540520819228497}), new DoubleVector(new double[] {12.91869294104302, 19.56542742942808, 47.5373425914746}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {21.157032295127767, 2.5857552688810657, -4.8987387279743615}), new DoubleVector(new double[] {14.274818878836324, 21.702552849561357, 52.494514789546635}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {23.3544830927465, 2.9888637122469426, -5.2965023816290895}), new DoubleVector(new double[] {15.769436618858421, 24.057928170219967, 57.957928843582714}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {25.776402748547277, 3.4330185979325307, -5.735258239948344}), new DoubleVector(new double[] {17.41669897888265, 26.653856885339845, 63.979318853517945}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {28.445680350101647, 3.9225174855824037, -6.218877554255087}), new DoubleVector(new double[] {19.232196867730043, 29.5149087834899, 70.61567570611025}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {31.387569805375893, 4.462014189134894, -6.751873314627787}), new DoubleVector(new double[] {21.233111835637878, 32.6681603527352, 77.92980470637035}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {34.629917477872425, 5.0566044278238245, -7.339320524403348}), new DoubleVector(new double[] {23.43838098445025, 36.14345466340336, 85.99092838898875}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {38.20340638398423, 5.711922293723575, -7.986758042417861}), new DoubleVector(new double[] {25.86887492939281, 39.97368181890552, 94.87533703842206}), new DoubleVector(new double[] {0.018270234126348064, 0.949250575880392, -0.31398971756366983}), 0.764504069283022, 0, 30.0);

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {45.0, 0.0}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {0.0, -5.0}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {-40.0, 0.0}));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_1, new DoubleVector(new double[] {-4.9999999999999964, 0.0}));

    PointSketchPrimitive pointSketchPrimitive_2 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 3"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_2, new DoubleVector(new double[] {0.0, -18.61}));

    sketch_0.setIsUptoDate(true);

    sketch_0.markFeatureForEdit();

    cadModel_1.allowMakingPartDirty(true);

    cadModel_1.getFeatureManager().stopSketchEdit(sketch_0, true);

    cadModel_1.getFeatureManager().updateModelAfterFeatureEdited(sketch_0, null);

    ExtrusionMerge extrusionMerge_2 = 
      cadModel_1.getFeatureManager().createExtrusionMerge(sketch_0);

    extrusionMerge_2.setAutoPreview(true);

    cadModel_1.allowMakingPartDirty(false);

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    extrusionMerge_2.setDirectionOption(0);

    extrusionMerge_2.setExtrudedBodyTypeOption(0);

    extrusionMerge_2.getDistance().setValueAndUnits(5.0, units_0);

    extrusionMerge_2.getDistanceAsymmetric().setValueAndUnits(0.1, units_0);

    extrusionMerge_2.getOffsetDistance().setValueAndUnits(0.1, units_0);

    extrusionMerge_2.setDistanceOption(0);

    extrusionMerge_2.setCoordinateSystemOption(0);

    Units units_1 = 
      ((Units) simulation_0.getUnitsManager().getObject("deg"));

    extrusionMerge_2.getDraftAngle().setValueAndUnits(10.0, units_1);

    extrusionMerge_2.setDraftOption(0);

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    extrusionMerge_2.setImportedCoordinateSystem(labCoordinateSystem_0);

    extrusionMerge_2.getDirectionAxis().setCoordinateSystem(labCoordinateSystem_0);

    extrusionMerge_2.getDirectionAxis().setUnits0(units_0);

    extrusionMerge_2.getDirectionAxis().setUnits1(units_0);

    extrusionMerge_2.getDirectionAxis().setUnits2(units_0);

    extrusionMerge_2.getDirectionAxis().setDefinition("");

    extrusionMerge_2.getDirectionAxis().setValue(new DoubleVector(new double[] {0.0, 0.0, 1.0}));

    extrusionMerge_2.setFace(null);

    extrusionMerge_2.setBody(null);

    extrusionMerge_2.setFeatureInputType(0);

    extrusionMerge_2.setInputFeatureEdges(new NeoObjectVector(new Object[] {}));

    extrusionMerge_2.setSketch(sketch_0);

    extrusionMerge_2.setInteractingBodies(new NeoObjectVector(new Object[] {}));

    extrusionMerge_2.setInteractingBodiesBodyGroups(new NeoObjectVector(new Object[] {}));

    extrusionMerge_2.setInteractingBodiesCadFilters(new NeoObjectVector(new Object[] {}));

    extrusionMerge_2.setInteractingSelectedBodies(false);

    extrusionMerge_2.setPostOption(0);

    extrusionMerge_2.setExtrusionOption(0);

    extrusionMerge_2.setIsBodyGroupCreation(false);

    cadModel_1.getFeatureManager().markDependentNotUptodate(extrusionMerge_2);

    cadModel_1.allowMakingPartDirty(true);

    extrusionMerge_2.markFeatureForEdit();

    cadModel_1.getFeatureManager().execute(extrusionMerge_2);

    scene_1.setTransparencyOverrideMode(SceneTransparencyOverride.USE_DISPLAYER_PROPERTY);
  }
}
