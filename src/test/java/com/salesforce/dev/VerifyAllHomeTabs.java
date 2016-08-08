package com.salesforce.dev;

import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.accounts.AccountsHome;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.campaigns.CampaignsHome;
import com.salesforce.dev.pages.cases.CasesHome;
import com.salesforce.dev.pages.chatter.ChatterHome;
import com.salesforce.dev.pages.contacts.ContactsHome;
import com.salesforce.dev.pages.contracts.ContractsHome;
import com.salesforce.dev.pages.forecasts.ForecastsHome;
import com.salesforce.dev.pages.leads.LeadsHome;
import com.salesforce.dev.pages.opportunities.OpportunitiesHome;
import com.salesforce.dev.pages.orders.OrdersHome;
import com.salesforce.dev.pages.solutions.SolutionsHome;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * This class will test all tabs pages from Salesforce.
 *
 * @author Monica Pardo.
 * @author Bruno Barrios.
 * @since 6/24/2015.
 */
public class VerifyAllHomeTabs {

    private NavigationBar navigationBar;

    @BeforeMethod(groups = {"Smoke"})
    public void setUp() {
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Smoke"})
    public void testVerifyAllHomeTabs() {
        ChatterHome chatterHome = navigationBar.goToChatterHome();
        assertTrue(chatterHome.isUserInChatterTab(), "Chatter page not displayed");

        CampaignsHome campaignsHome = navigationBar.goToCampaignsHome();
        assertTrue(campaignsHome.isUserInCampaignsTab(), "Campaings page not displayed");

        LeadsHome leadsHome = navigationBar.gotToLeadsHome();
        assertTrue(leadsHome.isUserInLeadsTab(), "lead page not displayed");

        AccountsHome accountsHome = navigationBar.goToAccountsHome();
        assertTrue(accountsHome.isUserInAccountsTab(), "Accounts page not displayed");

        ContactsHome contactsHome = navigationBar.goToContactsHome();
        assertTrue(contactsHome.isUserInContactsTab(), "Contacts page not displayed");

        OpportunitiesHome opportunitiesHome = navigationBar.goToOpportunitiesHome();
        assertTrue(opportunitiesHome.isUserInOpportunitiesTab(), "Opportunities page not displayed");

        ForecastsHome forecastsHome = navigationBar.goToForesCastsHome();
        assertTrue(forecastsHome.isUserInForecastsTab(), "Forecasts page not displayed");

        ContractsHome contractsHome = navigationBar.goToContractsHome();
        assertTrue(contractsHome.isUserInContractsTab(), "Contracts page not displayed");

        OrdersHome ordersHome = navigationBar.goToOrdersHome();
        assertTrue(ordersHome.isUserInOrdersTab(), "Orders page not displayed");

        CasesHome casesHome = navigationBar.goToCasesHome();
        assertTrue(casesHome.isUserInCasesTab(), "Cases page not displayed");

        SolutionsHome solutionsHome = navigationBar.goToSolutionsHome();
        assertTrue(solutionsHome.isUserInSolutionsTab(), "Solutions page not displayed");
    }
}
