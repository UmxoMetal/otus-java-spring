package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Answer;
import ru.otus.service.AnswerService;
import ru.otus.service.PrintService;
import ru.otus.service.QuestionService;

import static java.util.stream.Collectors.joining;

@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {

    private static final String QUESTION_PRINTER_TEMPLATE = """
           ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
            QUESTION № %s
                   %s
            ANSWERS :
           %s
           """;

    private final QuestionService questionService;
    private final AnswerService answerService;

    @Override
    public void getAndPrintQuestionsWithAnswers() {
        questionService.getAllQuestions()
                .forEach(question -> {
                    int questionId = question.getId();
                    System.out.printf(QUESTION_PRINTER_TEMPLATE, questionId, question.getText(), answerService.getAnswersByQuestionId(questionId)
                            .stream()
                            .map(Answer::getText)
                            .collect(joining("\n")));
                }
        );
    }
}