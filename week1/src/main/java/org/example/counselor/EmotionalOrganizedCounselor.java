package org.example.counselor;

public class EmotionalOrganizedCounselor extends EmotionalCounselor {
    public EmotionalOrganizedCounselor(String name) {
        super(name);
    }

    public void askSecondQuestion(){
        System.out.println("어떻게 할지 계획한 게 있을까요?");
    }

    public void adviceSecondQuestionYes() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        super.hug();
        System.out.println("다행이에요!");
        System.out.println("일단 지금은 계획한 것을 하나씩 해보면 어떨까요?");
        System.out.println("그렇게 하면 조금씩 나아질거에요. 제가 응원할게요!");
        System.out.println("-".repeat(50));
    }

    public void adviceSecondQuestionNo() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        super.hug();
        System.out.println("괜찮아요 걱정말아요!");
        System.out.println("당신은 혼자가 아니에요.");
        System.out.println("당장 할 수 있는게 무엇일 것 같나요? 하나씩 같이 해봐요!");
        System.out.println("-".repeat(50));
    }

    public void consult() throws InterruptedException {
        askFirstQuestion();
        String isStressed = inputHandler.getYesOrNo();
        if (isStressed.equals(NO)) {
            askSecondQuestion();
            String hasPlan = inputHandler.getYesOrNo();
            if (hasPlan.equals(YES)) {
                adviceSecondQuestionYes();
            } else {
                adviceSecondQuestionNo();
            }
        } else {
            adviceFirstQuestion();
        }
    }

}
