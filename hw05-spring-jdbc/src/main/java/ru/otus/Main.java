package ru.otus;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Main.class);
        Console.main(args);  //H2 CONSOLE
    }
}