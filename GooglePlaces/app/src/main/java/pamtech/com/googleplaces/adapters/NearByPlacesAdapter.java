package pamtech.com.googleplaces.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pamtech.com.googleplaces.R;
import pamtech.com.googleplaces.models.NearBy;
import pamtech.com.googleplaces.models.Result;

public class NearByPlacesAdapter extends RecyclerView.Adapter<NearByPlacesAdapter.NearByViewHolder> {
    private static final String TAG = "NearByPlacesAdapter";
    NearBy mNearBy;
    Context mContext;
    List<Result> mResults;
    int size;

    public NearByPlacesAdapter(NearBy nearBy, Context context) {

        mNearBy = nearBy;
        mContext = context;
        mResults = mNearBy.getResults();
        size = mResults.size();
        Log.d(TAG, "NearByPlacesAdapter: constructor");
    }

    @NonNull
    @Override
    public NearByViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: inflate view");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list,parent,false);
        return new NearByViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NearByViewHolder holder, int position) {
      holder.marker.setImageResource(R.drawable.ic_local_hospital_black_24dp);
      holder.placeName.setText(String.valueOf(mResults.get(position).getName()));
      holder.placeAddress.setText(String.valueOf(mResults.get(position).getPlusCode().getCompoundCode()));
    }

    @Override
    public int getItemCount() {
        return size;
    }

    class NearByViewHolder extends RecyclerView.ViewHolder{

        ImageView marker;
        TextView placeName;
        TextView placeAddress;

        public NearByViewHolder(View itemView) {
            super(itemView);
            marker = itemView.findViewById(R.id.place_image_view);
            placeName = itemView.findViewById(R.id.tvPlace);
            placeAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
