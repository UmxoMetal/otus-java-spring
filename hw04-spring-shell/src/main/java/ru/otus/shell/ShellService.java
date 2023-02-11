package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.service.TestService;

@ShellComponent
@RequiredArgsConstructor
public class ShellService {
    private final TestService testService;

    @ShellMethod(value = "Start test application", key = {"start-test"})
    public void startTest() {
        testService.runTest();
    }
}