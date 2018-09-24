package pamtech.com.weatherapi.models.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient {

    private static Retrofit ourInstance;

    static final String BASE_URL ="https://samples.openweathermap.org/";
    static final String APP_ID = "b6907d289e10d714a6e88b30761fae22";

    public static Retrofit getInstance() {
        if(ourInstance == null){
            ourInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }


}
