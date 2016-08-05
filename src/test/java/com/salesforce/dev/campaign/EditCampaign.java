package com.salesforce.dev.campaign;

import com.salesforce.dev.framework.dto.Campaign;
import com.salesforce.dev.framework.soap.CampaignGenie;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.base.SearchLookupBase;
import com.salesforce.dev.pages.campaigns.CampaignDetail;
import com.salesforce.dev.pages.campaigns.CampaignForm;
import com.salesforce.dev.pages.campaigns.CampaignSteps;
import com.salesforce.dev.pages.campaigns.CampaignsHome;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Map;

import static org.testng.Assert.assertEquals;

/**
 * This class will be used to test the edition of a Campaing.
 *
 * @author Marcelo Vargas.
 * @since 6/21/2015.
 */
public class EditCampaign {

    private static final Logger LOGGER = Logger.getLogger(EditCampaign.class.getName());

    private String campaignNameToUpdated;
    private String campaignParentName;
    private CampaignsHome campaignsHome;
    private CampaignDetail campaignDetail;
    private CampaignForm campaignForm;
    private MainPage mainPage;
    private NavigationBar navigationBar;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {

        Campaign campaign = CampaignGenie.getCampaign();
        CampaignGenie.createParentCampaign(campaign.getParentCampaign());
        campaignNameToUpdated = campaign.getCampaignName();
        campaignParentName = campaign.getParentCampaign();

        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignForm = campaignsHome.clickNewBtn();
        campaignForm.setCampaignName(campaignNameToUpdated);
        campaignForm.checkActiveCheckbox();
        campaignDetail = campaignForm.clickSaveBtn();
        mainPage = campaignDetail.gotoMainPage();
    }

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("EditCampaign.json", Campaign.class);
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testEditCampaign(Campaign campaign) {
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignDetail = campaignsHome.selectRecentItem(campaignNameToUpdated);
        campaignForm = campaignDetail.clickEditBtn()
                .setCampaignName(campaign.getCampaignName())
                .setTypeSelect(campaign.getCampaignType())
                .setStatusSelect(campaign.getCampaignStatus())
                .setStartDate(campaign.getStartDate())
                .setEndDate(campaign.getEndDate())
                .clickPanel()
                .setExpectedRevenue(campaign.getExpectedRevenue())
                .setBudgetedCost(campaign.getBudgetedCost())
                .setActualCost(campaign.getActualCost())
                .setExpectedResponse(campaign.getExpectedResponse())
                .setNumSent(campaign.getNumSent());
        SearchLookupBase searchLookup = campaignForm.clickLookupParentCampaign();
        searchLookup.searchText(campaign.getParentCampaign());
        campaignForm = searchLookup.goToCampaignForm();
        campaignDetail = campaignForm.clickSaveBtn();

        campaignParentName = campaign.getParentCampaign();

        Map<CampaignSteps, Object> mapCampaign = campaign.convertToMap();
        Map<Enum, Object> mapExpected = campaignDetail.getAssertionMap();
        mapCampaign.keySet().stream().forEach((step) -> {
            assertEquals(String.valueOf(mapExpected.get(step)), String.valueOf(mapCampaign.get(step)));
        });
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        campaignDetail.clickDeleteButton();
        LOGGER.info("Campaign was deleted");
        mainPage = campaignDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignDetail = campaignsHome.selectRecentItem(campaignParentName);
        campaignDetail.clickDeleteButton();
        LOGGER.info("Campaign Parent was deleted");
    }
}
