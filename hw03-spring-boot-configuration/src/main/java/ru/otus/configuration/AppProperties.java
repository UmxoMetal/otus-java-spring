package ru.otus.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Getter
@Configuration
public class AppProperties {

    @Value("${test.passed-score}")
    private String testPassedScore;

    @Value("${test.file-name}")
    private Resource resourceFile;
}