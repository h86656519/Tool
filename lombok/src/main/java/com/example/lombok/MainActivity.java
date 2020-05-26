package com.example.lombok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import lombok.Cleanup;
import lombok.val;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 1.生成toString方法，在加上排除特定變數(用id做效果)，
         * 2.生成val 型態
         * */

        val aaa = new Student("123", "0921", "girl");
        System.out.println(aaa);

        /**
         *
         * */
        Student student1 = new Student("1", "0909", "girl");
        Student student2 = new Student("1", "0909", "girl");
        Student student3 = new Student("1", "0909", "man");
        System.out.println("有沒有相等:" + student1.equals(student2));
        System.out.println("有沒有相等:" + student1.equals(student3));

        /**
        * RequiredArgsConstructor 效果
         * **/
//        Student s = new Student("123");

        /**
         * 可以自動呼叫close()
         * */
        String data = "123";
        //  @Cleanup InputStream is = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));

        /**
         * 生成Builder 模式
//         * */
//        Student s = new Student.StudentBuilder()
//                .id("A123...")
//                .phone("0921....")
//                .sex("MAN")
//                .build();
//        System.out.println(s);
    }
}
