package com.planittesting.automation.halloweenTraining.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import com.planittesting.automation.halloweenTraining.model.data.ContactData;
import com.planittesting.automation.halloweenTraining.model.data.LoginData;
import com.planittesting.automation.halloweenTraining.model.pages.HomePage;
import com.planittesting.automation.halloweenTraining.tests.support.dataProviders.CustomerArgumentsProvider;

public class ContactTests extends BaseTests {
    
    @Test
    void validateEmailFormatError() {
        var emailError = open(HomePage.class)
            .clickContactMenu()
            .setEmail("awdfasd")
            .getEmailError();

        assertEquals("Must be a valid email.", emailError);
        
    }

    @Test
    void validateMandatoryErrors() {
        var contactPage = open(HomePage.class)
            .clickContactMenu()
            .clickSubmitButton();
        
        assertEquals("Forename is required.", contactPage.getForenameError());
        assertEquals("Email is required.", contactPage.getEmailError());
        assertEquals("Message is required.", contactPage.getMessageError());
    }

    @Test
    void validateMandatoryErrorsCanBeFixed() {
        var contactPage = open(HomePage.class)
            .clickContactMenu()
            .clickSubmitButton()
            .setForename("juan")
            .setEmail("asad@hsgfd.com")
            .setMessage("Hello world");
        
        assertEquals("", contactPage.getForenameError());
        assertEquals("", contactPage.getEmailError());
        assertEquals("", contactPage.getMessageError());
    }

    @ParameterizedTest
    @ArgumentsSource(CustomerArgumentsProvider.class)
    void validateFormSubmittion(ContactData data, LoginData loginData) {
        var successMessage = open(HomePage.class)
            .clickContactMenu()
            .login(loginData)
            .setContactForm(data)
            .clickSubmitButton()
            .getSuccessMessage();
        assertEquals("Thanks "+data.forename()+", we appreciate your feedback.", successMessage);
    }

}
