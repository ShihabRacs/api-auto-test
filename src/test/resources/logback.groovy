import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.ThresholdFilter
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.rolling.RollingFileAppender
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy

import static ch.qos.logback.classic.Level.TRACE
import static ch.qos.logback.classic.Level.WARN

appender("CONSOLE", ConsoleAppender) {
    filter(ThresholdFilter) {
        level = TRACE
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%date %-5level - %X %msg %n "
    }
}
appender("FILE", RollingFileAppender) {
    file = "./log/testing.log"
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "./log/testing-%d{yyyy-MM-dd}.log"
    }
    filter(ThresholdFilter) {
        level = TRACE
    }
    encoder(PatternLayoutEncoder) {
        pattern = "%date %-5level - %X %msg %n "
    }
}
logger("com.test", TRACE, ["FILE"])
root(WARN, ["CONSOLE"])