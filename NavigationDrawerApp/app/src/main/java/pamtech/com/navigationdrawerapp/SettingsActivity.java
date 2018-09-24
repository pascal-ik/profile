package pamtech.com.navigationdrawerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mButton = findViewById(R.id.b_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToBrowser();
            }
        });

    }

    public void goToBrowser(){
        Intent web = new Intent(this, BrowserActivity.class);
        startActivity(web);
    }

}
