package com.my.corp;

import com.my.corp.dao.CVDaoPostgreSQL;

public class Main {
    public static void main(String... args) {
        CVDaoPostgreSQL dao = new CVDaoPostgreSQL();
        dao.get("poliakovaleek", "firstcv");
    }
}
