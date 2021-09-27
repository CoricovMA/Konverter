package org.konverter.objects;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface KonverterObject {

    void convert(List<MultipartFile> files);

    byte [] getConvertedObjectAsBytes();
}
