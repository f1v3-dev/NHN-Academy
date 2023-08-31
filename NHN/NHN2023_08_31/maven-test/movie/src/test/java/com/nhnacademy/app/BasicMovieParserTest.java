package com.nhnacademy.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BasicMovieParserTest {

    @Test
    @DisplayName("존재하지 않는 파일을 넘긴 경우 테스트")
    void notExistFileTest() {
        MovieParser parser = new BasicMovieParser();
        Assertions.assertThrows(FileNotFoundException.class, () -> parser.parse("NOT_EXIST_FILE.csv"));
    }
}