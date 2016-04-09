/**
 *
 */
package edu.neu.ccs.cs5004.seattle.assignment9_xhwu_bhling;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xhwu-bhling
 *
 */
public class CheckForLink {

  protected static String detectLink(String line) {
    if (line.matches("(.*?)\\[(.*?)\\]\\((.*?)\\)(.*?)")) {
      Pattern pattern = Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");
      Matcher matcher = pattern.matcher(line);
      if (matcher.find()) {
        line = line.replace(matcher.group(), CheckForLink.addTag(line));
      }
    }

    return line;
  }

  protected static String addTag(String line) {
    StringBuilder newLine = new StringBuilder("<a href=");
    Pattern urlPattern = Pattern.compile("\\((.*?)\\)");
    Matcher urlMatcher = urlPattern.matcher(line);
    if (urlMatcher.find()) {
      newLine.append("\"" + urlMatcher.group(1) + "\">");
    }

    Pattern textPattern = Pattern.compile("\\[(.*?)\\]");
    Matcher textMatcher = textPattern.matcher(line);
    if (textMatcher.find()) {
      newLine.append(textMatcher.group(1) + "</a>");
    }
    return newLine.toString();
  }
}
