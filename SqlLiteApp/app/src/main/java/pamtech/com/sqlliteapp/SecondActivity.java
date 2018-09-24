package pamtech.com.sqlliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    //ListView emp_list;
    RecyclerView persons_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        //bind listView
        //emp_list = findViewById(R.id.emp_listview);
        persons_view = findViewById(R.id.recyclerview);

        //create bundle and get employee list from MainActivity
        Bundle bundle = getIntent().getExtras();
        Log.d(TAG, "onCreate: bundle received");

        //if bundle is not null, proceed to display list in listView
        if (bundle != null) {
            ArrayList<Employee> employees = bundle.getParcelableArrayList("employee");

            //check that list is correct
            for(Employee e: employees){
               System.out.println(e.toString());
            }
            Log.d(TAG, "onCreate: list created");
            assert employees != null;
           // ListAdapter myList = new ArrayAdapter<Employee>(this, android.R.layout.simple_list_item_1, employees);
            //emp_list.setAdapter(myList);
            RecyclerAdapter display = new RecyclerAdapter(employees);
            Log.d(TAG, "onCreate: employees not empty");
            persons_view.setAdapter(display);
            persons_view.setLayoutManager(new LinearLayoutManager(this));
        }





    }
}
