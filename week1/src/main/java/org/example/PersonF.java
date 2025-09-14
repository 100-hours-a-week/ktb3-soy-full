package org.example;

public class PersonF extends Person {
    public PersonF(String name) {
        super(name);
    }

    public void askFirstQuestion(){
        System.out.println("그 문제를 생각하면 심장이 두근대고 떨리나요?");
    }

    public void adviceFirstQuestion() throws InterruptedException {
        System.out.println("-".repeat(50));
        super.think();
        this.hug();
        System.out.println("지금은 휴식을 취하셔야 합니다.");
        System.out.println("시원한 바람이 부는 바다로 가서 마음 놓고 휴식을 취해봅시다.");
        System.out.println("해결은 지금 도움이 되지 않아요.");
        System.out.println("-".repeat(50));
    }

    public void hug(){
        System.out.printf("%s가 당신에게 다가옵니다..\n", this.name);
        System.out.println("。.:☆*:･'(*⌒―⌒*)))");
        System.out.printf("%s가 따듯하게 안아줍니다..!\n", this.name);
    }
}
