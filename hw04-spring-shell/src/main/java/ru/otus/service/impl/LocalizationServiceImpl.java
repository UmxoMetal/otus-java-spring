package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.otus.service.LocalizationService;

import java.util.List;
import java.util.Locale;

import static java.util.Collections.emptyList;

@Service
public class LocalizationServiceImpl implements LocalizationService {
    private final MessageSource messageSource;
    private final Locale currentLocal;

    public LocalizationServiceImpl(MessageSource messageSource, @Value("${test.current-locale:ru}") Locale currentLocal) {
        this.messageSource = messageSource;
        this.currentLocal = currentLocal;
    }

    @Override
    public String getLocalizationTextByTag(@NonNull String tag) {
        return getLocalizationTextByTag(tag, emptyList());
    }

    @Override
    public String getLocalizationTextByTag(@NonNull String tag, List<String> args) {
        return messageSource.getMessage(tag, args.toArray(), currentLocal);
    }
}