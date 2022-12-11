package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import ru.otus.domain.Answer;
import ru.otus.repository.AnswerDao;
import ru.otus.service.impl.AnswerServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class AnswerServiceTest {

    private static AnswerDao answerDao;
    private static AnswerService answerService;
    private static Answer firstAnswer;
    private static Answer secondAnswer;
    private static List<Answer> mixQuestionIdAnswers;
    private static List<Answer> validQuestionIdAnswers;

    @BeforeAll
    public static void init() {
        answerDao = mock(AnswerDao.class);
        answerService = new AnswerServiceImpl(answerDao);
        firstAnswer = Answer.builder().questionId(1).build();
        secondAnswer = Answer.builder().questionId(2).build();
        mixQuestionIdAnswers = List.of(firstAnswer, secondAnswer);
        validQuestionIdAnswers = List.of(firstAnswer);
    }

    @Test
    @DisplayName("Получение ответов по id вопроса")
    public void getAnswersByQuestionIdTest() {
        when(answerDao.getAllAnswers()).thenReturn(mixQuestionIdAnswers);
        var answersByQuestionId = answerService.getAnswersByQuestionId(1);
        verify(answerDao, times(1)).getAllAnswers();
        Assertions.assertEquals(validQuestionIdAnswers, answersByQuestionId);
    }
}