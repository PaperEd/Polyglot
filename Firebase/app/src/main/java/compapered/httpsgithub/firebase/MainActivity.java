package compapered.httpsgithub.firebase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button setTImeClick = (Button)findViewById(R.id.setTimeClick);

        setTImeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit client = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                ApiInterface service = client.create(ApiInterface.class);
                TimePicker setTime = (TimePicker)findViewById(R.id.setTime);
                String refreshedToken = FirebaseInstanceId.getInstance().getToken();
                Log.d("Refreshed","RefreshedToken : " + refreshedToken);
                Call<Time> call = service.repo(refreshedToken,setTime.getHour(),setTime.getMinute());
                call.enqueue(new Callback<Time>() {
                    @Override
                    public void onResponse(Response<Time> response, Retrofit retrofit) {
                        if(response.isSuccess()){
//                            Time time = response.body();
                            Toast.makeText(getBaseContext(),"실행 잘되요",Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(getBaseContext(),"리스폰스가 안되잖아",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(getBaseContext(),"안되잖아",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
