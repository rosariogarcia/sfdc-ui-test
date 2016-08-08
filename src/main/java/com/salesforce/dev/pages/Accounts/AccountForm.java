package com.salesforce.dev.pages.accounts;


import java.util.HashMap;
import java.util.Map;

import com.salesforce.dev.framework.utils.IAutomationStep;
import com.salesforce.dev.pages.base.AbstractBasePage;
import com.salesforce.dev.pages.base.FormBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_ACTIVE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_ANNUAL_REVENUE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_BILLING_STREET;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_CUSTOMER_PRIORITY;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_DESCRIPTION;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_EMPLOYEES;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_FAX;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_INDUSTRY;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_NAME;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_NUMBER;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_NUMBER_LOCATIONS;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_OWNER_SHIP;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_PHONE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_RATING;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SHIPPING_STREET;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SICCODE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SITE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SLA;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SLASERIAL_NUMBER;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SLA_EXPIRATION_DATE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_THICKER;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_TYPE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_UPSELL_OPPORTUNITY;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_WEBSITE;


/**
 * This class will be used to represent Account form.
 * @author Walter.
 * @since 10/06/2015.
 */
public class AccountForm extends FormBase {

    //Account Information

    @FindBy(id = "acc2")
    private WebElement accountNameFld;

    @FindBy(id = "acc3")
    private WebElement parentAccountFld;

    @FindBy(id = "acc5")
    private WebElement accountNumberFld;

    @FindBy(id = "acc23")
    private WebElement accountSiteFld;

    @FindBy(id = "acc6")
    private WebElement accountTypeFld;

    @FindBy(id = "acc7")
    private WebElement accountIndustryFld;

    @FindBy(id = "acc8")
    private WebElement accountAnnualRevenueFld;

    @FindBy(id = "acc9")
    private WebElement accountRatingFld;

    @FindBy(id = "acc10")
    private WebElement accountPhoneFld;

    @FindBy(id = "acc11")
    private WebElement accountFaxFld;

    @FindBy(id = "acc12")
    private WebElement accountWebsiteFld;

    @FindBy(id = "acc13")
    private WebElement accountThickerFld;

    @FindBy(id = "acc14")
    private WebElement accountOwnershipFld;

    @FindBy(id = "acc15")
    private WebElement accountEmployeesFld;

    @FindBy(id = "acc16")
    private WebElement accountSICCodeFld;

    //AddressAddress Information

    @FindBy(id = "acc17street")
    private WebElement accountBillingStreetFld;

    @FindBy(id = "acc17city")
    private WebElement accountBillingCityFld;

    @FindBy(id = "acc17state")
    private WebElement accountBillingStateFld;

    @FindBy(id = "acc17zip")
    private WebElement accountBillingZipFld;

    @FindBy(id = "acc17country")
    private WebElement accountBillingCountryFld;

    @FindBy(id = "acc18street")
    private WebElement accountShippingStreetFld;

    @FindBy(id = "acc18city")
    private WebElement accountShippingCityFld;

    @FindBy(id = "acc18state")
    private WebElement accountShippingStateFld;

    @FindBy(id = "acc18zip")
    private WebElement accountShippingZipFld;

    @FindBy(id = "acc18country")
    private WebElement accountShippingCountryFld;

    // Additional Information

    @FindBy(xpath = "//label[contains(.,'Customer Priority')]/following::select")
    private WebElement accountCustomerPriorityFld;

    @FindBy(xpath = "//label[contains(.,'SLA Expiration Date')]/following::input")
    private WebElement accountSLAExpirationDateFld;

    @FindBy(xpath = "//label[contains(.,'Number of Locations')]/following::input")
    private WebElement accountNumberLocationsFld;

    @FindBy(xpath = "//label[contains(.,'Active')]/following::select")
    private WebElement accountActiveFld;

    @FindBy(xpath = "//label[contains(.,'SLA')]/following::select")
    private WebElement accountSLAFld;

    @FindBy(xpath = "//label[contains(.,'SLA Serial Number')]/following::input")
    private WebElement accountSLASerialNumberFld;

    @FindBy(xpath = "//label[contains(.,'Upsell Opportunity')]/following::select")
    private WebElement accountUpsellOpportunityFld;

    //Description Information

    @FindBy(name = "acc20")
    private WebElement accountDescriptionFld;

    @Override
    public AbstractBasePage clickCancelBtn() {
        return null;
    }

    @Override
    public AccountDetail clickSaveBtn() {
        clickSaveButton();
        return new AccountDetail();
    }

    public AccountForm setAccountNameFld(String accountName) {
        fillTextBox(accountNameFld, accountName);
        return this;
    }

    public AccountForm setAccountNumberFld(String accountNumber) {
        fillTextBox(accountNumberFld, accountNumber);
        return this;
    }

