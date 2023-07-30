package com.my.corp.dao;

import com.my.corp.entity.CVWork;
import com.my.corp.utils.LoggerMessages;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@Component
public class WorkDao extends PostgreDaoAbstract {

    @Override
    public List<CVWork> get(String userID, String cvID) {
        String query = getQuery("select_work_cv.sql");
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

    private List<CVWork> parseResultSet(ResultSet resultSet) throws SQLException {
        if (Objects.isNull(resultSet)) {
            return null;
        }
        List<CVWork> result = new ArrayList<>();
        while (resultSet.next()) {
            String period = resultSet.getString("period");
            String position = resultSet.getString("position");
            String company = resultSet.getString("company");
            String responsibility = resultSet.getString("reponsibility");
            result.add(new CVWork(period, position, company, responsibility));
        }
        return result;
    }
}
