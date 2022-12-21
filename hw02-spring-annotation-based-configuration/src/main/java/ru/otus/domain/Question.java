package ru.otus.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private final int id;
    private final String text;
}