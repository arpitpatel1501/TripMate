package grp16.tripmate.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Adapter class - object scope adapter
public class MyLogger implements ILogger {

    Logger logger;

    // Do not implement default constructor as it requires a classobj, thus making it private
    private MyLogger() {
    }

    public MyLogger(Object classObj) {
        logger = LogManager.getLogger(classObj);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

}
