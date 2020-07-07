package com.example.observerpattern;

import java.util.ArrayList;

public interface Observable {
    //    註冊
    void register(Observer observer); //總要傳入 誰註冊

    //    取消註冊
    void unRegister(Observer observer); //誰取消吧

    //  通知訂閱者(Observer)
    void inform();
}
