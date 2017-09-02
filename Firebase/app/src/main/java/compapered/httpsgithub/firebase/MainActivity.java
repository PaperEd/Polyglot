package compapered.httpsgithub.firebase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private NetworkService networkService;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApplicationController applicationController = ApplicationController.getInstance();
        applicationController.buildNetworkService("localhost",3000);
        networkService = ApplicationController.getInstance().getNetworkService();
        Button setTImeClick = (Button)findViewById(R.id.setTimeClick);
        setTImeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Time time = new Time();
                JSONObject sendingObject = new JSONObject();
                TimePicker setTime = (TimePicker)findViewById(R.id.setTime);
                int messagingHour = setTime.getHour();
                int messagingMinute = setTime.getMinute();
                time.setMinute(messagingMinute);
                time.setHour(messagingHour);
                try {
                    sendingObject.put("hour",messagingHour);
                    sendingObject.put("minute",messagingMinute);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Call<Time> timeCall = networkService.post_time(time);
                timeCall.enqueue(new Callback<Time>() {
                    @Override
                    public void onResponse(Response<Time> response, Retrofit retrofit) {
                        if(response.isSuccess()){
                            Toast.makeText(getBaseContext(),"됐어요",Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(getBaseContext(),"안되는데",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
                try {
                    sendingObject.put("hour",messagingHour);
                    sendingObject.put("minute",messagingMinute);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getBaseContext(),"실행 잘되요",Toast.LENGTH_LONG).show();

            }
        });

    }
}
