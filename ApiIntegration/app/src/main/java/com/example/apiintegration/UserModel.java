package com.example.apiintegration;

public class UserModel {

    int userId;
    int id;
    String title;

    UserModel(int userId,int id,String title){

        this.userId=userId;
        this.id=id;
        this.title=title;
    }

    public int getUserId(){
        return userId;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }
}
