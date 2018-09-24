package pamtech.com.zooapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private static final String TAG = "CategoryActivity";
    
    ListView categoriesLV;

    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        categoriesLV = findViewById(R.id.catList);//bind views

        mDatabaseHelper = new DatabaseHelper(getApplicationContext());
        ArrayList<String> categories = (ArrayList<String>) mDatabaseHelper.getCategories();

        ListAdapter displayCats = new ArrayAdapter<String>(CategoryActivity.this, android.R.layout.simple_list_item_1, categories);

        categoriesLV.setAdapter(displayCats);//display categories on screen

        categoriesLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent message = new Intent(CategoryActivity.this, ContentsActivity.class);
                String currentCategory = (String) categoriesLV.getItemAtPosition(position);
                message.putExtra("category", currentCategory);//pass category name so we can obtain all the animals in that category

                startActivity(message);
                Log.d(TAG, "onItemClick: load animals in selected category");
            }
        });
    }



}
