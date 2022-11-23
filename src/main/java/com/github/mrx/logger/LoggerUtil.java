package com.github.mrx.logger;

import com.github.mrx.logger.handler.DynamicLogHandler;
import com.github.mrx.logger.handler.Log4j2LogHandler;
import com.github.mrx.logger.handler.LogbackLogHandler;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LoggerUtil {

    private static final Set<DynamicLogHandler> handlers = new HashSet<>();

    private static final Set<PackageScanner> scanners = new HashSet<>();

    static {
        handlers.add(new LogbackLogHandler());
        handlers.add(new Log4j2LogHandler());
        // 默认使用 Reflections 做包扫描
        scanners.add(new ReflectionsPackageScanner());
    }

    /**
     * 防止被实例化
     */
    private LoggerUtil() {
    }

    /**
     * 使用 PackageScanner 扫描指定包下的 DynamicLogHandler
     */
    public static void scanPackages(String packages) {
        for (PackageScanner scanner : scanners) handlers.addAll(scanner.scanPackage(packages));
    }

    /**
     * 添加 DynamicLogHandler 用以处理 动态日志等级
     */
    public static void addHandler(DynamicLogHandler... newHandlers) {
        Collections.addAll(handlers, newHandlers);
    }

    /**
     * 添加 PackageScanner 用以扫描指定包下的 DynamicLogHandler
     */
    public static void addScanner(PackageScanner... scanners) {
        Collections.addAll(LoggerUtil.scanners, scanners);
    }

    /**
     * 设置指定 logger 的 日志等级
     */
    public static void setLevel(Logger logger, Level level) {
        handlers.stream().filter(it -> it.support(logger.getClass())).findFirst().ifPresent(it -> it.setLevel(logger, level));
    }

}
