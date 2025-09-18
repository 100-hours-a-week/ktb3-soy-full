package org.example;

import java.util.Scanner;

public class InputHandler {
    Scanner scanner;

    InputHandler(Scanner sc){
        this.scanner = sc;
    }

    public String getNameOfCounselor(){
        return this.scanner.next();
    }

    public boolean hasSolution(){
        while (true){
            String ans = this.scanner.next();
            if (ans.equals('y')) {return true;}
            else if (ans.equals('n')){return false;}
            System.out.println("y 또는 n을 입력해주세요.");
        }
    }

}
