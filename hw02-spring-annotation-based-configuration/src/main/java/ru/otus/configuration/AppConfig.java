package ru.otus.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.repository.CsvDao;
import ru.otus.repository.Impl.CsvDaoImpl;

@Configuration
public class AppConfig {
    @Bean
    CsvDao csvdao(@Value("${test.file.name}") String fileName) {
        return new CsvDaoImpl(fileName);
    }
}