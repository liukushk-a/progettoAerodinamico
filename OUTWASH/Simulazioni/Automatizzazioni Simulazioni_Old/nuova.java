// Simcenter STAR-CCM+ macro: nuova.java
// Written by Simcenter STAR-CCM+ 17.04.008
package macro;

import java.util.*;

import star.common.*;
import star.base.neo.*;
import java.io.*;

public class nuova extends StarMacro {

  public void execute() {
    execute0();
  }

  private void execute0() {

    new StarScript(getActiveRootObject(), new File(resolvePath("Import_Point_01.java"))).play();

  }
}
