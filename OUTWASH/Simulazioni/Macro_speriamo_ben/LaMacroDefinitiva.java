// Simcenter STAR-CCM+ macro: LaMacroDefinitiva.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import java.io.*;
import star.vis.*;
import star.cadmodeler.*;

public class LaMacroDefinitiva extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    new StarScript(getActiveRootObject(), new File(resolvePath("PreProcessing.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("PostProcessing.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("Run.java"))).play();


    new StarScript(getActiveRootObject(), new File(resolvePath("M25_ExportCSV.java"))).play();


  }
}
