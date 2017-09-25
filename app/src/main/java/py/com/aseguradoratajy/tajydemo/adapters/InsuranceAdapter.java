package py.com.aseguradoratajy.tajydemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentListItem;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;

import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.models.Insurance;
import py.com.aseguradoratajy.tajydemo.models.InsuranceDetail;
import py.com.aseguradoratajy.tajydemo.utils.Utiles;

/**
 * Created by Diego on 9/24/2017.
 */

public class InsuranceAdapter extends ExpandableRecyclerAdapter<InsuranceAdapter.InsuranceViewHolder, InsuranceAdapter.DetailViewHolder> implements ExpandableListView.OnChildClickListener {
    private List<? extends ParentListItem> parentItemList;
    private Context mContext;


    /**
     * Primary constructor. Sets up {@link #mParentItemList} and {@link #mItemList}.
     * <p>
     * Changes to {@link #mParentItemList} should be made through add/remove methods in
     * {@link ExpandableRecyclerAdapter}
     *
     * @param parentItemList List of all {@link ParentListItem} objects to be
     *                       displayed in the RecyclerView that this
     *                       adapter is linked to
     */
    public InsuranceAdapter(@NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
    }

    public InsuranceAdapter(Context context, @NonNull List<? extends ParentListItem> parentItemList) {
        super(parentItemList);
        mContext = context;
        this.parentItemList = parentItemList;
    }

    @Override
    public InsuranceViewHolder onCreateParentViewHolder(ViewGroup parentViewGroup) {
        View view = LayoutInflater.from(parentViewGroup.getContext()).inflate(R.layout.insurance_item, parentViewGroup, false);
        return new InsuranceViewHolder(view);
    }

    @Override
    public DetailViewHolder onCreateChildViewHolder(ViewGroup childViewGroup) {
        View view = LayoutInflater.from(childViewGroup.getContext()).inflate(R.layout.insurance_detail_item, childViewGroup, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(InsuranceViewHolder parentViewHolder, int position, ParentListItem parentListItem) {
        Insurance mInsurance = (Insurance) parentListItem;
        parentViewHolder.mDescription.setText(mInsurance.getNumber());
        parentViewHolder.mIcon.setBackgroundResource(mInsurance.getIcon());
        if (!parentViewHolder.isExpanded()) {
            parentViewHolder.mShowDetail.setImageResource(R.mipmap.ic_keyboard_arrow_down_black_24dp);
        } else {
            parentViewHolder.mShowDetail.setImageResource(R.mipmap.ic_keyboard_arrow_up_black_24dp);
        }
    }

    @Override
    public void onBindChildViewHolder(DetailViewHolder childViewHolder, int position, Object childListItem) {
        InsuranceDetail det = (InsuranceDetail) childListItem;
        childViewHolder.mExpiration.setText(det.getInsuranceExpiration());
        childViewHolder.mAmount.setText("Gs. " + Utiles.formatNumber("25000000"));
        childViewHolder.mMark.setText(det.getDescription());
        childViewHolder.mModel.setText(det.getModel());
    }



    public class InsuranceViewHolder extends ParentViewHolder {

        public TextView mDescription;
        public ImageView mIcon;
        public ImageView mShowDetail;

        public InsuranceViewHolder(View itemView) {
            super(itemView);
            mIcon = (ImageView) itemView.findViewById(R.id.insurance_icon);
            mShowDetail = (ImageView) itemView.findViewById(R.id.insurance_icon_detail);
            mDescription = (TextView) itemView.findViewById(R.id.insurance_description);
        }

        @Override
        public boolean isExpanded() {
            return super.isExpanded();
        }

        @Override
        public void onClick(View v) {
            if (!isExpanded()) {
                mShowDetail.setImageResource(R.mipmap.ic_keyboard_arrow_up_black_24dp);
            } else {
                mShowDetail.setImageResource(R.mipmap.ic_keyboard_arrow_down_black_24dp);
            }
            super.onClick(v);
        }

    }

    public class DetailViewHolder extends ChildViewHolder {

        public TextView mMark;
        public TextView mModel;
        public TextView mAmount;
        public TextView mExpiration;

        public DetailViewHolder(View itemView) {
            super(itemView);
            mMark = (TextView) itemView.findViewById(R.id.item_insurance_detail_mark);
            mModel = (TextView) itemView.findViewById(R.id.item_insurance_detail_model);
            mAmount = (TextView) itemView.findViewById(R.id.item_insurance_detail_amount);
            mExpiration = (TextView) itemView.findViewById(R.id.item_insurance_detail_expiration);

        }


    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}
