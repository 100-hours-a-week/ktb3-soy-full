package org.example;

public class RationalOrganizedCounselor extends RationalCounselor {
    public RationalOrganizedCounselor(String name) {
        super(name);
    }

    public void askSecondQuestion(){
        System.out.println("당신은 그 문제에 대한 계획이 있습니까?");
    }

    public void adviceSecondQuestionNo() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        System.out.println("해결책이 있어도 계획이 없으면 안됩니다.");
        System.out.println("해결책을 따라 일정표를 보고 어서 계획을 짜보세요.");
        System.out.println("계획을 짜는 것이 어렵다면 스스로의 데드라인을 만들고, 그에 따라 이행하세요.");
        System.out.println("-".repeat(50));
    }

    public void adviceSecondQuestionYes() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        System.out.println("해결책도, 계획도 있다면 뭐가 문제입니까?");
        System.out.println("어서 행동하세요!");
        System.out.println("-".repeat(50));
    }
}
