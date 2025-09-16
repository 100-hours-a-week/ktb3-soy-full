package org.example;

/*
실습 과제(제출하지 않아도 됩니다)

2. 스레드 클래스 만들기
• 예: IncrementThread라는 클래스를 만들고, Thread를 상속받거나 Runnable을 구현하도록 한다.
• 생성자에서 Counter 객체를 받아와서 저장한다.
• run() 메서드 안에서는 일정 횟수(예: 1000번)만큼 increment()를 호출한다.

3. 메인 메서드에서 테스트
• Counter 인스턴스를 하나만 생성한다.
• IncrementThread 스레드를 2개 이상 만들어서 동시에 실행(start)시킨다.
• 모든 스레드가 끝난 후(필요하면 join() 활용) Counter의 value를 출력한다.
• 예측한 값(예: 2개의 스레드가 각각 1000번씩 더하면 2000)과 실제 결과를 비교한다.

4. 동시성 문제 확인
• 처음엔 increment() 메서드에 어떤 동기화 처리를 하지 않고 돌려본다.
• 실제로 여러 번 실행해보면, 때로는 2000이 안 되는 값을 찍을 수도 있다.
• 동시성 문제(원자성 깨짐)를 직접 체험해본다.

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

        System.out.println("Counter: " + counter.value);

    }
}