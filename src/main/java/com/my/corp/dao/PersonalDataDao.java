package com.my.corp.dao;

import com.my.corp.entity.CVContacts;
import com.my.corp.utils.LoggerMessages;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Log4j2
@Component
public class PersonalDataDao extends PostgreDaoAbstract {

    @Override
    public HashMap<String, String> get(String userID, String cvID) {
        String query = getQuery("select_personal_data_cv.sql");
        log.info(LoggerMessages.getMessage("sql.parameters", "UserID: " + userID + " CV ID: " + cvID));
        log.info(query);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userID);
            statement.setString(2, cvID);
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }

    private HashMap<String, String> parseResultSet(ResultSet resultSet) throws SQLException {
        if (Objects.isNull(resultSet)) {
            return null;
        }
        HashMap<String, String> result = new HashMap<>();
        if (resultSet.next()) {
            result.put("firstName", resultSet.getString("first_name"));
            result.put("middleName", resultSet.getString("middle_name"));
            result.put("lastName", resultSet.getString("last_name"));
            result.put("position", resultSet.getString("position"));
        } else {
            return null;
        }
        return result;
    }
}
