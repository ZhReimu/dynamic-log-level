package com.github.mrx.logger;

import com.github.mrx.logger.handler.DynamicLogHandler;

import java.util.Set;

public interface PackageScanner {

    Set<? extends DynamicLogHandler> scanPackage(String packageName);

    default <T extends DynamicLogHandler> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("创建 DynamicLogHandler 实例失败, 请确保 DynamicLogHandler 拥有一个无参构造方法");
        }
    }

}
