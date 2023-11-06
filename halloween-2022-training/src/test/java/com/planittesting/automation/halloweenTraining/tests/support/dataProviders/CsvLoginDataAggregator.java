package com.planittesting.automation.halloweenTraining.tests.support.dataProviders;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.planittesting.automation.halloweenTraining.model.data.LoginData;

public class CsvLoginDataAggregator implements ArgumentsAggregator {

    @Override
    public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context)
            throws ArgumentsAggregationException {
        return new LoginData(
            accessor.getString(0),
            accessor.getString(1)
        );
    }
    
}
