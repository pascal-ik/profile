package pamtech.com.zooapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class ContentsActivity extends AppCompatActivity {

    RecyclerView animalsWithin;
    private static final String TAG = "ContentsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        Intent receive = getIntent();

        animalsWithin = findViewById(R.id.recyclerView);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(databaseHelper.getAnimals(receive.getStringExtra("category")));
        animalsWithin.setAdapter(adapter);
        animalsWithin.setLayoutManager(new LinearLayoutManager(this));
    }
}
