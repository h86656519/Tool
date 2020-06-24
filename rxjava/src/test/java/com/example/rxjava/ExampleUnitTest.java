package com.example.rxjava;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;

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
    public  void main() {
        fromGroupBy().subscribe(new Observer<GroupedObservable<Boolean, Integer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(GroupedObservable<Boolean, Integer> booleanIntegerGroupedObservable) { //外面onNext 拿到的是Observable
                if (booleanIntegerGroupedObservable.getKey()) { //getKey 就是拿第76行的判斷結果
                    booleanIntegerGroupedObservable.subscribe(new Observer<Integer>() { //再次訂閱
                        @Override
                        public void onSubscribe(Disposable d) {
                            System.out.println("Group onSubscribe");
                        }

                        @Override
                        public void onNext(Integer integer) { //裡面的onNext拿到的才是元素
                            System.out.println("Group onNext " + integer);
                        }

                        @Override
                        public void onError(Throwable e) {
                            System.err.println("Group onError " + e);
                        }

                        @Override
                        public void onComplete() {
                            System.out.println("Group onComplete");
                        }
                    });
                }

            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error " + e);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    private  Observable<GroupedObservable<Boolean, Integer>> fromGroupBy() {
        return Observable.range(1, 10)
                .groupBy(new Function<Integer, Boolean>() { //重點:GroupBy 是以Observable 來分組
                    @Override
                    public Boolean apply(Integer integer) throws Exception { //分組的條件
                        return integer % 2 == 0; //條件:是否為偶數
//                        return true; // 都符合條件 = 沒設條件
                    }
                }, new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer * 10; //對符合條件的元素做操作
                    }
                });
    }
}