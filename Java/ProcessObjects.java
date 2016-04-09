package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.List;

/**
 * The abstract class define methods to process a message line and add it into the master level
 * list.
 */
public abstract class ProcessObjects {

  protected String message;

  /**
   * Instantiates a new process level.
   */
  public ProcessObjects() {
    this.message = "";
  }

  /**
   * process a line of message, add it into the current list of levels passed in, and return this
   * list of levels after processing
   *
   * @param line a line of message
   * @param list the current list of levels
   * @return the new list of levels with this new level added
   */
  protected abstract List<Level> process(String line, List<Level> list);

  /**
   * Find and return the current level based on the level depth
   *
   * @param length the depth of the level in the level list
   * @param list the level list
   * @param condition the value to find the current level
   * @return the current level
   */
  protected Level moveToCurrentLevel(Integer length, List<Level> list, Integer condition) {
    if (list.size() == 0) {
      return null;
    }
    while (length > condition) {
      if (list.size() == 0) {
        return null;
      }
      list = list.get(list.size() - 1).getNextLevel();
      length--;
    }
    return list.get(list.size() - 1);
  }

  /**
   * @return the message
   */
  protected String getMessage() {
    return this.message;
  }

  /**
   * @param message the message to set
   */
  protected void setMessage(String message) {
    this.message = message;
  }

}
