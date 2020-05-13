package com.example.dialog;

import org.junit.Test;

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


    /**
     * 實做
     * */
    @Test
    public void test() {
        String name = "leo";
        StringBuilder name2 = new StringBuilder("leo");
        for (int i = 0; i < 100000; i++) {
            // String 用 + 背後原理也是用 StringBuilder 實做出來
//            name = name + i;       //15s 647ms
            name2 = name2.append(i); //16ms
        }
    }
}