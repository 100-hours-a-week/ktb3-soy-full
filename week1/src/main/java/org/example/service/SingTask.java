package org.example.service;

public class SingTask implements Runnable {
    public int singTaskDuration;
    public Song song = new Song();
    public SingTask(int singTaskDuration){
        this.singTaskDuration = singTaskDuration;
    }

    @Override
    public void run() {
        int index = 0;
        while (true) {
            song.singWithMelody(index);
            try {
                Thread.sleep(singTaskDuration);
            } catch (InterruptedException e) {
                break;
            }
            index = (index + 1) % song.LyricList.toArray().length;
        }
    }
}
