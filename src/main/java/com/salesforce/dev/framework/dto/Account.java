package com.salesforce.dev.framework.dto;

import java.util.EnumMap;
import java.util.Map;

import com.salesforce.dev.pages.accounts.AccountSteps;

import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_ACTIVE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_ANNUAL_REVENUE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_BILLING_STREET;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_CUSTOMER_PRIORITY;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_DESCRIPTION;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_EMPLOYEES;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_FAX;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_INDUSTRY;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_NAME;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_NUMBER;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_NUMBER_LOCATIONS;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_OWNER_SHIP;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_PHONE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_RATING;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SHIPPING_STREET;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SICCODE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SITE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SLA;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SLASERIAL_NUMBER;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_SLA_EXPIRATION_DATE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_THICKER;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_TYPE;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_UPSELL_OPPORTUNITY;
import static com.salesforce.dev.pages.accounts.AccountSteps.ACCOUNT_WEBSITE;

/**
 * @author Walter Mercado
 * @since 6/24/2015.
 */
public class Account {

    private String accountName;

    private String accountDesc;

    private String rating;

    private String ownership;

    private String phone;

    private String fax;

    private String number;

    private String website;

    private String accountSite;

    private String tickerSymbol;
    
    private String type;

    private String industry;
    private Integer employees;
    private Integer annualRevenue;
    private String sicCode;
    private String billingAddress;
    private String shippingAddress;
    private String customerPriority;
    private String sla;
    private String upSellOpportunity;
    private String active;
    private String slaExpirationDate;
    private String slaSerialNumber;
    private Integer numberOfLocations;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAccountSite() {
        return accountSite;
    }

    public void setAccountSite(String accountSite) {
        this.accountSite = accountSite;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getEmployees() {
        return employees;
    }

    public void setEmployees(Integer employees) {
        this.employees = employees;
    }

    public Integer getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(Integer annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public String getSicCode() {
        return sicCode;
    }

    public void setSicCode(String sicCode) {
        this.sicCode = sicCode;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCustomerPriority() {
        return customerPriority;
    }

    public void setCustomerPriority(String customerPriority) {
        this.customerPriority = customerPriority;
    }

    public String getSla() {
        return sla;
    }

    public void setSla(String sla) {
        this.sla = sla;
    }

    public String getUpSellOpportunity() {
        return upSellOpportunity;
    }

    public void setUpSellOpportunity(String opportunity) {
        this.upSellOpportunity = opportunity;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSlaExpirationDate() {
        return slaExpirationDate;
    }

    public void setSlaExpirationDate(String slaExpirationDate) {
        this.slaExpirationDate = slaExpirationDate;
    }

    public String getSlaSerialNumber() {
        return slaSerialNumber;
    }

    public void setSlaSerialNumber(String slaSerialNumber) {
        this.slaSerialNumber = slaSerialNumber;
    }

    public Integer getNumberOfLocations() {
        return numberOfLocations;
    }

    public void setNumberOfLocations(Integer numberOfLocations) {
        this.numberOfLocations = numberOfLocations;
    }

    public Map<AccountSteps, Object> convertToMap() {
        Map<AccountSteps, Object> mapAccount = new EnumMap<>(AccountSteps.class);
        mapAccount.put(ACCOUNT_NUMBER, number);
        mapAccount.put(ACCOUNT_SITE, accountSite);
        mapAccount.put(ACCOUNT_TYPE, type);
        mapAccount.put(ACCOUNT_INDUSTRY, industry);
        mapAccount.put(ACCOUNT_ANNUAL_REVENUE, annualRevenue);
        mapAccount.put(ACCOUNT_RATING, rating);
        mapAccount.put(ACCOUNT_PHONE, phone);
        mapAccount.put(ACCOUNT_NAME, getAccountName());
        mapAccount.put(ACCOUNT_FAX, fax);
        mapAccount.put(ACCOUNT_WEBSITE, website);
        mapAccount.put(ACCOUNT_THICKER, tickerSymbol);
        mapAccount.put(ACCOUNT_OWNER_SHIP, ownership);
        mapAccount.put(ACCOUNT_EMPLOYEES, employees);
        mapAccount.put(ACCOUNT_SICCODE, sicCode);
        mapAccount.put(ACCOUNT_BILLING_STREET, billingAddress);
        mapAccount.put(ACCOUNT_SHIPPING_STREET, shippingAddress);
        mapAccount.put(ACCOUNT_CUSTOMER_PRIORITY, customerPriority);
        mapAccount.put(ACCOUNT_SLA_EXPIRATION_DATE, slaExpirationDate);
        mapAccount.put(ACCOUNT_NUMBER_LOCATIONS, numberOfLocations);
        mapAccount.put(ACCOUNT_ACTIVE, active);
        mapAccount.put(ACCOUNT_SLA, sla);
        mapAccount.put(ACCOUNT_SLASERIAL_NUMBER, slaSerialNumber);
        mapAccount.put(ACCOUNT_UPSELL_OPPORTUNITY, upSellOpportunity);
        mapAccount.put(ACCOUNT_DESCRIPTION, accountDesc);
        Map<AccountSteps, Object> newAccountMap = new EnumMap<>(AccountSteps.class);
        mapAccount.entrySet().stream()
                .filter(step -> step.getValue() != null)
                .forEach(step -> newAccountMap.put(step.getKey(), step.getValue()));
        return newAccountMap;
    }
}
