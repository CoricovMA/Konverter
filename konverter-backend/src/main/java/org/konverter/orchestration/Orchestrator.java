package org.konverter.orchestration;

import org.konverter.Util.REQUEST_TYPE;
import org.konverter.objects.KonverterRequestFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Orchestrator {

    private static final Logger logger = LoggerFactory.getLogger(Orchestrator.class);
    private static final ThreadPoolExecutor ebookExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    private static final ThreadPoolExecutor pdfExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    public static Future<byte[]> executeRequest(List<MultipartFile> fileList, REQUEST_TYPE type){

        logger.info("Submitting the task to the executor.");

        if(type == REQUEST_TYPE.PDF){

            return pdfExecutor.submit(KonverterRequestFactory.newPdfRequest(fileList));

        }else if(type == REQUEST_TYPE.EBOOK){

            return ebookExecutor.submit(KonverterRequestFactory.newEbookRequest(fileList));
        }

        return null;
    }

}
