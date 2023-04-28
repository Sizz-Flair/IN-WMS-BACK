package com.wms.inwms.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final MessageSource source;

    public String getMessage(String name) {
        Locale locale = Locale.KOREA;
        return source.getMessage(name,null,locale);
    }
}
