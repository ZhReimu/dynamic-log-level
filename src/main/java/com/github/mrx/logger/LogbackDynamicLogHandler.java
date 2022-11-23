package com.github.mrx.logger;

import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.lang.reflect.Method;

public class LogbackDynamicLogHandler implements DynamicLogHandler {

    private static Class<?> loggerClazz;

    private static Class<?> levelClazz;

    private static Method setLevel;

    private static final String LOGGER_CLASS_NAME = "ch.qos.logback.classic.Logger";

    private static final String LEVEL_CLASS_NAME = "ch.qos.logback.classic.Level";

    public static final String SET_LEVEL_METHOD_NAME = "setLevel";

    @Override
    public synchronized void handle(Logger logger, Level level) {
        try {
            if (loggerClazz == null) loggerClazz = logger.getClass();
            if (levelClazz == null) levelClazz = Class.forName(LEVEL_CLASS_NAME);
            Object levelName = levelClazz.getField(level.name()).get(null);
            if (setLevel == null) setLevel = loggerClazz.getMethod(SET_LEVEL_METHOD_NAME, levelClazz);
            setLevel.invoke(logger, levelName);
        } catch (Exception e) {
            throw new RuntimeException("设置 Level 出错: " + e.getLocalizedMessage());
        }
    }

    @Override
    public boolean support(Class<?> loggerClazz) {
        return LOGGER_CLASS_NAME.equals(loggerClazz.getName());
    }
}
