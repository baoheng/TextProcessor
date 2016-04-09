

import java.util.ArrayList;
import java.util.List;

/**
 * The class extends abstract class ProcessLevel and adds some helper functions.
 */
public abstract class ProcessList extends ProcessObjects {

  protected static final String LIST_REGEX = "^(\\s*)(1\\.|\\*\\s)(.)*$";
  protected static final String LIST_TAG = "<li>";
  protected static final String END_LIST_TAG = "</li>";
  protected Boolean added;

  /**
   * Instantiates a new ProcessListLevel.
   */
  public ProcessList() {
    super();
    this.added = false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected abstract List<Level> process(String line, List<Level> list);

  /**
   *
   * @param list
   * @param prefix
   * @param prefixRegex
   * @param tagType
   * @return
   */
  protected List<Level> commonListOperation(List<Level> list, String prefix, String prefixRegex,
      String tagType) {
    StringBuilder index = new StringBuilder();
    String sub = this.getMessage().substring(0, this.getMessage().indexOf(prefix));
    Integer length = sub.length() / 2;
    Level currentLevel;
    this.added = true;
    setMessage(this.getMessage().replaceFirst(prefixRegex, ProcessList.LIST_TAG));
    if (length == 0) {
      index.append(String.valueOf(list.size() + 1));

      currentLevel =
          new Level(this.getMessage(), index.toString(), tagType, new ArrayList<Level>());
      list.add(currentLevel);

    } else {
      currentLevel = moveToCurrentLevel(length, list, 1);

      if (currentLevel != null) {
        index.append(getAlphabetNumber(length, currentLevel.getNextLevel().size()));

        currentLevel.getNextLevel()
            .add(new Level(this.getMessage(), index.toString(), tagType, new ArrayList<Level>()));
      } else {
        this.added = false;
      }
    }
    return list;
  }

  /**
   * Format and return the correct alphabetical and numeric index for the numbered list, that is,
   * numerically ordered in the odd depth and alphabetically ordered in the even depth.
   *
   * @param length the level depth of this level
   * @param i the number representing the index
   * @return a formatted index string
   */
  protected String getAlphabetNumber(Integer length, Integer i) {
    String index = "";
    if (length % 2 != 0) {
      index = String.valueOf(Character.toChars(i + 97));
    } else {
      index = String.valueOf(i + 1);
    }
    return index;
  }

  /**
   * Keeps track of the whether a line of a 'list' is added to the collection
   *
   * @return true if the line is added to the list
   */
  protected Boolean ifAdded() {
    return this.added;
  }

}
