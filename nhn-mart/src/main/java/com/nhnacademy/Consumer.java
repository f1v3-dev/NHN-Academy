package com.nhnacademy;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 소비자는 매장에 입장 후 물건을 구매할 수 있다.
 * 매장에는 입장 인원 제한이 있으므로, 인원 초과시 기다린다.
 * 매장에 입장하면 물건을 구매하고, 퇴장한다.
 * 1~10초 간격으로 구매한다.
 */

public class Consumer implements Runnable {
    Store store;
    Thread thread;

    public Consumer(String name, Store store) {
        this.thread = new Thread(this, name);
        this.store = store;
    }

    public String getName() {
        return thread.getName();
    }

    public Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        System.out.println(getName() + " 강림");
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 10000));
            store.sell();
            store.exit(this);
        } catch (InterruptedException e) {
            thread.currentThread().interrupt();
        }
    }
}