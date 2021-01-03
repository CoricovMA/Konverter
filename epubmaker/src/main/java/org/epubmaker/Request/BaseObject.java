package org.epubmaker.Request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public interface BaseObject {

    Logger logger = LogManager.getLogger(BaseObject.class);

    ObjectMapper objectMapper = new ObjectMapper();

    BaseObject fromString(String json) throws IOException;

    String toString();
}
