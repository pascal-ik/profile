package pamtech.com.weatherapi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
@author Pascal Adjaero
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText zipCodeET;
    private Button forecastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zipCodeET = findViewById(R.id.zipcode_editText);
        forecastButton = findViewById(R.id.show_forecast_button);

        forecastButton.setOnClickListener(View ->{
            String zipCode = "";
            try{
                if(zipCodeET.getText().equals(null) || zipCodeET.getText().length() != 5){
                    Toast.makeText(MainActivity.this, "Please enter a valid Zip Code", Toast.LENGTH_SHORT).show();
                }
                else{
                    zipCode = zipCodeET.getText().toString();
                    Intent message = new Intent(MainActivity.this, WeatherActivity.class);
                    message.putExtra("zip", zipCode);
                    startActivity(message);
                }
            }
            catch (NullPointerException e){
                Log.d(TAG, "onCreate: " );
                e.printStackTrace();
            }
        });
    }
}
