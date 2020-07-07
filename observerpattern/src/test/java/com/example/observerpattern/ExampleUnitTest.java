package com.example.observerpattern;

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
    public void main() {
        New news = new New();
        //  news.setLastNews(true);
        Reader jone = new Reader("jone");
        jone.subscrible(news);
        jone.unSubscrible(news);
    }
}