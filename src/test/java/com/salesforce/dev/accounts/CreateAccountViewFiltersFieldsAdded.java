package com.salesforce.dev.accounts;

import java.util.Iterator;
import java.util.List;

import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.framework.dto.FieldToDisplayView;
import com.salesforce.dev.framework.dto.FilterView;
import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.RandomGenerator;
import com.salesforce.dev.pages.accounts.AccountView;
import com.salesforce.dev.pages.accounts.AccountViewDetail;
import com.salesforce.dev.pages.accounts.AccountsHome;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.ViewDetailBase;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * This class will be used to test the filters view of the creation of an account.
 *
 * @author Carlos Orellana
 * @author Mijhail Villarroel
 */

public class CreateAccountViewFiltersFieldsAdded {

    private AccountsHome accountsHome;

    private AccountView accountView;

    private MainPage mainPage;

    private NavigationBar navigationBar;

    private ViewDetailBase accountViewDetail;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("CreateAccountsViewFiltersFieldAdded.json", ViewSalesForce.class);
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateCampaignViewWithFilters(ViewSalesForce viewSalesForce) {
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        accountsHome = navigationBar.goToAccountsHome();
        accountView = accountsHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName() + RandomGenerator.getInstance().getRandomString())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwner(viewSalesForce.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());
        accountView.addFilter(viewSalesForce.getAdditionalFields());
        List<FieldToDisplayView> fieldToDisplayViews = viewSalesForce.getFieldsDisplay();
        accountView.addAccountView(fieldToDisplayViews);
        accountViewDetail = accountView.clickSaveBtn();
        Assert.assertEquals(accountViewDetail.getViewSelected(), viewSalesForce.getViewName());
        for (FieldToDisplayView fields : fieldToDisplayViews) {
            Assert.assertTrue(accountViewDetail.validateFieldDisplayed(fields.getFieldToDisplay()));
        }
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        accountViewDetail.clickDeleteLnk();
    }
}
