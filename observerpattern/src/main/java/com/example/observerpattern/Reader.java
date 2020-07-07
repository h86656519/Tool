package com.example.observerpattern;

public class Reader implements Observer {
    private String name = null;
    private Observable news = null;

    public Reader(String name) {
        this.name = name;
    }

    @Override
    public void subscrible(Observable observable) {
        System.out.println(name + "開始訂閱了");
        this.news = observable;
        news.register(this);
    }

    @Override
    public void unSubscrible(Observable observable) {
        news.unRegister(this);
        System.out.println(name + "取消訂閱了");
    }

    @Override
    public void update() {
        read();
    }

    public void read() {
        if (((New) this.news).isLastNews()) {
            System.out.println("已經是最新資訊");
        }else {
            System.out.println("不是最新資訊");
        }
    }

}
