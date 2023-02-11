package ru.otus.service;

import ru.otus.domain.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> getAnswersByQuestionId(int questionId);
}