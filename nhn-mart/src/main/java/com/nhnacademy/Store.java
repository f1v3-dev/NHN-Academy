package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 매장은 물건을 납품 받아서 판매한다.
 * 매장에는 최대 '10개의 물건'만 전시할 수 있다.
 * 매장은 '최대 5명'까지만 동시 입장 가능하다.
 * 매장에서 물건 구매는 동시에 1명만 가능하다.
 * 매장에서 물건 판매 후 빈 공간에 생기면 생산자에게 알려 준다.
 * 매장에서 물건 납품은 동시에 1명만 가능하다.
 * 매장에서 물건이 들어오면 소비자에게 알려 준다.
 */
public class Store {
    ExecutorService consumerPool;
    ExecutorService producerPool;
    List<Item> itemList;
    int maxItemCount = 10;

    public Store() {
        this(5, 1);
    }

    public Store(int consumerPoolSize, int producerPoolSize) {
        itemList = new ArrayList<>();
        consumerPool = Executors.newFixedThreadPool(consumerPoolSize);
        producerPool = Executors.newFixedThreadPool(producerPoolSize);

    }

    /**
     * consumer 입장
     * 최대 5명까지만 입장
     */
    public void enter(Consumer consumer) {
        consumerPool.submit(consumer);
    }

    /**
     * consumer 퇴장
     * 물건 구매시 퇴장
     */
    public void exit(Consumer consumer) {
        System.out.println(consumer.getName() + " 나가요~");
    }

    /**
     * producer가 물건을 생산해서 넘김
     * 물건을 받아 진열대를 채움
     * consumer를 깨움
     */
    public synchronized void buy(Item item) {
        while (itemList.size() >= maxItemCount) {
            try {
                wait();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }
        itemList.add(item);

        notifyAll();
    }

    /**
     * consumer에게 물건을 넘김
     * producer를 깨워 물건을 만들게함
     */
    public synchronized void sell() {
        while (itemList.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        }

        Item item = itemList.remove(0);
        System.out.println(item.getName() + " 구매");
        notifyAll();

    }
}