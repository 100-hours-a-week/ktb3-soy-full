package org.example;

public class IncrementTask implements Runnable{

    Counter counter;
    public IncrementTask(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++){
            counter.increment();
        }

    }
}
