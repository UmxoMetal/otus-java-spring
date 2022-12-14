package ru.otus.repository.Impl;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.repository.CsvDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Boolean.TRUE;
import static java.lang.Integer.parseInt;
import static ru.otus.util.CsvUtils.readCsv;

public class CsvDaoImpl implements CsvDao {

    private List<String[]> csvData;
    private List<Question> questions;
    private List<Answer> answers;

    public CsvDaoImpl(String pathToCsv) {
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        csvData = readCsv(pathToCsv);
        parseData();
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public List<Answer> getAnswers() {
        return answers;
    }

    private void parseData() {
        var questionId = new AtomicInteger(1);
        csvData
                .forEach(csvLine -> {
                    questions.add(Question.builder()
                            .id(questionId.get())
                            .text(csvLine[0])
                            .build());
                    retrieveAndCreateAnswersFromCsvLine(csvLine, questionId.getAndIncrement());
                });
    }

    private void retrieveAndCreateAnswersFromCsvLine(String[] csvLine, int questionId) {
        var correctAnswerIndex = parseInt(csvLine[csvLine.length - 1]);
        for (int index = 1; index < csvLine.length - 1; index++) {
            var answerBuilder = Answer.builder()
                    .id(index)
                    .questionId(questionId)
                    .text(csvLine[index]);
            if (index == correctAnswerIndex) {
                answerBuilder.isCorrect(TRUE);
            }
            answers.add(answerBuilder.build());
        }
    }
}