package com.example.kotlin

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun main() {
        /**建構子*/
//        var box: Box1 = Box1(123)

        /**練習繼承*/
//        var son = Son()
//        println(son.character)
//        son.action()

//        /**練習多型*/
//        var person1 = Man("小明", 18)
//        var person2 = Woman("小華", 29)
//        var person3 = Man("小李", 25)
//        var person4 = Woman("小美", 14)
//        var list = listOf<Human>(person1, person2, person3, person4)
//        for (p in list) {
//            p.pee()
//        }

        /**練習interface*/
        var clsss1 = class1()
//        println(clsss1.listiner1())

        /**練習代理*/
//        var class2 = class2()
//        println(class2.listiner1())
//        println(class2.listiner2())

        /**練習宣告*/
//        var boy:child = child()  //child 被宣告sealed，所以不能直接初始化出來
//        var boy: child = child.boy(); //只能初始化child 裡面的method
//        var girl: child = child.girl();
//        var boy1: child = child.boy();
//        var list = listOf<child>(boy, girl, boy1)
//        for (s in list) {
//            if (s is child.girl)延遲
//                println(s.cihldFun())
//        }
        /**延遲初始化*/
        lateinit var aaa: String
//        lateinit var test:Float //錯誤，不能使用基礎類型Int
//        lateinit var bbb: Int //錯
//        lateinit var bbb: Boolean //錯

    }

    //    fun funName(參數名:參數類型):返回類型{body} ps:會defult判斷傳入的參數為不為null
    //    fun funName(參數名:參數類型?):返回類型{body} ps:加問號表示可以接受null
    @Test
    fun hellowarld() {
        syyHello("老王")
        println(sayHi("王小明是我"))
        println(sayHi("王小明"))
        //宣告就會自動決定存放空間，所以同以個變數二次給值時可能會放不下
        var name = "LEO"
        var i = 10
        var k: Int = 100 //直接在宣告時就指定容器，會比較清楚
        var j: Int //不給值宣告
        print(name)

        //val = final
        val age = 1010

        print(age)
    }


    fun syyHello(str: String) = "hello $str" //可以用這樣回傳字串

    fun sayHi(str: String): String { //也可以這樣寫
        if (str.length > 3) {
            return "hi $str"
        } else {
            return "再多打點字吧"
        }

    }

    data class Student(val id: Int, val name: String)


    /**
     * 建構子和init練習
     * */
    //寫法一
    class Box(boxid: Int) //包括主建構子

    //寫法二
    class Box1 { //多建構子寫法
        var id: Int = 0

        //主建構子
        constructor()

        //二級建構子
        constructor(boxid: Int) : this() {
            this.id = boxid
            println("呼叫只有一個參數的建構子")
        }

        constructor(boxid: Int, name: String) : this(boxid) //呼叫的是上一行建構子，所以this()可以加boxid = this()可以呼叫上面已經宣告出來的建構子
        constructor(boxid: Int, name: String, size: String) : this(boxid, name) //這個this呼叫68行的建構子
        constructor(boxid: Int, name: String, size: String, type: String) : this(boxid, name, size) //這個this是呼叫69行的建構子

        init {
            println("會先呼叫init，再呼叫constructor")
        }

        init {
            println("可以有多個init，調用順序就取決先寫先贏，多個init有什麼意義嗎?")
        }
    }

    //寫法三
    class Box2(boxid: Int) {  //主建構子
        //        constructor() 有主建構子就不用寫了，表示該class不會有無參數建構子
        constructor(boxid: Int, name: String) : this(boxid) //二級建構子要加在this ，裡面一定要放主建構子的參數

        constructor(boxid: Int, name: String, size: String, type: String) : this(boxid, type)
        constructor(boxid: Int, name: String, size: String) : this(boxid, name) //二級建構子之間就不一定要加

    }

    /**
     * 定義Static
     * */
    companion object {
        val pancel = "pencel"
        fun write() {}
    }

    fun static() {
        println("練習Static 的概念" + pancel)
    }

    /**
     *比對類型
     */
    @Test
    fun compareStudent() {
        var s1 = Student(1, "john")
        var s2 = Student(1, "Mary")
        var result = isSameStudent(s1, s2)
        var result2 = isSameStudent2(s1, s2)
        var result3 = isSameStudent3(s1, s2)
        var fun4 = { s1: Student, s2: Student -> s1.id == s2.id } //也可以將method 寫再這裡
        var result4 = fun4(s1, s2) //在這邊call 也可以

        var fun5: (Student, Student) -> Boolean = { s1, s2 -> s1.id == s2.id }

//        println(result)
        //   println(result2)
//        println(result3)
//        println(result4)
        println(fun5(s1, s2))
//        whenStudent(s1)
//        range()

    }

    fun isSameStudent(s1: Student, s2: Student): Boolean {
//        return s1.id.equals(s2.id) java 寫法，以可運行
        return s1.id == s2.id //kotlin
    }

    fun isSameStudent3(s1: Student, s2: Student): Boolean = s1.id == s2.id //只有一行的話也可以這樣寫
    fun isSameStudent2(s1: Student, s2: Student) = s1.id == s2.id //或是這樣寫，不宣告回傳值也行


    /**
     * 練習when
     * */
    fun whenStudent(s1: Student) {
        when (s1.id) {
            1 -> println("找到學生1了")
            2 -> print("這是學生2，不是這個人")
            else -> print("都不是這些人啦~")
        }
    }

    /**
     * 範圍用法
     * */
    fun range() {
        var num = 1..10
//        var num = 1 until 10 // 1~9 不包括 10
        var result = 0
//        for (number in num) {
//            println(number)
//            result = result + number
//        }
//        print(result)

        //setp 用法
        for (number in num step 2) { //1，3，5，7....
            println(number)
        }

        //reversed 反轉排列
        num.reversed()
        for (number in num) {
            println(number)
        }
        println("總數為:" + num.count())
    }


    /**
     * Array 練習
     * */
    @Test
    fun list() {
        var lists = listOf("1", "2", 3) //可以混和放
//        for (i in lists.withIndex()){
//            println(i)
//        }

        for (i in lists.iterator()) {
            println("印出全部list 裡的東西" + i)
        }
    }

    @Test
    fun map() {
        var map = HashMap<String, String>()
        map.put("1", "小明")
        map.put("2", "小白")
        map.put("3", "小李")
        map["4"] = "小黑" //也可以這樣放入
        map["4"] = "小綠" //這樣就會蓋過去

//        for (content in map) {
//            println(content) //會印出 key = value
//            println(content.value) //印出value
//            println(content.key) //印出key
//        }
//        println(map.size)

        println(map.get("1"))
        println(map["4"]) //意思同上

    }


    /**
     * 沒法輸入，不懂了???
     * */
    @Test
    fun inputLine() {
        println("請輸入第一個字")
        var firstInput = readLine()
        println("請輸入第二個字")
        var secondInput = readLine()
        println(firstInput + secondInput)
    }


    open class Father { //要用open 才能被繼承
        //要加open 才能開放給子類用
        open var character: String = "父類變數"
        open fun action() {
            println("父類行為")
        }
    }

    class Son : Father() { //繼承Father
        override var character: String = "子類變數" //調用父類變數,並改寫

        @Override
        override fun action() { //調用父類的method,並改寫
            println("子類行為")
        }
    }

    abstract class Human(name: String, age: Int) {
        abstract fun eat()

        abstract fun pee()
    }

    class Man(name: String, age: Int) : Human(name, age) {
        override fun eat() {

        }

        override fun pee() {
            println("站著尿尿")
        }
    }

    class Woman(name: String, age: Int) : Human(name, age) {
        override fun eat() {

        }

        override fun pee() {
            println("坐著尿尿")
        }
    }

    interface Myistiner {
        fun listiner1()
        fun listiner2()

    }

    class chlid : Myistiner {
        override fun listiner1() {
            println("監看小孩")
        }

        override fun listiner2() {
            println("listiner2")
        }
    }

    /*代理*/
    class class1 : Myistiner {
        override fun listiner1() {
            println("class1的listiner1")
        }

        override fun listiner2() {
            println("class1的listiner2")
        }

        fun class1fun() {
            println("class1的功能")
        }
    }


    class class2 : Myistiner by class1() { //加了by 就可以將原本class2也要implement 出listiner1 給class1 來實現
        //        override fun listiner1() {} //沒有有實做出來的就走代理的
//        override fun listiner2() {   //有實做出來的就走自己的
//            println("class2的listiner2")
//        }
    }

    object class3 {} //加上object 就是singleton

    /**sealed*/
    sealed class child {
        fun cihldFun() {
            println("小孩的行為")
        }

        class boy() : child()
        class girl() : child()
    }

    var left: Int = 0
    var right: Int = 2
    var top: Int = 0
    var bottom: Int = 2

    fun contains(x: Int, y: Int): Boolean {
        return return left < right && top < bottom
                && x >= left && x < right && y >= top && y < bottom
    }

    class Point(val x: Float, val y: Float, val time: Long) {
        fun distanceTo(start: Point): Float {
            return Math.sqrt(Math.pow((x - start.x).toDouble(), 2.0) + Math.pow((y - start.y).toDouble(), 2.0)).toFloat()
        }

        fun velocityFrom(start: Point): Float {
            return distanceTo(start) / (time - start.time)
        }

    }

    @Test
    fun testPoint() {
        var p1Time: Calendar = Calendar.getInstance()
        p1Time.set(2020, 6, 5, 12, 0, 0)
        var p2Time: Calendar = Calendar.getInstance()
        p2Time.set(2020, 6, 5, 12, 0, 1)
        var p1: Point = Point(0F, 0F, p1Time.timeInMillis)
        var p2: Point = Point(1F, 0F, p2Time.timeInMillis)
        var dis: Float = p2.distanceTo(p1)
        var vel: Float = p2.velocityFrom(p1)
        println("距離" + dis)
        println("速度" + vel)
        println("2的平方" + Math.pow(2.0, 10.0))
        println("100的平方>" + Math.sqrt(100.0))
    }

    /**例外處理*/
//    java 寫法
//    void throwsException() throws Exception {
//        throw new Exception();
//    }

    //kotlin寫法
    @Throws(Exception::class) //將method裡的 Exception 丟給外面去接
    fun throwsException() {
        lateinit var a: String
        print("" + a)
        throw Exception()
    }

    @Test
    fun runFor() {
        //遞減
//        for (i in 10 downTo 0) {
//            println(i)
//        }

//        //遞加
//        for(i in 0..10){
//
//        }

//        for(i in 0..10 step 2){
//            println(i)
//        }

        //    System.out.println(result)

        //kotlin for loop 不能用float 所以只能用while 做代替
//        for(float i = 0f; i < 1f; i ++){ //java 可以這樣寫
//            println(i)
//        }

        var i = 0f
        while (i < 10f) { //只能這樣代替了
            println(i)
            i++
        }

        var list = listOf<Int>(10, 20, 30, 40)
        println(list.size)
//        for (i in 0..list.size){
//            println(i)
//            println(list.get(i))
//        }


        for (i in 0 until list.size) {
            println(i)
            println(list.get(i))
        }
    }
}
