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

    currentView_0.setInput(new DoubleVector(new double[] {0.595025919647982, -0.29465854623986815, -0.039781359830789675}), new DoubleVector(new double[] {0.595025919647982, -0.29465854623986815, 2.152687360655893}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.5346217518235299, -0.32779163407606554, -0.027593351884771078}), new DoubleVector(new double[] {0.5346217518235299, -0.32779163407606554, 1.7436767614018749}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.5088705013121726, -0.34191679258440494, -0.02483401830091725}), new DoubleVector(new double[] {0.5088705013121726, -0.34191679258440494, 1.5693090847574347}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.5320466267733599, -0.3292041499263697, -0.022306904006355266}), new DoubleVector(new double[] {0.5320466267733599, -0.3292041499263697, 1.7262399937439699}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.5575403647811491, -0.3152202430022659, -0.024537593929382373}), new DoubleVector(new double[] {0.5575403647811491, -0.3152202430022659, 1.8988639936324305}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.5855834764216161, -0.29983794547795917, -0.026991341263944868}), new DoubleVector(new double[] {0.5855834764216161, -0.29983794547795917, 2.0887503923714856}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.616430899290978, -0.28291741816565097, -0.029690480467968516}), new DoubleVector(new double[] {0.616430899290978, -0.28291741816565097, 2.297625431423549}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.6503630644341954, -0.26430483812928696, -0.03265952780153736}), new DoubleVector(new double[] {0.6503630644341954, -0.26430483812928696, 2.527387974292246}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.6876884462397651, -0.2438310000080884, -0.03592549102131448}), new DoubleVector(new double[] {0.6876884462397651, -0.2438310000080884, 2.7801267724501617}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.7287463662258917, -0.22130977807477, -0.03951803939475518}), new DoubleVector(new double[] {0.7287463662258917, -0.22130977807477, 3.0581394504238686}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.8235901613428819, -0.16928575543675856, -0.04781682446847979}), new DoubleVector(new double[] {0.8235901613428819, -0.16928575543675856, 3.7003487361980536}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.8782382527197345, -0.13931000911190267, -0.0525984998050264}), new DoubleVector(new double[] {0.8782382527197345, -0.13931000911190267, 4.070383609736808}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.938351153154152, -0.10633668819850915, -0.057858344364317915}), new DoubleVector(new double[] {0.938351153154152, -0.10633668819850915, 4.477421970086923}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.0044753438309992, -0.07006603508462672, -0.06364419308978064}), new DoubleVector(new double[] {1.0044753438309992, -0.07006603508462672, 4.925164167819444}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.0772119534239002, -0.030168316742529143, -0.07000860126170405}), new DoubleVector(new double[] {1.0772119534239002, -0.030168316742529143, 5.4176805842984885}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.1572222240364962, 0.016419942095185054, -0.0792528208203569}), new DoubleVector(new double[] {1.1572222240364962, 0.016419942095185054, 5.959448642834454}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.2452335216824806, 0.06766702680044182, -0.0871781008814363}), new DoubleVector(new double[] {1.2452335216824806, 0.06766702680044182, 6.555393507035293}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

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

    currentView_0.setInput(new DoubleVector(new double[] {0.8681490783951031, -0.043264510851607735, -1.1739428795137963}), new DoubleVector(new double[] {0.8681490783951031, -0.043264510851607735, 5.891136350647042}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.1248787088072662, -0.26192143626273867, -2.0146884089204145}), new DoubleVector(new double[] {0.1248787088072662, -0.26192143626273867, 4.581820437675812}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.1919891855959695, -0.35513833604327344, -1.913219571369579}), new DoubleVector(new double[] {-0.1919891855959695, -0.35513833604327344, 4.023638390567024}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.47717028680601886, -0.4390335447417293, -1.8218975406490467}), new DoubleVector(new double[] {-0.47717028680601886, -0.4390335447417293, 3.521274554780011}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.7338332970491561, -0.514539238205133, -1.739708175927908}), new DoubleVector(new double[] {-0.7338332970491561, -0.514539238205133, 3.069147068830601}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.9648299738137771, -0.5824943527747471, -1.6657367235723792}), new DoubleVector(new double[] {-0.9648299738137771, -0.5824943527747471, 2.662232388646181}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-1.172726989598675, -0.6436539578574614, -1.5991631617837583}), new DoubleVector(new double[] {-1.172726989598675, -0.6436539578574614, 2.296009164683491}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.9856196272724079, -0.5886102991270118, -1.5383883504267102}), new DoubleVector(new double[] {-0.9856196272724079, -0.5886102991270118, 2.6256101510159677}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.7798015496440502, -0.5280622806809088, -1.5922266866750685}), new DoubleVector(new double[] {-0.7798015496440502, -0.5280622806809088, 2.988171199111281}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.5534016868756565, -0.46145946704542085, -1.6514488587390526}), new DoubleVector(new double[] {-0.5534016868756565, -0.46145946704542085, 3.3869883121646893}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.3043618392437146, -0.3881963724621492, -1.7165937225080525}), new DoubleVector(new double[] {-0.3043618392437146, -0.3881963724621492, 3.82568713403384}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {-0.030417998644968747, -0.30760696600719434, -1.7882532722230282}), new DoubleVector(new double[] {-0.030417998644968747, -0.30760696600719434, 4.308255852541063}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.27092021092205915, -0.21895862334642208, -1.8670782850689749}), new DoubleVector(new double[] {0.27092021092205915, -0.21895862334642208, 4.83908141631425}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.6023922538927934, -0.12144544275788552, -1.9537863901336587}), new DoubleVector(new double[] {0.6023922538927934, -0.12144544275788552, 5.42298955839091}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {0.967011506955179, -0.014180942405833522, -2.0491651474502737}), new DoubleVector(new double[] {0.967011506955179, -0.014180942405833522, 6.065288524882736}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.3680926780546596, 0.10381000584297091, -2.1540815025757585}), new DoubleVector(new double[] {1.3680926780546596, 0.10381000584297091, 6.771817375218708}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {1.8092819722745839, 0.23360005068483664, -2.269489778158923}), new DoubleVector(new double[] {1.8092819722745839, 0.23360005068483664, 7.54899912117613}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.294590197897344, 0.37636910059361783, -2.396438788132757}), new DoubleVector(new double[] {2.294590197897344, 0.37636910059361783, 8.403899045218669}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {2.828429246196831, 0.5334150555269466, -2.5360826573665456}), new DoubleVector(new double[] {2.828429246196831, 0.5334150555269466, 9.344288961867074}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {3.4156521973461604, 0.7061656053710962, -2.6896908703982874}), new DoubleVector(new double[] {3.4156521973461604, 0.7061656053710962, 10.378717866692243}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.061597445989031, 0.8961912108994049, -2.858659997544546}), new DoubleVector(new double[] {4.061597445989031, 0.8961912108994049, 11.516589666189992}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {4.772137219368301, 1.1052193769429222, -3.0445259818496666}), new DoubleVector(new double[] {4.772137219368301, 1.1052193769429222, 12.768248645412232}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {5.553730959182856, 1.3351503563834277, -3.2489783440031825}), new DoubleVector(new double[] {5.553730959182856, 1.3351503563834277, 14.14507350335103}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {6.413484083790985, 1.5880744369487172, -3.473876406579052}), new DoubleVector(new double[] {6.413484083790985, 1.5880744369487172, 15.659580866129915}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {7.359212515214628, 1.8662909239097885, -3.7212639191038406}), new DoubleVector(new double[] {7.359212515214628, 1.8662909239097885, 17.325538955242145}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {6.318911243440905, 1.56025278907405, -3.998168813752116}), new DoubleVector(new double[] {6.318911243440905, 1.56025278907405, 15.492985062137468}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {7.255182390675944, 1.83568711120247, -3.6940512778185592}), new DoubleVector(new double[] {7.255182390675944, 1.83568711120247, 17.142283570579888}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {6.225284133028548, 1.532709358129471, -3.968187091851682}), new DoubleVector(new double[] {6.225284133028548, 1.532709358129471, 15.328055218887574}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {7.152192572169612, 1.8053893380304644, -3.667110826902043}), new DoubleVector(new double[] {7.152192572169612, 1.8053893380304644, 16.960860748196783}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.171791850900284, 2.1053373146493666, -3.9338218135497236}), new DoubleVector(new double[] {8.171791850900284, 2.1053373146493666, 18.75694682281905}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.293351051754005, 2.4352800872386062, -4.2272038772667955}), new DoubleVector(new double[] {9.293351051754005, 2.4352800872386062, 20.732641494774533}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {8.059635922466791, 2.072343034934574, -4.555591443990625}), new DoubleVector(new double[] {8.059635922466791, 2.072343034934574, 18.559377340917777}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.169979532977845, 2.3989863802879903, -4.194931886305923}), new DoubleVector(new double[] {9.169979532977845, 2.3989863802879903, 20.51531506908824}), new DoubleVector(new double[] {0.0, 1.0, 0.0}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {9.058529594988011, 2.1939077694527747, 0.793647444939584}), new DoubleVector(new double[] {6.4293038458103515, -2.6441206219369993, 21.592431803954636}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.305850340651993, 2.836741138690937, -1.1986461790899696}), new DoubleVector(new double[] {7.142058322977035, -2.9849406285560125, 23.82888443633008}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {11.80013526877474, 3.600889270364436, -3.556984677830357}), new DoubleVector(new double[] {7.998507536864483, -3.3944715906149225, 26.51621669484878}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {13.186085198836253, 3.939993783557628, -3.9827047535110367}), new DoubleVector(new double[] {8.952312478748102, -3.8505554413936567, 29.509027882127285}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {14.70295767146536, 4.2800039050869305, -4.300292659863807}), new DoubleVector(new double[] {10.009644852592023, -4.356143323611336, 32.826683325244595}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {16.38245038713021, 4.65405846518328, -4.640456073626318}), new DoubleVector(new double[] {11.18136965529958, -4.916430586347224, 36.50327477908103}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {18.24366645929589, 5.068609589679554, -5.017536957207874}), new DoubleVector(new double[] {12.479866570743745, -5.537336861219987, 40.57764672300895}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {20.248393055139687, 5.446207332409469, -5.095246186908845}), new DoubleVector(new double[] {13.908213177732327, -6.220333763580025, 45.05945586132965}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {22.570859775655947, 6.027363651520448, -5.87016781673794}), new DoubleVector(new double[] {15.500955623876372, -6.981940331377208, 50.057099721152184}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {25.104364667564578, 6.595855644823944, -6.403475357467819}), new DoubleVector(new double[] {17.266669720446824, -7.82625728673689, 55.59748714178882}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    Units units_0 = 
      simulation_0.getUnitsManager().getPreferredUnits(Dimensions.Builder().length(1).build());

    currentView_0.setInput(new DoubleVector(new double[] {25.104364667564578, 6.595855644823944, -6.403475357467819}), new DoubleVector(new double[] {17.266669720446824, -7.82625728673689, 55.59748714178882}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    sketch_0.createRectangle(new DoubleVector(new double[] {-6.25, 6.25}), new DoubleVector(new double[] {6.25, -6.25}));

    currentView_0.setInput(new DoubleVector(new double[] {25.104364667564578, 6.595855644823944, -6.403475357467819}), new DoubleVector(new double[] {17.266669720446824, -7.82625728673689, 55.59748714178882}), new DoubleVector(new double[] {-0.013972157757680853, 0.9742898851717925, 0.22486440016047335}), 0.7738284541142865, 0, 30.0);

    PointSketchPrimitive pointSketchPrimitive_0 = 
      ((PointSketchPrimitive) sketch_0.getSketchPrimitive("Point 4"));

    sketch_0.translateSketchPrimitive(pointSketchPrimitive_0, new DoubleVector(new double[] {1.75, 0.0}));

    sketch_0.setIsUptoDate(true);

    sketch_0.markFeatureForEdit();

    cadModel_0.allowMakingPartDirty(true);

    cadModel_0.getFeatureManager().stopSketchEdit(sketch_0, true);

    cadModel_0.getFeatureManager().updateModelAfterFeatureEdited(sketch_0, null);

    currentView_0.setInput(new DoubleVector(new double[] {23.613451409431846, 4.515866047725003, 3.8417749401910366}), new DoubleVector(new double[] {7.193683244937632, 7.134477394836013, 57.084639986982296}), new DoubleVector(new double[] {0.04258451581619867, 0.9984450381044746, -0.03597311353295072}), 0.7738284541142865, 0, 30.0);

    ExtrusionMerge extrusionMerge_2 = 
      cadModel_0.getFeatureManager().createExtrusionMerge(sketch_0);

    extrusionMerge_2.setAutoPreview(true);

    cadModel_0.allowMakingPartDirty(false);

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.MAKE_SCENE_TRANSPARENT);

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

    cadModel_0.getFeatureManager().markDependentNotUptodate(extrusionMerge_2);

    cadModel_0.allowMakingPartDirty(true);

    extrusionMerge_2.markFeatureForEdit();

    cadModel_0.getFeatureManager().execute(extrusionMerge_2);

    scene_0.setTransparencyOverrideMode(SceneTransparencyOverride.USE_DISPLAYER_PROPERTY);

    currentView_0.setInput(new DoubleVector(new double[] {19.553571645232335, 5.109537314507561, 15.513272770625699}), new DoubleVector(new double[] {-3.306441765912281, 7.5944063387855145, 57.4201970512698}), new DoubleVector(new double[] {0.04258451581619862, 0.9984450381044743, -0.03597311353295091}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {19.71540011415557, 5.089023792352961, 15.135484025637325}), new DoubleVector(new double[] {-6.637654099736411, 7.716706840262154, 56.871238856653385}), new DoubleVector(new double[] {0.0425845158161981, 0.9984450381044742, -0.0359731135329507}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {19.93208001491616, 4.787605313478476, 14.689056922466678}), new DoubleVector(new double[] {-2.013499446786309, 20.392279126936636, 54.39609472126359}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {20.305670611894964, 2.6832728320465797, 9.36623323312476}), new DoubleVector(new double[] {-1.8497172453840613, 18.437133482061533, 49.452885974794405}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {18.235920585858132, 2.500309404143012, 8.929269412718853}), new DoubleVector(new double[] {-1.7023251169169211, 16.677643894095553, 45.00435583927578}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {16.373278877877723, 2.3356686286106427, 8.536066827630357}), new DoubleVector(new double[] {-1.5696829888199033, 15.094232040698088, 41.00100430201609}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {14.697044447680703, 2.1874985997651812, 8.18219959713321}), new DoubleVector(new double[] {-1.450314731749604, 13.669276667347454, 37.39827941891661}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {15.85239003524131, 2.57194749907576, 9.139610455638152}), new DoubleVector(new double[] {-1.557737449029649, 14.951632479227804, 40.64046880873953}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {17.476492985981434, 2.74376535980357, 9.553882978535817}), new DoubleVector(new double[] {-1.6759102308113851, 16.362316898323467, 44.20711233591622}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {19.263138615025728, 2.932768128811368, 10.00958901752395}), new DoubleVector(new double[] {-1.805908953818585, 17.91417317422382, 48.130681680431096}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {23.390710035113777, 3.3694073615940745, 11.062373065328437}), new DoubleVector(new double[] {-2.106236422824794, 21.499324295875528, 57.19504495608602}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {25.769183276421096, 3.6210380815489316, 11.669084240613884}), new DoubleVector(new double[] {-2.279299153174753, 23.565256006629014, 62.418354906919404}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {28.385681443113203, 3.897846304021421, 12.33650082078745}), new DoubleVector(new double[] {-2.469680690937417, 25.83793051725622, 68.16437416039943}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {31.26405865342062, 4.20232992056857, 13.070642523463114}), new DoubleVector(new double[] {-2.6791144178135142, 28.338040025407842, 74.48541894814738}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {34.430455561834904, 4.537303729958094, 13.87830187908159}), new DoubleVector(new double[] {-2.9095067696447066, 31.08834255790945, 81.43902855249314}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {37.91372316026428, 4.905799057051549, 14.76678533177487}), new DoubleVector(new double[] {-3.1629551377625607, 34.113875667635554, 89.08850559781351}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {33.02832902791849, 0.8952309364357021, 5.053289233617377}), new DoubleVector(new double[] {-3.934310347203811, 27.177969641961, 71.9313099927659}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {29.12282842871543, 0.4465886602163671, 4.123582615995794}), new DoubleVector(new double[] {-4.266756752014069, 24.188663296305297, 64.53672974528672}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {25.89966712569852, -0.2051650685184434, 2.658517595779756}), new DoubleVector(new double[] {-4.570132045081882, 21.460759864170512, 57.78877603836415}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {23.25816758572848, -1.0423637288256131, 0.7065897210279388}), new DoubleVector(new double[] {-4.849993476131832, 18.944289420703452, 51.56383938653196}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {20.1954761915975, -1.3085218129930674, 0.18987170092935912}), new DoubleVector(new double[] {-5.101868764076787, 16.679466021583096, 45.961396399882986}), new DoubleVector(new double[] {0.18182061522978793, 0.9452541918426709, -0.27099036270930105}), 0.7738284541142865, 0, 30.0);

    currentView_0.setInput(new DoubleVector(new double[] {10.750784691815355, 5.070817463246451, 16.71018741661059}), new DoubleVector(new double[] {-12.485587587576617, 14.246931270962724, 46.34178614516345}), new DoubleVector(new double[] {0.13259898026724598, 0.97142675477359, -0.19684402592442568}), 0.7738284541142865, 0, 30.0);
  }
}
