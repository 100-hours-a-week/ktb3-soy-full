package org.example;

public class RationalCounselor extends Counselor {

    public RationalCounselor(String name) {
        super(name);
    }

    public void askFirstQuestion(){
        System.out.println("당신은 그 문제에 대한 해결책을 아십니까?");
    }

    public void adviceFirstQuestion() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        System.out.println("문제 해결에 있어서 해결책을 중요합니다.");
        System.out.println("해결책을 먼저 생각해보시고 다시 찾아오세요.");
        System.out.println("해결책은 당신만 알고 있습니다.");
        System.out.println("-".repeat(50));
    }
}
