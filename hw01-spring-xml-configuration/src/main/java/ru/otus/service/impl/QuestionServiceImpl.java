package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Question;
import ru.otus.repository.QuestionDao;
import ru.otus.service.QuestionService;

import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }
}