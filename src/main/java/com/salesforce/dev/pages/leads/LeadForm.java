package com.salesforce.dev.pages.leads;

import com.salesforce.dev.pages.base.FormBase;
import com.salesforce.dev.pages.base.SearchLookupBase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;


/**
 * This class will be used to represent Lead form.
 *
 * @author Jimmy Vargas
 * @since 6/15/2015.
 */
public class LeadForm extends FormBase {

    @FindBy(id = "lea16city")
    @CacheLookup
    private WebElement city;

    @FindBy(id = "lea16country")
    @CacheLookup
    private WebElement country;

    /*Lead Information*/
    @FindBy(id = "name_salutationlea2")
    @CacheLookup
    private WebElement nameSalutation;

    @FindBy(id = "name_firstlea2")
    @CacheLookup
    private WebElement firstName;

    @FindBy(id = "name_lastlea2")
    @CacheLookup
    private WebElement lastName;

    @FindBy(id = "lea3")
    @CacheLookup
    private WebElement company;

    @FindBy(id = "lea4")
    @CacheLookup
    private WebElement title;

    @FindBy(id = "lea5")
    @CacheLookup
    private WebElement leadSource;

    @FindBy(id = "lea20")
    @CacheLookup
    private WebElement campaign;

    @FindBy(xpath = "//img[@alt='Campaign Lookup (New Window)']")
    @CacheLookup
    private WebElement campaingLookup;

    @FindBy(id = "lea6")
    @CacheLookup
    private WebElement industry;

    @FindBy(id = "lea7")
    @CacheLookup
    private WebElement annualRevenue;

    @FindBy(id = "lea8")
    @CacheLookup
    private WebElement phone;

    @FindBy(id = "lea9")
    @CacheLookup
    private WebElement mobile;

    @FindBy(id = "lea10")
    @CacheLookup
    private WebElement fax;

    @FindBy(id = "lea11")
    @CacheLookup
    private WebElement email;

    @FindBy(id = "lea12")
    @CacheLookup
    private WebElement website;

    @FindBy(id = "lea13")
    @CacheLookup
    private WebElement leadStatus;

    @FindBy(id = "lea14")
    @CacheLookup
    private WebElement rating;

    @FindBy(id = "lea15")
    @CacheLookup
    private WebElement numEmployees;

    /*Address Information*/
    @FindBy(id = "lea16street")
    @CacheLookup
    private WebElement street;

    @FindBy(id = "lea16state")
    @CacheLookup
    private WebElement state;

    @FindBy(id = "lea16zip")
    @CacheLookup
    private WebElement zipCode;

    /*additional information*/
    @FindBy(xpath = "//td[contains(.,'Product Interest')]/following::span/select")
    @CacheLookup
    private WebElement productInterest;

    @FindBy(xpath = "//td[contains(.,'SIC Code')]/following::input")
    @CacheLookup
    private WebElement SICcode;

    @FindBy(xpath = "//td[contains(.,'Number of Locations')]/following::input")
    @CacheLookup
    private WebElement numLocations;

    @FindBy(xpath = "//td[contains(.,'Current Generator')]/following::input")
    @CacheLookup
    private WebElement currentGenerators;

    @FindBy(xpath = "//td[contains(.,'Primary')]/following::select")
    @CacheLookup
    private WebElement primary;

    /*Others*/
    @FindBy(id = "lea17")
    @CacheLookup
    private WebElement description;

    @FindBy(id = "lea21")
    @CacheLookup
    private WebElement assignRule;

    public LeadForm() {

    }

    public LeadForm(LeadBuilder builder) {


        //setting the mandatory fields
        this.setLastName(builder.lastName);
        this.setCompany(builder.company);
        this.selectLeadStatusByVisibleText(builder.leadStatus);

        //setting Lead Information
        this.setTitle(builder.title);

        this.selectNameSalutationByVisibleText(builder.salutation);
        this.setFirstName(builder.firstName);
        this.selectLeadSourceByVisibleText(builder.leadSource);
        this.setCampaign(builder.campaign);
        this.selectCampaignLookup(builder.campaign);
        this.selectIndustryByVisibleText(builder.industry);
        this.setAnnualRevenue(builder.annualRevenue);
        this.setPhone(builder.phone);
        this.setMobile(builder.mobile);
        this.setFax(builder.fax);
        this.setEmail(builder.email);
        this.setWebsite(builder.website);
        this.selectRatingByVisibleText(builder.rating);
        this.setNumEmployees(builder.numEmployees);

        //address information
        this.setStreet(builder.street);
        this.setCity(builder.city);
        this.setState(builder.stateProvince);
        this.setZipCode(builder.zipPostalCode);
        this.setCountry(builder.country);

        //additional information
        this.selectProductInterestByVisibleText(builder.productInterest);
        this.setSICcode(builder.SICcode);
        this.setNumLocations(builder.numberLocations);
        this.setCurrentGenerators(builder.currentGenerators);
        this.selectPrimaryByVisibleText(builder.primary);
        this.setDescription(builder.description);
        this.setAssignRuleCheckBox(builder.asignRule);
    }


