package com.planittesting.automation.halloweenTraining.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.planittesting.automation.halloweenTraining.model.data.LoginData;
import com.planittesting.automation.halloweenTraining.model.pages.HomePage;
import com.planittesting.automation.halloweenTraining.tests.support.dataProviders.CsvToLoginData;

public class LoginTests extends BaseTests {
    
    @Test
    void validateSuccessfulLogin() {
        var user = open(HomePage.class)
            .clickContactMenu()
            .clickLoginMenu()
            .setUsername("juan")
            .setPassword("letmein")
            .clickAgreeTermsCheckbox()
            .clickLoginButton()
            .getUser();
        assertEquals("juan", user);
    }

    @ParameterizedTest
    @CsvSource({ 
		"juan,letmein"
    })
    void validateSuccessfulLogout(@CsvToLoginData LoginData data) {
        var user = open(HomePage.class)
            .clickContactMenu()
            .login(data)
            .clickLogoutMenu()
            .clickLogoutButton()
            .getUser();
        assertEquals("", user);
    }

}
