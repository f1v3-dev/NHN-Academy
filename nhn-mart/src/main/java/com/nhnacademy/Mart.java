package com.nhnacademy;

public class Mart {
    public static void main(String[] args) {
        Store store = new Store();
        int count = 1;
        Producer producer = new Producer(store);
        producer.start();
        System.out.println("producer.run()실행");
        for (int i = 0; i < 15; i++) {
            store.enter(new Consumer("손님" + count++, store));
        }
    }
}