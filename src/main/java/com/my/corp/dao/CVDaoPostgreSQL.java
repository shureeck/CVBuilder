package com.my.corp.dao;

import com.my.corp.entity.CVEntity;
import com.my.corp.utils.LoggerMessages;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;


@Log4j2
@Component
public class CVDaoPostgreSQL extends PostgreDaoAbstract {
    @Autowired
    @Qualifier("descriptionDao")
    Dao descriptionDao;
    @Autowired
    @Qualifier("contactsDao")
    Dao contactsDao;

    @Autowired
    @Qualifier("personalDataDao")
    Dao personalDao;
    @Autowired
    @Qualifier("skillsDao")
    Dao skilslDao;
    @Autowired
    @Qualifier("workDao")
    Dao workDao;

    @Autowired
    @Qualifier("educationDao")
    Dao educationDao;

    @Override
    public CVEntity get(String userID, String cvId) {
        HashMap<String, String> personalData = personalDao.get(userID, cvId);
        if (Objects.isNull(personalData)) {
            throw new RuntimeException("Personal data could be NULL.");
        }
        CVEntity cv = new CVEntity();
        cv.setFirstName(personalData.get("firstName"));
        cv.setLastName(personalData.get("lastName"));
        cv.setPosition(personalData.get("position"));
        cv.setSkills(skilslDao.get(userID, cvId));
        cv.setDescription(descriptionDao.get(userID, cvId));
        cv.setContactsList(contactsDao.get(userID, cvId));
        cv.setWorkExperience(workDao.get(userID, cvId));
        cv.setEducation(educationDao.get(userID, cvId));
        return cv;
    }
}
