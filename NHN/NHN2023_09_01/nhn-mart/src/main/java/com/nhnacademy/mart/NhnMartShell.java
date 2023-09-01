package com.nhnacademy.mart;

import java.util.Scanner;
import java.util.StringTokenizer;

public class NhnMartShell {

    public static void main(String[] args) {
        NhnMart mart = new NhnMart();
        mart.prepareMart();

        BuyList buyList = inputBuyListFromShell();

        // TODO 본인이름 영어로 변수명 작성!
        // 본인이름을 각자 맞게 영어로 변경
        // 홍길동 학생
        // -> hongGilDong or gilDong
        Customer seungJo = new Customer(buyList);

        // 장바구니를 챙긴다.
        seungJo.bring(mart.provideBasket());

        // 식품을 담는다.
        seungJo.pickFoods(mart.getFoodStand());

        // 카운터에서 계산한다.
        seungJo.payTox(mart.getCounter());
    }

    private static BuyList inputBuyListFromShell() {
        // TODO Scanner 입력을 받아 buyList 만들기
        Scanner sc = new Scanner(System.in);

        System.out.println("NHN 마트에 오신 것을 환영합니다. 사고 싶은 물건을 골라주세요. \n");

        BuyList buyList = new BuyList();

        StringTokenizer st = new StringTokenizer(sc.nextLine(), " ");
        while (st.hasMoreTokens()) {
            String name = st.nextToken();
            int amount = Integer.parseInt(st.nextToken());
            buyList.add(new BuyList.Item(name, amount));
        }

        return buyList;
    }
}
