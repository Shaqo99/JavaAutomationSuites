package com.planittesting.automation.halloweenTraining.model.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.planittesting.automation.halloweenTraining.model.data.ContactData;

public class ContactPage extends BasePage<ContactPage> {

    public ContactPage(WebDriver driver) {
        super(driver);
    }

    public ContactPage setForename(String forename) {
        driver.findElement(By.id("forename")).sendKeys(forename);
        return this;
    }

    public ContactPage setSurname(String surname) {
        driver.findElement(By.id("surname")).sendKeys(surname);
        return this;
    }

    public ContactPage setEmail(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
        return this;
    }

    public ContactPage setTelephone(String telephone) {
        driver.findElement(By.id("telephone")).sendKeys(telephone);
        return this;
    }

    public ContactPage setMessage(String message) {
        driver.findElement(By.id("message")).sendKeys(message);
        return this;
    }

    public ContactPage clickSubmitButton() {
        driver.findElement(By.id("submit-button")).click();
        return this;
    }

    public String getForenameError() {
        return getErrorText(By.id("forename-err"));
    }

    public String getEmailError() {
        return getErrorText(By.id("email-err"));
    }

    public String getMessageError() {
        return getErrorText(By.id("message-err"));
    }

    private String getErrorText(By locator) {
        var elements = driver.findElements(locator);
        if (elements.isEmpty())
            return "";
        return elements.get(0).getText();
    }

    public String getSuccessMessage() {
        return new WebDriverWait(driver, Duration.ofSeconds(120))
            .until(d -> d.findElement(By.className("success-message")))
            .getText();
    }

    public ContactPage setContactForm(ContactData data) {
        return setForename(data.forename())
            .setSurname(data.surname())
            .setEmail(data.email())
            .setTelephone(data.telephone())
            .setMessage(data.message());
    }
}
