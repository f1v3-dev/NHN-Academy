package TestCode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LibraryTest {

    @Test
    @DisplayName("preCondition Test")
    void preConditionTest() {
//        JUnit을 사용하지 않았을 때의 Test Code
//        try {
//            new Library(-1);
//        } catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//        }

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Library(-1));
    }


    @ParameterizedTest
    @DisplayName("Library constructor Test")
    @ValueSource(ints = {1, 10, 100, 1000})
    void libraryTest(int size) {
        Library library = new Library(size);

        Assertions.assertEquals(size, library.getLibrarySize());
    }

    /**
     * add()의 경우 exception 2개의 상황도
     * 테스트 코드로 작성해 주어야 한다.
     */
    @Test
    @DisplayName("Library add() Test")
    void addTest() {
        Library library = new Library(1);
        library.add("book 1");

        Assertions.assertEquals(1, library.getCount());
    }

    @Test
    @DisplayName("Library 꽉 찬 경우 테스트")
    void fullLibraryTest() {
        Library library = new Library(1);
        library.add("book 1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> library.add("book 2"));
    }

    @Test
    @DisplayName("Library 책 이름이 중복된 경우 테스트")
    void sameLibraryTest() {
        Library library = new Library(2);
        library.add("book 1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> library.add("book 1"));
    }

    @Test
    @DisplayName("Library delete() Test")
    void deleteTest() {
        Library library = new Library(1);
        library.add("book 1");
        library.delete("book 1");

        Assertions.assertEquals(0, library.getCount());
    }

    @Test
    @DisplayName("Library 책장이 빈 경우 테스트")
    void emptyLibraryTest() {
        Library library = new Library(1);

        Assertions.assertThrows(IllegalArgumentException.class, () -> library.delete("book 1"));
    }

    @Test
    @DisplayName("Library 없는 책을 지운 경우 테스트")
    void hasNotLibraryTest() {
        Library library = new Library(2);
        library.add("book 1");

        Assertions.assertThrows(IllegalArgumentException.class, () -> library.delete("book 2"));
    }

}
