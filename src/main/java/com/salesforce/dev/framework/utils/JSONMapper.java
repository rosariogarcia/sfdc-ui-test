package com.salesforce.dev.framework.utils;


import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.dev.framework.dto.Account;
import com.salesforce.dev.framework.dto.Contact;
import com.salesforce.dev.framework.dto.Lead;
import com.salesforce.dev.framework.dto.Opportunity;

import static com.salesforce.dev.framework.utils.Constants.SRC_TEST_RESOURCES_JSON;

/**
 * @author jimmy vargas on 6/22/2015.
 * @author Mijhail Villarroel
 */
public class JSONMapper {


    /**
     * Return T generic any class
     *
     * @param elementClass
     * @param nameJson Path of a json.
     * @return
     */
    public  static <T> T getGeneric(Class<T> elementClass, String nameJson) {
        final String pathFileJson = SRC_TEST_RESOURCES_JSON.concat(nameJson);
        Object result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
             result =mapper.readValue(new File(pathFileJson), elementClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (T)result;
    }



}
