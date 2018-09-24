package pamtech.com.bbva.models.retrofit;

import io.reactivex.Observable;
import pamtech.com.bbva.models.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApiInterface {
    @GET("maps/api/place/textsearch/json?")
    Observable<Response> getResult(@Query("point") String point, @Query("location") String location, @Query("radius") int radius, @Query("key") String key);

}
