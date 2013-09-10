package edu.gatech.cs2340.thenullterminators.database;

import java.util.ArrayList;

import edu.gatech.cs2340.thenullterminators.wheresmystuff.CreateItem;
import edu.gatech.cs2340.thenullterminators.wheresmystuff.Item;
import edu.gatech.cs2340.thenullterminators.wheresmystuff.Login;
import edu.gatech.cs2340.thenullterminators.wheresmystuff.Search;
import edu.gatech.cs2340.thenullterminators.wheresmystuff.User;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteOpenHelperWMS {

        public static final String KEY_ROWIDU = "_idu"; // row ID for users
        public static final String KEY_ROWIDI = "_idi"; // row ID for items
        public static final String KEY_USERNAME = "username";
        public static final String KEY_PASSWORD = "password";
        public static final String KEY_ISLOCKED = "locked";
        public static final String KEY_ISADMIN = "admin";
        public static final String KEY_PHONE = "phone";
        public static final String KEY_TITLE = "title";
        public static final String KEY_CATEGORY = "category";
        public static final String KEY_CITY = "city";
        public static final String KEY_STATE = "state";
        public static final String KEY_DESCRIPTION = "description";
        public static final String KEY_ISLOST = "islost";
        public static final String KEY_REWARD = "reward";
        public static final String KEY_MONTH = "month";
        public static final String KEY_DAY = "day";
        public static final String KEY_YEAR = "year";
        public static final String KEY_ISRESOLVED = "isresolved";
        public static final String KEY_ITEMUSER = "itemuser"; // the user who created item

        private static final String DATABASE_NAME = "wheresmystuffDB";
        private static final String DATABASE_TABLE_USERS = "userTable";
        private static final String DATABASE_TABLE_ITEMS = "itemTable";
        private static final int DATABASE_VERSION = 1;

        private DbHelper ourHelper;
        private final Context ourContext;
        private SQLiteDatabase ourDatabase;

        private static class DbHelper extends SQLiteOpenHelper {

                public DbHelper(Context context) {

                        super(context, DATABASE_NAME, null, DATABASE_VERSION);

                }

                @Override
                public void onCreate(SQLiteDatabase db) {

                        db.execSQL(

                                        "CREATE TABLE " + DATABASE_TABLE_USERS + " (" +                 // create table
                                        KEY_ROWIDU + " INTEGER PRIMARY KEY AUTOINCREMENT, " +   // row id
                                        KEY_USERNAME + " TEXT NOT NULL, " +                                     // user name
                                        KEY_PASSWORD + " TEXT NOT NULL, " +                                     // password
                                        KEY_PHONE    + " TEXT NOT NULL, "    +                                          // phone #
                                        KEY_ISADMIN  + " TEXT NOT NULL, "  +
                                        KEY_ISLOCKED + " TEXT);"

                                        );
                        //System.out.println("user table is created!!!");

                        db.execSQL(

                                        "CREATE TABLE " + DATABASE_TABLE_ITEMS + " (" +
                                        KEY_ROWIDI + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                        KEY_TITLE + " TEXT NOT NULL, " +
                                        KEY_CATEGORY + " TEXT NOT NULL, " +
                                        KEY_CITY + " TEXT NOT NULL, " +
                                        KEY_STATE + " TEXT NOT NULL, " +
                                        KEY_DESCRIPTION + " TEXT NOT NULL, " +
                                        KEY_ISLOST + " TEXT NOT NULL, " + // needs to be changed from boolean to string
                                        KEY_REWARD + " TEXT NOT NULL, " +
                                        KEY_MONTH + " INTEGER NOT NULL, " +
                                        KEY_DAY + " INTEGER NOT NULL, " +
                                        KEY_YEAR + " INTEGER NOT NULL, " +
                                        KEY_ISRESOLVED + " TEXT NOT NULL, " + // needs to be changed from boolean to string
                                        KEY_ITEMUSER + " TEXT);" // needs to be changed from user to string

                                        );
                }

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USERS);
                        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ITEMS);
                        onCreate(db);

                }

        }

        public SQLiteOpenHelperWMS(Context c) {

                ourContext = c;
                ourHelper = new DbHelper(ourContext);

        }

        public SQLiteOpenHelperWMS open() {

                //ourHelper = new DbHelper(ourContext);
                ourDatabase = ourHelper.getWritableDatabase();
                return this;

        }

        public void close(){
                ourHelper.close();
        }
        public void updateItemEntry(long lRow, String title, String category, String city, String state,
                                                                        String description, String isLost, String reward, int month,
                                                                                int day, int year, String isResolved, User ItemUser){
                ContentValues cv = new ContentValues();
                cv.put(KEY_TITLE, title);
                cv.put(KEY_CATEGORY, category);
                cv.put(KEY_CITY, city);
                cv.put(KEY_STATE, state);
                cv.put(KEY_DESCRIPTION, description);
                cv.put(KEY_ISLOST, isLost);
                cv.put(KEY_REWARD, reward);
                cv.put(KEY_MONTH, month);
                cv.put(KEY_DAY, day);
                cv.put(KEY_YEAR, year);
                cv.put(KEY_ISRESOLVED, isResolved);
                cv.put(KEY_ITEMUSER, ItemUser.getUserName());
                ourDatabase.update(DATABASE_TABLE_USERS, cv, KEY_ROWIDI+"="+ lRow, null);

        }
        public void updataUserEntry(long lRow, String name, String pass, String phone, String isAdmin,
                                                                        String isLocked){
                ContentValues cv = new ContentValues();
                cv.put(KEY_USERNAME, name);
                cv.put(KEY_PASSWORD, pass);
                cv.put(KEY_PHONE, phone);
                cv.put(KEY_ISADMIN, isAdmin);
                cv.put(KEY_ISLOCKED, isLocked);
                ourDatabase.update(DATABASE_TABLE_USERS, cv, KEY_ROWIDU + "=" + lRow, null);

        }
        public boolean deleteItem(long rowid){
                return ourDatabase.delete(DATABASE_TABLE_ITEMS, KEY_ROWIDI + "=" + rowid, null)>0;
        }

        public boolean deleteUser(long rowid){
                return ourDatabase.delete(DATABASE_TABLE_USERS, KEY_ROWIDU + "=" + rowid, null)>0;
        }

        public long createEntryItem(String title, String category, String city, String state,
                                                                                String description, String isLost, String reward, int month,
                                                                                                        int day, int year, String isResolved, User ItemUser){
                ContentValues cv = new ContentValues();
                cv.put(KEY_TITLE, title);
                cv.put(KEY_CATEGORY, category);
                cv.put(KEY_CITY, city);
                cv.put(KEY_STATE, state);
                cv.put(KEY_DESCRIPTION, description);
                cv.put(KEY_ISLOST, isLost);
                cv.put(KEY_REWARD, reward);
                cv.put(KEY_MONTH, month);
                cv.put(KEY_DAY, day);
                cv.put(KEY_YEAR, year);
                cv.put(KEY_ISRESOLVED, isResolved);
                cv.put(KEY_ITEMUSER, ItemUser.getUserName());
                return ourDatabase.insert(DATABASE_TABLE_ITEMS, null, cv);

        }
