package compapered.httpsgithub.firebase;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by dwg76 on 2017-09-01.
 */

public class FirebaseID extends FirebaseInstanceIdService{
    private static final String TAG = "FirebaseID";

    @Override
    public void onTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
    }

}
