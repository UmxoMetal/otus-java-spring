package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.repository.AnswerDao;
import ru.otus.service.AnswerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerDao answerDao;

    @Override
    public List<Answer> getAnswersByQuestionId(int questionId) {
        return answerDao.getAllAnswers()
                .stream()
                .filter(answer -> questionId == answer.getQuestionId())
                .toList();
    }
}