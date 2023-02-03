package ru.otus.service;

import lombok.NonNull;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.Collection;

public interface PrintService {
    void printQuestion(@NonNull Question question);

    void printAnswers(Collection<Answer> answers);

    void printResult(String result, int correctAnswersQty, String name, String surname);
}