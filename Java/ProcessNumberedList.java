/**
 *
 */
package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.List;

/**
 * The class extends abstract class ProcessListLevel and processes numbered list levels.
 *
 * @author xhwu-bhling
 */
public class ProcessNumberedList extends ProcessList {

  protected static final String NUMBERED_LIST_REGEX = "^(\\s)*1\\.(.)*$";
  protected static final String NUMBERED_LIST_PREFIX = "1.";
  protected static final String NUMBERED_LIST = "ol";
  protected static final String OL_TAG = "<ol>";
  protected static final String END_OL_TAG = "</ol>";

  /**
   * Instantiates a new ProcessNumberedListLevel.
   */
  public ProcessNumberedList() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<Level> process(String line, List<Level> list) {
    setMessage(CheckForEmphasis.detectBoldAndItalic(line));
    setMessage(CheckForLink.detectLink(this.getMessage()));
    return commonListOperation(list, ProcessNumberedList.NUMBERED_LIST_PREFIX,
        ProcessNumberedList.NUMBERED_LIST_PREFIX, ProcessNumberedList.NUMBERED_LIST);
  }

}
