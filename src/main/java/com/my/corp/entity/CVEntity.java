package com.my.corp.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class CVEntity {
    private String firstName;
    private String lastName;
    private String position;
    private List<String> skills;
    private List<CVContacts> contactsList;
    private String description;
    private List<CVWork> workExperience;
    private List<CVEducation> education;

    public String getFullName() {
        return firstName + " " + lastName;
    }

}


