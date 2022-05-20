package com.example.orderapp.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.orderapp.Model.Coffee;
import com.example.orderapp.Model.Order;
import com.example.orderapp.Model.User;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "order.db";
    private static int DATABASE_VERSION = 3;

    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "username TEXT," +
                "password TEXT," +
                "role INTEGER)";
        //chay lenh
        db.execSQL(sql);

        String sql1 = "CREATE TABLE coffees(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "img INTEGER," +
                "name TEXT," +
                "price TEXT," +
                "des TEXT)";
        //chay lenh
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE orders(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "user_id INTEGER," +
                "coffee_id INTEGER," +
                "address TEXT)";
        //chay lenh
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("ALTER TABLE orders ADD COLUMN quanity INTEGER DEFAULT 1");
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public User checkLogin(String username, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereClause = "username = ? and password = ?";
        String[] whereArgs ={username, password};
        Cursor rs = sqLiteDatabase.query("users", null, whereClause, whereArgs,
                null, null, null);

        while ((rs != null) && (rs.moveToNext())) {
            User user = new User();
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String u = rs.getString(2);
            String p = rs.getString(3);
            int role = rs.getInt(4);
            user.setId(id);
            user.setName(name);
            user.setUsername(u);
            user.setPassword(p);
            user.setRole(role);
            return user;
        }
        return null;
    }

    public List<User> getAllUser() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("users", null, null, null,
                null, null, null);
        List<User> list = new ArrayList<>();
        while ((rs != null) && (rs.moveToNext())) {
            int id = rs.getInt(0);
            String name = rs.getString(1);
            String u = rs.getString(2);
            String p = rs.getString(3);
            int role = rs.getInt(4);
            list.add(new User(id,name, u, p ,role));
        }
        return list;
    }

    public void addUser(User user) {
        String sql = "INSERT INTO users(name, username, password, role)" +
                " VALUES(?,?,?,?)";
        String[] args = {user.getName(), user.getUsername(), user.getPassword(), Integer.toString(user.getRole())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public void editUser(User user) {
        String sql = "UPDATE users SET name = ?, password = ? WHERE id = ?";
        String[] args = {user.getName(), user.getPassword(), Integer.toString(user.getId())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public void delete(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public List<Coffee> getAllCoffee() {
        List<Coffee> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("coffees", null, null,
                null, null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            int img = rs.getInt(1);
            String name = rs.getString(2);
            String price = rs.getString(3);
            String des = rs.getString(4);
            list.add(new Coffee(id, img, name, price, des));
        }
        return list;
    }

    public void addCoffee(Coffee coffee) {
        String sql = "INSERT INTO coffees(img, name, price, des)" +
                "VALUES(?,?,?,?)";
        String[] args = {Integer.toString(coffee.getImg()), coffee.getName(), coffee.getPrice(), coffee.getDes()};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public void updateCoffee(Coffee cf) {
        String sql = "UPDATE coffees SET img = ?, name = ?, price = ?, des=? WHERE id = ?";
        String[] args = {Integer.toString(cf.getImg()), cf.getName(), cf.getPrice(),
                cf.getDes(), Integer.toString(cf.getId())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public Coffee getCoffeeById(int id) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String whereClause = "id = ?";
        String[] whereArgs ={Integer.toString(id)};
        Cursor rs = sqLiteDatabase.query("coffees", null, whereClause, whereArgs,
                null, null, null);

        while ((rs != null) && (rs.moveToNext())) {
            Coffee cf = new Coffee();
            int img = rs.getInt(1);
            String name = rs.getString(2);
            String price = rs.getString(3);
            String des = rs.getString(4);
            cf.setId(id);
            cf.setName(name);
            cf.setPrice(price);
            cf.setDes(des);
            return cf;
        }
        return null;
    }

    public void deleteCoffee(int id) {
        String sql = "DELETE FROM coffees WHERE id = ?";
        String[] args = {Integer.toString(id)};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public void addOrder(Order order) {
        String sql = "INSERT INTO orders(user_id, coffee_id, address, quanity)" +
                "VALUES(?,?,?,?)";
        String[] args = {Integer.toString(order.getUser_id()), Integer.toString(order.getCoffee_id()),
                order.getAddress(), Integer.toString(order.getQuanity())};
        SQLiteDatabase st = getWritableDatabase();
        st.execSQL(sql, args);
    }

    public List<Order> getAllOrderById(int iduser) {
        List<Order> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("orders", null, null,
                null, null, null, null);
        while ((rs != null) && rs.moveToNext()) {
            int id = rs.getInt(0);
            int userid = rs.getInt(1);
            int coffeeid = rs.getInt(2);
            String address = rs.getString(3);
            int quanity = rs.getInt(4);
            list.add(new Order(id, userid, coffeeid, address, quanity));
        }
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getUser_id() != iduser) {
                list.remove(i);
            }
        }
        return list;
    }



}
