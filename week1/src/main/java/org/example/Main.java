package org.example;

import org.example.counselor.*;
import org.example.service.ConsultService;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ConsultService consultService = new ConsultService();
        consultService.introduce();
        consultService.singThread.start();

        // todo 이거 consultService 안에 넣기
        while (true) {
            if (!consultService.isPossibleToConsult()) break;
            Counselor counselor = consultService.getCounselor();
            try{
                counselor.consult();
            } catch (NullPointerException e){
                System.out.println(e.getMessage() + " counselor null 입니다.");
            }

            boolean isSatisfied = consultService.evaluateService();
            if (isSatisfied) break;
        }

        consultService.singThread.interrupt();
        consultService.closeProgram();

    }
}