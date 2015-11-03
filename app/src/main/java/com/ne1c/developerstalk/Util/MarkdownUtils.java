package com.ne1c.developerstalk.Util;

import com.ne1c.developerstalk.Models.StatusMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownUtils {
    private static final Pattern TEXT_PATTERN = Pattern.compile("\\w+");
    private static final Pattern SINGLELINE_CODE_PATTERN = Pattern.compile("`(.*?)`"); // `code`
    private static final Pattern MULTILINE_CODE_PATTERN = Pattern.compile("'''(.*?)'''");  // '''code'''
    private static final Pattern BOLD_PATTERN = Pattern.compile("\\*{2}(.*?)\\*{2}"); // **bold**
    private static final Pattern ITALICS_PATTERN = Pattern.compile("[*](.*?)[*]"); // *italics*
    private static final Pattern STRIKETHROUGH_PATTERN = Pattern.compile("~{2}.*?~{2}"); // ~~strikethrough~~
    private static final Pattern QUOTE_PATTERN = Pattern.compile(">\\s.*?\\s*"); // > blockquote
    private static final Pattern ISSUE_PATTERN = Pattern.compile("#.*?\\S"); // #123
    private static final Pattern LINK_PATTERN = Pattern.compile("\\[\\.*?\\S]\\(\\bhttp\\b://.*?\\)"); // [title](http://)
    private static final Pattern IMAGE_LINK_PATTERN = Pattern.compile("!\\[\\balt\\b]\\(\\bhttp\\b://.*?\\)"); // ![alt](http://)

    private final String mMessage;

    private List<String> mText;
    private List<String> mMultilineCode;
    private List<String> mSinglelineCode;
    private List<String> mBold;
    private List<String> mItalics;
    private List<String> mStrikethrough;
    private List<String> mQuote;
    private List<String> mLinks;
    private List<String> mImageLinks;
    private List<String> mIssues;

    public MarkdownUtils(String message) {
        if (message == null) {
            throw new NullPointerException("Message is null");
        } else {
            mMessage = message;
        }
    }

    private List readText(String message) {
        Matcher matcher = TEXT_PATTERN.matcher(message);

        if (mText != null) {
            return mText;
        }

        if (matcher.find()) {
            mText = new ArrayList<>();

            /*
              Hard work!
             */

            return mText;
        }

        return Collections.EMPTY_LIST;
    }

    private List readMultilineCode(String message) {
        Matcher matcher = MULTILINE_CODE_PATTERN.matcher(message);

        if (mMultilineCode != null) {
            return mMultilineCode;
        }

        if (matcher.find()) {
            mMultilineCode = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mMultilineCode.add(matcher.group(i).replace("\'\'\'", ""));
            }
            return mMultilineCode;
        }

        return Collections.EMPTY_LIST;
    }

    private List readSinglelineCode(String message) {
        Matcher matcher = SINGLELINE_CODE_PATTERN.matcher(message);

        if (mSinglelineCode != null) {
            return mSinglelineCode;
        }

        if (matcher.find()) {
            mSinglelineCode = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mSinglelineCode.add(matcher.group(i).replace("`", ""));
            }

            return mSinglelineCode;
        }

        return Collections.EMPTY_LIST;
    }

    private List readBold(String message) {
        Matcher matcher = BOLD_PATTERN.matcher(message);

        if (mBold != null) {
            return mBold;
        }

        if (matcher.find()) {
            mBold = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mBold.add(matcher.group(i).replace("**", ""));
            }

            return mBold;
        }

        return Collections.EMPTY_LIST;
    }

    private List readStrikethrough(String message) {
        Matcher matcher = STRIKETHROUGH_PATTERN.matcher(message);

        if (mStrikethrough != null) {
            return mStrikethrough;
        }

        if (matcher.find()) {
            mStrikethrough = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mStrikethrough.add(matcher.group(i).replace("~~", ""));
            }

            return mStrikethrough;
        }

        return Collections.EMPTY_LIST;
    }

    private List readQuote(String message) {
        Matcher matcher = QUOTE_PATTERN.matcher(message);

        if (mQuote != null) {
            return mQuote;
        }

        if (matcher.find()) {
            mQuote = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mQuote.add(matcher.group(i).replace(">", ""));
            }

            return mQuote;
        }

        return Collections.EMPTY_LIST;
    }

    private List readItalics(String message) {
        Matcher matcher = ITALICS_PATTERN.matcher(message);

        if (mItalics != null) {
            return mItalics;
        }

        if (matcher.find()) {
            mItalics = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mItalics.add(matcher.group(i).replace("*", ""));
            }

            return mBold;
        }

        return Collections.EMPTY_LIST;
    }

    private List readLinks(String message) {
        Matcher matcher = LINK_PATTERN.matcher(message);

        if (mLinks != null) {
            return mLinks;
        }

        if (matcher.find()) {
            mLinks = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mLinks.add(matcher.group(i).replaceFirst("\\[]\\(\\bhttp\\b://\\)", ""));
            }

            return mLinks;
        }

        return Collections.EMPTY_LIST;
    }

    private List readImageLinks(String message) {
        Matcher matcher = IMAGE_LINK_PATTERN.matcher(message);

        if (mImageLinks != null) {
            return mImageLinks;
        }

        if (matcher.find()) {
            mImageLinks = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mImageLinks.add(matcher.group(i).replaceFirst("!\\[\\balt\\b]\\(\\bhttp\\b://.*?\\)", ""));
            }

            return mLinks;
        }

        return Collections.EMPTY_LIST;
    }

    private List readIssues(String message) {
        Matcher matcher = ISSUE_PATTERN.matcher(message);

        if (mIssues != null) {
            return mIssues;
        }

        if (matcher.find()) {
            mIssues = new ArrayList<>();

            for (int i = 0; i < matcher.groupCount(); i++) {
                mIssues.add(matcher.group(i).replace("#", ""));
            }

            return mIssues;
        }

        return Collections.EMPTY_LIST;
    }

    public List getSinglelineCode() {
        return readSinglelineCode(mMessage);
    }

    public List getMultilineCode() {
        return readMultilineCode(mMessage);
    }

    public List getBold() {
        return readBold(mMessage);
    }

    public List getStrikethrough() {
        return readStrikethrough(mMessage);
    }

    public List getQuote() {
        return readQuote(mMessage);
    }

    public List getItalics() {
        return readItalics(mMessage);
    }

    public List getLinks() {
        return readLinks(mMessage);
    }

    public List getImageLinks() {
        return readImageLinks(mMessage);
    }

    public List getIssues() {
        return readIssues(mMessage);
    }

    public List getText() {
        return readText(mMessage);
    }
}