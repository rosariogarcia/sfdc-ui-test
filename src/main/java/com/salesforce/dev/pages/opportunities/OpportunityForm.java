package com.salesforce.dev.pages.opportunities;

import com.salesforce.dev.pages.base.FormBase;
import com.salesforce.dev.pages.base.SearchLookupBase;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * This class will be used to represent Opportunity form.
 *
 * @author Jimmy Vargas
 * @since 6/10/2015.
 */
public class OpportunityForm extends FormBase {

    @FindBy(id = "opp7")
    @CacheLookup
    private WebElement amountField;

    /*Opportunity Information*/
    @FindBy(id = "opp2")
    @CacheLookup
    private WebElement privateCheckBox;

    @FindBy(id = "opp3")
    @CacheLookup
    private WebElement opportunityNameField;

    @FindBy(id = "opp4")
    @CacheLookup
    private WebElement accountName;

    @FindBy(xpath = "//img=[@alt='Account Name Lookup (New Window)']")
    @CacheLookup
    private WebElement accountNameLookup;

    @FindBy(id = "opp5")
    @CacheLookup
    private WebElement type;

    @FindBy(id = "opp6")
    @CacheLookup
    private WebElement leadSource;

    @FindBy(id = "opp9")
    @CacheLookup
    private WebElement closeDateField;

    @FindBy(xpath = "//input[@id='opp9']/following::a")
    @CacheLookup
    private WebElement todayLink;

    @FindBy(id = "opp10")
    @CacheLookup
    private WebElement nextStep;

    @FindBy(id = "opp11")
    @CacheLookup
    private WebElement stageComboBox;

    @FindBy(id = "opp12")
    @CacheLookup
    private WebElement probability;

    @FindBy(id = "opp17")
    @CacheLookup
    private WebElement primaryCampaignSource;

    @FindBy(xpath = "//img[@alt='Primary Campaign Source Lookup (New Window)']")
    @CacheLookup
    private WebElement primaryCampaignSourceLookUp;

    /*Additional Information*/
    @FindBy(xpath = "//td[contains(.,'Order Number')]/following::input")
    @CacheLookup
    private WebElement orderNumber;

    @FindBy(xpath = "//td[contains(.,'Current Generator')]/following::input")
    @CacheLookup
    private WebElement currentGenerator;

    @FindBy(xpath = "//td[contains(.,'Tracking Number')]/following::input")
    @CacheLookup
    private WebElement trackingNumber;

    @FindBy(xpath = "//td[contains(.,'Main Competitor(s)')]/following::input")
    @CacheLookup
    private WebElement mainCompetitors;

    @FindBy(xpath = "//td[contains(.,'Delivery')]/following::span/select")
    @CacheLookup
    private WebElement deliveryStatus;

    /*description*/
    @FindBy(id = "opp14")
    @CacheLookup
    private WebElement description;

    @FindBy(name = "save")
    @CacheLookup
    private WebElement saveBtn;

    public OpportunityForm() {

        wait.until(ExpectedConditions.elementToBeClickable(saveNewBtn));
    }

    public OpportunityForm(OpportunityBuilder builder) {

        wait.until(ExpectedConditions.elementToBeClickable(saveNewBtn));

        this.setOpportunityName(builder.opName);
        this.setCloseDate(builder.closeDate);
        this.selectStageByVisibleText(builder.stage);

        //other fields
        this.setPrivateCheckBox(builder.isPrivate);
        this.selectAccountNameLookup(builder.accountName);
        this.selectTypeByVisibleText(builder.type);
        this.selectLeadSourceByVisibleText(builder.leadSource);
        this.setAmount(builder.amount);
        this.setNextStep(builder.nextStep);
        this.setProbability(builder.probability);
        this.selectPrimaryCampaignSourceLookUp(builder.primaryCampaignSource);

        //additional parameters
        this.setOrderNumber(builder.orderNumber);
        this.setCurrentGenerator(builder.currentGenerator);
        this.setTrackingNumber(builder.trackingNumber);
        this.setMainCompetitors(builder.mainCompetidors);
        this.selectDeliveryStatusByVisibleText(builder.deliveryInstallationStatus);

        this.setDescription(builder.opDescription);

    }

