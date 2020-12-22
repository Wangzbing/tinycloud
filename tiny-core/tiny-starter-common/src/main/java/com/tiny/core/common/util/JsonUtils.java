package com.tiny.core.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author
 * @date
 * @todo:TODO version:1.0
 */
public final class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T JsonToObj(String json, Class<T> object) {
        T t = null;
        try {
            MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
            t = MAPPER.readValue(json, object);
        } catch (Exception e) {
            log.warn("An error occurred while  json convert to object ： ", e);
        }
        return t;
    }

    public static <T> String objectToString(T object) {
        String json = "";
        try {
            MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
            MAPPER.setDateFormat(new SimpleDateFormat("yyyyMMddHHmmss"));
            json = MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("An error occurred while object convert to json ：", e);
        }
        return json;
    }

    public static <T> JSONObject objectToJson(T object) throws JSONException {
        String jsonStr = "";
        try {
            jsonStr = MAPPER.writeValueAsString(object);
        } catch (IOException e) {
            log.error("An error occurred while object convert to json ：", e);
        }
        return new JSONObject(jsonStr);
    }

    public static <T> byte[] objectToByte(T object) throws JsonProcessingException {

        byte[] result = new byte[0];

        try {
            result = MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            log.warn("An error occurred while object convert to byte ：", e);
        }

        return result;
    }

    public static <T> T byteToObject(byte[] object, Class<T> clazz) {

        T t = null;
        try {
            t = MAPPER.readValue(object, clazz);
        } catch (IOException e) {
            log.warn("An error occurred while byte convert to object ：", e);
        }

        return t;
    }

    public static <T> T stringToObject(String object, TypeReference<T> valueTypeRef) {

        T t = null;
        try {
            t = MAPPER.readValue(object, valueTypeRef);
        } catch (IOException e) {
            log.warn("An error occurred while byte convert to object ：", e);
        }

        return t;
    }
    
    public static <T> T byteToObject(byte[] object, TypeReference<T> valueTypeRef) {

        T t = null;
        try {
            t = MAPPER.readValue(object, valueTypeRef);
        } catch (IOException e) {
            log.warn("An error occurred while byte convert to object ：", e);
        }

        return t;
    }

    private JsonUtils() {

    }
}
