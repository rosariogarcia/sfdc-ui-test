package com.salesforce.dev.framework.utils;

/**
 * @author Walter Mercado on 6/22/2015.
 * @since 6/22/2015.
 * @author DanielGonzales
 * @since 7/26/2016.
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.dev.framework.dto.Account;
import com.salesforce.dev.framework.dto.Campaign;
import com.salesforce.dev.framework.dto.Chatter;
import com.salesforce.dev.framework.dto.ViewSalesForce;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.salesforce.dev.framework.utils.Constants.SRC_TEST_RESOURCES_JSON;


public final class DataDrivenManager {
    private static final Logger LOGGER = Logger.getLogger(DataDrivenManager.class.getName());

    private DataDrivenManager() {
    }

    public static Iterator<Object[]> getObjects(String nameJson, Class<?> elementClass) {
        List<Object[]> objectsArray = new ArrayList<>();

        try {
            final String pathFileJson = SRC_TEST_RESOURCES_JSON.concat(nameJson);
            Object jsonObject = new JSONParser().parse(new FileReader(pathFileJson));

            ObjectMapper objectMapper = new ObjectMapper();
            List<Object> objectList = objectMapper.readValue(((JSONArray) jsonObject).toJSONString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, elementClass));

            for (Object object : objectList) {
                objectsArray.add(new Object[]{object});
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return objectsArray.iterator();
    }

    }
