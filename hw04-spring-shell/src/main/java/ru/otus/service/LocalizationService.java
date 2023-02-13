package ru.otus.service;

import org.springframework.lang.NonNull;

import java.util.List;

public interface LocalizationService {
    String getLocalizationTextByTag(@NonNull String tag);

    String getLocalizationTextByTag(@NonNull String tag, List<String> args);
}
