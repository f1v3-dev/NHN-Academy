package com.nhnacademy.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BasicMovieParser implements MovieParser {
    @Override
    public List<Movie> parse(String fileName) throws IOException {

        List<Movie> movies = new ArrayList<>();

        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            // 첫 번째의 줄은 어떤 값인지 알려주는 것이므로 넘긴다.
//            String line = br.readLine();

            String line = "";

            while ((line = br.readLine()) != null) {
                if (line.contains("movieId")) continue;
                StringTokenizer st = new StringTokenizer(line, ",");
                String movieId = st.nextToken();
                String title = st.nextToken();

                StringTokenizer stGenres = new StringTokenizer(st.nextToken(), "|");
                Set<String> genres = new HashSet<>();
                while (stGenres.hasMoreTokens()) {
                    genres.add(stGenres.nextToken());
                }
                movies.add(new Movie(Long.valueOf(movieId), title, genres));
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return movies;
    }
}
