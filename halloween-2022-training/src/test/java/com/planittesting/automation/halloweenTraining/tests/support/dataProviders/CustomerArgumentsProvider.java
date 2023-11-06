package com.planittesting.automation.halloweenTraining.tests.support.dataProviders;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import com.planittesting.automation.halloweenTraining.model.data.ContactData;
import com.planittesting.automation.halloweenTraining.model.data.LoginData;

public class CustomerArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        // read test data here and convert to java record
        // read a json
        // read data from db
        // read data from API
        // never from Excel

        var contactData = new ContactData("Juan", "Florez", "asda@hjgsd.com", "0435363763", "hello");
        var loginData = new LoginData("jflorez", "letmein");
        var arguments = Arguments.of(contactData, loginData);
        return Stream.of(arguments);
    }
    
}
