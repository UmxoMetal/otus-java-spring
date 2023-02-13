package ru.otus.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Answer {
    private final int id;
    private final int questionId;
    private final String text;
    private final boolean isCorrect;
}