package com.example.dependencyinjection;


/**
 * 情境:  ShoppingCart 想用 checkout() 的 必須需要 PostShipping 才能實現，但這樣會造成高耦合
 */
//public class ShoppingCart {
//    private PostShipping postShipping; //郵局
//    Order order;
//
//    private void checkout() {
//        PostShipping sp = new PostShipping();  //舊式作法 new 出來，但這樣會造成高耦合
//        sp.shipOrder(order);
//
//        postShipping.shipOrder(order); //依賴注入寫法
//    }
//
//    //    作法一
//// 由建構式從外部注入被依賴的物件至依賴的程式中
//    private ShoppingCart(PostShipping postShipping) {
//        this.postShipping = postShipping;
//    }
//
//    //    作法二
//    private void setPostShipping(PostShipping postShipping) {
//        this.postShipping = postShipping;
//    }
//
//}


    //再次修改為這樣
public class ShoppingCart {

    private Shipping shipping; // <-- PostShipping 改為Shipping介面，此為利用物建導向的多型特性。

    public ShoppingCart(Shipping shipping) {
        this.shipping = shipping; // 具體的被依賴物件由外部注入，達到業務邏輯與被依賴物件的生成邏輯分開
    }

    private void checkout(Order order) {
        shipping.shipOrder(order);
    }

}
