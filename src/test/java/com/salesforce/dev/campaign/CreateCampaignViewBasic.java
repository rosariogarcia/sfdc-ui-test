package com.salesforce.dev.campaign;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.base.ViewDetailBase;
import com.salesforce.dev.pages.campaigns.CampaignView;
import com.salesforce.dev.pages.campaigns.CampaignsHome;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * This class will be used to test the creation of a campaign with basic view.
 *
 * @author Veronica Prado.
 * @author Mijhail Villarroel.
 * @since 8/22/2015.
 */
public class CreateCampaignViewBasic {

    private NavigationBar navigationBar;

    private ViewDetailBase campaignViewDetail;

    private ViewSalesForce viewSalesForce;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        viewSalesForce = JSONMapper.getGeneric(ViewSalesForce.class,"CreateCampaignViewBasic.json");
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"})
    public void testCreateCampaignView( ) {
        CampaignsHome campaignsHome = navigationBar.goToCampaignsHome();
        CampaignView campaignView = campaignsHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwner(viewSalesForce.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());
        campaignViewDetail = campaignView.clickSaveBtn();
        assertTrue(campaignViewDetail.validateNameView(viewSalesForce.getViewName()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        campaignViewDetail.clickDeleteLnk();
    }
}
