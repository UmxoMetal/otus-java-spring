package ru.otus.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Question;
import ru.otus.repository.CsvDao;
import ru.otus.repository.QuestionDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    private final CsvDao csvDao;

    @Override
    public List<Question> getAllQuestions() {
        return csvDao.getQuestions();
    }
}