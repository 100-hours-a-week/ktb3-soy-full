package org.example;

public class PersonFP extends PersonF{
    public PersonFP(String name) {
        super(name);
    }

    public void askSecondQuestion()
    {
        System.out.println("지금 당장 하고 싶은 일이 있나요?");
    }

    public void adviceSecondQuestionYes() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        super.hug();
        System.out.println("적어도 하고 싶은 일이 있어서 다행이에요!!!");
        System.out.println("고민은 차차 풀어도 좋으니, 시간을 가져볼까요?");
        System.out.println("지금은 하고 싶은걸 하면서 마음을 비워보아요. 제가 함께할게요!");
        System.out.println("-".repeat(50));
    }

    public void adviceSecondQuestionNo() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        super.hug();
        System.out.println("아이고 괜찮아요.");
        System.out.println("그럼 잠깐 산책하고 오는 건 어때요?");
        System.out.println("제가 함께 할게요!");
        System.out.println("-".repeat(50));
    }
}
