package com.virgosol.odeon.hook;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.mongodb.MongoClientURI;
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
    //System.setProperty("webdriver.ie.driver", ConfigurationHelper.INSTANCE.getConfiguration().getDriverPath());
    System.setProperty("webdriver.chrome.driver", ConfigurationHelper.INSTANCE.getConfiguration().getDriverPath());
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
      //ExtentKlovReporter klov = new ExtentKlovReporter();
      ////klov.initMongoDbConnection("135.181.73.110", 21325);
      //klov.initMongoDbConnection(new MongoClientURI("mongodb://vrgadmin:vrgmng21**@135.181.73.110:21325"));
      //klov.setProjectName("Odeon");
      //klov.setReportName("Odeon Test Automation");
      ////klov.setAnalysisStrategy(AnalysisStrategy.TEST);
      //klov.initKlovServerConnection("http://135.181.73.110:8982");
      //extentReports.attachReporter(klov);
    } catch (IOException e) {//mongodb://vrgadmin:vrgmng21**@135.181.73.110
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
