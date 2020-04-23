package com.dristi.phonecard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

public class Infodb extends SQLiteOpenHelper {

    static String name = "Info";
    static int version = 1;

    String sqlCreateUserTable = "CREATE TABLE if not exists 'userinfo' (\n" +
            "\t`Id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Firstname`\tTEXT,\n" +
            "\t`Lastname`\tTEXT,\n" +
            "\t`Username`\tTEXT,\n" +
            "\t`Password`\tTEXT,\n" +
            "\t`Email`\tTEXT,\n" +
            "\t`Phone`\tTEXT,\n" +
            "\t`Address`\tTEXT,\n" +
            "\t`Company`\tTEXT,\n" +
            "\t`Post`\tTEXT,\n" +
            "\t`Userpic`\tBLOB,\n" +
            "\t`Ucardpic`\tBLOB\n" +
            ")";

    String sqlCreateCardTable = "CREATE TABLE if not exists 'cardinfo' (\n" +
            "\t`Id`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t`Name`\tTEXT,\n" +
            "\t`Company`\tTEXT,\n" +
            "\t`Post`\tTEXT,\n" +
            "\t`Mobile`\tTEXT,\n" +
            "\t`Landline`\tTEXT,\n" +
            "\t`Fax`\tTEXT,\n" +
            "\t`Address`\tTEXT,\n" +
            "\t`Email`\tTEXT,\n" +
            "\t`Website`\tTEXT,\n" +
            "\t`Category`\tTEXT,\n" +
            "\t`Cardpic`\tBLOB\n" +
            ")";

    public Infodb(Context context) {

        super(context, name, null, version);

        getWritableDatabase().execSQL(sqlCreateUserTable);
        getWritableDatabase().execSQL(sqlCreateCardTable);

    }

    public void insertuser(ContentValues contentValues) {

        getWritableDatabase().insert("userinfo", "", contentValues);

    }

    public void insertcard(ContentValues contentValues) {

        getWritableDatabase().insert("cardinfo", "", contentValues);

    }

    public void updatecard(int id, ContentValues contentValues){

        getWritableDatabase().update("cardinfo",contentValues,"Id =" +id,null);

    }

    public void deletecard(int id){

        getWritableDatabase().delete("cardinfo","Id =" +id,null);

    }

