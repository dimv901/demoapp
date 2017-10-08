package py.com.aseguradoratajy.tajydemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.models.Insurance;
import py.com.aseguradoratajy.tajydemo.models.MapPolicyAccount;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

/**
 * Created by Diego on 9/25/2017.
 */

public class InsuranceAdapter extends RecyclerView.Adapter<InsuranceAdapter.InsuranceViewHolder> {

    private List<Insurance> mListInsurance;
    private Context mContext;

    public InsuranceAdapter(Context context, List<Insurance> insuranceList) {
        mListInsurance = insuranceList;
        mContext = context;
    }

    @Override
    public InsuranceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.insurance_item, parent, false);
        return new InsuranceViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(InsuranceViewHolder holder, int position) {
        Insurance i = mListInsurance.get(position);
        holder.mPolicyCode.setText(i.getPolicy().toUpperCase());
        holder.mPolicyType.setText(i.getSectionDescription().toUpperCase());
        holder.mFeePolicy.setText(i.getFeeDetails());
        holder.mAmount.setText(i.getAmount().toUpperCase());
        holder.mDateFrom.setText(i.getDateFrom());
        holder.mDateTo.setText(i.getDateTo());
        holder.mIcon.setBackgroundResource(MapPolicyAccount.policyIconType(i.getSctionCode()));

    }

    @Override
    public int getItemCount() {
        return mListInsurance.size();
    }

    public class InsuranceViewHolder extends RecyclerView.ViewHolder {
        TextView mPolicyCode;
        TextView mPolicyType;
        TextView mFeePolicy;
        TextView mAmount;
        TextView mDateFrom;
        TextView mDateTo;
        ImageView mIcon;

        public InsuranceViewHolder(View view) {
            super(view);
            mPolicyCode = (TextView) view.findViewById(R.id.policy_code_value);
            mPolicyType = (TextView) view.findViewById(R.id.policy_type_value);
            mFeePolicy = (TextView) view.findViewById(R.id.policy_fee_value);
            mAmount = (TextView) view.findViewById(R.id.policy_amount_value);
            mDateFrom = (TextView) view.findViewById(R.id.date_from_policy_value);
            mDateTo = (TextView) view.findViewById(R.id.date_to_policy_value);
            mIcon = (ImageView) view.findViewById(R.id.policy_type_icon);
        }
    }

    public void setData(List<Insurance> data) {
        mListInsurance = new ArrayList<>();
        mListInsurance.addAll(data);
        notifyDataSetChanged();
    }

}
