package org.example;

/*
실습 과제(제출하지 않아도 됩니다)


5. 동기화 적용 후 재실험
• increment() 메서드에 synchronized 키워드를 붙이거나,
• AtomicInteger를 사용해서 수정해본다.
• 다시 실행해보고, 이제는 항상 2000(또는 예상값)이 정상적으로 나오는지 확인한다.

 */
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        IncrementTask task1 = new IncrementTask(counter);
        IncrementTask task2 = new IncrementTask(counter);

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Counter: " + counter.getValue());

    }
}