    public boolean isLoginSucess(String username, String password) {

        String sql = "select count(*) from userinfo where Username = '" + username + "' and Password = '" + password + "'";

        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long count = statement.simpleQueryForLong();
        statement.close();

        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Long getUserId(String username, String password) {

        String sql = "select id from userinfo where Username = '" + username + "' and Password = '" + password + "'";

        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long id = statement.simpleQueryForLong();
        statement.close();

        return id;

    }

    public Userinfo getuserinfo(String id){

        String sql = "select * from userinfo where Id = " + id ;

        Log.i("kmk",sql);

        Cursor c = getReadableDatabase().rawQuery(sql,null);

        Userinfo info = new Userinfo();

        while (c.moveToNext()){
            info.id = c.getString(c.getColumnIndex("Id"));
            info.fname = c.getString(c.getColumnIndex("Firstname"));
            info.lname = c.getString(c.getColumnIndex("Lastname"));
            info.uname = c.getString(c.getColumnIndex("Username"));
            info.password = c.getString(c.getColumnIndex("Password"));
            info.email = c.getString(c.getColumnIndex("Email"));
            info.phone = c.getString(c.getColumnIndex("Phone"));
            info.address = c.getString(c.getColumnIndex("Address"));
            info.company = c.getString(c.getColumnIndex("Company"));
            info.post = c.getString(c.getColumnIndex("Post"));
            info.ucardpic = c.getBlob(c.getColumnIndex("Ucardpic"));
            info.userpic = c.getBlob(c.getColumnIndex("Userpic"));
        }
        c.close();
        return info;
    }

    public ArrayList<Cardinfo> getcardlistcomp() {

        String sql = "select * from cardinfo order by Company";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        ArrayList<Cardinfo> list = new ArrayList<>();

        while (c.moveToNext()) {
            Cardinfo info = new Cardinfo();
            info.id = c.getInt(c.getColumnIndex("Id"));
            info.name = c.getString(c.getColumnIndex("Name"));
            info.company = c.getString(c.getColumnIndex("Company"));
            info.email = c.getString(c.getColumnIndex("Email"));
            info.address = c.getString(c.getColumnIndex("Address"));
            info.post = c.getString(c.getColumnIndex("Post"));
            info.mobile = c.getString(c.getColumnIndex("Mobile"));
            info.fax = c.getString(c.getColumnIndex("Fax"));
            info.website = c.getString(c.getColumnIndex("Website"));
            info.category = c.getString(c.getColumnIndex("Category"));
            info.cardpic = c.getBlob(c.getColumnIndex("Cardpic"));
            list.add(info);
        }
        c.close();
        return list;
    }

    public ArrayList<Cardinfo> getcardlistname() {

        String sql = "select * from cardinfo order by Name";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        ArrayList<Cardinfo> list = new ArrayList<>();

        while (c.moveToNext()) {
            Cardinfo info = new Cardinfo();
            info.id = c.getInt(c.getColumnIndex("Id"));
            info.name = c.getString(c.getColumnIndex("Name"));
            info.company = c.getString(c.getColumnIndex("Company"));
            info.email = c.getString(c.getColumnIndex("Email"));
            info.address = c.getString(c.getColumnIndex("Address"));
            info.post = c.getString(c.getColumnIndex("Post"));
            info.mobile = c.getString(c.getColumnIndex("Mobile"));
            info.fax = c.getString(c.getColumnIndex("Fax"));
            info.website = c.getString(c.getColumnIndex("Website"));
            info.category = c.getString(c.getColumnIndex("Category"));
            info.cardpic = c.getBlob(c.getColumnIndex("Cardpic"));
            list.add(info);
        }
        c.close();
        return list;
    }

    public ArrayList<String> getcardnamelist() {

        String sql = "select * from cardinfo";

        Cursor c = getReadableDatabase().rawQuery(sql,null);

        ArrayList<String> list = new ArrayList<>();

        while (c.moveToNext()) {
            list.add(c.getString(c.getColumnIndex("Name")));
        }
        c.close();
        return list;
    }

    public ArrayList<Cardinfo> getcardcategoryname(String categ) {

        String sql = "select * from cardinfo where Category = '" +categ+ "' order by Name";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        ArrayList<Cardinfo> list = new ArrayList<>();

        while (c.moveToNext()) {
            Cardinfo info = new Cardinfo();
            info.id = c.getInt(c.getColumnIndex("Id"));
            info.name = c.getString(c.getColumnIndex("Name"));
            info.company = c.getString(c.getColumnIndex("Company"));
            info.email = c.getString(c.getColumnIndex("Email"));
            info.address = c.getString(c.getColumnIndex("Address"));
            info.post = c.getString(c.getColumnIndex("Post"));
            info.mobile = c.getString(c.getColumnIndex("Mobile"));
            info.fax = c.getString(c.getColumnIndex("Fax"));
            info.website = c.getString(c.getColumnIndex("Website"));
            info.category = c.getString(c.getColumnIndex("Category"));
            info.cardpic = c.getBlob(c.getColumnIndex("Cardpic"));
            list.add(info);
        }
        c.close();
        return list;
    }

    public ArrayList<Cardinfo> getcardcategorycomp(String categ) {

        String sql = "select * from cardinfo where Category = '" +categ+ "' order by Company";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        ArrayList<Cardinfo> list = new ArrayList<>();

        while (c.moveToNext()) {
            Cardinfo info = new Cardinfo();
            info.id = c.getInt(c.getColumnIndex("Id"));
            info.name = c.getString(c.getColumnIndex("Name"));
            info.company = c.getString(c.getColumnIndex("Company"));
            info.email = c.getString(c.getColumnIndex("Email"));
            info.address = c.getString(c.getColumnIndex("Address"));
            info.post = c.getString(c.getColumnIndex("Post"));
            info.mobile = c.getString(c.getColumnIndex("Mobile"));
            info.fax = c.getString(c.getColumnIndex("Fax"));
            info.website = c.getString(c.getColumnIndex("Website"));
            info.category = c.getString(c.getColumnIndex("Category"));
            info.cardpic = c.getBlob(c.getColumnIndex("Cardpic"));
            list.add(info);
        }
        c.close();
        return list;
    }

    ArrayList<String> categories = new ArrayList<>();

    public ArrayList<String> getcategorylist(){

        categories.add("Category");
        categories.add("Educational Institutions");
        categories.add("Corporates and Industries");
        categories.add("Hotels and Restaurants");
        categories.add("Financial Institutions");
        categories.add("Stores");
        categories.add("Travels and Tours");

        return categories;

    }

    public Cardinfo getcardinfo(int id) {

        String sql = "select * from cardinfo where Id = " + id;

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        Cardinfo info = new Cardinfo();

        while (c.moveToNext()) {
            info.id = c.getInt(c.getColumnIndex("Id"));
            info.name = c.getString(c.getColumnIndex("Name"));
            info.company = c.getString(c.getColumnIndex("Company"));
            info.email = c.getString(c.getColumnIndex("Email"));
            info.address = c.getString(c.getColumnIndex("Address"));
            info.post = c.getString(c.getColumnIndex("Post"));
            info.mobile = c.getString(c.getColumnIndex("Mobile"));
            info.landline = c.getString(c.getColumnIndex("Landline"));
            info.fax = c.getString(c.getColumnIndex("Fax"));
            info.website = c.getString(c.getColumnIndex("Website"));
            info.category = c.getString(c.getColumnIndex("Category"));
            info.cardpic = c.getBlob(c.getColumnIndex("Cardpic"));
        }
        c.close();
        return info;
    }

    public Cardinfo getcardinfosearch(String name) {

        String sql = "select * from cardinfo where Name = '"+name+"'";

        Cursor c = getReadableDatabase().rawQuery(sql, null);

        Cardinfo info = new Cardinfo();

        while (c.moveToNext()) {
            info.id = c.getInt(c.getColumnIndex("Id"));
            info.name = c.getString(c.getColumnIndex("Name"));
            info.company = c.getString(c.getColumnIndex("Company"));
            info.email = c.getString(c.getColumnIndex("Email"));
            info.address = c.getString(c.getColumnIndex("Address"));
            info.post = c.getString(c.getColumnIndex("Post"));
            info.mobile = c.getString(c.getColumnIndex("Mobile"));
            info.landline = c.getString(c.getColumnIndex("Landline"));
            info.fax = c.getString(c.getColumnIndex("Fax"));
            info.website = c.getString(c.getColumnIndex("Website"));
            info.category = c.getString(c.getColumnIndex("Category"));
            info.cardpic = c.getBlob(c.getColumnIndex("Cardpic"));
        }
        c.close();
        return info;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
