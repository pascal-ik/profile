package pamtech.com.weatherapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pamtech.com.weatherapi.adapter.WeatherDataAdapter;
import pamtech.com.weatherapi.models.Weather;
import pamtech.com.weatherapi.models.retrofit.WeatherApiClient;
import pamtech.com.weatherapi.models.retrofit.WeatherInterface;
import retrofit2.Retrofit;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    static final String BASE_URL ="https://samples.openweathermap.org/";
    static final String APP_ID = "b6907d289e10d714a6e88b30761fae22";
    String zipCode;
    private TextView cityTextView;
    private RecyclerView weatherRV;
    CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    WeatherInterface mWeatherInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityTextView = findViewById(R.id.city_textView);
        weatherRV = findViewById(R.id.recyclerView);

        Retrofit retrofit = WeatherApiClient.getInstance();
        mWeatherInterface = retrofit.create(WeatherInterface.class);

        Intent message = getIntent();
        zipCode = message.getStringExtra("zip");
        Toast.makeText(this, zipCode, Toast.LENGTH_SHORT).show();
        fetchData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCompositeDisposable.clear();
    }

    private void fetchData(){
        mCompositeDisposable.add(mWeatherInterface.getWeather(zipCode, APP_ID)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(weathers -> {
            Log.d(TAG, "accept: " + weathers.toString());
            cityTextView.setText(String.valueOf(weathers.getCity().getName()));
            displayData(weathers);
        }));
    }

    private void displayData(Weather weather){
        WeatherDataAdapter adapter = new WeatherDataAdapter(weather,this);
        weatherRV.setAdapter(adapter);
        weatherRV.setLayoutManager(new LinearLayoutManager(this));
    }
}
