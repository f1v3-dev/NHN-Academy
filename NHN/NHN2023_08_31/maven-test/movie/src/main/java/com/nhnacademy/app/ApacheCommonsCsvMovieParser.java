package com.nhnacademy.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class ApacheCommonsCsvMovieParser implements MovieParser {


    @Override
    public List<Movie> parse(String fileName) throws IOException {

        List<Movie> movies = new ArrayList<>();
        FileReader csvData = new FileReader(fileName, StandardCharsets.UTF_8);

        try {
            CSVParser parser = CSVParser.parse(csvData, CSVFormat.EXCEL);


            for (CSVRecord csvRecord : parser) {

                String[] values = csvRecord.values();
                if (values[0].equals("movieId") && values[1].equals("title") && values[2].equals("genres"))
                    continue;

                Long movieId = Long.valueOf(values[0]);
                String title = values[1];

                String genre = values[2];
                StringTokenizer st = new StringTokenizer(genre, "|");
                Set<String> genres = new HashSet<>();
                while (st.hasMoreTokens()){
                    genres.add(st.nextToken());
                }
                movies.add(new Movie(movieId, title, genres));

            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }


        return movies;
    }
}
