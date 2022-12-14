package com.virgosol.hepsijet.step;

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

    @Step("Mesajda tamam butonuna bas??l??r.")
    public void popUpAccept() throws IOException {
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
    }

    @Step("<key> li elementin uzunlu??unun 10 oldu??u do??rulan??r.")
        public void numberOfDataInDatagrid(String key) throws IOException {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        int elementSize = elements.size();
        if(elementSize <= 10)
            System.out.println("Elementin uzunlu??u...:" + elementSize + "dur." );
        else
            Assert.fail("Element uzunlu??u 10'dan b??y??kt??r.");
    }

    @Step("<key> textbox alan??na captcha de??eri yaz??l??r.")
    public void handlingCAPTCHA( String key) throws IOException {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement fileInput = driver.findElement(byElement);
        String captchaVal = JOptionPane.showInputDialog("Please enter the captcha value:");
        fileInput.sendKeys(captchaVal);
    }

    @Step("<key> li element g??z??k??yor mu?")
    public void elementIsDisplayed(String key) {
        By byElement2 = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement2));
        WebElement element = driver.findElement(byElement2);
        Boolean result = element.isDisplayed();
        System.out.println(result);
        if(result!=true){
            Assert.fail("Element sayfada g??r??n??r de??il.");
        }
    }

    @Step("Ekran ????z??n??rl??????n?? <screenResolution> yap.")
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

    @Step("<key1> tarihi ile <key2> tarihini kar????la??t??r.")
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
            System.out.println("1. Tarih, 2. Tarihten ??nce gelmektedir.");
        } else if (date1.compareTo(date2) == 0 || date1.compareTo(date2) > 0) {
            Assert.fail("1. Tarih, 2. Tarihten sonra gelmektedir.");
        }
    }

    @Step("<browserType> taray??c??n?? a??")
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
    }

    @Step("<key> dakika bekle")
    public void waitMinutes(String key) throws InterruptedException {
        Thread.sleep(Integer.parseInt(key) * 1000 * 60);
    }

    @Step("<key> li elementi g??r??n??r olana kadar bekle")
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

    @Step({"<key> li elementin ??zerine gel"})
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
              System.out.println("Tarihler e??it.");
      }
      else
          Assert.fail("Tarihler e??it de??il.");
    }


    @Step({"<key> li elementin <attr> attributesini <key2> olarak kaydet"})
    public void saveAttribute(String key, String attr, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(element, attr));
        StoreHelper.saveValue(key2, element.getAttribute(attr));
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olana t??kla"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olana <text2> de??erini yaz"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan?? se??"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n ??zerine gel"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n <attr> attributesini <text2> yap"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n textini <key2> olarak kaydet"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n <attr> attributesini <key2> olarak kaydet"})
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

    @Step({"<key> li elementin yan??ndaki <key2> elementine tikla"})
    public void clickElementNextTo(String key, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(elementNextTo));
        elementNextTo.click();
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementine <text> degerini yaz"})
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

    @Step({"<key> li elementin yan??ndaki <key2> elementinin ??zerine gel"})
    public void hoverElementNextTo(String key, String key2) {
        Actions action = new Actions(driver);
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
        action.moveToElement(elementNextTo).perform();
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementinin <attr> attributesini <text> yap"})
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

    @Step({"<key> li elementin yan??ndaki <key2> elementinin textini <key3> olarak kaydet"})
    public void saveTextNextTo(String key, String key2, String key3) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        StoreHelper.saveValue(key3, elementNextTo.getText());
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementinin <attr> attributesini <key3> olarak kaydet"})
    public void saveAttributeNextTo(String key, String key2, String attr, String key3) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
        waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(elementNextTo, attr));
        StoreHelper.saveValue(key3, elementNextTo.getAttribute(attr));
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementine t??kla"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementlerinden indexe g??re t??kla"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementlerinden <text2> de??erine e??it olana t??kla"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementine <text2> de??erini yaz"})
    public void findElementWithTextsAndSendKeyNextTo(String key, String text, String key2, String text2) throws InterruptedException {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            //System.out.println(element.getText());
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().equals(text)) {
                //System.out.println(element.getText() + "g??rd??");
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan ile ayn?? s??ralamada yan??ndaki <key2> elementine <text2> de??erini yaz"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan ile ayn?? s??ralamada yan??ndaki <key2> elementine t??kla"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan ile ayn?? s??ralamada alt??ndaki <key2> verileri <text2> olarak s??ral?? m??"})
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
                    if (text2 == "e??it") {
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementini se??"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin ??zerine gel"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin <attr> attributesini <text2> yap"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin textini <key3> olarak kaydet"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin <attr> attributesini <key3> olarak kaydet"})
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

    @Step({"<key> li elementler var m???"})
    public void checkElements(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        if (elements.isEmpty())
            Assert.fail("Elementler bulunamad??.");
    }

    @Step({"<key> li element var m???"})
    public void checkElement(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        } catch (Exception e) {
            Assert.assertTrue("Element bulunamad??.", false);
        }
    }

    @Step({"<key> li element g??r??n??r m???"})
    public void checkElementVisible(String key) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElement));
            WebElement element = driver.findElement(byElement);
            if (!element.isDisplayed())
                Assert.fail("Element g??r??n??r de??il.");
        } catch (Exception e) {
            Assert.assertTrue("Element g??r??n??r de??il.", false);
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan yok mu?"})
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

    @Step({"<key> li elementlerin texti var m???"})
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

    @Step({"<key> li elementin texti var m???"})
    public void checkText(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        if (element.getText() == null) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementin <attr> attributesi var m???"})
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

    @Step({"<key> li elementlerin <attr> attributesi var m???"})
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

    @Step({"<key> li element t??klanabilir mi?"})
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

    @Step({"<key> li elementin texti <text> i??eriyor mu?"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan var m???"})
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
        Assert.fail(text + " de??erine e??it element bulunamad??.");
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n <attr> attributesi var m???"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan t??klanabilir mi?"})
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
            Assert.assertTrue("Element t??klan??labilir de??il.", false);
        }
    }

    @Step({"<key> li elementin yan??ndaki <key2> element var m???"})
    public void checkElementNextTo(String key, String key2) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
        } catch (Exception e) {
            Assert.assertTrue("Yan??ndaki element yok.", false);
        }
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementinin texti var m???"})
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
            Assert.assertTrue("Yan??ndaki elementin texti yok.", false);
        }
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementinin <attr> attributesi var m???"})
    public void checkAttributeNextTo(String key, String key2, String attr) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            waitingAction.waitUntil(ExpectedConditions.attributeToBeNotEmpty(childElement, attr));
        } catch (Exception e) {
            Assert.assertTrue("Yan??ndaki elementin attributesi yok.", false);
        }
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementi t??klanabilir mi?"})
    public void checkClickableNextTo(String key, String key2) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(childElement));
        } catch (Exception e) {
            Assert.assertTrue("Yan??ndaki element t??klan??labilir de??il.", false);
        }
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementinin texti <text> mi?"})
    public void checkTextEqualsNextTo(String key, String key2, String text) {
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
            WebElement element = driver.findElement(byElement);
            waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
            WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
            waitingAction.waitUntil(ExpectedConditions.textToBePresentInElement(childElement, text));
        } catch (Exception e) {
            Assert.assertTrue("Yan??ndaki elementin texti " + text + " de??il.", false);
        }
    }

    @Step({"<key> li elementin yan??ndaki <key2> elementinin texti <text> i??eriyor mi?"})
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
            Assert.assertTrue("Yan??ndaki elementin texti " + text + " de??erini i??ermiyor.", false);
        }
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan elementin <attr> attributesi <attrtext> i??eriyor mu?"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> element var m???"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementi yok mu?"})
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

    @Step({"<key> li elementlerden texti olan??n yan??ndaki <key2> elementi var m???"})
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

    @Step({"<key> li elementlerden texti olan??n yan??ndaki <key2> elementin texti var m???"})
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
                        System.out.println("g??rd??");
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


    @Step({"<key> li elementlerden yan??ndaki <key2> elementinin <attr> attributesi <attrtext> i??erenin yan??ndaki <key3> elementine t??kla"})
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
        Assert.fail("Elementlerin hepsine t??klan??ld??.");
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> li elementlerden <text2> de??erine e??it olan var m???"})
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
            Assert.fail(text + " textli element bulunamad??.");
        else if (check1 == false && check2 == false)
            Assert.fail(text2 + " textli element bulunamad??.");
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> li elementler <text2> de??erine e??it mi?"})
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
                        if (lastElements.get(i).getText().contains(text2)) // equalsti fakat rowda ba??ka i??erikte vard?? // Guest Name
                        {
                            if (i == size - 1) break;
                            i++;
                        } else Assert.fail(text2 + " li elementlerin hepsi e??it de??il.");
                    }
                    break;
                }
            }
            if (var == false) {
                Assert.fail(text + " li element bulunamad??.");
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
                        } else Assert.fail(text2 + " li elementlerin hepsi e??it de??il.");
                    }
                    break;
                }
            }
            if (var == false) {
                Assert.fail(text + " li element bulunamad??.");
            }
        }
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> li elementlerden <text2> de??erine e??it olan??n yan??ndaki <key3> elementinin texti <text3> m???"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> li elementlerden <text2> de??erine e??it olan??n yan??ndaki <key3> elementinin textini <saveKey> olarak kaydet"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> li elementlerden <text2> de??erine e??it olan??n yan??ndaki <key3> elementine t??kla"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin <attr> attributesi var m???"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan elementin <attr> attributesi <attrtext> m???"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin <attr> attributesi <attrtext> i??eriyor mu?"})
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
                System.out.println("G??nderilen attr: " + attrtext);
                if (childElement.getAttribute(attr).contains(attrtext))
                    return;
            }
        }
        Assert.fail(text + " li element de??er i??ermiyor veya yan??ndaki elementin " + attr + " attributesine ait de??er bulunamad??.");
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin <attr> attributesi <attrtext> m???"})
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
                System.out.println("G??nderilen attr: " + attrtext);
                if (childElement.getAttribute(attr).equals(attrtext))
                    return;
            }
        }
        Assert.fail(text + " li element de??er i??ermiyor veya yan??ndaki elementin " + attr + " attributesine ait de??er bulunamad??.");
    }

    private String startDate = "";
    private String endDate = "";

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinin <attr> attributesini kaydet"})
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> li elementlerin textindeki tarihler do??ru aral??kta m???"})
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
                            Assert.fail("Tarih s??ralamas?? yanl????t??r.");
                            //System.out.println("Date1 is equal to Date2");
                        }
                        //pageHeaderEndDate = sdf.parse(endDate);

                        /*int result = tableRowStartDate.compareTo(pageHeaderStartDate);
                        int result2 = tableRowStartDate.compareTo(pageHeaderEndDate);
                        System.out.println("result: " + result);
                        System.out.println("result2: " + result2);

                        if (!((result == 0 || result > 0) && (result2 == 0 || result2 < 0)) || !(result2 > 0)) {
                            Assert.fail("Tarih s??ralamas?? yanl????t??r.");
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
                Assert.fail(text + " li element bulunamad??.");
            }
        }
    }


    @Step({"<key> li elementlerden <text> de??erine e??it olan elementten ??nceki <key2> elementlerin tarihi <attr> attributesi <attrtext> i??eriyor mu?"})
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
                    Assert.fail(text + " li elementlerden ??nceki elementlerin " + attr + " attributesi " + attrtext + " i??ermiyor.");
                if (i == size - 1) break;
                i++;
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementi t??klanabilir mi?"})
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

    @Step({"Sayfan??n title ?? <title> m???"})
    public void checkTitle(String title) {
        try {
            waitingAction.waitUntil(ExpectedConditions.titleIs(title));

        } catch (Exception e) {
            Assert.assertTrue("Sayfan??n title ?? " + title + " de??il", false);
        }
    }

    @Step({"Sayfan??n url i <url> m???"})
    public void checkUrl(String url) {
        try {
            waitingAction.waitUntil(ExpectedConditions.urlToBe(url));

        } catch (Exception e) {
            Assert.assertTrue("Sayfan??n url i " + url + " de??il. Url: " + driver.getCurrentUrl(), false);
        }
    }

    @Step({"Yeni a????lan sayfaya ge??"})
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

    @Step({"<frame> isimli frame e ge??"})
    public void navigateFrame(String frame) {
        try {
            driver.switchTo().frame(frame);
        } catch (Exception e) {
            System.out.println(e);
            Assert.assertTrue(false);

        }
    }

    @Step({"Frameden ana sayfaya ge??"})
    public void navigateFrame() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"Bulundu??un sayfay?? kapat"})
    public void closePage() {
        try {
            driver.close();
           // driver.switchTo().window(winHandleBefore);
           // driver.navigate().to("http://uatb2b.odeontours.com/");
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }


    @Step({"Bulundu??un sayfada geri git"})
    public void backPage() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"Bulundu??un sayfada ileri git"})
    public void forwardPage() {
        try {
            driver.navigate().forward();
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"Bulundu??un sayfay?? yenile"})
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

    @Step("<key> li elementi temizle")
    public void deleteText2(String key) {
        String s = "";
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        if (System.getProperty("os.name").toLowerCase().contains("Mac".toLowerCase())){
            s = Keys.chord(Keys.COMMAND, "a");
            element.sendKeys(s);
            element.sendKeys(Keys.DELETE);
        } if (System.getProperty("os.name").toLowerCase().contains("Windows".toLowerCase())
                || element.getText() != "") {
            element.sendKeys(Keys.CONTROL, "a");
            element.sendKeys(Keys.DELETE);
        }
    }

    @Step("<key> Entere t??kla")
    public void clickEnterWithElement(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        element.sendKeys(Keys.ENTER);
    }

    @Step("Entere t??kla")
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

    @Step({"<key> li elementlerden <text> de??erine e??it olan??n yan??ndaki <key2> elementinde Entere Tikla"})
    public void findElementWithTextsAndSendKeyNextToEnter(String key, String text, String key2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            //System.out.println(element.getText());
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                //System.out.println(element.getText() + "g??rd??");
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                //waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
                elementNextTo.sendKeys(Keys.ENTER);
                break;
            }
        }
    }

    @Step({"<key> li elementi s??r??kle ve b??rak"})
    public void dragAndDropColumn(String key) {
        Actions action = new Actions(driver);
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.visibilityOfElementLocated(byElement));
        WebElement element = driver.findElement(byElement);
        action.moveToElement(element).perform();
        action.dragAndDropBy(element, 150, 0).perform();
    }

    @Step({"Excel <excelFileName> dosyas??n??n do??ru uzant??da oldu??u kontrol edilir."})
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

            excel = new ExcelHelper("<fileRootPath>"+fileNamePath);
            String row0 = excel.getData(0,0,0);
            System.out.println("Data from Excel is: "+row0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        excel.deleteExcel();
    }

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
