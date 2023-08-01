package com.my.corp.dao;

import com.my.corp.entity.Secret;
import com.my.corp.utils.LoggerMessages;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

@Log4j2
public abstract class PostgreDaoAbstract implements Dao {

    private static final ResourceBundle resources = ResourceBundle.getBundle("constant");
    private final Secret secret = Secret.getInstance();

    @Override
    public abstract <T> T get(String userID, String cvID);

    protected String getConnectionString() {
        String host;
        String port;
        String database;
        if (secret.isValid()) {
            host = secret.getValue("host");
            port = secret.getValue("port");
            database = secret.getValue("dbname");
        } else {
            host = resources.getString("host");
            port = resources.getString("port");
            database = resources.getString("database");
        }
        return String.format(resources.getString("connectionString"), host, port, database);
    }

    protected String getUser() {
        if (secret.isValid()) {
            return secret.getValue("username");
        } else {
            return resources.getString("user");
        }
    }

    protected String getPassword() {
        if (secret.isValid()) {
            return secret.getValue("password");
        } else {
            return resources.getString("password");
        }
    }

    protected String getQuery(String fileName) {
        /* URL url = this.getClass().getClassLoader().getResource("sql/" + fileName);
        try {
            System.out.println(url.getPath());
            Path path = Paths.get(url.toURI());
            return Files.lines(path, StandardCharsets.UTF_8).collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e) {
            log.error(LoggerMessages.getMessage(e));
        }
         */

        InputStream in = getClass().getClassLoader().getResourceAsStream("sql/" + fileName);
        if (Objects.isNull(in)) {
            log.error("Query is NULL. Probably query file is missing.");
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();
        try {
            String line = reader.readLine();
            while (Objects.nonNull(line)) {
                builder.append(line).append("\n");
                line = reader.readLine();
            }
        } catch (IOException ex) {
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
        String userName = getUser();
        String password = getPassword();
        log.info(LoggerMessages.getMessage("get.connection", connectionString));
        return DriverManager.getConnection(connectionString,
                userName, password);
    }
}
