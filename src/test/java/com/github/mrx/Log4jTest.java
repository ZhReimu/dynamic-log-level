package com.github.mrx;

import com.github.mrx.logger.LoggerUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * @author Mr.X
 * @since 2022-11-23 21:11
 */
public class Log4jTest {

    private static final Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    @Test
    public void test() {
        LoggerUtil.setLevel(logger, Level.ERROR);
        logger.trace("test");
        logger.debug("test");
        logger.warn("test");
        logger.error("test");
    }

}
