/**
 *
 */
package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.List;

/**
 * The class extends abstract class ProcessListLevel and processes the unordered lists.
 *
 */
public class ProcessUnorderedList extends ProcessList {

  protected static final String UNORDERED_LIST_REGEX = "^(\\s)*\\*\\s(.)*$";
  protected static final String UNORDERED_LIST_PREFIX = "\\* ";
  protected static final String UNORDERED_LIST = "ul";
  protected static final String UL_TAG = "<ul>";
  protected static final String END_UL_TAG = "</ul>";


  /**
   * Instantiates a new ProcessUnorderedListLevel.
   */
  public ProcessUnorderedList() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<Level> process(String line, List<Level> list) {
    setMessage(CheckForEmphasis.detectBoldAndItalic(line));
    setMessage(CheckForLink.detectLink(this.getMessage()));
    return commonListOperation(list, "* ", ProcessUnorderedList.UNORDERED_LIST_PREFIX,
        ProcessUnorderedList.UNORDERED_LIST);
  }

}
