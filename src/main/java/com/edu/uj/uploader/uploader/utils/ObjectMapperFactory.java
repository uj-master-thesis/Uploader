package com.edu.uj.uploader.uploader.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class ObjectMapperFactory {

    public static ObjectMapper createObjectMapper() {
        return JsonMapper.builder()
                .enable(DeserializationFeature.USE_LONG_FOR_INTS)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .build();
    }
}