    //Lead information
    public void setLastName(String lastName) {
        if (lastName != null) {
            fillTextBox(this.lastName, lastName);
        }
    }

    public void setCompany(String company) {
        if (company != null) {
            fillTextBox(this.company, company);
        }
    }

    public void setTitle(String title) {
        if (title != null) {
            fillTextBox(this.title, title);
        }
    }

    /*Lead Information*/
    public void selectNameSalutationByVisibleText(String salutation) {
        if (salutation != null) {
            selectItemComboBox(nameSalutation, salutation);
        }
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            fillTextBox(this.firstName, firstName);
        }
    }

    public void selectLeadSourceByVisibleText(String leadSource) {
        if (leadSource != null) {
            selectItemComboBox(this.leadSource, leadSource);
        }
    }

    public void setCampaign(String campaign) {
        if (campaign != null) {
            fillTextBox(this.campaign, campaign);
        }
    }

    public void selectCampaignLookup(String campaignLookup) {
        if (campaignLookup != null) {
            campaingLookup.click();
            SearchLookupBase searchLookup = new SearchLookupBase();
            searchLookup.searchText(campaignLookup);
        }
    }

    public void selectIndustryByVisibleText (String industry) {
        if (industry != null) {
            selectItemComboBox (this.industry, industry);
        }
    }

    public void setAnnualRevenue (String annualRevenue) {
        if (annualRevenue != null) {
            fillTextBox (this.annualRevenue, annualRevenue);
        }
    }

    public void setPhone (String phone) {
        if (phone != null) {
            fillTextBox (this.phone, phone);

        }
    }

    public void setMobile (String mobile) {
        if (mobile != null) {
            fillTextBox (this.mobile, mobile);
        }
    }

    public void setFax (String fax) {
        if (fax != null) {
            fillTextBox (this.fax, fax);
        }
    }

    public void setEmail (String email) {
        if (email != null) {
            fillTextBox (this.email, email);
        }
    }

    public void setWebsite (String website) {
        if (website != null) {
            fillTextBox (this.website, website);
        }
    }


    public void selectRatingByVisibleText (String rating) {
        if (rating != null) {
            selectItemComboBox (this.rating, rating);
        }
    }

    public void setNumEmployees (String numEmployees) {
        if (numEmployees != null) {
            fillTextBox (this.numEmployees, numEmployees);
        }
    }

    /*Address Information*/
    public void setStreet (String street) {
        if (street != null) {
            fillTextBox (this.street, street);
        }
    }

    public void setCity (String city) {
        if (city != null) {
            fillTextBox (this.city, city);
        }
    }

    public void setState (String state) {
        if (state != null) {
            fillTextBox (this.state, state);
        }
    }

    public void setZipCode (String zipCode) {
        if (zipCode != null) {
            fillTextBox (this.zipCode, zipCode);
        }
    }

    public void setCountry (String country) {
        if (country != null) {
            fillTextBox (this.country, country);
        }
    }

    /*additional information*/
    public void selectProductInterestByVisibleText (String productInterest) {
        if (productInterest != null) {
            selectItemComboBox (this.productInterest, productInterest);

        }
    }

    public void setSICcode (String SICcode) {
        if (SICcode != null) {
            fillTextBox (this.SICcode, SICcode);
        }

    }

    public void setNumLocations (String numLocations) {
        if (numLocations != null) {
            fillTextBox (this.numLocations, numLocations);
        }
    }

    public void setCurrentGenerators (String currentGenerators) {
        if (currentGenerators != null) {
            fillTextBox (this.currentGenerators, currentGenerators);
        }
    }

    public void selectPrimaryByVisibleText (String primary) {
        if (primary != null) {
            selectItemComboBox (this.primary, primary);

        }
    }

    public void setDescription (String description) {
        if (description != null) {
            fillTextBox (this.description, description);
        }
    }

    public void setAssignRuleCheckBox(boolean assignRule) {

        if (assignRule) {
            if (!this.assignRule.isSelected()) {
                this.assignRule.click();
            }
        } else {
            if (this.assignRule.isSelected()) {
                this.assignRule.click();
            }
        }
    }

    public void selectLeadStatusByVisibleText(String leadStatus) {
        selectItemComboBox(this.leadStatus, leadStatus);
    }

    public LeadDetail clickSaveBtn() {
        super.clickSaveButton();
        return new LeadDetail();
    }

    public LeadForm clickSaveNewBtn() {
        super.clickSaveNewButton();
        return new LeadForm();
    }

    public LeadsHome clickCancelBtn() {
        super.clickCancelButton();
        return new LeadsHome();
    }

}
