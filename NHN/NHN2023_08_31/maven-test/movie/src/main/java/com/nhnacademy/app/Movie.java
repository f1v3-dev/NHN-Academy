package com.nhnacademy.app;

import java.util.Set;

public class Movie {
    private final long movieId;
    private final String title;
    private final Set<String> genres;

    public Movie(long movieId, String title, Set<String> genres) {
        if (movieId < 0) {
            throw new IllegalArgumentException("영화의 ID는 0보다 작을 수 없습니다.");
        }

        if (title.isBlank() || title.isEmpty()) {
            throw new IllegalArgumentException("제목은 공백일 수 없습니다.");
        }

        if (genres.isEmpty()) {
            throw new IllegalArgumentException("1개 이상의 장르를 넣어야 합니다.");
        }

        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
    }

    public long getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getGenres() {
        return genres;
    }


    @Override
    public String toString() {
        return "[" + getMovieId() + "] " +  getTitle() + " " + getGenres();
    }
}
