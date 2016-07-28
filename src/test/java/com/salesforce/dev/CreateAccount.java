package com.salesforce.dev;

import com.salesforce.dev.framework.JSONMapper;
import com.salesforce.dev.framework.Objects.Account;
import com.salesforce.dev.pages.Accounts.AccountDetail;
import com.salesforce.dev.pages.Accounts.AccountForm;
import com.salesforce.dev.pages.Accounts.AccountsHome;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.Home.LoginPage;
import com.salesforce.dev.pages.Login.Transporter;
import com.salesforce.dev.pages.MainPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

/**
 * Created by Walter on 13/06/2015.
 */
public class CreateAccount {

    private MainPage mainPage;
    private AccountDetail accountDetail;
    private HomePage homePage;
    private NavigationBar navigationBar;
    private Account account = JSONMapper.getAccountBase();
    private LoginPage loginPage;

    @BeforeMethod(groups = {"BVT"})
    public void setUp() {
        HomePage homePage = new HomePage();
        loginPage = homePage.clickLoginBtn();
        mainPage = loginPage.loginAsPrimaryUser();
    }

    @Test(groups = {"Acceptance"})
    public void testCreateAccount() {
        NavigationBar navigationBar = mainPage.gotoNavBar();
        AccountsHome accountsHome = navigationBar.goToAccountsHome();
        AccountForm accountForm = accountsHome.clickNewBtn()

            .setAccountNameFld(account.getAccountName())
            .setAccountRatingFld(account.getRating())
            .setAccountOwnershipFld(account.getOwnership())
            .setAccountPhoneFld(account.getPhone())
            .setAccountFaxFld(account.getFax())
            .setAccountNumberFld(account.getNumber())
            .setAccountWebsiteFld(account.getWebsite())
            .setAccountSiteFld(account.getAccountSite())
            .setAccountThickerFld(account.getTickerSymbol())
            .setAccountTypeFld(account.getType())
            .setAccountIndustryFld(account.getIndustry())
            .setAccountEmployeesFld(account.getEmployees())
            .setAccountAnnualRevenueFld(account.getAnnualRevenue())
            .setAccountSICCodeFld(account.getSicCode())
            .setAccountBillingStreetFld(account.getBillingAddress())
            .setAccountShippingStreetFld(account.getShippingAddress())
            .setAccountCustomerPriorityFld(account.getCustomerPriority())
            .setAccountSLAFld(account.getSla())
            //.setAccountUpsellOpportunityFld(account.getUpSellOpportunity()) //JsonMapper error
            .setAccountActiveFld(account.getActive())
            .setAccountSLAExpirationDateFld(account.getSlaExpirationDate())
            .setAccountSLASerialNumberFld(account.getSlaSerialNumber())
            .setAccountNumberLocationsFld(account.getNumberOfLocations())
            .setAccountDescriptionFld(account.getAccountDesc());
        accountDetail = accountForm.clickSaveBtn();

        Assert.assertTrue(accountDetail.validateAccountNameFld(account.getAccountName()));
        Assert.assertTrue(accountDetail.validateAccountRatingFld(account.getRating()));
        Assert.assertTrue(accountDetail.validateAccountOwnershipFld(account.getOwnership()));
        Assert.assertTrue(accountDetail.validateAccountPhoneFld(account.getPhone()));
        Assert.assertTrue(accountDetail.validateAccountFaxFld(account.getFax()));
        Assert.assertTrue(accountDetail.validateAccountNumberFld(account.getNumber()));
        Assert.assertTrue(accountDetail.validateAccountWebsiteFld(account.getWebsite()));
        Assert.assertTrue(accountDetail.validateAccountSiteFld(account.getAccountSite()));
        Assert.assertTrue(accountDetail.validateAccountTickerSymbolFld(account.getTickerSymbol()));
        Assert.assertTrue(accountDetail.validateAccountTypeFld(account.getType()));
        Assert.assertTrue(accountDetail.validateAccountIndustryFld(account.getIndustry()));
        Assert.assertTrue(accountDetail.validateAccountEmployeesFld(account.getEmployees()));
        Assert.assertTrue(accountDetail.validateAccountAnnualRevenueFld(account.getAnnualRevenue()));
        Assert.assertTrue(accountDetail.validateAccountSICCodeFld(account.getSicCode()));
        Assert.assertTrue(accountDetail.validateAccountBillingAddressFld(account.getBillingAddress()));
        Assert.assertTrue(accountDetail.validateAccountShippingAddressFld(account.getShippingAddress()));
        Assert.assertTrue(accountDetail.validateAccountCustomPriorityFld(account.getCustomerPriority()));
        Assert.assertTrue(accountDetail.validateAccountSLAFld(account.getSla()));
        Assert.assertTrue(accountDetail.validateAccountActiveFld(account.getActive()));
        Assert.assertTrue(accountDetail.validateAccountSLAExpirationDateFld(account.getSlaExpirationDate()));
        Assert.assertTrue(accountDetail.validateAccountSerialNumberFld(account.getSlaSerialNumber()));
        Assert.assertTrue(accountDetail.validateAccountNumberOfLocationsFld(account.getNumberOfLocations()));
        Assert.assertTrue(accountDetail.validateAccountDescriptionFld(account.getAccountDesc()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        accountDetail.clickDeleteBtn(true);

    }
}
