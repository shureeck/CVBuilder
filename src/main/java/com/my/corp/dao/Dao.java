package com.my.corp.dao;

public interface Dao {
    <T> T get(String userID, String cvID);
}
