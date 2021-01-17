package org.epubmaker;

import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        File f = new File("text.txt");
        System.out.println(FilenameUtils.isExtension(f.getName(), "txt"));
//        SpringApplication.run(App.class, args);
    }

}
