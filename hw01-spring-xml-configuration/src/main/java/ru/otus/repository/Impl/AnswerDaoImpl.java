package ru.otus.repository.Impl;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Answer;
import ru.otus.repository.AnswerDao;
import ru.otus.repository.CsvDao;

import java.util.List;

@RequiredArgsConstructor
public class AnswerDaoImpl implements AnswerDao {

  private final CsvDao csvDao;

  @Override
  public List<Answer> getAllAnswers() {
    return csvDao.getAnswers();
  }
}