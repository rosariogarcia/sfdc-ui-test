package com.salesforce.dev.pages.accounts;

import java.util.Map;
import java.util.HashMap;

import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.HomeBase;
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
 * This class will be used to represent Account details.
 * @author Walter
 */
public class AccountDetail extends DetailsBase {

    @FindBy(id = "acc2_ilecell")
    private WebElement accountNameFld;

    @FindBy(id = "acc5_ileinner")
    private WebElement accountNumberFld;

    @FindBy(id = "acc23_ileinner")
    private WebElement accountSiteFld;

    @FindBy(id = "acc6_ileinner")
    private WebElement accountTypeFld;

    @FindBy(id = "acc7_ileinner")
    private WebElement accountIndustryFld;

    @FindBy(id = "acc8_ileinner")
    private WebElement accountAnnualRevenueFld;

    @FindBy(id = "acc17_ileinner")
    private WebElement accountBillingAddressFld;

    @FindBy(xpath = "//td[contains(.,'Customer Priority')]/following::div")
    private WebElement accountCustomPriorityFld;

    @FindBy(xpath = "//td[contains(.,'SLA Expiration Date')]/following::div")
    private WebElement accountSLAExpirationDateFld;

    @FindBy(xpath = "//td[contains(.,'Number of Locations')]/following::div")
    private WebElement accountNumberOfLocationsFld;

    @FindBy(xpath = "//td[contains(.,'Active')]/following::div")
    private WebElement accountActiveFld;

    @FindBy(id = "acc20_ileinner")
    private WebElement accountDescriptionFld;

    @FindBy(id = "acc9_ilecell")
    private WebElement accountRatingFld;

    @FindBy(id = "acc10_ileinner")
    private WebElement accountPhoneFld;

    @FindBy(id = "acc11_ilecell")
    private WebElement accountFaxFld;

    @FindBy(id = "acc12_ilecell")
    private WebElement accountWebsiteFld;

    @FindBy(id = "acc13_ilecell")
    private WebElement accountTickerSymbolFld;

    @FindBy(id = "acc14_ileinner")
    private WebElement accountOwnershipFld;

    @FindBy(id = "acc15_ileinner")
    private WebElement accountEmployeesFld;

    @FindBy(id = "acc16_ileinner")
    private WebElement accountSICCodeFld;

    @FindBy(id = "acc18_ileinner")
    private WebElement accountShippingAddressFld;

    @FindBy(xpath = "//td[contains(.,'SLA')]/following::div")
    private WebElement accountSLAFld;

    @FindBy(xpath = "//td[contains(.,'SLA Serial Number')]/following::div")
    private WebElement accountSerialNumberFld;

    @FindBy(xpath = "//td[contains(.,'Upsell Opportunity')]/following::div")
    private WebElement accountUpSellOpportunityFld;

    @Override
    public AccountForm clickEditBtn() {
        clickEditButton();
        return new AccountForm();
    }


    @Override
    public HomeBase clickDeleteButton() {
        clickDeletedButton();
        return new AccountsHome();
    }

    public String getAccountNameFld() {
        return accountNameFld.getText().substring(0, accountNameFld.getText().length() - 17);
    }

    public String getAccountNumberFld() {
        return accountNumberFld.getText();
    }

    public String getAccountSiteFld() {
        return accountSiteFld.getText();
    }

    public String getAccountTypeFld() {
        return accountTypeFld.getText();
    }

    public String getAccountIndustryFld() {
        return accountIndustryFld.getText();
    }

    public String getAccountAnnualRevenueFld() {
        String realValue = accountAnnualRevenueFld.getText().replaceAll("[-+.^:,]", "");
        return realValue.substring(1);
    }

    public String getAccountBillingAddressFld() {
        return accountBillingAddressFld.getText();
    }

    public String getAccountCustomPriorityFld() {
        return accountCustomPriorityFld.getText();
    }

    public String getAccountSLAExpirationDateFld() {
        return accountSLAExpirationDateFld.getText();
    }

