package com.salesforce.dev.campaign;

import java.util.Iterator;
import java.util.Map;

import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.base.SearchLookupBase;
import com.salesforce.dev.pages.campaigns.CampaignForm;
import com.salesforce.dev.pages.campaigns.CampaignSteps;
import com.salesforce.dev.pages.campaigns.CampaignsHome;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.framework.dto.Campaign;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.framework.soap.CampaignGenie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * This class will be used to test the creation of a new Campaign.
 *
 * @author  Marcelo.Vargas.
 * @author  Mijhail Villarroel.
 * @since 6/15/2015.
 */
public class CreateCampaign {

    private String parentCampaign;

    private DetailsBase campaignDetail;

    private NavigationBar navigationBar;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("CreateCampaign.json", Campaign.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        parentCampaign = CampaignGenie.getCampaign().getParentCampaign();
        CampaignGenie.createParentCampaign(parentCampaign);
        MainPage mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateCampaign(Campaign campaign) {
        CampaignsHome campaignsHome = navigationBar.goToCampaignsHome();
        CampaignForm campaignForm = campaignsHome.clickNewBtn()
                .setEndDate(campaign.getEndDate())
                .setStartDate(campaign.getStartDate())
                .setCampaignName(campaign.getCampaignName())
                .checkActiveCheckbox()
                .setTypeSelect(campaign.getCampaignType())
                .setStatusSelect(campaign.getCampaignStatus())
                .setExpectedRevenue(campaign.getExpectedRevenue())
                .setBudgetedCost(campaign.getBudgetedCost())
                .setActualCost(campaign.getActualCost())
                .setExpectedResponse(campaign.getExpectedResponse())
                .setNumSent(campaign.getNumSent());
        SearchLookupBase searchLookup = campaignForm.clickLookupParentCampaign();
        searchLookup.searchText(parentCampaign);
        campaignForm = searchLookup.goToCampaignForm();
        campaignDetail = campaignForm.clickSaveBtn();
        Map<CampaignSteps, Object> mapExpected = campaign.convertToMap();
        Map<Enum, Object> mapActual = campaignDetail.getAssertionMap();
        mapExpected.keySet().stream().forEach((step) -> {
            assertEquals(String.valueOf(mapActual.get(step)), String.valueOf(mapExpected.get(step)));
        });
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        campaignDetail.clickDeleteButton();
    }
}