    public AccountForm setAccountSiteFld(String accountSite) {
        fillTextBox(accountSiteFld, accountSite);
        return this;
    }

    public AccountForm setAccountTypeFld(String accountType) {
        selectItemComboBox(accountTypeFld, accountType);
        return this;
    }

    public AccountForm setAccountIndustryFld(String accountIndustry) {
        selectItemComboBox(accountIndustryFld, accountIndustry);
        return this;
    }

    public AccountForm setAccountAnnualRevenueFld(Integer accountAnnualRevenue) {
        fillTextBox(accountAnnualRevenueFld, accountAnnualRevenue.toString());
        return this;
    }

    public AccountForm setAccountRatingFld(String accountRating) {
        selectItemComboBox(accountRatingFld, accountRating);
        return this;
    }

    public AccountForm setAccountPhoneFld(String accountPhone) {
        fillTextBox(accountPhoneFld, accountPhone);
        return this;
    }

    public AccountForm setAccountFaxFld(String accountFax) {
        fillTextBox(accountFaxFld, accountFax);
        return this;
    }

    public AccountForm setAccountWebsiteFld(String accountWebsite) {
        fillTextBox(accountWebsiteFld, accountWebsite);
        return this;
    }

    public AccountForm setAccountThickerFld(String accountThicker) {
        fillTextBox(accountThickerFld, accountThicker);
        return this;
    }

    public AccountForm setAccountOwnershipFld(String accountOwnership) {
        selectItemComboBox(accountOwnershipFld, accountOwnership);
        return this;
    }

    public AccountForm setAccountEmployeesFld(Integer accountEmployees) {
        fillTextBox(accountEmployeesFld, accountEmployees.toString());
        return this;
    }

    public AccountForm setAccountSICCodeFld(String accountSICCode) {
        fillTextBox(accountSICCodeFld, accountSICCode);
        return this;
    }

    public AccountForm setAccountBillingStreetFld(String accountBillingStreet) {
        fillTextBox(accountBillingStreetFld, accountBillingStreet);
        return this;
    }

    public AccountForm setAccountBillingCityFld(String accountBillingCity) {
        fillTextBox(accountBillingCityFld, accountBillingCity);
        return this;
    }

    public AccountForm setAccountBillingStateFld(String accountBillingState) {
        fillTextBox(accountBillingStateFld, accountBillingState);
        return this;
    }

    public AccountForm setAccountBillingZipFld(String accountBillingZip) {
        fillTextBox(accountBillingZipFld, accountBillingZip);
        return this;
    }

    public AccountForm setAccountBillingCountryFld(String accountBillingCountry) {
        fillTextBox(accountBillingCountryFld, accountBillingCountry);
        return this;
    }

    public AccountForm setAccountShippingStreetFld(String accountShippingStreet) {
        fillTextBox(accountShippingStreetFld, accountShippingStreet);
        return this;
    }

    public AccountForm setAccountShippingCityFld(String accountShippingCity) {
        fillTextBox(accountShippingCityFld, accountShippingCity);
        return this;
    }

    public AccountForm setAccountShippingStateFld(String accountShippingState) {
        fillTextBox(accountShippingStateFld, accountShippingState);
        return this;
    }

    public AccountForm setAccountShippingZipFld(String accountShippingZip) {
        fillTextBox(accountShippingZipFld, accountShippingZip);
        return this;
    }

    public AccountForm setAccountShippingCountryFld(String accountShippingCountry) {
        fillTextBox(accountShippingCountryFld, accountShippingCountry);
        return this;
    }

    public AccountForm setAccountCustomerPriorityFld(String accountCustomerPriority) {
        selectItemComboBox(accountCustomerPriorityFld, accountCustomerPriority);
        return this;
    }

    public AccountForm setAccountSLAExpirationDateFld(String accountSLAExpirationDate) {
        fillTextBox(accountSLAExpirationDateFld, accountSLAExpirationDate);
        return this;
    }

    public AccountForm setAccountSLAExpirationDateFld(Integer month, Integer day, Integer year) {
        accountSLAExpirationDateFld.click();
        selectDatePicker(month, day, year);
        return this;
    }

    public AccountForm setAccountNumberLocationsFld(Integer accountNumberLocations) {
        fillTextBox(accountNumberLocationsFld, accountNumberLocations.toString());
        return this;
    }

    public AccountForm setAccountActiveFld(String activeState) {
        selectItemComboBox(accountActiveFld, activeState);
        return this;
    }

    public AccountForm setAccountSLAFld(String accountSLA) {
        selectItemComboBox(accountSLAFld, accountSLA);
        return this;
    }

    public AccountForm setAccountSLASerialNumberFld(String accountSLASerialNumber) {
        fillTextBox(accountSLASerialNumberFld, accountSLASerialNumber);
        return this;
    }

