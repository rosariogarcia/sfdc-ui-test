package com.salesforce.dev.oportunity;

import java.util.Iterator;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.opportunities.OpportunitiesHome;
import com.salesforce.dev.pages.opportunities.OpportunityView;
import com.salesforce.dev.pages.opportunities.OpportunityViewDetail;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * This class will be used to test the basic view of the creation of an opportunity.
 *
 * @author Carlos Orellana
 * @since 8/22/2015.
 */
public class CreateOpportunityViewBasic {

    private static final Logger LOGGER = Logger.getLogger(CreateOpportunityViewBasic.class.getName());
    private NavigationBar navigationBar;
    private OpportunityViewDetail opportunityViewDetail;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("CreateOpportunityViewBasic.json", ViewSalesForce.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateOpportunityViewBasic(ViewSalesForce viewSalesForce) {
        OpportunitiesHome opportunitiesHome = navigationBar.goToOpportunitiesHome();
        OpportunityView opportunityView = opportunitiesHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwner(viewSalesForce.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());
        opportunityViewDetail = opportunityView.clickSaveBtn();
        assertTrue(opportunityViewDetail.validateNameView(viewSalesForce.getViewName()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        opportunityViewDetail.clickDeleteLnk();
        LOGGER.info("Opportunity View was deleted");
    }
}
