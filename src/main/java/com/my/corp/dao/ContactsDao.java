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
import java.util.List;
import java.util.Objects;

@Log4j2
@Component
public class ContactsDao extends PostgreDaoAbstract {

    @Override
    public List<CVContacts> get(java.lang.String userID, java.lang.String cvID) {
        String query = getQuery("select_contacts_cv.sql");
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

    private List<CVContacts> parseResultSet(ResultSet resultSet) throws SQLException {
        List<CVContacts> list = new ArrayList<>();
        if (Objects.nonNull(resultSet)) {
            while (resultSet.next()) {
                String type = resultSet.getString("contact_type");
                String data = resultSet.getString("contact_data");
                list.add(new CVContacts(type, data));
            }
        }
        return list;
    }
}
