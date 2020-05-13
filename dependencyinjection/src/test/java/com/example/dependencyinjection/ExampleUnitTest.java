package com.example.dependencyinjection;

import android.icu.text.Edits;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        int aaa = 0;
        int count = 3;
        for (int i = count - 1; i >= 0; i--) {
            System.out.print("i" + i + " > ");
            System.out.println(aaa);
            aaa++;
        }
    }

    @Test
    public void test2() {
        String isParent = "Y";
        String isdPay = "2";
//        if (isParent.equals("Y") && !"1".equals(idPay)) {
//            System.out.println("此訂單為子母件，不需要付款");
//        } else if (isParent.equals("Y") && "1".equals(idPay)) {
//            System.out.println("此訂單為子母件，需要付款");
//        } else if (!isParent.equals("Y") && "1".equals(idPay)) {
//            System.out.println("此訂單為不是子母件，需要付款");
//        } else {
//            System.out.println("此訂單為不是子母件，不需要付款");
//        }
        if (isParent.equals("Y")) {
            if ("1".equals(isdPay)) {
                System.out.println("此訂單為子母件，需要付款");
            } else {
                System.out.println("此訂單為子母件，不需要付款");
            }
        } else {
            if ("1".equals(isdPay)) {
                System.out.println("此訂單為不是子母件，需要付款");
            } else {
                System.out.println("此訂單為不是子母件，不需要付款");
            }
        }

    }


    public void test3() {
        List<Bean> list = new ArrayList<Bean>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", (Serializable) list); //傳遞 物件list 要轉型
    }

    public class Bean {
        String aaa;
    }

    @Test
    public void test4() {
        List<ParentBean> list = new ArrayList<ParentBean>(); //最終整理好的list
        List<Box> parent = new ArrayList<Box>(); //所有母單的list
        List<Box> child = new ArrayList<Box>(); //所有子單的list

        Box box1 = new Box();
        box1.setBox_id("A01"); //母
        box1.setParam_val2("0700101003242000016");
        box1.setParam_val4("0700101003242000016");
        Box box2 = new Box();
        box2.setBox_id("A02"); //a01的子
        box2.setParam_val2("0700141003242000020");
        box2.setParam_val4("0700101003242000016");
        Box box3 = new Box();
        box3.setBox_id("A09"); //母
        box3.setParam_val2("0700171003242000007");
        box3.setParam_val4("0700171003242000007");
        Box box4 = new Box();
        box4.setBox_id("A10"); //a09子
        box4.setParam_val2("0700131003242000011");
        box4.setParam_val4("0700171003242000007");
        Box box5 = new Box();
        box5.setBox_id("A11"); //a09子
        box5.setParam_val2("0700161003242000014");
        box5.setParam_val4("0700171003242000007");
        parent.add(box1);
        parent.add(box3);
        child.add(box2);
        child.add(box4);
        child.add(box5);

        List<Post> postListAdapter = new ArrayList<Post>(); // 所有母+子的list ，要從這裡加到最終的list
        Post post1 = new Post();
        post1.setBox_id("A01");
        post1.setPay_id("0700101003242000016");
        Post post2 = new Post();
        post2.setBox_id("A02");
        post2.setPay_id("0700141003242000020");
        Post post3 = new Post();
        post3.setBox_id("A09");
        post3.setPay_id("0700171003242000007");
        Post post4 = new Post();
        post4.setBox_id("A10");
        post4.setPay_id("0700131003242000011");
        Post post5 = new Post();
        post5.setBox_id("A11");
        post5.setPay_id("0700161003242000014");
        postListAdapter.add(post1);
        postListAdapter.add(post2);
        postListAdapter.add(post3);
        postListAdapter.add(post4);
        postListAdapter.add(post5);

        // For parent
        for (int i = 0; i < postListAdapter.size(); i++) {
            String payId = postListAdapter.get(i).getPay_id();

            for (int j = 0; j < parent.size(); j++) {
                if (parent.get(j).getParam_val2().equals(payId)) {
                    //母單
                    ParentBean parentBean = new ParentBean();
                    parentBean.setParentPost(postListAdapter.get(i));
                    System.out.println("母單: " + postListAdapter.get(i).getBox_id());
                    list.add(parentBean);
                }
            }
        }

        // For child
        for (int i = 0; i < postListAdapter.size(); i++) {
            String payId = postListAdapter.get(i).getPay_id();

            for (int j = 0; j < child.size(); j++) {
                if (child.get(j).getParam_val2().equals(payId)) {
//                    List<Post> postList1 = new ArrayList<Post>();
//                    postList1.add(postListAdapter.get(i));
//                    System.out.println("子單" + postListAdapter.get(i).getBox_id());
                    for (int k = 0; k < list.size(); k++) {
                        ParentBean parentBean = list.get(k);
                        if (parentBean.parentPost.pay_id.equals(child.get(j).getParam_val4())) {
                            parentBean.add(postListAdapter.get(i));
                        }
                    }
                }
            }
        }

//        for (int i = 0; i < parent.size(); i++) { //拿母單跟全部的比，對的話就set parentPost
//            ParentBean parentBean = new ParentBean();
//            for (int k = 0; k < postListAdapter.size(); k++) {
//                if (parent.get(i).getParam_val2().equals(postListAdapter.get(k).getPay_id())) {
//                    //母單
//                    parentBean.setParentPost(postListAdapter.get(k));
//                    System.out.println("母單: " + postListAdapter.get(k).getBox_id());
//                } else {
//                    //子單
//                    for (int j = 0; j < child.size(); j++) {
//                        if (child.get(j).getParam_val2().equals(postListAdapter.get(k).getPay_id())) {
//                            List<Post> postList1 = new ArrayList<Post>();
//                            postList1.add(postListAdapter.get(k));
//                            System.out.println("子單" + postListAdapter.get(k).getBox_id());
//                            System.out.println("他的母單是:" + parent.get(i).getBox_id());
//                            parentBean.setChlidPostList(postList1);
//
//                        }
//
//                    }
//                }
//            }
//            list.add(parentBean);
//        }
        System.out.println("母單總筆數: " + list.size());
    }

    public class ParentBean {
        Post parentPost; //一母
        List<Post> chlidPostList = new ArrayList<Post>(); //多子

        public Post getParentPost() {
            return parentPost;
        }

        public void setParentPost(Post parentPost) {
            this.parentPost = parentPost;
        }

        public List<Post> getChlidPostList() {
            return chlidPostList;
        }

        public void setChlidPostList(List<Post> chlidPostList) {
            this.chlidPostList = chlidPostList;
        }

        public void add(Post post) {
            this.chlidPostList.add(post);
        }
    }

    public class Post {

        public String box_id = ""; // idx = 0 //格口編號
        public String express_id = ""; // idx = 1 //運單號
        public String phone_number = ""; // idx = 2 //客戶手機號碼
        public String is_post = ""; // idx = 3 //是否上傳網路成功 0 : 已上傳 1 : 未上傳
        public String param_val1 = ""; // idx = 4     0 : 非到付件/寄付件 1 : 派件类型Param_val1值到付件
        public String pay_id = ""; // idx = 5
        public String pay_state = ""; // idx = 6      支付狀態 0：未支付；1：已支付；2：支付失败；3：取消支付;4:查询失败
        public String money = ""; // idx = 7          需除以 100
        public String cmd_type = ""; // idx = 8 //業務類型  cmd_type_cpost 客户寄件 , cmd_type_cget 客户取件 , cmd_type_cget_pay 客户取件付款 , cmd_type_cget_back_box 客户取返件业务 , cmd_type_dpost 收派员派件 , cmd_type_dget 收派员收件 , cmd_type_delay 派件转换为滞留件 , cmd_cancel_post 取消派件业务 ,
        public String status = ""; // idx = 9 //格口狀態
        public String last_close_time = ""; // idx = 10 //最近關門時間
        public String pay_time = ""; // idx = 11
        public String pay_type = ""; // idx = 12//支付方式
        public String box_size = ""; // idx = 13//格口大小
        public String box_cmd_range = ""; // idx = 14//格口是否可儲物
        public String box_order_id = ""; // idx = 15//格口排序
        public String verify_code = ""; // idx = 16//客戶取件碼
        public String operator_code = ""; // idx = 17//操作人員工
        public String operator_phone = ""; // idx = 18//操作人員手機號
        public String operator_type = ""; // idx = 19//操作人員類型
        public String operator_company_code = ""; // idx = 20//所屬公司代號
        public String operator_company_name = ""; // idx = 21//所屬公司名稱
        public String open_time = ""; // idx = 22//最近開門時間
        public String param_val2 = ""; // idx = 23//訂單號

        public String getBox_id() {
            return box_id;
        }

        public void setBox_id(String box_id) {
            this.box_id = box_id;
        }

        public String getExpress_id() {
            return express_id;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getIs_post() {
            return is_post;
        }

        public void setIs_post(String is_post) {
            this.is_post = is_post;
        }

        public String getParam_val1() {
            return param_val1;
        }

        public void setParam_val1(String param_val1) {
            this.param_val1 = param_val1;
        }

        public String getPay_id() {
            return pay_id;
        }

        public void setPay_id(String pay_id) {
            this.pay_id = pay_id;
        }

        public String getPay_state() {
            return pay_state;
        }

        public void setPay_state(String pay_state) {
            this.pay_state = pay_state;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCmd_type() {
            return cmd_type;
        }

        public void setCmd_type(String cmd_type) {
            this.cmd_type = cmd_type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLast_close_time() {
            return last_close_time;
        }

        public void setLast_close_time(String last_close_time) {
            this.last_close_time = last_close_time;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getBox_size() {
            return box_size;
        }

        public void setBox_size(String box_size) {
            this.box_size = box_size;
        }

        public String getBox_cmd_range() {
            return box_cmd_range;
        }

        public void setBox_cmd_range(String box_cmd_range) {
            this.box_cmd_range = box_cmd_range;
        }

        public String getBox_order_id() {
            return box_order_id;
        }

        public void setBox_order_id(String box_order_id) {
            this.box_order_id = box_order_id;
        }

        public String getVerify_code() {
            return verify_code;
        }

        public void setVerify_code(String verify_code) {
            this.verify_code = verify_code;
        }

        public String getOperator_code() {
            return operator_code;
        }

        public void setOperator_code(String operator_code) {
            this.operator_code = operator_code;
        }

        public String getOperator_phone() {
            return operator_phone;
        }

        public void setOperator_phone(String operator_phone) {
            this.operator_phone = operator_phone;
        }

        public String getOperator_type() {
            return operator_type;
        }

        public void setOperator_type(String operator_type) {
            this.operator_type = operator_type;
        }

        public String getOperator_company_code() {
            return operator_company_code;
        }

        public void setOperator_company_code(String operator_company_code) {
            this.operator_company_code = operator_company_code;
        }

        public String getOperator_company_name() {
            return operator_company_name;
        }

        public void setOperator_company_name(String operator_company_name) {
            this.operator_company_name = operator_company_name;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getParam_val2() {
            return param_val2;
        }

        public void setParam_val2(String param_val2) {
            this.param_val2 = param_val2;
        }

        public String ormGetPro(int idx) {
            switch (idx) {
                case 0:
                    return getBox_id();
                case 1:
                    return getExpress_id();
                case 2:
                    return getPhone_number();
                case 3:
                    return getIs_post();
                case 4:
                    return getParam_val1();
                case 5:
                    return getPay_id();
                case 6:
                    return getPay_state();
                case 7:
                    return getMoney();
                case 8:
                    return getCmd_type();
                case 9:
                    return getStatus();
                case 10:
                    return getLast_close_time();
                case 11:
                    return getPay_time();
                case 12:
                    return getPay_type();
                case 13:
                    return getBox_size();
                case 14:
                    return getBox_cmd_range();
                case 15:
                    return getBox_order_id();
                case 16:
                    return getVerify_code();
                case 17:
                    return getOperator_code();
                case 18:
                    return getOperator_phone();
                case 19:
                    return getOperator_type();
                case 20:
                    return getOperator_company_code();
                case 21:
                    return getOperator_company_name();
                case 22:
                    return getOpen_time();
                case 23:
                    return getParam_val2();
                default:
                    return "";
            }
        }

        public void ormSetPro(int idx, String value) {
            switch (idx) {
                case 0:
                    setBox_id(value);
                    return;
                case 1:
                    setExpress_id(value);
                    return;
                case 2:
                    setPhone_number(value);
                    return;
                case 3:
                    setIs_post(value);
                    return;
                case 4:
                    setParam_val1(value);
                    return;
                case 5:
                    setPay_id(value);
                    return;
                case 6:
                    setPay_state(value);
                    return;
                case 7:
                    setMoney(value);
                    return;
                case 8:
                    setCmd_type(value);
                    return;
                case 9:
                    setStatus(value);
                    return;
                case 10:
                    setLast_close_time(value);
                    return;
                case 11:
                    setPay_time(value);
                    return;
                case 12:
                    setPay_type(value);
                    return;
                case 13:
                    setBox_size(value);
                    return;
                case 14:
                    setBox_cmd_range(value);
                    return;
                case 15:
                    setBox_order_id(value);
                    return;
                case 16:
                    setVerify_code(value);
                    return;
                case 17:
                    setOperator_code(value);
                    return;
                case 18:
                    setOperator_phone(value);
                    return;
                case 19:
                    setOperator_type(value);
                    return;
                case 20:
                    setOperator_company_code(value);
                    return;
                case 21:
                    setOperator_company_name(value);
                    return;
                case 22:
                    setOpen_time(value);
                    return;
                case 23:
                    setParam_val2(value);
                    return;
                default:
                    return;
            }
        }
    }

    public class Box {

        private final String[] COLUMN = {"id", // idx = 0
                "box_id", // idx = 1
                "box_size", // idx = 2
                "box_cmd_range", // idx = 3
                "box_order_id", // idx = 4
                "status", // idx = 5
                "cmd_type", // idx = 6
                "phone_number", // idx = 7
                "verify_code", // idx = 8
                "operator_id", // idx = 9
                "operator_name", // idx = 10
                "operator_code", // idx = 11
                "operator_phone", // idx = 12
                "operator_type", // idx = 13
                "operator_company_code", // idx = 14
                "operator_company_name", // idx = 15
                "express_id", // idx = 16
                "open_time", // idx = 17
                "last_close_time", // idx = 18
                "is_post", // idx = 19
                "param_val1", // idx = 20
                "param_val2", // idx = 21
                "param_val3", // idx = 22
                "param_val4", // idx = 23
                "param_val5", // idx = 24
                "hire_id", // idx = 25
                "tx_id" // idx = 26
        };
        public String id = null; // idx = 0
        public String box_id = ""; // idx = 1
        public String box_size = ""; // idx = 2
        public String box_cmd_range = ""; // idx = 3
        public int box_order_id = 0; // idx = 4
        public String status = ""; // idx = 5
        public String cmd_type = ""; // idx = 6
        public String phone_number = ""; // idx = 7
        public String verify_code = ""; // idx = 8
        public String operator_id = ""; // idx = 9
        public String operator_name = ""; // idx = 10
        public String operator_code = ""; // idx = 11
        public String operator_phone = ""; // idx = 12
        public String operator_type = ""; // idx = 13
        public String operator_company_code = ""; // idx = 14
        public String operator_company_name = ""; // idx = 15
        public String express_id = ""; // idx = 16
        public String open_time = ""; // idx = 17
        public String last_close_time = ""; // idx = 18
        public String is_post = ""; // idx = 19
        public String param_val1 = ""; // idx = 20
        public String param_val2 = ""; // idx = 21  訂單號
        public String param_val3 = ""; // idx = 22 是否预发货运单
        public String param_val4 = ""; // idx = 23 母單訂單號
        public String param_val5 = ""; // idx = 24
        public String hire_id = ""; // idx = 25
        public String tx_id = ""; // idx = 26

        public String[] getColumn() {
            return COLUMN;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBox_id() {
            return this.box_id;
        }

        public void setBox_id(String box_id) {
            this.box_id = box_id;
        }

        public String getBox_size() {
            return this.box_size;
        }

        public void setBox_size(String box_size) {
            this.box_size = box_size;
        }

        public String getBox_cmd_range() {
            return this.box_cmd_range;
        }

        public void setBox_cmd_range(String box_cmd_range) {
            this.box_cmd_range = box_cmd_range;
        }

        public int getBox_order_id() {
            return this.box_order_id;
        }

        public void setBox_order_id(int box_order_id) {
            this.box_order_id = box_order_id;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCmd_type() {
            return this.cmd_type;
        }

        public void setCmd_type(String cmd_type) {
            this.cmd_type = cmd_type;
        }

        public String getPhone_number() {
            return this.phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getVerify_code() {
            return this.verify_code;
        }

        public void setVerify_code(String verify_code) {
            this.verify_code = verify_code;
        }

        public String getOperator_id() {
            return this.operator_id;
        }

        public void setOperator_id(String operator_id) {
            this.operator_id = operator_id;
        }

        public String getOperator_name() {
            return this.operator_name;
        }

        public void setOperator_name(String operator_name) {
            this.operator_name = operator_name;
        }

        public String getOperator_code() {
            return this.operator_code;
        }

        public void setOperator_code(String operator_code) {
            this.operator_code = operator_code;
        }

        public String getOperator_phone() {
            return this.operator_phone;
        }

        public void setOperator_phone(String operator_phone) {
            this.operator_phone = operator_phone;
        }

        public String getOperator_type() {
            return this.operator_type;
        }

        public void setOperator_type(String operator_type) {
            this.operator_type = operator_type;
        }

        public String getOperator_company_code() {
            return this.operator_company_code;
        }

        public void setOperator_company_code(String operator_company_code) {
            this.operator_company_code = operator_company_code;
        }

        public String getOperator_company_name() {
            return this.operator_company_name;
        }

        public void setOperator_company_name(String operator_company_name) {
            this.operator_company_name = operator_company_name;
        }

        public String getExpress_id() {
            return this.express_id;
        }

        public void setExpress_id(String express_id) {
            this.express_id = express_id;
        }

        public String getOpen_time() {
            return this.open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getLast_close_time() {
            return this.last_close_time;
        }

        public void setLast_close_time(String last_close_time) {
            this.last_close_time = last_close_time;
        }

        public String getIs_post() {
            return this.is_post;
        }

        public void setIs_post(String is_post) {
            this.is_post = is_post;
        }

        public String getParam_val1() {
            return this.param_val1;
        }

        public void setParam_val1(String param_val1) {
            this.param_val1 = param_val1;
        }

        public String getParam_val2() {
            return this.param_val2;
        }

        /**
         * 寄件時會存入訂單號
         *
         * @param param_val2
         */
        public void setParam_val2(String param_val2) {
            this.param_val2 = param_val2;
        }

        public String getParam_val3() {
            return this.param_val3;
        }

        public void setParam_val3(String param_val3) {
            this.param_val3 = param_val3;
        }

        public String getParam_val4() {
            return this.param_val4;
        }

        public void setParam_val4(String param_val4) {
            this.param_val4 = param_val4;
        }

        public String getParam_val5() {
            return this.param_val5;
        }

        /**
         * •在快遞員收件時判斷是否為待收件 1表示可待收件
         * •快遞員派件後會存入流水號
         *
         * @param param_val5
         */
        public void setParam_val5(String param_val5) {
            this.param_val5 = param_val5;
        }

        public String getHire_id() {
            return hire_id;
        }

        public void setHire_id(String hire_id) {
            this.hire_id = hire_id;
        }

        public String getTx_id() {
            return tx_id;
        }

        public void setTx_id(String tx_id) {
            this.tx_id = tx_id;
        }

        public String ormGetPro(int idx) {
            switch (idx) {
                case 0:
                    return getId();
                case 1:
                    return getBox_id();
                case 2:
                    return getBox_size();
                case 3:
                    return getBox_cmd_range();
                case 4:
                    return getBox_order_id() + "";
                case 5:
                    return getStatus();
                case 6:
                    return getCmd_type();
                case 7:
                    return getPhone_number();
                case 8:
                    return getVerify_code();
                case 9:
                    return getOperator_id();
                case 10:
                    return getOperator_name();
                case 11:
                    return getOperator_code();
                case 12:
                    return getOperator_phone();
                case 13:
                    return getOperator_type();
                case 14:
                    return getOperator_company_code();
                case 15:
                    return getOperator_company_name();
                case 16:
                    return getExpress_id();
                case 17:
                    return getOpen_time();
                case 18:
                    return getLast_close_time();
                case 19:
                    return getIs_post();
                case 20:
                    return getParam_val1();
                case 21:
                    return getParam_val2();
                case 22:
                    return getParam_val3();
                case 23:
                    return getParam_val4();
                case 24:
                    return getParam_val5();
                case 25:
                    return getHire_id();
                case 26:
                    return getTx_id();
                default:
                    return "";
            }
        }

        public void ormSetPro(int idx, String value) {
            switch (idx) {
                case 0:
                    setId(value);
                    break;
                case 1:
                    setBox_id(value);
                    break;
                case 2:
                    setBox_size(value);
                    break;
                case 3:
                    setBox_cmd_range(value);
                    break;
                case 4:
                    setBox_order_id(Integer.parseInt(value));
                    break;
                case 5:
                    setStatus(value);
                    break;
                case 6:
                    setCmd_type(value);
                    break;
                case 7:
                    setPhone_number(value);
                    break;
                case 8:
                    setVerify_code(value);
                    break;
                case 9:
                    setOperator_id(value);
                    break;
                case 10:
                    setOperator_name(value);
                    break;
                case 11:
                    setOperator_code(value);
                    break;
                case 12:
                    setOperator_phone(value);
                    break;
                case 13:
                    setOperator_type(value);
                    break;
                case 14:
                    setOperator_company_code(value);
                    break;
                case 15:
                    setOperator_company_name(value);
                    break;
                case 16:
                    setExpress_id(value);
                    break;
                case 17:
                    setOpen_time(value);
                    break;
                case 18:
                    setLast_close_time(value);
                    break;
                case 19:
                    setIs_post(value);
                    break;
                case 20:
                    setParam_val1(value);
                    break;
                case 21:
                    setParam_val2(value);
                    break;
                case 22:
                    setParam_val3(value);
                    break;
                case 23:
                    setParam_val4(value);
                    break;
                case 24:
                    setParam_val5(value);
                    break;
                case 25:
                    setHire_id(value);
                    break;
                case 26:
                    setTx_id(value);
                    break;
                default:
                    break;
            }
        }
    }

    @Test
    public void test5() {
        String aaa = null;
        switch (aaa) {
            case "1":
                System.out.println("1");
                break;
            case "2":
                System.out.println("2");
                break;
            default:
                System.out.println("defult");
        }
        System.out.println("1111111111111111111");

    }

    @Test
    public void test6() {
        String actionType = "聰明退";
        switch (actionType) {
            case "聰明退":
                System.out.println("聰明退");
                break;
            case "快遞員收件":
                System.out.println("快遞員收件");
                break;
            case "超級管理員":
                System.out.println("超級管理員");
                break;
            default:
                System.out.println("其他");
                break;
        }
    }

    /**
     * Lambda 練習
     */
    @Test
    public void test7() {
//        Runnable runnable = new Runnable() {
//            @Override //Functional Interface
//            public void run() {
//                System.out.println("一般寫法"); sout
//            }
//        };
//        runnable.run();

        Runnable runnable = () -> System.out.println("Lambda 寫法，好處: 1.比較短 2.可以取代 Functional Interface 3.可以避免產生出新的class 4.但只能用在只有一個method 的 interface，多個method 的interface 就不能用lambda");
        runnable.run();

        //放在collecttion 時
        Map<String, String> map = new HashMap<>();
        map.put("1", "第一");
        map.put("2", "第二");
        map.put("3", "第三");
        Set<String> set = map.keySet();

        //一般寫法
//        for (String s: set){
//            System.out.println(s + ": " + map.get(s));
//        }

        //Lambda寫法
        set.forEach(s -> System.out.println(s + ": " + map.get(s)));
    }

    //多個method 的interface 就不能用lambda
    interface lambdaInterface {
        void fun1();

        void fun2(); //mark 掉就可以用
    }

    public void test11() {
        Button btn;

//        btn.setOnCapturedPointerListener(new lambdaInterface() {
//            @Override
//            public void fun1() {
//
//            }
//
//            @Override
//            public void fun2() {
//
//            }
//        });
    }

    public void test8() {
        StringBuilder builder = new StringBuilder();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        //   spannableStringBuilder.setSpan(效果, );
    }

    @Test
    public void test9() {
        int aaa = 0;
        int bbb = 2;
        for (; aaa < bbb; aaa++) {
            System.out.println(aaa);
        }
    }


    @Test
    public void test10() {
        boolean aaa = false;
        if (!aaa) {
            System.out.println(1);
        } else {
            System.out.println(2);
        }
    }

    //    Iterator 練習
    ArrayList arrayList = new ArrayList();
    HashMap<Integer, String> map = new HashMap<>();

    @Test
    public void Iterator_test() { //最簡單的用法
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        Iterator it = arrayList.iterator();

//        while迴圈的寫法
//        while (it.hasNext()) {
//            int i = (int) it.next();
//            System.out.println(i);
//        }
//        System.out.println("--------------------------");
//      for迴圈的寫法
        for (Iterator iter = arrayList.iterator(); iter.hasNext(); ) {
            int str = (int) iter.next();
            System.out.println(str);
        }
        System.out.println("--------------------------");
//      map的寫法
        map.put(1, "一");
        map.put(2, "二");
        map.put(3, "三");

        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            System.out.println("The key is: " + mapEntry.getKey()
                    + ",value is :" + mapEntry.getValue());
        }
        System.out.println("--------------------------");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapEntry = (Map.Entry) iterator.next();
            System.out.println("The key is: " + mapEntry.getKey()
                    + ",value is :" + mapEntry.getValue());
        }
    }


}