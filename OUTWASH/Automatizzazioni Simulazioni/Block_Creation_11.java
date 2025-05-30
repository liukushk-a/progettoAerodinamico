// Simcenter STAR-CCM+ macro: Block_Creation_11.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import star.vis.*;
import star.meshing.*;

public class Block_Creation_11 extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    Simulation simulation_0 = 
      getActiveSimulation();

    simulation_0.getSceneManager().createGeometryScene("Geometry Scene", "Outline", "Surface", 1);

    Scene scene_2 = 
      simulation_0.getSceneManager().getScene("Geometry Scene 1");

    scene_2.initializeAndWait();

    SceneUpdate sceneUpdate_2 = 
      scene_2.getSceneUpdate();

    HardcopyProperties hardcopyProperties_2 = 
      sceneUpdate_2.getHardcopyProperties();

    hardcopyProperties_2.setCurrentResolutionWidth(25);

    hardcopyProperties_2.setCurrentResolutionHeight(25);

    hardcopyProperties_2.setCurrentResolutionWidth(1082);

    hardcopyProperties_2.setCurrentResolutionHeight(473);

    scene_2.resetCamera();

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    CurrentView currentView_1 = 
      scene_2.getCurrentView();

    currentView_1.setInput(new DoubleVector(new double[] {0.6712027075889896, 1.0005892214984005, 1.8343856806541368}), new DoubleVector(new double[] {0.6712027075889896, 1.0005892214984005, 123.1788931810334}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {0.7122206464294547, 0.8433537892766199, 2.488951702173935}), new DoubleVector(new double[] {0.7122206464294547, 0.8433537892766199, 111.11100517615418}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {1.549071819959801, 0.6033744086318882, 2.136772648247444}), new DoubleVector(new double[] {1.549071819959801, 0.6033744086318882, 100.24879981329576}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {2.304951148153684, 0.38661489539981875, 1.8186699657797902}), new DoubleVector(new double[] {2.304951148153684, 0.38661489539981875, 90.43759711432065}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {2.9876932881704406, 0.1908285464244252, 1.5313458284237242}), new DoubleVector(new double[] {2.9876932881704406, 0.1908285464244252, 81.57570437961384}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {3.6043748303637315, 0.013986045354290322, 1.2718225183513852}), new DoubleVector(new double[] {3.6043748303637315, 0.013986045354290322, 73.5712685053958}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {4.161387650924439, -0.14574557230650081, 1.0374099786304498}), new DoubleVector(new double[] {4.161387650924439, -0.14574557230650081, 66.34132387718766}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {4.664505161132411, -0.2900219171455515, 0.8256787058102333}), new DoubleVector(new double[] {4.664505161132411, -0.2900219171455515, 59.81093246425044}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {5.118942147524448, -0.4203384058903271, 0.6344344312088168}), new DoubleVector(new double[] {5.118942147524448, -0.4203384058903271, 53.91240710299059}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {5.529408828124013, -0.5380457628269671, 0.46169458511779027}), new DoubleVector(new double[] {5.529408828124013, -0.5380457628269671, 48.58460985503511}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {5.900159673933468, -0.6443640200811489, 0.30566831909132475}), new DoubleVector(new double[] {5.900159673933468, -0.6443640200811489, 43.77231831143042}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {6.235037495781959, -0.7403951601700545, 0.16473935254987992}), new DoubleVector(new double[] {6.235037495781959, -0.7403951601700545, 39.42565335182978}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    MeshPartFactory meshPartFactory_0 = 
      simulation_0.get(MeshPartFactory.class);

    SimpleBlockPart simpleBlockPart_0 = 
      meshPartFactory_0.createNewBlockPart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_0.setDoNotRetessellate(true);

    LabCoordinateSystem labCoordinateSystem_0 = 
      simulation_0.getCoordinateSystemManager().getLabCoordinateSystem();

    simpleBlockPart_0.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-1.0, -1.0, 0.0}));

    simpleBlockPart_0.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {3.0, 1.0, 5.0}));

    simpleBlockPart_0.rebuildSimpleShapePart();

    simpleBlockPart_0.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    simpleBlockPart_0.setDoNotRetessellate(true);

    simpleBlockPart_0.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-1.0, -1.0, 0.0}));

    simpleBlockPart_0.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_0.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {3.0, 1.0, 5.0}));

    simpleBlockPart_0.rebuildSimpleShapePart();

    simpleBlockPart_0.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {5.269783862396914, -0.8983053167837369, -1.048335929311584}), new DoubleVector(new double[] {5.269783862396914, -0.8983053167837369, 35.499561969944594}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {4.3970053665003785, -1.0410870522875482, -1.0968581479953734}), new DoubleVector(new double[] {4.3970053665003785, -1.0410870522875482, 31.949605758935736}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {3.78525601752945, -1.2094086187203776, -0.630765560505111}), new DoubleVector(new double[] {3.78525601752945, -1.2094086187203776, 28.75464518104161}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {3.234681611020584, -1.3608980264284385, -0.5676886035621465}), new DoubleVector(new double[] {3.234681611020584, -1.3608980264284385, 25.879180700446184}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {2.7391646458220817, -1.4972384931842395, -0.5109196705749532}), new DoubleVector(new double[] {2.7391646458220817, -1.4972384931842395, 23.29126267135453}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {3.18512991497833, -1.3745320729726092, -0.45893566424537724}), new DoubleVector(new double[] {3.18512991497833, -1.3745320729726092, 25.620388900031344}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {3.675691711076584, -1.239555010732557, -0.5048292705334916}), new DoubleVector(new double[] {3.675691711076584, -1.239555010732557, 28.182427751713618}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {4.21530969078138, -1.0910802411688112, -0.5553124487557142}), new DoubleVector(new double[] {4.21530969078138, -1.0910802411688112, 31.000670509437626}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {4.808889468645045, -0.9277579945968562, -0.6108437211114222}), new DoubleVector(new double[] {4.808889468645045, -0.9277579945968562, 34.10073754391792}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {5.461827226440181, -0.7481035227774844, -0.6719282239251143}), new DoubleVector(new double[] {5.461827226440181, -0.7481035227774844, 37.51081129304941}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {6.180058757553423, -0.5504836044534264, -0.7391209204939884}), new DoubleVector(new double[] {6.180058757553423, -0.5504836044534264, 41.26189240423895}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {6.970113449946775, -0.33310169204933826, -0.8130334656932448}), new DoubleVector(new double[] {6.970113449946775, -0.33310169204933826, 45.38808166921025}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {7.839173604437855, -0.09398159036984019, -0.8943364073837117}), new DoubleVector(new double[] {7.839173604437855, -0.09398159036984019, 49.92688982338047}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {8.795139775920013, 0.16905052190187783, -0.9837701429916521}), new DoubleVector(new double[] {8.795139775920013, 0.16905052190187783, 54.91957880102091}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {9.8467025672793, 0.45838584615162203, -1.0821473073185572}), new DoubleVector(new double[] {9.8467025672793, 0.45838584615162203, 60.41153669067758}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {11.003421629645699, 0.7766547005897143, -1.1903615955900193}), new DoubleVector(new double[] {11.003421629645699, 0.7766547005897143, 66.45269032684585}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {12.275812605204331, 1.1267504423854326, -1.309398158473556}), new DoubleVector(new double[] {12.275812605204331, 1.1267504423854326, 73.09795936295768}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {13.67544268395654, 1.5118557599119278, -1.440338271134081}), new DoubleVector(new double[] {13.67544268395654, 1.5118557599119278, 80.40775533212455}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {15.215035761317107, 1.935471606641315, -1.5843715718630875}), new DoubleVector(new double[] {15.215035761317107, 1.935471606641315, 88.44853084981042}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {16.908588145419888, 2.4014490377701865, -1.7428086916481504}), new DoubleVector(new double[] {16.908588145419888, 2.4014490377701865, 97.29338391407437}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 28.394541729001368, 0, 30.0);

    simpleBlockPart_0.setPresentationName("Box10");

    SimpleBlockPart simpleBlockPart_1 = 
      (SimpleBlockPart) simpleBlockPart_0.duplicatePart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_1.setPresentationName("Box25");

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    currentView_1.setInput(new DoubleVector(new double[] {16.688192892969866, 2.4233690362214233, 4.647405433606231}), new DoubleVector(new double[] {7.5296696943959205, 3.3342544209863023, 101.5305853245801}), new DoubleVector(new double[] {4.4140271523740676E-4, 0.9999560991140837, -0.009359754707706351}), 28.394541729001368, 0, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {16.688192892969866, 2.4233690362214233, 4.647405433606231}), new DoubleVector(new double[] {7.5296696943959205, 3.3342544209863023, 101.5305853245801}), new DoubleVector(new double[] {4.4140271523740676E-4, 0.9999560991140837, -0.009359754707706351}), 25.405452286984524, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {16.7951693981314, 2.878891189159832, 4.540906070532852}), new DoubleVector(new double[] {7.626122506452907, 3.7908232359284915, 101.53541055282558}), new DoubleVector(new double[] {4.4140271523740676E-4, 0.9999560991140837, -0.009359754707706351}), 27.9433465803457, 1, 30.0);

    currentView_1.setInput(new DoubleVector(new double[] {16.87781116770926, 2.869668692475149, 3.5595113872019293}), new DoubleVector(new double[] {9.697235258856267, 3.7822902453453633, 100.72145533468981}), new DoubleVector(new double[] {4.414027152374073E-4, 0.999956099114084, -0.009359754707706353}), 27.9433465803457, 1, 30.0);

    simpleBlockPart_1.setDoNotRetessellate(true);

    simpleBlockPart_1.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-1.0, -12.0, 0.0}));

    simpleBlockPart_1.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {12.0, 1.0, 5.0}));

    simpleBlockPart_1.rebuildSimpleShapePart();

    simpleBlockPart_1.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    simpleBlockPart_1.setDoNotRetessellate(true);

    simpleBlockPart_1.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-1.0, -10.0, 0.0}));

    simpleBlockPart_1.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_1.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {10.0, 1.0, 5.0}));

    simpleBlockPart_1.rebuildSimpleShapePart();

    simpleBlockPart_1.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    SimpleBlockPart simpleBlockPart_2 = 
      (SimpleBlockPart) simpleBlockPart_0.duplicatePart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_2.setPresentationName("Box50");

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    simpleBlockPart_2.setDoNotRetessellate(true);

    simpleBlockPart_2.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_2.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_2.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-1.0, -13.0, 0.0}));

    simpleBlockPart_2.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_2.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {13.0, 1.0, 5.0}));

    simpleBlockPart_2.rebuildSimpleShapePart();

    simpleBlockPart_2.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    SimpleBlockPart simpleBlockPart_3 = 
      (SimpleBlockPart) simpleBlockPart_0.duplicatePart(simulation_0.get(SimulationPartManager.class));

    simpleBlockPart_3.setPresentationName("Box80");

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

    simpleBlockPart_3.setDoNotRetessellate(true);

    simpleBlockPart_3.setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_3.getCorner1().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_3.getCorner1().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {-1.0, -18.0, 0.0}));

    simpleBlockPart_3.getCorner2().setCoordinateSystem(labCoordinateSystem_0);

    simpleBlockPart_3.getCorner2().setCoordinate(units_0, units_0, units_0, new DoubleVector(new double[] {18.0, 1.0, 5.0}));

    simpleBlockPart_3.rebuildSimpleShapePart();

    simpleBlockPart_3.setDoNotRetessellate(false);

    scene_2.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);
  }
}
