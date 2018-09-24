package pamtech.com.sqlliteapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";

    private ArrayList<Employee> employees;

    public RecyclerAdapter(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: ");
        Employee e = employees.get(position);
        holder.firstName.setText(e.firstName);
        holder.middleI.setText(e.middleInitial);
        holder.lastName.setText(e.lastName);
        holder.id.setText(e.getId());
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView middleI;
        TextView lastName;
        TextView id;

        public ViewHolder(View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.first_name_textView);
            middleI = itemView.findViewById(R.id.middle_initial_textView);
            lastName = itemView.findViewById(R.id.last_name_textView);
            id = itemView.findViewById(R.id.id_textView);
        }
    }
}
