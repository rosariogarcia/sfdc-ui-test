package com.salesforce.dev.accounts;

import com.salesforce.dev.framework.dto.Account;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.accounts.AccountForm;
import com.salesforce.dev.pages.accounts.AccountSteps;
import com.salesforce.dev.pages.accounts.AccountsHome;
import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.NavigationBar;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;


/**
 * This class will be used to test the creation of a new account.
 *
 * @author Walter
 * @author Mijhail Villarroel
 */
public class CreateAccountDD {

    private MainPage mainPage;

    private DetailsBase accountDetail;

    private NavigationBar navigationBar;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("AccountsBaseDD.json", Account.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateAccount(Account account) {
        AccountsHome accountsHome = navigationBar.goToAccountsHome();
        AccountForm accountForm = accountsHome.clickNewBtn()
                .setAccountNameFld(account.getAccountName())
                .setAccountDescriptionFld(account.getAccountDesc());
        accountDetail = accountForm.clickSaveBtn();
        Map<AccountSteps, Object> mapExpected = account.convertToMap();
        Map<Enum, Object> mapActual = accountDetail.getAssertionMap();
        mapExpected.keySet().stream().forEach((step) -> {
            assertEquals(String.valueOf(mapActual.get(step)), String.valueOf(mapExpected.get(step)));
        });
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        accountDetail.clickDeleteButton();
    }
}
