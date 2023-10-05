package com.nhnacademy;

/**
 * 생산자는 매장에 물건이 부족하지 않도록 채워둔다.
 * 물건은 1~10초 간격으로 채운다.
 * Thread내에서 난수 생성을 위해서는 ThreadLocalRandom.current().nextInt()를 사용하면 된다.
 */

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    Thread thread;
    Store store;

    public Producer(Store store) {
        thread = new Thread(this);
        this.store = store;
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (!thread.isInterrupted()) {
                store.buy(new Item("item"));
                System.out.println("생산자가 물건을 만들었습니다.");
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}