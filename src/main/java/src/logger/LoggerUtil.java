package src.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging messages.
 */

public class LoggerUtil {
    private static Logger logger;

    private LoggerUtil(Class className) {
        logger = LogManager.getLogger(className);
    }

    public static LoggerUtil getLogger(Class className) {
        return new LoggerUtil(className);
    }


    /**
     * Logs an informational message.
     *
     * @param message The message to be logged.
     */
    public  void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to be logged.
     */
    public  void logError(String message) {
        logger.error(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to be logged.
     */
    public void logWarn(String message) {
        logger.warn(message);
    }

    /**
     * Logs a debug message.
     *
     * @param message The message to be logged.
     */
    public  void logDebug(String message) {
        logger.debug(message);
    }
}