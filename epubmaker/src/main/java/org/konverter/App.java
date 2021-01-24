package org.konverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
//        File f = new File("text.txt");
//        System.out.println(FilenameUtils.isExtension(f.getName(), "txt"));
        SpringApplication.run(App.class, args);
    }

}
