package ru.otus.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvUtils {
    public static List<String[]> readCsv(String fileName) {
        try (var inputStream = new ClassPathResource(fileName).getInputStream();
             var reader = new CSVReader(new InputStreamReader(inputStream))) {
            return reader.readAll();
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}