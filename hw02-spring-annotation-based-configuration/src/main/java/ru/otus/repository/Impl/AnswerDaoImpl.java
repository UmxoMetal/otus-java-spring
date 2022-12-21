package ru.otus.repository.Impl;

import ru.otus.domain.Answer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.repository.AnswerDao;
import ru.otus.repository.CsvDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerDaoImpl implements AnswerDao {
  private final CsvDao csvDao;

  @Override
  public List<Answer> getAllAnswers() {
    return csvDao.getAnswers();
  }
}