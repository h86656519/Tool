package com.example.dependencyinjection;

/*這邊跟依賴注入練習無關
 * 練習 Builder Pattern
 * */
public class Customer {
    private String name;//必填
    private String id;//必填
    private String age; //選填
    private String address; //選填

    Customer(MyBuilder bundle) {
        this.name = bundle.name; //這樣builder 的變數和customer 的變數就會一致
//        this.name = name; 錯誤示範，這樣name印出的是null
        this.id = bundle.id;
        this.age = bundle.age;
        this.address = bundle.address;
    }

    public static MyBuilder getBuilder(String name, String id) { //將必填欄位在產生Builder 時就傳入
        return new MyBuilder(name, id);
    }

    public static final class MyBuilder {
        private String name;
        private String id;
        private String age;
        private String address;

        MyBuilder(String name, String id) { //因為某些欄位為必填，所以就設一個建構子來保持2個class的必填變數來保持一致
            this.name = name;
            this.id = id;
        }

        /*注意:型態是Builder 不是void
        為什麼型態是 MyBuilder?
        因為設void 在初始化新物件時，Customer.getBuilder("xxx", "xx").setAge("xx").之後就卡住了阿~ 沒發再build()結束了阿;
        */
        public MyBuilder setAge(String age) {
            this.age = age;
            return this;
        }

        public MyBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

    public String toString() {
        return this.getClass().getSimpleName() + ":{ "
                + "id:" + id + ", "
                + "name:" + name + ", "
                + "address:" + address + ", "
                + "age:" + age
                + " }";
    }
}
