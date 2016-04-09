package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.io.IOException;

/**
 * Represents the main console of the text processor program
 *
 * @author xhwu-bhling
 *
 */
public class GenerateHTMLProgram {

  /**
   * The main process method to run this application. It accepts a filename from command line, use
   * InputFile to process text document and generate the text output in HTML format based on the
   * required prototype and use OutputFile to save the content into a text file.
   *
   * @param args the command line arguments as a string array
   * @throws IOException if the file not existed or failed to write
   */
  public static void main(String[] args) throws IOException {
    if (args == null) {
      throw new IOException("Cannot handle null arrays.");
    } else if (args.length > 1) {
      throw new IOException("Please enter only one file name.");
    } else if (args.length < 1) {
      throw new IOException("Please enter the file name of the file to be processed.");
    } else {
      InputFile inf = new InputFile();
      inf.input(args[0]);
      OutputFile of = new OutputFile();
      of.output(inf.getContents(), args[0].replace(".txt", ".html"));
    }
  }

}
