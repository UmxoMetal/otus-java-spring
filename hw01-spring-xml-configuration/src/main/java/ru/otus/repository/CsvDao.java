package ru.otus.repository;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.List;

public interface CsvDao {
    List<Question> getQuestions();

    List<Answer> getAnswers();
}
