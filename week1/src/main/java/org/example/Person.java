package org.example;

import java.util.concurrent.TimeUnit;

public class Person {
    String name;

    public Person(String name){
        this.name = name;
        this.sayHello();
    }

    public void sayHello(){
        System.out.println("-".repeat(50));
        System.out.printf("안녕하세요. 저는 %s입니다.\n", this.name);
        System.out.println("제가 당신의 문제를 해결할 수 있는 길잡이가 되어줄게요.");
        System.out.println("-".repeat(50));
    }

    public void think() throws InterruptedException {
        System.out.printf("%s가 생각하고 있습니다. \n", this.name);
        System.out.println("잠시만 기다려주세요.");
        Thread.sleep(5000);
        System.out.println("생각 완료!");
    }
}
