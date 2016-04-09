
package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.ArrayList;
import java.util.List;

/**
 * The class processes paragraphs
 *
 * @author xhwu-bhling
 */
public class ProcessParagraph extends ProcessObjects {

  protected static final String END_PARAGRAPH_TAG = "</p>";
  protected static final String PARAGRAPH_TAG = "<p>";
  private StringBuilder paragraph;

  /**
   * Instantiates a new ProcessParagraph.
   */
  public ProcessParagraph() {
    this.paragraph = new StringBuilder();
  }

  /**
   * Process one line of paragraph, accumulate the paragraph to the end and return the entire
   * paragraph.
   *
   * @param line one line text of a paragraph or empty lines
   * @param list the list that contains every paragraph
   * @return the list that either added a new paragraph or empty line
   */
  @Override
  protected List<Level> process(String line, List<Level> list) {
    if (line.equals("")) {
      if (!inProcess()) {
        list.add(new Level(line, String.valueOf(list.size()), "newlines", new ArrayList<Level>()));
      } else {
        String result = this.getParagraph().insert(0, ProcessParagraph.PARAGRAPH_TAG)
            .insert(this.getParagraph().length() - 1, ProcessParagraph.END_PARAGRAPH_TAG)
            .toString();

        list.add(
            new Level(result, String.valueOf(list.size()), "paragraphs", new ArrayList<Level>()));
        this.paragraph = new StringBuilder();
      }
    } else {
      line = CheckForLink.detectLink(line);
      this.getParagraph().append(CheckForEmphasis.detectBoldAndItalic(line) + "\n");
    }
    return list;
  }


  /**
   * @return true if string length is greater than 0, otherwise false
   */
  protected boolean inProcess() {
    return this.paragraph.toString().length() > 0;
  }

  /**
   * @return the paragraph
   */
  protected StringBuilder getParagraph() {
    return this.paragraph;
  }
}
