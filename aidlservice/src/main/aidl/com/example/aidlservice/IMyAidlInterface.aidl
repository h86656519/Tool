// IMyAidlInterface.aidl
package com.example.aidlservice;

// Declare any non-default types here with import statements
import com.example.aidlservice.Book;
interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

void transmitSimpleData(int num);
    //in 表示輸入型引數，out 表示輸出型引數，inout 表示輸入輸出型引數
   void transmitComplicatedData(in Book book);
}
