package pamtech.com.weatherapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pamtech.com.weatherapi.R;

public class WeatherDataViewHolder extends RecyclerView.ViewHolder{

    ImageView weatherImg;
    TextView tempTextView;
    TextView weatherTextView;
    public WeatherDataViewHolder(View itemView) {
        super(itemView);
        weatherImg = itemView.findViewById(R.id.weather_image_view);
        tempTextView = itemView.findViewById(R.id.temp_text_view);
        weatherTextView = itemView.findViewById(R.id.weather_text_view);
    }
}
