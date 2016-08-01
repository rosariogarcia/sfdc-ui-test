package com.salesforce.dev.accounts;

import java.util.Map;

import com.salesforce.dev.framework.dto.Account;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.accounts.AccountDetail;
import com.salesforce.dev.pages.accounts.AccountForm;
import com.salesforce.dev.pages.accounts.AccountSteps;
import com.salesforce.dev.pages.accounts.AccountsHome;
import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.NavigationBar;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Walter on 13/06/2015.
 */
public class EditAccount {

    private AccountDetail accountDetail;
    private NavigationBar navigationBar;
    private AccountsHome accountsHome;
    private AccountForm accountForm;
    private Account account = JSONMapper.getAccountBase();
    private String accountName = "AccountName";

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        accountsHome = navigationBar.goToAccountsHome();
        accountForm = accountsHome.clickNewBtn();
        accountForm.setAccountNameFld(accountName);
        accountDetail = accountForm.clickSaveBtn();
        mainPage = accountDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"})
    public void testEditAccount() {
        accountsHome = navigationBar.goToAccountsHome();
        accountDetail = accountsHome.selectRecentItem(accountName);
        accountForm = accountDetail.clickEditBtn();
        Map<AccountSteps, Object> mapAccount = account.convertToMap();
        mapAccount.keySet().stream().forEach((step) -> {
            accountForm.getStrategyStepMap(mapAccount).get(step).executeStep();
        });
        accountDetail = accountForm.clickSaveBtn();
        Map<Enum, Object> mapExpected = accountDetail.getAssertionMap();
        mapAccount.keySet().stream().forEach((step) -> {
            assertEquals(String.valueOf(mapExpected.get(step)), String.valueOf(mapAccount.get(step)));
        });
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        accountDetail.clickDeleteButton();
    }
}
