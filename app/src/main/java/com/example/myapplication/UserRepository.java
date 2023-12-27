package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private DbHelper dbHelper;

    public UserRepository(Context context) {
        dbHelper = new DbHelper(context);
    }

    // 添加用户
    public void addUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, user.getName());
        values.put(DbHelper.COLUMN_USERNAME, user.getUsername());
        values.put(DbHelper.COLUMN_PASSWORD, user.getPassword());
        db.insert(DbHelper.TABLE_USERS, null, values);
        db.close();
    }

    // 查询所有用户
    @SuppressLint("Range")
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DbHelper.TABLE_USERS, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ID)));
                user.setUsername(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_USERNAME)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_PASSWORD)));
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

    // 根据用户名查询用户
    @SuppressLint("Range")
    public User getUserByUsername(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.TABLE_USERS,
                null,
                DbHelper.COLUMN_USERNAME + " = ?",
                new String[]{username},
                null,
                null,
                null
        );

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_PASSWORD)));
        }

        cursor.close();
        db.close();
        return user;
    }

    // 更新用户信息
    public void updateUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.COLUMN_NAME, user.getName());
        values.put(DbHelper.COLUMN_USERNAME, user.getUsername());
        values.put(DbHelper.COLUMN_PASSWORD, user.getPassword());
        db.update(
                DbHelper.TABLE_USERS,
                values,
                DbHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())}
        );
        db.close();
    }

    // 删除用户
    public void deleteUser(int userId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(
                DbHelper.TABLE_USERS,
                DbHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(userId)}
        );
        db.close();
    }

    @SuppressLint("Range")
    public User getUserById(int userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.TABLE_USERS,
                null,
                DbHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(userId)},
                null,
                null,
                null
        );

        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(DbHelper.COLUMN_PASSWORD)));
        }

        cursor.close();
        db.close();
        return user;
    }
}
