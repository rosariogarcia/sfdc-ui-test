package com.salesforce.dev.contact;

import java.util.Calendar;

import com.salesforce.dev.framework.dto.Account;
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

import static org.testng.Assert.assertTrue;

/**
 * This class will be used to test the edition of a contact.
 *
 * @author Marcelo Vargas.
 * @since 6/21/2015.
 */

public class EditContact {
    private static final Logger LOGGER = Logger.getLogger(EditContact.class.getName());

    private static final Integer MY_YEAR = 2015;

    private Contact contact ;

    private ContactsHome contactsHome;

    private ContactDetail contactDetail;

    private ContactForm contactForm;

    private MainPage mainPage;

    private DetailsBase accountDetail;

    private NavigationBar navigationBar;

    private AccountsHome accountsHome;

    private AccountForm accountForm;

    private SearchLookupBase searchLookup;

    @BeforeMethod(groups = {"Acceptance"})
    public void setUp() {
        mainPage = LoginPage.loginAsPrimaryUser();
        contact = (Contact) JSONMapper.getGeneric(Contact.class,"EditContact.json");
        navigationBar = mainPage.gotoNavBar();
        accountsHome = navigationBar.goToAccountsHome();
        accountForm = accountsHome.clickNewBtn();
        accountForm.setAccountNameFld(contact.getAccountName());
        accountDetail = accountForm.clickSaveBtn();
        mainPage = accountDetail.gotoMainPage();

        navigationBar = mainPage.gotoNavBar();
        contactsHome = navigationBar.goToContactsHome();
        contactForm = contactsHome.clickNewBtn();
        contactForm.setLastName(contact.getLastNameastName());
        contactDetail = contactForm.clickSaveBtn();

        mainPage = contactDetail.gotoMainPage();
    }

    @Test(groups = {"Acceptance"})
    public void testEditContact() {
        navigationBar = mainPage.gotoNavBar();
        contactsHome = navigationBar.goToContactsHome();

        contactDetail = contactsHome.selectRecentItem(contact.getLastNameastName());
        contactForm = contactDetail.clickEditBtn();
        contactForm.setLastName(contact.getLastNameastName())
        .setFirstNameRole(contact.getcontactRole())
        .setFirstName(contact.getFirstName());

        searchLookup = contactForm.clickLookupAccount();
        searchLookup.searchText(contact.getAccountName());
        contactForm = searchLookup.goToContactForm();

        contactForm.setTitle(contact.getTitle())
        .setDepartment(contact.getDepartment())
        .setBirthDate(Calendar.MONTH, Calendar.DAY_OF_MONTH, MY_YEAR);

        searchLookup = contactForm.clickLookupReportsTo();
        searchLookup.searchText(contact.getReportsTo());
        contactForm = searchLookup.goToContactForm();

        contactForm.setLeadSource(contact.getLeadSource())
        .setPhone(contact.getPhone())
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

        assertTrue(contactDetail.validateContactName(String.format("%s %s %s", contact.getcontactRole(), contact.getFirstName(), contact.getLastNameastName())));
    }

    @AfterMethod(groups = {"Acceptance"})
    public void tearDown() {
        contactDetail.clickDeleteButton();
        mainPage = accountDetail.gotoMainPage();
        navigationBar = mainPage.gotoNavBar();
        accountsHome = navigationBar.goToAccountsHome();
        accountDetail = accountsHome.selectRecentItem(contact.getAccountName());
        accountDetail.clickDeleteButton();
    }
}
