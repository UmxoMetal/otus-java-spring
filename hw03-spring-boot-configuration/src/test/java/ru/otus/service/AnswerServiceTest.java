package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.Answer;
import ru.otus.repository.AnswerDao;
import ru.otus.service.impl.AnswerServiceImpl;
import ru.otus.service.impl.QuestionServiceImpl;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = AnswerServiceImpl.class)
public class AnswerServiceTest {
    @MockBean
    private AnswerDao answerDao;
    @Autowired
    private AnswerService answerService;
    private static Answer firstAnswer;
    private static Answer secondAnswer;
    private static List<Answer> mixQuestionIdAnswers;
    private static List<Answer> validQuestionIdAnswers;

    @BeforeAll
    public static void init() {
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