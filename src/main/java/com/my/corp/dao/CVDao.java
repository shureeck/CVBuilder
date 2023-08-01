package com.my.corp.dao;

import com.my.corp.utils.LoggerMessages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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

public class CVDao extends PostgreDaoAbstract {
    @AllArgsConstructor
    private class CVBaseInf {
        @Getter
        @Setter
        private String cvID;
        @Getter
        @Setter
        private String description;
    }

    @Override
    public List<CVBaseInf> get(String userID, String cvID) {
        List<CVBaseInf> result = new ArrayList<>();
        String query = getQuery("select_cv_list.sql");
        log.info(LoggerMessages.getMessage("sql.parameters", "UserID: " + userID));
        log.info(query);
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (Objects.isNull(resultSet)) {
                return null;
            }
            while (resultSet.next()) {
                result.add(new CVBaseInf(resultSet.getString("cv_id"),
                        resultSet.getString("description")));
            }
            return result;
        } catch (SQLException exception) {
            log.error(exception);
        }
        return null;
    }
}
