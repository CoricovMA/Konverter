package org.konverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        File f = new File("test.png");
        //        SpringApplication.run(App.class, args);
    }

}
