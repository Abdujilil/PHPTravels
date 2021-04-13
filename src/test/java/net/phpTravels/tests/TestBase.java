package net.phpTravels.tests;

import net.phpTravels.utilities.BrowserUtils;
import net.phpTravels.utilities.ConfigurationReader;
import net.phpTravels.utilities.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {


    protected Actions actions;
    protected WebDriverWait wait;
    JavascriptExecutor jse;

    @BeforeMethod (alwaysRun = true)
    public void setUp() {

        Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(Driver.get());
        wait = new WebDriverWait(Driver.get(),10);
        jse = (JavascriptExecutor) Driver.get();
        Driver.get().manage().window().maximize();
        Driver.get().get(ConfigurationReader.get("url"));
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            BrowserUtils.getScreenshot(result.getName());
        }
//        BrowserUtils.sleep(1.5);
        Driver.closeDriver();
    }
}