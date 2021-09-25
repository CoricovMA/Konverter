package org.konverter.epub;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Metadata;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import nl.siegmann.epublib.service.MediatypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.konverter.Util.HtmlHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class KonverterBook {

    private static final Logger logger = LogManager.getLogger(KonverterBook.class);

    private final EpubWriter writer = new EpubWriter();
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private List<MultipartFile> files;

    private String bookTitle;
    private String receivedBookTextAggregated;
    private String generatedBookAsString;

    public KonverterBook(){}

    public void convertToEbook(){

        ByteArrayOutputStream bos= new ByteArrayOutputStream();

        this.bookTitle = files.get(0).getName().split("\\.")[0];

        appendByteStream(bos);

        this.receivedBookTextAggregated = bos.toString().trim();

        convertBook();

    }

    private void appendByteStream(ByteArrayOutputStream bos){

        files.stream().sequential().forEach(
                item ->{
                    try {
                        logger.info("Writing {} to ByteStream.", item.getName());

                        bos.write(item.getBytes());

                    } catch (IOException e) {

                        logger.warn("There was an error writing {} to ByteStream. {}", item.getName(), e.getMessage());

                    }
                }
        );

    }

    private void convertBook() {

        logger.info("Generating book");

        long start = System.currentTimeMillis();
        
        generateBookAsEpubHtml();
        
        writeToEbookObject();

        logger.info("Book generation done in {}ms", (System.currentTimeMillis() - start));
    }

    private void generateBookAsEpubHtml() {
        this.generatedBookAsString = HtmlHelper.getTitle(this.bookTitle) +
                HtmlHelper.prettyParagraph(receivedBookTextAggregated);
    }

    private void writeToEbookObject() {

        logger.info("Writing book.");

        Book convertedEbook = new Book();
        Metadata metadata = new Metadata();
        metadata.addTitle(this.bookTitle);
        convertedEbook.addSection(this.bookTitle, new Resource(this.generatedBookAsString.getBytes(), MediatypeService.XHTML));

        try {

            writer.write(convertedEbook, outputStream);

        } catch (IOException e) {

            logger.warn("There was an error writing the book. {}", e.getMessage());

        }

    }

    public byte [] getGeneratedEbookAsBytes(){
        return this.outputStream.toByteArray();
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
