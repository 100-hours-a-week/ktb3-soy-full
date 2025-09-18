package org.example;

import java.util.HashMap;

public class ConsultService {

    ConsultService(){}

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
    public boolean isPossibleToConsult(HashMap<String, Boolean> counselorList) {

        for (String name : counselorList.keySet()) {
            if (counselorList.get(name)) { // 가능한 상담사가 있다면
                return true;
            }
        }

        return false;
    }
    public void closeProgram() {
        System.out.println("저희 프로그램을 찾아주셔서 감사합니다.");
        System.out.println("다음에 고민거리가 생긴다면 언제든 저희를 찾아주세요.");
        System.out.println("좋은 날들 가득하시길 바라며..🍀");
    }

}
