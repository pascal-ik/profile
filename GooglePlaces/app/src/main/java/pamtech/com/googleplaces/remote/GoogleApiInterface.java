package pamtech.com.googleplaces.remote;

import io.reactivex.Observable;
import io.reactivex.Single;
import pamtech.com.googleplaces.models.NearBy;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApiInterface {
    @GET("place/nearbysearch/json?")
    Observable<NearBy> getNearByPlaces(@Query ("location") String location, @Query("radius") int radius, @Query("key") String key);
/*    @GET("api/distancematrix/json")
    Observable<>*/
}
