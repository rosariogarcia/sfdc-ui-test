package com.salesforce.dev.contact;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.soap.CampaignGenie;
import com.salesforce.dev.framework.utils.DataDrivenManager;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.contacts.ContactView;
import com.salesforce.dev.pages.contacts.ContactViewDetail;
import com.salesforce.dev.pages.contacts.ContactsHome;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.testng.Assert.assertFalse;

/**
 * This class will be used to test the view of the edition of a contact.
 *
 * @author alex
 * @since 06/09/2015.
 */

public class EditContactView {
    private ContactsHome contactsHome;
    private ContactViewDetail contactViewDetail;
    private MainPage mainPage;
    private NavigationBar navigationBar;
    private ContactView contactView;
    private String nameView;
    private static final String NAME_TEST = "anyName";

    @DataProvider(name = "dataDriven")
    public Iterator<Object[]> getValues() {
        return DataDrivenManager.getObjects("EditContactView.json", ViewSalesForce.class);
    }

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        ViewSalesForce viewSalesForce = CampaignGenie.getCampaignView("CreateContactView.json");
        nameView = viewSalesForce.getViewName();
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        contactsHome = navigationBar.goToContactsHome();
        contactView = contactsHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName());
        contactViewDetail = contactView.clickSaveBtn();
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testEditContact(ViewSalesForce viewSalesForceUpdate) {
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        contactsHome = navigationBar.goToContactsHome();
        contactView = contactsHome.clickEditViewLnk(nameView)
                .setViewName(viewSalesForceUpdate.getViewName())
                .setUniqueViewName(viewSalesForceUpdate.getUniqueViewName())
                .checkFilterByOwnerAll()
                .checkFilterByOwnerMy()
                .selectRestrictVisibility(viewSalesForceUpdate.getRestrictVisibility());
        contactViewDetail = contactView.clickSaveBtn();
        assertFalse(contactViewDetail.validateNameView(NAME_TEST));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        contactViewDetail.clickDeleteLnk();
        mainPage = contactViewDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
        contactsHome = navigationBar.goToContactsHome();
    }
}
