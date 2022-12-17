package ru.otus.repository;

import ru.otus.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> getAllQuestions();
}