    public AccountForm setAccountUpsellOpportunityFld(String accountUpSellOpportunity) {
        selectItemComboBox(accountUpsellOpportunityFld, accountUpSellOpportunity);
        return this;
    }

    public AccountForm setAccountDescriptionFld(String accountDescription) {
        fillTextBox(accountDescriptionFld, accountDescription);
        return this;
    }

    public Map<AccountSteps, IAutomationStep> getStrategyStepMap(Map<AccountSteps, Object> values) {
        Map<AccountSteps, IAutomationStep> strategyMap = new HashMap<>();
        strategyMap.put(ACCOUNT_NAME, () -> setAccountNameFld((String.valueOf(values.get(ACCOUNT_NAME)))));
        strategyMap.put(ACCOUNT_NUMBER, () -> setAccountNumberFld((values.get(ACCOUNT_NUMBER).toString())));
        strategyMap.put(ACCOUNT_SITE, () -> setAccountSiteFld((values.get(ACCOUNT_SITE).toString())));
        strategyMap.put(ACCOUNT_TYPE, () -> setAccountTypeFld((values.get(ACCOUNT_TYPE).toString())));
        strategyMap.put(ACCOUNT_INDUSTRY, () -> setAccountIndustryFld((values.get(ACCOUNT_INDUSTRY).toString())));
        strategyMap.put(ACCOUNT_ANNUAL_REVENUE, () -> setAccountAnnualRevenueFld(Integer.valueOf(values.get(ACCOUNT_ANNUAL_REVENUE).toString())));
        strategyMap.put(ACCOUNT_RATING, () -> setAccountRatingFld((values.get(ACCOUNT_RATING).toString())));
        strategyMap.put(ACCOUNT_PHONE, () -> setAccountPhoneFld((values.get(ACCOUNT_PHONE).toString())));
        strategyMap.put(ACCOUNT_FAX, () -> setAccountFaxFld((values.get(ACCOUNT_FAX).toString())));
        strategyMap.put(ACCOUNT_WEBSITE, () -> setAccountWebsiteFld((values.get(ACCOUNT_WEBSITE).toString())));
        strategyMap.put(ACCOUNT_THICKER, () -> setAccountThickerFld((values.get(ACCOUNT_THICKER).toString())));
        strategyMap.put(ACCOUNT_OWNER_SHIP, () -> setAccountOwnershipFld((values.get(ACCOUNT_OWNER_SHIP).toString())));
        strategyMap.put(ACCOUNT_EMPLOYEES, () -> setAccountEmployeesFld(Integer.valueOf(values.get(ACCOUNT_EMPLOYEES).toString())));
        strategyMap.put(ACCOUNT_SICCODE, () -> setAccountSICCodeFld((values.get(ACCOUNT_SICCODE).toString())));
        strategyMap.put(ACCOUNT_BILLING_STREET, () -> setAccountBillingStreetFld((values.get(ACCOUNT_BILLING_STREET).toString())));
        strategyMap.put(ACCOUNT_SHIPPING_STREET, () -> setAccountShippingStreetFld((values.get(ACCOUNT_SHIPPING_STREET).toString())));
        strategyMap.put(ACCOUNT_CUSTOMER_PRIORITY, () -> setAccountCustomerPriorityFld((values.get(ACCOUNT_CUSTOMER_PRIORITY).toString())));
        strategyMap.put(ACCOUNT_SLA_EXPIRATION_DATE, () -> setAccountSLAExpirationDateFld((values.get(ACCOUNT_SLA_EXPIRATION_DATE).toString())));
        strategyMap.put(ACCOUNT_NUMBER_LOCATIONS, () -> setAccountNumberLocationsFld(Integer.valueOf(values.get(ACCOUNT_NUMBER_LOCATIONS).toString())));
        strategyMap.put(ACCOUNT_ACTIVE, () -> setAccountActiveFld((values.get(ACCOUNT_ACTIVE).toString())));
        strategyMap.put(ACCOUNT_SLA, () -> setAccountSLAFld((values.get(ACCOUNT_SLA).toString())));
        strategyMap.put(ACCOUNT_SLASERIAL_NUMBER, () -> setAccountSLASerialNumberFld((values.get(ACCOUNT_SLASERIAL_NUMBER).toString())));
        strategyMap.put(ACCOUNT_UPSELL_OPPORTUNITY, () -> setAccountUpsellOpportunityFld((String.valueOf(values.get(ACCOUNT_UPSELL_OPPORTUNITY)))));
        strategyMap.put(ACCOUNT_DESCRIPTION, () -> setAccountDescriptionFld((values.get(ACCOUNT_DESCRIPTION).toString())));
        return strategyMap;
    }
}
