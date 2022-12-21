package ru.otus.service.impl;

import ru.otus.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.otus.service.*;


import static java.lang.Integer.parseInt;
import static java.util.Optional.ofNullable;
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private static final String PASSED_RESULT = "PASSED";
    private static final String FAILED_RESULT = "FAILED";
    private static final String PASSED_SCORE_PROP_NAME = "test.pass.score";

    private final ConsoleInputService consoleInputService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final PrintService printService;
    private final Environment environment;

    @Override
    public void runTest() {
        System.out.println("Welcome! Please enter your name:");
        var name = consoleInputService.readString();

        System.out.println("Enter your surname:");
        var surname = consoleInputService.readString();

        var totalScore = questionService.getAllQuestions()
                .stream()
                .mapToInt(question -> {
                    printService.printQuestion(question);
                    var answersByQuestionId = answerService.getAnswersByQuestionId(question.getId());
                    printService.printAnswers(answersByQuestionId);
                    System.out.println("Enter your answer");
                    var userAnswerId = consoleInputService.readInt();
                    System.out.println(userAnswerId);
                    var correctAnswerId = answersByQuestionId
                            .stream()
                            .filter(Answer::isCorrect)
                            .mapToInt(Answer::getId)
                            .findFirst()
                            .orElseThrow();
                    return correctAnswerId == userAnswerId ? 1 : 0;
                })
                .sum();

        printService.printResult(totalScore == (parseInt(ofNullable(environment.getProperty(PASSED_SCORE_PROP_NAME))
                .orElseThrow())) ?
                PASSED_RESULT :
                FAILED_RESULT, totalScore, name, surname);
    }
}