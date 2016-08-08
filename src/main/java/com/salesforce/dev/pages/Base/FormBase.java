package com.salesforce.dev.pages.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static com.salesforce.dev.framework.selenium.CommonOperation.clickWebElement;

/**
 * This class will be used to represent base form.
 *
 * @author Walter
 * @since 13/06/2015.
 */
public abstract class FormBase extends AbstractBasePage {

    private static final Logger LOGGER = Logger.getLogger(FormBase.class.getName());
    public static final int TIME_SLEEP_MILLIS = 200;

    @FindBy(name = "save")
    protected WebElement saveBtn;

    @FindBy(name = "save_new")
    protected WebElement saveNewBtn;

    @FindBy(name = "cancel")
    protected WebElement cancelBtn;

    //Calendar
    @FindBy(id = "calMonthPicker")
    protected WebElement monthPicker;

    @FindBy(id = "calYearPicker")
    protected WebElement yearPicker;

    /**
     * Method clicks the New button in the home page for each different category
     */
    protected abstract DetailsBase clickSaveBtn();


    protected abstract AbstractBasePage clickCancelBtn();

    /**
     * Methods that encapsulates the the operations waiting for the element and the action
     */
    protected void clickSaveButton() {
        clickWebElement(saveBtn);
    }

    protected void clickSaveNewButton() {
        clickWebElement(saveNewBtn);
    }

    protected void clickCancelButton() {
        clickWebElement(cancelBtn);
    }

    protected void selectDatePicker(Integer month, Integer day, Integer year) {
        this.selectItemComboBox(monthPicker, month.toString());
        this.selectItemComboBox(yearPicker, year.toString());
        WebElement selectDate = driver.findElement(
                By.xpath("//div[@class='calBody']/descendant::td[contains(.,'" + day + "')]"));
        selectDate.click();
    }

    protected void selectItemComboBox(WebElement webElement, String value) {
        if (value == null) {
            throw new IllegalArgumentException("The value cannot be null");
        }
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            Select comboBox = new Select(webElement);
            comboBox.selectByVisibleText(value);
        } catch (WebDriverException e) {
            throw new WebDriverException("The value " + value + " couldn't be selected", e);
        }
    }

    protected void fillTextBox(WebElement webElement, String value) {
        if (value == null) {
            throw new IllegalArgumentException("The value cannot be null.");
        }
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();

        try {
            Thread.sleep(TIME_SLEEP_MILLIS);
        } catch (InterruptedException e) {
            LOGGER.warn("Interrupted!", e);
            Thread.currentThread().interrupt();
        }
        webElement.sendKeys(value);
    }
}
