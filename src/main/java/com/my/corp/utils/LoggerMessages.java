package com.my.corp.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.layout.internal.ExcludeChecker;

import java.util.Arrays;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoggerMessages {
    private static final ResourceBundle messages = ResourceBundle.getBundle("logMessages");

    public static String getMessage(String property, String... args) {
        String message;
        try {
            message = messages.getString(property);
        } catch (MissingResourceException e) {
            return "Can't find appropriate message resource for property " + property + " in the ResourceBundle";
        }
        if (args.length > 0) {
            return String.format(message, args);
        } else {
            return message;
        }
    }

    public static String getMessage(Exception e) {
        return e.getMessage()
                + Arrays.stream(e.getStackTrace()).map(StackTraceElement::toString)
                .collect(Collectors.joining("\n\t"));

    }
}
