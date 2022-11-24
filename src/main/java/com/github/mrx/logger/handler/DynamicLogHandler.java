package com.github.mrx.logger.handler;

import org.slf4j.Logger;
import org.slf4j.event.Level;

public interface DynamicLogHandler {

    default void setLevel(Logger logger, Level level) {
        try {
            handle(logger, level);
        } catch (Exception e) {
            throw new RuntimeException("设置 Level 出错: " + e.getLocalizedMessage());
        }
    }

    void handle(Logger logger, Level level) throws Exception;

    boolean support(Logger logger);

    default boolean loggerExists() {
        try {
            Class.forName(loggerClass());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    String loggerClass();

}
    