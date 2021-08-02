package com.virgosol.example.hook;

import com.aventstack.extentreports.ExtentReports;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import com.virgosol.qa.common.file.FileHelper;
import com.virgosol.qa.report.ReportManager;
import com.virgosol.qa.web.core.helper.ConfigurationHelper;

import java.io.File;
import java.io.IOException;
import javax.mail.MessagingException;

public class HookSpec {

  @BeforeSuite
  public void beforeSpec() {
    System.setProperty("webdriver.ie.driver", ConfigurationHelper.INSTANCE.getConfiguration().getDriverPath());
    ReportManager reportManager = ReportManager.getInstance();
    try {
      ExtentReports extentReports = reportManager.createExtentReport();
//      ExtentKlovReporter klov = new ExtentKlovReporter();
//      klov.initMongoDbConnection("3.17.68.160", 27017);
//      klov.setProjectName("Virgosol");
//      klov.setReportName("Virgosol Test Automation");
//      klov.setAnalysisStrategy(AnalysisStrategy.TEST);
//      klov.initKlovServerConnection("http://3.17.68.160:8081");
//      extentReports.attachReporter(klov);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @AfterSuite
  public void afterSpec() {
    try {
      FileHelper.copyFile(new File("target/slnarch.log"),
          new File(ReportManager.getInstance().getReportDirectory() + "/executor.log"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      ReportManager.getInstance().saveReport();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
