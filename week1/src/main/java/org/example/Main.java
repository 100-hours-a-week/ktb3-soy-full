package org.example;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static <Int> void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        introduceProgram();
        introduceType();

        // <상담사 이름, 상담가능여부>
        HashMap<String, Boolean> counselorList = new HashMap<>();
        counselorList.put("bri", true);
        counselorList.put("ana", true);
        counselorList.put("dva", true);
        counselorList.put("zen", true);


        while (true) {

            if (isPossibleToConsult(counselorList) == false){
                System.out.println("이런, 찾을 수 있는 상담사가 없습니다.");
                break;
            }

            System.out.println("마음에 드는 상담사 분의 이름을 입력해주세요.");
            String name = sc.next();

            if (counselorList.containsKey(name) == false) {
                System.out.println("저희 프로그램 상담사가 아닙니다. 다시 선정하세요.");
                continue;
            }

            if (counselorList.get(name) == false) {
                System.out.println("이미 상담을 완료한 상담사입니다. 다시 선정하세요.");
                continue;
            }

            counselorList.put(name, false); // 상담사 상담 가능 상태 수정

            if (name.equals("bri")) {
                PersonTJ counselor = new PersonTJ(name);
                counselor.askFirstQuestion();
                String hasSolution = sc.next();
                if (hasSolution.equals("y")) {
                    counselor.askSecondQuestion();
                    String hasPlan = sc.next();
                    if (hasPlan.equals("y")) {
                        counselor.adviceSecondQuestionYes();
                    } else {
                        counselor.adviceSecondQuestionNo();
                    }
                } else {
                    counselor.adviceFirstQuestion();
                }
            } else if (name.equals("ana")) {
                PersonTP counselor = new PersonTP(name);
                counselor.askFirstQuestion();
                String ans = sc.next();
                if (ans.equals("y")) {
                    counselor.askSecondQuestion();
                    int deadline = sc.nextInt();
                    if (deadline > 7) {
                        counselor.adviceToSecondQuestionYesTime();
                    } else {
                        counselor.adviceToSecondQuestionNoTime();
                    }
                } else {
                    counselor.adviceFirstQuestion();
                }
            } else if (name.equals("zen")) {
                PersonFP counselor = new PersonFP(name);
                counselor.askFirstQuestion();
                String isStressed = sc.next();
                if (isStressed.equals("n")) {
                    counselor.askSecondQuestion();
                    String hasDesireFor = sc.next();
                    if (hasDesireFor.equals("y")) {
                        counselor.adviceSecondQuestionYes();
                    } else {
                        counselor.adviceSecondQuestionNo();
                    }
                } else {
                    counselor.adviceFirstQuestion();
                }
            } else if (name.equals("dva")) {
                PersonFJ counselor = new PersonFJ(name);
                counselor.askFirstQuestion();
                String isStressed = sc.next();
                if (isStressed.equals("n")) {
                    counselor.askSecondQuestion();
                    String hasPlan = sc.next();
                    if (hasPlan.equals("y")) {
                        counselor.adviceSecondQuestionYes();
                    } else {
                        counselor.adviceSecondQuestionNo();
                    }
                } else {
                    counselor.adviceFirstQuestion();
                }
            }

            System.out.println("상담은 만족스러우셨나요? y 또는 n으로 답변해주세요.");
            String isSatisfied = sc.next();

            if (isSatisfied.equals("y")) {
                // 프로그램 종료
                System.out.println("만족하셨다니 다행입니다.");
                break;
            }

        }

        closeProgram();

    }

    private static void closeProgram() {
        System.out.println("저희 프로그램을 찾아주셔서 감사합니다.");
        System.out.println("다음에 고민거리가 생긴다면 언제든 저희를 찾아주세요.");
        System.out.println("좋은 날들 가득하시길 바라며..🍀");
        System.exit(0);
    }

    private static boolean isPossibleToConsult(HashMap<String, Boolean> counselorList) {

        for (String name : counselorList.keySet()) {
            if (counselorList.get(name)) { // 가능한 상담사가 있다면
                return true;
            }
        }

        return false;
    }

    private static void introduceProgram() {
        System.out.println("고민이 있나요? 어떻게 해결하면 좋을지 알듯 말듯 잘 모르시겠나요?");
        System.out.println("잘 오셨습니다. 이곳의 멋진 상담사분들과 함께 때론 명쾌하고 때론 따뜻하게 문제를 풀어보아요.");
        System.out.println("만족하실때까지 도와드리겠습니다!");
    }

    private static void introduceType() {

        System.out.println("최고의 상담사들을 소개합니다.");
        System.out.println("-".repeat(50));
        System.out.printf("|  |이름|MBTI|%s자기소개%s|\n", " ".repeat(16), " ".repeat(15));
        System.out.printf("|🧐|bri|I_TJ|고민을 해결하는 고속도로 열어드립니다.%s|\n", " ".repeat(8));
        System.out.printf("|🤓|ana|I_TP|고민 들어드립니다. 반박시 당신 말이 맞습니다.%s|\n", " ".repeat(2));
        System.out.printf("|😉|dva|I_FJ|고민해결을 도와드립니다! 함께 나눠요~%s|\n", " ".repeat(8));
        System.out.printf("|😌|zen|I_FP|고민 들어드려요! 슬픔은 나누면 반이 되니까,,%s|\n", " ".repeat(2));
        System.out.println("-".repeat(50));
    }
}