package com.planittesting.automation.halloweenTraining.tests.support.dataProviders;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.params.aggregator.AggregateWith;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@AggregateWith(CsvLoginDataAggregator.class)
public @interface CsvToLoginData {
    
}
