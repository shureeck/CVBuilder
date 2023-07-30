package com.my.corp.dao;

import com.my.corp.utils.LoggerMessages;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Log4j2
@Component
public class DescriptionDao extends PostgreDaoAbstract {

    @Override
    public String get(String userID, String cvID) {
        String query = getQuery("select_description_cv.sql");
        log.info(LoggerMessages.getMessage("sql.parameters", "UserID: " + userID + " CV ID: " + cvID));
        log.info(query);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userID);
            statement.setString(2, cvID);
            ResultSet resultSet = statement.executeQuery();
            if (Objects.isNull(resultSet)) {
                return null;
            }
            resultSet.next();
            return resultSet.getString("description");
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }
}
