package org.konverter.objects;

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
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

public class KonverterEbook implements KonverterObject{

    private static final Logger logger = LogManager.getLogger();

    private final EpubWriter writer = new EpubWriter();
    private final ByteArrayOutputStream convertedBookOutputStream = new ByteArrayOutputStream();


    private String bookTitle;
    private String aggregatedTextFromFiles;
    private String convertedBookAsString;

    @Override
    public void convert(List<MultipartFile> files) {
        this.setBookTitle(files);
        this.aggregateGivenText(files);
        this.convertAggregatedTextToHtml();
        this.addHtmlTextToEbook();
    }

    private void setBookTitle(List<MultipartFile> files) {
        this.bookTitle = (files.size() > 1)
                ? files.get(0).getName().split("\\.")[0]
                : UUID.randomUUID().toString();
    }

    @Override
    public byte[] getConvertedObjectAsBytes() {
        return this.convertedBookOutputStream.toByteArray();
    }

    private void aggregateGivenText(List<MultipartFile> files){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        logger.info("Aggregating file contents into one stream.");
        long start = System.nanoTime();

        files.stream().sequential().forEach(
                file ->{

                    try{
                        logger.info("Appending file contents to ByteStream");

                        bos.write(file.getBytes());

                    } catch (IOException e) {

                        logger.warn("There was an issue reading the file contents. {}", e.getMessage());

                    }
                }
        );

        this.aggregatedTextFromFiles = bos.toString().trim();

        logger.info("Files aggregated in {}ns." , (System.nanoTime() - start));
    }

    private void convertAggregatedTextToHtml(){
        this.convertedBookAsString = HtmlHelper.getTitle(this.bookTitle) +
                HtmlHelper.prettyParagraph(aggregatedTextFromFiles);
    }

    private void addHtmlTextToEbook(){
        logger.info("Writing text to ebook.");
        long start = System.nanoTime();

        Book ebook = new Book();
        Metadata metadata = new Metadata();
        metadata.addTitle(this.bookTitle);
        ebook.addSection(this.bookTitle,
                new Resource(this.convertedBookAsString.getBytes(StandardCharsets.UTF_8), MediatypeService.XHTML));

        try{

            writer.write(ebook, convertedBookOutputStream);

        } catch (IOException e) {

            logger.warn("Something went wrong while writing contents to ebook. {}", e.getMessage());

        }

        logger.info("Finished writing text to ebook. Action took {}ns.", (System.nanoTime() - start));
    }
}
