package pamtech.com.weatherapi.models.retrofit;




import io.reactivex.Observable;
import pamtech.com.weatherapi.models.Weather;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherInterface {

    @GET("data/2.5/forecast")
    Observable<Weather> getWeather(@Query("zip")String zip, @Query("appid") String appId);

}
