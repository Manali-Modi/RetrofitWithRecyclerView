package com.example.retrofitdemo.model;

import java.util.List;

public class Users {

    private List<Data> data;

    public Users(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }
    public void setData(List<Data> data) {
        this.data = data;
    }
}
