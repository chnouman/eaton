package com.noumanch.eaton.models;

/**
 * Created by Nouman01 on 9/29/2017.
 */

public class Category {


    public Category() {
    }

    private String Name , path;

    public Category(String name, String path) {
        Name = name;
        this.path = path;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
