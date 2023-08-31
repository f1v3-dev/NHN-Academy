package com.nhnacademy.app;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

class MovieTest {

    static Stream<Arguments> movieValue() {
        return Stream.of(
                Arguments.of(1L, "Spider Man", new HashSet<>()),
                Arguments.of(2L, "Iron Man", new HashSet<>()),
                Arguments.of(3L, "Captain America", new HashSet<>()),
                Arguments.of(4L, "Avengers", new HashSet<>())
        );
    }

    @Test
    @DisplayName("Movie 객체 생성 테스트")
    void movieConstructorTest() {
        Set<String> genres = new HashSet<>();
        genres.add("Thriller");
        genres.add("Biography");
        genres.add("War");
        Movie movie = new Movie(1, "Oppenheimer", genres);

        Assertions.assertEquals(1, movie.getMovieId());
        Assertions.assertEquals("Oppenheimer", movie.getTitle());
        Assertions.assertEquals(genres, movie.getGenres());
    }

    @Test
    @DisplayName("Movie 객체 생성 테스트 (movieId가 음수인 경우)")
    void negativeMovieContructor() {
        Set<String> genres = new HashSet<>();
        genres.add("Thriller");

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Movie(-1, "movie", genres));
    }

    @Test
    @DisplayName("Movie 객체 생성 테스트 (제목이 없는 경우)")
    void nullTitleMovieConstructor() {
        Set<String> genres = new HashSet<>();
        genres.add("Thriller");

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Movie(1, "", genres));
    }

    @Test
    @DisplayName("Movie 객체 생성 테스트 (1개 이하의 장르인 경우)")
    void nullGenreMovieConstructor() {
        Set<String> genres = new HashSet<>();

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Movie(1, "movie", genres));
    }

    @Test
    @DisplayName("Movie 같은 장르가 2번 들어간 경우")
    void duplicateGenreTest() {
        Set<String> genres = new HashSet<>();
        genres.add("War");
        genres.add("War");

        Assertions.assertEquals(1, genres.size());
    }
}