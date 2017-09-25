package py.com.aseguradoratajy.tajydemo.models;

import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;

import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;

/**
 * Created by Diego on 9/24/2017.
 */

public class Insurance implements ParentListItem {

    public String number;
    private String insuranceType;
    private int icon;
    private int insuranceAmount;
    private List<InsuranceDetail> detail;
    private List<AccountBalance> accountBalance;
    private String expiration;

    public static Insurance getInstance() {
        return new Insurance("N-0501-86245-0", "SEGURO VEHICULO", R.mipmap.ic_launcher, 25000000, InsuranceDetail.getInstance(), AccountBalance.getInstance(), "01/01/2018");
    }

    public Insurance(String number, String insuranceType, int icon, int insuranceAmount, List<InsuranceDetail> detail, List<AccountBalance> accountBalance, String expiration) {
        this.number = number;
        this.insuranceType = insuranceType;
        this.icon = icon;
        this.insuranceAmount = insuranceAmount;
        this.detail = detail;
        this.accountBalance = accountBalance;
        this.expiration = expiration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(int insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public List<InsuranceDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<InsuranceDetail> detail) {
        this.detail = detail;
    }

    public List<AccountBalance> getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(List<AccountBalance> accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    @Override
    public List<?> getChildItemList() {
        return detail;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
