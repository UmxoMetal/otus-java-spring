package ru.otus.repository;

import ru.otus.domain.Answer;

import java.util.List;

public interface AnswerDao {
    List<Answer> getAllAnswers();
}