package pamtech.com.celebrityapp.fragments;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import pamtech.com.celebrityapp.Celebrity;
import pamtech.com.celebrityapp.R;
import pamtech.com.celebrityapp.db.DBHelper;

public class FragmentA extends Fragment {

    private static final String TAG = "FragmentA";
    ListView celebNames;

    FragmentInterface passName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.celeb_list,container, false);
        Log.d(TAG, "onCreateView: layout inflated");
        
        DBHelper dbHelper = new DBHelper(getContext());

        celebNames = v.findViewById(R.id.namesLV);

        ArrayList<String> names = (ArrayList<String>) dbHelper.getCelebNames();

        ListAdapter showNames = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_list_item_1,names);

        celebNames.setAdapter(showNames);

        celebNames.setOnItemClickListener((parent, view, position, id) -> {
            if(passName != null){
                String current = (String) showNames.getItem(position);
                assert current != null;
                passName.com(current);//pass name to activity
                Log.d(TAG, "onCreateView: " + current);
            }
        });

        return v;
    }

    public void setCom(FragmentInterface passName){
        this.passName = passName;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //check if activity implement interface
        if (context instanceof FragmentInterface) {
            passName = (FragmentInterface) context;
        }
        else{
            throw new RuntimeException(context.toString() + "must implement FragmentInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        passName = null;
    }


}
