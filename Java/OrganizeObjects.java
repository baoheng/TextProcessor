package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.ArrayList;
import java.util.List;

/**
 * The class processes lines in the input file. It also formats all content into HTML format.
 *
 * @author xhwu-bhling
 */
public class OrganizeObjects {

  private static final String END_CANNOT_PROCESS_LINE = ")</pre>";
  private static final String CANNOT_PROCESS_LINE = "<pre>(Cannot process line: ";
  private static final String BODY_HTML = "\n</body>\n</html>";
  private static final String TITLE_HEAD_BODY = "</title>\n</head>\n<body>\n";
  private static final String HTML_HEAD_TITLE = "<!DOCTYPE>\n<html>\n<head>\n\t<title>";
  private List<Level> header;
  private List<List<Level>> list;
  private List<Level> paragraphs;
  private Boolean inList;
  private List<Level> currentList;
  private ProcessParagraph pp;
  private boolean inBlockquote;
  private List<List<Level>> blockquote;

  /**
   * Instantiates a new OrganizeObjects with default field's values.
   */
  public OrganizeObjects() {
    super();
    this.header = new ArrayList<Level>();
    this.list = new ArrayList<List<Level>>();
    this.paragraphs = new ArrayList<Level>();
    this.inList = false;
    this.currentList = new ArrayList<Level>();
    this.pp = new ProcessParagraph();
    this.inBlockquote = false;
    this.blockquote = new ArrayList<List<Level>>();
  }

  /**
   * Process one line of content from the input file. It will check the line's format and use the
   * corresponding processor to process it.
   *
   * @param line the line of content from the input file to process
   * @return the converted content with HTML tags
   */
  protected String process(String line) {
    if (line.matches(ProcessHeader.HEADER_REGEX)) {
      line = handleHeader(line);
    } else if (this.inList || line.matches(ProcessList.LIST_REGEX)) {
      line = handleList(line);
    } else if (this.inBlockquote || line.startsWith("> ")) {
      line = handleBlockquote(line);
    } else if (line.startsWith("****")) {
      line = "<hr>\n";
    } else {
      line = handleParagraphs(line);
    }
    return line;
  }

  private String handleBlockquote(String line) {
    ProcessBlockquote pb = new ProcessBlockquote();
    if (!line.startsWith("> ")) {
      this.inBlockquote = false;
      this.blockquote.add(this.currentList);
      line = formatBlockquote(this.currentList).toString() + line;
      this.currentList = new ArrayList<Level>();
    } else {
      this.inBlockquote = true;
      pb.process(line, this.currentList);
      line = pb.ifAdded() ? null
          : OrganizeObjects.CANNOT_PROCESS_LINE + line + OrganizeObjects.END_CANNOT_PROCESS_LINE;
    }
    if (this.pp.inProcess()) {
      return this.pp.getMessage().toString() + "\n";
    }
    return line;
  }

  private StringBuilder formatBlockquote(List<Level> current) {
    StringBuilder accum = new StringBuilder("<blockquote>\n");

    for (Level level : current) {
      accum.append(level.getMessage() + "\n");
      if (level.hasNextLevel()) {
        accum.append(formatBlockquote(level.getNextLevel()));
      }
    }

    accum.append("</blockquote>\n");
    return accum;
  }

  private String handleParagraphs(String line) {
    this.paragraphs = this.pp.process(line, this.paragraphs);
    return this.pp.inProcess() ? null
        : this.paragraphs.get(this.paragraphs.size() - 1).getMessage();
  }

  /**
   * Process a header line and return the converted content.
   *
   * @param line the header line of content
   * @return the converted content with HTML header tags
   */
  private String handleHeader(String line) {
    ProcessHeader pl = new ProcessHeader();
    this.header = pl.process(line, this.header);
    if (this.pp.inProcess()) {
      return this.pp.getMessage().toString() + "\n" + pl.getMessage();
    }
    return pl.getMessage();
  }

  /**
   * Process a list line. It keeps the formatted list in currentList variable. If this list block
   * ends, this currentList will be added into lists field and the entire list content is returned
   * to the caller. If the list format can't be recognized, the content will be surrounded by these
   * tags and content "{@literal <pre>}(Cannot process line: " and "){@literal </pre>}".
   *
   * @param line the list line of content
   * @return the converted list content with HTML list tags
   */
  private String handleList(String line) {
    if (!line.matches(ProcessList.LIST_REGEX)) {
      this.inList = false;
      this.list.add(this.currentList);
      line = formatList(this.currentList).toString() + line;
      this.currentList = new ArrayList<Level>();
    } else {
      this.inList = true;
      ProcessList pll = handleListHelper(line);
      line = pll.ifAdded() ? null
          : OrganizeObjects.CANNOT_PROCESS_LINE + line + OrganizeObjects.END_CANNOT_PROCESS_LINE;
    }
    if (this.pp.inProcess()) {
      return this.pp.getMessage().toString() + "\n";
    }
    return line;
  }

