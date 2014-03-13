package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;


public abstract class Processor {

  public static final String DIVIDER = "---------------------------------------------------";
  protected String fileName;

  public abstract BigDecimal process();

  /**
   * Just a method to print stuff to the file
   * @param msg Message to print
   */
  protected void printOut(final String msg) {
    try {
      final PrintWriter out =
          new PrintWriter(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"\\"+fileName, true)));
      out.println(msg);
      out.close();
    }
    catch (final IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Simple helper method just to easy readability/coding
   * @param value of double
   * @return BigDecimal representation of the double
   */
  protected BigDecimal val (final double value) {
    return BigDecimal.valueOf(value);
  }

  protected void setFilename(final String fileName) {
    this.fileName = fileName;
  }

}
