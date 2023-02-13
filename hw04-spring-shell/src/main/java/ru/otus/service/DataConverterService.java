package ru.otus.service;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static java.lang.Integer.parseInt;

public interface DataConverterService {
    Question convertCsvLineToQuestion(int questionId, String[] csvLine);

    List<Answer> convertCsvLineToAnswers(int questionId, String[] csvLine);
}