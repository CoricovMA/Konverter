package org.epubmaker.Request;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseObject {
    protected static final ObjectMapper objectMapper = new ObjectMapper();
}
