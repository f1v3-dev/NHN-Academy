package com.nhnacademy.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ApacheCommonsCsvMovieParserTest {

    @Test
    @DisplayName("존재하지 않는 파일을 넘긴 경우 테스트")
    void notExistFileTest() {
        MovieParser parser = new ApacheCommonsCsvMovieParser();
        Assertions.assertThrows(FileNotFoundException.class, () -> parser.parse("NOT_EXIST_FILE.csv"));
    }
}