    public void setOpportunityName(String opportunityName) {
        if (opportunityName != null) {
            fillTextBox(opportunityNameField, opportunityName);
        }

    }

    /**
     * Sets the close date and the format e.g 12/25/2015
     */
    public void setCloseDate(String closeDate) {

        if ("today".equalsIgnoreCase(closeDate)) {
            todayLink.click();
        } else {
            closeDateField.clear();
            closeDateField.sendKeys(closeDate);
        }
    }

    /**
     * Sets the stage for the opportunity
     */
    public void selectStageByVisibleText(String stage) {
        if (stage != null) {
            selectItemComboBox(stageComboBox, stage);
        }

    }

    public void setAmount(String amount) {
        if (amount != null) {
            fillTextBox(amountField, amount);
        }
    }

    public void setPrivateCheckBox(boolean privateOp) {

        if (privateOp) {
            if (!privateCheckBox.isSelected()) {
                privateCheckBox.click();
            }
        } else {
            if (privateCheckBox.isSelected()) {
                privateCheckBox.click();
            }
        }
    }


    public void setAccountName(String accountName) {
        if (accountName != null) {
            fillTextBox(this.accountName, accountName);
        }
    }


    public void selectAccountNameLookup(String accountName) {
        if (accountName != null) {
            accountNameLookup.click();
            SearchLookupBase searchLookup = new SearchLookupBase();
            searchLookup.searchText(accountName);
        }
    }


    public void selectTypeByVisibleText(String type) {
        if (type != null) {
            selectItemComboBox(this.type, type);
        }
    }


    public void selectLeadSourceByVisibleText(String leadSource) {
        if (leadSource != null) {
            selectItemComboBox(this.leadSource, leadSource);
        }
    }


    public void setNextStep(String nextStep) {
        if (nextStep != null) {
            fillTextBox(this.nextStep, nextStep);
        }
    }

    public void setProbability(String probability) {
        if (probability != null) {
            fillTextBox(this.probability, probability);
        }
    }

    public void setPrimaryCampaignSource(String primaryCampaignSource) {
        if (primaryCampaignSource != null) {
            fillTextBox(this.primaryCampaignSource, primaryCampaignSource);
        }
    }

    public void selectPrimaryCampaignSourceLookUp(String primaryCampaignSource) {
        if (primaryCampaignSource != null) {
            this.primaryCampaignSourceLookUp.click();
            SearchLookupBase searchLookup = new SearchLookupBase();
            searchLookup.searchText(primaryCampaignSource);
        }
    }

    /*Additional Information*/
    public void setOrderNumber(String orderNumber) {
        if (orderNumber != null) {
            fillTextBox(this.orderNumber, orderNumber);
        }
    }

    public void setCurrentGenerator(String currentGenerator) {
        if (currentGenerator != null) {
            fillTextBox(this.currentGenerator, currentGenerator);
        }
    }

    public void setTrackingNumber(String trackingNumber) {
        if (trackingNumber != null) {
            fillTextBox(this.trackingNumber, trackingNumber);
        }
    }

    public void setMainCompetitors(String mainCompetitors) {
        if (mainCompetitors != null) {
            fillTextBox(this.mainCompetitors, mainCompetitors);
        }
    }

    public void selectDeliveryStatusByVisibleText(String deliveryStatus) {
        if (deliveryStatus != null) {
            selectItemComboBox(this.deliveryStatus, deliveryStatus);
        }
    }

    /*description*/
    public void setDescription(String description) {
        if (description != null) {
            fillTextBox(this.description, description);
        }
    }

    @Override
    public OpportunityDetail clickSaveBtn() {
        super.clickSaveButton();
        return new OpportunityDetail();
    }

    @Override
    public OpportunitiesHome clickCancelBtn() {
        super.clickCancelButton();
        return new OpportunitiesHome();
    }

    public OpportunityForm clickSaveNewBtn() {
        super.clickSaveNewButton();
        return new OpportunityForm();
    }
}
