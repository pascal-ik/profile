package pamtech.com.googleplaces.remote;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GoogleApiClient {
    private static Retrofit ourInstance;

    public static Retrofit getInstance() {
        if(ourInstance == null){
            ourInstance = new Retrofit.Builder()
                    .baseUrl("https://maps.googleapis.com/maps/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return ourInstance;
    }
}
