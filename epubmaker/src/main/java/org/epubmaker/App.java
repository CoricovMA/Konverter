package org.epubmaker;

import org.epubmaker.Parser.Tag;
import org.jsoup.Connection;

public class App
{
    public static void main( String[] args )
    {
        Tag t = new Tag("class", "test");
        System.out.println(t.toString());
        System.out.println( "Hello World!" );
    }
}