//              public long createEntryAdmin(String Username, String Password, String isAdmin){
//              ContentValues cv = new ContentValues();
//              cv.put(KEY_USERNAME, Username);
//              cv.put(KEY_PASSWORD, Password);
//              cv.put(KEY_ISADMIN,isAdmin);
//              return ourDatabase.insert(DATABASE_TABLE_USERS, null, cv);
//              }

        public long createEntryUser(String Username, String Password, String Phone,String isLocked, String isAdmin){
                ContentValues cv = new ContentValues();
                cv.put(KEY_USERNAME, Username);
                cv.put(KEY_PASSWORD, Password);
                cv.put(KEY_PHONE, Phone);
                cv.put(KEY_ISLOCKED,isLocked);
                cv.put(KEY_ISADMIN, isAdmin);
                System.out.println("I store an user " + Username);
                return ourDatabase.insert(DATABASE_TABLE_USERS, null, cv);

        }
        public long getItemRowid(String title){
                String[] columns = new String[]{KEY_ROWIDI,KEY_TITLE,KEY_CATEGORY,KEY_CITY,KEY_STATE,KEY_DESCRIPTION,KEY_ISLOST,
                                KEY_REWARD,KEY_MONTH,KEY_DAY,KEY_YEAR,KEY_ISRESOLVED,KEY_ITEMUSER};
                Cursor c = ourDatabase.query(DATABASE_TABLE_ITEMS, columns, KEY_TITLE+"="+title, null, null, null, null);
                c.moveToFirst();
                long rowid = c.getLong(c.getColumnIndex(KEY_ROWIDI));
                return rowid;
        }

        public long getUserRowid(String name){
                String[] columns = new String[]{KEY_ROWIDU,KEY_USERNAME,KEY_PASSWORD,KEY_PHONE,KEY_ISADMIN,KEY_ISLOCKED};
                Cursor c = ourDatabase.query(DATABASE_TABLE_USERS, columns, KEY_PHONE+"="+name, null, null, null, null);
                c.moveToFirst();
                long rowid = c.getLong(c.getColumnIndex(KEY_ROWIDU));
                return rowid;
        }

        public void getUserData(){
                String[] columns = new String[]{KEY_ROWIDU,KEY_USERNAME,KEY_PASSWORD,KEY_PHONE,KEY_ISADMIN,KEY_ISLOCKED};
                Cursor c = ourDatabase.query(DATABASE_TABLE_USERS, columns, null, null, null, null, null);
                String name,password,phone, isAdmin, isLocked;
                //String result = "";
                int iRow = c.getColumnIndex(KEY_ROWIDU);
                int iName = c.getColumnIndex(KEY_USERNAME);
                int iPassword = c.getColumnIndex(KEY_PASSWORD);
                int iPhone = c.getColumnIndex(KEY_PHONE);
                int iAdmin = c.getColumnIndex(KEY_ISADMIN);;
                int iLocked = c.getColumnIndex(KEY_ISLOCKED);
                Login.users = new ArrayList<User>();
                createEntryUser("admin", "admin", "","false","true");

                //int i = 0;
                for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                        //result = result + c.getString(iRow) + " " + c.getString(iName) + " " +
                                //      c.getString(iPassword) +" "; //+ c.getString(iPhone) + "\n";
                        name = c.getString(iName);
                        password = c.getString(iPassword);
                        phone = c.getString(iPhone);
                        isAdmin = c.getString(iAdmin);
                        isLocked = c.getString(iLocked);
                        System.out.println(name + " " + password);
//
                        if(isAdmin == null || isAdmin.equalsIgnoreCase("false") ){
                                Login.registerUser(name,password,phone);
                        }
////                    else if(isAdmin.equalsIgnoreCase("true"))
                        else Login.createAdmin(name, password);

                        for(int i = 0 ; i < Login.users.size(); i ++){
                                if(name.equalsIgnoreCase(Login.users.get(i).getUserName())){
                                        if(isLocked == null || isLocked.equalsIgnoreCase("false")){
                                                Login.users.get(i).setIsLocked(false);
                                        }
                                        else
                                                Login.users.get(i).setIsLocked(true);
                                }
                        }
        }
        }

        public void getItemData(){
                String[] columns = new String[]{KEY_ROWIDI,KEY_TITLE,KEY_CATEGORY,KEY_CITY,KEY_STATE,KEY_DESCRIPTION,KEY_ISLOST,
                                                                                        KEY_REWARD,KEY_MONTH,KEY_DAY,KEY_YEAR,KEY_ISRESOLVED,KEY_ITEMUSER};
                Cursor c = ourDatabase.query(DATABASE_TABLE_ITEMS, columns, null, null, null, null, null);
                //ArrayList<Item> tempItems = new ArrayList<Item>();
                Search.items = new ArrayList<Item>();
                //String title, category,city,state,description,reward,itemUser;
                boolean isLost,isResolved,check;
                isLost = true;
                isResolved = true;
                check = false;
                Integer month,day,year;
                month = day = year = 0;
                int iRow = c.getColumnIndex(KEY_ROWIDI);
                int iTitle = c.getColumnIndex(KEY_TITLE);
                int iCategory = c.getColumnIndex(KEY_CATEGORY);
                int iCity = c.getColumnIndex(KEY_CITY);
                int iState = c.getColumnIndex(KEY_STATE);
                int iDescription = c.getColumnIndex(KEY_DESCRIPTION);
                int iIslost = c.getColumnIndex(KEY_ISLOST);
                int iReward = c.getColumnIndex(KEY_REWARD);
                int iMonth = c.getColumnIndex(KEY_MONTH);
                int iDay = c.getColumnIndex(KEY_DAY);
                int iYear = c.getColumnIndex(KEY_YEAR);
                int iIsresolved = c.getColumnIndex(KEY_ISRESOLVED);
                int iItemUser = c.getColumnIndex(KEY_ITEMUSER);


                for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
                        month = month.parseInt(c.getString(iMonth));
                        day = day.parseInt(c.getString(iDay));
                        year = year.parseInt(c.getString(iYear));
                        if(c.getString(iIslost).equalsIgnoreCase("false")){
                                isLost = false;
                        }
                        if(c.getString(iIsresolved).equalsIgnoreCase("false")){
                                isResolved = false;
                        }

                        User temp = new User("","");
                        for(int i = 0; i < Login.users.size(); i++){
                                if(c.getString(iItemUser).equalsIgnoreCase(Login.users.get(i).getUserName())){
                                        temp = Login.users.get(i);
                                }
                        }
                        Search.items.add(new Item(c.getString(iTitle), c.getString(iCategory), c.getString(iCity), c.getString(iState),c.getString(iDescription), isLost, c.getString(iReward), iMonth, iDay, iYear, isResolved,temp.getUserName()));
                        //Search.items.add( new Item(title,category,city,state,description,isLost,reward,month,day,year,isResolved, user.getUserName()));
                }
        }
}
