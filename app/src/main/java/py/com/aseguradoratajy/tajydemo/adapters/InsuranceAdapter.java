package py.com.aseguradoratajy.tajydemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.models.Insurance;

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
        holder.mNumber.setText(i.getNumber().toUpperCase());
        holder.mDescription.setText(i.getDescription().toUpperCase());
        holder.mSubDescription.setText(i.getSubdescription().toUpperCase());
        if (i.isActive()) {
            holder.mStatus.setText("VIGENTE");
        } else {
            holder.mStatus.setText("EXPIRADO");

        }
        holder.mIcon.setBackgroundResource(i.getIcon());

    }

    @Override
    public int getItemCount() {
        return mListInsurance.size();
    }

    public class InsuranceViewHolder extends RecyclerView.ViewHolder {
        TextView mSubDescription;
        TextView mDescription;
        TextView mStatus;
        TextView mNumber;
        ImageView mIcon;

        public InsuranceViewHolder(View view) {
            super(view);
            mNumber = (TextView) view.findViewById(R.id.insurance_number);
            mDescription = (TextView) view.findViewById(R.id.insurance_description);
            mSubDescription = (TextView) view.findViewById(R.id.insurance_subdescription);
            mStatus = (TextView) view.findViewById(R.id.insurance_status);
            mNumber = (TextView) view.findViewById(R.id.insurance_number);
            mIcon = (ImageView) view.findViewById(R.id.insurance_icon);
        }
    }

}
