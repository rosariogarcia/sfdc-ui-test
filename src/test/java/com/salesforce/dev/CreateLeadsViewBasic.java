package com.salesforce.dev;

import com.salesforce.dev.framework.DataDrivenManager;
import com.salesforce.dev.framework.LoggerManager;
import com.salesforce.dev.framework.Objects.ViewSalesForce;
import com.salesforce.dev.pages.Base.NavigationBar;
import com.salesforce.dev.pages.Home.HomePage;
import com.salesforce.dev.pages.Leads.LeadView;
import com.salesforce.dev.pages.Leads.LeadViewDetail;
import com.salesforce.dev.pages.Leads.LeadsHome;
import com.salesforce.dev.pages.Login.Transporter;
import com.salesforce.dev.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

/**
 * Created by Ariel Mattos on 06/09/2015.
 */
public class CreateLeadsViewBasic {
    HomePage homePage;
    MainPage mainPage;
    NavigationBar navBar;
    LeadsHome leadsHome;
    LeadView leadView;
    LeadViewDetail leadViewDetail;

    @DataProvider(name = "dataDriven")
    public Iterator<ViewSalesForce[]> getValues() {
        DataDrivenManager dataDrivenManager = new DataDrivenManager();
        return dataDrivenManager.getDataView("CreateLeadsViewBasic.json");
    }

    @Test(groups = {"Acceptance"}, dataProvider = "dataDriven")
    public void testCreateLeadView(ViewSalesForce viewSalesForce){
        mainPage = Transporter.driverMainPage();
        navBar = mainPage.gotoNavBar();
        leadsHome = navBar.gotToLeadsHome();
        leadView = leadsHome.clickNewViewLnk()
                .setViewName(viewSalesForce.getViewName())
                .setUniqueViewName(viewSalesForce.getUniqueViewName())
                .checkFilterByOwner(viewSalesForce.getFilterByOwner())
                .selectRestrictVisibility(viewSalesForce.getRestrictVisibility());
        leadViewDetail = leadView.clickSaveBtn();
        LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                "Basic Leads View was created");
        Assert.assertTrue(leadViewDetail.validateNameView(viewSalesForce.getViewName()),
                "View name does not match " + viewSalesForce.getViewName());
    }

    @AfterMethod(groups={"Acceptance"})
    public void tearDown(){
        leadViewDetail.clickDeleteLnk(true);
        LoggerManager.getInstance().addInfoLog(this.getClass().getName(),
                "Lead View was deleted");
    }
}
