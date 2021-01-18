package org.epubmaker.Util;

public class HtmlMaker {

    public static String getDiv(String givenString){
        return String.format("<div>%s</div>", givenString);
    }

    public static String getParagraph(String givenString){
        return String.format("<p>%s</p>", givenString);
    }

    public static String getTitle(String title){
        return String.format("<h1>%s</h1>", title);
    }

}
