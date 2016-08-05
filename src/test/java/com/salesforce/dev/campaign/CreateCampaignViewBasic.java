package com.salesforce.dev.campaign;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.base.ViewDetailBase;
import com.salesforce.dev.pages.campaigns.CampaignView;
import com.salesforce.dev.pages.campaigns.CampaignsHome;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

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

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("CreateCampaignViewBasic.json", ViewSalesForce.class);
    }


    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateCampaignView(ViewSalesForce viewSalesForce) {
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
