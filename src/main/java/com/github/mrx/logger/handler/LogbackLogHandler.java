package com.github.mrx.logger.handler;

import org.slf4j.Logger;
import org.slf4j.event.Level;

public class LogbackLogHandler implements DynamicLogHandler {

    private static final String LOGGER_CLASS_NAME = "ch.qos.logback.classic.Logger";

    @Override
    public synchronized void handle(Logger logger, Level level) {
        if (loggerExists()) {
            ((ch.qos.logback.classic.Logger) logger)
                    .setLevel(ch.qos.logback.classic.Level.toLevel(level.name()));
        }
    }

    @Override
    public boolean support(Class<?> loggerClazz) {
        return LOGGER_CLASS_NAME.equals(loggerClazz.getName());
    }

    @Override
    public String loggerClass() {
        return LOGGER_CLASS_NAME;
    }

}
