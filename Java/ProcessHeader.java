

import java.util.ArrayList;
import java.util.List;

/**
 * The class extends abstract ProcessLevel and process header messages.
 */
public class ProcessHeader extends ProcessObjects {

  protected static final String HEADER_REGEX = "#+(.)*";
  protected static final String HEADER_PREFIX = "# ";
  protected static final String HEADER_END_PREFIX = " ";
  protected static final String HEADER = "header";
  protected static final String H1 = "<h1>";
  protected static final String END_H1 = "</h1>";
  protected static final String H2 = "<h2>";
  protected static final String END_H2 = "</h2>";
  protected static final String H3 = "<h3>";
  protected static final String END_H3 = "</h3>";
  protected static final String H4 = "<h4>";
  protected static final String END_H4 = "</h4>";
  protected static final String H5 = "<h5>";
  protected static final String END_H5 = "</h5>";
  protected static final String H6 = "<h6>";
  protected static final String END_H6 = "</h6>";

  /**
   * Instantiates a new ProcessHeaderLevel with a message and the entire level list.
   *
   */
  public ProcessHeader() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<Level> process(String line, List<Level> list) {
    setMessage(line);
    StringBuilder index = new StringBuilder();
    String sub =
        line.substring(0, line.indexOf(ProcessHeader.HEADER_END_PREFIX, line.indexOf("#")));
    Integer length = sub.length();
    Level currentLevel;
    setMessage(replaceHeaderTag(sub, length));

    if (length == 1) {
      index.append(String.valueOf(list.size() + 1));
      currentLevel = new Level(this.getMessage(), index.toString(), ProcessHeader.HEADER,
          new ArrayList<Level>());
      list.add(currentLevel);
    } else {
      currentLevel = moveToCurrentLevel(sub.length(), list, 2);
      if (currentLevel != null) {
        index.append(currentLevel.getIndex() + "." + (currentLevel.getNextLevel().size() + 1));
        currentLevel.getNextLevel().add(new Level(this.getMessage(), index.toString(),
            ProcessHeader.HEADER, new ArrayList<Level>()));
      }
    }
    return list;

  }

  /**
   * add header tag {@literal
   * 
  <h1></h1>}, {@literal
   * 
  <h2></h2>}, ... to the header text based on the header level value
   *
   * @param prefix the header prefix
   * @param level the level value
   * @return the header with added header tags {@literal <h1></h1>}, {@literal <h2></h2>}, ...
   */
  private String replaceHeaderTag(String prefix, Integer level) {
    String newHeader = this.getMessage();
    switch (level) {
      case 1:
        newHeader =
            this.getMessage().replace(prefix, ProcessHeader.H1) + ProcessHeader.END_H1;
        break;
      case 2:
        newHeader =
            this.getMessage().replace(prefix, ProcessHeader.H2) + ProcessHeader.END_H2;
        break;
      case 3:
        newHeader =
            this.getMessage().replace(prefix, ProcessHeader.H3) + ProcessHeader.END_H3;
        break;
      case 4:
        newHeader =
            this.getMessage().replace(prefix, ProcessHeader.H4) + ProcessHeader.END_H4;
        break;
      case 5:
        newHeader =
            this.getMessage().replace(prefix, ProcessHeader.H5) + ProcessHeader.END_H5;
        break;
      default:
        newHeader =
            this.getMessage().replace(prefix, ProcessHeader.H6) + ProcessHeader.END_H6;
        break;
    }
    return newHeader;
  }
}
