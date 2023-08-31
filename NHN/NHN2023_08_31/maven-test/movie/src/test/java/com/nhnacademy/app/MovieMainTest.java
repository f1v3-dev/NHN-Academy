package com.nhnacademy.app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovieMainTest {

    @Test
    @DisplayName("MovieMain 테스트")
    void movieTest() throws IOException {

        // 1,Toy Story (1995),Adventure|Animation|Children|Comedy|Fantasy
        MovieParser parser = new BasicMovieParser();
        List<Movie> result = parser.parse("test.csv");
        Assertions.assertEquals(10, result.size());
    }

}