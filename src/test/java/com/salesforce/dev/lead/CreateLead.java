package com.salesforce.dev.lead;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.salesforce.dev.framework.dto.Campaign;
import com.salesforce.dev.framework.dto.Lead;
import com.salesforce.dev.framework.soap.CampaignGenie;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.HomePage;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.campaigns.CampaignDetail;
import com.salesforce.dev.pages.campaigns.CampaignsHome;
import com.salesforce.dev.pages.leads.LeadBuilder;
import com.salesforce.dev.pages.leads.LeadDetail;
import com.salesforce.dev.pages.leads.LeadForm;
import com.salesforce.dev.pages.leads.LeadsHome;

/**
 * This class will be used to test the creation of a lead.
 *
 * @author Jimmy Vargas.
 * @since 6/15/2015.
 */
public class CreateLead {
    
    private NavigationBar navBar;

    private Lead lead;

    private CampaignsHome campaignsHome;

    private CampaignDetail campaignDetail;

    private String parentCampaign = "CampaignParent";

    private MainPage mainPage; 

    private NavigationBar navigationBar;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        mainPage = LoginPage.loginAsPrimaryUser();
        navBar = mainPage.gotoNavBar();
        lead = JSONMapper.getGeneric(Lead.class,"CreateLead.json");
        Campaign campaign = CampaignGenie.getCampaign();
        CampaignGenie.createParentCampaign(campaign.getParentCampaign());
    }

    @Test(groups = {"Acceptance"})
    public void testCreateLead() {
        LeadsHome leadsHome = navBar.gotToLeadsHome();
        leadsHome.clickNewBtn();

        LeadForm leadForm = new LeadBuilder(lead.lastName, lead.company, lead.leadStatus)
                .setCampaign(lead.campaignLookup)
                .setSalutation(lead.nameSalutation)
                .setFirstName(lead.firstName)
                .setTitle(lead.title)
                .setLeadSource(lead.leadSource)
                .setIndustry(lead.industry)
                .setAnnualRevenue(lead.annualRevenue)
                .setPhone(lead.phone)
                .setMobile(lead.mobile)
                .setFax(lead.fax)
                .setEmail(lead.email)
                .setWebsite(lead.website)
                .setLeadStatus(lead.leadStatus)
                .setRating(lead.rating)
                .setNumEmployees(lead.numEmployees)
                .setStreet(lead.street)
                .setCity(lead.city)
                .setStateProvince(lead.stateProvince)
                .setZipPostalCode(lead.zipCode)
                .setCountry(lead.country)
                .setProductInterest(lead.productInterest)
                .setSICcode(lead.SICCode)
                .setNumberLocations(lead.numberLocations)
                .setCurrentGenerators(lead.currentGenerators)
                .setPrimary(lead.primary)
                .setDescription(lead.description)
                .build();
        LeadDetail leadDetail = leadForm.clickSaveBtn();
        Assert.assertEquals(leadDetail.getWebsite(), "http://" + lead.website, "The website is not correct");
        Assert.assertEquals(leadDetail.getAddress(), lead.street + "\n" +
                lead.city + ", " +
                lead.stateProvince + " " +
                lead.zipCode + "\n" +
                lead.country, "The address is not correct");
        leadDetail.validateFields(lead);
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        LeadsHome leadsHome = mainPage.gotoNavBar().gotToLeadsHome();
        DetailsBase leadDetail = leadsHome.openLead(lead.lastName + ", " + lead.firstName);
        leadDetail.clickDeleteButton();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignDetail = campaignsHome.selectRecentItem(parentCampaign);
        campaignDetail.clickDeleteButton();
    }
}
