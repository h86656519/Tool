package com.example.getcontentprovider;

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
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    private static final int MY_PERMISSION_REQUEST_READ_CONTACTS = 100;
    Button btn;

    /**
     * 取得 ContentProvider
     * 這邊是傳送資料方，用get命名不好，會誤會
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSION_REQUEST_READ_CONTACTS);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Uri bookUri = Uri.parse("content://com.czy.contentproviderdemo.BookProvider/book");
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("bookName", "從其他app 新增來的2");
//        getContentResolver().insert(bookUri, contentValues);
                Cursor bookCursor = getContentResolver().query(bookUri, new String[]{"_id", "bookName"}, null, null, null);
                if (bookCursor != null) {
                    while (bookCursor.moveToNext()) {
                        Log.e(TAG, "ID:" + bookCursor.getInt(bookCursor.getColumnIndex("_id"))
                                + "  BookName:" + bookCursor.getString(bookCursor.getColumnIndex("bookName")));
                    }
                    bookCursor.close();
                }
                break;
        }
    }

}
