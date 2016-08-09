package com.salesforce.dev.lead;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.leads.LeadView;
import com.salesforce.dev.pages.leads.LeadViewDetail;
import com.salesforce.dev.pages.leads.LeadsHome;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This class will be used to test the basic view of the edition of a lead.
 *
 * @author Ariel Mattos.
 * @since 07/09/2015.
 */
public class EditLeadsViewBasic {

    private LeadsHome leadsHome;

    private MainPage mainPage;

    private NavigationBar navigationBar;

    private LeadView leadView;

    private String viewName;

    private LeadViewDetail leadViewDetail;

    private ViewSalesForce viewSalesForceUpdate;


    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        viewSalesForceUpdate = JSONMapper.getGeneric(ViewSalesForce.class,"EditLeadsViewBasic.json");
        ViewSalesForce viewSalesForce =  JSONMapper.getGeneric(ViewSalesForce.class,"CreateLeadsViewBasic.json");
        viewName = viewSalesForce.getViewName();
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        leadsHome = navigationBar.gotToLeadsHome();
        leadView = leadsHome.clickNewViewLnk()
                .setViewName(viewName)
                .setUniqueViewName(viewSalesForce.getUniqueViewName());
        leadViewDetail = leadView.clickSaveBtn();
    }

    @Test(groups = {"Acceptance"})
    public void testEditCampaign() {
        navigationBar = mainPage.gotoNavBar();
        leadsHome = navigationBar.gotToLeadsHome();
        leadView = leadsHome.clickEditViewLnk(viewName)
                .setViewName(viewSalesForceUpdate.getViewName())
                .setUniqueViewName(viewSalesForceUpdate.getUniqueViewName())
                .checkFilterByOwner(viewSalesForceUpdate.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForceUpdate.getRestrictVisibility());
        leadViewDetail = leadView.clickSaveBtn();
        Assert.assertTrue(leadViewDetail.validateNameView(viewSalesForceUpdate.getViewName()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        leadViewDetail.clickDeleteLnk();
    }
}
