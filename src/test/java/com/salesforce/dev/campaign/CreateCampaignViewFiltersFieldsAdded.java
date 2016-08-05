package com.salesforce.dev.campaign;

import com.salesforce.dev.framework.dto.FieldToDisplayView;
import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.HomePage;
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
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * This class will be used to test the creation of a Campaing with filters view.
 *
 * @author Veronica.
 * @since 8/21/2015.
 */
public class CreateCampaignViewFiltersFieldsAdded {

    private static final Logger LOGGER = Logger.getLogger(CreateCampaignViewFiltersFieldsAdded.class.getName());

    private CampaignsHome campaignsHome;

    private CampaignView campaignView;

    private HomePage homePage;

    private MainPage mainPage;

    private NavigationBar navigationBar;

    private ViewDetailBase campaignViewDetail;

    @DataProvider(name = "dataDriven")
        public Iterator<Object[]> getValues() {
            return DataDrivenManager.getObjects("CreateCampaignViewFiltersFieldAdded.json", ViewSalesForce.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateCampaignViewWithFilters(ViewSalesForce viewSalesForce) {
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignView = campaignsHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwner(viewSalesForce.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());
        campaignView.addAdditionalFilters(viewSalesForce);
        List<FieldToDisplayView> fieldToDisplayViews = campaignView.selectFieldsToDisplay(viewSalesForce);

        campaignViewDetail = campaignView.clickSaveBtn();
        assertTrue(campaignViewDetail.validateNameView(viewSalesForce.getViewName()));
        //validateFieldsAdded
        for (FieldToDisplayView fields : fieldToDisplayViews) {
            Assert.assertTrue(campaignViewDetail.validateFieldDisplayed(fields.getFieldToDisplay()));
        }
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        campaignViewDetail.clickDeleteLnk();
        LOGGER.info("Campaign View was deleted");
    }
}
