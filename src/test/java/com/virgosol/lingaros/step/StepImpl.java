package com.virgosol.lingaros.step;

import com.thoughtworks.gauge.Step;
import com.virgosol.lingaros.helper.ElementHelper;
import com.virgosol.lingaros.helper.StoreHelper;
import com.virgosol.qa.web.core.di.InjectionHelper;
import com.virgosol.qa.web.core.element.Element;
import com.virgosol.qa.web.core.model.Configuration;
import com.virgosol.qa.web.core.wait.WaitingAction;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

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

    @Step({"Click element with key <key>",
            "<key> li elemente tikla"})
    public void clickElement(String key) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(byElement));
        driver.findElement(byElement).click();
    }

    @Step("<key> sn bekle")
    public void bekle(String key) throws InterruptedException {
        Thread.sleep(Integer.parseInt(key) * 1000);
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
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(byElement));
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
            waitingAction.waitUntil(ExpectedConditions.attributeToBe(element,attr,attrtext));
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
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                waitingAction.waitUntil(ExpectedConditions.elementToBeClickable(element));
                element.click();
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <pin> pin değerine tıkla"})
    public void findElementPinWithTextsAndClick(String key, String pin) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        int k = 1;
        for (int i = 0; i < pin.length(); i++){
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
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                waitingAction.waitUntil(ExpectedConditions.elementToBeClickable((elementNextTo)));
                elementNextTo.click();
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementlerinden <text2> değerine eşit olana tıkla"})
    public void findElementWithTextsAndClickWithTextNextTo(String key, String text, String key2, String text2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                List<WebElement> elementsNextTo = element.findElements(ElementHelper.getElementInfoToBy(key2));
                for (WebElement lastElement : elementsNextTo) {
                    if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text2))) || lastElement.getText().contains(text2)) {
                        waitingAction.waitUntil(ExpectedConditions.elementToBeClickable((lastElement)));
                        lastElement.click();
                        break;
                    }
                }
                break;
            }
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementine <text2> değerini yaz"})
    public void findElementWithTextsAndSendKeyNextTo(String key, String text, String key2, String text2) {
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            System.out.println(element.getText());
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                System.out.println(element.getText() + "gİrdİ");
                WebElement elementNextTo = element.findElement(ElementHelper.getElementInfoToBy(key2));
                waitingAction.waitUntil(ExpectedConditions.visibilityOf(elementNextTo));
                elementNextTo.clear();
                if (text2.startsWith("Deger_")) {
                    elementNextTo.sendKeys(StoreHelper.getValue(text2));
                } else {
                    elementNextTo.sendKeys(text2);
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
        try {
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if (!element.isDisplayed()) {
                    Assert.assertTrue(false);
                }
            }
        } catch (Exception e) {
            Assert.assertTrue("Elementler bulunamadı.", false);
        }
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
            if ((text.startsWith("Deger_") && element.getText().replaceAll("\\s+", "").contains(StoreHelper.getValue(text).replaceAll("\\s+", ""))) || element.getText().replaceAll("\\s+", "").contains(text.replaceAll("\\s+", ""))) {
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
        Boolean var = false;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            if ((text.startsWith("Deger_") && element.getText().replaceAll("\\s+", "").contains(StoreHelper.getValue(text).replaceAll("\\s+", ""))) || element.getText().replaceAll("\\s+", "").contains(text.replaceAll("\\s+", ""))) {
                var = true;
            }
        }
        Assert.assertTrue(var);
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

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> li elementlerden <text2> değerine eşit olan var mı?"})
    public void findElementWithTextsAndFindElementWithTextNextTo(String key, String text, String key2, String text2) {
        Boolean var = false;
        By byElement = ElementHelper.getElementInfoToBy(key);
        waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        List<WebElement> elements = driver.findElements(byElement);
        for (WebElement element : elements) {
            System.out.println(element.getText());
            if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                By byElement2 = ElementHelper.getElementInfoToBy(key2);
                List<WebElement> lastElements = element.findElements(byElement2);
                for (WebElement lastElement : lastElements) {
                    System.out.println(lastElement.getText());
                    if ((text2.startsWith("Deger_") && lastElement.getText().contains(StoreHelper.getValue(text2))) || lastElement.getText().contains(text2)) {
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
    public void findElementWithTextAndCheckAttributeTextNextTo(String key, String text, String attr,String attrtext) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.attributeToBe(element,attr,attrtext));
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
        }
    }

    @Step({"<key> li elementlerden <text> değerine eşit olanın yanındaki <key2> elementinin <attr> attributesi <attrtext> mı?"})
    public void findElementWithTextsAndCheckAttributeTextNextTo(String key, String text, String key2, String attr,String attrtext) {
        try {
            Boolean var = false;
            By byElement = ElementHelper.getElementInfoToBy(key);
            waitingAction.waitUntil(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
            List<WebElement> elements = driver.findElements(byElement);
            for (WebElement element : elements) {
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
                    waitingAction.waitUntil(ExpectedConditions.presenceOfNestedElementLocatedBy(element, ElementHelper.getElementInfoToBy(key2)));
                    WebElement childElement = element.findElement(ElementHelper.getElementInfoToBy(key2));
                    waitingAction.waitUntil(ExpectedConditions.attributeToBe(childElement,attr,attrtext));
                    break;
                }
            }
        } catch (Exception e) {
            Assert.assertTrue(false);
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
                if ((text.startsWith("Deger_") && element.getText().contains(StoreHelper.getValue(text))) || element.getText().contains(text)) {
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
            Assert.assertTrue("Sayfanın url i " + url + " değil", false);
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
            driver.switchTo().window(winHandleBefore);
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
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    @Step("frame gir")
    public void framegir() {
        waitingAction.waitUntil(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ms-rtestate-field iframe")));
        driver.switchTo().frame(driver.findElement(By.cssSelector(".ms-rtestate-field iframe")));
    }
}
