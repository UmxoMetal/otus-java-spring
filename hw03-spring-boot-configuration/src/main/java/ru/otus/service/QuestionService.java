package ru.otus.service;

import ru.otus.domain.Question;

import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();
}