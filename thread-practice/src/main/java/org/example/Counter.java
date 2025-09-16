package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
    public final AtomicInteger value = new AtomicInteger(0);

    public int getValue(){
        return value.get();
    }

    public void increment(){
        while(true){
            int nowValue = this.getValue();
            int newValue = nowValue + 1;

            if (value.compareAndSet(nowValue, newValue)){ // 원자성 보장
                return;
            }

        }
    }

}
