package pamtech.com.celebrityapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pamtech.com.celebrityapp.Celebrity;
import pamtech.com.celebrityapp.R;
import pamtech.com.celebrityapp.db.DBHelper;

public class FragmentB extends Fragment {
    private static final String TAG = "FragmentB";
    FragmentInterface passName;
    private ImageView mImageView;
    private TextView mTextView;
    DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.celeb_detail, container, false);
        Log.d(TAG, "onCreateView: inflated");

        mImageView = v.findViewById(R.id.celeb_imageView);
        mTextView = v.findViewById(R.id.desc_textView);

        return v;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInterface) {
            passName = (FragmentInterface) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        passName = null;
    }

    public void showDetail(String name){
        dbHelper = new DBHelper(getContext());
        Celebrity celebrity;
        Log.d(TAG, "showDetail: name received from Fragment A " + name);
        celebrity = dbHelper.getCeleb(name);

        mImageView.setImageResource(celebrity.getImageResId());
        mTextView.setText(celebrity.getDescription());

    }
}
