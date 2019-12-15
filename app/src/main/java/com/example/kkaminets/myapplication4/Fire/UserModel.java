package com.example.kkaminets.myapplication4.Fire;


import java.io.Serializable;

public class UserModel implements Serializable {

    String cost,firstName,time,count,unit,key;

    public UserModel(){
    }

    public UserModel(String cost, String firstName, String time,String count,String unit, String key) {
        this.cost= cost;
        this.firstName = firstName;
        this.time = time;
        this.unit= unit;
        this.count= count;
        this.key = key;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
    public String getCost() {
        return cost;
    }

    public void setCost(String job) {
        this.cost = job;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

