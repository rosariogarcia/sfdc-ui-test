package com.salesforce.dev.contact;

import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.contacts.ContactView;
import com.salesforce.dev.pages.contacts.ContactViewDetail;
import com.salesforce.dev.pages.contacts.ContactsHome;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

    private ViewSalesForce viewSalesForceUpdate;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        viewSalesForceUpdate = JSONMapper.getGeneric(ViewSalesForce.class,"EditContactView.json");
        ViewSalesForce viewSalesForce =  JSONMapper.getGeneric(ViewSalesForce.class,"CreateContactView.json");
        nameView = viewSalesForce.getViewName();
        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        contactsHome = navigationBar.goToContactsHome();
        contactView = contactsHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName());
        contactViewDetail = contactView.clickSaveBtn();
    }

    @Test(groups = {"Acceptance"})
    public void testEditContact() {
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
