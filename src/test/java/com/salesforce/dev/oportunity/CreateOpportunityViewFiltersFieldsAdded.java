package com.salesforce.dev.oportunity;

import com.salesforce.dev.framework.dto.FieldToDisplayView;
import com.salesforce.dev.framework.dto.FilterView;
import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.opportunities.OpportunitiesHome;
import com.salesforce.dev.pages.opportunities.OpportunityView;
import com.salesforce.dev.pages.opportunities.OpportunityViewDetail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * This class will be used to test the filters view of the creation of an opportunity.
 *
 * @author Carlos Orellana
 * @since 8/22/2015.
 */
public class CreateOpportunityViewFiltersFieldsAdded {
    private NavigationBar navigationBar;

    private OpportunityViewDetail opportunityViewDetail;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("CreateOpportunityViewFiltersFieldAdded.json", ViewSalesForce.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateOpportunityViewWithFilters(ViewSalesForce viewSalesForce) {
        OpportunitiesHome opportunitiesHome = navigationBar.goToOpportunitiesHome();
        OpportunityView opportunityView = opportunitiesHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwner(viewSalesForce.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());

        List<FilterView> additionalField = viewSalesForce.getAdditionalFields();
        int count = 1;
        for (FilterView addFilter : additionalField) {
            opportunityView = opportunityView.addAdditionalFiltersByField(count, addFilter.getFieldFilter(), addFilter.getOperatorFilter(), addFilter.getValueFilter());
            count++;
        }

        List<FieldToDisplayView> fieldToDisplayViews = viewSalesForce.getFieldsDisplay();
        for (FieldToDisplayView fields : fieldToDisplayViews) {
            opportunityView = opportunityView.addNewFieldToDisplay(fields.getFieldToDisplay());
        }
        opportunityViewDetail = opportunityView.clickSaveBtn();

        assertTrue(opportunityViewDetail.validateNameView(viewSalesForce.getViewName()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        opportunityViewDetail.clickDeleteLnk();
    }
}
