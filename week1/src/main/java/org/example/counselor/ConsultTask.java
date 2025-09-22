package org.example.counselor;

public class ConsultTask implements Runnable{
    private Counselor counselor;

    @Override
    public void run() {
        try {
            counselor.consult();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
