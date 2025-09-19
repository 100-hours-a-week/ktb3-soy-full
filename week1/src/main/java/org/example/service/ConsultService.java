package org.example.service;

import org.example.counselor.*;
import org.example.utility.InputHandler;

import java.util.HashMap;

public class ConsultService {
    private InputHandler handler = new InputHandler();
    public HashMap<String, Boolean> counselorList = new HashMap<>();
    public static final boolean hasDone = false;

    public ConsultService(){
        counselorList.put("bri", hasDone);
        counselorList.put("ana", hasDone);
        counselorList.put("dva", hasDone);
        counselorList.put("zen", hasDone);
    }

    public void introduce(){
        introduceProgram();
        introduceCounselor();
    }
    private void introduceProgram() {
        System.out.println("고민이 있나요? 어떻게 해결하면 좋을지 알듯 말듯 잘 모르시겠나요?");
        System.out.println("잘 오셨습니다. 이곳의 멋진 상담사분들과 함께 때론 명쾌하고 때론 따뜻하게 문제를 풀어보아요.");
        System.out.println("만족하실때까지 도와드리겠습니다!");
    }
    private void introduceCounselor() {
        System.out.println("최고의 상담사들을 소개합니다.");
        System.out.println("-".repeat(50));
        System.out.printf("|  |이름|MBTI|%s자기소개%s|\n", " ".repeat(16), " ".repeat(15));
        System.out.printf("|🧐|bri|I_TJ|고민을 해결하는 고속도로 열어드립니다.%s|\n", " ".repeat(8));
        System.out.printf("|🤓|ana|I_TP|고민 들어드립니다. 반박시 당신 말이 맞습니다.%s|\n", " ".repeat(2));
        System.out.printf("|😉|dva|I_FJ|고민해결을 도와드립니다! 함께 나눠요~%s|\n", " ".repeat(8));
        System.out.printf("|😌|zen|I_FP|고민 들어드려요! 슬픔은 나누면 반이 되니까,,%s|\n", " ".repeat(2));
        System.out.println("-".repeat(50));
    }
    public boolean isPossibleToConsult() {
        for (String name : counselorList.keySet()) {
            if (!counselorList.get(name)) { // 가능한 상담사가 있다면
                return true;
            }
        }
        return false;
    }

    private boolean isInCounselorList(String name){
        return counselorList.containsKey(name);
    }

    private boolean hasDoneConsultedWith(String name){
        return counselorList.get(name);
    }

    private String getCounselorName(){
        System.out.println("마음에 드는 상담사 분의 이름을 입력해주세요.");
        while(true) {
            String name = handler.getString();
            if (isInCounselorList(name) && hasDoneConsultedWith(name)) {
                System.out.println("이미 상담을 완료한 상담사입니다. 다시 선정하세요.");
            } else if (isInCounselorList(name) && !hasDoneConsultedWith(name)) {
                System.out.printf("%s 님이 배정되었습니다.\n", name);
                counselorList.put(name, !hasDone);
                return name;
            } else {
                System.out.println("존재하지 않는 분입니다. 다시 입력해주세요.");
            }
        }
    }

    public void closeProgram() {
        System.out.println("저희 프로그램을 찾아주셔서 감사합니다.");
        System.out.println("다음에 고민거리가 생긴다면 언제든 저희를 찾아주세요.");
        System.out.println("좋은 날들 가득하시길 바라며..🍀");
    }

    public boolean evaluateService(){
        System.out.println("상담은 만족스러우셨나요? y 또는 n으로 답변해주세요.");
        String isSatisfied = handler.getYesOrNo();

        if (isSatisfied.equals("y")) {
            // 프로그램 종료
            System.out.println("만족하셨다니 다행입니다.");
            return true;
        } else{
            return false;
        }
    }

    public Counselor getCounselor(){
        Counselor counselor = null;
        ConsultTask task = new ConsultTask();
        String counselorName = getCounselorName();

        if (counselorName.equals("bri")) {
            counselor = new RationalOrganizedCounselor(counselorName, task);
        } else if (counselorName.equals("ana")) {
            counselor = new RationalFlexibleCounselor(counselorName, task);
        } else if (counselorName.equals("zen")) {
            counselor = new EmotionalFlexibleCounselor(counselorName, task);
        } else if (counselorName.equals("dva")) {
            counselor = new EmotionalOrganizedCounselor(counselorName, task);
        }

        return counselor;
    }

}
