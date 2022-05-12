package dev.springallergies.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

///Contains Specific log methods tied to a particular LogLevel
///Also contains an optional log option that allows you to specify the log level
@Component
@Aspect
public class Logger {

    static int requestCounter = 0;

    public static void logMessage(String output) {
        log(LogLevel.message, output);
    }

    public static void logDebug(String output) {
        log(LogLevel.debug, output);
    }

    public static void logWarning(String output) {
        log(LogLevel.warning, output);
    }

    public static void logError(String output) {
        log(LogLevel.error, output);
    }

    public static void logFatal(String output) {
        log(LogLevel.fatal, output);
    }

    ///Core of the Logger class
    ///publicly accessible for when logLevel is variable
    public static void log(LogLevel logLevel, String output) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(System.currentTimeMillis());
        output = String.format("[%s %s]: %s\n", logLevel.name().toUpperCase(Locale.ROOT), sdf.format(calendar.getTime()), output);
        System.out.println(output);

        try {
            Path logLocation = Paths.get(System.getProperty("user.dir"), "Log.log");
            if (!Files.exists(logLocation)) Files.createFile(logLocation);
            Files.write(logLocation, output.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.err.println("Failed to write to Log");
        }
    }


    @Before("logJP()")// connect my advice to the point cut express
    public static void logCounter(){
        requestCounter++;
        logDebug("The total amount of requests is : " + requestCounter);
    }

    @After("logJP()")
    public static void finished(){
        logDebug("Request has finished processing");
    }

    @Pointcut("within(dev.springallergies.controllers..*)")
    private void logJp(){};
}
