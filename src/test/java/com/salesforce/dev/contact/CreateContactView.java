package com.salesforce.dev.contact;

import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.framework.dto.FieldToDisplayView;
import com.salesforce.dev.framework.dto.FilterView;
import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.RandomGenerator;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.campaigns.CampaignForm;
import com.salesforce.dev.pages.campaigns.CampaignsHome;
import com.salesforce.dev.pages.contacts.ContactView;
import com.salesforce.dev.pages.contacts.ContactViewDetail;
import com.salesforce.dev.pages.contacts.ContactsHome;
import com.salesforce.dev.pages.HomePage;
import com.salesforce.dev.pages.MainPage;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

import static org.testng.Assert.assertFalse;

/**
 *This class will be used to test the view of the cretion of a contact.
 *
 * @author Alexander Apaza.
 * @since 6/12/2015.
 */
public class CreateContactView {

    private ContactsHome contactHome;

    private ContactView contactView;

    private HomePage homePage;

    private ContactViewDetail contactViewDetail;

    private CampaignsHome campaignsHome;

    private MainPage mainPage;

    private NavigationBar navigationBar;

    private String campaignName;

    private CampaignForm campaignForm;

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("CreateContactView.json", ViewSalesForce.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        campaignName = "Camp" + RandomGenerator.getInstance().getRandomString();
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        campaignsHome = navigationBar.goToCampaignsHome();
        campaignForm = campaignsHome.clickNewBtn();
        campaignForm.setCampaignName(campaignName);
        campaignForm.checkActiveCheckbox();
        campaignForm.clickSaveBtn();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateContactView(ViewSalesForce viewSalesForce) {
        contactHome = navigationBar.goToContactsHome();
        contactView = contactHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwnerAll()
                .checkFilterByOwnerMy()
                .setFilterByCampaign(campaignName)
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());

        List<FilterView> additionalField = viewSalesForce.getAdditionalFields();
        int count = 1;
        for (FilterView addFilter : additionalField) {
            contactView = contactView.addAdditionalFiltersByField(count, addFilter.getFieldFilter(),
                    addFilter.getOperatorFilter(), addFilter.getValueFilter());
            count++;
        }
        List<FieldToDisplayView> fieldToDisplayViews = viewSalesForce.getFieldsDisplay();
        for (FieldToDisplayView fields : fieldToDisplayViews)
            contactView = contactView.addNewFieldToDisplay(fields.getFieldToDisplay());
        contactViewDetail = contactView.clickSaveBtn();
        assertFalse(contactViewDetail.validateNameView("AnyName"));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        contactViewDetail.clickDeleteLnk();
    }
}
