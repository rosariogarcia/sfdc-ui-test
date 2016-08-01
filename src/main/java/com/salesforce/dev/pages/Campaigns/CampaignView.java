package com.salesforce.dev.pages.campaigns;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.salesforce.dev.framework.dto.FieldToDisplayView;
import com.salesforce.dev.framework.dto.FilterView;
import com.salesforce.dev.framework.dto.ViewSalesForce;
import com.salesforce.dev.framework.selenium.DriverManager;
import com.salesforce.dev.pages.base.ViewBase;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by veronica on 8/20/2015.
 */
public class CampaignView extends ViewBase {
    private List<FieldToDisplayView> fieldToDisplayViews;

    private static final Logger LOGGER = Logger.getLogger(CampaignView.class.getName());

    public CampaignView() {

        try {
            wait.withTimeout(DriverManager.getInstance().getTimeoutNormal(), TimeUnit.SECONDS)
                    .until(ExpectedConditions.visibilityOf(saveBtn));
        } catch (WebDriverException e) {
            throw new WebDriverException(e);
        } finally {
            wait.withTimeout(DriverManager.getInstance().getTimeoutNormal(), TimeUnit.SECONDS);
        }
    }

    @Override
    public Object clickCancelBtn() {
        return null;
    }

    @Override
    public CampaignView setViewName(String viewName) {
        setViewNameFld(viewName);
        return this;
    }

    @Override
    public CampaignView setUniqueViewName(String uniqueViewName) {
        setUniqueViewNameFld(uniqueViewName);
        return this;
    }

    @Override
    public CampaignView checkFilterByOwnerAll() {
        checkFilterOwnerAll();
        return this;
    }

    @Override
    public CampaignView checkFilterByOwnerMy() {
        checkFilterOwnerMy();
        return this;
    }

    @Override
    public CampaignView checkFilterByOwner(String filter) {
        if (filter.compareToIgnoreCase("All campaigns") == 0)
            checkFilterOwnerAll();
        else
            checkFilterOwnerMy();
        return this;
    }

    @Override
    public CampaignView addAdditionalFiltersByField(int numberField, String field, String operator, String value) {
        addAdditionalFilterByField(numberField, field, operator, value);
        return this;
    }

    @Override
    public CampaignView addNewFieldToDisplay(String newField) {
        addNewFldToDisplay(newField);
        return this;
    }

    @Override
    public CampaignView selectRestrictVisibility(String optionVisibility) {
        selectRestrictVisibilityRadio(optionVisibility);
        return this;
    }

    @Override
    public CampaignViewDetail clickSaveBtn() {
        clickSaveButton();
        LOGGER.info("Campaign view was created");
        return new CampaignViewDetail();
    }

    public void addAdditionalFilters(ViewSalesForce viewSalesForce) {
        List<FilterView> additionalField = viewSalesForce.getAdditionalFields();
        int count = 1;
        for (FilterView addFilter : additionalField) {
            addAdditionalFiltersByField(count, addFilter.getFieldFilter(),
                    addFilter.getOperatorFilter(), addFilter.getValueFilter());
            count++;
        }
    }

    public List<FieldToDisplayView> selectFieldsToDisplay(ViewSalesForce viewSalesForce) {
        List<FieldToDisplayView> fieldToDisplayViews = viewSalesForce.getFieldsDisplay();
        for (FieldToDisplayView fields : fieldToDisplayViews) {
            addNewFieldToDisplay(fields.getFieldToDisplay());
        }
        return fieldToDisplayViews;
    }
}
