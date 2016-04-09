package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * the class accept a filename and process the file and generate the processed file content, header
 * list and numbered list
 */
public class InputFile {

  private String contents;

  /**
   * Instantiates a new inputFile with given file name
   *
   */
  public InputFile() {
    this.contents = "";
  }

  /**
   * The method processes the file and generates the processed file content, header list and
   * numbered list
   *
   * @param fileName the name of the file to be read
   * @throws IOException if the file not exist
   */
  protected void input(String fileName) throws IOException {
    BufferedReader bf = null;
    String line = null;
    OrganizeObjects organizer = new OrganizeObjects();
    try {
      bf = new BufferedReader(
          new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
      while ((line = bf.readLine()) != null) {
        line = organizer.process(line);
        if (line != null) {
          this.contents += line + "\n";
        }
      }
      this.contents = organizer.appendHTML(fileName, this.contents);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("Cannot open the specified file: " + fileName + ".");
    } finally {
      if (bf != null) {
        bf.close();
      }
    }
  }

  /**
   * @return the contents
   */
  protected String getContents() {
    return this.contents;
  }

}
