package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;

/**
 * Created by Diego on 9/24/2017.
 */

public class Insurance {

    private String identifyCard;
    private Integer sctionCode;
    private String sectionDescription;
    private String policy;
    private String endorsement;
    private String feeDetails;
    private String ruc;
    private String insured;
    private String comercialPhone;
    private String comercialAddress;
    private String particularAddress;
    private String dateFrom;
    private String dateTo;
    private String issue;
    private String expiration;
    private String amount;
    private String payment;
    private String balance;
    private String lastPayment;

    public Insurance(){

    }

    public Insurance(String identifyCard, Integer sctionCode, String sectionDescription, String policy, String endorsement, String feeDetails, String ruc, String insured, String comercialPhone, String comercialAddress, String particularAddress, String dateFrom,
                     String dateTo, String issue, String expiration, String amount, String payment, String balance, String lastPayment) {
        this.identifyCard = identifyCard;
        this.sctionCode = sctionCode;
        this.sectionDescription = sectionDescription;
        this.policy = policy;
        this.endorsement = endorsement;
        this.feeDetails = feeDetails;
        this.ruc = ruc;
        this.insured = insured;
        this.comercialPhone = comercialPhone;
        this.comercialAddress = comercialAddress;
        this.particularAddress = particularAddress;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.issue = issue;
        this.expiration = expiration;
        this.amount = amount;
        this.payment = payment;
        this.balance = balance;
        this.lastPayment = lastPayment;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    public Integer getSctionCode() {
        return sctionCode;
    }

    public void setSctionCode(Integer sctionCode) {
        this.sctionCode = sctionCode;
    }

    public String getSectionDescription() {
        return sectionDescription;
    }

    public void setSectionDescription(String sectionDescription) {
        this.sectionDescription = sectionDescription;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getEndorsement() {
        return endorsement;
    }

    public void setEndorsement(String endorsement) {
        this.endorsement = endorsement;
    }

    public String getFeeDetails() {
        return feeDetails;
    }

    public void setFeeDetails(String feeDetails) {
        this.feeDetails = feeDetails;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getInsured() {
        return insured;
    }

    public void setInsured(String insured) {
        this.insured = insured;
    }

    public String getComercialPhone() {
        return comercialPhone;
    }

    public void setComercialPhone(String comercialPhone) {
        this.comercialPhone = comercialPhone;
    }

    public String getComercialAddress() {
        return comercialAddress;
    }

    public void setComercialAddress(String comercialAddress) {
        this.comercialAddress = comercialAddress;
    }

    public String getParticularAddress() {
        return particularAddress;
    }

    public void setParticularAddress(String particularAddress) {
        this.particularAddress = particularAddress;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(String lastPayment) {
        this.lastPayment = lastPayment;
    }

    @Override
    public String toString() {
        return "Insurance{" +
                "identifyCard='" + identifyCard + '\'' +
                ", sctionCode='" + sctionCode + '\'' +
                ", sectionDescription='" + sectionDescription + '\'' +
                ", policy='" + policy + '\'' +
                ", endorsement='" + endorsement + '\'' +
                ", feeDetails='" + feeDetails + '\'' +
                ", ruc='" + ruc + '\'' +
                ", insured='" + insured + '\'' +
                ", comercialPhone='" + comercialPhone + '\'' +
                ", comercialAddress='" + comercialAddress + '\'' +
                ", particularAddress='" + particularAddress + '\'' +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", issue='" + issue + '\'' +
                ", expiration='" + expiration + '\'' +
                ", amount='" + amount + '\'' +
                ", payment='" + payment + '\'' +
                ", balance='" + balance + '\'' +
                ", lastPayment='" + lastPayment + '\'' +
                '}';
    }
}
