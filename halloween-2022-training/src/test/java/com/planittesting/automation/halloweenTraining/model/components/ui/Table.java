package com.planittesting.automation.halloweenTraining.model.components.ui;

import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Table {
    private WebElement rootElement;

    public Table(WebElement rootElement) {
        this.rootElement = rootElement;
    }

    public WebElement getCell(String searchColumn, String searchValue, String returnColumn) {
        var searchColumnIndex = getColumnIndex(searchColumn);
        var returnColumnIndex = getColumnIndex(returnColumn);

        var rows = rootElement.findElements(By.cssSelector("tbody tr"));
        for(var row: rows) {
            var cells = row.findElements(By.tagName("td"));
            if(cells.get(searchColumnIndex).getText().equals(searchValue)) {
                return cells.get(returnColumnIndex);
            }
        }
        throw new RuntimeException("Value for serachColumn: '"+searchColumn+"', returnColumn: '"+returnColumn+"', searchValue: '"+searchValue+"' not found");
    }

    private int getColumnIndex(String column) {
        var columns = rootElement.findElements(By.tagName("th"));
        return IntStream.range(0, columns.size())
            .filter(i -> columns.get(i).getText().equals(column))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Column '"+column+"'' not found in the table"));
    }
}
