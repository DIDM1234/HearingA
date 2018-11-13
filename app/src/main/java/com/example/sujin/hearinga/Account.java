package com.example.sujin.hearinga;

public class Account {
    int userID;
    String name;
    String iconName;

    public Account(int userID,String name,String iconName)
    {
        this.userID = userID;
        this.name = name;
        this.iconName= iconName;
    }

    public int getUserID(){return this.userID;}
    public String getName(){return this.name;}
    public String getIconName(){return iconName;}

}
