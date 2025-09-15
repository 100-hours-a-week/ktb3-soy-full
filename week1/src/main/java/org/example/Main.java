package org.example;

import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static <Int> void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        HashMap<String, Boolean> counselorList = new HashMap<>();
        counselorList.put("bri", true);
        counselorList.put("ana", true);
        counselorList.put("dva", true);
        counselorList.put("zen", true);


        ConsultProgram consultProgram = new ConsultProgram();
        consultProgram.introduceProgram();
        consultProgram.introduceCounselor();

        while (true) {

            if (consultProgram.isPossibleToConsult(counselorList) == false){
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
                RationalOrganizedCounselor counselor = new RationalOrganizedCounselor(name);
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
                RationalFlexibleCounselor counselor = new RationalFlexibleCounselor(name);
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
                EmotionalFlexibleCounselor counselor = new EmotionalFlexibleCounselor(name);
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
                EmotionalOrganizedCounselor counselor = new EmotionalOrganizedCounselor(name);
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

        consultProgram.closeProgram();

    }





}