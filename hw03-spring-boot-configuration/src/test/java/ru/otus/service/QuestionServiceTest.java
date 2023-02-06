package ru.otus.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.domain.Question;
import ru.otus.repository.QuestionDao;
import ru.otus.service.impl.QuestionServiceImpl;

import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest(classes = QuestionServiceImpl.class)
public class QuestionServiceTest {
    @MockBean
    private QuestionDao questionDao;
    @Autowired
    private QuestionService questionService;
    private static List<Question> questions;

    @BeforeAll
    public static void init() {
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