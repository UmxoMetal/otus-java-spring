package ru.otus.repository.Impl;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.repository.CsvDao;
import ru.otus.util.CsvUtils;
import ru.otus.util.DataConverter;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.collections.CollectionUtils.*;

@RequiredArgsConstructor
public class CsvDaoImpl implements CsvDao {
    private final String pathToCsv;
    private final List<Question> questions = new ArrayList<>();
    private final List<Answer> answers = new ArrayList<>();
    private List<String[]> csvData;

    @Override
    public List<Question> getQuestions() {
        if (isEmpty(questions)) {
            csvData = CsvUtils.readCsv(pathToCsv);
            parseData();
        }
        return questions;
    }

    @Override
    public List<Answer> getAnswers() {
        if (isEmpty(answers)) {
            csvData = CsvUtils.readCsv(pathToCsv);
            parseData();
        }
        return answers;
    }

    private void parseData() {
        var questionId = 1;
        for (String[] csvLine : csvData) {
            questions.add(DataConverter.convertCsvLineToQuestion(questionId, csvLine));
            answers.addAll(DataConverter.convertCsvLineToAnswers(questionId++, csvLine));
        }
    }
}