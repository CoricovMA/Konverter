package org.konverter.Util;

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

    public static String prettyParagraph(String given){
        String [] paragraphs = given.split("\\.");
        StringBuilder sb = new StringBuilder();
        StringBuilder inner = new StringBuilder();
        int count = 0;
        for(String str : paragraphs){
            if(count == 5){
                sb.append(HtmlMaker.getParagraph(inner.toString()));
                inner = new StringBuilder();
                inner.append(String.format("%s. ", str));
                count = 0;
            }else{
                count++;
                inner.append(String.format("%s. ", str));
            }
        }

        return HtmlMaker.getDiv(sb.toString());
    }
}
