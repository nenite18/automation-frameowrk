import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import src.client.HttpClient;
import src.config.Constant;
import src.config.HttpStatusCode;
import src.logger.LoggerUtil;
import src.util.Helper;
import src.util.JsonUtil;

import java.util.Properties;

public class BaseTest extends BaseConfig {

    public static JsonUtil jsonUtil;
    public static Helper helper;
    public static HttpClient httpClient;
    public static Constant constant;
    public static ExtentReports extentReport;
    public static ExtentHtmlReporter extentHtmlReporter;


    public static HttpStatusCode HttpStatusCode;

    private static LoggerUtil logger = LoggerUtil.getLogger(BaseTest.class);

    @BeforeSuite(alwaysRun = true)
    public void setUpSuite() {
        Properties runtimeProperties = BaseConfig.runtimeProperties;
    }

    @BeforeTest
    public void setUpTest() {
        jsonUtil = new JsonUtil();
        helper = new Helper();
        httpClient = new HttpClient();
        extentHtmlReporter = new ExtentHtmlReporter("./ExtentReport.html");
        extentReport = new ExtentReports();
        extentReport.attachReporter(extentHtmlReporter);

    }

    @AfterTest
    public void tearDownTest() {
        logger.logInfo("Test execution completed");
    }

    @AfterSuite
    public void tearDownSuite() {
        extentReport.flush();
        logger.logInfo("Suite execution completed");
    }


}
