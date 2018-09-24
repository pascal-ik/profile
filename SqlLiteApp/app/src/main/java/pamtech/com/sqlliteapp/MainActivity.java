package pamtech.com.sqlliteapp;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private static final String TAG = "MainActivityTag";
/*    LocalBroadcastManager mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);//new local broadcast manager*/

    EditText emp_id;
    EditText emp_fname;
    EditText emp_mi;
    EditText emp_lname;
    EditText emp_phone;
    Button save_emp;
    Button show_emp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emp_id = findViewById(R.id.id_editText);
        emp_fname = findViewById(R.id.fname_editText);
        emp_mi = findViewById(R.id.mi_editText);
        emp_lname = findViewById(R.id.lname_editText);
        emp_phone = findViewById(R.id.phone_editText);

        save_emp = findViewById(R.id.save_button);
        show_emp = findViewById(R.id.show_button);


        IntentFilter filter = new IntentFilter("pamtech.com.sqlliteapp");
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void saveEmployee(View view){

/*        final Employee[] current = {null};*/

        new Thread(new Runnable() {


            @Override
            public void run() {
                try{
                    Thread.sleep(0);
                    Employee employee = new Employee(emp_id.getText().toString(), emp_fname.getText().toString(), emp_mi.getText().toString(), emp_lname.getText().toString(), emp_phone.getText().toString());

                    //broadcast employee
                    LocalBroadcastManager local = LocalBroadcastManager.getInstance(MainActivity.this);

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                    dataBaseHelper.addNewEmployee(employee);

                    ComponentName cm = new ComponentName("pamtech.com.sqlliteapp", "pamtech.com.sqlliteapp.MyReceiver");
                    //send person using local broadcast
                    Intent person = new Intent(MainActivity.this, MyReceiver.class);//new intent
                    Bundle bundle = new Bundle();//new bundle to pass object
                    person.setAction("pamtech.com.sqlliteapp");
                    bundle.putParcelable("person",employee);
                    person.putExtras(bundle);
                    person.setComponent(cm);
                    Log.d(TAG, "run: is component working?");
                    local.sendBroadcast(person);//broadcast person
                    Log.d(TAG, "run: local broadcast sent");
                    //receiver declared statically inside manifest


                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void showEmployee(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<Employee> employees = dataBaseHelper.getEmployees();//create array list of employee objects and fill list with all the employees in the DB

                //display arrayList in log
                for(Employee employee : employees){
                    Log.d(TAG, "showEmployee: " + employee.getFirstName() + " " + employee.getLastName());
                }

                //pass list to SecondActivity using parcelable
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);//create new intent
                Bundle bundle = new Bundle();//create new bundle
                ArrayList<Employee> employees1 = new ArrayList<>(employees);//create arrayList of employee objects from list of employee objects
                bundle.putParcelableArrayList("employee", employees1);//pass list to bundle
                intent.putExtras(bundle);//pass bundle to second activity
                MainActivity.this.startActivity(intent);//start second activity


            }
        }).start();//start thread




    }

   private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle data = intent.getExtras();
            assert data != null;
            Employee employeeBun = data.getParcelable("person");
            Log.d(TAG, "onReceive: we have" + employeeBun);
        }
    };



}
