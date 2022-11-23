package com.github.mrx.logger;

import com.github.mrx.logger.handler.DynamicLogHandler;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class ReflectionsPackageScanner implements PackageScanner {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionsPackageScanner.class);

    @Override
    public Set<? extends DynamicLogHandler> scanPackage(String packageName) {
        try {
            return new Reflections(packageName).getSubTypesOf(DynamicLogHandler.class).stream().map(this::newInstance).collect(Collectors.toSet());
        } catch (Throwable t) {
            String msg = t.getLocalizedMessage();
            if (t instanceof NoClassDefFoundError) msg = "找不到 Reflections 库相关类, 请检查依赖!";
            logger.warn("包扫描出错: {}", msg);
        }
        return Collections.emptySet();
    }

}
