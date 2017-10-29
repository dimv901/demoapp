package py.com.aseguradoratajy.tajydemo.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.activities.MainActivity;
import py.com.aseguradoratajy.tajydemo.entities.Insurance;
import py.com.aseguradoratajy.tajydemo.models.MapPolicyAccount;
import py.com.aseguradoratajy.tajydemo.repositories.InsuranceRepository;
import py.com.aseguradoratajy.tajydemo.utils.RecyclerItemClickListener;
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
    public void onBindViewHolder(final InsuranceViewHolder holder, int position) {
        final Insurance i = mListInsurance.get(position);
        holder.mPolicyCode.setText(i.getPolicy().toUpperCase());
        holder.mPolicyType.setText(i.getSectionDescription());
        holder.mDateFrom.setText(i.getDateFrom());
        holder.mDateTo.setText(i.getDateTo());
        holder.mIcon.setBackgroundResource(MapPolicyAccount.policyIconType(i.getSectionCode()));


    }

    @Override
    public int getItemCount() {
        return mListInsurance.size();
    }

    class InsuranceViewHolder extends RecyclerView.ViewHolder {
        TextView mPolicyCode;
        TextView mPolicyType;
        TextView mDateFrom;
        TextView mDateTo;
        ImageView mIcon;
        ImageButton mDetailsButton;

        InsuranceViewHolder(View view) {
            super(view);
            mPolicyCode = (TextView) view.findViewById(R.id.policy_code_value);
            mPolicyType = (TextView) view.findViewById(R.id.policy_type_value);
            mDateFrom = (TextView) view.findViewById(R.id.date_from_policy_value);
            mDateTo = (TextView) view.findViewById(R.id.date_to_policy_value);
            mIcon = (ImageView) view.findViewById(R.id.policy_type_icon);
            mDetailsButton = (ImageButton) view.findViewById(R.id.details_action_button);
            mDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    optionsDialog(getItemAtPosition(getAdapterPosition()));
                }
            });
        }
    }

    public void setData(List<Insurance> data) {
        mListInsurance = new ArrayList<>();
        mListInsurance.addAll(data);
        notifyDataSetChanged();
    }

    public Insurance getItemAtPosition(int position) {
        return mListInsurance.get(position);
    }

    private void optionsDialog(final Insurance insuranceObject) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setIcon(R.mipmap.ic_touch_app_black_36dp);
        builder.setTitle(mContext.getString(R.string.tag_options))
                .setItems(R.array.policy_options, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0:
                                if (insuranceObject != null) {
                                    List<Insurance> insuranceList = InsuranceRepository.getInsuranceByPolicyNumber(insuranceObject.getPolicy());
                                    if (insuranceList.size() == 0) {
                                        Utiles.getToast(mContext, mContext.getString(R.string.policy_details_is_empty));
                                    } else {
                                        detailsPolicies(insuranceList);
                                    }
                                } else {
                                    Utiles.getToast(mContext, mContext.getString(R.string.error_get_object_position));
                                }
                                break;
                            case 1:
                                if (insuranceObject != null) {
                                    accountPolicyDetails(insuranceObject);
                                } else {
                                    Utiles.getToast(mContext, mContext.getString(R.string.error_get_object_position));
                                }
                                break;
                        }

                    }
                }).setNegativeButton(R.string.tag_back, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.setCancelable(false);
        builder.show();

    }


    private void accountPolicyDetails(Insurance insurance) {
        //StringBuilder stringBuilder = new StringBuilder();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View dialogView = layoutInflater.inflate(R.layout.account_details_dialog, null);
        TextView mCiAccountTextView = (TextView) dialogView.findViewById(R.id.ci_account_value);
        TextView mRucAccountTextView = (TextView) dialogView.findViewById(R.id.ruc_account_value);
        TextView mParticularAddressTextView = (TextView) dialogView.findViewById(R.id.particular_address_value);
        TextView mCommercialPhoneTextView = (TextView) dialogView.findViewById(R.id.commercial_phone_account);
        mCiAccountTextView.setText(insurance.getIdentifyCard());
        mRucAccountTextView.setText(insurance.getRuc());
        mParticularAddressTextView.setText(insurance.getParcitularAddress());
        mCommercialPhoneTextView.setText(insurance.getComercialPhone());

        builder.setPositiveButton(mContext.getString(R.string.tag_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setView(dialogView);
        builder.create();
        builder.setCancelable(false);
        builder.show();

    }

    private void detailsPolicies(List<Insurance> insurances) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View dialogView = layoutInflater.inflate(R.layout.policy_details_dialog, null);
        InsuranceDetailsAdapter mAdapter;
        RecyclerView mPackageRecyclerView = (RecyclerView) dialogView.findViewById(R.id.policies_recyclerView);
        mPackageRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new InsuranceDetailsAdapter(mContext, new ArrayList<Insurance>());
        mPackageRecyclerView.setAdapter(mAdapter);
        mPackageRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        mPackageRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPackageRecyclerView.setHasFixedSize(true);
        mPackageRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(mContext, mPackageRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
        mAdapter.setData(insurances);
        builder.setView(dialogView);
        builder.setPositiveButton(mContext.getString(R.string.label_accept), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create();
        builder.setCancelable(false);
        builder.show();

    }
}
