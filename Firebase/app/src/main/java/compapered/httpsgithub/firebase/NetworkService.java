package compapered.httpsgithub.firebase;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by dwg76 on 2017-09-01.
 */

public interface NetworkService {
    @POST("/getAlarm")
    Call<Time> post_time(@Body Time time);
}
