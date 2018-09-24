package pamtech.com.celebrityapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import pamtech.com.celebrityapp.db.DBHelper;
import pamtech.com.celebrityapp.fragments.FragmentA;
import pamtech.com.celebrityapp.fragments.FragmentActionListener;
import pamtech.com.celebrityapp.fragments.FragmentB;
import pamtech.com.celebrityapp.fragments.FragmentInterface;

public class MainActivity extends AppCompatActivity implements FragmentInterface {

    private static final String TAG = "MainActivity";
    private FragmentA mFragmentA;
    private FragmentB mFragmentB;
    //private Fragment listFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //listFragment = findViewById(R.id.fragment_list);
        makeCelebs();





        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        mFragmentA = new FragmentA();
        mFragmentA.setCom(this);

        fragmentTransaction.replace(R.id.fragment_list, mFragmentA);
        fragmentTransaction.commit();
        Log.d(TAG, "onCreate: done");
    }

    public void makeCelebs() {
        new Thread(() -> {
            try {
                Thread.sleep(0);
                Celebrity sam = new Celebrity(1, (String) getText(R.string.sam), (String) getText(R.string.sam_desc), R.drawable.shia);
                Celebrity frank = new Celebrity(2, (String) getText(R.string.megatron), (String) getText(R.string.megatron_desc), R.drawable.frank);
                Celebrity peter = new Celebrity(3, (String) getText(R.string.prime), (String) getText(R.string.prime_desc), R.drawable.peter);
                Celebrity megan = new Celebrity(4, (String) getText(R.string.mikaela), (String) getText(R.string.mikaela_desc), R.drawable.megan);

                DBHelper dbHelper = new DBHelper(MainActivity.this);

                dbHelper.addCelebrity(sam);
                dbHelper.addCelebrity(frank);
                dbHelper.addCelebrity(peter);
                dbHelper.addCelebrity(megan);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


    @Override
    public void com(String name) {
        Toast.makeText(this,name, Toast.LENGTH_SHORT).show();
        mFragmentB = (FragmentB) getSupportFragmentManager().findFragmentById(R.id.fragment_detail);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_detail,mFragmentB)
                .commit();

        assert mFragmentB != null;
        mFragmentB.showDetail(name);

    }
}
