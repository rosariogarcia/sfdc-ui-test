package com.salesforce.dev.campaign;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.soap.CampaignGenie;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.base.ViewDetailBase;
import com.salesforce.dev.pages.campaigns.CampaignView;
import com.salesforce.dev.pages.campaigns.CampaignsHome;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.assertTrue;

/**
 * This class will be used to test the edition of a Campaing with a basic view
 *
 * @author Veronica Prado.
 * @since 8/22/2015.
 */
public class EditCampaignViewBasic {
    private static final Logger LOGGER = Logger.getLogger(EditCampaignViewBasic.class.getName());
    private CampaignsHome campaignsHome;
    private MainPage mainPage;
    private NavigationBar navigationBar;
    private CampaignView campaignView;
    private String nameView;
    private ViewDetailBase campaignViewDetail;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("EditCampaignViewBasic.json", ViewSalesForce.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        ViewSalesForce viewSalesForce = CampaignGenie.getCampaignView("CreateCampaignViewBasic.json");
        nameView = viewSalesForce.getViewName();
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignView = campaignsHome.clickNewViewLnk()
                .setViewName(nameView)
                .setUniqueViewName(viewSalesForce.getUniqueViewName());
        campaignViewDetail = campaignView.clickSaveBtn();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testEditCampaign(ViewSalesForce viewSalesForceUpdate) {
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignView = campaignsHome.clickEditViewLnk(nameView)
                .setViewName(viewSalesForceUpdate.getViewName())
                .setUniqueViewName(viewSalesForceUpdate.getUniqueViewName())
                .checkFilterByOwner(viewSalesForceUpdate.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForceUpdate.getRestrictVisibility());
        campaignViewDetail = campaignView.clickSaveBtn();
        assertTrue(campaignViewDetail.validateNameView(viewSalesForceUpdate.getViewName()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        campaignViewDetail.clickDeleteLnk();
    }
}
