package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.otus.configuration.AppProperties;
import ru.otus.domain.Answer;
import ru.otus.service.*;

import static java.lang.Integer.parseInt;
import static java.util.Optional.ofNullable;
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private static final String PASSED_RESULT = "PASSED";
    private static final String FAILED_RESULT = "FAILED";
    private final ConsoleInputService consoleInputService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final PrintService printService;
    private final AppProperties appProperties;
    private final LocalizationService localizationService;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void runTest() {
        System.out.println(localizationService.getLocalizationTextByTag("msg.enter.name"));
        var name = consoleInputService.readString();

        System.out.println(localizationService.getLocalizationTextByTag("msg.enter.surname"));
        var surname = consoleInputService.readString();

        var totalScore = questionService.getAllQuestions()
                .stream()
                .mapToInt(question -> {
                    printService.printQuestion(question);
                    var answersByQuestionId = answerService.getAnswersByQuestionId(question.getId());
                    printService.printAnswers(answersByQuestionId);
                    System.out.println(localizationService.getLocalizationTextByTag("msg.enter.answer"));
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

        printService.printResult(totalScore == (parseInt(appProperties.getTestPassedScore())) ?
                PASSED_RESULT :
                FAILED_RESULT, totalScore, name, surname);
    }
}