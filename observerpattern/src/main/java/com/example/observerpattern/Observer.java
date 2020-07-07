package com.example.observerpattern;

public interface Observer {
    //   訂閱
    void subscrible(Observable observable); //總要傳入向誰訂閱吧

    //   取消訂閱
    void unSubscrible(Observable observable);

    //更新通知，總要有個接收器把更新的東西給接下來吧~
    void update();
}