  /**
   * Helper function to handle one list line. If it is unordered list line, use
   * ProcessUnorderedListLevel to process it; otherwise, use ProcessNumberedListLevel to process it.
   * After process, add this level into currentList.
   *
   * @param line the list line to process
   * @return the process list level object used to process this list line
   */
  private ProcessList handleListHelper(String line) {
    ProcessList pll;
    if (line.matches(ProcessUnorderedList.UNORDERED_LIST_REGEX)) {
      pll = new ProcessUnorderedList();
    } else {
      pll = new ProcessNumberedList();
    }
    this.currentList = pll.process(line, this.currentList);
    return pll;
  }

  /**
   * This method converts the entire list block into a HTML string. After we collect all list levels
   * in a list block and store them in currentList, we use this method to convert them into a HTML
   * formatted string as a StringBuilder.
   *
   * @param current the level list with an entire list block
   * @return the StringBuilder containing the formatted HTML content
   */
  private StringBuilder formatList(List<Level> current) {
    StringBuilder accum = new StringBuilder();
    String endTag = "";
    Boolean unclosedOLTag = false;
    Boolean unclosedULTag = false;
    for (Level l : current) {
      if (l.getType().equals(ProcessNumberedList.NUMBERED_LIST)) {
        endTag = ProcessNumberedList.END_OL_TAG;
        accum.append(addNestedTag(accum, unclosedULTag, ProcessUnorderedList.END_UL_TAG,
            ProcessNumberedList.OL_TAG));
        unclosedOLTag = true;
        unclosedULTag = false;
      } else {
        endTag = ProcessUnorderedList.END_UL_TAG;
        accum.append(addNestedTag(accum, unclosedOLTag, ProcessNumberedList.END_OL_TAG,
            ProcessUnorderedList.UL_TAG));
        unclosedOLTag = false;
        unclosedULTag = true;
      }
      accum.append(l.getMessage());
      if (l.hasNextLevel()) {
        accum.append(formatList(l.getNextLevel()) + "</li>\n");
      } else {
        accum.append("</li>\n");
      }
    }
    accum.append(endTag + "\n");
    return accum;
  }

  /**
   * Add tags between numbered lists and unordered lists.
   *
   * @param accum the string builder to build the entire list string
   * @param unclosedTag indicate whether we should add endTag to close the previous unclosed tag
   * @param endTag the end list tag
   * @param openTag the open list tag
   * @return the tags to add
   */
  private String addNestedTag(StringBuilder accum, Boolean unclosedTag, String endTag,
      String openTag) {
    StringBuilder newTag = new StringBuilder();
    if (unclosedTag) {
      newTag.append(endTag + "\n" + openTag + "\n");
    } else if (accum.length() == 0) {
      newTag.append("\n" + openTag + "\n");
    }

    return newTag.toString();
  }

  /**
   * Generate and return the header list as a string.
   *
   * @return the header list as a string
   */
  protected String appendHeader() {
    return appendHeaderHelper(this.header, new StringBuilder());
  }

  /**
   * Helper method to collect all headers from header level list. It passes in a StringBuilder and
   * keep all header string in this builder.
   *
   * @param header the header level list
   * @param accum the StringBuilder to collect the header string of all headers
   * @return the content collected in the string builder
   */
  private String appendHeaderHelper(List<Level> header, StringBuilder accum) {
    for (Level l : header) {
      accum.append(l.getMessage() + "\n");
      if (l.hasNextLevel()) {
        accum.append(appendHeaderHelper(l.getNextLevel(), new StringBuilder()));
      }
    }
    return accum.toString();
  }

  /**
   * Add {@literal <html>}, {@literal <head>}, {@literal <title>} and {@literal <body>} tags into
   * the output HTML content.
   *
   * @param fileName the input filename
   * @param contents the HTML contents generated by this program
   * @return the content by adding all HTML tags
   */
  protected String appendHTML(String fileName, String contents) {
    String HTMLTop = OrganizeObjects.HTML_HEAD_TITLE + fileName + OrganizeObjects.TITLE_HEAD_BODY
        + contents + OrganizeObjects.BODY_HTML;
    return HTMLTop;
  }
}