    public String getAccountNumberOfLocationsFld() {
        return accountNumberOfLocationsFld.getText();
    }

    public String getAccountActiveFld() {
        return accountActiveFld.getText();
    }

    public String getAccountDescriptionFld() {
        return accountDescriptionFld.getText();
    }

    public String getAccountRatingFld() {
        return accountRatingFld.getText();
    }

    public String getAccountPhoneFld() {
        return accountPhoneFld.getText();
    }

    public String getAccountFaxFld() {
        return accountFaxFld.getText();
    }

    public String getAccountWebsiteFld() {
        return accountWebsiteFld.getText();
    }

    public String getAccountTickerSymbolFld() {
        return accountTickerSymbolFld.getText();
    }

    public String getAccountOwnershipFld() {
        return accountOwnershipFld.getText();
    }

    public String getAccountEmployeesFld() {
        return accountEmployeesFld.getText().replaceAll("[-+.^:,]", "");

    }

    public String getAccountSICCodeFld() {
        return accountSICCodeFld.getText();
    }

    public String getAccountShippingAddressFld() {
        return accountShippingAddressFld.getText();
    }

    public String getAccountSLAFld() {
        return accountSLAFld.getText();
    }

    public String getAccountSerialNumberFld() {
        return accountSerialNumberFld.getText();
    }

    public String getAccountUpSellOpportunityFld() {
        return accountUpSellOpportunityFld.getText();
    }
    @Override
    public Map<Enum, Object> getAssertionMap() {
        Map<Enum, Object> assertionMap = new HashMap<>();
        assertionMap.put(ACCOUNT_NAME, getAccountNameFld());
        assertionMap.put(ACCOUNT_RATING, getAccountRatingFld());
        assertionMap.put(ACCOUNT_OWNER_SHIP, getAccountOwnershipFld());
        assertionMap.put(ACCOUNT_PHONE, getAccountPhoneFld());
        assertionMap.put(ACCOUNT_FAX, getAccountFaxFld());
        assertionMap.put(ACCOUNT_NUMBER, getAccountNumberFld());
        assertionMap.put(ACCOUNT_WEBSITE, getAccountWebsiteFld());
        assertionMap.put(ACCOUNT_SITE, getAccountSiteFld());
        assertionMap.put(ACCOUNT_THICKER, getAccountTickerSymbolFld());
        assertionMap.put(ACCOUNT_TYPE, getAccountTypeFld());
        assertionMap.put(ACCOUNT_INDUSTRY, getAccountIndustryFld());
        assertionMap.put(ACCOUNT_EMPLOYEES, getAccountEmployeesFld());
        assertionMap.put(ACCOUNT_ANNUAL_REVENUE, getAccountAnnualRevenueFld());
        assertionMap.put(ACCOUNT_SICCODE, getAccountSICCodeFld());
        assertionMap.put(ACCOUNT_BILLING_STREET, getAccountBillingAddressFld());
        assertionMap.put(ACCOUNT_SHIPPING_STREET, getAccountShippingAddressFld());
        assertionMap.put(ACCOUNT_CUSTOMER_PRIORITY, getAccountCustomPriorityFld());
        assertionMap.put(ACCOUNT_SLA, getAccountSLAFld());
        assertionMap.put(ACCOUNT_ACTIVE, getAccountActiveFld());
        assertionMap.put(ACCOUNT_SLA_EXPIRATION_DATE, getAccountSLAExpirationDateFld());
        assertionMap.put(ACCOUNT_SLASERIAL_NUMBER, getAccountSerialNumberFld());
        assertionMap.put(ACCOUNT_UPSELL_OPPORTUNITY, getAccountUpSellOpportunityFld());
        assertionMap.put(ACCOUNT_NUMBER_LOCATIONS, getAccountNumberOfLocationsFld());
        assertionMap.put(ACCOUNT_DESCRIPTION, getAccountDescriptionFld());
        return assertionMap;
    }
}
