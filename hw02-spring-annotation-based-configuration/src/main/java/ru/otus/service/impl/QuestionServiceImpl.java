package ru.otus.service.impl;

import ru.otus.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.repository.QuestionDao;
import ru.otus.service.QuestionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDao questionDao;

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }
}