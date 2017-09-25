package py.com.aseguradoratajy.tajydemo.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 9/24/2017.
 */

public class AccountBalance {
    private int quota;
    private boolean paid;
    private String expirationDate;


    public static List<AccountBalance> getInstance() {
        List<AccountBalance> mList = new ArrayList<>();

        AccountBalance a1 = new AccountBalance(220000, true, "01/01/2017");
        AccountBalance a2 = new AccountBalance(220000, true, "01/02/2017");
        AccountBalance a3 = new AccountBalance(220000, true, "01/03/2017");
        AccountBalance a4 = new AccountBalance(220000, true, "01/04/2017");
        AccountBalance a5 = new AccountBalance(220000, true, "01/05/2017");
        AccountBalance a6 = new AccountBalance(220000, true, "01/06/2017");
        AccountBalance a7 = new AccountBalance(220000, true, "01/07/2017");
        AccountBalance a8 = new AccountBalance(220000, true, "01/08/2017");
        AccountBalance a9 = new AccountBalance(220000, true, "01/09/2017");
        AccountBalance a10 = new AccountBalance(220000, false, "01/10/2017");
        AccountBalance a11 = new AccountBalance(220000, false, "01/11/2017");
        AccountBalance a12 = new AccountBalance(220000, false, "01/12/2017");

        mList.add(a1);
        mList.add(a2);
        mList.add(a3);
        mList.add(a4);
        mList.add(a5);
        mList.add(a6);
        mList.add(a7);
        mList.add(a8);
        mList.add(a9);
        mList.add(a10);
        mList.add(a11);
        mList.add(a12);

        return mList;
    }

    public AccountBalance(int quota, boolean paid, String expirationDate) {
        this.quota = quota;
        this.paid = paid;
        this.expirationDate = expirationDate;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
