package org.example.CounselorPackage;

public class RationalFlexibleCounselor extends RationalCounselor {

    public RationalFlexibleCounselor(String name){
        super(name);
    }

    public void askSecondQuestion(){
        System.out.println("데드라인이 얼마나 남았나요?");
    }

    public void adviceToSecondQuestionYesTime() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        System.out.println("데드라인이 꽤 남았군요!");
        System.out.println("아직은 조금 쉬어도 될 것 같습니다.");
        System.out.println("쉬세요!");
        System.out.println("-".repeat(50));
    }

    public void adviceToSecondQuestionNoTime() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        System.out.println("데드라인이 얼마 안남았습니다.");
        System.out.println("지금 가지고 계신 해결책으로 계획을 세우셔야 합니다.");
        System.out.println("지금 당장 계획을 세우십시오.");
        System.out.println("-".repeat(50));
    }



}
