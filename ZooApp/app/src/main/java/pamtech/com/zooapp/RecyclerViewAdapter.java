package pamtech.com.zooapp;

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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";
    
    List<Animal> animals;
    public RecyclerViewAdapter(List<Animal> animals){
        Log.d(TAG, "RecyclerViewAdapter: new object made");
        this.animals = animals;

    }


    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: layout inflated");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Animal animal = animals.get(position);//get animal at current position


        if(animal.getImg_path() == 1){
            holder.animalPicture.setImageResource(animal.getImg_path());
        }
        else{
            holder.animalPicture.setImageResource(animal.getImg_path());
        }

        holder.animalName.setText(animal.getName());
        holder.animalDescription.setText(animal.getDescription());


    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView animalName;
        TextView animalDescription;
        ImageView animalPicture;

        public ViewHolder(View itemView) {
            super(itemView);
            animalName = itemView.findViewById(R.id.nameTV);
            animalDescription = itemView.findViewById(R.id.descTV);
            animalPicture = itemView.findViewById(R.id.imageView);
        }
    }
}
