package pamtech.com.weatherapi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pamtech.com.weatherapi.R;
import pamtech.com.weatherapi.models.Weather;

public class WeatherDataAdapter extends RecyclerView.Adapter<WeatherDataViewHolder> {
    private static final String TAG = "WeatherDataAdapter";
    Weather forecast;
    Context mContext;

    List<pamtech.com.weatherapi.models.List> dayForecast;
    int listSize;

    public WeatherDataAdapter(Weather forecast, Context context) {
        this.forecast = forecast;
        mContext = context;
        dayForecast = forecast.getList();
        listSize = dayForecast.size();
        Log.d(TAG, "WeatherDataAdapter: " + listSize);
    }

    @NonNull
    @Override
    public WeatherDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item_list,parent,false);

        return new WeatherDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherDataViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
       holder.tempTextView.setText(String.valueOf(dayForecast.get(position).getMain().getTemp()));
       holder.weatherTextView.setText(String.valueOf(dayForecast.get(position).getWeather().get(0).getMain()));
       holder.weatherImg.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + listSize);
        return listSize;
    }
}
