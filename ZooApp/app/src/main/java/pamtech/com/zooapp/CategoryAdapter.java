package pamtech.com.zooapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<String> {

    public CategoryAdapter(@NonNull Context context, ArrayList<String> categories) {
        super(context, 0, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        //check if view is already inflated
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);//if not, inflate view
        }

        String currentCategory = getItem(position);//get position of current category

        TextView category = (TextView) listItemView.findViewById(R.id.categoryTV);

        category.setText(currentCategory);//set the text on the category

        return listItemView;
    }
}
