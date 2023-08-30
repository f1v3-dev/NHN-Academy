package TestCode;

public class Library {

    private String[] list;
    private int count;

    public Library(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("도서관 크기는 1보다 작은 값을 넣을 수 없습니다.");
        }

        list = new String[size];
        count = 0;
        for (int i = 0; i < size; i++) {
            list[i] = "";
        }
    }

    public void add(String title) {
        if (count == list.length) {
            throw new IllegalArgumentException("책장이 꽉 찼습니다. 책장을 비워주세요.");
        }

        for (int i = 0; i < count; i++) {
            if (list[i].equals(title)) {
                throw new IllegalArgumentException("[" + title + "] 은 이미 있는 책입니다.");
            }
        }

        list[count++] = title;
        System.out.println("[" + title + "] 책을 추가 했습니다.");
    }

    public void delete(String title) {

        if (count == 0) {
            throw new IllegalArgumentException("책장이 비어있어서 삭제가 불가능합니다.");
        }

        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(title)) {
                for (int j = i; j < list.length - 1; j++) {
                    list[j] = list[j + 1];
                }

                list[list.length - 1] = "";
                count--;
                return;
            }
        }

        throw new IllegalArgumentException("없는 [" + title + "] 책을 지울 수 없습니다.");

    }

    public void find(String title) {

        for (int i = 0; i < count; i++) {
            if (list[i].equals(title)) {
                System.out.println("책 검색 결과 : " + title);
                return;
            }
        }

        System.out.println("검색한 책 [" + title + "]은 도서관에 없습니다.");
    }

    public void findAll() {
        System.out.println("\n모든 책 출력 : ");

        System.out.println("=====================");
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals("")) {
                continue;
            }

            System.out.println((i + 1) + " : " + list[i]);
        }

        System.out.println("=====================");
    }
}
