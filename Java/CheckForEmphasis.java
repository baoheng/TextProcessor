package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckForEmphasis {


  protected static String detectBoldAndItalic(String line) {
    // pattern for text emphasized by character *
    Pattern boldPattern = Pattern.compile("\\*[^\\s\\*][^\\*]*?[^\\s\\*]\\*|\\*\\*");
    Matcher boldMatcher = boldPattern.matcher(line);

    // find all matches
    while (boldMatcher.find()) {
      String match = boldMatcher.group();
      String addBold = CheckForEmphasis.addTag(match, "^\\*", "\\*$", "<strong>", "</strong>");
      line = line.replace(match, addBold);
    }

    // pattern for text emphasized by character _
    Pattern italicPattern = Pattern.compile("\\_.*?\\_");
    Matcher italicMatcher = italicPattern.matcher(line);

    // find all matches
    while (italicMatcher.find()) {
      String match = italicMatcher.group();
      String addItalic = CheckForEmphasis.addTag(match, "^\\_", "\\_$", "<em>", "</em>");
      line = line.replace(match, addItalic);
    }
    return line;
  }


  private static String addTag(String group, String old1, String old2, String openTag,
      String endTag) {
    group = group.replaceFirst(old1, openTag);
    group = group.replaceFirst(old2, endTag);
    return group;
  }
}
