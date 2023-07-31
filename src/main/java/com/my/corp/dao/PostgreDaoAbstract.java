package com.my.corp.dao;

import com.my.corp.utils.LoggerMessages;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Log4j2
public abstract class PostgreDaoAbstract implements Dao {

    private static final ResourceBundle resources = ResourceBundle.getBundle("constant");

    @Override
    public abstract <T> T get(String userID, String cvID);

    protected String getConnectionString() {
        String host = resources.getString("host");
        String port = resources.getString("port");
        String database = resources.getString("database");
        return String.format(resources.getString("connectionString"), host, port, database);
    }

    protected String getQuery(String fileName) {
        /*URL url = this.getClass().getClassLoader().getResource("sql/" + fileName);
        try {
            System.out.println(url.getPath());
            Path path = Paths.get(url.toURI());
            return Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e) {
            log.error(LoggerMessages.getMessage(e));
        }
         */

        InputStream in = getClass().getClassLoader().getResourceAsStream("sql/" + fileName);
        if (Objects.isNull(in)){
            log.error("Query is NULL. Probably query file is missing.");
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();
       try {
           String line = reader.readLine();
           while (Objects.nonNull(line)){
               builder.append(line).append("\n");
               line=reader.readLine();
           }
       }catch (IOException ex){
           ex.printStackTrace();
       }
        return builder.toString();
    }

    public Connection getConnection() throws SQLException {
        String clazz = resources.getString("jdbcClass");
        try {
            Class.forName(clazz);
        } catch (ClassNotFoundException exception) {
            log.error(LoggerMessages.getMessage(exception));
        }
        String connectionString = getConnectionString();
        String userName = resources.getString("user");
        String password = resources.getString("password");
        log.info(LoggerMessages.getMessage("get.connection", connectionString));
        return DriverManager.getConnection(connectionString,
                userName, password);
    }
}
