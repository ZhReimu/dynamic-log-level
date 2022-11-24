package com.github.mrx.logger.handler;

import org.slf4j.Logger;
import org.slf4j.event.Level;

public class LogbackLogHandler implements DynamicLogHandler {

    @Override
    public synchronized void handle(Logger logger, Level level) {
        ((ch.qos.logback.classic.Logger) logger)
                .setLevel(ch.qos.logback.classic.Level.toLevel(level.name()));
    }

    @Override
    public boolean support(Logger logger) {
        return logger instanceof ch.qos.logback.classic.Logger;
    }

    @Override
    public String loggerClass() {
        return "ch.qos.logback.classic.Logger";
    }

}
