import src.logger.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseConfig {
    protected static Properties runtimeProperties;
    public static final LoggerUtil logger = LoggerUtil.getLogger(BaseConfig.class);

    public BaseConfig() {
        runtimeProperties = new Properties();
    }

    public static void loadProperties() {
        try (InputStream input = BaseConfig.class.getClassLoader().getResourceAsStream("runtime.properties")) {
            runtimeProperties.load(input);
        } catch (IOException ex) {
            logger.logInfo("Could not load properties file: " + ex);
            throw new RuntimeException("Could not load properties file: " + ex);
        }
    }


}
