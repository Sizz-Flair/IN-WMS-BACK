package com.wms.inwms.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final MessageSource source;
    private static MessageSource messageSource;

    @PostConstruct
    public void setMessageSource() {
        messageSource = source;
    }

    public String getMessage(String name) {
        Locale locale = Locale.KOREA;
        return source.getMessage(name,null,locale);
    }

    public static String message(String name) {
        Locale locale = Locale.KOREA;
        return messageSource.getMessage(name,null,locale);
    }
}
