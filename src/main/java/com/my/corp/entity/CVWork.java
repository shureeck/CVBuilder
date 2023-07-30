package com.my.corp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CVWork {
    private String period;
    private String position;
    private String organization;
    private String responsibility;

    public List<String> getResponsibilityList() {
        return Arrays.stream(responsibility.split("<ul>")).map(String::trim).filter(p -> !p.isEmpty()).collect(Collectors.toList());
    }
}
