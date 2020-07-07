package com.example.observerpattern;

import java.util.ArrayList;

public class New implements Observable {
    //註冊者的清單
    private ArrayList<Observer> registerList = new ArrayList();
    private boolean isLastNews = false; //是否為最新資訊?

    @Override
    public void register(Observer observer) {
        registerList.add(observer);
        inform();
    }

    @Override
    public void unRegister(Observer observer) {
        registerList.remove(observer);
    }

    @Override
    public void inform() {
        for (Observer observer : registerList) {
            observer.update();
        }
//        registerList.forEach(Observer::update); java8寫法，不導了
    }

    public void setLastNews(boolean lastNews) {
        isLastNews = lastNews;
    }

    public boolean isLastNews() {
        return isLastNews;
    }
}
