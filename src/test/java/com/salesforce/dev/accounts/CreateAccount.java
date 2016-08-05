package com.salesforce.dev.accounts;

import java.util.Map;

import com.salesforce.dev.framework.dto.Account;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.accounts.AccountForm;
import com.salesforce.dev.pages.accounts.AccountSteps;
import com.salesforce.dev.pages.accounts.AccountsHome;
import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.MainPage;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * This class will be used to the creation of a new account
 *
 * @author Walter
 * @author Mijhail Villarroel
 */
public class CreateAccount {

    private MainPage mainPage;

    private DetailsBase accountDetail;

    private Map<AccountSteps, Object> mapAccount;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
       mapAccount =JSONMapper.getGeneric(Account.class,"CreateAccountBase.json").convertToMap();
        mainPage = LoginPage.loginAsPrimaryUser();
    }

    @Test(groups = {"Acceptance"})
    public void testCreateAccount() {
        NavigationBar navigationBar = mainPage.gotoNavBar();
        AccountsHome accountsHome = navigationBar.goToAccountsHome();
        AccountForm accountForm = accountsHome.clickNewBtn();
        mapAccount.keySet().stream().forEach(step -> accountForm.getStrategyStepMap(mapAccount).get(step).executeStep());
        accountDetail = accountForm.clickSaveBtn();
        Map<Enum, Object> mapExpected = accountDetail.getAssertionMap();
        mapAccount.keySet().stream().forEach(step -> assertEquals(String.valueOf(mapExpected.get(step)), String.valueOf(mapAccount.get(step))));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        accountDetail.clickDeleteButton();

    }
}
