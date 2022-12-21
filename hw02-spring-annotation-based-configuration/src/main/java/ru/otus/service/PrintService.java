package ru.otus.service;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import lombok.NonNull;

import java.util.Collection;

public interface PrintService {
    void printQuestion(@NonNull Question question);

    void printAnswers(Collection<Answer> answers);

    void printResult(String result, int correctAnswersQty, String name, String surname);
}