package org.example.service;

import java.util.ArrayList;

public class Song {
    public ArrayList<String> LyricList = new ArrayList<>();

    Song(){
        LyricList.add("여기 내 마음속에 모든 말을\n" + "다 꺼내어 줄 순 없지만\n" + "사랑한다는 말이에요");
        LyricList.add("어떻게 나에게\n" +
                "그대란 행운이 온 걸까\n" +
                "지금 우리 함께 있다면\n" +
                "아, 얼마나 좋을까요");
        LyricList.add("난 파도가 머물던\n" +
                "모래 위에 적힌 글씨처럼\n" +
                "그대가 멀리 사라져 버릴 것 같아\n" +
                "또 그리워 더 그리워");
        LyricList.add("나의 일기장 안에 모든 말을\n" +
                "다 꺼내어 줄 순 없지만\n" +
                "사랑한다는 말");
        LyricList.add("이 밤 그날의 반딧불을 당신의\n" +
                "창 가까이 띄울게요\n" +
                "좋은 꿈이길 바라요");
    }

    public String getLyric(int index){
        return LyricList.get(index);
    }

    public void singWithMelody(int index){
        System.out.println("⭐️ . 🌙 . ⭐️ . 🌙 .");
        System.out.println(getLyric(index));
        System.out.println("⭐️ . 🌙 . ⭐️ . 🌙 .");
    }

}
