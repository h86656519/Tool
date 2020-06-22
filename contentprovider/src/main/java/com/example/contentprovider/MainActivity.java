package com.example.contentprovider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private static final int MY_PERMISSION_REQUEST_READ_CONTACTS = 100;
/**
 * 練習:ContentProvider，提供給另一個app getContentProvider 來操作自己的table
 *  manifest 設定:
 *     authorities : 提供一個授權字串，其他app 也要有才能進行操作
 *     exported : true 可以支援
 *     這邊是接收方阿
 * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSION_REQUEST_READ_CONTACTS);

        Uri bookUri = Uri.parse("content://com.czy.contentproviderdemo.BookProvider/book");
        ContentValues contentValues = new ContentValues();

        //自己新增自己
//        contentValues.put("bookName", "叫什么名字好呢");
//        getContentResolver().insert(bookUri, contentValues);
        Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "bookName"}, null, null, null);
        if (bookCursor != null) {
            while (bookCursor.moveToNext()) {
                Log.e(TAG, "ID:" + bookCursor.getInt(bookCursor.getColumnIndex("_id"))
                        + "  BookName:" + bookCursor.getString(bookCursor.getColumnIndex("bookName")));
            }
            bookCursor.close();
        }

        Uri userUri = Uri.parse("content://com.czy.contentproviderdemo.BookProvider/user");
        contentValues.clear();
        contentValues.put("userName", "叶叶叶");
        contentValues.put("sex", "男");
        getContentResolver().insert(userUri, contentValues);
        Cursor userCursor = getContentResolver().query(userUri, new String[]{"_id", "userName", "sex"}, null, null, null);
        if (userCursor != null) {
            while (userCursor.moveToNext()) {
                Log.e(TAG, "ID:" + userCursor.getInt(userCursor.getColumnIndex("_id"))
                        + "  UserName:" + userCursor.getString(userCursor.getColumnIndex("userName"))
                        + "  Sex:" + userCursor.getString(userCursor.getColumnIndex("sex")));
            }
            userCursor.close();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_READ_CONTACTS: {
                if (grantResults.length < 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    finish();
                }
                return;
            }
        }
    }


}
