package py.com.aseguradoratajy.tajydemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.entities.Insurance;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

/**
 * Created by Manu0 on 10/8/2017.
 */

public class InsuranceDetailsAdapter extends RecyclerView.Adapter<InsuranceDetailsAdapter.InsuranceDetailsViewHolder> {

    private List<Insurance> mListInsuranceItems;
    private Context mContext;

    public InsuranceDetailsAdapter(Context context, List<Insurance> insuranceList) {
        mListInsuranceItems = insuranceList;
        mContext = context;
    }

    @Override
    public InsuranceDetailsAdapter.InsuranceDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_policy_details, parent, false);
        return new InsuranceDetailsAdapter.InsuranceDetailsViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final InsuranceDetailsAdapter.InsuranceDetailsViewHolder holder, int position) {
        Insurance i = mListInsuranceItems.get(position);
        holder.mFeePolicy.setText(String.valueOf(position + 1) + "/" + String.valueOf(getItemCount()));
        holder.mAmountPolicy.setText(Utiles.formatNumber(i.getAmount()," Gs."));
        holder.mExpireDate.setText(i.getExpiration());
        holder.mEmitionDate.setText(i.getIssue());
        holder.mLastPaymentDate.setText(i.getLastPayment());
        holder.mBalance.setText(Utiles.formatNumber(i.getBalance()," Gs."));

    }

    @Override
    public int getItemCount() {
        return mListInsuranceItems.size();
    }

    class InsuranceDetailsViewHolder extends RecyclerView.ViewHolder {
        TextView mFeePolicy;
        TextView mAmountPolicy;
        TextView mExpireDate;
        TextView mEmitionDate;
        TextView mLastPaymentDate;
        TextView mBalance;


        InsuranceDetailsViewHolder(View view) {
            super(view);
            mFeePolicy = (TextView) view.findViewById(R.id.policy_fee_value);
            mAmountPolicy = (TextView) view.findViewById(R.id.policy_amount_value);
            mExpireDate = (TextView) view.findViewById(R.id.expire_date_policy_value);
            mEmitionDate = (TextView) view.findViewById(R.id.emition_date_policy_value);
            mLastPaymentDate = (TextView) view.findViewById(R.id.last_payment_policy_value);
            mBalance = (TextView) view.findViewById(R.id.balance_policy_value);

        }
    }

    public void setData(List<Insurance> data) {
        mListInsuranceItems = new ArrayList<>();
        mListInsuranceItems.addAll(data);
        notifyDataSetChanged();
    }

}
