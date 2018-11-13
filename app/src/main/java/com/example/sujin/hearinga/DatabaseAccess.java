package com.example.sujin.hearinga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public  static  DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }

    public void open()
    {
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null)
        {
            this.db.close();
        }
    }

    public List<Account> getAccount()
    {
        String sql = "select * from account order by UserID DESC";
        int index;
        c=db.rawQuery(sql,new String[]{});
        List<Account> list = new ArrayList<>();
        Account account;
        while (c.moveToNext()){
            index=c.getColumnIndex("UserID");
            int userID = c.getInt(index);

            index=c.getColumnIndex("Name");
            String name = c.getString(index);

            index = c.getColumnIndex("IconName");
            String iconName = c.getString(index);

            account = new Account(userID,name,iconName);
            list.add(account);
        }
        return  list;
    }

    public long insertAccount(Account account)
    {
        ContentValues content = new ContentValues();
        content.put("Name",account.name);
        content.put("IconName",account.iconName);
        long result = db.insert("Account",null,content);
        return  result;
    }

}
