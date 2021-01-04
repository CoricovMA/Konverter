package org.epubmaker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.epubmaker.JPMTL.JPUtil.JPSearch;
import org.epubmaker.JPMTL.JPUtil.NovelResponse;
import org.epubmaker.JPMTL.JPUtil.SearchResponse;
import org.epubmaker.Request.RequestMaker;
import org.json.JSONObject;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        JPSearch.init();

        ObjectMapper mapper = new ObjectMapper();
        JSONObject json = new JSONObject("{\n" +
                "        \"id\": 219,\n" +
                "        \"content_type\": \"novel\",\n" +
                "        \"created_at\": \"2020-04-15T16:26:59.804Z\",\n" +
                "        \"author\": \"Enomoto Kaisei\",\n" +
                "        \"language\": \"ja\",\n" +
                "        \"cover\": \"https://img.jpmtl.com/1/7869a950-7f3b-11ea-a3ae-39b762c3f9a9\",\n" +
                "        \"user_id\": 1,\n" +
                "        \"title\": \"The 5000-year-old Herbivorous Dragon\",\n" +
                "        \"synopsis\": \"Saying 5000 year-old-dragon has an impressive ring to it, yet that dragon was an extremely harmless, herbivorous being. However, because of his uselessly giant figure and scary appearance, he was mistaken for the Demon King’s executive and provided by the nearby village with a sacrificial maiden.\\nFundamentally cowardly and timid dragon tried to send her away, but the extremely enthusiastic sacrifice didn’t want to give up. Inevitably, he lied: “Okay, I just ate a small portion of your soul, so you can go back already”, but the maiden with outrageous assumptions has misunderstood that she became the dragon’s kin.\\nThen, out of all things, she began to manifest strange power on the basis of her wrong assumptions – in turn, the helpless dragon, by some mistake started a rebellion against the Demon King.\\nRebellion or whatnot, he wasn’t even his subordinate in the first place.\\n\",\n" +
                "        \"main_genre_id\": 1,\n" +
                "        \"status\": \"ongoing\",\n" +
                "        \"rating\": null,\n" +
                "        \"bookmark_count\": 6,\n" +
                "        \"genres\": [\n" +
                "            {\n" +
                "                \"id\": 30,\n" +
                "                \"name\": \"Action\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 13,\n" +
                "                \"name\": \"Adventure\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": 23,\n" +
                "                \"name\": \"Comedy\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"pen_name\": \"MTL Translator\",\n" +
                "        \"word_count\": 264113,\n" +
                "        \"chapter_count\": 203,\n" +
                "        \"views\": 5463,\n" +
                "        \"name\": \"Fantasy\",\n" +
                "        \"review_count\": null\n" +
                "    }".trim().strip());

//        System.out.println(json);
//            NovelResponse res = mapper.readValue(json.toString(), NovelResponse.class);
            System.out.println(JPSearch.findBook("2417"));

    }
}
