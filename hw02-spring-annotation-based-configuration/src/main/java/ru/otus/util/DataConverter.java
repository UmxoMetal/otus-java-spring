package ru.otus.util;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static java.lang.Integer.parseInt;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class DataConverter {
    public static Question convertCsvLineToQuestion(int questionId, String[] csvLine) {
        return Question.builder()
                .id(questionId)
                .text(csvLine[0])
                .build();
    }

    public static List<Answer> convertCsvLineToAnswers(int questionId, String[] csvLine) {
        var answers = new ArrayList<Answer>();
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
        return answers;
    }
}