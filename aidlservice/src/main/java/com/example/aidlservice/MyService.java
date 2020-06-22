package com.example.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    public MyService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void transmitSimpleData(int num) throws RemoteException {
            Log.i("MyService", "MyService 傳輸的基本型別資料為：" + num);
        }

        @Override
        public void transmitComplicatedData(Book book) throws RemoteException {
            Log.i("MyService", "MyService 傳輸的複雜資料為：" + book.toString());
        }
    };
}
