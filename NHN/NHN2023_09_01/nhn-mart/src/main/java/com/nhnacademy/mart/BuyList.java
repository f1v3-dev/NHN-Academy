package com.nhnacademy.mart;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BuyList Class.
 */
public class BuyList {

    private static final Logger log = LoggerFactory.getLogger(BuyList.class);

    private final ArrayList<Item> items = new ArrayList<>();

    public void add(Item item) {
        items.add(item);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Item Class.
     */
    public static class Item {
        private final String name;
        private final int amount;

        /**
         * Item Class Constructor.
         */
        public Item(String name, int amount) {
            if (name.isEmpty()) {
                log.warn("이름은 공백이 될 수 없습니다.");
                throw new IllegalArgumentException("이름은 공백이 될 수 없습니다.");
            }

            if (amount < 1) {
                log.warn("수량은 1보다 작을 수 없습니다.");
                throw new IllegalArgumentException("수량은 1보다 작을 수 없습니다.");
            }
            this.name = name;
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public int getAmount() {
            return amount;
        }
    }
}
