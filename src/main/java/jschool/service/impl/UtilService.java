package jschool.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/** Service for aditional methids, used everywhere*/
public class UtilService {
    /**
     * parses json input into object
     * */
    public static JsonNode parseJsonInput(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return jsonNode;
        }
    }
}
