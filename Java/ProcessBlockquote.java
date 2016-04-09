

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xhwu-bhling
 *
 */
public class ProcessBlockquote extends ProcessObjects {

  private static final String BLOCKQUOTE = "blockquote";
  protected static final String BLOCKQUOTE_REGEX = "\\>\\s";
  private Boolean isAdded;

  public ProcessBlockquote() {
    super();
    this.isAdded = false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<Level> process(String line, List<Level> list) {
    setMessage(CheckForEmphasis.detectBoldAndItalic(line));
    setMessage(CheckForLink.detectLink(this.getMessage()));
    Pattern blockquotePattern = Pattern.compile("\\>\\s(?=[\\>\\s]*?)");
    Matcher blockquoteMatcher = blockquotePattern.matcher(line);
    Integer length = 0;
    while (blockquoteMatcher.find()) {
      length++;
    }

    setMessage(ProcessParagraph.PARAGRAPH_TAG + this.getMessage().substring(length * 2)
        + ProcessParagraph.END_PARAGRAPH_TAG);

    StringBuilder index = new StringBuilder();
    Level currentLevel;
    this.isAdded = true;

    if (length == 1) {
      index.append(String.valueOf(list.size() + 1));
      currentLevel = new Level(this.getMessage(), index.toString(), ProcessHeader.HEADER,
          new ArrayList<Level>());
      list.add(currentLevel);
    } else {
      currentLevel = moveToCurrentLevel(length, list, 2);
      if (currentLevel != null) {
        index.append(currentLevel.getIndex() + "." + (currentLevel.getNextLevel().size() + 1));
        currentLevel.getNextLevel().add(new Level(this.getMessage(), index.toString(),
            ProcessBlockquote.BLOCKQUOTE, new ArrayList<Level>()));
      } else {
        this.isAdded = false;
      }
    }
    return list;
  }

  protected boolean ifAdded() {
    return this.isAdded;
  }

}
