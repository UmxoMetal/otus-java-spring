package ru.otus.repository.Impl;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Question;
import ru.otus.repository.CsvDao;
import ru.otus.repository.QuestionDao;

import java.util.List;

@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {

    private final CsvDao csvDao;

    @Override
    public List<Question> getAllQuestions() {
        return csvDao.getQuestions();
    }
}