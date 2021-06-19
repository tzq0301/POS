import ch.qos.logback.classic.encoder.PatternLayoutEncoder

import static ch.qos.logback.classic.Level.INFO

appender("file", FileAppender) {
    file = "payment/payment.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%date %level [%thread] %logger{10} [%file:%line] %msg%n"
    }
}

root(INFO, ["file"])