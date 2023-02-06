package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.service.ConsoleInputService;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ConsoleInputServiceImpl implements ConsoleInputService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public int readInt() {
        return scanner.nextInt();
    }

    @Override
    public String readString() {
        return scanner.nextLine().trim();
    }
}