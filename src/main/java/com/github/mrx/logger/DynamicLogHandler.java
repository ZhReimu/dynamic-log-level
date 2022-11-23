package com.github.mrx.logger;

import org.slf4j.Logger;
import org.slf4j.event.Level;

public interface DynamicLogHandler {

    void handle(Logger logger, Level level);

    boolean support(Class<?> loggerClazz);
    
}
    