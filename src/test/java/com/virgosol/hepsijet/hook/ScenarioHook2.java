package com.virgosol.hepsijet.hook;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterStep;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeStep;
import com.thoughtworks.gauge.ExecutionContext;
import com.virgosol.qa.report.ReportManager;
import com.virgosol.qa.web.core.di.Injectable;
import com.virgosol.qa.web.core.driver.DriverAction;
import com.virgosol.qa.web.gauge.hook.ScenarioHook;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import javax.inject.Inject;

public class ScenarioHook2 implements Injectable {

    @Inject
    DriverAction driverAction;

    @Inject
    WebDriver driver;

    private Logger logger = LoggerFactory.getLogger(ScenarioHook2.class);

    @BeforeScenario
    public void beforeScenario2(ExecutionContext executionContext) throws Exception{
        inject();
        logger.debug("Before Scenario");
        ReportManager reportManager = ReportManager.getInstance();
        reportManager.createNewExtentTest(executionContext.getCurrentScenario().getName());
        reportManager.getExtentTest()
                .info(String.format("Starting Test=%s", executionContext.getCurrentScenario().getName()));
        reportManager.setAuthor("Virgosol");
        reportManager.setCategory("Test");
        executionContext.getCurrentScenario();
        //Dimension expectedWindowSize = new Dimension(1920, 1080);
        //getDriver().manage().window().setSize(expectedWindowSize);
        //getCapabilities();
        //driver.navigate().to(ConfigurationHelper.INSTANCE.getBaseUrl());
        driver.manage().window().maximize();
        //getDriver().manage().window().setSize(new Dimension(375, 812)); // IPHONE X
        //getDriver().navigate().refresh();
        Thread.sleep(1500);
    }

    @AfterScenario
    public void afterScenario(ExecutionContext executionContext) {
        if (executionContext.getCurrentScenario().getIsFailing()) {
            logger.error(executionContext.getCurrentStep().getStackTrace());
        }
        logger.debug("After Scenario");
    }

    @BeforeStep
    public void testStepBefore(ExecutionContext executionContext) {
        logger.info(executionContext.getCurrentStep().getDynamicText() + " adimi basladi");
    }

    @AfterStep
    public void testStep(ExecutionContext executionContext) {
        if (executionContext.getCurrentScenario().getIsFailing()) {
            logger.info(executionContext.getCurrentStep().getDynamicText() + " adimi basarisiz olarak bitti");
            logger.error(executionContext.getCurrentStep().getStackTrace());
            ReportManager.getInstance()
                    .getExtentTest()
                    .fail(
                            executionContext.getCurrentStep().getDynamicText() + " adimi basarisiz olarak bitti",
                            ReportManager.getInstance()
                                    .createMediaEntity(driverAction.takeScreenShotAndCompress()));
            ReportManager.getInstance()
                    .getExtentTest()
                    .fail(executionContext.getCurrentStep().getStackTrace());
        } else {
            logger.info(executionContext.getCurrentStep().getDynamicText() + " adimi basariyla bitti");

            if (!executionContext.getCurrentStep().getText().contains("saniye bekle")) {
                File file = driverAction.takeScreenShotAndCompress();
                if (file != null) {
                    ReportManager.getInstance()
                            .getExtentTest()
                            .pass(executionContext.getCurrentStep().getDynamicText() + " adimi basariyla bitti",
                                    ReportManager.getInstance()
                                            .createMediaEntity(file));
                }
            }
            /*
            if (!executionContext.getCurrentStep().getText().contains("saniye bekle")) {
                ReportManager.getInstance()
                        .getExtentTest()
                        .pass(executionContext.getCurrentStep().getDynamicText() + " adimi basariyla bitti");
            }
             */
        }
    }

  /*public Capabilities getCapabilities() {
      final DesiredCapabilities dc = DesiredCapabilities.chrome();
      dc.setCapability(ChromeOptions.CAPABILITY, new ChromeOptions() {
          {
              setExperimentalOption("mobileEmulation", new HashMap<String, Object>() {
                  {
                      put("deviceName", "iPhone X");
                  }
              });
          }
      });
    return dc;
  }*/

   /*
  @Override
  public Capabilities getCapabilities() {
    InternetExplorerOptions options=new InternetExplorerOptions();
    options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
    options.ignoreZoomSettings();
    options.destructivelyEnsureCleanSession();
    //options.disableNativeEvents();
    //options.requireWindowFocus();
    options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,true);
    options.introduceFlakinessByIgnoringSecurityDomains();
    options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
    return options;
  }
   */
}
