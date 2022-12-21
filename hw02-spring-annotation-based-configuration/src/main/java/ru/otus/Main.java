package ru.otus;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.impl.TestServiceImpl;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        final var annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Main.class);
        annotationConfigApplicationContext.getBean(TestServiceImpl.class).runTest();
    }
}