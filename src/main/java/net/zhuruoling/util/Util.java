package net.zhuruoling.util;

import net.zhuruoling.logging.MemoryAppender;
import org.apache.logging.log4j.LogManager;

public class Util {
    public static void addAppender() {
        var rootLogger = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();
        var config = rootLogger.get();
        var appender = MemoryAppender.newAppender("OMMSMemoryLogger");
        appender.start();
        config.addAppender(appender, null, null);
        rootLogger.addAppender(appender);
        config.start();
    }
}
