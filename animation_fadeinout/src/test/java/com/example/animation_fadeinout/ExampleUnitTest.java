package com.example.animation_fadeinout;

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

    @Test
    public void test() {
        String name = null;
        String name2 = null;

        for (int i = 0; i < 5; i++) {
            name = name + i;
        }
        System.out.println(name);
    }
}