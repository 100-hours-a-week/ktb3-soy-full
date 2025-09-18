package org.example.utility;

import java.util.Scanner;

public class InputHandler {
    Scanner sc = new Scanner(System.in);

    public String getString(){return sc.next();}

    public String getYesOrNo() {
        while (true) {
            String answer = getString();
            if (answer.equals("y") || answer.equals("n")) { return answer; }
            else { System.out.println("[입력 에러] y 또는 n만 입력하세요."); }
        }
    }

    public int getDeadline(){
        while (true) {
            try{
                String answer = getString();
                int deadline = Integer.parseInt(answer);
                return deadline;
            } catch (Exception e){
                System.out.println("[입력 에러] 숫자를 입력하세요.");
            }


        }
    }

}
