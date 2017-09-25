package py.com.aseguradoratajy.tajydemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import py.com.aseguradoratajy.tajydemo.R;
import py.com.aseguradoratajy.tajydemo.models.Branches;
import py.com.aseguradoratajy.tajydemo.models.MapLocation;

/**
 * Created by Diego on 9/23/2017.
 */

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.BranchViewHolder> {

    private List<Branches> mBranchList = new ArrayList<>();
    private Context mContext;
    protected GoogleMap mGoogleMap;
    protected MapLocation mMapLocation;

    public BranchAdapter(List<Branches> branchesList, Context context) {
        mBranchList = branchesList;
        mContext = context;
    }


    public class BranchViewHolder extends RecyclerView.ViewHolder implements OnMapReadyCallback {
        TextView mBranchName;
        TextView mBranchAddress;
        TextView mBranchPhone;
        MapView mMap;
        FloatingActionButton mBranchCall;

        public BranchViewHolder(View itemView) {
            super(itemView);
            mBranchName = (TextView) itemView.findViewById(R.id.branch_item_name);
            mBranchAddress = (TextView) itemView.findViewById(R.id.branch_item_address);
            mBranchPhone = (TextView) itemView.findViewById(R.id.branch_item_phone);
            mBranchCall= (FloatingActionButton) itemView.findViewById(R.id.branch_item_fab);
            mMap = (MapView) itemView.findViewById(R.id.branch_item_map);

            mBranchCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    attemptCall(getItemAtPosition(getAdapterPosition()).getBranchPhone());
                }
            });

            //mMap.onCreate(null);
            mMap.getMapAsync(this);
        }

        public void setMapLocation(MapLocation mapLocation) {
            mMapLocation = mapLocation;
            // If the map is ready, update its content.
            if (mGoogleMap != null) {
                updateMapContents();
            }
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mGoogleMap = googleMap;
            MapsInitializer.initialize(mContext);

            // If we have map data, update the map content.
            if (mMapLocation != null) {
                updateMapContents();
            }
        }

        protected void updateMapContents() {
            // Since the mapView is re-used, need to remove pre-existing mapView features.
            mGoogleMap.clear();

            // Update the mapView feature data and camera position.
            mGoogleMap.addMarker(new MarkerOptions().position(mMapLocation.getCenter()));

            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mMapLocation.getCenter(), 18f);
            mGoogleMap.moveCamera(cameraUpdate);
        }
    }

    private void attemptCall(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        mContext.startActivity(intent);
    }

    public Branches getItemAtPosition(int position) {
        return mBranchList.get(position);
    }

    @Override
    public BranchAdapter.BranchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.branch_item, parent, false);
        BranchViewHolder viewHolder = new BranchViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BranchAdapter.BranchViewHolder holder, int position) {
        Branches branches = mBranchList.get(position);
        MapLocation mapLocation = new MapLocation(branches.getBranchName(), branches.getBranchLocation());
        holder.mBranchName.setText(branches.getBranchName().toUpperCase());
        holder.mBranchAddress.setText(branches.getBranchAddress().toUpperCase());
        holder.mBranchPhone.setText(branches.getBranchPhone().toUpperCase());
        holder.setMapLocation(mapLocation);
    }

    @Override
    public int getItemCount() {
        return mBranchList.size();
    }
}
