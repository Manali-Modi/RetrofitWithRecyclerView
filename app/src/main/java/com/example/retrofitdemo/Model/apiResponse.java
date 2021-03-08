package com.example.retrofitdemo.Model;

import java.util.List;

public class apiResponse {

    List<Data> data;

    public apiResponse(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
