package compapered.httpsgithub.firebase;

import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by LeeJongHyun on 2017-09-07.
 */

public interface ApiInterface {
    @POST
    Call<Time> repo(@Query("hour")int hour , @Query("minute") int minute);
}
