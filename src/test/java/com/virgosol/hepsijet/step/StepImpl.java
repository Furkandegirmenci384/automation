package com.virgosol.odeon.step;

import com.thoughtworks.gauge.Step;
import com.virgosol.hepsijet.helper.ElementHelper;
import com.virgosol.hepsijet.config.ExcelHelper;
import com.virgosol.hepsijet.helper.StoreHelper;
import com.virgosol.hepsijet.model.ElementInfo;
import com.virgosol.qa.web.core.di.InjectionHelper;
import com.virgosol.qa.web.core.element.Element;
import com.virgosol.qa.web.core.model.Configuration;
import com.virgosol.qa.web.core.wait.WaitingAction;
import jxl.read.biff.BiffException;
import org.apache.commons.lang.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class StepImpl {
    String winHandleBefore;
    private final Logger logger = LoggerFactory.getLogger(StepImpl.class);
    @Inject
    Element element;
    @Inject
    WebDriver driver;
    @Inject
    WaitingAction waitingAction;
    @Inject
    Configuration configuration;

    public StepImpl() {
        InjectionHelper.getInstance().getFeather().injectFields(this);
    }


    @Step("<url> urle git")
    public void goToUrl(String url) {
        driver.navigate().to(url);
    }

    @Step("Mesajda tamam butonuna basılır.")
    public void popUpAccept() throws IOException {
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
    }

    @Step("<key> li elementin uzunluğunun 10 olduğu doğrulanır.")
        public void numberOfDataInDatagrid(String key) throws IOException {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        int elementSize = elements.size();
        if(elementSize <= 10)
            System.out.println("Elementin uzunluğu...:" + elementSize + "dur." );
        else
            Assert.fail("Element uzunluğu 10'dan büyüktür.");
    }

    @Step("<key> textbox alanına captcha değeri yazılır.")
    public void handlingCAPTCHA( String key) throws IOException {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement fileInput = driver.findElement(byElement);
        String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
        fileInput.sendKeys(captchaVal);
    }

    @Step("<key> li element gözüküyor mu?")
    public void elementIsDisplayed(String key) {
        By byElement2 = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement2));
        WebElement element = driver.findElement(byElement2);
        Boolean result = element.isDisplayed();
        System.out.println(result);
        if(result!=true){
            Assert.fail("Element sayfada görünür değil.");
        }
    }

    @Step("Ekran çözünürlüğünü <screenResolution> yap.")
    public void changeScreenResolution(String screenResolution) throws IOException {

        if (screenResolution.contains("1920x1080")) {
            driver.manage().window().setSize(new Dimension(1920, 1080));
        }
        else if (screenResolution.contains("1280x768")) {
            driver.manage().window().setSize(new Dimension(1280, 768));
        }
        else if (screenResolution.contains("1680x1050")) {
            driver.manage().window().setSize(new Dimension(1680, 1050));
        }
        else if (screenResolution.contains("1360x768")) {
             driver.manage().window().setSize(new Dimension(1360, 768));
        }
        else if (screenResolution.contains("1024x768")) {
             driver.manage().window().setSize(new Dimension(1024, 768));
        }
        else if (screenResolution.contains("800x600")) {
             driver.manage().window().setSize(new Dimension(800, 600));
        }
    }

    @Step("<key1> tarihi ile <key2> tarihini karşılaştır.")
    public void dateCompare(String key1, String key2) throws IOException {

        By byElement = ElementHelper.getElementInfoToBy(key1);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        String date1 = element.getAttribute("value");
        System.out.println(date1);

        By byElement2 = ElementHelper.getElementInfoToBy(key2);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement2));
        WebElement element2 = driver.findElement(byElement2);
        String date2 = element2.getAttribute("value");
        System.out.println(date2);

        if (date1.compareTo(date2) < 0) {
            System.out.println("Cancellation Date, Cancellation Start Date' den önce gelmektedir.");
        } else if (date1.compareTo(date2) == 0 || date1.compareTo(date2) > 0) {
            Assert.fail("Cancellation Date, Cancellation Start Date' den önce gelmemektedir.");
        }
    }

    @Step("<browserType> tarayıcını aç")
    public void openBrowser(String browserName) throws IOException {

        if (browserName.contains("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            this.driver = driver;
        }else if (browserName.contains("Microsoft Edge")) {
            System.setProperty("webdriver.edge.driver", "drivers/msedgedriver");
            EdgeDriver driver = new EdgeDriver();
            driver.manage().window().maximize();
            this.driver = driver;
        }
    }

    @Step("Url <screenResolution> tarayıcıda açılır.")
    public void openInBrowser(String browserName) throws IOException {

        if (browserName.contains("Firefox")) {
            System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
            WebDriver driver = new FirefoxDriver();
            driver.manage().window().maximize();
            String url = "http://uatobbackoffice.odeontours.com/reservationdetail/H3IQNUNDB";
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
            WebElement usernameSpace = driver.findElement(By.id("basic_username"));
            usernameSpace.click();
            usernameSpace.sendKeys("OdeonbedsAdmin");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
            WebElement passwordSpace = driver.findElement(By.id("basic_password"));
            passwordSpace.click();
            passwordSpace.sendKeys("De53Acy2..");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
            WebElement submitButton = driver.findElement(By.cssSelector("[class=\"ant-btn ant-btn-primary\"]"));
            submitButton.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
            Assert.assertEquals("http://uatobbackoffice.odeontours.com/reservationdetail/H3IQNUNDB",driver.getCurrentUrl());
        }

        else if (browserName.contains("Microsoft Edge")) {
            System.setProperty("webdriver.edge.driver", "drivers/msedgedriver");
            EdgeDriver driver = new EdgeDriver();
            driver.manage().window().maximize();
            String url = "http://uatobbackoffice.odeontours.com/reservationdetail/H3IQNUNDB";
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
            WebElement usernameSpace = driver.findElement(By.id("basic_username"));
            usernameSpace.click();
            usernameSpace.sendKeys("OdeonbedsAdmin");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
            WebElement passwordSpace = driver.findElement(By.id("basic_password"));
            passwordSpace.click();
            passwordSpace.sendKeys("De53Acy2..");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
            WebElement submitButton = driver.findElement(By.cssSelector("[class=\"ant-btn ant-btn-primary\"]"));
            submitButton.click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
            Assert.assertEquals("http://uatobbackoffice.odeontours.com/reservationdetail/H3IQNUNDB",driver.getCurrentUrl());
        }
    }

    @Step({"Click element with key <key>",
            "<key> li elemente tikla"})
    public void clickElement(String key) throws InterruptedException {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        swipeToElement(driver.findElement(byElement));
        //waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(byElement));
        driver.findElement(byElement).click();
    }

    @Step({"<key> li elemente tikla ve <file> dosya secimini yap"})
    public void findFile(String key, String filePath) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement fileInput = driver.findElement(byElement);
        //JavascriptExecutor executor = (JavascriptExecutor)driver;
        //executor.executeScript("arguments[0].click();", fileInput);
        fileInput.click();
        File file = new File(filePath);
        StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
        //Copy to clipboard
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Cmd + Tab is needed since it launches a Java app and the browser looses focus
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_TAB);
        robot.delay(500);

        //Open Goto window
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_G);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_G);

        //Paste the clipboard value
        robot.keyPress(KeyEvent.VK_META);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_META);
        robot.keyRelease(KeyEvent.VK_V);

        //Press Enter key to close the Goto window and Upload window
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


    @Step("<key> sn bekle")
    public void waitSeconds(String key) throws InterruptedException {
        Thread.sleep(Integer.parseInt(key) * 1000);
        //driver.wait(Integer.parseInt(key) * 1000);
    }

    @Step("<key> dakika bekle")
    public void waitMinutes(String key) throws InterruptedException {
        Thread.sleep(Integer.parseInt(key) * 1000 * 60);
        //driver.sleep(Integer.parseInt(key) * 1000 * 60);
        //driver.wait(300000);
    }

    @Step("<key> li elementi görünür olana kadar bekle")
    public void waitVisible(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElement));
        } catch (TimeoutException t) {
            return;
        }
    }

    @Step({"Send keys to element with key <key> and text <text>",
            "<key> li elemente <text> degerini yaz"})
    public void sendKey(String key, String text) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        element.clear();
        if (text.startsWith("Deger_")) {
            element.sendKeys(StoreHelper.getValue(text));
        } else {
            element.sendKeys(text);
        }
    }

    @Step({"Wait <key> element",
            "<key> li elementi bekle"})
    public void waitElement(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        } catch (TimeoutException t) {
            return;
        }
    }

    @Step("<key> li elementi invisible olana kadar bekle")
    public void waitInVisible(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.invisibilityOfElementLocated(byElement));
        } catch (TimeoutException t) {
            return;
        }
    }

    @Step({"<key> li elementin üzerine gel"})
    public void hoverElement(String key) {
        Actions action = new Actions(driver);
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.visibilityOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        action.moveToElement(element).perform();
    }

    @Step({"<key> li elementin <attr> attributesi <attrtext> mi"})
    public void findElementWithTextAndCheckAttributeText(String key, String attr, String attrtext) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.attributeToBe(element, attr, attrtext));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementin <attr> attributesini <text> yap"})
    public void setAttribute(String key, String attr, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attr, text);
    }

    @Step({"<key> li elementin textini <key2> olarak kaydet"})
    public void saveText(String key, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        StoreHelper.saveValue(key2, element.getText());
    }

    @Step({"<key> li elementin tarih bilgisi kontrol edilir."})
    public void dateControl(String key1) {

      By byElement = ElementHelper.getElementInfoToBy(key1);
      waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
      WebElement element = driver.findElement(byElement);
      String updatedDate = element.getText();
      System.out.println(updatedDate);

      LocalDateTime dateTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH");
      String date = dateTime.format(formatter);
      System.out.println(dateTime.format(formatter));

      if(updatedDate.contains(date)){
              System.out.println("Tarihler eşit.");
      }
      else
          Assert.fail("Tarihler eşit değil.");
    }


    @Step({"<key> li elementin <attr> attributesini <key2> olarak kaydet"})
    public void saveAttribute(String key, String attr, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
        StoreHelper.saveValue(key2, element.getAttribute(attr));
    }

    @Step({"<key> li elementlerden <text> değerine eşit olana tıkla"})
    public void findElementWithTextsAndClick(String key, String text) {
        String elementText = null;
        boolean flag=true;
        int count=1;
        while(flag){
            try {
                By byElement = ElementHelper.getElementInfoToBy(key);
                waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
                List<WebElement> elements = driver.findElements(byElement);
                for (WebElement element : elements) {
                    swipeToElement(element);
                    if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                        if (!element.isDisplayed())
                            waitingAction.waitUntil(ExpectedConditions.visibilityOfElementLocated(byElement));
                        elementText = element.getText();
                        //Thread.sleep(1);
                        element.click();
                        break;
                    }
                }
                flag=false;
                break;
            }

            catch(StaleElementReferenceException e) {
                count=count+1;
                scrollDown();
                if(text.equals(elementText))
                {
                    break;
                }
            }
        }
    }

    @Step({"<key> li elementlerden <pin> pin değerine tıkla"})
    public void findElementPinWithTextsAndClick(String key, String pin) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        int k = 1;
        for (int i = 0; i < pin.length(); i++) {
            String pinNo = pin.substring(i, k++);
            for (WebElement element : elements) {
                if ((pinNo.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(pinNo))) || element.getText().contains(pinNo)) {
                    waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(element));
                    element.click();
                    //break;
                }
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olana <text2> değerini yaz"})
    public void findElementWithTextsAndSendKey(String key, String text, String text2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                waitingAction.waitUntil(ExpectedConditions.visibilityOf(element));
                element.clear();
                if (text2.startsWith("Deger_")) {
                    element.sendKeys(StoreHelper.getValue(text2));
                } else {
                    element.sendKeys(text2);
                }
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanı seç"})
    public void findElementWithTextsAndSelect(String key, String text) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        Select list = new Select(element);
        if (text.startsWith("Deger_")) {
            list.selectByVisibleText(StoreHelper.getValue(text));
        } else {
            list.selectByVisibleText(text);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın üzerine gel"})
    public void findElementWithTextsAndHover(String key, String text) {
        Actions action = new Actions(driver);
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                waitingAction.waitUntil(ExpectedConditions.visibilityOf(element));
                action.moveToElement(element).perform();
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın <attr> attributesini <text2> yap"})
    public void findElementWithTextsAndSetAttribute(String key, String text, String attr, String text2) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attr, text2);
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın textini <key2> olarak kaydet"})
    public void findElementWithTextsAndSaveText(String key, String text, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                StoreHelper.saveValue(key2, element.getText());
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın <attr> attributesini <key2> olarak kaydet"})
    public void findElementWithTextsAndSaveAttribute(String key, String text, String attr, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
                StoreHelper.saveValue(key2, element.getAttribute(attr));
                break;
            }
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementine tikla"})
    public void clickElementNextTo(String key, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(elementNextTo));
        elementNextTo.click();
    }

    @Step({"<key> li elementin yanındaki <key2> elementine <text> degerini yaz"})
    public void sendKeyNextTo(String key, String key2, String text) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
        elementNextTo.clear();
        if (text.startsWith("Deger_")) {
            elementNextTo.sendKeys(StoreHelper.getValue(text));
        } else {
            elementNextTo.sendKeys(text);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin üzerine gel"})
    public void hoverElementNextTo(String key, String key2) {
        Actions action = new Actions(driver);
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
        action.moveToElement(elementNextTo).perform();
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin <attr> attributesini <text> yap"})
    public void setAttributeNextTo(String key, String key2, String attr, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        if (text.startsWith("Deger_")) {
            js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", elementNextTo, attr, StoreHelper.getValue(text));
        } else {
            js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", elementNextTo, attr, text);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin textini <key3> olarak kaydet"})
    public void saveTextNextTo(String key, String key2, String key3) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        StoreHelper.saveValue(key3, elementNextTo.getText());
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin <attr> attributesini <key3> olarak kaydet"})
    public void saveAttributeNextTo(String key, String key2, String attr, String key3) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(elementNextTo, attr));
        StoreHelper.saveValue(key3, elementNextTo.getAttribute(attr));
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementine tıkla"})
    public void findElementWithTextsAndClickNextTo(String key, String text, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                WebElement elementsNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                //waitingAction.waitUntil(ExpectedConditions.elementToBeClickable((elementNextTo)));
                elementsNextTo.click();
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementlerinden indexe göre tıkla"})
    public void findElementWithTextsAndClickNextToWithIndex(String key, String text, String key2) {
        int count = 0;
        int pager = 1;
        int pageSize = 0;
        boolean check = false;
        do {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            int size = elements.size();
            for (int i = 0; i < size; i++) {
                swipeToElement(elements.get(i));
                if ((text.startsWith("Deger_") && elements.get(i).getText().contains(StoreHelper.getValue(text))) || elements.get(i).getText().equals(text)) {
                    check = true;
                    List<WebElement> elementsNextTo = elements.get(i).findElements(ElementHelper.getElementInfoToBy(key2));
                    //waitingAction.waitUntil(ExpectedConditions.elementToBeClickable((elementNextTo)));
                    elementsNextTo.get(i).click();
                    return;
                } else if (i == size - 1 && check == false) {
                    if (pager == elements.size())
                        break;
                    if (pager < elements.size()) {
                        By byElement2 = ElementHelper.getElementInfoToBy("txt_pager");
                        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement2));
                        List<WebElement> elementsPage = driver.findElements(byElement2);
                        pageSize = elementsPage.size();
                        elementsPage.get(pager).click();
                    }
                    pager++;
                }
            }
        } while (count < pageSize);
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementlerinden <text2> değerine eşit olana tıkla"})
    public void findElementWithTextsAndClickWithTextNextTo(String key, String text, String key2, String text2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            swipeToElement(element);
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                List<WebElement> elementsNextTo = element.findElements(ElementHelper.getElementInfoToBy(key2));
                for (WebElement lastElement : elementsNextTo) {
                    if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text2))) || lastElement.getText().equals(text2)) {
                        waitingAction.waitUntil(ExpectedConditions.elementToBeClickable((lastElement)));
                        JavascriptExecutor js = (JavascriptExecutor)driver;
                        js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", lastElement);
                        lastElement.click();
                        //js.executeScript("arguments[0].click();", lastElement);
                        break;
                    }
                }
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementine <text2> değerini yaz"})
    public void findElementWithTextsAndSendKeyNextTo(String key, String text, String key2, String text2) throws InterruptedException {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            //System.out.println(element.getText());
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                //System.out.println(element.getText() + "gİrdİ");
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                //waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
                //elementNextTo.clear();
                if (text2.startsWith("Deger_") && elementNextTo.getText().contains(StoreHelper.getValue(text2))) {
                    elementNextTo.sendKeys(StoreHelper.getValue(text2));
                } else {
                    //elementNextTo.click();
                    //Actions action = new Actions(driver);
                    Thread.sleep(1);
                    //action.click(elementNextTo).perform();
                    ////action.moveToElement(elementNextTo,0,0).sendKeys(text2);
                    //action.moveByOffset(0,0).sendKeys(text2).perform();
                    JavascriptExecutor js = ((JavascriptExecutor)driver);
                    js.executeScript("arguments[0].setAttribute('style', 'border: 2px solid red;');", elementNextTo);
                    js.executeScript("arguments[0].value='"+text2+"';", elementNextTo);
                    //elementNextTo.sendKeys(text2);
                }
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan ile aynı sıralamada yanındaki <key2> elementine <text2> değerini yaz"})
    public void findElementWithTextsAndSendKeyAllignment(String key, String text, String key2, String text2) {
        int a = 0;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                WebElement elementNextTo = element.findElements(ElementHelper.getElementInfoToBy(key2)).get(a);
                waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
                elementNextTo.clear();
                if (text2.startsWith("Deger_")) {
                    elementNextTo.sendKeys(StoreHelper.getValue(text2));
                } else {
                    elementNextTo.sendKeys(text2);
                }
                break;
            }
            a++;
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan ile aynı sıralamada yanındaki <key2> elementine tıkla"})
    public void findElementWithTextsAndClickAllignment(String key, String text, String key2) {
        int a = 0;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                WebElement elementNextTo = element.findElements(ElementHelper.getElementInfoToBy(key2)).get(a);
                waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(elementNextTo));
                elementNextTo.click();
                break;
            }
            a++;
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan ile aynı sıralamada altındaki <key2> verileri <text2> olarak sıralı mı"})
    public void findElementWithTextsAndOrderAllignment(String key, String text, String key2, String text2) {
        int a = 0;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                List<WebElement> elementNextTo = element.findElements(ElementHelper.getElementInfoToBy(key2));
                for (int i = a + 1; i < elementNextTo.size(); i = i + 7) {
                    if (text2 == "BK") {
                        if (elementNextTo.get(i).getText().toLowerCase().compareTo(elementNextTo.get(i + 7).getText().toLowerCase()) < 0) {
                            Assert.assertTrue(false);
                            break;
                        }
                    }
                    if (text2 == "KB") {
                        if (elementNextTo.get(i).getText().toLowerCase().compareTo(elementNextTo.get(i + 7).getText().toLowerCase()) > 0) {
                            Assert.assertTrue(false);
                            break;
                        }
                    }
                    if (text2 == "eşit") {
                        if (elementNextTo.get(i).getText().toLowerCase().compareTo(elementNextTo.get(i + 7).getText().toLowerCase()) != 0) {
                            Assert.assertTrue(false);
                            break;
                        }
                    } else {
                        break;
                    }
                }
                break;
            }
            a++;
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementini seç"})
    public void findElementWithTextsAndSelectNextTo(String key, String text, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        Select list = new Select(elementNextTo);
        if (text.startsWith("Deger_")) {
            list.selectByVisibleText(StoreHelper.getValue(text));
        } else {
            list.selectByVisibleText(text);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin üzerine gel"})
    public void findElementWithTextsAndHoverNextTo(String key, String text, String key2) {
        Actions action = new Actions(driver);
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
                action.moveToElement(elementNextTo).perform();
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin <attr> attributesini <text2> yap"})
    public void findElementWithTextsAndSetAttributeNextTo(String key, String text, String key2, String attr, String text2) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                if (text2.startsWith("Deger_")) {
                    js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", elementNextTo, attr, StoreHelper.getValue(text2));
                } else {
                    js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", elementNextTo, attr, text2);
                }
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin textini <key3> olarak kaydet"})
    public void findElementWithTextsAndSaveTextNextTo(String key, String text, String key2, String key3) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                StoreHelper.saveValue(key3, elementNextTo.getText());
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin <attr> attributesini <key3> olarak kaydet"})
    public void findElementWithTextsAndSaveAttributeNextTo(String key, String text, String key2, String attr, String key3) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(elementNextTo, attr));
                StoreHelper.saveValue(key3, elementNextTo.getAttribute(attr));
                break;
            }
        }
    }

    @Step({"<key> li elementler var mı?"})
    public void checkElements(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        if (elements.isEmpty())
            Assert.fail("Elementler bulunamadı.");
    }

    @Step({"<key> li element var mı?"})
    public void checkElement(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        } catch (Exception e) {
            Assert.assertTrue("Element bulunamadı.", false);
        }
    }

    @Step({"<key> li element görünür mü?"})
    public void checkElementVisible(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElement));
            WebElement element = driver.findElement(byElement);
            if (!element.isDisplayed())
                Assert.fail("Element görünür değil.");
        } catch (Exception e) {
            Assert.assertTrue("Element görünür değil.", false);
        }
    }

    @Step({"<key> li element yok mu?"})
    public void checkNotExistElement(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            if (element != null)
                Assert.assertFalse("Element bulundu.", true);
            else return;
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan yok mu?"})
    public void checkTextNotExistsElements(String key, String text) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            int size = elements.size();
            for (int i = 0; i < size; ) {
                if (elements.get(i).getText().equals(text))
                    Assert.assertFalse("Element bulundu.", true);
                if (i == size - 1) return;
                i++;
            }
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Step({"<key> li elementlerin texti var mı?"})
    public void checkTextsElement(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if (element.getText() == null) {
                Assert.assertTrue(false);
            }
        }
    }

    @Step({"<key> li elementin texti var mı?"})
    public void checkText(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        if (element.getText() == null) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementin <attr> attributesi var mı?"})
    public void checkAttribute(String key, String attr) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
        } catch (Exception e) {
            Assert.assertTrue("Elementin " + attr + " attributesi yok.", false);
        }
    }

    @Step({"<key> li elementin <attr> attributesi yok mu"})
    public void checkNotExistAttribute(String key, String attr) throws IOException {

        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
        if (attr != null) {
            Assert.assertFalse("Elementin " + attr + " attributesi var.", true);
        } else return;
    }

    @Step({"<key> li elementlerin <attr> attributesi yok mu?"})
    public void secondCheckNotExistAttribute(String key, String attr) throws IOException {

        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
            if (attr != null) {
                Assert.assertFalse("Elementin " + attr + " attributesi var.", true);
            } else return;
        }
    }

    @Step({"<key> li elementlerin <attr> attributesi var mı?"})
    public void findElementWithCheckAttribute(String key, String attr) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
            }
        } catch (Exception e) {
            Assert.assertTrue("Elementlerin " + attr + " attributesi yok.", false);
        }
    }

    @Step({"<key> li elementlerin <attr> attributesi <attrtext> mi"})
    public void findElementWithCheckAttributeText(String key, String attr, String attrtext) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                waitingAction.waitUntil(ExpectedConditions.attributeToBe(element, attr, attrtext));
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li element tıklanabilir mi?"})
    public void checkClickable(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementin texti <text> mi?"})
    public void checkTextEquals(String key, String text) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            if ((text.startsWith("Deger_") && element.getText().replaceAll("\\s+", "").contains(StoreHelper.getValue(text).replaceAll("\\s+", ""))) || element.getText().replaceAll("\\s+", "").equals(text.replaceAll("\\s+", ""))) {
            } else {
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementin texti <text> içeriyor mu?"})
    public void checkTextContains(String key, String text) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        if (text.startsWith("Deger_")) {
            if (!(element.getText().contains(StoreHelper.getValue(text))))
                Assert.assertTrue(false);
        } else {
            if (!(element.getText().contains(text))) {
                Assert.assertTrue(false);
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan var mı?"})
    public void findElementWithTextsAndCheckText(String key, String text) {
        int count = 0;
        while (count < 5) {
            try {
                By byElement = ElementHelper.getElementInfoToBy(key);
                waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
                List<WebElement> elements = driver.findElements(byElement);
                for (int i = 0; i < elements.size(); ) {
                    if ((text.startsWith("Deger_") && elements.get(i).getText().contains(StoreHelper.getValue(text))) || elements.get(i).getText().equals(text)) {
                        return;
                    }
                    if (i % 3 == 0) swipeToElement(elements.get(i));
                    if (i == elements.size() - 1) break;
                    i++;
                }
            } catch (StaleElementReferenceException s) {
            }
            count++;
        }
        Assert.fail(text + " değerine eşit element bulunamadı.");
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın <attr> attributesi var mı?"})
    public void findElementWithTextsAndCheckAttribute(String key, String text, String attr) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if (element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
                }
            }
        } catch (Exception e) {
            Assert.assertTrue("Elementin " + attr + " attributesi yok.", false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan tıklanabilir mi?"})
    public void findElementWithTextsAndCheckClickable(String key, String text) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(element));
                }
            }
        } catch (Exception e) {
            Assert.assertTrue("Element tıklanılabilir değil.", false);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> element var mı?"})
    public void checkElementNextTo(String key, String key2) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
        } catch (Exception e) {
            Assert.assertTrue("Yanındaki element yok.", false);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin texti var mı?"})
    public void checkTextNextTo(String key, String key2) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            if (childElement.getText() == null) {
                Assert.assertTrue(false);
            }
        } catch (Exception e) {
            Assert.assertTrue("Yanındaki elementin texti yok.", false);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin <attr> attributesi var mı?"})
    public void checkAttributeNextTo(String key, String key2, String attr) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(childElement, attr));
        } catch (Exception e) {
            Assert.assertTrue("Yanındaki elementin attributesi yok.", false);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementi tıklanabilir mi?"})
    public void checkClickableNextTo(String key, String key2) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(childElement));
        } catch (Exception e) {
            Assert.assertTrue("Yanındaki element tıklanılabilir değil.", false);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin texti <text> mi?"})
    public void checkTextEqualsNextTo(String key, String key2, String text) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            waitingAction.waitUntil(ExpectedConditions.textToBePresentInElement(childElement, text));
        } catch (Exception e) {
            Assert.assertTrue("Yanındaki elementin texti " + text + " değil.", false);
        }
    }

    @Step({"<key> li elementin yanındaki <key2> elementinin texti <text> içeriyor mi?"})
    public void checkTextContainsNextTo(String key, String key2, String text) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                var = true;
            }
            Assert.assertTrue(var);
        } catch (Exception e) {
            Assert.assertTrue("Yanındaki elementin texti " + text + " değerini içermiyor.", false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan elementin <attr> attributesi <attrtext> içeriyor mu?"})
    public void findElementWithTextAndCheckAttributeContainsTextNextTo(String key, String text, String attr, String attrtext) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
                    if (element.getAttribute(attr).contains(attrtext))
                        break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> element var mı?"})
    public void findElementWithTextsAndCheckElementNextTo(String key, String text, String key2) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementi yok mu?"})
    public void findElementWithTextsAndCheckElementNextToNotExists(String key, String text, String key2) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                    WebElement nextElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                    waitingAction.waitUntil(ExpectedConditions.invisibilityOfElementLocated(ElementHelper.getElementInfoToBy(key2)));
                    if (nextElement.getText() == null)
                        return;
                    else {
                        Assert.fail("Yanindaki elementin texti var");
                    }
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Step({"<key> li elementlerden texti olanın yanındaki <key2> elementi var mı?"})
    public void findElementsCheckTextsAndCheckElementNextTo(String key, String key2) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if (!(element.getText() == null)) {
                    waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden texti olanın yanındaki <key2> elementin texti var mı?"})
    public void findElementsCheckTextsAndCheckElementsTextNextTo(String key, String key2) {

        Boolean var = false;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if (!(element.getText() == null)) {
                By byElement2 = ElementHelper.getElementInfoToBy(key2);
                List<WebElement> lastElements = element.findElements(byElement2);
                for (WebElement lastElement : lastElements) {
                    if (!(lastElement.getText() == null)) {
                        System.out.println("gİrdİ");
                        waitingAction.waitUntil(ExpectedConditions.visibilityOf(lastElement));
                        var = true;
                        break;
                    }
                }
                break;
            }
        }
        Assert.assertTrue(var);
    }


    @Step({"<key> li elementlerden yanındaki <key2> elementinin <attr> attributesi <attrtext> içerenin yanındaki <key3> elementine tıkla"})
    public void findElementsCheckTextsAndClickElementTextNextTo(String key, String key2, String attr, String attrtext, String key3) {
        int i = 0;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            scrollToElement(element);
            By byElement2 = ElementHelper.getElementInfoToBy(key2);
            WebElement lastElement = element.findElement(byElement2);
            if (lastElement.getAttribute(attr).contains(attrtext)) {
                By byElement3 = ElementHelper.getElementInfoToBy(key3);
                WebElement lastNearElement = lastElement.findElement(byElement3);
                lastNearElement.click();
            }
            if (lastElement.getAttribute(attr).contains(attrtext))
                i++;
        }
        for (WebElement element : elements) {
            scrollToElement(element);
            By byElement2 = ElementHelper.getElementInfoToBy(key2);
            WebElement lastElement = element.findElement(byElement2);
            if (lastElement.getAttribute(attr).contains(attrtext))
                i--;
            if (i == 0) return;
        }
        Assert.fail("Elementlerin hepsine tıklanıldı.");
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementlerden <text2> değerine eşit olan var mı?"})
    public void findElementWithTextsAndFindElementWithTextNextTo(String key, String text, String key2, String text2) {
        Boolean check1 = false;
        Boolean check2 = false;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            swipeToElement(element);
            System.out.println(element.getText());
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                check1 = true;
                By byElement2 = ElementHelper.getElementInfoToBy(key2);
                List<WebElement> lastElements = element.findElements(byElement2);
                for (WebElement lastElement : lastElements) {
                    swipeToElement(element);
                    System.out.println(lastElement.getText());
                    if ((text2.startsWith("Deger_") && lastElement.getText().contains(StoreHelper.getValue(text2))) || lastElement.getText().contains(text2)) {
                        waitingAction.waitUntil(ExpectedConditions.visibilityOf(lastElement));
                        check2 = true;
                        break;
                    }
                }
                break;
            }
        }
        if (check1 == false)
            Assert.fail(text + " textli element bulunamadı.");
        else if (check1 == false && check2 == false)
            Assert.fail(text2 + " textli element bulunamadı.");
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementler <text2> değerine eşit mi?"})
    public void findElementWithTextsAndFindElementCheckWithTextNextTo(String key, String text, String key2, String text2) {
        Boolean var = false;
        if (key2.equals("txt_tableRowHeaderNameWithRows")) {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            int sizeParent = elements.size();
            for (int k = 0; k < sizeParent; k++) {
                swipeToElement(elements.get(k));
                System.out.println(elements.get(k).getText());
                if (elements.get(k).getText().equals(text)) {
                    var = true;
                    ElementInfo elementInfo = ElementHelper.getElementInfo(key2);
                    String by = (elementInfo.getValue() + "/td[" + (k + 1) + "]");
                    List<WebElement> lastElements = elements.get(k).findElements(By.xpath(by));
                    int size = lastElements.size();
                    System.out.println(text2 + " Size: " + size);
                    for (int i = 0; i < size; ) {
                        swipeToElement(lastElements.get(i));
                        System.out.println(" Element Text: " + lastElements.get(i).getText());
                        String lastText = lastElements.get(i).getText();
                        if (lastElements.get(i).getText().contains(text2)) // equalsti fakat rowda başka içerikte vardı // Guest Name
                        {
                            if (i == size - 1) break;
                            i++;
                        } else Assert.fail(text2 + " li elementlerin hepsi eşit değil.");
                    }
                    break;
                }
            }
            if (var == false) {
                Assert.fail(text + " li element bulunamadı.");
            }
        } else {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                swipeJS(element);
                System.out.println(element.getText());
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    var = true;
                    By byElement2 = ElementHelper.getElementInfoToBy(key2);
                    List<WebElement> lastElements = element.findElements(byElement2);
                    int size = lastElements.size();
                    for (int i = 0; i < size; ) {
                        System.out.println(" Element Text: " + lastElements.get(i).getText());
                        if (lastElements.get(i).getText().equals(text2)) {
                            if (i == size - 1) break;
                            i++;
                        } else Assert.fail(text2 + " li elementlerin hepsi eşit değil.");
                    }
                    break;
                }
            }
            if (var == false) {
                Assert.fail(text + " li element bulunamadı.");
            }
        }
    }

    String dataMarkupId = "";

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementler <text2> değerine eşit olanın yanındaki <key3> elementinin id bilgisini kaydet ve <key4> elementlerinden yanındaki <key5> elementin delete butonuna tıkla"})
    public void findElementWithTextsAndFindElementSaveTextAndClickIndexNextTo(String key, String text, String key2, String text2, String key3, String key4, String key5) {
        Boolean var = false;
        if (key2.equals("txt_tableRowHeaderNameWithRows")) {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            int sizeParent = elements.size();
            for (int k = 0; k < sizeParent; k++) {
                swipeToElement(elements.get(k));
                System.out.println(elements.get(k).getText());
                if (elements.get(k).getText().equals(text)) {
                    var = true;
                    ElementInfo elementInfo = ElementHelper.getElementInfo(key2);
                    String by = (elementInfo.getValue() + "/td[" + (k + 1) + "]");
                    List<WebElement> lastElements = elements.get(k).findElements(By.xpath(by));
                    int size = lastElements.size();
                    System.out.println(text2 + " Size: " + size);
                    for (int i = 0; i < size; ) {
                        swipeToElement(lastElements.get(i));
                        System.out.println(" Element Text: " + lastElements.get(i).getText());
                        String lastText = lastElements.get(i).getText();
                        if (lastElements.get(i).getText().contains(text2)) // equalsti fakat rowda başka içerikte vardı // Guest Name
                        {
                            System.out.println("Data Row Text(İF İÇİNDE): " + lastElements.get(i).getText());
                            By byElement3 = ElementHelper.getElementInfoToBy(key3);
                            WebElement element = lastElements.get(i).findElement(byElement3);
                            dataMarkupId = element.getText();
                            System.out.println("Silinecek Data Id: " + dataMarkupId);
                            findElementWithTextsAndClickNextToWithIndex(key4, dataMarkupId, key5);
                            return;
                        }
                        //else Assert.fail(text2 + " li elementlerin hepsi eşit değil.");
                    }
                    break;
                }
            }
            if (var == false) {
                Assert.fail(text + " li element bulunamadı.");
            }
        }
    }


    @Step({"<key> li elementlerden id bilgisi silindi mi?"})
    public void findElementsCheckTextsAndDeletedId(String key) {
        System.out.println("Silindiği kontrol edilecek data: " + dataMarkupId);
        checkTextNotExistsElements(key, dataMarkupId);
    }


    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementlerden <text2> değerine eşit olanın yanındaki <key3> elementinin texti <text3> mı?"})
    public void findElementWithTextsAndFindElementWithTextAndFindWithTextNextTo(String key, String text, String key2, String text2, String key3, String text3) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    By byElement2 = ElementHelper.getElementInfoToBy(key2);
                    List<WebElement> lastElements = element.findElements(byElement2);
                    for (WebElement lastElement : lastElements) {
                        if ((text2.startsWith("Deger_") && lastElement.getText().contains(StoreHelper.getValue(text2))) || lastElement.getText().contains(text2)) {
                            By lastlastbyElement = ElementHelper.getElementInfoToBy(key3);
                            WebElement finalelement = lastElement.findElement(lastlastbyElement);
                            if ((text3.startsWith("Deger_") && finalelement.getText().contains(StoreHelper.getValue(text3))) || finalelement.getText().contains(text3)) {
                                waitingAction.waitUntil(ExpectedConditions.visibilityOf(finalelement));
                                var = true;
                                break;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            Assert.assertTrue(var);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementlerden <text2> değerine eşit olanın yanındaki <key3> elementinin textini <saveKey> olarak kaydet"})
    public void findElementWithTextsAndFindElementWithTextAndSaveTextNextTo(String key, String text, String key2, String text2, String key3, String text3) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    By byElement2 = ElementHelper.getElementInfoToBy(key2);
                    List<WebElement> lastElements = element.findElements(byElement2);
                    for (WebElement lastElement : lastElements) {
                        if ((text2.startsWith("Deger_") && lastElement.getText().contains(StoreHelper.getValue(text2))) || lastElement.getText().contains(text2)) {
                            By lastlastbyElement = ElementHelper.getElementInfoToBy(key3);
                            WebElement finalelement = lastElement.findElement(lastlastbyElement);
                            waitingAction.waitUntil(ExpectedConditions.visibilityOf(finalelement));
                            StoreHelper.saveValue(text3, finalelement.getText());
                            var = true;
                            break;
                        }
                    }
                    break;
                }
            }
            Assert.assertTrue(var);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementlerden <text2> değerine eşit olanın yanındaki <key3> elementine tıkla"})
    public void findElementWithTextsAndFindElementWithTextAndClickNextTo(String key, String text, String key2, String text2, String key3) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    By byElement2 = ElementHelper.getElementInfoToBy(key2);
                    List<WebElement> lastElements = element.findElements(byElement2);
                    for (WebElement lastElement : lastElements) {
                        if ((text2.startsWith("Deger_") && lastElement.getText().contains(StoreHelper.getValue(text2))) || lastElement.getText().contains(text2)) {
                            By lastlastbyElement = ElementHelper.getElementInfoToBy(key3);
                            WebElement finalelement = lastElement.findElement(lastlastbyElement);
                            waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(finalelement));
                            finalelement.click();
                            var = true;
                            break;
                        }
                    }
                    break;
                }
            }
            Assert.assertTrue(var);
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin <attr> attributesi var mı?"})
    public void findElementWithTextsAndCheckAttributeNextTo(String key, String text, String key2, String attr) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                    WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                    waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(childElement, attr));
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olan elementin <attr> attributesi <attrtext> mı?"})
    public void findElementWithTextAndCheckAttributeTextNextTo(String key, String text, String attr, String attrtext) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.attributeToBe(element, attr, attrtext));
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin <attr> attributesi <attrtext> içeriyor mu?"})
    public void findElementWithTextsAndCheckAttributeTextNextTo(String key, String text, String key2, String attr, String attrtext) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            swipeToElement(element);
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                //waitingAction.waitUntil(ExpectedConditions.attributeToBe(childElement,attr,attrtext));
                String attrText = childElement.getAttribute(attr);
                System.out.println("Element attr: " + childElement.getAttribute(attr));
                System.out.println("Gönderilen attr: " + attrtext);
                if (childElement.getAttribute(attr).contains(attrtext))
                    return;
            }
        }
        Assert.fail(text + " li element değer içermiyor veya yanındaki elementin " + attr + " attributesine ait değer bulunamadı.");
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin <attr> attributesi <attrtext> mı?"})
    public void findElementWithTextAndCheckAttributeEqualsTextNextTo(String key, String text, String key2, String attr, String attrtext) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            swipeToElement(element);
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                //waitingAction.waitUntil(ExpectedConditions.attributeToBe(childElement,attr,attrtext));
                String attrText = childElement.getAttribute(attr);
                System.out.println("Element attr: " + childElement.getAttribute(attr));
                System.out.println("Gönderilen attr: " + attrtext);
                if (childElement.getAttribute(attr).equals(attrtext))
                    return;
            }
        }
        Assert.fail(text + " li element değer içermiyor veya yanındaki elementin " + attr + " attributesine ait değer bulunamadı.");
    }

    private String startDate = "";
    private String endDate = "";

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin <attr> attributesini kaydet"})
    public void findElementWithTextsAndSaveAttributeTextNextTo(String key, String text, String key2, String attr) {
        try {
            if (key2.equals("txtbx_startDateInput")) {
                By byElement = ElementHelper.getElementInfoToBy(key);
                waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
                List<WebElement> elements = driver.findElements(byElement);
                for (WebElement element : elements) {
                    if (element.getText().equals(text)) {
                        waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                        WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                        startDate = childElement.getAttribute(attr);
                        break;
                    }
                }
            } else if (key2.equals("txtbx_endDateInput")) {
                By byElement = ElementHelper.getElementInfoToBy(key);
                waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
                List<WebElement> elements = driver.findElements(byElement);
                for (WebElement element : elements) {
                    if (element.getText().equals(text)) {
                        waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                        WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                        endDate = childElement.getAttribute(attr);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementlerin textindeki tarihler doğru aralıkta mı?"})
    public void findElementWithTextsAndSaveAttributeTextNextTo(String key, String text, String key2) throws ParseException {
        Boolean var = false;
        String lastText = "";
        if (key2.equals("txt_tableRowHeaderNameWithRows")) {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            int sizeParent = elements.size();
            for (int k = 0; k < sizeParent; k++) {
                swipeJS(elements.get(k));
                System.out.println(elements.get(k).getText());
                if (elements.get(k).getText().equals(text)) {
                    var = true;
                    ElementInfo elementInfo = ElementHelper.getElementInfo(key2);
                    String by = (elementInfo.getValue() + "/td[" + (k + 1) + "]");
                    List<WebElement> lastElements = elements.get(k).findElements(By.xpath(by));
                    int size = lastElements.size();
                    for (int i = 0; i < size; ) {

                        swipeJS(lastElements.get(i));
                        System.out.println(" Element Text: " + lastElements.get(i).getText());
                        if (lastElements.get(i).getText().contains(" ")) {
                            int spaceIndex = lastElements.get(i).getText().indexOf(" ");
                            lastText = lastElements.get(i).getText().substring(0, spaceIndex);
                        } else {
                            lastText = lastElements.get(i).getText();
                        }
                        System.out.println("Tarih: " + lastText);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                        Date tableRowStartDate = sdf.parse(lastText);
                        Date pageHeaderStartDate = sdf.parse(startDate);
                        Date pageHeaderEndDate = null;
                        System.out.println("End Date is : " + endDate);
                        int result;
                        int result2;
                        if (endDate.equals("")) {
                            Date nowDate = new Date(System.currentTimeMillis());
                            //endDate = sdf.format(nowDate);
                            //pageHeaderEndDate = sdf.parse(sdf.format(nowDate));
                            System.out.println("End Date Now Time : " + endDate);
                            result = tableRowStartDate.compareTo(pageHeaderStartDate);
                            result2 = -1;
                        } else {
                            pageHeaderEndDate = sdf.parse(endDate);
                            result = tableRowStartDate.compareTo(pageHeaderStartDate);
                            result2 = tableRowStartDate.compareTo(pageHeaderEndDate);
                            System.out.println("result: " + result);
                            System.out.println("result2: " + result2);
                        }

                        if (!((result == 0 || result > 0) && (result2 == 0 || result2 < 0))) {
                            Assert.fail("Tarih sıralaması yanlıştır.");
                            //System.out.println("Date1 is equal to Date2");
                        }
                        //pageHeaderEndDate = sdf.parse(endDate);

                        /*int result = tableRowStartDate.compareTo(pageHeaderStartDate);
                        int result2 = tableRowStartDate.compareTo(pageHeaderEndDate);
                        System.out.println("result: " + result);
                        System.out.println("result2: " + result2);

                        if (!((result == 0 || result > 0) && (result2 == 0 || result2 < 0)) || !(result2 > 0)) {
                            Assert.fail("Tarih sıralaması yanlıştır.");
                            //System.out.println("Date1 is equal to Date2");
                        }*/


                        /*else if (result > 0) {
                            System.out.println("Date1 is after Date2");
                        } else if (result < 0) {
                            System.out.println("Date1 is before Date2");
                        } else {
                            System.out.println("How to get here?");
                        }*/

                        if (i == size - 1) break;
                        i++;
                    }
                    break;
                }
            }
            if (var == false) {
                Assert.fail(text + " li element bulunamadı.");
            }
        }
    }


    @Step({"<key> li elementlerden <text> değerine eşit olan elementten önceki <key2> elementlerin tarihi <attr> attributesi <attrtext> içeriyor mu?"})
    public void findElementWithTextsAndCheckClickableNextTo(String key, String text, String key2, String attr, String attrtext) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            int size = elements.size();
            int disableDayCount = 0;
            for (int i = 0; i < size; i++) {
                if (!elements.get(i).getText().equals(text)) {
                    disableDayCount++;
                } else break;
            }
            By byElement2 = ElementHelper.getElementInfoToBy(key2);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement2));
            List<WebElement> elements2 = driver.findElements(byElement2);
            for (int i = 0; i < disableDayCount; ) {
                if (!elements2.get(i).getAttribute(attr).contains(attrtext))
                    Assert.fail(text + " li elementlerden önceki elementlerin " + attr + " attributesi " + attrtext + " içermiyor.");
                if (i == size - 1) break;
                i++;
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerin yanındaki <key2> elementlerinden <attr> attributesi <attrtext> içermeyen ilk elemente tıkla"})
    public void findElementWithTextsAndCheckAttrAndClickFirst(String key, String key2, String attr, String attrtext) {
        Boolean var = false;
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);

            By byElement2 = ElementHelper.getElementInfoToBy(key2);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement2));
            List<WebElement> elements2 = driver.findElements(byElement2);

            By byElement3 = ElementHelper.getElementInfoToBy("txt_accommondationStartDate");
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement3));
            WebElement element = driver.findElement(byElement3);
            for (int i = 0; i < elements2.size(); i++) {
                //swipeToElement(elements2.get(i));
                if (element.getAttribute("value").equals("")) {
                    if (i == elements2.size() - 1) {
                        System.out.println("Size: " + i);
                        System.out.println("Elements Size: " + elements.size());
                        System.out.println("Elements2 Size: " + elements2.size());
                        System.out.println("Elements Son Attr: " + elements.get(i).getAttribute("class"));
                        System.out.println("Elements Son: " + elements2.get(i).getText());
                        var = true;
                        if (!elements.get(i + 1).getAttribute(attr).contains(attrtext)) {
                            System.out.println("1. " + elements.get(i).getText());
                            System.out.println("2. " + elements.get(i + 1).getText());
                            elements.get(i + 1).click();
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (var == false) Assert.fail("Elementin size değeri i değerine eşit değil.");
            //Assert.fail("Elementin "+attr+" attributesi "+attrtext+" içeriyor.");
        }
    }


    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementi tıklanabilir mi?"})
    public void findElementWithTextsAndCheckClickableNextTo(String key, String text, String key2) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                    System.out.println("Element Text: " + element.getText());
                    var = true;
                    waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                    WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                    waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(childElement));
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"Sayfanın title ı <title> mı?"})
    public void checkTitle(String title) {
        try {
            waitingAction.waitUntil(ExpectedConditions.titleIs(title));

        } catch (Exception e) {
            Assert.assertTrue("Sayfanın title ı " + title + " değil", false);
        }
    }

    @Step({"Sayfanın url i <url> mı?"})
    public void checkUrl(String url) {
        try {
            waitingAction.waitUntil(ExpectedConditions.urlToBe(url));

        } catch (Exception e) {
            Assert.assertTrue("Sayfanın url i " + url + " değil. Url: " + driver.getCurrentUrl(), false);
        }
    }

    @Step({"Yeni açılan sayfaya geç"})
    public void navigateWindow() {
        try {
            winHandleBefore = driver.getWindowHandle();
            Set<String> winHandles = driver.getWindowHandles();
            for (String handle : winHandles) {
                if (!handle.equals(winHandleBefore)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<frame> isimli frame e geç"})
    public void navigateFrame(String frame) {
        try {
            driver.switchTo().frame(frame);
        } catch (Exception e) {
            System.out.println(e);
            Assert.assertTrue(false);

        }
    }

    @Step({"Frameden ana sayfaya geç"})
    public void navigateFrame() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"Bulunduğun sayfayı kapat"})
    public void closePage() {
        try {
            driver.close();
           // driver.switchTo().window(winHandleBefore);
           // driver.navigate().to("http://uatb2b.odeontours.com/");
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }


    @Step({"Bulunduğun sayfada geri git"})
    public void backPage() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"Bulunduğun sayfada ileri git"})
    public void forwardPage() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"Bulunduğun sayfayı yenile"})
    public void refreshPage() {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }


    @Step("Beklemeleri kapat")
    public void closeWait() {
        configuration.setWaitAjax(false);
        configuration.setWaitPageLoad(false);
        configuration.setWaitAngular(false);
    }

    @Step("Beklemeleri ac")
    public void openWait() {
        configuration.setWaitAjax(true);
        configuration.setWaitPageLoad(true);
        configuration.setWaitAngular(false);
    }


    @Step("<key> li elementin içindekileri sil")
    public void deleteText(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        element.clear();
        if (System.getProperty("os.name").contains("Mac"))
            element.sendKeys(Keys.COMMAND + "a");
        else if (System.getProperty("os.name").contains("Windows"))
            element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    @Step("<key> li elementi temizle")
    public void deleteText2(String key) {
        String s = "";
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        if (System.getProperty("os.name").contains("Mac"))
            s = Keys.chord(Keys.COMMAND, "a");
        else if (System.getProperty("os.name").contains("Windows"))
            s = Keys.chord(Keys.CONTROL, "a");
        element.sendKeys(s);
        element.sendKeys(Keys.DELETE);
    }

    @Step("<key> Entere tıkla")
    public void clickEnterWithElement(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        element.sendKeys(Keys.ENTER);
    }

    @Step("Entere tıkla")
    public void clickEnter() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(200);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinde Entere Tikla"})
    public void findElementWithTextsAndSendKeyNextToEnter(String key, String text, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            //System.out.println(element.getText());
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                //System.out.println(element.getText() + "gİrdİ");
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                //waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
                elementNextTo.sendKeys(Keys.ENTER);
                break;
            }
        }
    }

    @Step({"<key> li elementi sürükle ve bırak"})
    public void dragAndDropColumn(String key) {
        Actions action = new Actions(driver);
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.visibilityOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        action.moveToElement(element).perform();
        action.dragAndDropBy(element, 150, 0).perform();
    }


    @Step({"Excel <excelFileName> dosyasından veri okuma işlemi yapılır."})
    public void excelReadData(String excelFileName) throws IOException {
        ExcelHelper excel = null;
        String fileNamePath = "";
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm");
        String date = dateTime.format(formatter);
        System.out.println(dateTime.format(formatter));

        if (excelFileName.contains("Markup")){
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);
        }
        else if(excelFileName.contains("reservation"))    {
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);
        }
        try {
           // excel = new ExcelHelper("/Users/dilekaysegun/Downloads/"+fileNamePath);
           // File file = new File("/Users/dilekaysegun/Downloads/reservation-04.10.2021-11.37.xlsx");
           // ExcelHelper excel = new ExcelHelper("/Users/dilekaysegun/Downloads/"+fileNamePath);
            File file = new File("/Users/dilekaysegun/Downloads/"+fileNamePath);
            FileInputStream inputStream = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wb.getSheet("Sheet");
            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            //her hücrede bulunan verileri yazdırmak için tüm satırı gez
            for (int i = 0; i <= rowCount; i++) {
                //hücre sayısını al
                int cellcount = sheet.getRow(i).getLastCellNum();
                System.out.println("Row" + i + " data is :");
                for (int j = 0; j < cellcount; j++) {
                    System.out.print(sheet.getRow(i).getCell(j).getStringCellValue() + ",");
                    String deneme= sheet.getRow(i).getCell(j).getStringCellValue();
                        //System.out.println("deneme :"+ deneme);
                    char harf = deneme.charAt(4);
                    char ch = 'ç';
                    int asciiKod = (int) ch;

                }
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        //excel.deleteExcel();
}


    @Step({"Excel <excelFileName> dosyasının doğru uzantıda olduğu kontrol edilir."})
    public void checkFilename(String excelFileName) throws IOException, ParseException {

        ExcelHelper excel = null;
        String fileNamePath = "";
        //LocalDateTime dateTime = LocalDateTime.now();
        Date dateTime = new Date();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm");

        //String date = dateTime.format(formatter);
        //System.out.println(dateTime.format(formatter));

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy-HH.mm");

        String date = format.format(dateTime);

        //date = date.substring(date.length()-2, date.length()-1);

        if (excelFileName.contains("Markup")){
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);

        }
        else if(excelFileName.contains("reservation"))    {
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);
        }
        try {
            File file = new File(fileNamePath);

            if (!file.exists()) {
                DateUtils.addMinutes(dateTime, 1);
                date = format.format(dateTime);
                fileNamePath = excelFileName+"-"+date+".xlsx";
            }

            excel = new ExcelHelper("/Users/dilekaysegun/Downloads/"+fileNamePath);
            String row0 = excel.getData(0,0,0);
            System.out.println("Data from Excel is: "+row0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        excel.deleteExcel();

        //"/Users/virgosol-furkan/Downloads/"

        //File file = new File("/Users/virgosol-furkan/Downloads/");
//
        //FileInputStream fileInputStream = new FileInputStream(file);
//
        //XSSFWorkbook wb = new XSSFWorkbook(fileInputStream);
//
        //XSSFSheet sheet1 = wb.getSheetAt(0); //name verilebiliyor.
//
        //String data0 = sheet1.getRow(0).getCell(0).getStringCellValue();
//
        //System.out.println("Data from Excel is : "+data0);
//
        //wb.close();



        //FİRST COLUMN VALUES
        /*int rowCount = sheet1.getLastRowNum();

        System.out.println("Total rows is : "+rowCount);

        for(int i = 0; i < rowCount; i++){
            String data0 = sheet1.getRow(i).getCell(0).getStringCellValue();
            System.out.println("Test Data from Excel is : "+data0);
            System.out.println("Data From Row"+i+" is: "+data0);
        }*/

        // SET CELL VALUE
        //sheet1.getRow(0).createCell(2).setCellValue("Pass");
        //sheet1.getRow(1).createCell(2).setCellValue("Fail");

        //FileOutputStream fileOutputStream = new FileOutputStream(file);
        //wb.write(fileOutputStream);


    }

    @Step({"Excel <excelFileName> dosyasının uzantısı kontrol edilir ve içeriğinin datagrid yapısıyla aynı olduğu kontrol edilir."})
    public void checkDataGridAndExcelColumn(String excelFileName) throws IOException, BiffException {

        ExcelHelper excel = null;
        String fileNamePath = "";
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm");
        String date = dateTime.format(formatter);
        System.out.println(dateTime.format(formatter));

        if (excelFileName.contains("Markup")){
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);

        }
        else if(excelFileName.contains("reservation"))    {
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);
        }
        try {
            excel = new ExcelHelper("/Users/dilekaysegun/Downloads/"+fileNamePath);
            String row0 = excel.getData(0,0,0);
            System.out.println("Data from Excel is: "+row0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        By byElement = ElementHelper.getElementInfoToBy("txt_tableRowNames");
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        int size = elements.size();

        ElementInfo elementInfo = ElementHelper.getElementInfo("txt_tableRowHeaderNameWithRows");
        String by = (elementInfo.getValue()+"/td["+(1)+"]");
        List<WebElement> rowElements = elements.get(0).findElements(By.xpath(by));
        int firstRowSize = rowElements.size();

        String data[][] = new String[size][firstRowSize+1];
        int rowSize = 0;

        for (int i = 0; i < size; ){
            swipeToElement(elements.get(i));
            data[i][0] = elements.get(i).getText();
            System.out.println("Dizi İçeriği: "+data[i][0]);
            if (i == size - 1) break;
            i++;
        }

        for (int k = 0 ; k < size; k++) {
            swipeToElement(elements.get(k));
            System.out.println(elements.get(k).getText());
            if (elements.get(k).getText().equals(data[k][0])) {
                ElementInfo elementInfo1 = ElementHelper.getElementInfo("txt_tableRowHeaderNameWithRows");
                String by1 = (elementInfo1.getValue()+"/td["+(k+1)+"]");
                List<WebElement> lastElements = elements.get(k).findElements(By.xpath(by1));
                rowSize = lastElements.size();
                for (int i = 0; i < rowSize; i++) {
                    swipeToElement(lastElements.get(i));
                    data[k][i+1] = lastElements.get(i).getText();
                }
            }
        }

        /*for(int i = 0; i < size;i++){
            for(int k = 0; k < rowSize+1; k++){
                System.out.println("Array List ["+i+"]"+"["+k+"] :" +data[i][k]);
            }
        }
        System.out.println("Excel Data: ");*/

        //System.out.println("Array List ["+1+"]"+"["+1+"] :" +excel.getData(0,1,1));

        /*for(int i = 0; i < size;i++){
            for(int k = 0; k < rowSize+1; k++){
                System.out.println("Array List ["+i+"]"+"["+k+"] :" +excel.getData(0,k,i));
            }
        }*/

        for (int i = 0; i < size; i++){
            for(int k = 0; k < rowSize+1; k++){
                if (!(data[i][k].equals(excel.getData(0,k,i))))
                    Assert.fail(data[i][k]+" texti "+excel.getData(0,k,i)+" textine eşit değil.");
            }
        }

        //key txt_tableRowNames

        //System.out.println("Element Data Grid Size: "+size);
        //System.out.println("Excel Table First Column Y: "+excel.getYCount(0));
        //System.out.println("Excel Table Last Column X: "+excel.getXCount(0));

        excel.deleteExcel();
    }

    /*@Step({"Excel <excelFileName> dosyasının uzantısı kontrol edilir ve içeriğinin datagrid yapısıyla aynı olduğu kontrol edilir."})
    public void checkDataGridAndExcelColumn2(String excelFileName) throws IOException {

        ExcelHelper excel = null;
        String fileNamePath = "";
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH.mm");
        String date = dateTime.format(formatter);
        System.out.println(dateTime.format(formatter));

        if (excelFileName.contains("Markup")){
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);

        }
        else if(excelFileName.contains("reservation"))    {
            fileNamePath = excelFileName+"-"+date+".xlsx";
            System.out.println(fileNamePath);
        }
        try {
            excel = new ExcelHelper("/Users/virgosol-furkan/Downloads/"+fileNamePath);
            String row0 = excel.getData(0,0,0);
            System.out.println("Data from Excel is: "+row0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        By byElement = ElementHelper.getElementInfoToBy("txt_tableRowNames");
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        int size = elements.size();
        String data[] = new String[size];
        for (int i = 0; i < size; ){
            swipeToElement(elements.get(i));
            data[i] = elements.get(i).getText();
            System.out.println("Dizi İçeriği: "+data[i]);
            if (i == size - 1) break;
            i++;
        }

        for (int i = 0; i < size; ){
            if (!(data[i].equals(excel.getData(0,0,i))))
                Assert.fail(data[i]+" texti "+excel.getData(0,0,i)+" textine eşit değil.");
            if (i == size - 1) break;
            i++;
        }

        excel.deleteExcel();
    }*/

    @Step("<key> swipe element")
    public void swipeToElement(String key) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        for(int i=0;i<25;i++) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            js.executeScript("arguments[0].scrollIntoView({behavior: \"auto\", block: \"center\", inline: \"center\"});", element);
        }
    }

    @Step("frame gir")
    public void framegir() {
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ms-rtestate-field iframe")));
        driver.switchTo().frame(driver.findElement(By.cssSelector(".ms-rtestate-field iframe")));
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0;i<30;i++) {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        }
    }

    public void wait(int s) throws InterruptedException {
        Thread.sleep(s*1000L);
    }

    // TRUE FUNCTION
    public void swipeToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0;i<25;i++) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            js.executeScript("arguments[0].scrollIntoView({behavior: \"auto\", block: \"center\", inline: \"center\"});", element);
        }
    }

    public void swipeJS(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for(int i=0;i<30;i++) {
            js.executeScript("arguments[0].scrollIntoView({behavior: \"smooth\", block: \"center\", inline: \"center\"});", element);
        }
    }



    public void scrollDown() {
        try {
            int i=0;
            for(;i<=30;i++) {
                ((JavascriptExecutor) driver).executeScript(("window.scrollBy(0,"+i+")"), "");
            }
            for(;i>0;i--) {
                ((JavascriptExecutor) driver).executeScript(("window.scrollBy(0,"+i+")"), "");
            }
        } catch (WebDriverException wde) {
        } catch (Exception e) {
        }
    }

    public void scrollUp() {
        try {
            int i=0;
            for(;i>-30;i--) {
                ((JavascriptExecutor) driver).executeScript(("window.scrollBy(0,"+i+")"), "");
            }
            for(;i<0;i++) {
                ((JavascriptExecutor) driver).executeScript(("window.scrollBy(0,"+i+")"), "");
            }
        } catch (WebDriverException wde) {
        } catch (Exception e) {
        }
    }
}
