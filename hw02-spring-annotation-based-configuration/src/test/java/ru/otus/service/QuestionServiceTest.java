package ru.otus.service;

import ru.otus.domain.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.repository.QuestionDao;
import ru.otus.service.impl.QuestionServiceImpl;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class QuestionServiceTest {
    private static QuestionDao questionDao;
    private static QuestionService questionService;
    private static List<Question> questions;

    @BeforeAll
    public static void init() {
        questionDao = mock(QuestionDao.class);
        questionService = new QuestionServiceImpl(questionDao);
        var question = Question.builder().id(1).build();
        questions = List.of(question);
    }

    @Test
    @DisplayName("Получение всех ответов")
    public void getAllQuestionsTest() {
        when(questionDao.getAllQuestions()).thenReturn(questions);
        var questionsList = questionService.getAllQuestions();
        verify(questionDao, times(1)).getAllQuestions();
        Assertions.assertEquals(questions, questionsList);
    }
}