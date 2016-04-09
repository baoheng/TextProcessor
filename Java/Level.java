package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.List;

/**
 * The Level represents the nesting sections or numbered lists
 */
public class Level {

  private String index;
  private String Message;
  private String type;
  private List<Level> nextLevel;

  /**
   * Instantiates a new level with given message, index, and its next levels
   *
   * @param message the level's message
   * @param index the level's index
   * @param type the object type of this level, i.e. header, ol, ul
   * @param nextLevel the level's next levels
   */
  public Level(String message, String index, String type, List<Level> nextLevel) {
    this.index = index;
    this.Message = message;
    this.type = type;
    this.nextLevel = nextLevel;
  }

  /**
   *
   * @return the level's next level
   */
  protected List<Level> getNextLevel() {
    return this.nextLevel;
  }

  /**
   * @return the level's index
   */
  protected String getIndex() {
    return this.index;
  }

  /**
   * @return the message
   */
  protected String getMessage() {
    return this.Message;
  }

  /**
   * @return the type
   */
  protected String getType() {
    return this.type;
  }

  /**
   * @return true if the level has next level, otherwise false
   */
  protected Boolean hasNextLevel() {
    if (this.nextLevel == null) {
      return false;
    }
    return this.getNextLevel().size() > 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.Message == null ? 0 : this.Message.hashCode());
    result = prime * result + (this.index == null ? 0 : this.index.hashCode());
    result = prime * result + (this.nextLevel == null ? 0 : this.nextLevel.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Level other = (Level) obj;
    if (this.Message == null) {
      if (other.Message != null) {
        return false;
      }
    } else if (!this.Message.equals(other.Message)) {
      return false;
    }
    if (this.index == null) {
      if (other.index != null) {
        return false;
      }
    } else if (!this.index.equals(other.index)) {
      return false;
    }
    if (this.nextLevel == null) {
      if (other.nextLevel != null) {
        return false;
      }
    } else if (!this.nextLevel.equals(other.nextLevel)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Level [index=" + this.index + ", Message=" + this.Message + ", type=" + this.type
        + ", nextLevel=" + this.nextLevel + "]";
  }

}
