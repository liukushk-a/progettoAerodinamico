// Simcenter STAR-CCM+ macro: PostProcessing.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import java.io.*;

public class PostProcessing extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    new StarScript(getActiveRootObject(), new File(resolvePath("M15_autosave_mesh.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M16_CpT.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M17_velocity_vector.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M18_Velocity_Scalar.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M19_Streamlines.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M20_Reports.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M21_Monitor_Creation.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M22_Autosave_Plot.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M22_Monitors_Check.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M23_LayoutViews.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M24_Autosave_SIM.java"))).play();

  }
}
