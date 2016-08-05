package com.salesforce.dev.contact;

import java.util.Calendar;

import com.salesforce.dev.framework.dto.Contact;
import com.salesforce.dev.framework.utils.JSONMapper;
import com.salesforce.dev.pages.LoginPage;
import com.salesforce.dev.pages.MainPage;
import com.salesforce.dev.pages.accounts.AccountDetail;
import com.salesforce.dev.pages.accounts.AccountForm;
import com.salesforce.dev.pages.accounts.AccountsHome;
import com.salesforce.dev.pages.base.DetailsBase;
import com.salesforce.dev.pages.base.NavigationBar;
import com.salesforce.dev.pages.base.SearchLookupBase;
import com.salesforce.dev.pages.contacts.ContactDetail;
import com.salesforce.dev.pages.contacts.ContactForm;
import com.salesforce.dev.pages.contacts.ContactsHome;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * This clas will be used to test the creation of a contact.
 *
 * @author Marcelo Vargas.
 * @since 6/12/2015.
 */

public class CreateContact {

    private static final Logger LOGGER = Logger.getLogger(CreateContact.class.getName());



    private ContactsHome contactsHome;

    private ContactDetail contactDetail;

    private ContactForm contactForm;

    private MainPage mainPage;

    private DetailsBase accountDetail;

    private NavigationBar navigationBar;

    private AccountsHome accountsHome;

    private AccountForm accountForm;

    private SearchLookupBase searchLookup;

    private String accountName = "AccountTest";

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {

        mainPage = LoginPage.loginAsPrimaryUser();
        navigationBar = mainPage.gotoNavBar();
        accountsHome = navigationBar.goToAccountsHome();
        accountForm = accountsHome.clickNewBtn();
        accountForm.setAccountNameFld(accountName);
        accountDetail = accountForm.clickSaveBtn();
        mainPage = accountDetail.gotoMainPage();
    }

    @Test(groups = {"Acceptance"})
    public void testCreateContact() {
        navigationBar = mainPage.gotoNavBar();
        contactsHome = navigationBar.goToContactsHome();
        contactForm = contactsHome.clickNewBtn();
        Contact contact = JSONMapper.getGeneric(Contact.class,"CreateAccountBase.json");
        contactForm.setFirstNameRole(contact.getcontactRole())
                .setFirstName(contact.getcontactRole())
                .setFirstName(contact.getFirstName())
                .setLastName(contact.getLastNameastName());

        searchLookup = contactForm.clickLookupAccount();
        searchLookup.searchText(accountName);
        contactForm = searchLookup.goToContactForm();

        contactForm.setTitle(contact.getTitle())
                .setDepartment(contact.getDepartment());

        searchLookup = contactForm.clickLookupReportsTo();
        searchLookup.searchText(contact.getReportsTo());
        contactForm = searchLookup.goToContactForm();

        final int year = 2015;
        contactForm.setLeadSource(contact.getLeadSource())
                .setPhone(contact.getPhone())
                .setBirthDate(Calendar.JUNE, Calendar.SATURDAY, year)
                .setHomePhone(contact.getHomePhone())
                .setMobile(contact.getMobile())
                .setOtherPhone(contact.getOtherPhone())
                .setFax(contact.getFax())
                .setEmail(contact.getEmail())
                .setAssistant(contact.getassistant())
                .setAssistantPhone(contact.getAssistantPhone())
                .setMailingStreet(contact.getMailingStreet())
                .setMailingCity(contact.getMailingCity())
                .setMailingState(contact.getMailingState())
                .setMailingZip(contact.getMailingZip())
                .setMailingCountry(contact.getMailingCountry())
                .setOtherStreet(contact.getOtherStreet())
                .setOtherCity(contact.getOtherCity())
                .setOtherState(contact.getOtherState())
                .setOtherZip(contact.getOtherZip())
                .setOtherCountry(contact.getOtherCountry())
                .setLanguages(contact.getLanguages())
                .setLevel(contact.getLevel())
                .setDescription(contact.getDescription());
        contactDetail = contactForm.clickSaveBtn();
        Assert.assertTrue(contactDetail.validateContactName(contact.getcontactRole() + " " + contact.getFirstName() + " " + contact.getLastNameastName()));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        contactDetail.clickDeleteButton();
        LOGGER.info("Contact was deleted");
        mainPage = contactDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
        accountsHome = navigationBar.goToAccountsHome();
        accountDetail = accountsHome.selectRecentItem(accountName);
        accountDetail.clickDeleteButton();

    }
}
