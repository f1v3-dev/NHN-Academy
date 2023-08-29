package test;

public class Library {

    private String[] list;
    private int count;
    public Library(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("도서관 크기는 1보다 작은 값을 넣을 수 없습니다.");

        list = new String[size];
        count = 0;
        for (int i = 0; i < size; i++) {
            list[i] = "";
        }
    }

    public void add(String title) {

        boolean isContain = false;

        if (list.length <= count) {
//            throw new IllegalArgumentException("책장이 꽉 찼습니다. 책장을 비워주세요");
            System.out.println("책장이 꽉 찼습니다. 책장을 비워주세요.");
            return;
        }

        for (int i = 0; i < count; i++) {
            if (list[i].equals(title)) {
//                throw new IllegalArgumentException("[" + title + "] 은 이미 있는 책입니다.");
                isContain = true;
            }
        }

        if (isContain) {
            System.out.println("[" + title + "] 은 이미 있는 책입니다.");
        } else {
            System.out.println("[" + title + "] 책을 추가 했습니다.");
            list[count++] = title;
        }
    }

    public void delete(String title) {

        boolean isContain = false;

        for (int i = 0; i < count; i++) {
            if (list[i].equals(title)) {
                isContain = true;
                list[i] = "";
            }
        }

        if (isContain) {
            System.out.println("[" + title + "] 책을 지웠습니다.");
            count--;
        } else {
            System.out.println("없는 [" + title + "] 책을 지울 수 없습니다.");
        }
    }

    public void find(String title) {
        boolean isContain = false;
        for (int i = 0; i < count; i++) {
            if (list[i].equals(title)) {
                isContain = true;
                break;
            }
        }

        if (isContain) {
            System.out.println("책 검색 결과 : " + title);
        } else {
            System.out.println("검색한 책 [" + title + "]은 도서관에 없습니다.");
        }


    }

    public void findAll() {
        System.out.println();
        System.out.println("모든 책 출력 : ");

        System.out.println("=====================");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + " : " +  list[i]);
        }
        System.out.println("=====================");
    }
}
