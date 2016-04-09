package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * The class writes the contents and headers into an output file.
 */
public class OutputFile {

  /**
   * Format all content and write it into the output file.
   *
   * @param content the content written to output file
   * @param output the output filename
   *
   * @throws IOException if failed to writing to the output file
   */
  protected void output(String content, String output) throws IOException {
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(
          new OutputStreamWriter(new FileOutputStream(output), StandardCharsets.UTF_8));
      bw.write(content);
      bw.flush();
    } catch (IOException e) {
      throw new IOException("Cannot output the file: " + output + ".");
    } finally {
      if (bw != null) {
        bw.close();
      }
    }
  }

}
