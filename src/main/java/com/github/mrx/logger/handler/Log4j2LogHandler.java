package com.github.mrx.logger.handler;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.lang.reflect.Field;

public class Log4j2LogHandler implements DynamicLogHandler {

    private static final String LOGGER_CLASS_NAME = "org.apache.logging.slf4j.Log4jLogger";

    @Override
    public void handle(Logger logger, Level level) throws Exception {
        Field loggerField = logger.getClass().getDeclaredField("logger");
        loggerField.setAccessible(true);
        org.apache.logging.log4j.core.Logger loggerObj =
                (org.apache.logging.log4j.core.Logger) loggerField.get(logger);
        loggerObj.setLevel(org.apache.logging.log4j.Level.toLevel(level.name()));
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
