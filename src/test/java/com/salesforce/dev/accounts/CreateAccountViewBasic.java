package com.salesforce.dev.accounts;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.framework.utils.RandomGenerator;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.accounts.AccountView;
import com.salesforce.dev.pages.accounts.AccountsHome;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.base.ViewDetailBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.assertTrue;

/**
 * This class will be used to test the basic view of the creation of ane account.
 *
 * @author Carlos Orellana
 * @author Mijhail Villarroel
 */
public class CreateAccountViewBasic {

    private AccountsHome accountsHome;

    private AccountView accountView;

    private MainPage mainPage;

    private NavigationBar navigationBar;

    private ViewDetailBase accountViewDetail;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("CreateAccountsViewBasic.json", ViewSalesForce.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateCampaignView(ViewSalesForce viewSalesForce) {
        String viewName = viewSalesForce.getViewName() + RandomGenerator.getInstance().getRandomString();
        viewSalesForce.setViewName(viewName);
        accountsHome = navigationBar.goToAccountsHome();
        accountView = accountsHome.clickNewViewLnk()
                .setViewName(viewName)
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwner(viewSalesForce.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());
        accountViewDetail = accountView.clickSaveBtn();
        assertTrue(accountViewDetail.validateNameView(viewSalesForce.getViewName()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        accountViewDetail.clickDeleteLnk();
    }
}
