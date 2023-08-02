package com.my.corp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.corp.configuration.SpringConfig;
import com.my.corp.dao.CVDao;
import com.my.corp.dao.CVDaoPostgreSQL;
import com.my.corp.dao.Dao;
import com.my.corp.entity.CVEntity;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedHashMap;
import java.util.Map;


public class Main {
    public static LinkedHashMap a  = new LinkedHashMap(Map.of("key1", "value1", "map2", "value2"));
    public static void main(String... args) throws JsonProcessingException {
       // new Handler().handle(a);
      Dao dao = new AnnotationConfigApplicationContext(SpringConfig.class)
               .getBean("CVDaoPostgreSQL", CVDaoPostgreSQL.class);
       CVEntity entity = dao.get("poliakovaleek", "firstcv");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity));
        System.out.println("Done");


    }
}
