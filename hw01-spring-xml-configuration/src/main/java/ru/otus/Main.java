package ru.otus;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.service.impl.PrintServiceImpl;

public class Main {
    private static final String CONTEXT_PATH = "/spring-context.xml";

    /**
     *  1 cd target
     *  2 java - jar hw01-spring-xml-configuration-1.0-SNAPSHOT-jar-with-dependencies.jar
     * */
    public static void main(String[] args) {
        final var classPathXmlApplicationContext = new ClassPathXmlApplicationContext(CONTEXT_PATH);
        final var printService = classPathXmlApplicationContext.getBean(PrintServiceImpl.class);
        printService.getAndPrintQuestionsWithAnswers();
    }
}