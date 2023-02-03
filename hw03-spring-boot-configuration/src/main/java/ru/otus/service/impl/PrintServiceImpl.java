package ru.otus.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.service.LocalizationService;
import ru.otus.service.PrintService;

import java.util.Collection;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class PrintServiceImpl implements PrintService {
    private static final String ANSWER_PRINTER_TEMPLATE  = " * %s \n";
    private final LocalizationService localizationService;

    @Override
    public void printQuestion(@NonNull Question question) {
        System.out.printf(localizationService.getLocalizationTextByTag("msg.print.question"), question.getId(), question.getText());
    }

    @Override
    public void printAnswers(Collection<Answer> answers) {
        if (isNotEmpty(answers)) {
            answers.forEach(answer -> System.out.printf(ANSWER_PRINTER_TEMPLATE, answer.getText()));
        }
    }

    @Override
    public void printResult(String result, int correctAnswersQty, String name, String surname) {
        System.out.printf(localizationService.getLocalizationTextByTag("msg.print.answer"), result, name, surname, correctAnswersQty);
    }
}